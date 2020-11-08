package fr.iutfbleau.projetIHM2020FI2.CONTROLEUR;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import fr.iutfbleau.projetIHM2020FI2.MNP.*;
import fr.iutfbleau.projetIHM2020FI2.MODEL.*;

public class SetupModel 
{
	private static Joueur joueur;

	public SetupModel()
	{
		//SetupModelP();
		SetupModelNonP();
	}

	private void SetupModelNonP()
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
		
		SetupModel.joueur = new JoueurNP();
		SetupModel.joueur.setPiece(p0);
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

		Piece p10 = usinePassagePiece.newPiece();
		Passage p4p10 = usinePassagePiece.newPassage(Direction.EST,p4,Direction.OUEST,p10);
	}

	private void SetupModelP()
	{
		//-------------------- CHARGEMENT DU MODELE PERSISTANT -------------------
		PassagePieceFactory usinePassagePiece = new PassagePieceFactoryBD();
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

		Passage p1p5 = usinePassagePiece.newPassage(Direction.SUD,p1,Direction.NORD,p5);
		Passage p2p4 = usinePassagePiece.newPassage(Direction.EST,p2,Direction.OUEST,p4);
		Passage p3p9 = usinePassagePiece.newPassage(Direction.EST,p3,Direction.OUEST,p9);
		Passage p4p3 = usinePassagePiece.newPassage(Direction.SUD,p4,Direction.NORD,p3);
		Passage p5p6 = usinePassagePiece.newPassage(Direction.EST,p5,Direction.OUEST,p6);
		Passage p6p7 = usinePassagePiece.newPassage(Direction.EST,p6,Direction.OUEST,p7);
		Passage p7p8 = usinePassagePiece.newPassage(Direction.EST,p7,Direction.OUEST,p8);
		Passage p8p9 = usinePassagePiece.newPassage(Direction.NORD,p8,Direction.SUD,p9);	
		System.out.print("Création du dongeon.\n");

		TrucFactory usineTruc = new TrucFactoryBD();
		Truc t0 = usineTruc.newTruc(TypeTruc.ALCOOL,"une bouteille de rhum hors d'âge");
		Truc t1 = usineTruc.newTruc(TypeTruc.CLE,"une clé en bronze");
		Truc t2 = usineTruc.newTruc(TypeTruc.EAU,"une cruche d'eau");
		Truc t3 = usineTruc.newTruc(TypeTruc.EAU,"une gourde d'eau");
		Truc t4 = usineTruc.newTruc(TypeTruc.GOODIES,"une bourse en cuir avec 10 euros");	
		Truc t5 = usineTruc.newTruc(TypeTruc.GOODIES,"une bourse en cuir avec 10 euros");
		System.out.print("Création des objets.\n");

		if (p0.addTruc(t0)&&p0.addTruc(t1)&&p0.addTruc(t2)&&p1.addTruc(t3)&&p2.addTruc(t4)&&p1.addTruc(t4)&&p3.addTruc(t5))
		{
			System.out.print("Ajout des objets dans les pièces du dongeon.\n");
		}

		Piece p10 = usinePassagePiece.newPiece();
		Passage p4p10 = usinePassagePiece.newPassage(Direction.EST,p4,Direction.OUEST,p10);

		SetupModel.joueur = new JoueurBD();
		System.out.print("Création du joueur.\n");
	}

	public static Joueur getJoueur()
	{
		return SetupModel.joueur;
	}
	
}