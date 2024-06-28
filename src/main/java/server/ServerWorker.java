package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa obsługująca serwer do gry w wisielca
 *
 * @author Tomasz Gajos
 */
public class ServerWorker {

    private static final int SERVER_PORT;

    private ServerSocket serverSocket;

    /**
     * Zwraca socket serwera
     *
     * @return socket serwera
     */
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    /**
     * Ustawia socket serwera
     *
     * @param serverSocket socket serwera
     */
    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    static {
        SERVER_PORT = 2002;
    }

    /**
     * Konstruktor klasy Ustawia serwer na określonym porcie
     *
     * @throws IOException
     */
    public ServerWorker() throws IOException {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Serwer uruchomiony na porcie " + SERVER_PORT);
        } catch (IOException ex) {
            System.out.println("Wystąpił błąd podczas tworzenia serwera");
            throw ex;
        }
    }

    /**
     * Inicjalizuje serwer Czeka na połączenie dwóch graczy, łączy ich w pary i
     * uruchamia osobny wątek dla każdej pary
     */
    public void initialize() {
        Socket socket1 = null;
        Socket socket2 = null;
        PrintWriter writer1 = null;
        PrintWriter writer2 = null;
        BufferedReader reader1 = null;
        BufferedReader reader2 = null;

        try {
            while (true) {

                socket1 = getServerSocket().accept();
                reader1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
                writer1 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket1.getOutputStream())), true);
                System.out.println("Otwarto połączenie z pierwszym klientem z pary");

                socket2 = getServerSocket().accept();
                reader2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
                writer2 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket2.getOutputStream())), true);
                System.out.println("Otwarto połączenie z drugim klientem z pary");

                ServerClient client1 = new ServerClient(socket1, writer1, reader1);
                ServerClient client2 = new ServerClient(socket2, writer2, reader2);

                ClientPair clientPair = new ClientPair(client1, client2);

                new ServerClientPairWorker(clientPair).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerWorker.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Błąd podczas otwierania połączenia z klientem");
        } finally {
            try {
                serverSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(ServerWorker.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Błąd podczas zamykania serwera");
            }
        }
    }
}
