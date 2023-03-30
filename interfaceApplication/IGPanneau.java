package interfaceApplication;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import gestionRoutiers.Panneau;


/**
 *  Definition de l'interface graphique des panneaux
 */
public class IGPanneau extends JPanel implements  ActionListener {

	/* Les attributs */
	
	private static final long serialVersionUID = 1L;
	private Panneau panneau;
    
	public static final int WIDTH = 15;
	public static final int HEIGHT = 15;
	
	
	/*   Constructeur   */
	public IGPanneau(Panneau pan) {
		
		this.setPanneau(pan);
		this.setBounds(pan.getAbscisse(), pan.getOrdonne(), WIDTH, HEIGHT);
		int tail = pan.getTail();

		if (tail >= 80) {                           //En fonction du poids, le troncon est bloque ou non
			this.setBackground(Color.RED);         // troncon bloqu�, panneau rouge
		}else {
			this.setBackground(Color.GREEN);      //troncon libre, panneau vert
		}
	
		

	}
	
	/*  Les accesseurs  */
	
	public Panneau getPanneau() {
		return panneau;
	}

	private void setPanneau(Panneau panneau) {
		this.panneau = panneau;
	}

	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

