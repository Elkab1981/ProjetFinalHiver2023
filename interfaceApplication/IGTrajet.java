package interfaceApplication;

import java.util.ArrayList;
import gestionRoutiers.Troncon;
import gestionRoutiers.Noeud;
import gestionRoutiers.Trajet;

/**
*  Cette classe d�finit l'interface graphique du trajet d�fini              
*/

public class IGTrajet {

	/*   Les attributs   */
	
	private ArrayList<IGNoeud> noeuds = new ArrayList<IGNoeud>();
	private ArrayList<IGTroncon> troncons = new ArrayList<IGTroncon>();

	/*     Contructeur    */
	
	public IGTrajet(Trajet trajet) {
		for (Noeud noeud : trajet.getNoeuds()) {   //Construction � partir des noeuds choisi
			noeuds.add(new IGNoeud(noeud));

			Troncon troncon = trajet.getTronconSuivant(noeud);
			if (troncon != null) {
				IGTroncon igTroncon = new IGTroncon(troncon);
              
				troncons.add(igTroncon);
			}
		}
	}

	/*   Les accesseurs    */
	
	public ArrayList<IGNoeud> getNoeuds() {
		return noeuds;
	}

	public ArrayList<IGTroncon> getTroncons() {
		return troncons;
	}

}




