/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkka.harkkaohjelma_new;

import java.util.ArrayList;
import org.apache.commons.math3.distribution.FDistribution;

/**
 * ANOVA (Analysis of variance) tarjoaa metodin, jolla voi laskea X määrän
 * muuttujia välisen yksisuuntaisen varianssi- analyysin. Todellisuudessahan
 * kyse on saman muuttujan eri ryhmien välisten keskiarvojen vertailusta.
 *
 *
 */
public class ANOVA {

    private ArrayList<Muuttuja> muuttujat;
    private Data data;

    public ANOVA(String muuttujanNimi, String ryhmittelevanMuuttujanNimi, ArrayList arvot) {
        this.muuttujat = data.getMuuttuja(muuttujanNimi).ryhmittele(ryhmittelevanMuuttujanNimi, arvot);
    }

    public ANOVA(ArrayList<Muuttuja> muuttujat) {
        this.muuttujat = muuttujat;
    }

    /**
     * Metodi laskee kullekin ryhmälle ryhmään kuuluvien arvojen vaihtelua
     * kuvaavan neliösumman ja yhdistää nämä ryhmien sisäistä vaihtelua
     * mittaavaksi neliösummaksi.
     *
     * @return ryhmien sisäistä vaihtelua kuvaava neliösumma
     */
    public double ryhmienSisainenVaihtelu() {
        ArrayList<Double> SSgroups = new ArrayList<>();
        int i = 1;
        for (Muuttuja muuttuja : muuttujat) {
            double SSmuuttuja = 1.0 * (muuttuja.getArvot().size() - 1) * muuttuja.otosVarianssi();
            SSgroups.add(SSmuuttuja);
            i = 1 + 1;
        }
        double summa = 0;
        for (Double ss : SSgroups) {
            summa = summa + ss;
        }
        return summa;
    }

    /**
     * Metodi laskee kokonaiskeskiarvon, missä on mukana kaikkiin ryhmiin
     * kuuluvat alkiot!
     *
     * @return kokonaiskeskiarvo
     */
    public double laskeKokonaisKa() {
        double summa = 0;
        int N = 0;
        for (Muuttuja muuttuja : muuttujat) {
            for (double arvo : muuttuja.getArvot()) {
                summa = summa + arvo;
                N = N + 1;
            }
        }

        double ka = 1.0 * summa / N;
        return ka;
    }

    /**
     * Metodi laske kaikkien analyysissa mukana olevien arvojen määrän.
     *
     * @return havaintoyksikköjen/analyysissa mukana olevien arvojen
     * kokonaismäärä
     */
    public int kokonaisN() {
        int N = 0;
        for (Muuttuja muuttuja : muuttujat) {
            for (double arvo : muuttuja.getArvot()) {
                N = N + 1;
            }
        }
        return N;
    }

    /**
     * Metodi laske suureen (neliösumma) joka mittaa ryhmien välistä
     * ryhmäkeskiarvojen vaihtelua kokonaiskeskiarvon ympärillä.
     *
     * @return ryhmien välistä vaihtelua kuvaava neliösumma
     *
     */
    public double ryhmienValinenVaihtelu() {
        double summa = 0;
        for (Muuttuja muuttuja : muuttujat) {
            double a = 1.0 * Math.pow(muuttuja.Keskiarvo() - this.laskeKokonaisKa(), 2) * muuttuja.getArvot().size();
            summa = summa + a;
        }
        double SSryhmienValinenVaihtelu = summa;
        return summa;
    }

    /**
     * Metodi laskee sisäisen vaihtelun vapausasteet (df, degrees of freedom).
     * Lasketaan vähentä- mällä kokonaisN:stä ryhmien määrä.
     * @return ryhmien sisäisen vaihtelun vapausasteet
     */
    public int dfSisainenVaihtelu() {
        int df = this.kokonaisN() - this.muuttujat.size();
        return df;
    }

    /**
     * Metodi laskee ryhmien väliseen vaihteluun liittyvät vapausasteet (ryhmien
     * määrä-1).
     *
     * @return ryhmien välisen vaihtelun vapausasteet
     *
     */
    public int dfValinenVaihtelu() {
        int df = this.muuttujat.size() - 1;
        return df;
    }

    /**
     * Metodi laskee sisäistä vaihtelua kuvaavan keskineliösummasuureen (Mean
     * Square) jakamalla vastaavan neliösumman vapausasteillaan.
     *
     * @return sisäisen vaihtelun keskineliösumma
     */
    public double MSsisainenVaihtelu() {
        double a = 1.0 * this.ryhmienSisainenVaihtelu() / this.dfSisainenVaihtelu();
        return a;
    }

    /**
     * Metodi laskee ryhmien välistä vaihtelua kuvaavan keskineliösummasuureen
     * (Mean Square) jakamalla vastaavan neliösumman vapausasteillaan.
     *
     * @return ryhmien välisen vaihtelun keskineliösumma
     */
    public double MSvalinenVaihtelu() {
        double a = 1.0 * this.ryhmienValinenVaihtelu() / this.dfValinenVaihtelu();
        return a;
    }

    /**
     * Metodi laskee F-testisuureen arvon, joka kertoo ryhmien sisäisen ja
     * välisen vaihtelun suhteesta. * @return F-testisuure
     */
    public double laskeTestisuureenArvo() {
        double testisuure = 1.0 * this.MSvalinenVaihtelu() / this.MSsisainenVaihtelu();
        return testisuure;
    }

    /**
     * Laskee p-arvon joka kertoo testituloksen tilastollisesta
     * merkitsevyydestä. p-arvo laske- taan vertaamalla F-testisuuretta
     * F-jakaumaan tietyillä vapausasteilla.
     *
     * @return p-arvo
     */
    public double laskeP_arvo() {
        FDistribution F = new FDistribution(this.dfValinenVaihtelu(), this.dfSisainenVaihtelu());
        double p = F.cumulativeProbability(this.laskeTestisuureenArvo());
        return p;
    }

    /**
     * Tulostaa kaikkien eri ryhmien alkiot. Eri ryhmien alkiot on sijoitettu erillisiin 
     * muuttujiin, vaikka ne voivat olla alkuperäisessä datassa samassa muuttujassa.
     */
    public void tulostaMuuttujat() {
        for (Muuttuja muuttuja : this.muuttujat) {
            System.out.println(muuttuja.getNimi());
        }
    }

}
