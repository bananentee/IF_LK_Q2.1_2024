/**
 * @author Sven Ibe
 * @version
 */

package graphen.breitensuche;

import abiklassen.graph.Vertex;

public class Person extends Vertex {


    private int bekanntheitsgrad;

    /**
     * Ein neues Objekt vom Typ Vertex wird erstellt. Seine Markierung hat den Wert false.
     *
     * @param pID
     */
    public Person(String pID, int bekanntheitsgrad) {
        super(pID);
        this.bekanntheitsgrad = bekanntheitsgrad;
    }

    public int setBekanntheitsgrad(int bekanntheitsgrad) {
        return this.bekanntheitsgrad = bekanntheitsgrad;
    }

}
