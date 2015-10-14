/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkka.harkkaohjelma_new.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class TiedostonLukija {

    public TiedostonLukija() {

    }

    public HashMap<String, ArrayList<Double>> lue(String tiedosto) throws FileNotFoundException {
        File luettavatiedosto = new File(tiedosto);
        Scanner lukija = new Scanner(luettavatiedosto);

        HashMap<String, ArrayList<Double>> listat = new HashMap<>();
        ArrayList<String> muuttujatArrayna = new ArrayList<>();
        String header = lukija.nextLine();
        String[] muuttujienNimet = header.split(";");

        for (String nimi : muuttujienNimet) {
            listat.put(nimi, new ArrayList<Double>());
            muuttujatArrayna.add(nimi);
        }

        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            String[] jaettuRivi = rivi.split(";");
            for (int i = 0; i < listat.keySet().size(); i++) {
                String value = jaettuRivi[i];
                Double arvo = Double.parseDouble(value);
                String name = muuttujatArrayna.get(i);
                listat.get(name).add(arvo);
            }

        }
        lukija.close();
        return listat;

    }
}
