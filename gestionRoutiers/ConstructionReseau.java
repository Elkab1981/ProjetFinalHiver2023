package gestionRoutiers;


/*
 * Cette classe nous permet de de positionner nos diff�rents noeuds, 
 * panneaux et troncons dans le r�seau.  
 */
public class ConstructionReseau {

	@SuppressWarnings("removal")
	public static ReseauRoutier construireReseauRoutier() {
		ReseauRoutier reseauRout = new ReseauRoutier();
		
		// L'ensemble de ces valeurs représente les différents poids
		// des troncons qui est également associée aux panneaux 
		int p1 =  (int) (Math.random()*80);
		int p2 = (int) (Math.random() * 80);
		int p3 = (int) (Math.random() * 80);
		int p4 = (int) (Math.random() * 80);
		int p5 = (int) (Math.random() * 100);
		int p6 =  (int)(Math.random() * 100);
		int p7 =  (int)(Math.random() * 100);
		int p8 =  (int)(Math.random() * 100);
		int p9 =  (int)(Math.random() * 100);
		int p10 = (int)(Math.random() * 80);
		int p11 =  (int) (Math.random() * 100);
		int p12 =  (int)(Math.random() * 100);
		int p13 = (int)(Math.random() * 100);
		int p14 = (int)(Math.random() * 100);
		int p15 = (int)(Math.random() * 100);
		int p16 = (int)(Math.random() * 100);
		int p17 = (int)(Math.random() * 100);
		int p18 = (int)(Math.random() * 100);
		int p19 = (int)(Math.random() * 100);
		int p20 = (int)(Math.random() * 100);
		int p21 = (int)(Math.random() * 100);
		int p22 = (int)(Math.random() * 100);
		int p23 = (int)(Math.random() * 100);
		int p24 = (int)(Math.random() * 100);
		int p25 = (int)(Math.random() * 100);
		int p26 = (int)(Math.random() * 100);
		int p27 = (int)(Math.random() * 100);
		int p28 = (int)(Math.random() * 100);
		int p29 = (int)(Math.random() * 100);
		int p30 = (int)(Math.random() * 100);
		int p31 = (int)(Math.random() * 100);
		int p32 = (int)(Math.random() * 100);
		

		// Ajout des panneaux	
		
		reseauRout.ajouterPanneau(150, 70, "A - B", p1 );
		reseauRout.ajouterPanneau(220, 85, "B - C", p2);
		reseauRout.ajouterPanneau(395, 85, "C - D", p3);
		reseauRout.ajouterPanneau(570, 85, "D - E", p4);
				
		reseauRout.ajouterPanneau(150, 195, "B - F", p5);
		reseauRout.ajouterPanneau(350, 195, "C - G", p6);
		reseauRout.ajouterPanneau(500, 195, "D - H", p7);
		reseauRout.ajouterPanneau(700, 195, "E - I", p8);
				
		reseauRout.ajouterPanneau(220, 235, "F - G", p9);
		reseauRout.ajouterPanneau(395, 235, "G - H", p10);
		reseauRout.ajouterPanneau(570, 235, "H - I", p11);
				
		reseauRout.ajouterPanneau(150, 345, "F - J", p12);
		reseauRout.ajouterPanneau(350, 345, "G - K", p13);
		reseauRout.ajouterPanneau(500, 345, "H - L", p14);
		reseauRout.ajouterPanneau(700, 345, "I - M", p15);
				
		reseauRout.ajouterPanneau(220, 385, "J - K", p16);
		reseauRout.ajouterPanneau(395, 385, "K - L", p17);
		reseauRout.ajouterPanneau(570, 385, "L - M", p18);
				
		reseauRout.ajouterPanneau(150, 495, "J - N", p19);
		reseauRout.ajouterPanneau(350, 495, "K - O", p20);
		reseauRout.ajouterPanneau(500, 495, "L - P", p21);
		reseauRout.ajouterPanneau(700, 495, "M - Q", p22);
				
		reseauRout.ajouterPanneau(220, 535, "N - O", p23);
		reseauRout.ajouterPanneau(570, 535, "P - Q", p24);
				
		reseauRout.ajouterPanneau(150, 645, "N - R", p25);
		reseauRout.ajouterPanneau(350, 645, "O - S", p26);
		reseauRout.ajouterPanneau(500, 645, "P - T", p27);
		reseauRout.ajouterPanneau(700, 645, "Q - U", p28);
				
		reseauRout.ajouterPanneau(220, 685, "R - S", p29);
		reseauRout.ajouterPanneau(395, 685, "S - T", p30);
		reseauRout.ajouterPanneau(570, 685, "T - U", p31);
		reseauRout.ajouterPanneau(720, 685, "U - V", p32);
		
		// Ajout des noeuds
		reseauRout.ajouterNoeud(100, 00, "A");  
		reseauRout.ajouterNoeud(100, 100, "B"); 
		reseauRout.ajouterNoeud(300, 100, "C"); 
		reseauRout.ajouterNoeud(450, 100, "D"); 
		reseauRout.ajouterNoeud(650, 100, "E"); 
		
		reseauRout.ajouterNoeud(100, 250, "F");
		reseauRout.ajouterNoeud(300, 250, "G"); 
		reseauRout.ajouterNoeud(450, 250, "H"); 
		reseauRout.ajouterNoeud(650, 250, "I"); 
		
		reseauRout.ajouterNoeud(100, 400, "J"); 
		reseauRout.ajouterNoeud(300, 400, "K"); 
		reseauRout.ajouterNoeud(450, 400, "L"); 
		reseauRout.ajouterNoeud(650, 400, "M"); 
		
		reseauRout.ajouterNoeud(100, 550, "N"); 
		reseauRout.ajouterNoeud(300, 550, "O"); 
		reseauRout.ajouterNoeud(450, 550, "P");
		reseauRout.ajouterNoeud(650, 550, "Q"); 
		
		reseauRout.ajouterNoeud(100, 700, "R"); 
		reseauRout.ajouterNoeud(300, 700, "S"); 
		reseauRout.ajouterNoeud(450, 700, "T"); 

		reseauRout.ajouterNoeud(650, 700, "U"); 
		reseauRout.ajouterNoeud(750, 700, "V"); 

		// Ajout des voies
		reseauRout.ajouterTroncon(reseauRout.noeuds.get(0), reseauRout.noeuds.get(1), "A - B",
				p1);
		reseauRout.ajouterTroncon(reseauRout.noeuds.get(1), reseauRout.noeuds.get(2), "B - C",
				p2);
		reseauRout.ajouterTroncon(reseauRout.noeuds.get(1), reseauRout.noeuds.get(5), "B - F",
				p5);

		reseauRout.ajouterTroncon(reseauRout.noeuds.get(2), reseauRout.noeuds.get(3), "C - D", 
				p3);
		reseauRout.ajouterTroncon(reseauRout.noeuds.get(2), reseauRout.noeuds.get(6), "C - G", 
				p6);
	    reseauRout.ajouterTroncon(reseauRout.noeuds.get(3), reseauRout.noeuds.get(4), "D - E",
	    		p4);
	    reseauRout.ajouterTroncon(reseauRout.noeuds.get(4), reseauRout.noeuds.get(8), "E - I",
	    		p8);
		reseauRout.ajouterTroncon(reseauRout.noeuds.get(3), reseauRout.noeuds.get(7), "D - H",
				p7);

		reseauRout.ajouterTroncon(reseauRout.noeuds.get(5), reseauRout.noeuds.get(6), "F - G", 
				p9);
		reseauRout.ajouterTroncon(reseauRout.noeuds.get(5), reseauRout.noeuds.get(9), "F - J", 
				p12);
		reseauRout.ajouterTroncon(reseauRout.noeuds.get(6), reseauRout.noeuds.get(7), "G - H", 
				p10);
		reseauRout.ajouterTroncon(reseauRout.noeuds.get(6), reseauRout.noeuds.get(10), "G - K", 
				p13);

		reseauRout.ajouterTroncon(reseauRout.noeuds.get(7), reseauRout.noeuds.get(8), "H - I", 
				p11);
		reseauRout.ajouterTroncon(reseauRout.noeuds.get(7), reseauRout.noeuds.get(11),"H - L", 
				p14);
		reseauRout.ajouterTroncon(reseauRout.noeuds.get(8), reseauRout.noeuds.get(12), "I - M", 
				p15);
		reseauRout.ajouterTroncon(reseauRout.noeuds.get(9), reseauRout.noeuds.get(10), "J - K", 
				p16);
		reseauRout.ajouterTroncon(reseauRout.noeuds.get(9), reseauRout.noeuds.get(13), "J - N", 
				p19);

		reseauRout.ajouterTroncon(reseauRout.noeuds.get(10), reseauRout.noeuds.get(11), "K - L", 
				p17);
		reseauRout.ajouterTroncon(reseauRout.noeuds.get(10), reseauRout.noeuds.get(14), "K - O", 
				p20);
		reseauRout.ajouterTroncon(reseauRout.noeuds.get(11), reseauRout.noeuds.get(12), "L - M", 
				p18);

		reseauRout.ajouterTroncon(reseauRout.noeuds.get(11), reseauRout.noeuds.get(15), "L - P", 
				p21);
		reseauRout.ajouterTroncon(reseauRout.noeuds.get(12), reseauRout.noeuds.get(16), "M - Q", 
				p22);
		reseauRout.ajouterTroncon(reseauRout.noeuds.get(13), reseauRout.noeuds.get(14), "N - O", 
				p23);
		reseauRout.ajouterTroncon(reseauRout.noeuds.get(13), reseauRout.noeuds.get(17), "N - R", 
				p25);

		reseauRout.ajouterTroncon(reseauRout.noeuds.get(14), reseauRout.noeuds.get(18), "O - S", 
				p26);
		reseauRout.ajouterTroncon(reseauRout.noeuds.get(17), reseauRout.noeuds.get(18), "R - S", 
				p29);
		reseauRout.ajouterTroncon(reseauRout.noeuds.get(15), reseauRout.noeuds.get(16), "P - Q", 
				p24);
		reseauRout.ajouterTroncon(reseauRout.noeuds.get(15), reseauRout.noeuds.get(19), "P - T", 
				p27);
		reseauRout.ajouterTroncon(reseauRout.noeuds.get(19), reseauRout.noeuds.get(20), "Q - U", 
				p28);
		
		reseauRout.ajouterTroncon(reseauRout.noeuds.get(18), reseauRout.noeuds.get(19), "S - T", 
				p30);
		reseauRout.ajouterTroncon(reseauRout.noeuds.get(16), reseauRout.noeuds.get(20), "T - U", 
				p31);
		reseauRout.ajouterTroncon(reseauRout.noeuds.get(20), reseauRout.noeuds.get(21), "U - V", 
				p32);

		 return reseauRout;
  }

}
