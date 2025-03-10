/**
 * @author Sven Ibe
 * @version
 */

package graphen.breitensuche;

import abiklassen.List;
import abiklassen.Queue;
import abiklassen.graph.Graph;
import abiklassen.graph.Vertex;

public class GraphSearch {

    /* static methods */
    public static void main(String[] args) {
        new GraphSearch().breitenSuche("Anna");
    }

    /* attributes */
    private final Graph netz;

    /* constructors */
    public GraphSearch() {
        netz = new Graph();
        netz.addVertex(new Person("Anna", -1));
        netz.addVertex(new Person("Bernd", -1));
    }

    /* object methods */

    public void breitenSuche(String startKnotenID) {
        netz.setAllVertexMarks(false);
        Vertex startKnoten = netz.getVertex(startKnotenID);
        startKnoten.setMark(true);
        Queue<Vertex> s = new Queue<>();
        s.enqueue(startKnoten);

        while (!s.isEmpty()) {
            Vertex k = s.front();
            List<Vertex> nachbarKnoten = netz.getNeighbours(k);
            nachbarKnoten.toFirst();
            while (nachbarKnoten.hasAccess()) {
                if (!nachbarKnoten.getContent().isMarked()) {
                    nachbarKnoten.getContent().setMark(true);
                    s.enqueue(nachbarKnoten.getContent());
                }
                nachbarKnoten.next();
            }
        }

    }

    public void tiefenSuche(String startKnotenID) {
        netz.setAllVertexMarks(false);
        Vertex startKnoten = netz.getVertex(startKnotenID);
        startKnoten.setMark(true);
        List<Vertex> neighbours = netz.getNeighbours(startKnoten);

        neighbours.toFirst();
        while (neighbours.hasAccess()) {
            if (!neighbours.getContent().isMarked()) {
                tiefenSuche(neighbours.getContent().getID());
            }
        }

    }

}
