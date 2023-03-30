package gestionRoutiers;


/*
 * Cette énumération permet de donner des
 * numéros pour chaque direction
 */
public enum Direction {

	Haut(0), Droite(1), Bas(1), Gauche(2);

	private final int value;

	private Direction(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
