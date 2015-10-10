/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkka.harkkaohjelma_new.kayttoliittyma;

import harkka.harkkaohjelma_new.Data;
import harkka.harkkaohjelma_new.Kahden_otoksen_t_testi;
import harkka.harkkaohjelma_new.Yhden_otoksen_t_testi;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ItemListener;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

/**
 *
 * @author DELL
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private KlikkaustenKuuntelija listen;
    private ItemListener itemlisten;
    private int valinnannro;
    private Data data;

    public Kayttoliittyma() {
        this.listen = new KlikkaustenKuuntelija(this);
        this.itemlisten = new ValintaKuuntelija(this);
    }

    public void setValinnanNro(int nro) {
        this.valinnannro = nro;
    }

    @Override
    public void run() {
        this.kaynnistaGraafinenKayttoliittyma();
    }

    private void luoKomponentit(Container container) {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        container.add(new JLabel("VALIKKO:"));

        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton lue = new JRadioButton("Lue data");
        buttonGroup.add(lue);
        container.add(lue);
        lue.setName("lue");
        lue.addItemListener(this.itemlisten);
        JRadioButton syota = new JRadioButton("Syötä data");
        buttonGroup.add(syota);
        container.add(syota);
        syota.setName("syota");
        syota.addItemListener(itemlisten);
        JRadioButton explore = new JRadioButton("Tarkastele dataa");
        buttonGroup.add(explore);
        container.add(explore);
        explore.setName("explore");
        explore.addItemListener(itemlisten);
        JRadioButton analysoi = new JRadioButton("Analysoi");
        buttonGroup.add(analysoi);
        container.add(analysoi);
        analysoi.setName("analysoi");
        analysoi.addItemListener(itemlisten);
        JRadioButton lopeta = new JRadioButton("Lopeta");
        buttonGroup.add(lopeta);
        container.add(lopeta);
        lopeta.setName("lopeta");
        lopeta.addItemListener(itemlisten);

        ButtonGroup buttonGroup2 = new ButtonGroup();
        JButton valitse = new JButton("Valitse");
        buttonGroup2.add(valitse);
        listen.setButtonValitse(valitse);

        valitse.addActionListener(listen);

        container.add(valitse);

    }

    public int getValinnanNro() {
        return this.valinnannro;
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

    private JPanel muuttujaNimet() {
        JPanel panel = new JPanel(new GridLayout(1, 5));
        panel.add(new JLabel("Muuttujien nimet:"));
        TextField tf1, tf2, tf3, tf4, tf5, tf6;
        tf1 = new TextField("", 20);
        tf2 = new TextField("", 20);
        tf3 = new TextField("", 20);
        tf4 = new TextField("", 20);
        tf5 = new TextField("", 20);
        panel.add(tf1);
        panel.add(tf2);
        panel.add(tf3);
        panel.add(tf4);
        panel.add(tf5);
        return panel;
    }

    private JPanel luoValikko() {
        JPanel panel = new JPanel(new GridLayout(10, 5));
        for (int i = 0; i < 50; i++) {
            JTextField textField = new JTextField();
            panel.add(textField);
        }

        return panel;
    }

    private JPanel datanNimi() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.add(new JLabel("Datan nimi:"));
        JTextField y = new JTextField("");
        panel.add(y);
        return panel;
    }

    public JPanel havainnotTallenna() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(new JLabel("Havainnot:"));
        JButton tallenna = new JButton("Tallenna");
        panel.add(tallenna);
        return panel;
    }

    public void syotaData() {
        this.frame.setBackground(Color.white);
        Container container = this.frame.getContentPane();
        container.removeAll();
        GridLayout layout = new GridLayout(4, 1);
        container.setLayout(layout);
        container.add(this.datanNimi(), BorderLayout.SOUTH);
        container.add(this.muuttujaNimet(), BorderLayout.SOUTH);
        container.add(this.havainnotTallenna());
        container.add(this.luoValikko(), BorderLayout.SOUTH);

        this.frame.repaint();
        frame.pack();
        frame.setVisible(true);

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
        if (this.data == null) {
            container.add(new JLabel("Et ole syöttänyt dataa"));
        } else {
            container.add(new JLabel("Yhden otoksen t-testi:"));
            container.add(new JLabel("Kaytossasi on data: " + this.data.getName()));

            container.add(new JLabel("Riippuvan muuttujan nimi"));
            ButtonGroup buttonGroup = new ButtonGroup();
            for (String nimi : this.data.getMuuttujanNimet()) {
                buttonGroup.add(new JRadioButton(nimi));
                container.add(new JRadioButton(nimi));
            }

            container.add(new JLabel("Ryhmittelevän muuttujan nimi"));

            ButtonGroup buttonGroup2 = new ButtonGroup();
            for (String nimi : this.data.getMuuttujanNimet()) {
                buttonGroup.add(new JRadioButton(nimi));
                container.add(new JRadioButton(nimi));
            }

        }

        this.frame.repaint();
        frame.pack();
        frame.setVisible(true);
    }

}
