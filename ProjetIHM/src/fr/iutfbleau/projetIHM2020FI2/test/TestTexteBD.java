package fr.iutfbleau.projetIHM2020FI2.test;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import fr.iutfbleau.projetIHM2020FI2.MODEL.*;

public class TestTexteBD
{
	public static void main(String[] args) 
	{
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

		Piece[] tabPiece = {p0, p1, p2, p3, p4, p5, p6, p7, p8, p9};
		Passage p0p1 = usinePassagePiece.newPassage(Direction.OUEST,p0,Direction.EST,p1);
		Passage p0p2 = usinePassagePiece.newPassage(Direction.NORD,p0,Direction.SUD,p2);
		Passage p0p3 = usinePassagePiece.newPassage(Direction.EST,p0,Direction.OUEST,p3);
		p0p3.setEtatPassage(EtatPassage.LOCKED);
		Passage p1p5 = usinePassagePiece.newPassage(Direction.SUD,p1,Direction.NORD,p5);
		Passage p3p9 = usinePassagePiece.newPassage(Direction.EST,p3,Direction.OUEST,p9);
		Passage p4p3 = usinePassagePiece.newPassage(Direction.SUD,p4,Direction.NORD,p3);
		Passage p5p6 = usinePassagePiece.newPassage(Direction.EST,p5,Direction.OUEST,p6);
		Passage p6p7 = usinePassagePiece.newPassage(Direction.EST,p6,Direction.OUEST,p7);
		Passage p7p8 = usinePassagePiece.newPassage(Direction.EST,p7,Direction.OUEST,p8);
		Passage p8p9 = usinePassagePiece.newPassage(Direction.NORD,p8,Direction.SUD,p9);
		TrucFactory usineTruc = new TrucFactoryBD();
		Truc t0 = usineTruc.newTruc(TypeTruc.ALCOOL,"une bouteille de rhum hors d'âge");
		Truc t1 = usineTruc.newTruc(TypeTruc.CLE,"une clé en bronze");
		Truc t2 = usineTruc.newTruc(TypeTruc.EAU,"une cruche d'eau");
		Truc t3 = usineTruc.newTruc(TypeTruc.EAU,"une gourde d'eau");
		Truc t4 = usineTruc.newTruc(TypeTruc.GOODIES,"une bourse en cuir avec 10 euros");	
		Truc t5 = usineTruc.newTruc(TypeTruc.GOODIES,"une bourse en cuir avec 10 euros");
		Truc t6 = usineTruc.newTruc(TypeTruc.GOODIES,"une bourse en cuir avec 10 euros");
		Truc t7 = usineTruc.newTruc(TypeTruc.GOODIES,"Chausure");
		Truc t8 = usineTruc.newTruc(TypeTruc.EAU,"eau croupie");
		System.out.print("Création du dongeon.\n");
		System.out.print("Création des objets.\n");

		if (p0.addTruc(t0)&&p0.addTruc(t1)&&p0.addTruc(t2)&&p1.addTruc(t3)&&p2.addTruc(t4)&&p1.addTruc(t4)&&p3.addTruc(t5)&&p0.addTruc(t6))
		{
			System.out.print("Ajout des objets dans les pièces du dongeon.\n");
		}
		Joueur j = new JoueurBD();
		System.out.print("Création du joueur.\n");

		j.addTruc(t7);
		j.addTruc(t6);
		j.addTruc(t8);

		System.out.println("==========================");
		System.out.println("==========================");
		//description pièce du joueur et son sac à dos.
		System.out.print(j.getPiece().getDescription());
		System.out.print(j.getDescription());
		System.out.println("==========================");
		// action sur une porte
		Passage p = j.getPiece().getPassage(Direction.OUEST);//c'est le passage p0p1
		if (p.agir(null)) 
		{//"le joueur" agit sur le passage à l'ouest, en cas de changement la méthode retourne vrai.
			System.out.print("Le joueur ouvre la porte à l'ouest.\n");	
			System.out.print(j.getPiece().getDescription());
			System.out.print(j.getDescription());
		}
		System.out.println("==========================");
		// prise d'un objet
		if (j.getPiece().removeTruc(t2) && j.addTruc(t2))
			System.out.print("Le joueur prend un objet.\n");
		System.out.print(j.getPiece().getDescription());
		System.out.print(j.getDescription());
		System.out.println("==========================");
		//déplacement
		System.out.print("Le joueur va à l'ouest.\n");
		j.setPiece(p.getAutrePiece(j.getPiece()));       // le joueur est dans la pièce p1
		//----------------------------------------------
		System.out.println("------------------------");

		//System.out.print("\n quellePiece(Piece[] tabPiece, Piece reel)= \n" + p.getAutrePiece(j.getPiece()));

		System.out.println("piece actuel du joueur = " + quellePiece(tabPiece,j.getPiece()) );
		//System.out.println("autre pièce = " + quellePiece(tabPiece, p.getAutrePiece(p1)) );
		
		System.out.println("----- BOUCLE ------");
		System.out.println("autre pièce en partant de p0");
		/*for(int i = 0; i < 10; i++){
			System.out.println("autre pièce ("+i+") = " + quellePiece(tabPiece, p.getAutrePiece(tabPiece[i])));
		}*/

		System.out.println("----- TEST METHODE ------");
		Piece[] tabAEteParcouru = new Piece[50];
		//parcoursGraphe(null, p0,tabAEteParcouru, 0, tabPiece);

		System.out.print("\n-----CHANGEMENT1------.\n");

		System.out.println("------------------------");
		//---------------------------------------------
		System.out.print(j.getPiece().getDescription());
		System.out.print(j.getDescription());
		System.out.println("==========================");
		//dépôt d'un objet.
		if (j.removeTruc(t2) && j.getPiece().addTruc(t2))
			System.out.print("Le joueur dépose un objet.\n");
		System.out.print(j.getPiece().getDescription());
		System.out.print(j.getDescription());
		System.out.println("==========================");
		//déplacement
		System.out.print("Le joueur retourne à l'est.\n");
		p = j.getPiece().getPassage(Direction.EST);
		j.setPiece(p.getAutrePiece(j.getPiece()));
		System.out.print(j.getPiece().getDescription());
		System.out.print(j.getDescription());
		System.out.println("==========================");
		//prise d'un objet
		if (j.getPiece().removeTruc(t1) && j.addTruc(t1))
			System.out.print("Le joueur prend un objet.\n");
		System.out.print(j.getPiece().getDescription());
		System.out.print(j.getDescription());
		System.out.println("==========================");
		// action sur une porte
		p = j.getPiece().getPassage(Direction.EST);
		if (p.agir(t1)) {//"le joueur" agit sur le passage à l'est avec t1, en cas de changement la méthode retourne vrai.
			System.out.print("Le joueur ouvre une porte avec un objet.\n");
			System.out.print(j.getPiece().getDescription());
			System.out.print(j.getDescription());
		}
		System.out.println("==========================");
		//déplacement
		System.out.print("Le joueur va à l'est.\n");
		j.setPiece(p.getAutrePiece(j.getPiece()));
		System.out.print(j.getPiece().getDescription());
		System.out.print(j.getDescription());
		System.out.println("==========================");
	}

	public static String quellePiece(Piece[] tabPiece, Piece reel){

		int i = 0;

		while(reel != tabPiece[i]){
			i++;
		}

		return "piece"+i;
	}


}	
