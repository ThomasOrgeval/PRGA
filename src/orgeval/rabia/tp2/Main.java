package orgeval.rabia.tp2;

public class Main {

    public static void main(String[] args) {
        Grille<String> maGrille = new Grille<>(3, 5);
        for (int l = 1; l <= maGrille.getHauteur(); l++) {
            String texteLigne = Integer.toString(l);
            for (int c = 1; c <= maGrille.getLargeur(); c++) {
                maGrille.setCellule(l, c, texteLigne + ", " + c);
            }
        } System.out.println(maGrille);
    }
}
