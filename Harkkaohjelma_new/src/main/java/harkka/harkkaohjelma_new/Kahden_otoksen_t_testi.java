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

    /**
     * Konstruktori asettaa vertailtaviksi ryhmiksi kahden muuttujan saamat
     * arvot.
     *
     * @param muuttuja1
     * @param muuttuja2
     */
    public Kahden_otoksen_t_testi(Muuttuja muuttuja1, Muuttuja muuttuja2) {
        this.muuttuja1 = muuttuja1;
        this.muuttuja2 = muuttuja2;
    }

    /**
     * Vaihtoehtoinen konstruktori, joka hyödyntää ryhmittelevää muuttujaa.
     *
     * @param muuttujanNimi
     * @param ryhmittelevanMuuttujanNimi
     * @param data
     */
    public Kahden_otoksen_t_testi(String muuttujanNimi, String ryhmittelevanMuuttujanNimi, Data data) {
        ArrayList<Muuttuja> muuttujat = data.getMuuttuja(muuttujanNimi).
                ryhmittele(ryhmittelevanMuuttujanNimi, data.getMuuttuja(ryhmittelevanMuuttujanNimi).
                        erilaisetArvot());
        this.muuttuja1 = muuttujat.get(1);
        this.muuttuja2 = muuttujat.get(2);
    }

    /**
     * Tulostaa nollahypoteesin.
     */
    public void tulostaNollaHypoteesi() {
        System.out.println("Otokset 1 ja 2 ovat populaatioista, joiden keskiarvo on sama");
    }

    /**
     * Tulostaa oletukset.
     */
    public void tulostaOletukset() {
        System.out.println("Muuttujat X1 ja X2 on normaalisti jakautuneita. Otokset 1 ja 2 ovat riippumattomia. Otoskoko on riittävän suuri. Jne.");
    }

    /**
     * Laskee t-testisuureen arvon.
     *
     * @return t-testisuure
     * @throws harkka.harkkaohjelma_new.exceptions.TyhjaMuuttujaException
     */
    public double laskeTestisuureenArvo() throws TyhjaMuuttujaException {

        if (this.muuttuja1.getArvot().isEmpty()) {
            throw new TyhjaMuuttujaException();
        } else if (this.muuttuja2.getArvot().isEmpty()) {
            throw new TyhjaMuuttujaException();
        } else {

            double testisuure = 1.0 * (muuttuja1.Keskiarvo() - muuttuja2.Keskiarvo()) / Math.sqrt(muuttuja1.otosVarianssi() / muuttuja1.getArvot().size() + muuttuja2.otosVarianssi() / muuttuja2.getArvot().size());

            return testisuure;
        }
    }

    /**
     * Tulostaa testisuureen tietoja.
     */
    public void tulostaTestisuureenTiedot() throws TyhjaMuuttujaException {
        System.out.println("Testisuureen arvo on " + laskeTestisuureenArvo() + " ja se noudattaa t-jakaumaa vapausasteella " + this.laskeVapausasteet());
    }

    /**
     * Laskee vapausasteet (df, degrees of freedom)
     *
     * @return vapausasteet
     */
    public int laskeVapausasteet() {
        int df = muuttuja1.getArvot().size() + muuttuja2.getArvot().size() - 2;
        return df;
    }

    /**
     * Laskee yksisuuntaisen p-arvon testisuureelle. Todennäköisyys lasketaan
     * siis vain t-jakauman toisesta päästä.
     *
     * @return p-arvo (one-tailed)
     */
    public double laskeP_arvoTestisuureelle_yksisuuntainen() throws TyhjaMuuttujaException {
        TDistribution T = new TDistribution(this.laskeVapausasteet());
        double p = T.cumulativeProbability(this.laskeTestisuureenArvo());
        return p;
    }

    /**
     * Laskee kaksisuuntaisen p-arvon testisuureelle. Todennäköisyys lasketaan
     * siis vain t-jakauman molemmista päistä.
     *
     * @return p-arvo (two-tailed)
     */
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
