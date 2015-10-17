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

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == valitse) {
            if (this.kayttis.getValinnanNro() == 1) {
                this.kayttis.lueData();
            } else if (this.kayttis.getValinnanNro() == 2) {
                this.kayttis.syotaData();
            } else if (this.kayttis.getValinnanNro() == 3) {
                this.kayttis.analysoi();
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
            String teksti = this.datannimi.getText();
            this.kayttis.setData(new Data());
            this.kayttis.getData().setName(teksti);
            this.datannimi.setText("");
            System.out.println(this.kayttis.getData().getName());

            ArrayList<String> muuttujalista = new ArrayList<>();
            for (TextField kentta : this.muuttujat) {
                String muuttujannimi = kentta.getText();
                if (!muuttujannimi.isEmpty()) {
                    muuttujalista.add(muuttujannimi);
                    kentta.setText("");
                }
            }
            this.kayttis.getData().setMuuttujaLista(muuttujalista);
            System.out.println(this.kayttis.getData().getMuuttujanNimet());

            ArrayList<Double> havaintolista = new ArrayList<>();
            ArrayList<Double> nonNanhavaintolista = new ArrayList<>();
            boolean datakunnossa = true;
            for (JTextField kentta : this.havainnot) {

                if (!kentta.getText().isEmpty()) {
                    if (this.tryParseDouble(kentta.getText())) {
                        Double havainto = Double.parseDouble(kentta.getText());
                        havaintolista.add(havainto);
                        kentta.setText("");
                        nonNanhavaintolista.add(havainto);
                    } else if (this.tryParseInt(teksti)) {
                        int havainto = Integer.parseInt(kentta.getText());
                        havaintolista.add((double) havainto);
                        kentta.setText("");
                        nonNanhavaintolista.add((double) havainto);
                    } else {
                        int o = 1;
                        this.kayttis.tarkistaSyottamasiData(o);
                        datakunnossa = false;
                    }

                } else {
                    havaintolista.add(Double.NaN);
                }

            }
            System.out.println(havaintolista);
            int muuttujia = muuttujalista.size();
            int havaintoja = (int) nonNanhavaintolista.size() / muuttujalista.size();
            Double[][] taulukko = new Double[muuttujia][havaintoja];



            ArrayList<String> henkilot = new ArrayList<>();
            for (int h = 0; h <= havaintoja; h++) {
                henkilot.add("Kh" + h);
            }
            this.kayttis.getData().setHenkilot(henkilot);

            int i = 0;
            int j = 0;
            for (Double luku : nonNanhavaintolista) {
                System.out.println(i);
                System.out.println(j);
                taulukko[i][j] = luku;
                i = i + 1;

                if ((i) % muuttujia == 0) {
                    j = j + 1;
                    i = 0;
                }

            }

            System.out.println(Arrays.deepToString(taulukko));

            this.kayttis.getData().setData(taulukko);
            System.out.println(Arrays.deepToString(this.kayttis.getData().getData()));
            if (datakunnossa) {
                this.kayttis.dataTallennettu();
            }
        } else if (ae.getSource() == palaa) {
            this.kayttis.tulostaPaavalikko();
        } else if (ae.getSource() == tunnarit) {
            this.kayttis.tulostaTunnarit();
        } else if (ae.getSource() == lueData) {
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

        } else if (ae.getSource() == ts) {
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

}
