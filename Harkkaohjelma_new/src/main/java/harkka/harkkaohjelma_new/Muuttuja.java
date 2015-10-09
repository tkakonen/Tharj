/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkka.harkkaohjelma_new;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Muuttuja sisältää listana datataulukon pystysarakkeen arvot. Se tarjoaa
 * metodit muuttujan arvojen perustunnuslukujen laskemiseen.
 */
public class Muuttuja {

    private String muuttujanNimi;
    private ArrayList<Double> arvot;
    private Data data;

    public Muuttuja(String muuttujanNimi, ArrayList<Double> arvot) {
        this.muuttujanNimi = muuttujanNimi;
        this.arvot = arvot;
    }

    public Muuttuja(String muuttujanNimi, Data data) {
        this.muuttujanNimi = muuttujanNimi;
        int arvojenMaara = data.getHenkilot().size();
        for (int i = 0; i < arvojenMaara; i++) {
            this.arvot.add(data.getData()[data.getMuuttujanNimet().indexOf(muuttujanNimi)][i]);
        }
    }

    /**
     * Metodi laskee muuttujan sisältämien arvojen keskiarvon.
     *
     * @return muuttujan arvojen keskiarvo
     */
    public double Keskiarvo() {
        double ka = 0;
        double summa = 0;

        for (Double arvo : this.arvot) {
            summa = summa + arvo;
            double keskiarvo = 1.0 * summa / this.arvot.size();
            ka = keskiarvo;
        }
        return ka;
    }

    /**
     * Metodi laskee muuttujan sisältämien arvojen otosvarianssin.
     *
     * @return muuttujan arvojen otosvarianssi
     */
    public double otosVarianssi() {
        double ka = this.Keskiarvo();
        double neliosumma = 0;
        for (Double arvo : this.arvot) {
            neliosumma = neliosumma + 1.0 * Math.pow(arvo - ka, 2);
        }
        double varianssi = 1.0 * neliosumma / (this.arvot.size() - 1);
        return varianssi;
    }

    /**
     * Metodi laskee muuttujan sisältämien arvojen keskiarvon keskivirheen.
     *
     * @return muuttujan arvojen keskiarvon keskivirhe
     */
    public double kaKeskivirhe() {
        double var = this.otosVarianssi();
        int otoskoko = this.arvot.size();

        double keskiarvonKeskivirhe = 1.0 * Math.sqrt(1.0 * var / otoskoko);
        return keskiarvonKeskivirhe;
    }

    /**
     * Metodi palauttaa muuttujan arvot ArrayList- muodossa.
     *
     * @return muuttujan arvot listana
     */
    public ArrayList<Double> getArvot() {
        return this.arvot;
    }

    /**
     * Metodi käy läpi muuttujan arvot ja laskee toisistaan poikkeavien arvojen
     * lukumäärän.
     *
     * @return toisistaan poikkeavien muuttujan arvojen lukumäärä
     */
    public ArrayList<Double> erilaisetArvot() {
        ArrayList<Double> erilaisetArvot = new ArrayList<>();
        for (Double arvo : this.getArvot()) {
            if (!erilaisetArvot.contains(arvo)) {
                erilaisetArvot.add(arvo);
            }
        }
        return erilaisetArvot;
    }

    public String getNimi() {
        return this.muuttujanNimi;
    }

    public ArrayList<Muuttuja> ryhmittele(String muuttujanNimi, ArrayList<Double> arvot) {
        Muuttuja ryhmittelevaMuuttuja = this.data.getMuuttuja(muuttujanNimi);
        ArrayList<Muuttuja> ryhmitellytMuuttujat = new ArrayList<>();
        for (Double arvo : arvot) {
            ArrayList<Double> lista = new ArrayList<>();
            ArrayList<Integer> indeksit = new ArrayList<>();
            for (Double value : ryhmittelevaMuuttuja.getArvot()) {
                if (value.equals(arvo)) {
                    indeksit.add(ryhmittelevaMuuttuja.getArvot().indexOf(value));
                }
                for (int index : indeksit) {
                    lista.add(this.arvot.get(index));
                }

            }
            Muuttuja muuttuja = new Muuttuja("arvo", lista);
            ryhmitellytMuuttujat.add(muuttuja);
        }
        return ryhmitellytMuuttujat;
    }
}
