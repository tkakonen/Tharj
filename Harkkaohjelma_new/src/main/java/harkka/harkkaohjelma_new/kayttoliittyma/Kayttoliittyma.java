/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkka.harkkaohjelma_new.kayttoliittyma;

import harkka.harkkaohjelma_new.ANOVA;
import harkka.harkkaohjelma_new.Data;
import harkka.harkkaohjelma_new.Kahden_otoksen_t_testi;
import harkka.harkkaohjelma_new.Muuttuja;
import harkka.harkkaohjelma_new.Yhden_otoksen_t_testi;
import harkka.harkkaohjelma_new.exceptions.TyhjaMuuttujaException;
import harkka.harkkaohjelma_new.reader.TiedostonLukija;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
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
    private String muuttujanNimi;
    private Double myy;
    private String ryhmMuuttujanNimi;
    private TiedostonLukija fileReader;
    private String tiedostonNimi;

    public Kayttoliittyma() {
        this.listen = new KlikkaustenKuuntelija(this);
        this.itemlisten = new ValintaKuuntelija(this);
    }

    public void setValinnanNro(int nro) {
        this.valinnannro = nro;
    }

    public void setMyy(Double myy) {
        this.myy = myy;
    }

    public void setRyhmMuuttujanNimi(String nimi) {
        this.ryhmMuuttujanNimi = nimi;
    }

    public void setTiedostonNimi(String nimi) {
        this.tiedostonNimi = nimi;
    }

    @Override
    public void run() {
        this.kaynnistaGraafinenKayttoliittyma();
    }

    public void setMuuttujanNimi(String nimi) {
        this.muuttujanNimi = nimi;
    }

    public String getMuuttujanNimi() {
        return this.muuttujanNimi;
    }

    public void tulostaPaavalikko() {
        Container container = this.frame.getContentPane();
        container.removeAll();
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

        if (this.data != null) {
            container.add(new JLabel("Käytössäsi on data '" + this.data.getName() + "'"));
        }

        this.asetaIkkuna();

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
        tulostaPaavalikko();

        frame.pack();
        frame.setVisible(true); // Luo sovelluksesi tänne
    }

    private JPanel muuttujaNimet() {
        JPanel panel = new JPanel(new GridLayout(1, 5));
        panel.add(new JLabel("Muuttujien nimet:"));
        TextField tf1, tf2, tf3, tf4, tf5;
        tf1 = new TextField("", 20);
        tf2 = new TextField("", 20);
        tf3 = new TextField("", 20);
        tf4 = new TextField("", 20);
        tf5 = new TextField("", 20);
        ArrayList<TextField> muuttujat = new ArrayList<>();
        muuttujat.add(tf1);
        muuttujat.add(tf2);
        muuttujat.add(tf3);
        muuttujat.add(tf4);
        muuttujat.add(tf5);
        for (TextField kentta : muuttujat) {
            kentta.addActionListener(listen);
            panel.add(kentta);
        }
        listen.setTextFieldsMuuttujat(muuttujat);
        return panel;
    }

    private JPanel luoValikko() {
        JPanel panel = new JPanel(new GridLayout(10, 5));
        ArrayList<JTextField> havainnot = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            JTextField textField = new JTextField();
            panel.add(textField);
            textField.addActionListener(listen);
            havainnot.add(textField);
        }
        listen.setTextFieldsHavainnot(havainnot);
        return panel;
    }

    private JPanel datanNimi() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.add(new JLabel("Datan nimi:"));
        JTextField y = new JTextField("");
        panel.add(y);
        y.addActionListener(listen);
        listen.setTextFieldDatannimi(y);

        return panel;
    }

    public JPanel havainnotTallenna() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(new JLabel("Havainnot:"));
        JButton tallenna = new JButton("Tallenna");
        panel.add(tallenna);
        JButton palaa = new JButton("Palaa päävalikkoon");
        panel.add(palaa);
        tallenna.addActionListener(listen);
        palaa.addActionListener(listen);
        listen.setButtonTallenna(tallenna);
        listen.setPalaa(palaa);
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

        this.asetaIkkuna();

    }

    public void analysoi() {

        this.frame.setBackground(Color.white);
        Container container = this.frame.getContentPane();
        container.removeAll();
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        container.add(new JLabel("VALITSE ANALYYSI:"));

        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton perus = new JRadioButton("Perutunnusluvut");
        buttonGroup.add(perus);
        container.add(perus);
        perus.setName("perus");
        perus.addItemListener(itemlisten);
        JRadioButton ttest1 = new JRadioButton("Yhden otoksen t-testi");
        buttonGroup.add(ttest1);
        container.add(ttest1);
        ttest1.setName("ttest1");
        ttest1.addItemListener(itemlisten);
        JRadioButton ttest2 = new JRadioButton("Kahden otoksen t-testi");
        buttonGroup.add(ttest2);
        container.add(ttest2);
        ttest2.setName("ttest2");
        ttest2.addItemListener(itemlisten);
        JRadioButton anova = new JRadioButton("Varianssianalyysi");
        buttonGroup.add(anova);
        container.add(anova);
        anova.setName("anova");
        anova.addItemListener(itemlisten);
        JRadioButton lopeta = new JRadioButton("Lopeta");
        buttonGroup.add(lopeta);
        container.add(lopeta);
        lopeta.setName("lopeta");
        lopeta.addItemListener(itemlisten);

        ButtonGroup buttonGroup2 = new ButtonGroup();
        JButton valitse2 = new JButton("Valitse");
        buttonGroup2.add(valitse2);

        listen.setButtonValitse2(valitse2);
        valitse2.addActionListener(listen);
        container.add(valitse2);
        JButton palaa = new JButton("Palaa päävalikkoon");
        palaa.addActionListener(listen);
        listen.setPalaa(palaa);
        this.asetaIkkuna();
    }

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void dataTallennettu() {
        JFrame tallennettu = new JFrame("Ikkuna");
        tallennettu.setPreferredSize(new Dimension(200, 200));
        tallennettu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tallennettu.setBackground(Color.white);
        Container container = tallennettu.getContentPane();
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        container.add(new JLabel("Data '" + this.data.getName() + "' on tallennettu"));
        ButtonGroup buttonGroup3 = new ButtonGroup();
        JButton palaa = new JButton("Palaa päävalikkoon");
        buttonGroup3.add(palaa);
        container.add(palaa);
        palaa.addActionListener(listen);
        listen.setPalaa(palaa);

        tallennettu.pack();
        tallennettu.setVisible(
                true);

    }

    public void perusTunnusLuvut() {
        this.frame.setBackground(Color.white);
        Container container = this.frame.getContentPane();
        container.removeAll();
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        if (this.data == null) {
            container.add(new JLabel("Et ole syöttänyt dataa"));
            JButton palaa = new JButton("Palaa päävalikkoon");
            container.add(palaa);
            palaa.addActionListener(listen);
            listen.setPalaa(palaa);
            this.asetaIkkuna();
        } else {
            container.add(new JLabel("Valitse muuttuja, jonka perustunnusluvut haluat tulostaa:"));
            container.add(new JLabel("Kaytossasi on data: " + this.data.getName()));

            container.add(new JLabel("Muuttujan nimi"));
            ButtonGroup buttonGroup = new ButtonGroup();
            for (String nimi : this.data.getMuuttujanNimet()) {
                JRadioButton nappula = new JRadioButton(nimi);
                buttonGroup.add(nappula);
                container.add(nappula);
                nappula.setName(nimi);
                nappula.addItemListener(itemlisten);
            }

            JButton tulostaTunnusluvut = new JButton("Tulosta tunnusluvut");
            container.add(tulostaTunnusluvut);
            tulostaTunnusluvut.addActionListener(listen);
            listen.setTunnarit(tulostaTunnusluvut);
            JButton palaa = new JButton("Palaa päävalikkoon");
            container.add(palaa);
            palaa.addActionListener(listen);
            listen.setPalaa(palaa);

            this.asetaIkkuna();

        }
    }

    public void anova() {
        this.frame.setBackground(Color.white);
        Container container = this.frame.getContentPane();
        container.removeAll();
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        if (this.data == null) {
            container.add(new JLabel("Et ole syöttänyt dataa"));
            JButton palaa = new JButton("Palaa päävalikkoon");
            container.add(palaa);
            palaa.addActionListener(listen);
            listen.setPalaa(palaa);
            this.frame.repaint();
            frame.pack();
            frame.setVisible(true);
        } else {
            container.add(new JLabel("Yksisuuntainen varianssianalyysi:"));
            container.add(new JLabel("Kaytossasi on data: " + this.data.getName()));
            container.add(new JLabel("Riippuvan muuttujan nimi"));
            ButtonGroup buttonGroup = new ButtonGroup();
            for (String nimi : this.data.getMuuttujanNimet()) {
                JRadioButton nappula = new JRadioButton(nimi);
                buttonGroup.add(nappula);
                container.add(nappula);
                nappula.addItemListener(itemlisten);
                nappula.setName(nimi);
            }

            container.add(new JLabel("Ryhmittelevän muuttujan nimi"));

            ButtonGroup buttonGroup2 = new ButtonGroup();
            for (String nimi : this.data.getMuuttujanNimet()) {
                JRadioButton nappula = new JRadioButton(nimi);
                buttonGroup2.add(nappula);
                container.add(nappula);
                nappula.addItemListener(itemlisten);
                nappula.setName(nimi + "2");
            }

            ButtonGroup buttonGroup3 = new ButtonGroup();
            JButton ts = new JButton("Laske testisuure");
            buttonGroup3.add(ts);
            listen.setTs(ts);
            ts.addActionListener(listen);
            container.add(ts);

            this.asetaIkkuna();
        }
    }

    public void anovaTulos() {
        this.frame.setBackground(Color.white);
        Container container = this.frame.getContentPane();
        container.removeAll();
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        container.add(new JLabel("Yksisuuntainen varianssianalyysi, tulokset:"));

        ArrayList erilaiset = this.data.getMuuttuja(this.ryhmMuuttujanNimi).erilaisetArvot();
        this.data.getMuuttuja(muuttujanNimi).setData(this.data);
        this.data.getMuuttuja(ryhmMuuttujanNimi).setData(this.data);
        ArrayList ryhmat = this.data.getMuuttuja(muuttujanNimi).ryhmittele(this.ryhmMuuttujanNimi, erilaiset);

        ANOVA anova = new ANOVA(ryhmat);
        anova.tulostaMuuttujat();
        container.add(new JLabel("F-testisuureen arvo:"));
        double ts = anova.laskeTestisuureenArvo();
        container.add(new JLabel("" + ts));

        container.add(new JLabel("Vapausasteet: df(sis)=" + anova.dfSisainenVaihtelu() + ", df(väl)=" + anova.dfValinenVaihtelu()));

        container.add(new JLabel("P-arvo:"));
        container.add(new JLabel("" + anova.laskeP_arvo()));

        JButton palaa = new JButton("Palaa päävalikkoon");
        container.add(palaa);
        palaa.addActionListener(listen);
        listen.setPalaa(palaa);

        this.asetaIkkuna();
    }

    public void t_testaa() {
        this.frame.setBackground(Color.white);
        Container container = this.frame.getContentPane();
        container.removeAll();
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        if (this.data == null) {
            container.add(new JLabel("Et ole syöttänyt dataa"));
            JButton palaa = new JButton("Palaa päävalikkoon");
            container.add(palaa);
            palaa.addActionListener(listen);
            listen.setPalaa(palaa);
            this.asetaIkkuna();
        } else {
            container.add(new JLabel("Yhden otoksen t-testi:"));
            container.add(new JLabel("Kaytossasi on data: " + this.data.getName()));

            container.add(new JLabel("Riippuvan muuttujan nimi"));
            ButtonGroup buttonGroup = new ButtonGroup();
            for (String nimi : this.data.getMuuttujanNimet()) {
                JRadioButton nappula = new JRadioButton(nimi);
                buttonGroup.add(nappula);
                container.add(nappula);
                nappula.addItemListener(itemlisten);
                nappula.setName(nimi);
            }
            container.add(new JLabel("Anna vertailtava keskiarvo:"));
            JTextField myy = new JTextField("");
            myy.setMaximumSize(
                    new Dimension(Integer.MAX_VALUE, myy.getPreferredSize().height));
            container.add(myy);
            myy.addActionListener(listen);
            listen.setTextFieldMyy(myy);

            ButtonGroup buttonGroup2 = new ButtonGroup();
            JButton ts = new JButton("Laske testisuure");
            buttonGroup2.add(ts);
            listen.setTs(ts);
            ts.addActionListener(listen);
            container.add(ts);

        }

        JButton palaa = new JButton("Palaa päävalikkoon");
        container.add(palaa);
        palaa.addActionListener(listen);
        listen.setPalaa(palaa);

        this.asetaIkkuna();
    }

    public void ttestaa2() {
        this.frame.setBackground(Color.white);
        Container container = this.frame.getContentPane();
        container.removeAll();
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        if (this.data == null) {
            container.add(new JLabel("Et ole syöttänyt dataa"));
            JButton palaa = new JButton("Palaa päävalikkoon");
            container.add(palaa);
            palaa.addActionListener(listen);
            listen.setPalaa(palaa);
            this.asetaIkkuna();
        } else {
            container.add(new JLabel("Yhden otoksen t-testi:"));
            container.add(new JLabel("Kaytossasi on data: " + this.data.getName()));
            container.add(new JLabel("Riippuvan muuttujan nimi"));
            ButtonGroup buttonGroup = new ButtonGroup();
            for (String nimi : this.data.getMuuttujanNimet()) {
                JRadioButton nappula = new JRadioButton(nimi);
                buttonGroup.add(nappula);
                container.add(nappula);
                nappula.addItemListener(itemlisten);
                nappula.setName(nimi);
            }

            container.add(new JLabel("Ryhmittelevän muuttujan nimi"));

            ButtonGroup buttonGroup2 = new ButtonGroup();
            for (String nimi : this.data.getMuuttujanNimet()) {
                JRadioButton nappula = new JRadioButton(nimi);
                buttonGroup2.add(nappula);
                container.add(nappula);
                nappula.addItemListener(itemlisten);
                nappula.setName(nimi + "2");
            }

            ButtonGroup buttonGroup3 = new ButtonGroup();
            JButton ts = new JButton("Laske testisuure");
            buttonGroup3.add(ts);
            listen.setTs(ts);
            ts.addActionListener(listen);
            container.add(ts);

        }

        JButton palaa = new JButton("Palaa päävalikkoon");
        container.add(palaa);
        palaa.addActionListener(listen);
        listen.setPalaa(palaa);

        this.asetaIkkuna();
    }

    public void lopeta() {
        this.frame.dispose();
    }

    public void tTestitulos() {
        this.frame.setBackground(Color.white);
        Container container = this.frame.getContentPane();
        container.removeAll();
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        container.add(new JLabel("Yhden otoksen t-testi, tulokset:"));
        container.add(new JLabel("Muuttujan keskiarvo: " + this.data.getMuuttuja(muuttujanNimi).Keskiarvo()));
        container.add(new JLabel("Vertaillaan keskiarvoon: " + this.myy));

        container.add(new JLabel("T-testisuureen arvo:"));

        Yhden_otoksen_t_testi testi = new Yhden_otoksen_t_testi();
        testi.lisaaParametrit(this.data.getMuuttuja(muuttujanNimi), myy);
        double ts = testi.laskeTestisuureenArvo();

        container.add(new JLabel("" + ts));

        container.add(new JLabel("Vapausasteet:"));
        container.add(new JLabel("" + testi.laskeVapausasteet()));

        container.add(new JLabel("P-arvo (2-tailed):"));
        container.add(new JLabel("" + testi.laske_kaksisuuntainen_p_arvoTestisuureelle()));

        JButton palaa = new JButton("Palaa päävalikkoon");
        container.add(palaa);
        palaa.addActionListener(listen);
        listen.setPalaa(palaa);

        this.asetaIkkuna();

    }

    public void tulostaTunnarit() {
        this.frame.setBackground(Color.white);
        Container container = this.frame.getContentPane();
        container.removeAll();
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        container.add(new JLabel("Muuttujan '" + this.getMuuttujanNimi() + "' perustunnusluvut:"));
        System.out.println(this.data.getMuuttuja(muuttujanNimi).getArvot());
        System.out.println(this.data.getMuuttuja(muuttujanNimi).getArvot());
        System.out.println(this.data.getMuuttuja(muuttujanNimi).getArvot());
        System.out.println(this.data.getMuuttuja(muuttujanNimi).getArvot());

        container.add(new JLabel("Keskiarvo: " + this.data.getMuuttuja(muuttujanNimi).Keskiarvo()));
        container.add(new JLabel("Otosvarianssi: " + this.data.getMuuttuja(muuttujanNimi).otosVarianssi()));

        JButton palaa = new JButton("Palaa päävalikkoon");
        container.add(palaa);
        palaa.addActionListener(listen);
        listen.setPalaa(palaa);

        this.asetaIkkuna();

    }

    public void Ttestitulos2() throws TyhjaMuuttujaException {
        this.frame.setBackground(Color.white);
        Container container = this.frame.getContentPane();
        container.removeAll();
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        container.add(new JLabel("Yhden otoksen t-testi, tulokset:"));
        container.add(new JLabel("Muuttujan keskiarvo: " + this.data.getMuuttuja(muuttujanNimi).Keskiarvo()));
        container.add(new JLabel("Muuttujan keskiarvo: " + this.data.getMuuttuja(this.ryhmMuuttujanNimi).Keskiarvo()));

        container.add(new JLabel("T-testisuureen arvo:"));

        Kahden_otoksen_t_testi testi = new Kahden_otoksen_t_testi(this.data.getMuuttuja(muuttujanNimi), this.data.getMuuttuja(this.ryhmMuuttujanNimi));

        double ts = testi.laskeTestisuureenArvo();

        container.add(new JLabel("" + ts));

        container.add(new JLabel("P-arvo (2-tailed):"));
        container.add(new JLabel("" + testi.laskeP_arvoTestisuureelle_kaksisuuntainen()));

        JButton palaa = new JButton("Palaa päävalikkoon");
        container.add(palaa);
        palaa.addActionListener(listen);
        listen.setPalaa(palaa);

        this.asetaIkkuna();
    }

    public void lueData() {
        this.frame.setBackground(Color.white);
        Container container = this.frame.getContentPane();
        container.removeAll();
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        container.add(new JLabel("Luettavan tiedoston nimi: "));

        JTextField filename = new JTextField("");
        filename.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, filename.getPreferredSize().height));
        container.add(filename);
        filename.addActionListener(listen);
        listen.setFileName(filename);

        JButton lueData = new JButton("Lue tiedosto");
        lueData.addActionListener(listen);
        listen.setLueData(lueData);
        container.add(lueData);

        JButton palaa = new JButton("Palaa päävalikkoon");
        container.add(palaa);
        palaa.addActionListener(listen);
        listen.setPalaa(palaa);

        this.asetaIkkuna();
    }

    public void asetaIkkuna() {
        this.frame.repaint();
        frame.pack();
        frame.setVisible(true);
    }

    public void tarkistaSyottamasiData(int ongelmanTyyppi) {
        final JFrame tarkista = new JFrame("Ikkuna");
        tarkista.setPreferredSize(new Dimension(300, 200));
        tarkista.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tarkista.setBackground(Color.white);
        Container container = tarkista.getContentPane();
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        container.add(new JLabel("Datan tallentaminen ei onnistunut."));
        if (ongelmanTyyppi == 1) {
        container.add(new JLabel("Tarkista, että syöttämäsi data on numeerinen."));
        container.add(new JLabel("Käytä desimaalierottimena pistettä."));
        } else if (ongelmanTyyppi ==2) {
        container.add(new JLabel("Tarkista, että kaikilla muuttujilla on nimi."));
        }
        ButtonGroup buttonGroup3 = new ButtonGroup();

        JButton ok = new JButton("OK");
        buttonGroup3.add(ok);
        container.add(ok);
        ActionListener klik = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tarkista.dispose();
            }
        };
        ok.addActionListener(klik);
        tarkista.pack();
        tarkista.setVisible(true);

    }

}
