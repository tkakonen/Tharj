package harkka.harkkaohjelma_new;

import java.util.ArrayList;
import org.apache.commons.math3.distribution.TDistribution;

/**
 * Yhden otoksen t-testin avulla voi laskea testisuureen, jonka avulla voidaan
 * päätellä eroaako tietyn muuttujan keskiarvo jostain ennalta asetetusta
 * luvusta.
 *
 */
public class Yhden_otoksen_t_testi {

    private String testinnimi;
    private Otos otos;
    private double myynolla;
    private Muuttuja muuttuja;

    /**
     * Määrittää muuttujan, jolle testi tehdään, sekä luvun, johon muuttujan
     * keskiarvoa verrataan.
     */
    public void lisaaParametrit(Muuttuja muuttuja, double myynolla) {
        this.muuttuja = muuttuja;
        this.myynolla = myynolla;
    }

    /**
     * Tulostaa nollahypoteesin. Nollahypoteesi on todennäköisesti totta, ellei
     * testin antama p-arvo ole pieni.
     */
    public void tulostaNollaHypoteesi() {
        System.out.println("Nollahypoteesi: otoksen keskiarvo = " + myynolla);
    }

    /**
     * Tulostaa oletukset. Jos nämä eivät päde, testiä ei pitäisi käyttää eikä
     * se anna luotettavaa tulosta.
     */
    public void tulostaOletukset() {
        System.out.println("Muuttuja X on normaalijakautunut. Otoskoko on riittävän suuri. Jne.");
    }

    /**
     * Laskee T-testisuureen arvon.
     *
     * @return t-testisuure
     */
    public double laskeTestisuureenArvo() {
        double testisuure = 1.0 * (this.muuttuja.Keskiarvo() - myynolla) / this.muuttuja.kaKeskivirhe();
        return testisuure;
    }

    /**
     * Tulostaa tietoa testisuureesta.
     */
    public void tulostaTestisuureenTiedot() {
        System.out.println("Testisuureen arvo on " + laskeTestisuureenArvo() + " ja se noudattaa t-jakaumaa vapausasteella n-1");
    }

    /**
     * Laskee testiin liittyvät vapausasteet (degrees of freedom)
     *
     * @return vapausasteet
     */
    public int laskeVapausasteet() {
        int df = muuttuja.getArvot().size() - 1;
        return df;
    }

    /**
     * Laskee tilastollisesta merkitsevyydestä kertovan p-arvon vertaamalla
     * testisuuretta Studentin t-jakaumaan tietyin vapausastein.
     * @return p-arvo (one-tailed)
     */
    public double laske_yksisuuntainen_P_arvoTestisuureelle() {
        TDistribution T = new TDistribution(this.laskeVapausasteet());
        double p = T.cumulativeProbability(this.laskeTestisuureenArvo());
        return p;
    }

    /**
     * Laskee tilastollisesta merkitsevyydestä kertovan p-arvon vertaamalla
     * testisuuretta Studentin t-jakaumaan tietyin vapausastein.
     * @return p-arvo (two-tailed)
     */
    public double laske_kaksisuuntainen_p_arvoTestisuureelle() {
        TDistribution T = new TDistribution(this.laskeVapausasteet());
        double p = 2 * T.cumulativeProbability(this.laskeTestisuureenArvo());
        return p;
    }

}
