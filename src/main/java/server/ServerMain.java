package server;

import java.io.IOException;

/**
 * Klasa uruchamiająca serwer
 *
 * @author Ja
 */
public class ServerMain {

    /**
     * Tworzy i uruchamia serwer
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new ServerWorker().initialize();
    }
}
