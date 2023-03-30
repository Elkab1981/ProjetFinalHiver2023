package gestionRoutiers;


public class NoeudEvent {

	private Noeud noeud;

	public NoeudEvent(Noeud noeud) {
		this.setNoeud(noeud);
	}

	public Noeud getNoeud() {
		return noeud;
	}

	private void setNoeud(Noeud noeud) {
		this.noeud = noeud;
	}
}
