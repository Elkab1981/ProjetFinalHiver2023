package gestionRoutiers;


public interface TronconListener {

	void congestionnerTroncon(TronconEvent event);

	void decongTroncon(TronconEvent event);
}