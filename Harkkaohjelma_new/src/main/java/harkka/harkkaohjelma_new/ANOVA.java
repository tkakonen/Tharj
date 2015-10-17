/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkka.harkkaohjelma_new;

import java.util.ArrayList;
import org.apache.commons.math3.distribution.FDistribution;

/**
 * ANOVA (Analysis of variance) tarjoaa metodin, jolla voi laskea X määrän muuttujia välisen yksisuuntaisen varianssi-
 * analyysin. Todellisuudessahan kyse on saman muuttujan eri ryhmien välisten keskiarvojen vertailusta.
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

    public int kokonaisN() {
        int N = 0;
        for (Muuttuja muuttuja : muuttujat) {
            for (double arvo : muuttuja.getArvot()) {
                N = N + 1;
            }
        }
        return N;
    }

    public double ryhmienValinenVaihtelu() {
        double summa = 0;
        for (Muuttuja muuttuja : muuttujat) {
            double a = 1.0 * Math.pow(muuttuja.Keskiarvo() - this.laskeKokonaisKa(), 2) * muuttuja.getArvot().size();
            summa = summa + a;
        }
        double SSryhmienValinenVaihtelu = summa;
        return summa;
    }

    public int dfSisainenVaihtelu() {
        int df = this.kokonaisN() - this.muuttujat.size();
        return df;
    }

    public int dfValinenVaihtelu() {
        int df = this.muuttujat.size() - 1;
        return df;
    }

    public double MSsisainenVaihtelu() {
        double a = 1.0 * this.ryhmienSisainenVaihtelu() / this.dfSisainenVaihtelu();
        return a;
    }

    public double MSvalinenVaihtelu() {
        double a = 1.0 * this.ryhmienValinenVaihtelu() / this.dfValinenVaihtelu();
        return a;
    }

    
    public double laskeTestisuureenArvo() {
        double testisuure = 1.0 * this.MSvalinenVaihtelu() / this.MSsisainenVaihtelu();
        return testisuure;
    }
    
    public double laskeP_arvo() {
        FDistribution F = new FDistribution(this.dfValinenVaihtelu(),this.dfSisainenVaihtelu());
        double p = F.cumulativeProbability(this.laskeTestisuureenArvo());
        return p;
    }
    
    
    public void tulostaMuuttujat() {
        for (Muuttuja muuttuja: this.muuttujat) {
            System.out.println(muuttuja.getNimi());
        }
    }
    

}
