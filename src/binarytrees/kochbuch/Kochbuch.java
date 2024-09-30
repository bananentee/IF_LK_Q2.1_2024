package binarytrees.kochbuch;

import abiklassen.BinarySearchTree;
import abiklassen.List;

public class Kochbuch {

    private BinarySearchTree<Rezept> rezeptbaum = new BinarySearchTree<>();

    public static void main(String[] args) {
        new Kochbuch();
    }

    Kochbuch() {
        rezeptbaum.insert(new Rezept("Moussaka", "irgend ein Auflauf",
                setZutatenliste("Parmesan", "Kartoffel", "Aubergine")));
        rezeptbaum.insert(new Rezept("Chili sin carne", "Geil",
                setZutatenliste("Bohnen", "Mais", "Tomate")));
        rezeptbaum.insert(new Rezept("Auberginenroellchen", "nicht meins",
                setZutatenliste("Aubergine", "Mozzarella", "Tomatensauce")));
        rezeptbaum.insert(new Rezept("Exotischer Griesspudding", "könnt sehr gefaehrlich kommen",
                setZutatenliste("Ei", "Mango", "Griess")));
        rezeptbaum.insert(new Rezept("Romanasalat", "hat potenzial",
                setZutatenliste("Salat", null, null)));
        rezeptbaum.insert(new Rezept("Tofu_Burger", "wuergereiz",
                setZutatenliste("veganes Patty", "Tofu", "Salat")));

        System.out.println(suche("Moussaka").getBeschreibung());
        System.out.println();
        traversiereRezepte();
        System.out.println();
        getZutaten("Moussaka");
    }

    private List<Zutat> setZutatenliste(String z1, String z2, String z3) {
        List<Zutat> zutatenliste = new List<>();
        zutatenliste.toFirst();
        zutatenliste.append(new Zutat(z1, null));
        zutatenliste.append(new Zutat(z2, null));
        zutatenliste.append(new Zutat(z3, null));
        return zutatenliste;
    }

    public Rezept suche(String schluessel) {
        if (!rezeptbaum.isEmpty()) {
            return rezeptbaum.search(new Rezept(schluessel, null, null));
        }
        return null;
    }

    public void traversiereRezepte() {
        if (!rezeptbaum.isEmpty()) {
            this.traversiere(rezeptbaum);
        }
    }

    private void traversiere(BinarySearchTree<Rezept> pRezeptbaum) {
        if (!pRezeptbaum.isEmpty()) {
            this.traversiere(pRezeptbaum.getLeftTree());

            this.traversiere(pRezeptbaum.getRightTree());
            System.out.println(pRezeptbaum.getContent().getSchluessel());

        }
    }

    public void getZutaten (String schluessel) {
        Rezept r = rezeptbaum.search(new Rezept(schluessel, null, null));
        List<Zutat> zutatenliste = r.getZutatenliste();
        zutatenliste.toFirst();
        while (zutatenliste.hasAccess()) {
            System.out.println(zutatenliste.getContent().getName());
            zutatenliste.next();
        }
    }
}
