/**
 * @author Sven Ibe
 * @version
 */

package server.connection;

import abiklassen.network.*;

public class EchoClient2 {
    Connection connection;
    /* constructors */
    EchoClient2(String pServerIP, int pServerPort, String pMessage) {
        connection = new Connection(pServerIP,pServerPort);
        connection.send(pMessage);
    }

}
