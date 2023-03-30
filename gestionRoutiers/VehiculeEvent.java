package gestionRoutiers;


public class VehiculeEvent {

	private Vehicule vehicule;

	public VehiculeEvent(Vehicule vehicule) {
		this.setVehicule(vehicule);
	}

	// Setters et Getters
	public Vehicule getVehicule() {
		return this.vehicule;
	}

	private void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}
}