package interfaceApplication;

import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import gestionRoutiers.Troncon;

import gestionRoutiers.VehiculeEvent;
import gestionRoutiers.ReseauRoutier;
import gestionRoutiers.Noeud;
import gestionRoutiers.Trajet;
import gestionRoutiers.Panneau;

/**
*  Classe d�finissant l'interface graphique du reseau routier               
*/

public class IGReseauRoutier extends JPanel implements VehiculeListener {  

	/*   les attributs   */
	
	private static final long serialVersionUID = 1L;
	private ArrayList<IGPanneau> igPanneaux = new ArrayList<IGPanneau>();
	private ReseauRoutier reseauRout;
	private ArrayList<IGNoeud> igNoeuds = new ArrayList<IGNoeud>();
	private ArrayList<IGTroncon> igTroncons = new ArrayList<IGTroncon>();
	
	private IGVehicule vehiculeChoisi;
	
	/*   Constructeur   */
	public IGReseauRoutier(ReseauRoutier reseauRout) {
		this.setReseauRoutier(reseauRout);
		
	}

	
	/* 
	*
	* Cette methode permet d'afficher le chemin � parcourir  */

	public void afficherChemin() {
		if(vehiculeChoisi!=null){
			Trajet trajet = vehiculeChoisi.getVehicule().getChemin();		 
				
			if(trajet!=null){
				if (trajet.getNoeuds() != null) {
					for (Noeud noeud : trajet.getNoeuds()) {
						IGNoeud igNoeud = this.getIGNoeud(noeud);
						if (igNoeud.getNoeud().equals(trajet.getNoeuds().get(0)))  // si noeud est Noeud de d�part
							igNoeud.setBackground(new Color(12, 145, 0));
		
						if (igNoeud.getNoeud().equals(trajet.getNoeuds().get(trajet.getNoeuds().size() - 1)))
							igNoeud.setBackground(new Color(12, 145, 0));  //si noeud est noeud d'arriv�� Noeud d'arriv�e
						else
							igNoeud.setBackground(new Color(12, 145, 254));
		
						Troncon troncon = trajet.getTronconSuivant(noeud);
						if (troncon != null) {
							// D�finir couleur de troncon
							this.getIGTroncon(troncon).setBackground(new Color(12, 145, 254));
						}
					}
				}
			}
			this.getIGNoeud(vehiculeChoisi.getVehicule().getDepart()).setBackground(new Color(12, 145, 0));
		} 
	}

	/* 
	*
	* RED�FINIR LES COULEURS DU TRONCON  */

	public void updateColors() {
		for (IGTroncon igTroncon : igTroncons) {
			igTroncon.updateColor();
		}

		for (IGNoeud igNoeud : igNoeuds) {
			igNoeud.updateColor();
			
		}
	}
	
	/* 
	*
	* Cette methode permet de construire le r�seau routier  */

	private void construireReseau() {
		
		
		for (Troncon troncon : this.getReseauRoutier().troncons) {
			IGTroncon igTroncon = new IGTroncon(troncon);
			troncon.addEventListener(igTroncon);
			igTroncons.add(igTroncon);
			this.add(igTroncon);
		}

		for (Noeud noeud : this.getReseauRoutier().noeuds) {
			IGNoeud igNoeud = new IGNoeud(noeud);
			noeud.addEventListener(igNoeud);

			igNoeuds.add(igNoeud);
			this.add(igNoeud);
		}
		
		for (Panneau pan : this.getReseauRoutier().panneaux) {
			IGPanneau igPan = new IGPanneau(pan);

			igPanneaux.add(igPan);
			this.add(igPan);
		}
	}

	public ReseauRoutier getReseauRoutier() {
		return reseauRout;
	}

	/*  Les accesseurs   */
	
	private void setReseauRoutier(ReseauRoutier reseauRout) {
		this.reseauRout = reseauRout;
		for (Component comp : this.getComponents()) {
			this.remove(comp);
		}
		construireReseau();
	}

	private IGNoeud getIGNoeud(Noeud noeud) {
		for (IGNoeud igNoeud : igNoeuds) {
			if (igNoeud.getNoeud().equals(noeud))
				return igNoeud;
		}
		return null;
	}

	private IGTroncon getIGTroncon(Troncon troncon) {
		for (IGTroncon igTroncon : igTroncons) {
			if (igTroncon.getTroncon().equals(troncon))
				return igTroncon;
		}
		return null;
	}

	public ArrayList<IGNoeud> getIGNoeuds() {
		return igNoeuds;
	}

	public ArrayList<IGTroncon> getIGTroncons() {
		return igTroncons;
	}
	
	public ArrayList<IGPanneau> getIGPanneaux() {
		return igPanneaux;
	}

	public IGVehicule getVehiculeChoisi() {
		return vehiculeChoisi;
	}

	public void setVehiculeChoisi(IGVehicule vehiculeChoisi) {
		try {
			BufferedImage image;
			if (this.vehiculeChoisi != null) {
				//Autres véhicule
				image = ImageIO.read(new File("images/vehicule_rouge.JPG"));
				this.vehiculeChoisi.setIcon(new ImageIcon(image));
			}
			this.vehiculeChoisi = vehiculeChoisi;

			// mon véhicule
			image = ImageIO.read(new File("images/vehicule_jaune.JPG"));
			this.vehiculeChoisi.setIcon(new ImageIcon(image));
			vehiculeChoisi.getVehicule().addEventListener(this);
		} catch (IOException e) {
	
			e.printStackTrace();
		}

	}

	@Override
	public void deplacerVoiture(VehiculeEvent event) {

	}

	@Override
	public void arretVoiture(VehiculeEvent event) {

	}

	@Override
	/*  Mije � jour du chemein en fonction de congestion     */
	public void majTrajetSuivi(VehiculeEvent event) {
		if (event.getVehicule().equals(vehiculeChoisi.getVehicule())) {

			this.updateColors();
        }
	}
	
}