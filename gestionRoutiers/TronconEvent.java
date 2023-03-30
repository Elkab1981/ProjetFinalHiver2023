package gestionRoutiers;


public class TronconEvent {

	private Troncon troncon;

	public TronconEvent(Troncon troncon) {
		this.setTroncon(troncon);
	}

	// Setters et Getters
	public Troncon getTroncon() {
		return troncon;
	}

	private void setTroncon(Troncon troncon) {
		this.troncon = troncon;
	}
}

