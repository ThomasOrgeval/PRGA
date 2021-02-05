package orgeval.rabia.tp4.test;

import morpions.kit.test.*;
import orgeval.rabia.tp4.ModeleMorpions;
import orgeval.rabia.tp4.SpecifModeleMorpions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MorpionsTest {

    ModeleMorpions morpions;
    MorpionsReference morpionsRef;
    Bogue1 morpions1;
    Bogue2 morpions2;
    Bogue3 morpions3;
    Bogue4 morpions4;
    Bogue5 morpions5;
    public static final int NB_CASES = 9;

    @BeforeEach
    public void setUp() throws Exception {
        morpions = new ModeleMorpions();
        morpions1 = new Bogue1();
        morpions2 = new Bogue2();
        morpions3 = new Bogue3();
        morpions4 = new Bogue4();
        morpions5 = new Bogue5();
        morpionsRef = new MorpionsReference();
    }

    @Test
    public void testInit() {
        morpions = new ModeleMorpions();
        assertNotNull(morpions.getEtatJeu(), "Init échouée");

        // Test de l'invariant de la classe
        testInvariant();
    }

    @Test
    public void testPremierCoup() {
        morpions.jouerCoup(1, 1);
        assertFalse(morpions.estFinie(), "Partie pas finie après premier coup");
        // ----------------------
        // SÉQUENCE 2 À COMPLÉTER
        // ----------------------

        // On reteste l'invariant
        testInvariant();
    }

    private void testInvariant() {
        // Le nombre de coups est positif ou nul, et inférieur ou égal au nombre de cases
        assertTrue(morpions.getNombreCoups() >= 0, "Nombre de coups >= 0");
        assertTrue(morpions.getNombreCoups() <= NB_CASES,
                "Nombre de coups <= " + NB_CASES);
        // ----------------------
        // SÉQUENCE 3 À COMPLÉTER
        // ----------------------
    }

    @Test
    public void testCoupDejaJoue() {
        morpions.jouerCoup(1, 1);
        assertFalse(morpions.estCoupAutorise(1, 1), "La case (1,1) ne peut être jouée");
    }

    @Test
    public void testPartiePasFinie() {
        morpions.jouerCoup(1, 1);
        morpions.jouerCoup(2, 2);
        assertFalse(morpions.estFinie(), "La partie n'est pas finie");

        morpions = new ModeleMorpions();
        morpions.jouerCoup(1, 1);
        morpions.jouerCoup(1, 2);
        morpions.jouerCoup(1, 3);
        morpions.jouerCoup(2, 1);
        morpions.jouerCoup(3, 1);
        assertFalse(morpions.estFinie(), "La partie n'est pas finie");
    }

    @Test
    public void testPartieFinie() {
        morpions.jouerCoup(1, 1);
        morpions.jouerCoup(1, 2);
        morpions.jouerCoup(1, 3);
        morpions.jouerCoup(2, 1);
        morpions.jouerCoup(2, 2);
        morpions.jouerCoup(2, 3);
        morpions.jouerCoup(3, 2);
        morpions.jouerCoup(3, 1);
        morpions.jouerCoup(3, 3);
        assertTrue(morpions.estFinie(), "La partie est finie");

        morpions = new ModeleMorpions();
        morpions.jouerCoup(1, 1); // J1
        morpions.jouerCoup(2, 2); // J2
        morpions.jouerCoup(1, 2); // J1
        morpions.jouerCoup(2, 3); // J2
        morpions.jouerCoup(1, 3); // J1
        assertTrue(morpions.estFinie());
    }

    @Test
    public void testJoueur1gagnant() {
        morpions.jouerCoup(1, 1); // J1
        morpions.jouerCoup(2, 2); // J2
        morpions.jouerCoup(1, 2); // J1
        morpions.jouerCoup(2, 3); // J2
        morpions.jouerCoup(1, 3); // J1
        assertSame(morpions.getEtatJeu(), SpecifModeleMorpions.Etat.J1_VAINQUEUR, "Le joueur 1 est gagnant");
    }

    @Test
    public void testJoueur2gagnant() {
        morpions.jouerCoup(3, 3); // J1
        morpions.jouerCoup(1, 1); // J2
        morpions.jouerCoup(2, 2); // J1
        morpions.jouerCoup(1, 2); // J2
        morpions.jouerCoup(2, 3); // J1
        morpions.jouerCoup(1, 3); // J2
        assertSame(morpions.getEtatJeu(), SpecifModeleMorpions.Etat.J2_VAINQUEUR, "Le joueur 2 est gagnant");
    }

    @Test
    public void testEtatJeu() {
        morpions.jouerCoup(1, 1);
        assertEquals(SpecifModeleMorpions.Etat.J2_JOUE, morpions.getEtatJeu());
        morpions.jouerCoup(3, 3);
        assertEquals(SpecifModeleMorpions.Etat.J1_JOUE, morpions.getEtatJeu());

        morpions.jouerCoup(1, 3);
        morpions.jouerCoup(2, 1);
        morpions.jouerCoup(2, 3);
        morpions.jouerCoup(2, 2);
        morpions.jouerCoup(3, 2);
        morpions.jouerCoup(1, 2);
        morpions.jouerCoup(3, 1);
        assertEquals(SpecifModeleMorpions.Etat.MATCH_NUL, morpions.getEtatJeu());
    }

    @Test
    public void testCoupTrop() {
        morpions.jouerCoup(1, 1);
        assertFalse(morpions.estCoupAutorise(4, 1));
        assertFalse(morpions.estCoupAutorise(4, 4));
        assertFalse(morpions.estCoupAutorise(0, 2));
    }
}
