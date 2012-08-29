package muistipeli;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Luokka jossa varsinainen pelialusta ja -tapahtumat toteutetaan. 
 * GridLayout-asettelu sopii tähän. Käytettävissä on liikennemerkkiryhmittäin 
 * kolme kuvahakemistoa mistä kuvat haetaan 
 * (kuvat_kielto, kuvat_maarays, kuvat_varoitus)
 */
public class Pelipohja extends JPanel {

    /**
     * Käytettyjen arvausten määrä
     */
    private int arvaukset = -1;
    
    /**
     * Pelin korttiparien määrä
     */
    private int korttipareja = -1;
    
    /**
     * Pelissä olevat kortit
     */
    private Kortit[] pelinkortit;
    
    /**
     * Tekstinä kuinka monta arvausta käytetty
     */
    private JLabel arvattu_txt;
    
    /**
     * Tekstinä kuinka monta paria jäljellä
     */
    private JLabel parejajaljella_txt;
    
    /**
     * Ikkuna (pelipohja)
     */
    private Muistipeli ikkuna;
    
    /**
     * Käännetyt kortit joille ei ole löytynyt paria
     */
    private Kortit[] kaannettykortti = new Kortit[2];
    
    /**
     * Valittu liikennemerkkihakemisto (-merkkilaji)
     */
    private int merkkilaji;
    
    /**
     * Ajastin joka kääntää kortit takaisin (jos ei löytynyt pareja)
     */
    private java.util.Timer ajastin = new java.util.Timer();
    
    /**
     * Totuusarvo (onko ajastin päällä)
     */
    private boolean ajastinpaalla = false;

    /**
     * Alustaa uuden pelipohjan
     *
     * @param ikkuna
     * @param merkkilaji
     */
    public Pelipohja(Muistipeli ikkuna, int merkkilaji) {
        this.ikkuna = ikkuna;
        this.merkkilaji = merkkilaji;
        uusiPeli(merkkilaji);
    }

    /**
     * Aloittaa uuden pelin (pelaajan valitsemalla merkkivalikoimalla)
     *
     * @param merkkilaji
     */
    public void uusiPeli(int merkkilaji) {
        if (ajastinpaalla) {
            ajastin.cancel();
            ajastinpaalla = false;
        }
        korttipareja = 8;
        setLayout(new GridLayout(4, 4, 5, 5));

        this.merkkilaji = merkkilaji;

        int[] arvot = uudetKortit();

        // Vanhojen korttien poisto ennen uusien asettamista:
        if (this.arvaukset != -1) {
            for (int i = 0; i < pelinkortit.length; i++) {
                remove(pelinkortit[i]);
            }
        }

        pelinkortit = new Kortit[(korttipareja * 2)];

        Pelipohja.HiiriKuuntelija a = new Pelipohja.HiiriKuuntelija();
        for (int i = 0; i < (korttipareja * 2); i++) {
            pelinkortit[i] = new Kortit(this, arvot[i], merkkilaji);
            add(pelinkortit[i]);
            pelinkortit[i].addActionListener(a);
        }
        if (arvaukset == -1) {

            // Alustetaan arvattu_txt ja parejajaljella_txt
            arvattu_txt = new JLabel("  Arvattu pareja: 0");
            arvattu_txt.setForeground(Color.black);
//                arvattu_txt.setText("  Arvaukset: 0");
            parejajaljella_txt = new JLabel("  Pareja jäljellä: " + korttipareja);
            parejajaljella_txt.setForeground(Color.black);
            this.arvaukset = 0;
        }
        arvattu_txt.setText("  Arvaukset: 0");
        parejajaljella_txt.setText("  Pareja jäljellä: " + korttipareja);
        nollaaArvaus();
        doLayout();
        ikkuna.teeUusiIkkuna();
    }

    /**
     * Hiirellä klikkausten kuuntelija (sisäluokka), TimerTask-luokan aliluokka
     */
    class HiiriKuuntelija extends java.util.TimerTask implements ActionListener {

        /**
         * Käännettyjen korttien lukumäärä
         */
        private int kaannetytkortit = 0;
        
        /**
         * Totuusarvo (edellisen yrityksen)
         */
        private boolean arvausvaarin = false;

        /**
         * Ajastimen määräämä toiminto (piilottaa käännetyt kortit näkyvistä jos
         * säädetty aikaraja tulee vastaan
         */
        @Override
        public void run() {
            kaannettykortti[0].piilotaKuva();
            kaannettykortti[1].piilotaKuva();
            arvausvaarin = false;
        }

        /**
         * Klikattujen korttien määrän seuraaminen (mikäli kaksi klikattua
         * korttia ovat samoja, kortit jäävät näkyviin)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Object o = e.getSource();
            if (o instanceof Kortit) {
                Kortit klikattukortti = (Kortit) e.getSource();

                // Jos klikataan kortteja uudestaan (ja aikaisempi arvaus
                // oli väärä), korttien kuvat käännetään piiloon
                // eli taustakuva tulee tilalle
                if (arvausvaarin) {
                    kaannettykortti[0].piilotaKuva();
                    kaannettykortti[1].piilotaKuva();
                    arvausvaarin = false;
                    if (ajastinpaalla) {
                        ajastin.cancel();
                        ajastinpaalla = false;
                    }
                }

                if (!klikattukortti.onKaannetty()) {
                    if (kaannetytkortit < 2) {
                        kaannettykortti[kaannetytkortit] = klikattukortti;
                        klikattukortti.kaannaKuvaEsiin();
                        kaannetytkortit++;
                        arvausvaarin = false;
                    }

                    if (kaannetytkortit >= 2) {
                        if (kaannettykortti[0].kortinnumero() == kaannettykortti[1].kortinnumero()) {
                            vahennaPari();
                            lisaaArvaus();
                            kaannetytkortit = 0;
                            arvausvaarin = false;
                            if (ajastinpaalla) {
                                ajastin.cancel();
                                ajastinpaalla = false;
                            }

                            if (onkoRatkaistu()) {
                                peliRatkaistu();
                            }
                        } else {
                            if (ajastinpaalla) {
                                ajastin.cancel();
                                ajastinpaalla = false;
                            }
                            lisaaArvaus();
                            arvausvaarin = true;
                            kaannetytkortit = 0;
                            ajastin = new java.util.Timer();
                            ajastin.schedule(new Pelipohja.HiiriKuuntelija(), 1000);
                            ajastinpaalla = true;
                        }
                    }
                }
            }
        }
    }

    /**
     * Arvotaan kortit
     *
     * @return Aputaulukko
     */
    private int[] uudetKortit() {

        // Kortit "kahdennetaan"
        int indeksienmaara = korttipareja * 2;
        int[] aputaulukko = new int[indeksienmaara];
        int apu;

        // Luodaan taulukko muotoa 0,0,1,1 jne.)
        for (int i = 0; i < indeksienmaara; i++) {
            aputaulukko[i] = i / 2;
        }


        // Taulukon sekoitus. Jokaiselle taulukon alkiolle arvotaan pari
        // (samasta taulukosta). Nämä vaihtavat paikkaa, arvonta 
        // toistetaan niin kauan saadaan kaikki parit ja 
        // tulos palautetaan
        for (int i = 0; i < indeksienmaara; i++) {
            apu = aputaulukko[i];
            int apuindeksi = (int) (aputaulukko.length * Math.random());
            aputaulukko[i] = aputaulukko[apuindeksi];
            aputaulukko[apuindeksi] = apu;
        }
        return aputaulukko;
    }

    /**
     * Totuusarvo (onko peli ratkaistu)
     *
     * @return korttipareja
     */
    public boolean onkoRatkaistu() {
        return (this.korttipareja == 0);
    }

    /**
     * Totuusarvo (onko siirtoja tehty)
     *
     * @return arvaukset
     */
    public boolean onkoAloitettu() {
        return (this.arvaukset != 0);
    }

    /**
     * Tarkistus (onko parit löydetty)
     */
    private void peliRatkaistu() {
        arvattu_txt.setText("  Hienoa, löysit parit!");
        parejajaljella_txt.setText("  Käytit " + arvaukset + " arvausta.");
    }

    /**
     * Vähentää löydetyn parin ilmoitustekstistä
     */
    private void vahennaPari() {
        korttipareja--;
        parejajaljella_txt.setText("  Pareja jäljellä: " + korttipareja);
    }

    /**
     * Lisää arvausten määrää
     */
    private void lisaaArvaus() {
        arvaukset++;
        arvattu_txt.setText("  Arvaukset: " + arvaukset);
    }

    /**
     * Määrittää arvausten ja korttiparien määrän alkutilanteeseen
     */
    private void nollaaArvaus() {
        arvaukset = 0;
        korttipareja = 8;
    }

    /**
     * Palauttaa pelaajan arvaukset
     *
     * @return arvausten määrä
     */
    public JLabel naytaArvaukset() {
        return arvattu_txt;
    }

    /**
     * Palauttaa jäljellä olevien parien määrän
     *
     * @return jäljellä olevien parien määärä
     */
    public JLabel naytaParit() {
        return parejajaljella_txt;
    }
}