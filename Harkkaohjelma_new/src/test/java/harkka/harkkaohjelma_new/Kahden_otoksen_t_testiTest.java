/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkka.harkkaohjelma_new;

import harkka.harkkaohjelma_new.exceptions.TyhjaMuuttujaException;
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
public class Kahden_otoksen_t_testiTest {

    ArrayList<Double> lista = new ArrayList<>();
    ArrayList<Double> lista2 = new ArrayList<>();

    Muuttuja muuttuja1 = new Muuttuja("testimuuttuja1", lista);
    Muuttuja muuttuja2 = new Muuttuja("testimuuttuja2", lista2);

    Kahden_otoksen_t_testi testi = new Kahden_otoksen_t_testi(muuttuja1, muuttuja2);

    public Kahden_otoksen_t_testiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        lista.add(4.0);
        lista.add(4.0);
        lista.add(2.0);
        lista.add(2.0);

        lista2.add(5.0);
        lista2.add(4.0);
        lista2.add(2.0);
        lista2.add(2.0);
    }

    @After
    public void tearDown() {
        System.out.println(testi.getMuuttuja1().getArvot());
        System.out.println(testi.getMuuttuja2().getArvot());
    }

    @Test
    public void laskeTestiSuureenArvo() throws TyhjaMuuttujaException{

        double ts = testi.laskeTestisuureenArvo();

        assertEquals(ts, -0.264, 0.01);
    }

    @Test
    public void laskeVapausasteet() {
        int df = testi.laskeVapausasteet();
        assertEquals(df, 6);
    }

    @Test (expected = TyhjaMuuttujaException.class)
    public void muuttujaEiOleTyhjaLaskettaessa() throws TyhjaMuuttujaException {

        ArrayList<Double> list = new ArrayList<>();
        ArrayList<Double> list2 = new ArrayList<>();

        Muuttuja muuttuja3 = new Muuttuja("testimuuttuja1", list);
        Muuttuja muuttuja4 = new Muuttuja("testimuuttuja2", list2);

        Kahden_otoksen_t_testi testi = new Kahden_otoksen_t_testi(muuttuja3, muuttuja4);
        
        testi.laskeTestisuureenArvo();
    }

}
