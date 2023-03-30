package interfaceApplication;

import gestionRoutiers.VehiculeEvent;

public interface VehiculeListener {

	void deplacerVoiture(VehiculeEvent e);

	void arretVoiture(VehiculeEvent e);

	void majTrajetSuivi(VehiculeEvent e);

}