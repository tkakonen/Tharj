package harkka.harkkaohjelma_new;

import harkka.harkkaohjelma_new.exceptions.TyhjaMuuttujaException;
import java.util.ArrayList;
import org.apache.commons.math3.distribution.TDistribution;

/**
 * Kahden otoksen t-testi tarjoaa metodin, joka laskee testisuureen jonka avulla
 * voidaan päätellä, eroavatko kahden riippumattoman otoksen tietyn muuttujan
 * keskiarvot toisistaan.
 *
 */
public class Kahden_otoksen_t_testi {

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

    public double laskeTestisuureenArvo() throws TyhjaMuuttujaException {

        if (this.muuttuja1.getArvot().isEmpty()) {
            throw new TyhjaMuuttujaException();
        }else if (this.muuttuja2.getArvot().isEmpty()) {
            throw new TyhjaMuuttujaException();
        } else {

            double testisuure = 1.0 * (muuttuja1.Keskiarvo() - muuttuja2.Keskiarvo()) / Math.sqrt(muuttuja1.otosVarianssi() / muuttuja1.getArvot().size() + muuttuja2.otosVarianssi() / muuttuja2.getArvot().size());

            return testisuure;
        }
    }

    public void tulostaTestisuureenTiedot() throws TyhjaMuuttujaException{
        System.out.println("Testisuureen arvo on " + laskeTestisuureenArvo() + " ja se noudattaa t-jakaumaa vapausasteella " + this.laskeVapausasteet());
    }

    public int laskeVapausasteet() {
        int df = muuttuja1.getArvot().size() + muuttuja2.getArvot().size() - 2;
        return df;
    }


    public double laskeP_arvoTestisuureelle_yksisuuntainen() throws TyhjaMuuttujaException {
        TDistribution T = new TDistribution(this.laskeVapausasteet());
        double p = T.cumulativeProbability(this.laskeTestisuureenArvo());
        return p;
    }
    
    public double laskeP_arvoTestisuureelle_kaksisuuntainen() throws TyhjaMuuttujaException {
        double p = this.laskeP_arvoTestisuureelle_yksisuuntainen();
        return p;
    }
    

    public Muuttuja getMuuttuja1() {
        return this.muuttuja1;
    }

    public Muuttuja getMuuttuja2() {
        return this.muuttuja2;
    }

}
