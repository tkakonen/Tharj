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
public class Yhden_otoksen_t_testiTest {

    public Yhden_otoksen_t_testiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void laskeKeskiarvo() {
        ArrayList<Double> lista = new ArrayList<>();
        lista.add(4.0);
        lista.add(4.0);
        lista.add(2.0);
        lista.add(2.0);

        Yhden_otoksen_t_testi testi = new Yhden_otoksen_t_testi();
        testi.lisaaParametrit(lista, 6.1);
        double ka = testi.laskeKeskiarvo();

        assertEquals(3.0, ka, 0.0);
    }

    @Test
    public void laskeOtosVarianssi() {
        ArrayList<Double> lista = new ArrayList<>();
        lista.add(4.0);
        lista.add(4.0);
        lista.add(2.0);
        lista.add(2.0);

        Yhden_otoksen_t_testi testi = new Yhden_otoksen_t_testi();
        testi.lisaaParametrit(lista, 6.1);
        double var = testi.laskeOtosVarianssi();

        assertEquals(var, 1.154700538, 1.0);
    }

    @Test
    public void OtoskokoEiNolla() {
        ArrayList<Double> lista = new ArrayList<>();

        Yhden_otoksen_t_testi testi = new Yhden_otoksen_t_testi();
        testi.lisaaParametrit(lista, 6.1);

        assertEquals(0, 0);
    }

    @Test
    public void laskeKeskiarvonKeskivirhe() {
        ArrayList<Double> lista = new ArrayList<>();
        lista.add(4.0);
        lista.add(4.0);
        lista.add(2.0);
        lista.add(2.0);

        Yhden_otoksen_t_testi testi = new Yhden_otoksen_t_testi();
        testi.lisaaParametrit(lista, 6.1);
        double kv = testi.laskeKeskiarvonKeskivirhe();

        assertEquals(kv, 0.5773503, 0.0000001);
    }

    @Test
    public void laskeTestiSuureenArvo() {
        ArrayList<Double> lista = new ArrayList<>();
        lista.add(4.0);
        lista.add(4.0);
        lista.add(2.0);
        lista.add(2.0);

        Yhden_otoksen_t_testi testi = new Yhden_otoksen_t_testi();
        testi.lisaaParametrit(lista, 3.2);
        double ts = testi.laskeTestisuureenArvo();

        assertEquals(ts, -0.34641014, 0.0000001);
    }
}
