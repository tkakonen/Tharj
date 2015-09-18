/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkka.harkkaohjelma_new;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author DELL
 */
public class Muuttuja {

    private String muuttujanNimi;
    private ArrayList<Double> arvot;

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

    public double otosVarianssi() {
        double ka = this.Keskiarvo();
        double neliosumma = 0;
        for (Double arvo : this.arvot) {
            neliosumma = neliosumma + 1.0 * Math.pow(arvo - ka, 2);
        }
        double varianssi = 1.0 * neliosumma / (this.arvot.size() - 1);
        return varianssi;
    }

    public double kaKeskivirhe() {
        double var = this.otosVarianssi();
        int otoskoko = this.arvot.size();

        double keskiarvonKeskivirhe = 1.0 * Math.sqrt(1.0 * var / otoskoko);
        return keskiarvonKeskivirhe;
    }

    public ArrayList<Double> getArvot() {
        return this.arvot;
    }

    public ArrayList<Double> erilaisetArvot() {
        ArrayList<Double> erilaisetArvot = new ArrayList<>();
        for (Double arvo : this.getArvot()) {
            if (!erilaisetArvot.contains(arvo)) {
                erilaisetArvot.add(arvo);
            }
        }
        return erilaisetArvot;
    }

}
