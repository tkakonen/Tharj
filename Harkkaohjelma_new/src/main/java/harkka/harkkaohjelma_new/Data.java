/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkka.harkkaohjelma_new;

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class Data {

    private Double[][] datataulukko;
    private ArrayList<String> muuttujalista;
    private ArrayList<String> henkilot;
    
    public Data() {
        
    }

    public Data(Double[][] datataulukko, ArrayList<String> muuttujalista) {
        this.datataulukko = datataulukko;
        this.muuttujalista = muuttujalista;
    }
    
    public void tulostaData(){
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
   
    
    

}
