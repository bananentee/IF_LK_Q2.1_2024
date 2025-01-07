package server.calculator;

import abiklassen.network.Client;

/**
 * @author Sven Ibe
 */

public class CalcClient extends Client {


    /* constructors */
    public CalcClient(String pServerIP, int pServerPort) {
        super(pServerIP, pServerPort);
    }

    /* methods */
    @Override
    public void processMessage(String pMessage) {
        System.out.println("Client received: " + pMessage);
    }

}
