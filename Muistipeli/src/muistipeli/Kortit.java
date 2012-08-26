package muistipeli;


import java.awt.Insets;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;


/**
 * 
 * @author jarih
 */
public class Kortit extends JButton {
    


	/** Kortin numero (tunnusnumero, jolla kortille saadaan haettua pari)
         * @param kortinnumero 
         */
	private int kortinnumero;

	/** Pelipohja korttien sijoittelua varten */
	private Pelipohja pelipohja;

	/** Totuusarvo, apumuuttuja (ilmoittaa onko kortti käännetty) */
	private boolean onkaannetty;

	/** Kortissa oleva kuva (näkyy kun kortti on käännetty) */
	private ImageIcon kortinkuva;

	/** Kortin takapuolella oleva kuva (sama kaikissa korteissa) */
	private ImageIcon kortintaustakuva;

        /** Luo pelipohjaan uuden kortin
         * @param pelipohja
         * @param kortinnumero  
         */
	public Kortit(Pelipohja pelipohja, int kortinnumero) {
            this.kortinnumero = kortinnumero;
            this.pelipohja = pelipohja;
            this.onkaannetty = false;
            File g = new File("kuvat/kortintausta.jpg");
            if (g.exists()) {
                kortintaustakuva = new ImageIcon(g.getPath());
            } else kortintaustakuva = null;
            File f = new File("kuvat/"+(kortinnumero+1)+".jpg");
            if (f.exists()) {
                kortinkuva = new ImageIcon(f.getPath());
                setMargin(new Insets(0,0,0,0));
                setBorderPainted(false);
                setIcon(kortintaustakuva);
            }
        }
        
        /** Palauttaa kortin tunnusnumeron
         * @return kortin tunnusnumero
         */
        public int kortinnumero() {
            return this.kortinnumero;
        }

        /** Palauttaa pelipohjan
         * @return pelipohja
         */
	public Pelipohja pelipohja() {
            return pelipohja;
	}

        /** Palauttaa totuusarvon (onko kortti käännetty vai ei)
         * @return onko käännetty
         */
	public boolean onKaannetty() {
            return this.onkaannetty;
	}

	/** Kääntää kortin esille */
	public void kaannaKuvaEsiin() {
            onkaannetty=true;
            setIcon(kortinkuva);
	}

	/** Kääntää kortin jälleen nurinpäin */
	public void piilotaKuva() {
            onkaannetty=false;
            setIcon(kortintaustakuva);
	}

}