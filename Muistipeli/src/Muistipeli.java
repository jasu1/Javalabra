
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Muistipeli {

	/** Pelin pohja */
	private Pelipohja pelipohja;


	/** Ikkuna johon peli muodostetaan */
	private JFrame ikkuna;


	/** Luo uuden muistipelin */
        private Muistipeli() {
		ikkuna =  new JFrame("Muistipeli"); 
                pelipohja = new Pelipohja(this);

		JPanel tekstit = new JPanel();
		tekstit.setLayout(new GridLayout(1,2));
		tekstit.add(pelipohja.naytaArvaukset());
		tekstit.add(pelipohja.naytaParit());
                //testiä taustavärin vaihtamiseksi
//                ikkuna.getContentPane().setBackground(Color.red);

		ikkuna.getContentPane().add(tekstit,BorderLayout.NORTH);
		ikkuna.getContentPane().add(pelipohja,BorderLayout.CENTER);
                
             //...taas testiä taustavärin vaihtamiseksi
             //värin vaihtaminen ei näköjään nyt onnistu tässä,
             //joten täytyy kokeilla eri vaihtoehtoja, esim. JLabeliin 
             //pitää kokeilla seuraavaksi läpinäkyvyyttä toisen luokan sisällä
             //allaoleva on säilössä pelkästään tekstinä, peli sinänsä toimii
//                ikkuna.setFocusable(true);// INSERT THIS
//                ikkuna.getContentPane().setBackground(Color.yellow);
                // jatkuu...

		ikkuna.addWindowListener(new WindowAdapter() {
            @Override
			public void windowClosing(WindowEvent e) {
				System.exit(0); 
			}
		}); // ) ja ;, koska tarkistus ilmoittaa puuttuvan (pohdittava miksi?)
                    // Onkohan tämä tehtävissä toisin?

		// Määritellään valikon osat
		MenuItem uusipeli = new MenuItem("Uusi peli", new MenuShortcut(KeyEvent.VK_N));
		MenuItem lopetus = new MenuItem("Lopeta", new MenuShortcut(KeyEvent.VK_Q));
		Menu pelivalikko = new Menu("Valikko");


		// Lisätään valikon osat
		pelivalikko.add(uusipeli);
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
                                pelipohja.uusiPeli();
			}
		});

		teeUusiIkkuna();
		ikkuna.setVisible(true);
	}


	/** Piirtää ikkunan uudestaan */
	public void teeUusiIkkuna() {
		ikkuna.pack();
		ikkuna.setSize(ikkuna.getPreferredSize());
//                ikkuna.getContentPane().setBackground(Color.red);
		ikkuna.doLayout();
	}

	/** Päämetodi joka käynnistää uuden pelin */
	public static void main(String args[]) {	
		Muistipeli peli = new Muistipeli();
	}
}