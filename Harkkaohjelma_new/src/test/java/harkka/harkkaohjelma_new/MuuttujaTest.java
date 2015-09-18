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
public class MuuttujaTest {

    public MuuttujaTest() {
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
    public void Keskiarvo() {
        ArrayList<Double> lista = new ArrayList<>();
        lista.add(4.0);
        lista.add(4.0);
        lista.add(2.0);
        lista.add(2.0);

        Muuttuja muuttuja = new Muuttuja("TestiMuuttuja", lista);
        
        double ka = muuttuja.Keskiarvo();

        assertEquals(3.0, ka, 0.0);
    }

    @Test
    public void otosVarianssi() {
        ArrayList<Double> lista = new ArrayList<>();
        lista.add(4.0);
        lista.add(4.0);
        lista.add(2.0);
        lista.add(2.0);

        Muuttuja muuttuja = new Muuttuja("TestiMuuttuja", lista);
        
        double var = muuttuja.otosVarianssi();

        assertEquals(var, 1.154700538, 1.0);
    }

    @Test
    public void kaKeskivirhe() {
        ArrayList<Double> lista = new ArrayList<>();
        lista.add(4.0);
        lista.add(4.0);
        lista.add(2.0);
        lista.add(2.0);

        Muuttuja muuttuja = new Muuttuja("MuuttujanNimi", lista);
        double kv = muuttuja.kaKeskivirhe();

        assertEquals(kv, 0.5773503, 0.0000001);
    }

}
