package server.calculator;

/**
 * @author Sven Ibe
 */

public class CalcEXE {

    /* static methods */
    public static void main(String[] args) {
        CalcServer server = new CalcServer(8000);
        CalcClient client = new CalcClient("localhost", 8000);
        client.send("ADD:23123,123");
        client.send("SUB:12323,23342");
        client.send("MUL:145765,3452");
        client.send("DIV:345345,3346");
    }

}
