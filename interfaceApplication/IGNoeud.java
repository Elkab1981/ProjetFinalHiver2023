package interfaceApplication;

import java.awt.Color;

import javax.swing.*;
import gestionRoutiers.Noeud;
import gestionRoutiers.NoeudEvent;
import gestionRoutiers.NoeudListener;

/**
*  Classe d�finissant l'intervace graphique des noeuds               
*/

public class IGNoeud extends JButton implements NoeudListener {
		
	/*        D�claration des attributs   */
	
	private Noeud noeud;

	public static final int WIDTH = 50;
	public static final int HEIGHT = 50;

	
	/*       Constructeur      */
	
	public IGNoeud(Noeud noeud) {
		this.setNoeud(noeud);
		this.setText(noeud.getNom());
		this.setBounds(noeud.getAbscisse(), noeud.getOrdonne(), WIDTH, HEIGHT);

			updateColor();
		}

	@Override
	public void congestionnerNoeud(NoeudEvent event) {
		updateColor();
	}

	@Override
	public void decongNoeud(NoeudEvent event) {
		updateColor();
	}

	/*  Les accesseurs    */
	
	public Noeud getNoeud() {
		return noeud;
	}

	
	/* Le setter     */
	
	private void setNoeud(Noeud noeud) {
		this.noeud = noeud;
	}

	
	/*Mise � jour de couleur*/
	
	public void updateColor() {
		if (noeud != null) {
			if (noeud.getEstBloque())
				this.setBackground(Color.PINK);
			else
				this.setBackground(Color.GRAY);

		}
	}
}
