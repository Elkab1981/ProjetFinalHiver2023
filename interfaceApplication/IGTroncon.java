package interfaceApplication;

import java.awt.Color;
import javax.swing.JPanel;
import gestionRoutiers.Troncon;
import gestionRoutiers.Direction;
import gestionRoutiers.TronconEvent;
import gestionRoutiers.SensTroncon;
import gestionRoutiers.TronconListener;

/**
*  Classe d�finissant l'interface graphique de troncon              
*/
public class IGTroncon extends JPanel implements TronconListener {  
	
	/*   Les attributs   */
	
	private static final long serialVersionUID = 1L;
	private Troncon troncon;
	
	
	/*   Constructeur  */
	
	public IGTroncon(Troncon troncon) {
		this.setTroncon(troncon);
		this.setToolTipText("" + troncon.getTaille());

		int dx = 25;
		int dy = 25;

		int lg = dx;
		int ht = dy;

		int posX_1 = Math.min(troncon.getDepart().getAbscisse(), troncon.getDestination().getAbscisse());
		int posY_1 = Math.min(troncon.getDepart().getOrdonne(), troncon.getDestination().getOrdonne());
		int posX_2 = Math.max(troncon.getDepart().getAbscisse(), troncon.getDestination().getAbscisse());
		int posY_2 = Math.max(troncon.getDepart().getOrdonne(), troncon.getDestination().getOrdonne());

		if (troncon.getOrientation() == SensTroncon.Vertical) {
			ht = Math.abs(troncon.getDepart().getOrdonne() - troncon.getDestination().getOrdonne()) - IGNoeud.HEIGHT;
			dy = IGNoeud.HEIGHT;

			if (troncon.getDirection() == Direction.Bas) {
				dx = 0;
			}

			this.setBounds(posX_1 + dx, posY_1 + dy, posX_2 - posX_1 + lg, ht);
		} else {
			lg = Math.abs(troncon.getDepart().getAbscisse() - troncon.getDestination().getAbscisse()) - IGNoeud.WIDTH;
			dx = IGNoeud.WIDTH;

			if (troncon.getDirection() == Direction.Gauche) {				
				dy = 0;
			}
			this.setBounds(posX_1 + dx, posY_1 + dy, lg, posY_2 - posY_1 + ht);
		}

		
	}

	// met les couleurs du réseau à jour
	public void updateColor() {	
		this.setBackground(Color.GRAY);
	}

	
	/*     Les accesseurs     */
	public Troncon getTroncon() {
		return troncon;
	}

	private void setTroncon(Troncon troncon) {
		this.troncon = troncon;
	}

	

	@Override
	public void congestionnerTroncon(TronconEvent event) {

	}

	@Override
	public void decongTroncon(TronconEvent event) {
		
	}
}
