package gestionRoutiers;


import java.util.ArrayList;

/*
 * Cette classe définit le réseau routier et l'ensemble de ces caractéristiques
 */

public class ReseauRoutier extends Exception {

	private static final long serialVersionUID = 1L;
	public static final long MAX_X = 1000;
	public static final long MAX_Y = 1000;
	public static final long MIN_X = 0;
	public static final long MIN_Y = 0;
	private String nom;
	private ArrayList<Vehicule> vehicules = new ArrayList<Vehicule>();
	
	// ces attributs public seront réutilisés dans d'autres classes
	public ArrayList<Noeud> noeuds = new ArrayList<Noeud>();   
	public ArrayList<Troncon> troncons = new ArrayList<Troncon>();
	public ArrayList<Panneau> panneaux = new ArrayList<Panneau>();

	public ReseauRoutier() {
		
	}

	public void ajouterNoeud(int abscisse, int ordonne, String nom) {
		
			Noeud noeud = new Noeud(abscisse, ordonne, nom);
			noeuds.add(noeud);
	}
	
	 public void ajouterPanneau(int abscisse, int ordonne, String nom, int tail) {

			Panneau panneau = new Panneau(abscisse, ordonne, nom, tail);
			panneaux.add(panneau);
	}

	//Méthode pour l'ajout d'un troncon
	public void ajouterTroncon(Noeud de, Noeud a, String nom, int taille) {

		Troncon troncon1 = new Troncon(de, a, nom, taille);
		Troncon troncon2 = new Troncon(a, de, nom, taille);
		
		de.ajouterTronconRef(troncon1);
		a.ajouterTronconRef(troncon1);
		
		de.ajouterTronconRef(troncon2);
		a.ajouterTronconRef(troncon2);

		troncons.add(troncon1);
		troncons.add(troncon2);
		
	}
	
	// ajout d'un véhicule
	public Vehicule ajouterVehicule(Noeud depart) {
		
		try {
			if (depart == null)
				throw new NullPointerException("Veuillez ajouter un noeud de départ");

			Vehicule nouveauVehicule = new Vehicule(this, depart);
			vehicules.add(nouveauVehicule);

			return nouveauVehicule;

		} catch (NullPointerException ex) {
			return null;
		}
	}


	public void retirerVehicle(Vehicule vehicule) {
		vehicules.remove(vehicule);
	}

	// Setters et Getters
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public ArrayList<Noeud> getNoeuds() {
		return noeuds;
	}

	

	public ArrayList<Vehicule> getVehicules() {
		return this.vehicules;
	}

}