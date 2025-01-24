package graphen.dijkstra;

import abiklassen.graph.Vertex;
import abiklassen.List;

public class Main {

    public static void main(String[] args) {

        // Erstelle das Navigationssystem und bereite den Start- bzw. Zielknoten vor.
        Navigationssystem navi = new Navigationssystem("Navigationssystem.db");
        Vertex start = navi.getNetz().getVertex("I");
        Vertex ziel = navi.getNetz().getVertex("O");

        // Berechne den kürzesten Weg.
        List<Vertex> ergebnis = navi.berechneKuerzestenWeg(start, ziel);

        // Gib das Ergebnis auf der Konsole aus.
        ergebnis.toFirst();
        while (ergebnis.hasAccess()) {
            System.out.print(ergebnis.getContent().getID() + " -> ");
            ergebnis.next();
        }
        System.out.println("ENDE");
    }
}
