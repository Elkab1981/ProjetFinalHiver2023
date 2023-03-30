package gestionRoutiers;


/*
 * Cette �num�ration nous permet de donner 
 * une valeur pour chaque �tat du troncon
 */

public enum EtatTroncon {

	Bloque(0), Fluide(1);

	private final int value;

	private EtatTroncon(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
