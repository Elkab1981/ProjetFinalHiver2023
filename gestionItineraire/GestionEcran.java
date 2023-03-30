package gestionItineraire;
import java.awt.*;
import java.awt.event.*;


import javax.swing.*;

import javax.swing.border.EmptyBorder;

import gestionRoutiers.*;

import interfaceApplication.*;


public class GestionEcran extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel panelReseau;
	private IGReseauRoutier igReseauRout;
	private ReseauRoutier reseau = null;

	private Cursor curseur = new Cursor(Cursor.HAND_CURSOR);
	private Cursor curseurDef = new Cursor(Cursor.DEFAULT_CURSOR);
	private Moniteur moniteur = new Moniteur();
	
	private JComboBox<Noeud> departs,arrivees; // Listes deroulantes
	public Noeud dep, ar;
	private JLabel conteneurImage = new JLabel();
	
	public GestionEcran() {
		
		reseau = ConstructionReseau.construireReseauRoutier();
		initComposants();
		initActionListeners();
		initialiseVehicule();
		panelReseau.add(igReseauRout);
		
		moniteur.setBounds(1025, 13, 400, 600);
		getContentPane().add(moniteur);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 860, 507, 113);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		departs = new JComboBox<Noeud>(igReseauRout.getReseauRoutier().noeuds.toArray(new Noeud[igReseauRout.getReseauRoutier().noeuds.size()]));
		arrivees = new JComboBox<Noeud>(igReseauRout.getReseauRoutier().noeuds.toArray(new Noeud[igReseauRout.getReseauRoutier().noeuds.size()]));
		
		moniteur.add(departs);
		departs.setBounds(20, 75, 125, 30);
		moniteur.add(arrivees);
		arrivees.setBounds(210, 75, 125, 30);

		JButton btMarche = new JButton("Demarrer");
		btMarche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				initVehicule();
				
			}
		});
		
		JButton btArret = new JButton("Quitter");
		btArret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				arret();
			}
		});
		
		btMarche.setBounds(1250, 650, 100, 50);
		getContentPane().add(btMarche);
		btMarche.setBackground(new Color(200, 200, 255));
		
		btArret.setBounds(1100, 650, 100, 50);
		getContentPane().add(btArret);
		btArret.setBackground(new Color(200, 50, 50));

	}
	
	public void arret() {
		
		for (Component comp : igReseauRout.getComponents()) {
			if (comp.getClass().getName() == "interfaceApplication.IGVehicule")
				igReseauRout.remove(comp);
		}
	}

	// Les équipements sont initialises et le réseau Routier chargé
	public void initComposants() {

		panelReseau = new JPanel();
		panelReseau.setBorder(new EmptyBorder(15, 15, 10, 10));
		setContentPane(panelReseau);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1450, 900);
		panelReseau.setLayout(null);
		
		conteneurImage = new JLabel();
		conteneurImage.setBounds(5, 5, 1450, 900);
		this.add(conteneurImage);

		igReseauRout = new IGReseauRoutier(reseau);
		igReseauRout.setBounds(12, 13, 1000, 850);
		igReseauRout.setLayout(null);
		
	}

	private void initVehicule() {
		for (Component comp : igReseauRout.getComponents()) {
			if (comp.getClass().getName() == "interfaceApplication.IGVehicule")
				igReseauRout.remove(comp);
		}
		//Autres vehicule sur le réseau routier
		for (int i = 0; i < 10; i++) {
		IGVehicule igVehicule = new IGVehicule(reseau.ajouterVehicule(reseau.noeuds.get(
		(int)(Math.random() * 5))));
		igVehicule.getVehicule().setDestination(reseau.noeuds.get(
		(int) (Math.random() * 20)));
		
		igVehicule.addActionListener(this);
		igVehicule.addMouseListener(this);
		igReseauRout.add(igVehicule);
		igReseauRout.setComponentZOrder(igVehicule, 0);
		//
		igVehicule.getVehicule().seDeplacer();
		 }
	
		 // Noeud départ
		IGVehicule igVehicule2 = new IGVehicule(reseau.ajouterVehicule(reseau.noeuds.get(7)));   
		// Noeud d'arrivée
		igVehicule2.getVehicule().setDestination(reseau.noeuds.get(20));      
		
		igVehicule2.addActionListener(this);
		igVehicule2.addMouseListener(this);
		igReseauRout.add(igVehicule2);
		igReseauRout.setComponentZOrder(igVehicule2, 0);
		
		igVehicule2.getVehicule().seDeplacer();
		igVehicule2.doClick();

	}
	
	private void initialiseVehicule() {
		initVehicule();
	}

	// definition des Listeners sur les troncons et noeuds
	private void initActionListeners() {

		for (IGTroncon igTroncon : this.igReseauRout.getIGTroncons()) {
			igTroncon.addMouseListener(this);
		}

		for (IGNoeud igNoeud : this.igReseauRout.getIGNoeuds()) {
			igNoeud.addActionListener(this);
			igNoeud.addMouseListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource().getClass().getName() == "interfaceApplication.IGNoeud") {
				IGNoeud igNoeud = (IGNoeud) e.getSource();
		
				if (igNoeud.getNoeud().getEstBloque()) {
					igNoeud.getNoeud().retirerAccident();
					igReseauRout.updateColors();
				}
				else {
					igNoeud.getNoeud().ajouterAccident(25000);
					igReseauRout.updateColors();
					igReseauRout.afficherChemin();
				}
				return;
			}

			if (e.getSource().getClass().getName() == "interfaceApplication.IGVehicule") {

				igReseauRout.updateColors();

				IGVehicule igVehicule = (IGVehicule) e.getSource();
				igReseauRout.setVehiculeChoisi(igVehicule);
				igReseauRout.afficherChemin();
				this.moniteur.setVehicule(igVehicule.getVehicule());
				
				return;
			}

		} catch (Exception ex) {

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

		if (e.getSource().getClass().getName() == "interfaceApplication.IGVehicule") {
			IGVehicule item = (IGVehicule) e.getSource();
			item.setCursor(curseur);
		}
		if (e.getSource().getClass().getName() == "interfaceApplication.IGTroncon") {
			IGTroncon item = (IGTroncon) e.getSource();
			item.setCursor(curseur);
		}
		if (e.getSource().getClass().getName() == "interfaceApplication.IGNoeud") {
			IGNoeud item = (IGNoeud) e.getSource();
			item.setCursor(curseur);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {

		if (e.getSource().getClass().getName() == "interfaceApplication.IGVehicule") {
			IGVehicule item = (IGVehicule) e.getSource();
			item.setCursor(curseurDef);
		}
		if (e.getSource().getClass().getName() == "interfaceApplication.IGTroncon") {
            IGTroncon item = (IGTroncon) e.getSource();
			item.setCursor(curseurDef);
		}
		if (e.getSource().getClass().getName() == "interfaceApplication.IGNoeud") {
			IGNoeud item = (IGNoeud) e.getSource();
			item.setCursor(curseurDef);
		}
	}

	

	@Override
	public void mouseReleased(MouseEvent event) {
		
	}
	
	public IGReseauRoutier getIGReseauRoutier() {
		return igReseauRout;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}	
}

