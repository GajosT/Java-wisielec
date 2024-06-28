package server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Klasa reprezentująca klienta
 *
 * @author Tomasz Gajos
 */
public class ServerClient {

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    /**
     * Zwraca socket klienta
     *
     * @return socket klienta
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
     * Konstruktor klienta
     *
     * @param socket socket klienta
     * @param writer PrintWriter do wysyłania danych
     * @param reader BufferedReader do odbierania danych
     */
    public ServerClient(Socket socket, PrintWriter writer, BufferedReader reader) {
        this.socket = socket;
        this.writer = writer;
        this.reader = reader;
    }

}
