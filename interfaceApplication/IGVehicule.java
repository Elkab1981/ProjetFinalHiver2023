package interfaceApplication;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import gestionRoutiers.Troncon;
import gestionRoutiers.Direction;
import gestionRoutiers.SensTroncon;
import gestionRoutiers.Vehicule;
import gestionRoutiers.VehiculeEvent;

/**
*  Classe d�finissant l'interface graphique des vehicule � circuler                
*/
public class IGVehicule extends JButton implements VehiculeListener {

	/*  Les attributs  */
	private static final long serialVersionUID = 1L;
	private Vehicule vehicule = null;
	private BufferedImage image = null;

	/*Le constructeur*/
	
	public IGVehicule(Vehicule vehicule) {
		try {
			this.setVehicule(vehicule);
			this.vehicule = vehicule;
			this.setBounds(vehicule.getAbscisse(), vehicule.getOrdonne(), 20, 20);

			image = ImageIO.read(new File("images/vehicule_rouge.png"));
			this.setIcon(new ImageIcon(image));

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	
	/*Les evenement sur les voiture */
	@Override
	public void deplacerVoiture(VehiculeEvent e) {
		
		Vehicule vehicule = e.getVehicule();
		if(vehicule!=null) {
			Troncon troncon = vehicule.getTronconActuel();
			
	
			int dx = 25;
			int dy = 25;
	
			int lg = dx;
			int ht = dy;
			
			// Orientation du véhicule
			if (troncon.getOrientation() == SensTroncon.Vertical) {
				ht = Math.abs(troncon.getDepart().getOrdonne() - troncon.getDestination().getOrdonne()) - IGNoeud.HEIGHT;
	
				if (troncon.getDirection() == Direction.Bas) {
					dx = 0;
				}
				this.setLocation(vehicule.getAbscisse() + dx, vehicule.getOrdonne() + dy);
			} else {
				lg = Math.abs(troncon.getDepart().getAbscisse() - troncon.getDestination().getAbscisse()) - IGNoeud.WIDTH;
	
				if (troncon.getDirection() == Direction.Gauche) {
					dy = 0;
				}
				this.setLocation(vehicule.getAbscisse() + dx , vehicule.getOrdonne()+ dy);
			}
			
		}
	}

	@Override
	public void arretVoiture(VehiculeEvent e) {
	
	}

	@Override
	public void majTrajetSuivi(VehiculeEvent e) {
	
		if (e.getVehicule().equals(this.getVehicule())) {

		}

	}

	
	/*  Les accesseurs  */
	
	public Vehicule getVehicule() {
		return vehicule;
	}

	private void setVehicule(Vehicule vehicule) {
		
			this.vehicule = vehicule;
			this.vehicule.addEventListener(this);
		
	}

}


