package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa zarządzająca parą klientów oraz obsługę gry
 *
 * @author Tomasz Gajos
 */
public class ServerClientPairWorker extends Thread {

    boolean guesserNewGame = false;
    boolean word_GiverNewGame = false;
    boolean closedByWord_Giver = false;
    boolean closedByGuesser = false;
    private final ClientPair clientPair;
    private String phrase;
    PrintWriter writerGuesser;
    BufferedReader readerGuesser;
    PrintWriter writerWord_Giver;
    BufferedReader readerWord_Giver;
    private String codedPhrase;
    private int chances;
    ServerClient word_giver;
    ServerClient guesser;

    /**
     * Konstruktor klasy
     *
     * @param clientPair para klientó
     */
    public ServerClientPairWorker(ClientPair clientPair) {
        this.clientPair = clientPair;
    }

    /**
     * Uruchamia nową grę Losuje każdemu z graczy rolę i przekazuje im tę
     * informację
     */
    public synchronized void startNewGame() {
        chances = 10;
        Random random = new Random();
        boolean randomRole = random.nextBoolean();
        ServerClient word_giver = randomRole ? clientPair.getClient1() : clientPair.getClient2();
        ServerClient guesser = randomRole ? clientPair.getClient2() : clientPair.getClient1();

        writerGuesser = guesser.getWriter();
        readerGuesser = guesser.getReader();
        writerWord_Giver = word_giver.getWriter();
        readerWord_Giver = word_giver.getReader();

        writerGuesser.println("ROLE:GUESSER");
        writerWord_Giver.println("ROLE:WORD_GIVER");
    }

    /**
     * Sprawdza czy obaj gracze chcą rozpocząć nową grę Jeżeli obaj gracze są
     * chętni, uruchamia nową grę
     */
    public synchronized void checkNewGame() {
        if (guesserNewGame && word_GiverNewGame) {

            guesserNewGame = false;
            word_GiverNewGame = false;
            startNewGame();
        }
    }

    /**
     * Sprawdza, czy podana litera znajduje się w haśle
     *
     * @param phrase hasło
     * @param letter litera
     * @return boolean
     */
    public boolean checkLetter(String phrase, String letter) {
        return phrase.contains(letter);
    }

    /**
     * Koduje podane hasło, zastępując znaki podkreśleniami
     *
     * @param hasło
     */
    private void codePhrase(String phrase) {
        codedPhrase = "";
        for (char c : phrase.toCharArray()) {
            if (c == ' ') {
                codedPhrase += ' ';
            } else {
                codedPhrase += '_';
            }
        }
    }

    /**
     * Dekoduje zakodowane hasło zastępując podkreślenie pasującą literą w
     * odpowiednich miejcach
     *
     * @param guess
     */
    private void decodePhrase(String guess) {
        char[] updatedCodedPhrase = codedPhrase.toCharArray();
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) == guess.charAt(0)) {
                updatedCodedPhrase[i] = guess.charAt(0);
            }
        }
        codedPhrase = String.valueOf(updatedCodedPhrase);
    }

    /**
     * Uruchamia wątek i obsługuje wysyłanie komunikatów do graczy, oraz
     * otrzymywanie komunikatów od graczy
     */
    @Override
    public void run() {
        try {
            startNewGame();
            Thread guesserThread = new Thread(() -> {
                try {
                    while (!closedByGuesser) {
                        String guesserMessage = readerGuesser.readLine();
                        // Sprawdza, czy podana litera pasuje do hasła
                        if (guesserMessage != null && guesserMessage.startsWith("LETTERGUESS:")) {
                            String guess = guesserMessage.substring(12);
                            writerGuesser.println("LETTERGUESS:" + guess);
                            writerWord_Giver.println("LETTERGUESS:" + guess);
                            // Jeżeli pasuje, hasło zostaje zdekodowane, jeżeli wszystkie litery zostały zgadnięte nastepuje koniec gry, jeżeli nie wszystkie litery zostały zgadnięte wysyłana jest do klientów wiadomość oraz komunikat o poprawnej literze 
                            if (checkLetter(phrase, guess)) {
                                decodePhrase(guess);
                                if (phrase.equals(codedPhrase)) {
                                    writerGuesser.println("GAMEWON:" + phrase);
                                    writerWord_Giver.println("GAMEWON:" + phrase);
                                } else {
                                    writerGuesser.println("MESSAGE:" + guess + " to poprawna litera");
                                    writerWord_Giver.println("MESSAGE:" + guess + " to poprawna litera");
                                    writerGuesser.println("CORRECTLETTERGUESS:" + codedPhrase);
                                    writerWord_Giver.println("CORRECTLETTERGUESS:" + codedPhrase);
                                }
                            } // Jeżeli litera nie pasuje do hasła zmniejsza się ilość szans, jeżeli wszystkie szanse się skończyły następuje koniec gry, jeżeli się nie skończyły wysyłana jest do klientów wiadomość oraz komunikat o błędnej literze
                            else {
                                chances--;
                                if (chances == 0) {
                                    writerGuesser.println("GAMELOST:" + phrase);
                                    writerWord_Giver.println("GAMELOST:" + phrase);
                                } else {
                                    writerGuesser.println("MESSAGE:" + guess + " to niepoprawna litera");
                                    writerWord_Giver.println("MESSAGE:" + guess + " to niepoprawna litera");
                                    writerGuesser.println("INCORRECTLETTERGUESS:");
                                    writerWord_Giver.println("INCORRECTLETTERGUESS:");
                                }
                            }
                        } // Sprawdzane jest, czy obaj gracze chcą zacząć nową grę
                        else if (guesserMessage != null && guesserMessage.startsWith("NEWGAME:")) {
                            guesserNewGame = true;
                        } // Ustawia hasło i wysyła do klientów zakodowane hasło
                        else if (guesserMessage != null && guesserMessage.startsWith("PHRASE:")) {
                            phrase = guesserMessage.substring(7);
                            codePhrase(phrase);
                            writerGuesser.println("PHRASE:" + codedPhrase);
                            writerWord_Giver.println("PHRASE:" + codedPhrase);
                        } // Po wyjściu gracza po zakończeniu gry wysyłą do drugiego gracza komunikat o wyjściu drugiego gracza, oraz zamyka połączenie gracza, który wyszedł
                        else if (guesserMessage != null && guesserMessage.startsWith("END:")) {
                                writerWord_Giver.println("END:");
                            writerGuesser.println("ENDTHIS:");
                                closedByGuesser = true;
                            break;
                        } // Sprawdza czy podana odpowiedź pasuje do hasła
                        else if (guesserMessage != null && guesserMessage.startsWith("ANSWERGUESS:")) {
                            String answer = guesserMessage.substring(12);
                            // Jeżeli odpowiedź pasuje wysyłany jest do graczy komunikat o poprawnej odpowiedzi i o zakońćzeniu gry
                            if (answer.equals(phrase)) {
                                writerGuesser.println("CORRECTANSWERGUESS:");
                                writerWord_Giver.println("CORRECTANSWERGUESS:");
                                writerGuesser.println("GAMEWON:" + phrase);
                                writerWord_Giver.println("GAMEWON:" + phrase);
                            } // Jeżeli odpowiedź nie pasuje do hasła zmniejsza się ilość szans, jeżeli wszystkie szanse się skończyły następuje koniec gry, jeżeli się nie skończyły wysyłana jest do klientów wiadomość oraz komunikat o błędnej odpowiedzi
                            else {
                                chances--;
                                if (chances == 0) {
                                    writerGuesser.println("GAMELOST:" + phrase);
                                    writerWord_Giver.println("GAMELOST:" + phrase);
                                } else {
                                    writerGuesser.println("MESSAGE:" + answer + " to niepoprawna odpowiedź");
                                    writerWord_Giver.println("MESSAGE:" + answer + " to niepoprawna odpowiedź");
                                    writerGuesser.println("INCORRECTANSWERGUESS:");
                                    writerWord_Giver.println("INCORRECTANSWERGUESS:");
                                }
                            }
                        }
                        checkNewGame();
                    }
                } catch (IOException e) {
                    Logger.getLogger(ServerClientPairWorker.class.getName()).log(Level.SEVERE, null, e);
                }
            });

            Thread word_GiverThread = new Thread(() -> {
                try {
                    while (!closedByWord_Giver) {
                        String word_GiverMessage = readerWord_Giver.readLine();
                        // Ustawia hasło i wysyła do klientów zakodowane hasło
                        if (word_GiverMessage != null && word_GiverMessage.startsWith("PHRASE:")) {
                            phrase = word_GiverMessage.substring(7);
                            codePhrase(phrase);
                            writerGuesser.println("PHRASE:" + codedPhrase);
                            writerWord_Giver.println("PHRASE:" + codedPhrase);

                        } // Sprawdzane jest, czy obaj gracze chcą zacząć nową grę
                        else if (word_GiverMessage != null && word_GiverMessage.startsWith("NEWGAME:")) {
                            word_GiverNewGame = true;
                        } // Sprawdza, czy podana litera pasuje do hasła
                        else if (word_GiverMessage != null && word_GiverMessage.startsWith("LETTERGUESS:")) {
                            String guess = word_GiverMessage.substring(12);
                            writerGuesser.println("LETTERGUESS:" + guess);
                            writerWord_Giver.println("LETTERGUESS:" + guess);
                            // Jeżeli pasuje, hasło zostaje zdekodowane, jeżeli wszystkie litery zostały zgadnięte nastepuje koniec gry, jeżeli nie wszystkie litery zostały zgadnięte wysyłana jest do klientów wiadomość oraz komunikat o poprawnej literze 

                            if (checkLetter(phrase, guess)) {
                                decodePhrase(guess);
                                if (phrase.equals(codedPhrase)) {
                                    writerGuesser.println("GAMEWON:" + phrase);
                                    writerWord_Giver.println("GAMEWON:" + phrase);
                                } else {
                                    writerGuesser.println("MESSAGE:" + " to poprawna litera");
                                    writerWord_Giver.println("MESSAGE:" + " to poprawna litera");
                                    writerGuesser.println("CORRECTLETTERGUESS:" + codedPhrase);
                                    writerWord_Giver.println("CORRECTLETTERGUESS:" + codedPhrase);
                                }
                            } // Jeżeli litera nie pasuje do hasła zmniejsza się ilość szans, jeżeli wszystkie szanse się skończyły następuje koniec gry, jeżeli się nie skończyły wysyłana jest do klientów wiadomość oraz komunikat o błędnej literze
                            else {
                                chances--;
                                if (chances == 0) {
                                    writerGuesser.println("GAMELOST:");
                                    writerWord_Giver.println("GAMELOST:");
                                } else {
                                    writerGuesser.println("MESSAGE:" + guess + " to niepoprawna litera");
                                    writerWord_Giver.println("MESSAGE:" + guess + " to niepoprawna litera");
                                    writerGuesser.println("INCORRECTLETTERGUESS:");
                                    writerWord_Giver.println("INCORRECTLETTERGUESS:");
                                }
                            }
                        } // Po wyjściu gracza po zakończeniu gry wysyłą do drugiego gracza komunikat o wyjściu drugiego gracza, oraz zamyka połączenie gracza, który wyszedł
                        else if (word_GiverMessage != null && word_GiverMessage.startsWith("END:")) {
                                writerGuesser.println("END:");
                                writerWord_Giver.println("ENDTHIS:");

                                closedByWord_Giver = true;

                            break;
                        } // Sprawdza czy podana odpowiedź pasuje do hasła
                        else if (word_GiverMessage != null && word_GiverMessage.startsWith("ANSWERGUESS:")) {
                            String answer = word_GiverMessage.substring(12);
                            // Jeżeli odpowiedź pasuje wysyłany jest do graczy komunikat o poprawnej odpowiedzi i o zakońćzeniu gry

                            if (answer.equals(phrase)) {
                                writerGuesser.println("GAMEWON:" + phrase);
                                writerWord_Giver.println("GAMEWON:" + phrase);
                            } // Jeżeli odpowiedź nie pasuje do hasła zmniejsza się ilość szans, jeżeli wszystkie szanse się skończyły następuje koniec gry, jeżeli się nie skończyły wysyłana jest do klientów wiadomość oraz komunikat o błędnej odpowiedzi
                            else {
                                chances--;
                                if (chances == 0) {
                                    writerGuesser.println("GAMELOST:");
                                    writerWord_Giver.println("GAMELOST:");
                                } else {
                                    writerGuesser.println("MESSAGE:" + answer + " to niepoprawna odpowiedź");
                                    writerWord_Giver.println("MESSAGE:" + answer + " to niepoprawna odpowiedź");
                                    writerGuesser.println("INCORRECTANSWERGUESS");
                                    writerWord_Giver.println("INCORRECTANSWERGUESS");
                                }
                            }
                        }
                        checkNewGame();
                    }
                } catch (IOException e) {
                    Logger.getLogger(ServerClientPairWorker.class.getName()).log(Level.SEVERE, null, e);
                }
            });
            guesserThread.start();
            word_GiverThread.start();

            guesserThread.join();
            word_GiverThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerClientPairWorker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                clientPair.getClient1().getReader().close();
                clientPair.getClient1().getWriter().close();
                clientPair.getClient1().getSocket().close();
                clientPair.getClient2().getReader().close();
                clientPair.getClient2().getWriter().close();
                clientPair.getClient2().getSocket().close();
            } catch (IOException ex) {
                Logger.getLogger(ServerClientPairWorker.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Błąd podczas zamykania strumieni");
            }
        }
    }
}
