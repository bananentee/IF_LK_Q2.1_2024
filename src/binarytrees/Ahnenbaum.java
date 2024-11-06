package binarytrees;

import abiklassen.binarytree.BinaryTree;

public class Ahnenbaum {

    public static void main(String[] args) {
        new Ahnenbaum();
    }

    BinaryTree<String> llw, lrw, rlw, rrw, lw, rw, lisasAhnenbaum;
    private int i;

    Ahnenbaum() {
        llw = new BinaryTree<>("Jacqueline Bouvier");
        lrw = new BinaryTree<>("Clancy Bouvier");
        rlw = new BinaryTree<>("Mona Simpson");
        rrw = new BinaryTree<>("Abraham J. Simpson");
        lw = new BinaryTree<>("Marge Simpson",llw,lrw);
        rw = new BinaryTree<>("Homer Simpson",rlw,rrw);
        lisasAhnenbaum = new BinaryTree<>("Lisa Simpson",lw,rw);

        preOrderAusgabe();
        System.out.println();
        inOrderAusgabe();
        System.out.println();
        postOrderAusgabe();
        countAusgabe();
    }

    public void preOrderAusgabe() {
        preSearch(lisasAhnenbaum);
    }

    private void preSearch(BinaryTree<String> b) {
        System.out.println(b.getContent());
        if (!b.getLeftTree().isEmpty()) {
            preSearch(b.getLeftTree());
        }
        if (!b.getRightTree().isEmpty()) {
            preSearch(b.getRightTree());
        }
    }

    public void inOrderAusgabe() {
        inSearch(lisasAhnenbaum);
    }

    private void inSearch(BinaryTree<String> b) {
        if (!b.getLeftTree().isEmpty()) {
            inSearch(b.getLeftTree());
        }
        System.out.println(b.getContent());
        if (!b.getRightTree().isEmpty()) {
            inSearch(b.getRightTree());
        }
    }

    public void postOrderAusgabe() {
        postSearch(lisasAhnenbaum);
    }

    private void postSearch(BinaryTree<String> b) {
        if (!b.getLeftTree().isEmpty()) {
            postSearch(b.getLeftTree());
        }
        if (!b.getRightTree().isEmpty()) {
            postSearch(b.getRightTree());
        }
        System.out.println(b.getContent());
    }

    public void countAusgabe() {
        i = 0;
        count(lisasAhnenbaum);
        System.out.println(i);
    }

    private void count(BinaryTree<String> b) {
        if (!b.getLeftTree().isEmpty()) {
            count(b.getLeftTree());
        }
        if (!b.getRightTree().isEmpty()) {
            count(b.getRightTree());
        }
        i++;
    }

}
