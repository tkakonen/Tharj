/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkka.harkkaohjelma_new;

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
public class Yhden_otoksen_t_testi {

    private String testinnimi;
    private ArrayList<Double> otos;
    private double myynolla;

    public void lisaaParametrit(ArrayList<Double> otos, double myynolla) {
        this.otos = otos;
        this.myynolla = myynolla;

    }

    public void tulostaNollaHypoteesi() {
        System.out.println("Nollahypoteesi: otoksen keskiarvo = " + myynolla);
    }

    public void tulostaOletukset() {
        System.out.println("Muuttuja X on normaalijakautunut");
    }

    public double laskeKeskiarvo() {
        double ka = 0;
        double summa = 0;

        for (Double arvo : otos) {
            summa = summa + arvo;
            double keskiarvo = 1.0 * summa / otos.size();
            ka = keskiarvo;
        }
        return ka;
    }

    public double laskeOtosVarianssi() {
        double ka = laskeKeskiarvo();
        double neliosumma = 0;
        for (Double arvo : otos) {
            neliosumma = neliosumma + 1.0 * Math.pow(arvo - ka, 2);
        }
        double varianssi = 1.0 * neliosumma / (otos.size() - 1);
        return varianssi;
    }

    public double laskeKeskiarvonKeskivirhe() {
        double var = laskeOtosVarianssi();
        int otoskoko = otos.size();

        double keskiarvonKeskivirhe = 1.0 * Math.sqrt(1.0 * var / otoskoko);
        return keskiarvonKeskivirhe;
    }

    public double laskeTestisuureenArvo() {
        double testisuure = 1.0 * (laskeKeskiarvo() - myynolla) / laskeKeskiarvonKeskivirhe();
        return testisuure;
    }

    public void tulostaTestisuureenTiedot() {
        System.out.println("Testisuureen arvo on " + laskeTestisuureenArvo() + " ja se noudattaa t-jakaumaa vapausasteella n-1");
    }

    public int laskeVapausasteet() {
        int df = otos.size() - 1;
        return df;
    }

    //////TÃ¤ssÃ¤ kohtaa tarvitsisin t-jakaumasta saatavia arvoja.
    //////TDistribution jakauma = new TDistribution(laskeVapausasteet()).
    public void luoJakauma() {

    }

    public double laskeP_arvoTestisuureelle() {
        return 0;

    }

}
