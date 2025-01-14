package server.connection;

import abiklassen.network.Server;

public class EchoServer extends Server {

    /* Konstruktor(en) */
    public EchoServer(int pPort) {
        super(pPort);
    }

    /* Objektmethoden */
    @Override
    public void processNewConnection(String pClientIP, int pClientPort) {
        System.out.println("CLIENT: Ich habe zum Server " + pClientIP + ":" + pClientPort + " eine Verbindung hergestellt.");
    }

    @Override
    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        System.out.println("[SERVER] " + pMessage);
    }

    @Override
    public void processClosingConnection(String pClientIP, int pClientPort) {
        System.out.println("CLIENT: Ich habe die Verbindung mit " + pClientIP + ":" + pClientPort + " geschlossen.");
    }

}
