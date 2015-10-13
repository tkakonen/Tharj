/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkka.harkkaohjelma_new.kayttoliittyma;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JRadioButton;

/**
 *
 * @author DELL
 */
public class ValintaKuuntelija implements ItemListener {

    private JRadioButton eka;
    private JRadioButton toka;
    private JRadioButton kolmas;
    private JRadioButton neljas;
    private JRadioButton viides;
    private int valinnannro;
    private Kayttoliittyma kayttis;

    public ValintaKuuntelija(Kayttoliittyma kayttis) {
        this.kayttis = kayttis;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            JRadioButton check = (JRadioButton) e.getSource();
            String name = check.getName();
            if (name.equals("lue")) {
                System.out.println("pööö");
                this.kayttis.setValinnanNro(1);
            } else if (name.equals("syota")) {
                System.out.println("koo");
                this.kayttis.setValinnanNro(2);
            } else if (name.equals("analysoi")) {
                System.out.println("joo");
                this.kayttis.setValinnanNro(3);
            } else if (name.equals("explore")) {
                System.out.println("ahaa");
                this.kayttis.setValinnanNro(4);
            } else if (name.equals("lopeta")) {
                System.out.println("papapaa");
                this.kayttis.setValinnanNro(5);
                
            } else if (name.equals("perus")) {
                System.out.println("papapaa");
                this.kayttis.setValinnanNro(1);
            } else if (name.equals("ttest1")) {
                System.out.println("papapaa");
                this.kayttis.setValinnanNro(2);
            } else if (name.equals("ttest2")) {
                System.out.println("papapaa");
                this.kayttis.setValinnanNro(3);
            } else if (name.equals("anova")) {
                System.out.println("papapaa");
                this.kayttis.setValinnanNro(4);
            } else if (name.equals("lopeta")) {
                System.out.println("papapaa");
                this.kayttis.setValinnanNro(5);
            } else {
            for (String nimi: this.kayttis.getData().getMuuttujanNimet()) {
                if (name.equals(nimi)) {
                    this.kayttis.setMuuttujanNimi(nimi);
            }
            }
                    
                    }

        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
            // Your deselected code here.
        }
    }

}
