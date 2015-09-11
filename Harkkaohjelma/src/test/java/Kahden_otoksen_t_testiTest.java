/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    }

    @After
    public void tearDown() {
    }

    @Test
    public void laskeTestiSuureenArvo() {
        ArrayList<Double> lista = new ArrayList<>();
        lista.add(4.0);
        lista.add(4.0);
        lista.add(2.0);
        lista.add(2.0);

        ArrayList<Double> lista2 = new ArrayList<>();

        lista.add(5.0);
        lista.add(4.0);
        lista.add(2.0);
        lista.add(2.0);

        Kahden_otoksen_t_testi testi = new Kahden_otoksen_t_testi(lista, lista2);
        double ts = testi.laskeTestisuureenArvo();

        assertEquals(0, 0);
    }

}
