/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkka.harkkaohjelma_new;

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<Double> lista = new ArrayList<>();
        lista.add(4.0);
        lista.add(4.0);
        lista.add(2.0);
        lista.add(2.0);

        ArrayList<Double> lista2 = new ArrayList<>();

        lista2.add(5.0);
        lista2.add(4.0);
        lista2.add(2.0);
        lista2.add(2.0);

        Muuttuja muuttuja1 = new Muuttuja("testimuuttuja1", lista);
        Muuttuja muuttuja2 = new Muuttuja("testimuuttuja2", lista2);
        Kahden_otoksen_t_testi testi = new Kahden_otoksen_t_testi(muuttuja1, muuttuja2);
        double ts = testi.laskeTestisuureenArvo();
        System.out.println(lista);
        System.out.println(lista2);
        System.out.println(ts);
        
        

    }

}
