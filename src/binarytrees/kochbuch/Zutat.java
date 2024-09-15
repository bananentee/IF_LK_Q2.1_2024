package binarytrees.kochbuch;

public class Zutat {
    private String name;
    private String beschreibung;

    Zutat(String name, String beschreibung) {
        this.name = name;
        this.beschreibung = beschreibung;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
