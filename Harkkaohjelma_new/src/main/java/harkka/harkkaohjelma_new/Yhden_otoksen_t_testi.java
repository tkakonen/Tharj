package harkka.harkkaohjelma_new;

import java.util.ArrayList;


/**
 * Yhden otoksen t-testin avulla voi laskea testisuureen, jonka avulla voidaan päätellä eroaako tietyn muuttujan
 * keskiarvo jostain ennalta asetetusta luvusta.
 *
 */
public class Yhden_otoksen_t_testi {

    private String testinnimi;
    private Otos otos;
    private double myynolla;
    private Muuttuja muuttuja;

    public void lisaaParametrit(Muuttuja muuttuja, double myynolla) {
        this.muuttuja = muuttuja;
        this.myynolla = myynolla;
    }

    public void tulostaNollaHypoteesi() {
        System.out.println("Nollahypoteesi: otoksen keskiarvo = " + myynolla);
    }

    public void tulostaOletukset() {
        System.out.println("Muuttuja X on normaalijakautunut");
    }

    public double laskeTestisuureenArvo() {
        double testisuure = 1.0 * (this.muuttuja.Keskiarvo() - myynolla) / this.muuttuja.kaKeskivirhe();
        return testisuure;
    }

    public void tulostaTestisuureenTiedot() {
        System.out.println("Testisuureen arvo on " + laskeTestisuureenArvo() + " ja se noudattaa t-jakaumaa vapausasteella n-1");
    }

    public int laskeVapausasteet() {
        int df = muuttuja.getArvot().size() - 1;
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
