package server.telefonauskunft;

public class Test {

	/* Statische Variablen */

	/* Statische Methoden */
	private static void warte(int pMillisekunden) {
		try {
			Thread.sleep(pMillisekunden);
		} catch (InterruptedException e) {
		}

	}

	/* Attribute / Objektvariablen */

	/* Konstruktoren */

	/* Objektmethoden */

	/* Getter & Setter */

	/* Main-Methode */
	public static void main(String[] args) {

		AuskunftServer server = new AuskunftServer(5555);
		AuskunftClient client = new AuskunftClient("localhost", 5555);

		client.send("NUMMER:+49 123 456789");
		client.send("NUMMER:blablabla");
		client.send("NAME:Lovelace");
		client.send("NAME:King Julien");
		client.send("NAM:King Julien");
		client.send("Bananen");

		warte(500);

		server.close();

//		String message = "NUMMER:+49 123 456789";
//		String[] arr = message.split(":");
//		System.out.println(arr[0] + " " + arr[1]);

	}
}
