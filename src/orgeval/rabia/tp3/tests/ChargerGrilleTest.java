package orgeval.rabia.tp3.tests;

import org.junit.jupiter.api.Test;
import orgeval.rabia.tp1.MotsCroises;
import orgeval.rabia.tp3.modele.ChargerGrille;

public class ChargerGrilleTest {

    @Test
    public final void testExtraireGrille() {
        MotsCroises motsCroises = ChargerGrille.extraireGrille(10);
        System.out.println("test");
    }

}
