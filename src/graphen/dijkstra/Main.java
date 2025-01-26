package graphen.dijkstra;

import abiklassen.Stack;
import abiklassen.graph.Vertex;
import abiklassen.List;

public class Main {

    public static void main(String[] args) {

        // Erstelle das Navigationssystem und bereite den Start- bzw. Zielknoten vor.
        Navigationssystem navi = new Navigationssystem("Navigationssystem.db");
        Vertex start = navi.getNetz().getVertex("B");
        Vertex ziel = navi.getNetz().getVertex("K");

        // Berechne den kürzesten Weg.
        List<Vertex> ergebnis = navi.berechneKuerzestenWeg(start, ziel);

        // Gib das Ergebnis auf der Konsole aus.
        Stack<Vertex> stack = new Stack<>();

        // Fülle das Ergebnis in einen Stack
        ergebnis.toFirst();
        while (ergebnis.hasAccess()) {
            stack.push(ergebnis.getContent());
            ergebnis.next();
        }
        System.out.print("Folgende Knoten wurden bereist: ");

        // Invertiere die Liste, sodass der Weg von Start bis Ziel angezeigt wird
        while (!stack.isEmpty()) {
            System.out.print(stack.top().getID() + " -> ");
            stack.pop();
        }
        System.out.println("ENDE");
    }
}
