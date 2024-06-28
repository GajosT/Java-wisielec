package client;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 * Klasa obsługująca komunikację między użytkownikiem i serwerem oraz
 * zarządzająca stanem gry
 *
 * @author Tomasz Gajos
 */
public class ClientWorker extends Thread {

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private JFrame currentFrame;
    private String lastLetter;
    private boolean game = true;
    private String codedPhrase;
    private String phrase;
    private String result;
    private int guessCount = 0;

    /**
     * Zwraca Socket połączenia
     *
     * @return Socket połączenia
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Zwraca obiekt do wysyłania danych
     *
     * @return PrintWriter do wysyłania danych
     */
    public PrintWriter getWriter() {
        return writer;
    }

    /**
     * Zwraca obiekt do odbierania danych
     *
     * @return BufferedReader do odbierania danych
     */
    public BufferedReader getReader() {
        return reader;
    }

    /**
     * Zwraca ostatnią zgadywana literę
     *
     * @return ostatnia zgadywana litera
     */
    public String getLastGuess() {
        return lastLetter;
    }

    /**
     * Zwraca poprawne hasło
     *
     * @return poprawne hasło
     */
    public String getPhrase() {
        return phrase;
    }

    /**
     * Zwraca wynik gry
     *
     * @return wynik gry
     */
    public String getResult() {
        return result;
    }

    /**
     * Zwraca ilość ruchów
     *
     * @return ilość ruchów
     */
    public int getGuessCount() {
        return guessCount;
    }

    /**
     * Tworzy instancję ClienWorker i inicjalizuje połączenie z serwerem
     *
     * @param okno aktualnie wyświetlane przez aplikację
     */
    public ClientWorker(JFrame okno) {
        try {
            socket = new Socket("localhost", 2002);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            currentFrame = okno;

        } catch (IOException ex) {
            Logger.getLogger(ClientWorker.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Błąd podczas otwierania połączenia z serwerem");
        }

    }

    /**
     * Kończy grę wysyłając komunikat do serwera i zamykając połączenie
     */
    public void endGame() {
        writer.println("END:");
    }

    /**
     * Zapisuje hasło na serwerze
     *
     * @param phrase hasło do zapisania
     */
    public void registerPhrase(String phrase) {
        writer.println("PHRASE:" + phrase);
    }

    /**
     * Wysyła zgadywaną literę do serwera
     *
     * @param letter zgadywana litera
     */
    public void sendLetterGuess(String letter) {
        writer.println("LETTERGUESS:" + letter);
    }

    /**
     * Wysyła zgadywaną odpowiedź do serwera
     *
     * @param answer zgadywana odpowiedź
     */
    public void sendAnswerGuess(String answer) {
        writer.println("ANSWERGUESS:" + answer);
    }

    /**
     * Rozpoczyna nową grę wysyłając komunikat do serwera
     */
    public void newGame() {
        writer.println("NEWGAME:");
    }

    /**
     * Zamyka połaczenie z serwerem
     */
    private void closeConnection() {
        try {
            game = false;
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Uruchamia wątek i obsługuje wysyłanie komunikatów do serwera, oraz
     * otrzymywanie komunikatów od serwera
     */
    @Override
    public void run() {
        try {
            while (game) {
                String serverMessage = reader.readLine();

                // Ustawia rolę klientowi i przełącza widok na właściwe okno
                if (serverMessage != null && serverMessage.startsWith("ROLE:")) {
                    String role = serverMessage.substring(5);
                    currentFrame.dispose();
                    if ("GUESSER".equals(role)) {
                        java.awt.EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                currentFrame = new GuesserView(ClientWorker.this);
                                currentFrame.setVisible(true);
                            }
                        });
                    } else if ("WORD_GIVER".equals(role)) {
                        java.awt.EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                currentFrame = new Word_GiverView(ClientWorker.this);
                                currentFrame.setVisible(true);
                            }
                        });
                    }

                } // Zamyka closeWaitView, oraz ustawia widoczność zaszyfrowanego hasła w oknach graczy
                else if (serverMessage != null && serverMessage.startsWith("PHRASE:")) {
                    codedPhrase = serverMessage.substring(7);
                    EventQueue.invokeLater(() -> {
                        if (currentFrame instanceof GuesserView guesserView) {
                            guesserView.closeWaitView();
                            guesserView.setPhrase(codedPhrase);
                        } else {
                            ((Word_GiverView) currentFrame).setPhrase(codedPhrase);
                        }
                    });

                } // Wyświetla wiadomość od serwera
                else if (serverMessage != null && serverMessage.startsWith("MESSAGE:")) {
                    String message = serverMessage.substring(8);
                    EventQueue.invokeLater(() -> {
                        if (currentFrame instanceof GuesserView guesserView) {
                            guesserView.setServerMessage(message);
                        } else {
                            ((Word_GiverView) currentFrame).setServerMessage(message);
                        }
                    });
                } // ustawia ostatnio zgadywaną literę i zwiększa ilość ruchów o 1
                else if (serverMessage != null && serverMessage.startsWith("LETTERGUESS:")) {
                    guessCount++;
                    lastLetter = serverMessage.substring(12);
                } // Dodaje źle zgadniętą literę do listy oraz wywołuje rysowanie wisielca na oknie
                else if (serverMessage != null && serverMessage.startsWith("INCORRECTLETTERGUESS:")) {
                    EventQueue.invokeLater(() -> {
                        if (currentFrame instanceof GuesserView guesserView) {
                            guesserView.addLetterToList(lastLetter);
                            guesserView.drawHangman();
                        } else {
                            ((Word_GiverView) currentFrame).addLetterToList(lastLetter);
                            ((Word_GiverView) currentFrame).drawHangman();
                        }
                    });
                } // Ujawnia położenie dobrze zgadniętej litery w haśle
                else if (serverMessage != null && serverMessage.startsWith("CORRECTLETTERGUESS:")) {
                    codedPhrase = serverMessage.substring(19);
                    EventQueue.invokeLater(() -> {
                        if (currentFrame instanceof GuesserView guesserView) {
                            guesserView.setPhrase(codedPhrase);
                        } else {
                            ((Word_GiverView) currentFrame).setPhrase(codedPhrase);
                        }
                    });
                } // Zwiększa ilość ruchów o 1 oraz wywołuje rysowanie wisielca na oknie
                else if (serverMessage != null && serverMessage.startsWith("INCORRECTANSWERGUESS:")) {
                    guessCount++;
                    EventQueue.invokeLater(() -> {
                        if (currentFrame instanceof GuesserView guesserView) {
                            guesserView.drawHangman();
                        } else {
                            ((Word_GiverView) currentFrame).drawHangman();
                        }
                    });
                } // Zwiększa ilość ruchów o 1
                else if (serverMessage != null && serverMessage.startsWith("CORRECTANSWERGUESS:")) {
                    guessCount++;
                } // Przełąca okno i zamyka połączenie z serwerem
                else if (serverMessage != null && serverMessage.startsWith("END:")) {
                    currentFrame.dispose();
                    currentFrame = new PlayerLeftView(result, phrase, guessCount);
                    currentFrame.setVisible(true);
                    closeConnection();
                } // Przełącza okno, ustawia wynik gry oraz zapisuje hasło
                else if (serverMessage != null && serverMessage.startsWith("GAMEWON:")) {
                    result = "Hasło zostało zgadnięte";
                    phrase = serverMessage.substring(8);
                    currentFrame.dispose();
                    currentFrame = new TheEndView(phrase, ClientWorker.this);
                    currentFrame.setVisible(true);

                } // Przełącza okno, ustawia wynik gry oraz zapisuje hasło
                else if (serverMessage != null && serverMessage.startsWith("GAMELOST:")) {

                    result = "Hasło nie zostało zgadnięte";
                    phrase = serverMessage.substring(9);
                    currentFrame.dispose();
                    currentFrame = new BadEndView(phrase, ClientWorker.this);
                    currentFrame.setVisible(true);
                } //zamyka okno i przerywa połączenie 
                else if (serverMessage != null && serverMessage.startsWith("ENDTHIS:")) {
                    currentFrame.dispose();
                    closeConnection();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientWorker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
    }
}
