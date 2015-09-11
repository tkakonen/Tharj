
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DELL
 */
public class Paaohjelma {

    public static void main(String[] args) {
        ArrayList<Double> lista = new ArrayList<Double>();
        lista.add(5.1);
        lista.add(7.0);
        lista.add(5.5);
        lista.add(4.54);
        lista.add(12.4);
        new Yhden_otoksen_t_testi().lisaaParametrit(lista, 6.1);
    }

}
