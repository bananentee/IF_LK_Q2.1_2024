package binarytrees.kochbuch;

import abiklassen.binarytree.ComparableContent;
import abiklassen.List;

public class Rezept implements ComparableContent<Rezept> {

    private String schluessel;
    private String beschreibung;
    private List<Zutat> zutatenliste;

    Rezept (String schluessel, String beschreibung, List<Zutat> zutatenliste) {
        this.schluessel = schluessel;
        this.beschreibung = beschreibung;
        this.zutatenliste = zutatenliste;
    }

    @Override
    public boolean isGreater(Rezept pContent) {
        return schluessel.compareTo(pContent.getSchluessel()) > 0;
    }

    @Override
    public boolean isEqual(Rezept pContent) {
        return schluessel.compareTo(pContent.getSchluessel()) == 0;
    }

    @Override
    public boolean isLess(Rezept pContent) {
        return schluessel.compareTo(pContent.getSchluessel()) < 0;
    }

    public List<Zutat> getZutatenliste() {
        return zutatenliste;
    }

    public String getSchluessel() {
        return schluessel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
