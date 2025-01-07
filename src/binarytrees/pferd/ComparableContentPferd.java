package binarytrees.pferd;

public interface ComparableContentPferd<C extends ComparableContentPferd> {

    boolean isGreater(Pferd p);
    boolean isLess(Pferd p);
    boolean isEqual(Pferd p);
}
