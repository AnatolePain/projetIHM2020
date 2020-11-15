/**
 * La classe <code>PieceVueController</code> gère les intéraction de la fausse vue 3d cliquable 
 * entre le modèle et les autres vues
 * @version 1.0
 * @author Anatole PAIN
 */
package fr.iutfbleau.projetIHM2020FI2.CONTROLEUR;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class PieceVueController
{
	/**
    * Retient en mémoire les informations d'affichage à envoyer à la vue:
    * Si 1 dans le tableaux alors c'est un rocher, si 0 une porte.
   	*/
	private static int direction[] = new int[4]; 	

	/**
    * Retient en mémoire les informations d'affichage à envoyer à la vue:
    * Si 1 dans le tableaux alors c'est une porte ouverte, sinon 0 une porte.
   	*/
	private static int directionO[] = new int[4];

	/**
	* Corespond au numéro de l'énumération API/Direction.
	**/
	private static int numDirection = 0;

	/**
	* Constructeur qui set les données d'affichage en fonction du modèle et l'envoie à la vue
	* (porte ouverte, porte fermée ou rocher).
	**/
	public PieceVueController()
	{
		Joueur j = SetupModel.getJoueur();
		for(int i = 0; i < 4;i++)					
		{
			Passage autrePassage = j.getPiece().getPassage(Direction.values()[i]);
			PieceVueController.direction[i] = autrePassage == null ? 1 : 0;
			PieceVueController.directionO[i] = autrePassage == null ? 0 : autrePassage.getEtatPassage() == EtatPassage.OPEN ? 1 : 0;
		}
		PieceVue.getPieceVue().transition(PieceVueController.direction,PieceVueController.directionO);
	}

	/**
	* Change la pièce du joueur dans la vue et dans le modèle si elle n'est
	* pas fermée ou vérouillée, en fonciton de la direction en atribut.
	**/
	public static void setPieceController(){

		Direction d = Direction.values()[PieceVueController.numDirection];
		Passage p = SetupModel.getJoueur().getPiece().getPassage(d);
		if(p == null){
			DialogDescription.showD(d.toString(),"le passage est bloqué par un rocher");
		}else if(p.getEtatPassage() == EtatPassage.OPEN){

			//--- MODELE --- 
			Piece newPiece = p.getAutrePiece(SetupModel.getJoueur().getPiece());
			SetupModel.getJoueur().setPiece(newPiece);

			//--- VUE ---

			//PieceVue
			for(int i = 0; i < 4;i++)					
			{
				Passage autrePassage = newPiece.getPassage(Direction.values()[i]);
				PieceVueController.direction[i] = autrePassage == null ? 1 : 0;
				PieceVueController.directionO[i] = autrePassage == null ? 0 : autrePassage.getEtatPassage() == EtatPassage.OPEN ? 1 : 0;
			}
			PieceVue.getPieceVue().transition(direction,directionO);

			//MiniCarte
			MiniCarteVue.getMinicarteVue().move(d);
			MiniCarteVue.getMinicarteVue().affichePassage(direction);

			//Contenue de la pièce
			PieceController.changePiece(SetupModel.getJoueur().getPiece());
		}
		else if(p.getEtatPassage() == EtatPassage.CLOSED){
			DialogDescription.showD(d.toString(),p.getDescription());
		}
		else if(p.getEtatPassage() == EtatPassage.LOCKED){
			DialogDescription.showD(d.toString(),p.getDescription());
		}else{
			//erreur
		}

	}

	/**
	* Lance la méthode agir sans trucs sur le passage de la direction. Change la vue en fonction.
	**/
 	public static void agirNull(){

 		Direction d = Direction.values()[PieceVueController.numDirection];
 		Joueur j = SetupModel.getJoueur();
 		Passage p = j.getPiece().getPassage(d);

 		if(p != null){

 			//MODELE
	 		if(p.agir(null)){

	 			//VUE
		 		if(p.getEtatPassage() == EtatPassage.CLOSED){

		 			PieceVueController.directionO[d.ordinal()] = 0;
					PieceVue.getPieceVue().reCreateOuvert(PieceVueController.directionO);
					DialogDescription.showD(d.toString(),"la porte " + d.toString() + " est maintenant fermée");
		 		}else if(p.getEtatPassage() == EtatPassage.OPEN){

					PieceVueController.directionO[d.ordinal()] = 1;
					PieceVue.getPieceVue().reCreateOuvert(PieceVueController.directionO);
					DialogDescription.showD(d.toString(),"la porte " + d.toString() + " est maintenant ouverte");
		 		}

		 	}else{
		 		DialogDescription.showD(d.toString(),p.getDescription());
		 	}
		}/*else{
			DialogDescription.showD("Porte","le passage " + d.toString() + " est bloqué par un rocher");
		}*/
 	}

 	/**
	* Lance la méthode agir avec un truc sur le passage de la direction. Change la vue en fonction.
 	**/
 	public static void agirAvecTruc(Truc t)
	{
 		Direction d;
 		Joueur j = SetupModel.getJoueur();
 		Passage p;
		TypeTruc tt = t.getTypeTruc();

		if (tt == TypeTruc.CLE)
		{
			d = Direction.values()[PieceVueController.numDirection];
			p = j.getPiece().getPassage(d);
 			if(p != null){

				//MODELE
	 			if(p.agir(t)){

	 				//VUE
		 			if(p.getEtatPassage() == EtatPassage.OPEN)
					{
	 					PieceVueController.directionO[d.ordinal()] = 1;
						PieceVue.getPieceVue().reCreateOuvert(PieceVueController.directionO);
		 				DialogDescription.showD("Bravo !","Vous avez déverrouillé la porte " + d.toString() + " !");
	 				}
					else if(p.getEtatPassage() == EtatPassage.LOCKED )
					{
			 			PieceVueController.directionO[d.ordinal()] = 0;
						PieceVue.getPieceVue().reCreateOuvert(PieceVueController.directionO);
		 				DialogDescription.showD("Bravo !","Vous avez verrouillé la porte " + d.toString() + " !");
		 			}
		 		}else{
		 			DialogDescription.showD("Attention ","la clé doit être rouillé");
		 		}
			}else{
				DialogDescription.showD("Attention "," Il n'y pas de passage dans le direction " + d.toString() + " !");
			}
		}

 	}

 	/**
	* Change la diréction par la droite (quart de tour dans le sens horaire)
 	**/
 	public static void orientationADroite(){
 		PieceVueController.numDirection = (PieceVueController.numDirection+1)%4;
		PieceVue.getPieceVue().changementThread(true);
 	}

 	/**
	* Change la diréction par la gauche (quart de tour dans le sens anti-horaire)
 	**/
 	public static void orientationAGauche(){
 		if(--PieceVueController.numDirection < 0){
			PieceVueController.numDirection = 3;
		}
		PieceVue.getPieceVue().changementThread(false);
 	}


}