package gestionRoutiers;


public interface NoeudListener {
	
	void congestionnerNoeud(NoeudEvent event);

	void decongNoeud(NoeudEvent event);
}