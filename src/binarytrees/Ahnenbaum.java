package binarytrees;

public class Ahnenbaum {

    public static void main(String[] args) {
        new Ahnenbaum();
    }

    BinaryTree<String> llw;
    BinaryTree<String> lrw;
    BinaryTree<String> rlw;
    BinaryTree<String> rrw;
    BinaryTree<String> lw;
    BinaryTree<String> rw;
    BinaryTree<String> svensAhnenbaum;

    Ahnenbaum() {
        llw = new BinaryTree<>("Katherina Ibe");
        lrw = new BinaryTree<>("Viktor Fedossimov");
        rlw = new BinaryTree<>("Ela Chernega-Pokrovsky");
        rrw = new BinaryTree<>("Vater von Igor Ibe");
        lw = new BinaryTree<>("Natalie Ibe",llw,lrw);
        rw = new BinaryTree<>("Igor Ibe",rlw,rrw);
        svensAhnenbaum = new BinaryTree<>("Sven Ibe",lw,rw);
    }



}
