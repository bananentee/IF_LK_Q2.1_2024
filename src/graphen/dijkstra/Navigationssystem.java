package graphen.dijkstra;

import abiklassen.List;
import abiklassen.database.DatabaseConnector;
import abiklassen.database.QueryResult;
import abiklassen.graph.Edge;
import abiklassen.graph.Graph;
import abiklassen.graph.Vertex;

/**
 * Eine Implementation des Dijkstra-Algorithmus für die Abiturklassen ab 2018.
 *
 * @author Michel May
 * @version 1.0
 */
public class Navigationssystem {

    /* Statische Variablen */

    /* Statische Methoden */

    /* Objektvariablen / Attribute */
    private Graph netz;
    private Wegfinder wegfinder;

    /* Konstruktoren */
    public Navigationssystem(String datenbank) {

        netz = new Graph();
        wegfinder = new Wegfinder();

        DatabaseConnector connector = new DatabaseConnector(null, -1, datenbank, null, null);

        // Zuerst holen wir die Knoten aus der DB, wandeln sie in Objekt um und fügen sie dem Graphen hinzu.
        connector.executeStatement("SELECT * FROM Haltestellen");
        QueryResult result = connector.getCurrentQueryResult();
        String[][] data = result.getData();

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                Vertex temp1 = new Vertex(data[i][j]);
                netz.addVertex(temp1);
            }
        }

        // Jetzt dasselbe für die Kanten. Allerdings müssen wir aus dem Graphen dann immer die Referenzen auf die Knoten abfragen.
        connector.executeStatement("SELECT * FROM Verbindungen");
        result = connector.getCurrentQueryResult();
        data = result.getData();

        Vertex knoten1, knoten2;
        double gewicht;

        for (int i = 0; i < data.length; i++) {
            knoten1 = netz.getVertex(data[i][0]);
            knoten2 = netz.getVertex(data[i][1]);
            gewicht = Double.parseDouble(data[i][2]);
            netz.addEdge(new Edge(knoten1, knoten2, gewicht));
        }

//        List <Edge> list = netz.getEdges();
//        list.toFirst();
//        while (list.hasAccess()) {
//            System.out.println(list.getContent().getWeight());
//            list.next();
//        }


    }

    /* Objektmethoden */
    public List<Vertex> berechneKuerzestenWeg(Vertex von, Vertex nach) {
        return wegfinder.berechneKuerzestenWeg(von, nach);
    }

    /* Getter & Setter */
    public Graph getNetz() {
        return netz;
    }

    private class Wegfinder {

        /**
         * Dieses Attribut stellt die Tabelle des Dijkstra-Algorithmus dar. Pro Knoten des Graphen erfolgt ein Eintrag,
         * der wiederum besteht aus dem jew. Vorgänger des Knotens sowie der aufsummierten Strecke, die benötigt wird,
         * um ihn vom Startpunkt aus zu erreichen.
         */
        private Wegabschnitt[] abstandstabelle;

        private Wegfinder() {
            abstandstabelle = null;
        }

        /**
         * Diese Methode stellt das Kernstück der Klasse dar. Sie berechnet den kürzesten Weg zwischen den beiden über-
         * gebenen Knoten mithilfe des Dijkstra-Algorithmus.
         *
         * @param von  der Konten, von dem aus der Weg berechnet werden soll.
         * @param nach der Knoten, zu dem der Weg berechnet werden soll.
         * @return eine Liste, die beginnend vom Startpunkt aus alle Knoten entlang des kürzesten Weges in der richtigen
         * Reihenfolge enthält.
         */
        public List<Vertex> berechneKuerzestenWeg(Vertex von, Vertex nach) {

            //////////////////
            // VORBEREITUNG //
            //////////////////


            netz.setAllVertexMarks(false);
            bereiteAbstandstabelleVor(von);


            ///////////////////////////////////
            // BERECHNUNG DER KÜRZESTEN WEGE //
            ///////////////////////////////////

            for (int i = 0; i < abstandstabelle.length; i++) {
                Vertex currentVertex = abstandstabelle[i].knoten;
                List<Vertex> nachbarKnoten = netz.getNeighbours(currentVertex);

                nachbarKnoten.toFirst();
                while (nachbarKnoten.hasAccess()) {
                    Vertex currentNeighbor = nachbarKnoten.getContent();
                    if (!currentNeighbor.isMarked()) {
                        Edge currentEdge = netz.getEdge(currentVertex, currentNeighbor);
                        Wegabschnitt currentAbschnitt = abstandstabelle[sucheInAbstandstabelle(currentNeighbor)];

                        double bisher = currentAbschnitt.streckeBisHierHer;
                        double neu = abstandstabelle[i].streckeBisHierHer + currentEdge.getWeight();

                        if (neu < bisher) {
                            currentAbschnitt.vorgaenger = currentVertex;
                            currentAbschnitt.streckeBisHierHer = neu;
                        }
                    }
                    nachbarKnoten.next();
                }
                currentVertex.setMark(true);
                sortiereAbstandstabelle(sucheInAbstandstabelle(currentVertex) + 1);
            }


            //////////////////////////////////
            // AUFBEREITUNG DES ENDERGEBNIS //
            //////////////////////////////////

            return erstelleErgebnisAusTabelle(nach);
        }

        /**
         * Diese Hilfsmethode initialisiert eine neue {@link #abstandstabelle}. Hierbei legt sie für jeden Knoten im
         * Graphen einen {@link Wegabschnitt} an und initialisiert ihn mit <code>null</code> für den Vorgänger und dem
         * Wert unendlich (bzw. <code>Double.MAX_VALUE</code>).
         */
        private void bereiteAbstandstabelleVor(Vertex von) {
            int anzahlKnoten = 0;
            List <Vertex> allVertices = netz.getVertices();
            allVertices.toFirst();
            while (allVertices.hasAccess()) {
                anzahlKnoten++;
                allVertices.next();
            }
            abstandstabelle = new Wegabschnitt[anzahlKnoten];
            allVertices.toFirst();
            for (int i = 0; i < abstandstabelle.length; i++, netz.getVertices().next() /* ende Durchlauf abgespielt */) {
                Vertex currentVertex = allVertices.getContent();
                if (currentVertex.getID().equals(von.getID())) {
                    abstandstabelle[i] = new Wegabschnitt(currentVertex, null, 0d);
                    tausche(i,0);
                } else {
                    abstandstabelle[i] = new Wegabschnitt(currentVertex, null, Double.MAX_VALUE);
                }
            }

        }

        /**
         * Diese Hilfsmethode geht von hinten nach vorne durch die {@link #abstandstabelle} ('Backtracking') und erstellt
         * anhand der jeweiligen Vorgänger den Weg vom Start- zum Zielknoten. Dieser Weg wird (in richtiger Reihenfolge)
         * in einer Liste gespeichert und zurückgegeben.
         *
         * @param nach der Zielknoten, zu dem der Weg führen soll.
         * @return ein Objekt vom Typ {@link List}, das alle Knoten ({@link Vertex}-Objekte) enthält, die den Weg zum
         * Zielknoten beschreiben. Der Startknoten ist dabei automatisch das erste Element in der Tabelle.
         */
        private List<Vertex> erstelleErgebnisAusTabelle(Vertex nach) {

            Vertex gesuchterKnoten = nach;
            List<Vertex> ergebnis = new List<>();

            for (int i = abstandstabelle.length - 1; i <= 0; i--) {
                if (!abstandstabelle[i].knoten.equals(nach)) {
                    continue;
                }
                ergebnis.append(gesuchterKnoten);
                gesuchterKnoten = abstandstabelle[i].vorgaenger;
            }

            return ergebnis;
        }

        /**
         * Diese Hilfsmethode sucht den Index eines übergebenen <code>Vertex</code> in der {@link #abstandstabelle} und
         * gibt ihn zurück. Sollte der Knoten nicht gefunden werden, wird eine Exception geworfen, da dieser Fall nicht
         * vorgesehen ist.
         *
         * @param vertex der Knoten, dessen Index bestimmt werden soll.
         * @return der Index des gesuchten Knotens in der {@link #abstandstabelle}.
         * @throws IllegalArgumentException falls sich der gesuchte Knoten nicht in der {@link #abstandstabelle} befindet
         *                                  (was unmöglich sein sollte).
         */
        private int sucheInAbstandstabelle(Vertex vertex) {
            for (int i = 0; i < abstandstabelle.length; i++) {
                if (vertex.getID().equals(abstandstabelle[i].knoten.getID())) {
                    return i;
                }
            }
            throw new IllegalArgumentException("Der Zielknoten wurde nicht gefunden. Dies darf nicht passieren! " + vertex.getID());
        }

        /**
         * Diese Methode sortiert das Array <code>abstandstabelle</code> aufsteigend und verwendet hierbei den BubbleSort-
         * Algorithmus. Es beginnt beim übergebenen Parameter, lässt also alle Elemente 'links davon' unberührt. Der Algo-
         * rithmus bricht automatisch ab, sofern er bei einem Durchlauf keine Tauschoperationen mehr durchgeführt hat, das
         * Array also sortiert ist.
         *
         * @param start der Start-Index, ab dem sortiert werden soll.
         */
        private void sortiereAbstandstabelle(int start) {

            boolean breakpoint = false;

            for (int j = abstandstabelle.length - 1; j >= 0; j--) {
                breakpoint = true;
                for (int i = start; i < j; i++) {
                    if (abstandstabelle[i].streckeBisHierHer > abstandstabelle[i + 1].streckeBisHierHer) {
                        breakpoint = false;
                        tausche(i, i + 1);
                    }
                }
                if (breakpoint) {
                    break;
                }
            }
        }

        /**
         * Diese Hilfsmethode vertauscht zwei Elemente in der {@link #abstandstabelle}.
         *
         * @param a das erste zu tauschende Element.
         * @param b das zweite zu tauschende Element.
         */
        private void tausche(int a, int b) {
            Wegabschnitt temp = abstandstabelle[a];
            abstandstabelle[a] = abstandstabelle[b];
            abstandstabelle[b] = temp;
        }

        /**
         * Diese Hilfsmethode gibt den aktuellen Stand der {@link #abstandstabelle} aus und dient einzig dem Debugging.
         */
        private void printAbstandstabelle() {

            Wegabschnitt tmp;

            for (int j = 0; j < abstandstabelle.length; j++) {
                tmp = abstandstabelle[j];
                System.out.println(tmp.knoten.getID() + ", "
                        + (tmp.vorgaenger != null ? tmp.vorgaenger.getID() : "null")
                        + " -> " + tmp.streckeBisHierHer);
            }
            System.out.println("-----------------------------------------");
        }

        /**
         * Mithilfe dieser Klasse wird die bei der Ausführung des Dijkstra-Algorithmus entstehende Tabelle realisiert. Pro
         * Konten im Graphen wird ein Objekt vom Typ <code>Wegabschnitt</code> angelegt und in einem Array verwaltet. Es
         * enthält den jeweiligen {@link #knoten}, dessen {@link #vorgaenger} sowie die aufsummierte {@link #streckeBisHierHer}.
         */
        private class Wegabschnitt {

            private Vertex knoten;
            private Vertex vorgaenger;
            private double streckeBisHierHer;

            private Wegabschnitt(Vertex knoten, Vertex vorgaenger, double streckeBisHierHer) {
                this.knoten = knoten;
                this.vorgaenger = vorgaenger;
                this.streckeBisHierHer = streckeBisHierHer;
            }
        }

    }


}