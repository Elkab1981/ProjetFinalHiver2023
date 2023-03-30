package gestionRoutiers;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/*
 * Cette classe d�finit un noeud et l'ensemble de ces caract�ristiques
 */
public class Noeud {
 
		private int abscisse;
		private int ordonne;
		private Timer timer;
		private TimerTask task;

		private int index;
		private String nom; 
		private ArrayList<Troncon> troncons = new ArrayList<Troncon>();
		private ArrayList<NoeudListener> eventListeners = new ArrayList<NoeudListener>();
		private boolean estBloque = false;
		
		public Noeud(String nom) {
			this.setNom(nom);
		}
		public Noeud(int abscisse, int ordonne, String nom) {
			this.setAbscisse(abscisse);
			this.setOrdonne(ordonne);
			this.setNom(nom);
		}

		// Ajout d'�v�nement
		public void addEventListener(NoeudListener listener) {
			try {

				if (listener == null)
					throw new NullPointerException("Veuillez ajouter un �v�nement");

				if (!this.eventListeners.contains(listener))
					this.eventListeners.add(listener);

			} catch (NullPointerException ex) {
				System.out.println(ex.getMessage());
			}
		}

		public void ajouterTronconRef(Troncon troncon) {
			troncons.add(troncon);
		}

		public void retirerTronconRef(Troncon troncon) {
			troncons.remove(troncon);
		}

		public ArrayList<Troncon> getTroncons() {
			return troncons;
		}

		public ArrayList<Troncon> getSortie() {

			ArrayList<Troncon> returnValue = new ArrayList<Troncon>();
			for (Troncon item : troncons) {
				if (item.getDepart().equals(this)) {
					returnValue.add(item);
				}
			}
			return returnValue;
		}

		// Ajout accident
		public void ajouterAccident(long duration) {
			estBloque = true;
			Noeud noeud = this;

			timer = new Timer();

			task = new TimerTask() {
				@Override
				public void run() {
					noeud.retirerAccident();
				}
			};
			timer.schedule(task, duration);

			for (NoeudListener listener : eventListeners) {
				if (listener != null)
					listener.congestionnerNoeud(new NoeudEvent(this));
			}
		}

		// Retrait accident
		public void retirerAccident() {
			estBloque = false;
			timer.cancel();
			task.cancel();
			timer = null;
			for (NoeudListener listener : eventListeners) {
				if (listener != null)
					listener.decongNoeud(new NoeudEvent(this));
			}
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

		public boolean getEstBloque() {
			return estBloque;
		}

		public int getIndex() {
			return index;
		}

		protected void setIndex(int index) {
			this.index = index;
		}

		public String getNom() {
			return nom;
		}

		private void setNom(String nom) {
			this.nom = nom;
		}

		public String toString() {
			return nom;
		}
	
}
