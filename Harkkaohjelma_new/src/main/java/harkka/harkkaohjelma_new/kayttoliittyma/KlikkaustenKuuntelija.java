/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkka.harkkaohjelma_new.kayttoliittyma;

import harkka.harkkaohjelma_new.Data;
import harkka.harkkaohjelma_new.exceptions.TyhjaMuuttujaException;
import harkka.harkkaohjelma_new.reader.TiedostonLukija;
import java.awt.Container;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author DELL
 */
public class KlikkaustenKuuntelija implements ActionListener {

    private Kayttoliittyma kayttis;
    private JButton valitse;
    private JButton valitse2;
    private JButton tallenna;
    private JTextField datannimi;
    private ArrayList<TextField> muuttujat;
    private ArrayList<JTextField> havainnot;
    private JButton palaa;
    private JButton tunnarit;
    private JTextField myy;
    private JButton ts;
    private JButton lueData;
    private JTextField filename;
    private JTable taulukko;
    private int virheenTyyppi;

    public KlikkaustenKuuntelija(Kayttoliittyma kayttis) {
        this.kayttis = kayttis;
    }

    public void setButtonValitse(JButton valitse) {
        this.valitse = valitse;
    }

    public void setButtonValitse2(JButton valitse2) {
        this.valitse2 = valitse2;
    }

    public void setButtonTallenna(JButton tallenna) {
        this.tallenna = tallenna;
    }

    public void setTextFieldDatannimi(JTextField datannimi) {
        this.datannimi = datannimi;
    }

    public void setTextFieldsMuuttujat(ArrayList<TextField> muuttujat) {
        this.muuttujat = muuttujat;
    }

    public void setTextFieldsHavainnot(ArrayList<JTextField> havainnot) {
        this.havainnot = havainnot;
    }

    public void setPalaa(JButton palaa) {
        this.palaa = palaa;
    }

    public void setTunnarit(JButton tunnarit) {
        this.tunnarit = tunnarit;
    }

    public void setTextFieldMyy(JTextField myy) {
        this.myy = myy;
    }

    public void setTs(JButton ts) {
        this.ts = ts;
    }

    public void setLueData(JButton lue) {
        this.lueData = lue;
    }

    public void setFileName(JTextField filename) {
        this.filename = filename;
    }

    public void setTaulukko(JTable taulukko) {
        this.taulukko = taulukko;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == valitse) {
            if (this.kayttis.getValinnanNro() == 1) {
                this.kayttis.lueData();
            } else if (this.kayttis.getValinnanNro() == 2) {
                this.kayttis.syotaData();
            } else if (this.kayttis.getValinnanNro() == 3) {
                this.kayttis.analysoi();
            } else if (this.kayttis.getValinnanNro() == 4) {
                this.kayttis.tarkasteleDataa();
            } else if (this.kayttis.getValinnanNro() == 5) {
                this.kayttis.lopeta();
            }
        } else if (ae.getSource() == valitse2) {
            if (this.kayttis.getValinnanNro() == 1) {
                this.kayttis.perusTunnusLuvut();
            } else if (this.kayttis.getValinnanNro() == 2) {
                this.kayttis.t_testaa();
                System.out.println("aaaaaa");
            } else if (this.kayttis.getValinnanNro() == 3) {
                this.kayttis.ttestaa2();
            } else if (this.kayttis.getValinnanNro() == 4) {
                this.kayttis.anova();
            } else if (this.kayttis.getValinnanNro() == 5) {
                this.kayttis.lopeta();
            }
        } else if (ae.getSource() == tallenna) {
            this.lueTaulukko();
        } else if (ae.getSource() == palaa) {
            this.kayttis.tulostaPaavalikko();
        } else if (ae.getSource() == tunnarit) {
            this.kayttis.tulostaTunnarit();
        } else if (ae.getSource() == lueData) {
            this.lueData();
        } else if (ae.getSource() == ts) {
            this.laskeTestisuure();
        }
    }

    boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    boolean tryParseDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean tarkistaData(HashMap<String, ArrayList<Double>> listat) {
        int listatJoillaAlkioita = 0;
        ArrayList<Integer> listojenKoot = new ArrayList<>();
        for (String nimi : listat.keySet()) {
            if (!listat.get(nimi).isEmpty()) {
                listatJoillaAlkioita = listatJoillaAlkioita + 1;
                listojenKoot.add(listat.get(nimi).size());
            }
        }
        if (listatJoillaAlkioita != listat.keySet().size()) {
            return false;
        } else if (listat.keySet().isEmpty()) {
            this.virheenTyyppi=1;
            return false;
        }
        int ekanKoko = listojenKoot.get(0);
        for (int i : listojenKoot) {
            if (i != ekanKoko) {
                this.virheenTyyppi=2;
                return false;
            }
        }
        return true;
    }

    public void lueData() {
        this.kayttis.setTiedostonNimi(filename.getText());
        TiedostonLukija lukija = new TiedostonLukija();
        HashMap<String, ArrayList<Double>> datalistat = new HashMap<>();
        try {
            System.out.println(filename.getText());
            datalistat = lukija.lue(filename.getText());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KlikkaustenKuuntelija.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<String> muuttujanNimet = new ArrayList<>();
        for (String nimi : datalistat.keySet()) {
            muuttujanNimet.add(nimi);
        }
        Data data = new Data();
        this.kayttis.setData(data);
        this.kayttis.getData().setMuuttujaLista(muuttujanNimet);
        Double[][] datataulukko = new Double[muuttujanNimet.size()][datalistat.get(muuttujanNimet.get(1)).size()];
        for (String nimi : datalistat.keySet()) {
            for (int i = 0; i < datalistat.get(muuttujanNimet.get(1)).size(); i++) {
                datataulukko[muuttujanNimet.indexOf(nimi)][i] = datalistat.get(nimi).get(i);
            }
            this.kayttis.getData().setData(datataulukko);
        }
        ArrayList<String> henkilot = new ArrayList<>();
        for (int h = 0; h <= datalistat.get(muuttujanNimet.get(1)).size(); h++) {
            henkilot.add("Kh" + h);
        }
        this.kayttis.getData().setHenkilot(henkilot);
        this.kayttis.dataTallennettu();
    }

    public void laskeTestisuure() {
        if (this.kayttis.getValinnanNro() == 2) {
            Double myynolla = Double.parseDouble(this.myy.getText());
            this.myy.setText("");
            this.kayttis.setMyy(myynolla);
            this.kayttis.tTestitulos();
        }
        if (this.kayttis.getValinnanNro() == 3) {
            try {
                this.kayttis.Ttestitulos2();
            } catch (TyhjaMuuttujaException ex) {
                Logger.getLogger(KlikkaustenKuuntelija.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (this.kayttis.getValinnanNro() == 4) {
            this.kayttis.anovaTulos();
        }
    }

    public void lueTaulukko() {
        String teksti = this.datannimi.getText();
        this.datannimi.setText("");
        ArrayList<String> muuttujalista = new ArrayList<>();
        for (TextField kentta : this.muuttujat) {
            String muuttujannimi = kentta.getText();
            if (!muuttujannimi.isEmpty()) {
                muuttujalista.add(muuttujannimi);
                kentta.setText("");
            }
        }
        HashMap<String, ArrayList<Double>> listat = new HashMap<>();
        for (String nimi : muuttujalista) {
            listat.put(nimi, new ArrayList<Double>());
        }
        int nimenNro = 0;
        for (String nimi : listat.keySet()) {
            for (int i = 0; i < 10; i++) {
                Object value = this.taulukko.getValueAt(i, nimenNro);
                if (value != null) {
                    String arvo = value.toString();
                    if (this.tryParseDouble(arvo)) {
                        Double havainto = Double.parseDouble(arvo);
                        listat.get(nimi).add(havainto);
                    } else if (this.tryParseInt(arvo)) {
                        int havainto = Integer.parseInt(arvo);
                        listat.get(nimi).add((double) havainto);
                    } else {
                        this.virheenTyyppi = 1;
                        this.kayttis.tarkistaSyottamasiData();
                    }
                }
            }
            nimenNro = nimenNro + 1;
        }
        if (this.tarkistaData(listat)) {
            this.kayttis.setData(new Data());
            this.kayttis.getData().setName(teksti);
            this.kayttis.getData().setMuuttujaLista(muuttujalista);
            Double[][] datataulukko = new Double[muuttujalista.size()][listat.get(muuttujalista.get(1)).size()];
            for (String nimi : listat.keySet()) {
                for (int i = 0; i < listat.get(muuttujalista.get(1)).size(); i++) {
                    datataulukko[muuttujalista.indexOf(nimi)][i] = listat.get(nimi).get(i);
                }
                this.kayttis.getData().setData(datataulukko);
            }
            ArrayList<String> henkilot = new ArrayList<>();
            for (int h = 0; h <= listat.get(muuttujalista.get(1)).size(); h++) {
                henkilot.add("Kh" + h);
            }
            this.kayttis.getData().setHenkilot(henkilot);
            this.kayttis.dataTallennettu();
        } else {
            this.kayttis.tarkistaSyottamasiData();
        }
    }
    
    public int getVirheenTyyppi() {
        return this.virheenTyyppi;
    }
}
