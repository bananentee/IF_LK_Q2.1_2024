/**
 * @author Sven Ibe
 * @version
 */

package server.echo;

public class Execute {
    /**
        Konzept abstrakter Klassen:
        Besonders in diesem Beispiel müssen abstrakte Klassen verwendet werden, da
        sie eine besondere Eigenschaft besitzen. Sie haben eine festgelegte Struktur,
        bestehend aus abstrakten Methoden, die die Grundstruktur für erbende Klassen festlegen.
        Das ist in dem Sinne vom Vorteil, da wir bei unserer Serverarchitektur ein festgelegtes
        Protokoll haben (send, process). Damit diese Struktur erhalten bleibt, erben Klassen von
        der abstrakten Klasse und überschreiben die Methodenrümpfe. Somit stellen abstrakte Klassen
        eine Infrastruktur für unsere Projekte bereit.
     */

    /* static variables */
    public static void main(String[] args) {
        while(true){
            EchoServer server = new EchoServer(8000);
            EchoClient client = new EchoClient("localhost", 8000);
            client.send("hallo");
        }

//        client.close();
//        server.close();
    }

    /* static methods */


    /* attributes */


    /* constructors */


    /* object methods */


    /* getter & setter */

}
