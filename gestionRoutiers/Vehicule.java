package gestionRoutiers;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import interfaceApplication.VehiculeListener;

/*
 * La classe Vehicule nous permet de d�finir les carct�ristiques
 * d'un vehicule
 */
public class Vehicule implements TrajetListener, TronconListener {
		
		private int abscisse;
		private int ordonne;

		private Timer timer = new Timer();
		private TimerTask task;
		private Noeud depart;
		private Noeud destination;
		private Troncon tronconActuel;

		private ReseauRoutier reseauRout;
		private ArrayList<VehiculeListener> eventListeners = new ArrayList<VehiculeListener>();
		private Trajet trajet = new Trajet();

		/* Constructeurs */
		
		public Vehicule(ReseauRoutier reseauRout, Noeud depart) {
			this.setReseauRoutier(reseauRout);
			this.setDepart(depart);

			this.setAbscisse(depart.getAbscisse());
			this.setOrdonne(depart.getOrdonne());
			this.getChemin().addEventListener(this);
		}

		public Vehicule(ReseauRoutier reseauRout, Noeud depart, Noeud destination) {
			this.setReseauRoutier(reseauRout);
			this.setDepart(depart);
			this.setDestination(destination);

			this.setAbscisse(depart.getAbscisse());
			this.setOrdonne(depart.getOrdonne());

			this.getChemin().setNoeuds(AlgoDijkstra.trouverDistanceMin(reseauRout, this.getDepart(), this.getDestination()));
			this.getChemin().addEventListener(this);
			this.setTronconActuel(this.getChemin().getTronconSuivant(this.getDepart()));
		}

		public void addEventListener(VehiculeListener listener) {
			
			if (!this.eventListeners.contains(listener))
				this.eventListeners.add(listener);

		}

		public void removeEventListener(VehiculeListener listener) {
			this.eventListeners.remove(listener);
		}
	
		//permet le d�placement du v�hicule
		public void seDeplacer() {

			Vehicule vehicule = this;
			timer = new Timer();
			task = new TimerTask(){
				
				//@Override
				public void run() {
					if (tronconActuel != null) {
						if (tronconActuel.getEtat() != EtatTroncon.Bloque) {
							switch (tronconActuel.getDirection()) {
							case Haut:
								if (vehicule.getOrdonne() > tronconActuel.getDestination().getOrdonne()) {
									vehicule.setOrdonne(vehicule.ordonne - 1);
								}
								break;

							case Droite:
								if (vehicule.getAbscisse() < tronconActuel.getDestination().getAbscisse()) {
									vehicule.setAbscisse(vehicule.abscisse + 1);
								}
								break;
							case Bas:
								if (vehicule.getOrdonne() < tronconActuel.getDestination().getOrdonne()) {
									vehicule.setOrdonne(vehicule.ordonne + 1);
								}
								break;
							case Gauche:
								if (vehicule.getAbscisse() > tronconActuel.getDestination().getAbscisse()) {
									vehicule.setAbscisse(vehicule.abscisse - 1);
								}
								break;
							}

							if (vehicule.getAbscisse() == tronconActuel.getDestination().getAbscisse()
									& vehicule.getOrdonne() == tronconActuel.getDestination().getOrdonne()) {
								Troncon tronconSuivant = vehicule.getChemin().getTronconSuivant(tronconActuel.getDestination());
								if (tronconSuivant != null)
									setTronconActuel(tronconSuivant);

							}
						}
						 else
						 arret();
					}
					 else
					 arret();
				}
			};
			timer.scheduleAtFixedRate(task, 0, 25); //temps mis entre deux d�placement
		}

		// Stop le v�hilcule
		public void arret() {
			timer.cancel();
			
			for (VehiculeListener listener : eventListeners) {
				if (listener != null)
					listener.arretVoiture(new VehiculeEvent(this));
			}
		}

		//Setters et Getters
		public int getAbscisse() {
			return abscisse;
		}

		private void setAbscisse(int abscisse) {
			if (abscisse != this.abscisse) {
				this.abscisse = abscisse;

				for (VehiculeListener listener : eventListeners) {
					if (listener != null)
						listener.deplacerVoiture(new VehiculeEvent(this));
				}
			}
		}

		public int getOrdonne() {
			return ordonne;
		}

		private void setOrdonne(int ordonne) {
			if (ordonne != this.ordonne) {
				this.ordonne = ordonne;

				for (VehiculeListener listener : eventListeners) {
					if (listener != null)
						listener.deplacerVoiture(new VehiculeEvent(this));
				}
			}
		}

		public Noeud getDestination() {
			return destination;
		}

		public void setDestination(Noeud destination) {
			
			this.destination = destination;

			if (this.getTronconActuel() == null) {
				this.getChemin().setNoeuds(AlgoDijkstra.trouverDistanceMin(reseauRout, this.getDepart(), this.getDestination()));
				this.setTronconActuel(this.getChemin().getTronconSuivant(this.getDepart()));
			} else {
				this.getChemin().setNoeuds(
						AlgoDijkstra.trouverDistanceMin(reseauRout, this.getTronconActuel().getDestination(), this.getDestination()));
			}

			for (VehiculeListener listener : eventListeners) {
				if (listener != null)
					listener.majTrajetSuivi(new VehiculeEvent(this));
			}
		}

		public Noeud getDepart() {
			return depart;
		}

		public void setDepart(Noeud depart) {
			this.depart = depart;
		}

		private ReseauRoutier getReseauRoutier() {
			return reseauRout;
		}

		private void setReseauRoutier(ReseauRoutier reseauRout) {
			this.reseauRout = reseauRout;
		}

		public Trajet getChemin() {
			return trajet;
		}

		public Troncon getTronconActuel() {
			return tronconActuel;
		}

		

		private void setTronconActuel(Troncon tronconActuel) {
			if(tronconActuel!=null){
				if(this.tronconActuel!=null)
					this.tronconActuel.removeEventListener(this);
				
				this.tronconActuel = tronconActuel;		
				this.tronconActuel.addEventListener(this);
			}
		}

		//@Override
		// Met � jour le trajet
		public void majTrajet(TrajetEvent event) {

			if (this.getTronconActuel() == null) {
				this.getChemin().setNoeuds(AlgoDijkstra.trouverDistanceMin(reseauRout, this.getDepart(), this.getDestination()));
				this.setTronconActuel(this.getChemin().getTronconSuivant(this.getDepart()));
			} else {
				this.getChemin().setNoeuds(
						AlgoDijkstra.trouverDistanceMin(getReseauRoutier(), this.getTronconActuel().getDestination(), this.getDestination()));
			}

			for (VehiculeListener listener : eventListeners) {
				if (listener != null)
					listener.majTrajetSuivi(new VehiculeEvent(this));
			}	
		}
		
		@Override
		public void congestionnerTroncon(TronconEvent event) {
			arret();
		}

		@Override
		public void decongTroncon(TronconEvent event) {
			seDeplacer();
			
		}
}
	
