package interfaceApplication;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gestionRoutiers.Troncon;
import gestionRoutiers.Vehicule;
import gestionRoutiers.VehiculeEvent;

/**
*  Classe pour d�finir le moniteur du v�hicule             
*/
public class Moniteur extends JPanel implements VehiculeListener{

	/*    Les attributs  */
	private static final long serialVersionUID = 1L;
	private Vehicule voiture;

	private int distPrevuee = 0;
	private int distParcourue = 0;
	private JLabel labArrivee = new JLabel();	
	private JLabel labDistParText = new JLabel();
	
	private JLabel labDistParcourue = new JLabel();
	private JLabel labDistPreText = new JLabel();
	private JLabel labDistPrevuee = new JLabel();
	private JLabel labDepart = new JLabel();


	/*     Constructeur sans arguments     */
	public Moniteur() {

		this.setBackground(new Color(150, 190, 190));
		this.setLayout(null);
		
		labDistParText = new JLabel();
		labDistParText.setFont(new Font("Serif", Font.PLAIN, 20));
		labDistParText.setBounds(20, 300, 170, 20);
		this.add(labDistParText);

		labDistParcourue = new JLabel();
		labDistParcourue.setBounds(200, 300, 100, 30);
		labDistParcourue.setFont(new Font("Serif", Font.PLAIN, 20));
		this.add(labDistParcourue);

		labDepart = new JLabel("Depart");
		labDepart.setBounds(20, 40, 150, 30);
		labDepart.setFont(new Font("Serif", Font.PLAIN, 20));
		this.add(labDepart);
		

		labArrivee = new JLabel("Arrivee");
		labArrivee.setBounds(210, 40, 150, 30);
		labArrivee.setFont(new Font("Serif", Font.PLAIN, 20));
		this.add(labArrivee);

		labDistPreText = new JLabel();
		labDistPreText.setBounds(20, 260, 170, 20);
		labDistPreText.setFont(new Font("Serif", Font.PLAIN, 20));
		this.add(labDistPreText);
		
		labDistPrevuee = new JLabel();
		labDistPrevuee.setBounds(200, 260, 100, 30);
		labDistPrevuee.setFont(new Font("Serif", Font.PLAIN, 20));
		this.add(labDistPrevuee);
		
		JLabel lab1 = new JLabel("Circulation fluide");
		lab1.setFont(new Font("Serif", Font.PLAIN, 20));
		lab1.setBounds(50, 500, 200, 20);
		this.add(lab1);
		
		JPanel pan1 = new JPanel();
		pan1.setBackground(Color.GREEN);
		pan1.setBounds(20, 505, 15, 15);
		this.add(pan1);

		JPanel pan2 = new JPanel();
		pan2.setBackground(Color.RED);
		pan2.setBounds(20, 545, 15, 15);
		this.add(pan2);		

		JLabel lab2 = new JLabel("Circulation congestionn\u00E9e ou accident");
		lab2.setFont(new Font("Serif", Font.PLAIN, 20));
		lab2.setBounds(50, 540, 300, 25);
		this.add(lab2);		
	}

	/*     Accesseurs     */
	public void setVehicule(Vehicule vehicule) {
		if (voiture != null)                             /*Pas d'evenement sans vehicule  */
			voiture.removeEventListener(this);

		voiture = vehicule;
		voiture.addEventListener(this);
	}

	private void initTableau() {
		if (voiture != null) {

			Troncon troncon = voiture.getTronconActuel();
			if(troncon!=null) {
				this.labDistParText.setText("Distance parcourue : ");
			}
			if(distPrevuee == 0 ) {
				distPrevuee = distancePrevuee(voiture);
			}
			
			distParcourue = distParcourue + longueurTroncon(voiture);
			this.labDistPrevuee.setText("" + distPrevuee/50 + " Km");
			this.labDistPreText.setText("Distance prevuee : ");
			this.labDistParcourue.setText("" + distParcourue/4500 + " Km");
			
		}
	}
	
	/*   Calcul et Affichage de la longueur du trajet effectif    */
	
	private int longueurTroncon(Vehicule vehicule) {
		int longTroncon = 0;
		int posX, posY;
		
		//Longueur en abscisse(trajet horizontal)
			posX = Math.abs(vehicule.getAbscisse() - vehicule.getTronconActuel().getDestination().getAbscisse());
			
		//Longueur en ordonn�e (trajet vertical)
			posY = Math.abs(vehicule.getOrdonne() - vehicule.getTronconActuel().getDestination().getOrdonne());
			
			//Distance totale
			longTroncon = Math.abs(posX - posY);

		return longTroncon;
	}

	/*   Calcul et affichage de la Longueur du trajet prevu    */
	private int distancePrevuee(Vehicule vehicule) {
		int distance = 0;
		ArrayList<Troncon> troncons = new ArrayList<Troncon>();

		Troncon troncon = vehicule.getChemin().getTronconSuivant(vehicule.getTronconActuel().getDestination());
		while (troncon != null) {
			troncons.add(troncon);
			troncon = vehicule.getChemin().getTronconSuivant(troncon.getDestination());
			
		}

		distance = longueurTroncon(vehicule);
		
		for (Troncon item : troncons) {
			distance = distance + item.longueurTroncon();
		}
	

		return distance;
	}

	@Override
	public void deplacerVoiture(VehiculeEvent e) {

		initTableau();
	}

	@Override
	public void arretVoiture(VehiculeEvent e) {

		initTableau();
	}

	@Override
	public void majTrajetSuivi(VehiculeEvent e) {

	}

}

