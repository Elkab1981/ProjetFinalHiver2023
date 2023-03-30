package gestionRoutiers;


public class TrajetEvent {

	private Trajet trajet;

	public TrajetEvent(Trajet trajet) {
		this.setChemin(trajet);
	}

	public Trajet getChemin() {
		return trajet;
	}

	private void setChemin(Trajet trajet) {
		this.trajet = trajet;
	}
}