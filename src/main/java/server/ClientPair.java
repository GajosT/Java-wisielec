package server;

/**
 * Klasa łącząca klientów w pary
 *
 * @author Tomasz Gajos
 */
public class ClientPair {

    private ServerClient client1;
    private ServerClient client2;

    /**
     * Zwraca pierwszego klienta
     *
     * @return pierwszy klient
     */
    public ServerClient getClient1() {
        return client1;
    }

    /**
     * Zwraca drugiego klienta
     *
     * @return drugi klient
     */
    public ServerClient getClient2() {
        return client2;
    }

    /**
     * Konstruktor ClientPair
     *
     * @param client1 pierwszy klient
     * @param client2 drugi klient
     */
    public ClientPair(ServerClient client1, ServerClient client2) {
        this.client1 = client1;
        this.client2 = client2;
    }
}
