/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkka.harkkaohjelma_new;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author DELL
 */
public class ANOVATest {

    ArrayList<Double> lista1 = new ArrayList<>();
    ArrayList<Double> lista2 = new ArrayList<>();
    ArrayList<Double> lista3 = new ArrayList<>();

    Muuttuja muuttuja1 = new Muuttuja("Testimuuttuja1", lista1);
    Muuttuja muuttuja2 = new Muuttuja("Testimuuttuja2", lista2);
    Muuttuja muuttuja3 = new Muuttuja("Testimuuttuja3", lista3);

    ArrayList<Muuttuja> muuttujat = new ArrayList<>();

    ANOVA anova = new ANOVA(muuttujat);

    public ANOVATest() {

    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        lista1.add(5.0);
        lista1.add(4.0);
        lista1.add(2.0);
        lista1.add(2.0);

        lista2.add(4.0);
        lista2.add(4.0);
        lista2.add(2.0);
        lista2.add(2.0);

        lista3.add(4.0);
        lista3.add(4.0);
        lista3.add(2.0);
        lista3.add(2.0);

        muuttujat.add(muuttuja1);
        muuttujat.add(muuttuja2);
        muuttujat.add(muuttuja3);

    }

    @After
    public void tearDown() {
        System.out.println(muuttuja3.getArvot());
        anova.tulostaMuuttujat();
    }

    @Test
    public void laskeTestiSuureenArvo() {

        double ts = anova.laskeTestisuureenArvo();

        assertEquals(ts, 0.051, 0.01);

    }

    @Test
    public void MSsis() {

    }

    @Test
    public void MSval() {

    }
}
