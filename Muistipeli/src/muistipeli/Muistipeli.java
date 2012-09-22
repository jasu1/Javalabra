package muistipeli;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * Muistipelin graafinen käyttöliittymä. Kortit käännetään
 * hiirellä klikkaamalla ja kun kaksi korttia on yhtä aikaa käännettynä, tapahtuu
 * korttiparin tarkistus. Mikäli kortit ovat samat, ne jäävät näkyviin, muutoin
 * ne kääntyvät piiloon. Pelaaja voi valita pelissä käytettävät kortit
 * hakemistoista liikennemerkkiryhmän perusteella (tällä hetkellä käytettävissä
 * kolme ryhmää).
 *
 * @author Jari Haapala
 */
public class Muistipeli {

    /**
     * Pelin pohja mihin kaikki muut pelin elementit sijoitetaan
     */
    private Pelipohja pelipohja;
    
    /**
     * Liikennemerkkiryhmä: ryhmään kuuluvat eri liikennemerkit tarkoituksensa
     * mukaan jaoteltuna (merkit on tallennettu ryhmän mukaisiin hakemistoihin).
     */
    private int merkkilaji;
    
    /**
     * Ikkuna johon peli muodostetaan
     */
    private JFrame ikkuna;
    private CheckboxMenuItem kieltomerkit;
    private CheckboxMenuItem maaraysmerkit;
    private CheckboxMenuItem varoitusmerkit;

    /**
     * Luo uuden muistipelin valitusta liikennemerkkiryhmästä
     * @param mlaji 
     */
    public Muistipeli(int mlaji) {
        ikkuna = new JFrame("Muistipeli");
        merkkilaji = mlaji;
        pelipohja = new Pelipohja(this, merkkilaji);

        JPanel tekstit = new JPanel();
        tekstit.setLayout(new GridLayout(1, 2));
        tekstit.add(pelipohja.naytaArvaukset());
        tekstit.add(pelipohja.naytaParit());

        ikkuna.getContentPane().add(tekstit, BorderLayout.NORTH);
        ikkuna.getContentPane().add(pelipohja, BorderLayout.CENTER);

        ikkuna.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Määritellään valikon osat
        MenuItem uusipeli = new MenuItem("Uusi peli", new MenuShortcut(KeyEvent.VK_N));
        MenuItem lopetus = new MenuItem("Lopeta", new MenuShortcut(KeyEvent.VK_Q));

        kieltomerkit = new CheckboxMenuItem("Kieltomerkit", (merkkilaji == 1));
        maaraysmerkit = new CheckboxMenuItem("Määräysmerkit", (merkkilaji == 2));
        varoitusmerkit = new CheckboxMenuItem("Varoitusmerkit", (merkkilaji == 3));

        Menu pelivalikko = new Menu("Valikko");

        Menu merkkivalikko = new Menu("Valitse merkkiryhmä ");

        // Luodaan alavalikko josta valitaan haluttu liikennemerkkiryhmä
        merkkivalikko.add(kieltomerkit);
        merkkivalikko.add(maaraysmerkit);
        merkkivalikko.add(varoitusmerkit);


        // Lisätään valikon osat
        pelivalikko.add(uusipeli);
        pelivalikko.addSeparator();
        pelivalikko.add(merkkivalikko);
        pelivalikko.addSeparator();
        pelivalikko.add(lopetus);


        MenuBar valikkopalkki = new MenuBar();
        valikkopalkki.add(pelivalikko);

        ikkuna.setMenuBar(valikkopalkki);
        lopetus.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        uusipeli.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pelipohja.uusiPeli(merkkilaji);
            }
        });

        // Seuraava määrittelysarja on osittain itseään toistava, mutta 
        // toisaalta loogisesti selkeä. Ainakin näin saa toimimaan sen, että 
        // liikennemerkkiryhmää valittaessa ei aikaisemmin valittu
        // merkkiryhmän täppi jää valikossa päälle. 
        // Nämä voinee koodata siistimminkin, vaatii jatkotyötä...
        kieltomerkit.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (merkkilaji != 1) {
                    kieltomerkit.setState(true);
                    maaraysmerkit.setState(false);
                    varoitusmerkit.setState(false);
                    merkkilaji = 1;
                    pelipohja.uusiPeli(merkkilaji);
                } else {
                    kieltomerkit.setState(true);
                }
            }
        });

        maaraysmerkit.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (merkkilaji != 2) {
                    kieltomerkit.setState(false);
                    maaraysmerkit.setState(true);
                    varoitusmerkit.setState(false);
                    merkkilaji = 2;
                    pelipohja.uusiPeli(merkkilaji);
                } else {
                    maaraysmerkit.setState(true);
                }
            }
        });

        varoitusmerkit.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (merkkilaji != 3) {
                    kieltomerkit.setState(false);
                    maaraysmerkit.setState(false);
                    varoitusmerkit.setState(true);
                    merkkilaji = 3;
                    pelipohja.uusiPeli(merkkilaji);
                } else {
                    varoitusmerkit.setState(true);
                }
            }
        });

        teeUusiIkkuna();
        ikkuna.setVisible(true);
    }

    /**
     * Piirtää pelin ikkunan uudestaan
     */
    public void teeUusiIkkuna() {
        ikkuna.pack();
        ikkuna.setSize(ikkuna.getPreferredSize());
        ikkuna.doLayout();
    }
}