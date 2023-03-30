package gestionRoutiers;


/*
 * Cette �num�ration nous permet de donner une
 * valeur pour un sens d'orientation sur le troncon
 */

public enum SensTroncon {

	Horizontal(0), Vertical(1);

	private final int value;

	private SensTroncon(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

