package gestionRoutiers;


import java.util.ArrayList;

/*
 * Cette classe d�finit le trajet a suivre et l'ensemble de ces caract�ristiques
 */

public class Trajet implements TronconListener{  

		private ArrayList<Noeud> noeuds = new ArrayList<Noeud>();
		private ArrayList<TrajetListener> eventListeners = new ArrayList<TrajetListener>();

		// Ajout d'�couteur d'�v�nement sur le trajet
		public void addEventListener(TrajetListener listener) {
			try {
				if (listener == null)
					throw new NullPointerException("Veuillez ajouter un �v�nement");

				if (!this.eventListeners.contains(listener))
					this.eventListeners.add(listener);

			} catch (NullPointerException ex) {
				System.out.println(ex.getMessage());
			}
		}

		// Setters et Getters
		public ArrayList<Noeud> getNoeuds() {
			return noeuds;
		}

		public void setNoeuds(ArrayList<Noeud> noeuds) {
			this.noeuds = noeuds;

			if (noeuds != null) {
				for (Noeud noeud : noeuds) {
					if (this.getTronconSuivant(noeud) != null)
						this.getTronconSuivant(noeud).addEventListener(this);
				}
			}
		}

		public Troncon getTronconSuivant(Noeud noeud) {
			if (noeuds != null) {
				if (noeuds.contains(noeud)) {
					int index = noeuds.indexOf(noeud);

					if (index >= 0 & index + 1 < noeuds.size()) {
						for (Troncon troncon : noeud.getSortie()) {
							if (troncon.getDestination().equals(noeuds.get(index + 1)))
								return troncon;
						}
						return null;
					} else
						return null;
				} else
					return null;
			} else
				return null;
		}

		@Override
		public void congestionnerTroncon(TronconEvent event) {
			
			for (TrajetListener listener : eventListeners) {
				if (listener != null) {
					listener.majTrajet(new TrajetEvent(this));
				}
			}
		}

		@Override
		public void decongTroncon(TronconEvent event) {

			for (TrajetListener listener : eventListeners) {
				if (listener != null) {
					listener.majTrajet(new TrajetEvent(this));
				}
			}
		}

}
