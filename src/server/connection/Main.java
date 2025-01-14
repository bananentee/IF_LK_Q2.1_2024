/**
 * @author Sven Ibe
 * @version
 */

package server.connection;

public class Main {

    /* static methods */
    public static void main(String[] args) {
        while (true) {
            new EchoServer(8000);
            new EchoClient2("localhost", 8000, "abc");
        }
    }

}
