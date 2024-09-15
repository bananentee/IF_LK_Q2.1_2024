package binarytrees.pferd;

public interface ComparableContent<C extends ComparableContent> {

    boolean isGreater(Pferd p);
    boolean isLess(Pferd p);
    boolean isEqual(Pferd p);
}
