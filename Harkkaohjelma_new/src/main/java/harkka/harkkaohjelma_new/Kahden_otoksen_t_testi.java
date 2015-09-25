
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

////// Tämä luokka vaatii vastuun siirtämistä Muuttuja-luokalle kuten luokassa Yhden_otoksen_t_testi,
 ///// samoin vastaavia muutoksia testiluokkaan.    
    
    private String testinnimi;
    private Muuttuja muuttuja1;
    private Muuttuja muuttuja2;
    private Double kriittinen_p;
    private boolean kaksisuuntainen;

    public Kahden_otoksen_t_testi(Muuttuja muuttuja1, Muuttuja muuttuja2) {
        this.muuttuja1 = muuttuja1;
        this.muuttuja2 = muuttuja2;
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

    public double laskeTestisuureenArvo() {
        double testisuure = 1.0 * (muuttuja1.Keskiarvo()- muuttuja2.Keskiarvo())/Math.sqrt(muuttuja1.kaKeskivirhe() + muuttuja2.kaKeskivirhe());
       
        return testisuure;
    }

    public void tulostaTestisuureenTiedot() {
        System.out.println("Testisuureen arvo on " + laskeTestisuureenArvo() + " ja se noudattaa t-jakaumaa vapausasteella n-1");
    }

    public int laskeVapausasteet() {
        int df = muuttuja1.getArvot().size() - 1;
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

