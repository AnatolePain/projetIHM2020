package fr.iutfbleau.projetIHM2020FI2.VUE;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import fr.iutfbleau.projetIHM2020FI2.MNP.*;
import javax.swing.*;
import java.awt.*;

public class GameVue  extends  JPanel{

	private ImageClassLoader icl = new ImageClassLoader();

	public GameVue(JFrame f)
	{



		//-------------------- CHARGEMENT DU MODELE NON PERSISTANT -------------------


		PassagePieceFactory usinePassagePiece = new PassagePieceFactoryNP();
		    
		Piece p0 = usinePassagePiece.newPiece();
		Piece p1 = usinePassagePiece.newPiece();
		Piece p2 = usinePassagePiece.newPiece();
		Piece p3 = usinePassagePiece.newPiece();
		Piece p4 = usinePassagePiece.newPiece();
		Piece p5 = usinePassagePiece.newPiece();
		Piece p6 = usinePassagePiece.newPiece();
		Piece p7 = usinePassagePiece.newPiece();
		Piece p8 = usinePassagePiece.newPiece();	
		Piece p9 = usinePassagePiece.newPiece();

		Passage p0p1 = usinePassagePiece.newPassage(Direction.OUEST,p0,Direction.EST,p1);
		Passage p0p2 = usinePassagePiece.newPassage(Direction.NORD,p0,Direction.SUD,p2);
		Passage p0p3 = usinePassagePiece.newPassage(Direction.EST,p0,Direction.OUEST,p3);
		//p0p3.setEtatPassage(EtatPassage.OPEN);
		//p0p3.setEtatPassage(EtatPassage.LOCKED);
		Passage p1p5 = usinePassagePiece.newPassage(Direction.SUD,p1,Direction.NORD,p5);
		Passage p2p4 = usinePassagePiece.newPassage(Direction.EST,p2,Direction.OUEST,p4);
		Passage p3p9 = usinePassagePiece.newPassage(Direction.EST,p3,Direction.OUEST,p9);
		Passage p4p3 = usinePassagePiece.newPassage(Direction.SUD,p4,Direction.NORD,p3);
		Passage p5p6 = usinePassagePiece.newPassage(Direction.EST,p5,Direction.OUEST,p6);
		Passage p6p7 = usinePassagePiece.newPassage(Direction.EST,p6,Direction.OUEST,p7);
		Passage p7p8 = usinePassagePiece.newPassage(Direction.EST,p7,Direction.OUEST,p8);
		Passage p8p9 = usinePassagePiece.newPassage(Direction.NORD,p8,Direction.SUD,p9);	
		System.out.print("Création du dongeon.\n");
		
		Joueur j = new JoueurNP();
		j.setPiece(p0);
		System.out.print("Création du joueur.\n");

		//Une usinePassagePiece pour produire des trucs.
		TrucFactory usineTruc = new TrucFactoryNP();
		Truc t0 = usineTruc.newTruc(TypeTruc.ALCOOL,"une bouteille de rhum hors d'âge");
		Truc t1 = usineTruc.newTruc(TypeTruc.CLE,"une clé en bronze");
		Truc t2 = usineTruc.newTruc(TypeTruc.EAU,"une cruche d'eau");
		Truc t3 = usineTruc.newTruc(TypeTruc.EAU,"une gourde d'eau");
		Truc t4 = usineTruc.newTruc(TypeTruc.GOODIES,"une bourse en cuir avec 10 euros");	
		Truc t5 = usineTruc.newTruc(TypeTruc.GOODIES,"une bourse en cuir avec 10 euros");
		System.out.print("Création des objets.\n");

		if (p0.addTruc(t0)&&p0.addTruc(t1)&&p0.addTruc(t2)&&p1.addTruc(t3)&&p2.addTruc(t4)&&p1.addTruc(t4)&&p3.addTruc(t5))
			System.out.print("Ajout des objets dans les pièces du dongeon.\n");

		//-------------------------------------------------------------------------


	    GridBagLayout theLayout = new GridBagLayout();
	    this.setLayout(theLayout);
	    Color nuanceGris = new Color(92, 99, 116);
	    this.setBackground(Color.lightGray);


		//Mini-carte
		MiniCarteVue mcv = new MiniCarteVue();
		GridBagConstraints gbc01 = new GridBagConstraints();
		gbc01.gridx = 1;      // la plage de cellules commence à la première colonne
		gbc01.gridy = 0;      // la plage de cellules commence à la deuxième ligne
		gbc01.gridwidth = 1;  // la plage de cellules englobe deux colonnes
		gbc01.gridheight = 1; // la plage de cellules englobe une seule ligne
		gbc01.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
		//gbc.anchor = GridBagConstraints.CENTER; // se place au centre de la plage
		gbc01.weightx = 0.0;  // souhaite plus de largeur si possible
		gbc01.weighty = 0.0;  // n'a pas besoin de hauteur supplémentaire
		gbc01.insets = new Insets(16, 16, 0, 16);    // laisse 5 pixels de vide autour du composant
		this.add(mcv, gbc01);


		//Inventaire joueur
		DialogDescription dialog = new DialogDescription(f);
		InventaireContenuVue icv = new InventaireContenuVue(dialog);
		icv.setPreferredSize(new Dimension(300, 200));
		GridBagConstraints gbc02 = new GridBagConstraints();
		gbc02.gridx = 1;      // la plage de cellules commence à la première colonne
		gbc02.gridy = 1;      // la plage de cellules commence à la deuxième ligne
		gbc02.gridwidth = 1;  // la plage de cellules englobe deux colonnes
		gbc02.gridheight = 2; // la plage de cellules englobe une seule ligne
		gbc02.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
		//gbc.anchor = GridBagConstraints.CENTER; // se place au centre de la plage
		gbc02.weightx = 0.0;  // souhaite plus de largeur si possible
		gbc02.weighty = 0.0;  // n'a pas besoin de hauteur supplémentaire
		gbc02.insets = new Insets(16, 16, 16, 16);    // laisse 5 pixels de vide autour du composant
		
		//icv.addTruc(TypeTruc.CLE,"Cle qui ouvre une porte, il est indiquer porte 516");
  		//icv.addTruc(TypeTruc.CLE,"Cle qui ouvre une porte, il est indiquer porte 517");
  		//icv.addTruc(TypeTruc.EAU,"Gourde d'eau");
  		//icv.addTruc(TypeTruc.ALCOOL,"Du bon Rhum");
  		//icv.addTruc(TypeTruc.GOODIES,"500 piece d'or");
  		//icv.addTruc(TypeTruc.ALCOOL,"Du vieux vin");
  		//icv.addTruc(TypeTruc.EAU,"AHHHHHHHHHHHHHHHHHHHHHHHHHHH");
		this.add(icv, gbc02);

		j.addTruc(t2);
		icv.addTruc(t2.getTypeTruc(),t2.getDescription());

		//Contenu de la pièce
        PieceContenuVue pcv = new PieceContenuVue(dialog, j);
		pcv.setPreferredSize(new Dimension(800, 250));
		GridBagConstraints gbc03 = new GridBagConstraints();
		gbc03.gridx = 0;      // la plage de cellules commence à la première colonne
		gbc03.gridy = 2;      // la plage de cellules commence à la deuxième ligne
		gbc03.gridwidth = 1;  // la plage de cellules englobe deux colonnes
		gbc03.gridheight = 1; // la plage de cellules englobe une seule ligne
		gbc03.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
		//gbc.anchor = GridBagConstraints.CENTER; // se place au centre de la plage
		gbc03.weightx = 0.0;  // souhaite plus de largeur si possible
		gbc03.weighty = 0.0;  // n'a pas besoin de hauteur supplémentaire
		gbc03.insets = new Insets(16, 16, 16, 0);    // laisse 5 pixels de vide autour du composant
		this.add(pcv, gbc03);

		// pcv.addTruc(TypeTruc.CLE,"Cle qui ouvre une porte, il est indiquer porte 516");
  //       pcv.addTruc(TypeTruc.CLE,"Cle qui ouvre une porte, il est indiquer porte 517");
  //       pcv.addTruc(TypeTruc.EAU,"Gourde d'eau");
  //       pcv.addTruc(TypeTruc.ALCOOL,"Du bon Rhum");
  //       pcv.addTruc(TypeTruc.GOODIES,"500 piece d'or");
  //       pcv.addTruc(TypeTruc.ALCOOL,"Du vieux vin");
  //       pcv.addTruc(TypeTruc.EAU,"AHHHHHHHHHHHHHHHHHHHHHHHHHHH");

	    //Vue en fausse 3D
	    PieceVue pv = new PieceVue(j, mcv, pcv);
		GridBagConstraints gbc00 = new GridBagConstraints();
		gbc00.gridx = 0;      // la plage de cellules commence à la première colonne
		gbc00.gridy = 0;      // la plage de cellules commence à la deuxième ligne
		gbc00.gridwidth = 1;  // la plage de cellules englobe deux colonnes
		gbc00.gridheight = 2; // la plage de cellules englobe une seule ligne
		gbc00.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
		//gbc.anchor = GridBagConstraints.CENTER; // se place au centre de la plage
		gbc00.weightx = 1.0;  // souhaite plus de largeur si possible
		gbc00.weighty = 1.0;  // n'a pas besoin de hauteur supplémentaire
		gbc00.insets = new Insets(16, 16, 0, 0);    // laisse 5 pixels de vide autour du composant
		this.add(pv, gbc00);


	}




}