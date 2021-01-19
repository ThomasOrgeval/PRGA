package orgeval.rabia.tp2;

public class Main {

    public static void main(String[] args) {
        Grille<String> maGrille = new Grille<>(3, 5);
        for (int l = 1; l <= maGrille.getHauteur(); l++) {
            String texteLigne = Integer.toString(l);
            for (int c = 1; c <= maGrille.getLargeur(); c++) {
                maGrille.setCellule(l, c, texteLigne + ", " + c);
            }
        }
        System.out.println(maGrille);

        Character[] tab = new Character[8];
        tab[0] = null;
        tab[1] = 'A';
        tab[2] = 'B';
        tab[3] = null;
        tab[4] = null;
        tab[5] = 'C';
        tab[6] = 'D';
        tab[7] = null;
        IterateurMots iter = new IterateurMots(tab);
        while (iter.hasNext()) {
            System.out.print(iter.next() + ", ");
        }
    }
}
