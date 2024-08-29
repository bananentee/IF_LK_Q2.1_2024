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
    BinaryTree<String> lisasAhnenbaum;

    Ahnenbaum() {
        llw = new BinaryTree<>("Jacqueline Bouvier");
        lrw = new BinaryTree<>("Clancy Bouvier");
        rlw = new BinaryTree<>("Mona Simpson");
        rrw = new BinaryTree<>("Abraham J. Simpson");
        lw = new BinaryTree<>("Marge Simpson",llw,lrw);
        rw = new BinaryTree<>("Homer Simpson",rlw,rrw);
        lisasAhnenbaum = new BinaryTree<>("Lisa Simpson",lw,rw);

        preOrderAusgabe();
    }

    public void preOrderAusgabe() {
        search(lisasAhnenbaum);
    }

    private void search(BinaryTree<String> b) {
        System.out.println(b.getContent());
        if (!b.getLeftTree().isEmpty()) {
            search(b.getLeftTree());
        }
        if (!b.getRightTree().isEmpty()) {
            search(b.getRightTree());
        }
    }



}
