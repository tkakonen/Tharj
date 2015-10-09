/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkka.harkkaohjelma_new.kayttoliittyma;

import harkka.harkkaohjelma_new.Kahden_otoksen_t_testi;
import harkka.harkkaohjelma_new.Yhden_otoksen_t_testi;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import java.util.Scanner;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

/**
 *
 * @author DELL
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private KlikkaustenKuuntelija listen;

    public Kayttoliittyma() {
        this.listen = new KlikkaustenKuuntelija(this);
    }

    @Override
    public void run() {
        this.kaynnistaGraafinenKayttoliittyma();
    }

    public void tulostaPaavalikko() {
        System.out.println("");
        System.out.println("");

        System.out.println("Valikko: ");
        System.out.println("");
        System.out.println("0: Lopeta");
        System.out.println("1: Lue data");
        System.out.println("2: Syötä data");
        System.out.println("3: Analysoi");
    }

    private void luoKomponentit(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        container.add(new JLabel("VALIKKO:"));

        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton lue = new JRadioButton("Lue data");
        buttonGroup.add(lue);
        container.add(lue);
        JRadioButton syota = new JRadioButton("Syötä data");
        buttonGroup.add(syota);
        container.add(syota);
        JRadioButton explore = new JRadioButton("Tarkastele dataa");
        buttonGroup.add(explore);
        container.add(explore);
        JRadioButton analysoi = new JRadioButton("Analysoi");
        buttonGroup.add(analysoi);
        container.add(analysoi);
        JRadioButton lopeta = new JRadioButton("Lopeta");
        buttonGroup.add(lopeta);
        container.add(lopeta);

        ButtonGroup buttonGroup2 = new ButtonGroup();
        JButton valitse = new JButton("Valitse");
        buttonGroup2.add(valitse);
        listen.setButtonValitse(valitse);

        valitse.addActionListener(listen);

        container.add(valitse);

    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame() {
        frame = new JFrame("Ikkuna");
        frame.setPreferredSize(new Dimension(700, 500));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void kaynnistaGraafinenKayttoliittyma() {
        this.setFrame();
        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true); // Luo sovelluksesi tänne
    }

    public void tyhjaRuutu() {

        this.frame.setBackground(Color.white);
        Container container = this.frame.getContentPane();
        container.removeAll();
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        container.add(new JLabel("VALITSE ANALYYSI:"));

        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton lue = new JRadioButton("Perutunnusluvut");
        buttonGroup.add(lue);
        container.add(lue);
        JRadioButton syota = new JRadioButton("Yhden otoksen t-testi");
        buttonGroup.add(syota);
        container.add(syota);
        JRadioButton explore = new JRadioButton("Kahden otoksen t-testi");
        buttonGroup.add(explore);
        container.add(explore);
        JRadioButton analysoi = new JRadioButton("Varianssianalyysi");
        buttonGroup.add(analysoi);
        container.add(analysoi);
        JRadioButton lopeta = new JRadioButton("Lopeta");
        buttonGroup.add(lopeta);
        container.add(lopeta);

        ButtonGroup buttonGroup2 = new ButtonGroup();
        JButton valitse2 = new JButton("Valitse");
        buttonGroup2.add(valitse2);

        listen.setButtonValitse2(valitse2);
        valitse2.addActionListener(listen);
        this.frame.repaint();
        container.add(valitse2);
        frame.pack();
        frame.setVisible(true);
    }

    public void t_testaa() {
        this.frame.setBackground(Color.white);
        Container container = this.frame.getContentPane();
        container.removeAll();
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        container.add(new JLabel("Yhden otoksen t-testi:"));

        frame.pack();
        frame.setVisible(true);
    }

    public void kaynnistaTekstiKayttoliittyma() {
        System.out.println("Tervetuloa");
        this.tulostaPaavalikko();
        Scanner lukija = new Scanner(System.in);

        while (true) {
            int syote = Integer.parseInt(lukija.nextLine());
            if (syote == 0) {
                break;
            } else if (syote == 1) {
                System.out.println("Luettava tiedosto: ");
                System.out.println("Poistu syötteellä X");
                String tiedostonNimi = lukija.nextLine();

                System.out.println("Palaa pÃƒÂ¤ÃƒÂ¤valikkoon syÃƒÂ¶tteella X");
                if (tiedostonNimi.equals("X")) {
                    break;
                }
            } else if (syote == 2) {
                System.out.println("SyÃƒÂ¶tÃƒÂ¤ data alla olevaan kenttÃƒÂ¤ÃƒÂ¤n.");
            } else if (syote == 3) {
                System.out.println("Valitse analyysi:");
                System.out.println("");
                System.out.println("");
                System.out.println("1: Perustunnusluvut");
                System.out.println("2: Yhden otoksen t-testi");
                System.out.println("3: Kahden riippumattoman otoksen t-testi");
                System.out.println("4: Yksisuuntainen ANOVA");
                System.out.println("0: Palaa pÃƒÂ¤ÃƒÂ¤valikkoon.");
                while (true) {
                    int syote2 = Integer.parseInt(lukija.nextLine());
                    if (syote2 == 1) {
                        System.out.println("KÃƒÂ¤ytÃƒÂ¶ssÃƒÂ¤ oleva data: xxxx");
                        System.out.println("Valitse muuttuja");
                        String muuttujanNimi = lukija.nextLine();
                        ////PerusTL tunnarit = new PerusTL(muuttuja);.
                        ////Tunnarit.tulosta();
                    } else if (syote2 == 2) {
                        System.out.println("KÃƒÂ¤ytÃƒÂ¶ssÃƒÂ¤si on data xxxxx");
                        System.out.println("Valitse muuttuja");
                        String muuttujanNimi = lukija.nextLine();
                        System.out.println("Valitse vertailtava keskiarvo");
                        int myynolla = Integer.parseInt(lukija.nextLine());
                        Yhden_otoksen_t_testi testi = new Yhden_otoksen_t_testi();
                        testi.lisaaParametrit(null, myynolla);
                        double ts = testi.laskeTestisuureenArvo();
                        System.out.println(ts);
                    } else if (syote2 == 3) {
                        System.out.println("KÃ¤ytÃ¶ssÃ¤si on data xxxxx");
                        System.out.println("Valitse riippuva muuttuja");
                        System.out.println("Valitse ryhmittelevÃ¤ muuttuja");

                        ////*Kahden_otoksen_t_testi testi = new Kahden_otoksen_t_testi(muuttuja1, muuttuja2);
                        ////*double ts = testi.laskeTestisuureenArvo();
                        ////*System.out.println(ts);
                    } else if (syote2 == 4) {
                        System.out.println("KÃ¤ytÃ¶ssÃ¤si on data xxxxxx");
                        System.out.println("Riippuva muuttuja");
                        System.out.println("RyhmittelevÃ¤ muuttuja");

                        ////ANOVA anova = new ANOVA();
                        /////jne....
                    }
                }
            }

        }
    }

}
