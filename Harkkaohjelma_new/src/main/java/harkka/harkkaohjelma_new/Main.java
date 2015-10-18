/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkka.harkkaohjelma_new;

import harkka.harkkaohjelma_new.exceptions.TyhjaMuuttujaException;
import harkka.harkkaohjelma_new.kayttoliittyma.Kayttoliittyma;
import java.util.ArrayList;
import org.apache.commons.math3.distribution.TDistribution;

/**
 *
 * @author DELL
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Kayttoliittyma kayttis = new Kayttoliittyma();
        kayttis.run();       
    }

}
