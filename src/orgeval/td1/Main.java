package orgeval.td1;

import orgeval.td1.conca.MultiEnsConcatenable;
import orgeval.td1.gen.MultiEnsGen;
import orgeval.td1.gen.SpecifMultiEnsGen;
import orgeval.td1.nonGen.MultiEnsNonGen;
import orgeval.td1.nonGen.SpecifMultiEnsNonGen;

public class Main {

    public static void main(String[] args) {
        SpecifMultiEnsNonGen ens = new MultiEnsNonGen();

        System.out.println(ens.taille());
        System.out.println(ens.present(11));
        System.out.println(ens.frequence(11));
        System.out.println(ens.enlever(11));

        ens.ajouter(33);
        ens.ajouter(11);
        System.out.println(ens.present(11));
        System.out.println(ens.frequence(11));
        System.out.println(ens.taille());

        ens.ajouter(33);
        System.out.println(ens.present(33));
        System.out.println(ens.frequence(33));

        System.out.println(ens.enlever(33));
        System.out.println(ens.present(33));
        System.out.println(ens.frequence(33));

        System.out.println(ens.enlever(33));
        System.out.println(ens.present(33));
        System.out.println(ens.frequence(33));
        System.out.println(ens.taille());

        System.out.println("----------------");

        SpecifMultiEnsGen<String> ens2 = new MultiEnsGen<>();
        ens2.ajouter("11");
        System.out.println(ens2.taille());
        System.out.println(ens2.present("11"));
        System.out.println(ens2.frequence("11"));
        System.out.println(ens2.enlever("11"));
        System.out.println(ens2.taille());

        System.out.println("----------------");

        MultiEnsConcatenable<Integer> ens3 = new MultiEnsConcatenable<>();
        ens3.ajouter(11);
        ens3.ajouter(22);
        ens3.ajouter(11);
        ens3.ajouter(44);
        System.out.println(ens3.concatenation());
    }

}
