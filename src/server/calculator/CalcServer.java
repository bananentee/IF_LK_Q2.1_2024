package server.calculator;

import abiklassen.network.Server;

/**
 * @author Sven Ibe
 */

public class CalcServer extends Server {

    /* constructors */
    public CalcServer(int pPort) {
        super(pPort);
    }

    /* methods */
    @Override
    public void processNewConnection(String pClientIP, int pClientPort) {
        System.out.println("[SERVER] New connection from " + pClientIP + ":" + pClientPort);
    }

    @Override
    public void processMessage(String pClientIP, int pClientPort, String m) {
        if (m.contains(":")) {
            String[] part = m.split(":");
            switch (part[0]) {
                case "ADD":
                    String[] addPart = part[1].split(",");
                    System.out.println("[SERVER] Your solution is: " + ((Integer.parseInt(addPart[0]) + Integer.parseInt(addPart[1]))));
                    break;
                case "SUB":
                    String[] subPart = part[1].split(",");
                    System.out.println("[SERVER] Your solution is: " + ((Integer.parseInt(subPart[0]) - Integer.parseInt(subPart[1]))));
                    break;
                case "MUL":
                    String[] mulPart = part[1].split(",");
                    System.out.println("[SERVER] Your solution is: " + ((Integer.parseInt(mulPart[0]) * Integer.parseInt(mulPart[1]))));
                    break;
                case "DIV":
                    String[] divPart = part[1].split(",");
                    System.out.println("[SERVER] Your solution is: " + ((Double.parseDouble(divPart[0]) / Double.parseDouble(divPart[1]))));
                    break;
                default:
                    System.out.println("[SERVER] False input");
                    break;
            }
        } else {
            System.out.println("[SERVER] False input");
        }
    }

    @Override
    public void processClosingConnection(String pClientIP, int pClientPort) {
        System.out.println("[SERVER] Closing connection from " + pClientIP + ":" + pClientPort);
    }

}
