package gestionRoutiers;


import java.util.ArrayList;
import java.util.Arrays;

	/*
	 * La classe Algodijkstra nous permettant de d�terminer le plus court chemin
	 */
public class AlgoDijkstra {
	
	public static ArrayList<Noeud> trouverDistanceMin(ReseauRoutier reseauRout, Noeud source, Noeud destination) {
		
		Noeud[] noeuds = reseauRout.noeuds.toArray(new Noeud[0]);
		Troncon[] tabTroncon = reseauRout.troncons.toArray(new Troncon[0]);

		Noeud[] tabNoeud = new Noeud[noeuds.length];
		int cpt = 0;
		source.setIndex(cpt);
		
		tabNoeud[cpt] = source;
		for (Noeud noeud : noeuds) {
			if (!noeud.equals(source)) {
				cpt = cpt + 1;
				noeud.setIndex(cpt);
				tabNoeud[cpt] = noeud;
			}
		}

		/* Parcourt l'ensemble des neouds, si la taille n'est pas le Max, alors 
		*  prend le noeud pr�c�dent comme noeud de d�part
		*/
		int[][] taille = initTaille(tabNoeud, tabTroncon);
		int[] tail = new int[tabNoeud.length];
		Noeud[] neoud = new Noeud[tabNoeud.length];
		ArrayList<Noeud> tab = new ArrayList<Noeud>();
		
		for (int i = 0; i < tabNoeud.length; i++) {
			tab.add(tabNoeud[i]);
			tail[i] = taille[0][i];
			if (tail[i] != Integer.MAX_VALUE) {
				neoud[i] = tabNoeud[0];
			}
		}

		for (int i = 1; i < tabNoeud.length; i++) {
			int val = Integer.MAX_VALUE;
			Noeud noeu = tabNoeud[0];

			for (Noeud j : tab) {
				if (tail[j.getIndex()] < val) {
					noeu = j;
					val = tail[j.getIndex()];
				}
			}
			tab.remove(noeu);

			for (int j = 1; j < tabNoeud.length; j++) {

				if (tail[noeu.getIndex()] != Integer.MAX_VALUE && taille[noeu.getIndex()][j] != Integer.MAX_VALUE
						&& tail[noeu.getIndex()] + taille[noeu.getIndex()][j] < tail[j]) {
					tail[j] = tail[noeu.getIndex()] + taille[noeu.getIndex()][j];
					neoud[j] = noeu;
				}
			}
		}

		tab.clear();
		int valIndex = destination.getIndex();
		tab.add(destination);
		
		while (neoud[valIndex] != tabNoeud[0]) {
			if (neoud[valIndex] == null) {
				return null;
			}

			tab.add(0, neoud[valIndex]);
			valIndex = neoud[valIndex].getIndex();
		}

		tab.add(0, tabNoeud[0]);
		return tab;
	}

	private static int[][] initTaille(Noeud[] noeuds, Troncon[] tabTroncon) {
		
		ArrayList<Troncon> listeTroncons = new ArrayList<Troncon>();
		for (int i = 0; i < tabTroncon.length; i++)
			if (tabTroncon[i].getEtat() != EtatTroncon.Bloque)
				listeTroncons.add(tabTroncon[i]);

		tabTroncon = listeTroncons.toArray(new Troncon[0]);

		int[][] taille = new int[noeuds.length][noeuds.length];
		for (int i = 0; i < noeuds.length; i++) {
			Arrays.fill(taille[i], Integer.MAX_VALUE);
		}

		for (Troncon e : tabTroncon) {
			taille[e.getDepart().getIndex()][e.getDestination().getIndex()] = e.getTaille();
		}
		return taille;
	}
}
