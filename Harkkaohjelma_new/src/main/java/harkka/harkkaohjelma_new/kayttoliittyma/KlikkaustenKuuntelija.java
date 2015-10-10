/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkka.harkkaohjelma_new.kayttoliittyma;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author DELL
 */
public class KlikkaustenKuuntelija implements ActionListener {

    private Kayttoliittyma kayttis;
    private JButton valitse;
    private JButton valitse2;

    public KlikkaustenKuuntelija(Kayttoliittyma kayttis) {
        this.kayttis = kayttis;
    }

    public void setButtonValitse(JButton valitse) {
        this.valitse = valitse;
    }

    public void setButtonValitse2(JButton valitse2) {
        this.valitse2 = valitse2;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == valitse) {
            if (this.kayttis.getValinnanNro() == 2) {
                this.kayttis.syotaData();
            } else if (this.kayttis.getValinnanNro() == 3) {
                this.kayttis.tyhjaRuutu();
            } else {
                
            }
        } else if (ae.getSource() == valitse2) {
            this.kayttis.t_testaa();
            System.out.println("aaaaaa");
        }

    }
}
