package gestionRoutiers;


import java.util.ArrayList;

/*
 * Cette classe d�finit un troncon et l'ensemble de ces caract�ristiques
 */

public class Troncon implements NoeudListener {
	
		private String nom;
		
		private Noeud depart = null;
		private Noeud destination = null;
		private Direction direction = null;
		private SensTroncon orientation = null;
		private EtatTroncon etat = EtatTroncon.Fluide;
		
		private int taille;
		public static final int TAILLE_MIN = 0;
		public static final int TAILLE_MAX = 100;
		private ArrayList<TronconListener> eventListeners = new ArrayList<TronconListener>();

		public Troncon(Noeud de, Noeud a, String nom, int taille) {
			initValeurs(de, a, nom, taille);
		}
		
	    // Initialisation des valeurs
		private void initValeurs(Noeud de, Noeud a, String nom, int taille) {
			
			this.setDepart(de);
			this.setDestination(a);
			this.setNom(nom);
			this.setTaille(taille);
			
			this.setDirection(Troncon.getDirection(this));
			this.setOrientation(Troncon.getOrientation(this));
			a.addEventListener(this);
		}

		// D�termine la longueur d'un troncon
		public int longueurTroncon(){

			int returnValue = 0;

			int dx = Math.abs(this.getDepart().getAbscisse() - this.getDestination().getAbscisse());
			int dy = Math.abs(this.getDepart().getOrdonne() - this.getDestination().getOrdonne());
		    returnValue = Math.abs(dx - dy);
			
			return returnValue;		
		}		
		
		// Ajout d'�couteurs sur un troncon
		public void addEventListener(TronconListener listener) {
			try {
				if (listener == null)
					throw new NullPointerException("Veuillez ajouter un �v�nement");

				if (!this.eventListeners.contains(listener))
					this.eventListeners.add(listener);

			} catch (NullPointerException ex) {
				System.out.println(ex.getMessage());
			}
		}

		// Retrait d'�couteurs sur un troncon
		public void removeEventListener(TronconListener listener) {
			eventListeners.remove(listener);
		}

		@Override
		public void congestionnerNoeud(NoeudEvent event) {
			 
			if (event.getNoeud().equals(this.getDestination())) {
				this.setEtat(EtatTroncon.Bloque);

				for (TronconListener item : eventListeners) {
					if (item != null)
						item.congestionnerTroncon(new TronconEvent(this));
				}
			}
		}

		@Override
		public void decongNoeud(NoeudEvent event) {
	
			if (event.getNoeud().equals(destination)) {
				this.setEtat(EtatTroncon.Fluide);
				
				for (TronconListener item : eventListeners) {
					if (item != null)
						item.decongTroncon(new TronconEvent(this));
				}
			}
		}

		// Setters et Getters	
		public void setTaille(int taille) {
			this.taille = taille;
		}
		
		public int getTaille() {
			return taille;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
				
		}

		public Noeud getDepart() {
			return depart;
		}

		private void setDepart(Noeud noeud) {
			try {

				if (noeud == null)
					throw new NullPointerException("Veuillez choisir un noeud de depart");

				this.depart = noeud;
			} catch (NullPointerException ex) {
				System.out.println(ex.getMessage());
			}
		}

		public Noeud getDestination() {
			return destination;
		}

		private void setDestination(Noeud noeud) {
			try {

				if (noeud == null)
					throw new NullPointerException("Veuillez choisir un noeud d'arriv�e");

				this.destination = noeud;
			} catch (NullPointerException ex) {
				System.out.println(ex.getMessage());
			}
		}

		public EtatTroncon getEtat() {
			return etat;
		}

		protected void setEtat(EtatTroncon etat) {
			this.etat = etat;
		}

		public static Direction getDirection(Troncon troncon) {
			try {
				if (troncon.getDepart().getAbscisse() == troncon.getDestination().getAbscisse()) {
					if (troncon.getDepart().getOrdonne() < troncon.getDestination().getOrdonne()) {
						return Direction.Bas;

					} else {
						return Direction.Haut;
					}
				} else {
					if (troncon.getDepart().getAbscisse() > troncon.getDestination().getAbscisse()) {

						return Direction.Gauche;
					} else {
						return Direction.Droite;
					}
				}
			} catch (NullPointerException ex) {
				return null;
			}
		}
		
		public static SensTroncon getOrientation(Troncon troncon) {
			try {
				if (troncon.getDepart().getAbscisse() == troncon.getDestination().getAbscisse()) {
					return SensTroncon.Vertical;
				} else {
					return SensTroncon.Horizontal;
				}
			} catch (NullPointerException ex) {
				return null;
			}
		}
		
		public Direction getDirection() {
			return direction;
		}

		private void setDirection(Direction direction) {
			this.direction = direction;
		}

		public SensTroncon getOrientation() {
			return orientation;
		}

		private void setOrientation(SensTroncon orientation) {
			this.orientation = orientation;
		}
	}

