/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkka.harkkaohjelma_new;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Otos sisältää metodin, jolla voi luoda uuden pienemmän datan, johon valitaan vain osa havaintoyksiköistä
 * tietyn muuttujan arvojen perusteella. Esim. muuttujan sukupuoli avulla voidaan valita uuteen dataan vain naisten
 * mittaustulokset. 
 * 
 */
public class Otos {

    private String nimi;
    private Data data;
    private Data otos;
    private String ryhmittelevaMuuttuja;
    private int haluttuArvo;

    public Otos(Data data, String ryhmittelevaMuuttuja, int haluttuArvo) {
        this.data = data;
        this.ryhmittelevaMuuttuja = ryhmittelevaMuuttuja;
        this.haluttuArvo = haluttuArvo;
        this.otos = new Data();
    }

    public void luoOtos() {
        int i = this.data.getMuuttujanNimet().indexOf(ryhmittelevaMuuttuja);
        Data otos = new Data();
        int a = this.data.getMuuttujanNimet().size();
        for (int j = 0; j < a; j++) {
            if (this.data.getData()[i][j] != this.haluttuArvo) {
                for (int b = 1; b < a; b++) {
                    this.otos.getData()[b][j] = data.getData()[b][j];
                }
            }
        }
    }

    public Data getOtos() {

        return this.otos;
    }

}
