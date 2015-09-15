
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
public class Kahden_otoksen_t_testi {

    private String testinnimi;
    private ArrayList<Double> otos1;
    private ArrayList<Double> otos2;
    private Double kriittinen_p;
    private boolean kaksisuuntainen;

    public Kahden_otoksen_t_testi(ArrayList<Double> otos1, ArrayList<Double> otos2) {
        this.otos1 = otos1;
        this.otos2 = otos2;
    }

    public void asetaParametrit(Double kriittinen_p, boolean kaksisuuntainen) {
        this.kriittinen_p = kriittinen_p;
        this.kaksisuuntainen = kaksisuuntainen;
    }

    public void tulostaNollaHypoteesi() {
        System.out.println("Otokset 1 ja 2 ovat populaatioista, joiden keskiarvo on sama");
    }

    public void tulostaOletukset() {
        System.out.println("Muuttujat X1 ja X2 on normaalisti jakautuneita. Otokset 1 ja 2 ovat riippumattomia");
    }

    //// Sori aivan kauheaa copypastekoodia, korjaan seuraavaan palautukseen.
    
    public double laskeKeskiarvo() {
        double ka = 0;
        double summa = 0;

        for (Double arvo : otos1) {
            summa = summa + arvo;
            double keskiarvo = 1.0 * summa / otos1.size();
            ka = keskiarvo;
        }
        return ka;
    }

    public double laskeKeskiarvo2() {
        double ka = 0;
        double summa = 0;

        for (Double arvo : otos2) {
            summa = summa + arvo;
            double keskiarvo = 1.0 * summa / otos2.size();
            ka = keskiarvo;
        }
        return ka;
    }

    public double laskeOtosVarianssi() {
        double ka = laskeKeskiarvo();
        double neliosumma = 0;
        for (Double arvo : otos1) {
            neliosumma = neliosumma + 1.0 * Math.pow(arvo - ka, 2);
        }
        double varianssi = 1.0 * neliosumma / (otos1.size() - 1);
        return varianssi;
    }
    
        public double laskeOtosVarianssi2() {
        double ka = laskeKeskiarvo2();
        double neliosumma = 0;
        for (Double arvo : otos2) {
            neliosumma = neliosumma + 1.0 * Math.pow(arvo - ka, 2);
        }
        double varianssi = 1.0 * neliosumma / (otos2.size() - 1);
        return varianssi;
    }

    public double laskeKeskiarvonKeskivirhe() {
        double var = laskeOtosVarianssi();
        int otoskoko = otos1.size();

        double keskiarvonKeskivirhe = 1.0 * Math.sqrt(1.0 * var / otoskoko);
        return keskiarvonKeskivirhe;
    }
    
        public double laskeKeskiarvonKeskivirhe2() {
        double var = laskeOtosVarianssi2();
        int otoskoko = otos2.size();

        double keskiarvonKeskivirhe = 1.0 * Math.sqrt(1.0 * var / otoskoko);
        return keskiarvonKeskivirhe;
    }

    public double laskeTestisuureenArvo() {
        double testisuure = 1.0 * (laskeKeskiarvo()- laskeKeskiarvo2())/Math.sqrt(laskeKeskiarvonKeskivirhe()+laskeKeskiarvonKeskivirhe2());
       
        return testisuure;
    }

    public void tulostaTestisuureenTiedot() {
        System.out.println("Testisuureen arvo on " + laskeTestisuureenArvo() + " ja se noudattaa t-jakaumaa vapausasteella n-1");
    }

    public int laskeVapausasteet() {
        int df = otos1.size() - 1;
        return df;
    }

    //////Tässä kohtaa tarvitsisin t-jakaumasta saatavia arvoja.
    //////TDistribution jakauma = new TDistribution(laskeVapausasteet()).
    public void luoJakauma() {

    }

    public double laskeP_arvoTestisuureelle() {
        return 0;

    }

}

