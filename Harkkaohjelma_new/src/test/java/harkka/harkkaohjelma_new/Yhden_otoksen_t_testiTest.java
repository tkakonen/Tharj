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

    ArrayList<Double> lista = new ArrayList<>();
    ArrayList<Double> lista2 = new ArrayList<>();

    Muuttuja muuttuja1 = new Muuttuja("testimuuttuja1", lista);

    Yhden_otoksen_t_testi testi = new Yhden_otoksen_t_testi();

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

        lista.add(1.0);
        lista.add(2.0);
        lista.add(3.0);
        lista.add(4.0);
        lista.add(1.0);
        lista.add(2.0);
        lista.add(3.0);
        lista.add(4.0);
        
        testi.lisaaParametrit(muuttuja1, 3.0);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void OtoskokoEiNolla() {
        ArrayList<Double> lista = new ArrayList<>();
        Muuttuja muuttuja = new Muuttuja("TestiMuuttuja", lista);
        Yhden_otoksen_t_testi testi = new Yhden_otoksen_t_testi();
    }

    @Test
    public void laskeTestiSuureenArvo() {        
        double ts = testi.laskeTestisuureenArvo();
        assertEquals(ts, -1.183, 0.01);
    }

    @Test
    public void laskeVapausasteet() {
        int df = testi.laskeVapausasteet();
        assertEquals(df, 7);
    }
    
    @Test
    public void laske_p_arvo_kaksisuuntainen() {
        double p = testi.laske_kaksisuuntainen_p_arvoTestisuureelle();
        assertEquals(p, 0.275, 0.01);
    }
    
    @Test
    public void laske_p_arvo_yksisuuntainen() {
        double p = testi.laske_yksisuuntainen_P_arvoTestisuureelle();
        assertEquals(p, 0.137672, 0.0001);
    }
    
    
}
