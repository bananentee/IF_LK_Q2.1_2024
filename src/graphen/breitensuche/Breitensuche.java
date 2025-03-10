/**
 * @author Sven Ibe
 * @version
 */

package graphen.breitensuche;

import abiklassen.List;
import abiklassen.Queue;
import abiklassen.graph.Graph;
import abiklassen.graph.Vertex;

public class Breitensuche {

    /* static variables */



    /* static methods */
    public static void main(String[] args) {
        new Breitensuche().breitenSuche("Anna");
    }


    /* attributes */
    private Graph netz;

    /* constructors */
    public Breitensuche() {
        netz = new Graph();
        netz.addVertex(new Person("Anna", -1));
        netz.addVertex(new Person("Bernd", -1));

    }

    /* object methods */

    public void breitenSuche(String startKnotenID) {
        netz.setAllVertexMarks(false);
        Vertex startKnoten = netz.getVertex(startKnotenID);
        startKnoten.setMark(true);
        Queue<Vertex> queue = new Queue<>();
        queue.enqueue(startKnoten);

        while (!queue.isEmpty()) {
            Vertex k = queue.front();
            List<Vertex> nachbarKnoten = netz.getNeighbours(k);
            nachbarKnoten.toFirst();
            while (nachbarKnoten.hasAccess()) {
                // TODO Bekanntheitsgrad ermitteln!
                if (!nachbarKnoten.getContent().isMarked()) {
                    nachbarKnoten.getContent().setMark(true);
                    queue.enqueue(nachbarKnoten.getContent());
                }
                nachbarKnoten.next();
            }
        }

        // debugging
        while (!queue.isEmpty()) {
            System.out.println(queue.front().getID());
            queue.dequeue();
        }
    }

    /* getter & setter */

}
