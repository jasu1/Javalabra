package muistipeli;

import java.awt.Insets;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Luokka, jossa korttien hakeminen oikeasta hakemistosta määritellään (lisäksi
 * muut suoranaisesti kortteihin liittyvät tapahtumat).
 *
 * @author Jari Haapala
 */
public class Kortit extends JButton {

    /**
     * Kortin numero (tunnusnumero, jolla kortille saadaan haettua pari)
     */
    private int kortinnumero;
    
    /**
     * Pelipohja korttien sijoittelua varten
     */
    private Pelipohja pelipohja;
    
    /**
     * Totuusarvo, apumuuttuja (ilmoittaa onko kortti käännetty)
     */
    private boolean onkaannetty;
    
    /**
     * Kortissa oleva kuva (näkyy kun kortti on käännetty)
     */
    private ImageIcon kortinkuva;
    
    /**
     * Kortin takapuolella oleva kuva (sama hakemisto ja kuva kaikissa korteissa)
     */
    private ImageIcon kortintaustakuva;

    /**
     * Luo pelipohjaan uudet kortit
     *
     * @param pelipohja
     * @param kortinnumero
     * @param merkkilaji
     */
    public Kortit(Pelipohja pelipohja, int kortinnumero, int merkkilaji) {
        this.kortinnumero = kortinnumero;
        this.pelipohja = pelipohja;
        this.onkaannetty = false;

//        File g = new File("kuvat/kortintausta.jpg");

//        kortintaustakuva = new ImageIcon(g.getPath());
        
        // Merkkilajin valintakoodia voisi varmaan siistiä niin, että
        // yhdistäisi yhteisiä osia (taas turhaa toistoa koodissa). 
        // Jäi nyt tekemättä, mutta toimii kuitenkin hyvin.
        // Jatkokehittelyn/miettimisen paikka.
        if (merkkilaji == 2) {
            File g = new File("kuvat/kuvat_maarays/kortintausta.jpg");
            kortintaustakuva = new ImageIcon(g.getPath());
            File f = new File("kuvat/kuvat_maarays/" + (kortinnumero + 1) + ".jpg");
            kortinkuva = new ImageIcon(f.getPath());
            setMargin(new Insets(0, 0, 0, 0));
            setBorderPainted(false);
            setIcon(kortintaustakuva);

        } else {
            if (merkkilaji == 3) {
                File g = new File("kuvat/kuvat_varoitus/kortintausta.jpg");
                kortintaustakuva = new ImageIcon(g.getPath());
                File f = new File("kuvat/kuvat_varoitus/" + (kortinnumero + 1) + ".jpg");
                kortinkuva = new ImageIcon(f.getPath());
                setMargin(new Insets(0, 0, 0, 0));
                setBorderPainted(false);
                setIcon(kortintaustakuva);
                

            } else {
                File g = new File("kuvat/kuvat_kielto/kortintausta.jpg");
                kortintaustakuva = new ImageIcon(g.getPath());
                File f = new File("kuvat/kuvat_kielto/" + (kortinnumero + 1) + ".jpg");
                kortinkuva = new ImageIcon(f.getPath());
                setMargin(new Insets(0, 0, 0, 0));
                setBorderPainted(false);
                setIcon(kortintaustakuva);
                                   
                }
            }
        }

    /**
     * Palauttaa kortin tunnusnumeron
     *
     * @return kortin tunnusnumero
     */
    public int kortinnumero() {
        return this.kortinnumero;
    }

    /**
     * Palauttaa pelipohjan
     *
     * @return pelipohja
     */
    public Pelipohja pelipohja() {
        return pelipohja;
    }

    /**
     * Palauttaa totuusarvon (onko kortti käännetty vai ei)
     *
     * @return onko käännetty
     */
    public boolean onKaannetty() {
        return this.onkaannetty;
    }

    /**
     * Kääntää klikatun kortin näkyviin
     */
    public void kaannaKuvaEsiin() {
        onkaannetty = true;
        setIcon(kortinkuva);
    }

    /**
     * Kääntää kortin jälleen nurinpäin (jos arvaus ei onnistunut)
     */
    public void piilotaKuva() {
        onkaannetty = false;
        setIcon(kortintaustakuva);
    }
}
