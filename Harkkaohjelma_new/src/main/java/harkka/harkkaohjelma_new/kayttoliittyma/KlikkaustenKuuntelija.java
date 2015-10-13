/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkka.harkkaohjelma_new.kayttoliittyma;

import harkka.harkkaohjelma_new.Data;
import java.awt.Container;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == valitse) {
            if (this.kayttis.getValinnanNro() == 2) {
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
            for (JTextField kentta : this.havainnot) {

                if (!kentta.getText().isEmpty()) {
                    Double havainto = Double.parseDouble(kentta.getText());
                    havaintolista.add(havainto);
                    kentta.setText("");
                    nonNanhavaintolista.add(havainto);
                } else {
                    havaintolista.add(Double.NaN);
                }

            }
            System.out.println(havaintolista);
            int muuttujia = muuttujalista.size();
            int havaintoja = (int) nonNanhavaintolista.size() / muuttujalista.size();
            Double[][] taulukko = new Double[muuttujia][havaintoja];
            System.out.println((int) nonNanhavaintolista.size() / muuttujalista.size());
            System.out.println(nonNanhavaintolista);
            System.out.println(muuttujalista.size());
            System.out.println(havaintoja);
            System.out.println("pöööö");
            
            ArrayList<String> henkilot = new ArrayList<>();
            for (int h=0;  h<=havaintoja; h++) {
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

            this.kayttis.dataTallennettu();
        } else if (ae.getSource() == palaa) {
            System.out.println("aabbbccc");
            this.kayttis.tulostaPaavalikko();
        }  else if (ae.getSource() == tunnarit) {
            this.kayttis.tulostaTunnarit();
        }
    }
}
