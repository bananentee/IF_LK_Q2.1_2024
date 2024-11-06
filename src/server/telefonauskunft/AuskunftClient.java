package server.telefonauskunft;

import abiklassen.network.Client;

public class AuskunftClient extends Client {

    /* Statische Variablen */

    /* Statische Methoden */

    /* Attribute / Objektvariablen */

    /* Konstruktoren */
    public AuskunftClient(String pServerIP, int pServerPort) {
        super(pServerIP, pServerPort);
    }

    /* Objektmethoden */
    @Override
    public void processMessage(String pMessage) {

        System.out.println("CLIENT: Ich habe die folgende Nachricht erhalten:\n\t" + pMessage);

    }

    /* Getter & Setter */
}
