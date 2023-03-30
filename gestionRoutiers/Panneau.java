package gestionRoutiers;


/*
 * Cette classe d�finit un panneau et l'ensemble de ces caract�ristiques
 */
public class Panneau {

	private int abscisse;
	private int ordonne;
	private int tail;
	private String nom; 
	
	public Panneau (int abscisse, int ordonne, String nom, int tp) {
		this.setAbscisse(abscisse);
		this.setOrdonne(ordonne);
		this.setNom(nom);
		tail = tp;
		
	}
	
	// Setters et Getters
	public int getAbscisse() {
		return abscisse;
	}

	public void setAbscisse(int abscisse) {
		this.abscisse = abscisse;
	}

	public int getOrdonne() {
		return ordonne;
	}

	public void setOrdonne(int ordonne) {
		this.ordonne = ordonne;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getTail() {
		return tail;
	}
	
}
