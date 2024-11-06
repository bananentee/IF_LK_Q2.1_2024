package server.telefonauskunft;

public class Eintrag {

    private String name;
    private String nummer;

    Eintrag (String pName, String pNummer) {
        this.name = pName;
        this.nummer = pNummer;
    }

    public String getName() {
        return name;
    }

    public String getNummer() {
        return nummer;
    }
}
