package server.telefonauskunft;

import abiklassen.List;
import abiklassen.network.Server;

/**
 * @author Sven Ibe
 * @author Michel May
 */
public class AuskunftServer extends Server {

    /* Statische Variablen */
    private  List<Eintrag> telefonbuch;

    /* Statische Methoden */

    /* Attribute / Objektvariablen */

    /* Constructors */
    public AuskunftServer(int pPort) {

        super(pPort);

        telefonbuch = new List<>();

        // ein paar Dummy-Einträge hinzufügen
        telefonbuch.append(new Eintrag("Luca Mustermensch", "+49 123 456789"));
        telefonbuch.append(new Eintrag("Homer Simpson", "+1 654 5646542"));
        telefonbuch.append(new Eintrag("Ada Lovelace", "+44 1012 18151852"));
        telefonbuch.append(new Eintrag("Alan Turing", "+44 2306 19121954"));
        telefonbuch.append(new Eintrag("Grace Hopper", "+1 912 19061992"));

    }

    /* Konstruktoren */
    /**
     * Diese Methode wird beim Herstellen einer neuen Verbindung aufgerufen, muss jedoch nichts weiter leisten. Aus
     * diesem Grund wird lediglich eine kurze Nachricht auf der Konsole ausgegeben.
     * 
     * @param pClientIP Die IP des Clients.
     * @param pClientPort Der Port des Clients.
     */
    @Override
    public void processNewConnection(String pClientIP, int pClientPort) {
        System.out.println("Eine neue Verbindung mit dem Client " + pClientIP + ":" + pClientPort +
                " wurde hergestellt.");
        System.out.println("--------------------------------------");
    }

    /* Objektmethoden */
    /**
     * Diese Methode bildet den Kern der Klasse <code>AuskunftServer</code>. Hier wird auf die verschiedenen Anfragen
     * der Clients entsprechend reagiert.
     * 
     * @param pClientIP Die IP des Clients.
     * @param pClientPort Der Port des Clients.
     * @param pMessage Die vom Client übersendete Nachricht.
     */
    @Override
    public void processMessage(String pClientIP, int pClientPort, String pMessage) {

        System.out.println("SERVER: Ich habe die folgende Nachricht von " + pClientIP + "(" + pClientPort +
                ") erhalten:\n\t" + pMessage);

        if (pMessage.contains(":")) {
            boolean isThere = false;
            String[] arr = pMessage.split(":");
            String substring = arr[0];
            String content = arr[1];

            if (substring.equals("NUMMER")) {
                telefonbuch.toFirst();
                while (telefonbuch.hasAccess()) {
                    if (telefonbuch.getContent().getNummer().contains(content)) {
                        System.out.println("SERVER: Der Name der eingegeben Nummer lautet:\n\t" +
                                telefonbuch.getContent().getName());
                        isThere = true;
                    }
                    telefonbuch.next();
                }
            } else if (substring.equals("NAME")) {
                telefonbuch.toFirst();
                while (telefonbuch.hasAccess()) {
                    if (telefonbuch.getContent().getName().contains(content)) {
                        System.out.println("SERVER: Die Telefonnummer der eingegeben Person lautet:\n\t" +
                                telefonbuch.getContent().getNummer());
                        isThere = true;
                    }
                    telefonbuch.next();
                }
            } else {
                System.out.println("SERVER: Prefix ist falsch!");
            }

            if (!isThere) {
                System.out.println("SERVER: Name oder Nummer wurde nicht gefunden");
                System.out.println("--------------------------------------");
            } else {
                System.out.println("SERVER: Die Anfrage wurde erfolgreich bearbeitet!");
                System.out.println("--------------------------------------");
            }

        } else {
            System.out.println("SERVER: Nachricht konnte nicht gelesen werden");
            System.out.println("--------------------------------------");
        }

    }


    /**
     * Diese Methode wird beim Beenden einer bestehenden Verbindung aufgerufen, muss jedoch nichts weiter leisten. Aus
     * diesem Grund wird lediglich eine kurze Nachricht auf der Konsole ausgegeben.
     * 
     * @param pClientIP Die IP des Clients.
     * @param pClientPort Der Port des Clients.
     */
    @Override
    public void processClosingConnection(String pClientIP, int pClientPort) {

        System.out.println("Die Verbindung zum Client " + pClientIP + ":" + pClientPort + " wird beendet.");

    }

    /* Getter & Setter */

}
