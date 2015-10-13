/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkka.harkkaohjelma_new;

import java.util.ArrayList;

/**
 * Data sisältää datataulukon.Pystysarakkeissa on tietyn muuttujan eri arvot,
 * vaakariveillä havaintoyksikön arvot/mittaustulokset.
 *
 */
public class Data {

    private Double[][] datataulukko;
    private ArrayList<String> muuttujalista;
    private ArrayList<String> henkilot;
    private String name;

    public Data() {

    }

    public Data(Double[][] datataulukko, ArrayList<String> muuttujalista) {
        this.datataulukko = datataulukko;
        this.muuttujalista = muuttujalista;
    }

    public void tulostaData() {
        System.out.println(muuttujalista);
        System.out.println(datataulukko);
    }

    public Double[][] getData() {
        return this.datataulukko;
    }

    public ArrayList<String> getMuuttujanNimet() {
        return this.muuttujalista;
    }

    public ArrayList<String> getHenkilot() {
        return this.henkilot;
    }

    public void setMuuttujaLista(ArrayList<String> muuttujalista) {
        this.muuttujalista = muuttujalista;
    }

    public Muuttuja getMuuttuja(String nimi) {
        ArrayList<Double> arvot= new ArrayList<>();
        int sijainti = this.muuttujalista.indexOf(nimi);
        int a = this.henkilot.size();
        System.out.println(this.henkilot.size());
        for (int i = 0; i < (a-1); i++ ) {
            arvot.add(this.getData()[sijainti][i]);
        }
        Muuttuja muuttuja = new Muuttuja(nimi, arvot);
        return muuttuja;
    }

    public Muuttuja getMuuttuja(int jarjestysNro) {

        return null;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setData(Double[][] taulukko) {
        this.datataulukko = taulukko;
    }
    
    public void setHenkilot(ArrayList<String> henkilot) {
        this.henkilot = henkilot;
    } 

}
