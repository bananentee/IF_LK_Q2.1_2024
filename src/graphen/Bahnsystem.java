package graphen;

import abiklassen.Edge;
import abiklassen.Graph;
import abiklassen.Vertex;

public class Bahnsystem {

    /* Statische Variablen */

    /* Statische Methoden */
    public static void main(String[] args) {
       Bahnsystem b = new Bahnsystem();
        System.out.println(b.netz.getEdge(b.netz.getVertex("HBF"), b.netz.getVertex("HKP")).getWeight());
    }

    /* Attribute */
    private Graph netz;

    /* Konstruktoren */
    public Bahnsystem() {
        netz = new Graph();

        Vertex knoten1 = new Vertex("HBF");
        Vertex knoten2 = new Vertex("HKP");
        Vertex knoten3 = new Vertex("MT");
        Vertex knoten4 = new Vertex("TRB");
        Vertex knoten5 = new Vertex("LPZ");
        Vertex knoten6 = new Vertex("ZOO");
        Vertex knoten7 = new Vertex("EP");
        Vertex knoten8 = new Vertex("BR");
        Vertex knoten9 = new Vertex("VA");

        Edge kante = new Edge(knoten1, knoten2, 1);
        Edge kante1 = new Edge(knoten2, knoten3, 1);
        Edge kante2 = new Edge(knoten3, knoten4, 2);
        Edge kante3 = new Edge(knoten4, knoten5, 2);
        Edge kante4 = new Edge(knoten5, knoten6, 10);
        Edge kante5 = new Edge(knoten6, knoten7, 5);
        Edge kante6 = new Edge(knoten7, knoten8, 7);
        Edge kante7 = new Edge(knoten8, knoten9, 6);
        Edge kante8 = new Edge(knoten9, knoten1, 7);

        netz.addVertex(knoten1);
        netz.addVertex(knoten2);
        netz.addVertex(knoten3);
        netz.addVertex(knoten4);
        netz.addVertex(knoten5);
        netz.addVertex(knoten6);
        netz.addVertex(knoten7);
        netz.addVertex(knoten8);
        netz.addVertex(knoten9);

        netz.addEdge(kante);
        netz.addEdge(kante1);
        netz.addEdge(kante2);
        netz.addEdge(kante3);
        netz.addEdge(kante4);
        netz.addEdge(kante5);
        netz.addEdge(kante6);
        netz.addEdge(kante7);
        netz.addEdge(kante8);

    }

    /* Objektmethoden */

    /* Getter und Setter */

}
