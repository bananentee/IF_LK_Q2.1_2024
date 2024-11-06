package server.echo;

import abiklassen.network.Client;

public class EchoClient extends Client {

    /* Statische Variablen */

    /* Statische Methoden */

    /* Objektvariablen / Attribute */

    /* Konstruktor(en) */
    public EchoClient(String pServerIP, int pServerPort) {
        super(pServerIP, pServerPort);
    }


    /* Objektmethoden */
    @Override
    public void processMessage(String pMessage) {
        System.out.println(pMessage);
    }

    /* Getter & Setter */

}
