/**
 * @author Sven Ibe
 * @version
 */

package binarytrees.pferd;

import java.awt.*;

public class Pferd implements ComparableContentPferd<ComparableContentPferd> {

    /* static variables */


    /* static methods */
    public static void main(String[] args) {
        Pferd pferd1 = new Pferd(Color.black, 20, "black", 'k');
        Pferd pferd2 = new Pferd(Color.white, 33, "white", 'm');
        System.out.println(pferd2.isGreater(pferd1));
    }

    /* attributes */
    private Color color;
    private double speed;
    private String race;
    private char gender;

    /* constructors */
    public Pferd(Color color, double speed, String race, char gender) {
        this.color = color;
        this.speed = speed;
        this.race = race;
        this.gender = gender;
    }

    /* object methods */
    @Override
    public boolean isGreater(Pferd p) {
        return this.race.compareTo(p.getRace()) > 0 || this.speed > p.getSpeed();
    }

    @Override
    public boolean isLess(Pferd p) {
        return this.race.compareTo(p.getRace()) < 0 || this.speed < p.getSpeed();
    }

    @Override
    public boolean isEqual(Pferd p) {
        return this.race.compareTo(p.getRace()) == 0 || this.speed == p.getSpeed();
    }

    /* getter & setter */

    public Color getColor() {
        return color;
    }

    public double getSpeed() {
        return speed;
    }

    public String getRace() {
        return race;
    }

    public char getGender() {
        return gender;
    }
}
