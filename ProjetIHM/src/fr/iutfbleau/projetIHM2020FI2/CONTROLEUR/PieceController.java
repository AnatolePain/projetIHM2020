/**
 * La classe <code>PieceController</code> gère les intéraction de la fausse vue 3d cliquable 
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

public class PieceController 
{	

	/**
	* Tableaux des cases du contenu de la pièce 
	**/
	private static CasePiece[] caseP;

	/**
	* Tableaux des events de description pour chaque cases
	**/
	private static DescriptionEvent[] descriptionEvent;
	
	//model
	private Joueur jP;
	private Piece piecePos;

	/**
	* Map permétant d'associer chaque casse un à truc 
	**/
	private static Map<CasePiece, Truc> mapCasePieceTruc = new HashMap<CasePiece, Truc>();

	/**
	* Déclaration de tout les events et affiche le contenu de la pièces en fonction du modèle 
	*
	**/
	public PieceController()
	{
		PieceController.descriptionEvent = new DescriptionEvent[PieceContenuVue.getNbCase()];
		PieceController.caseP = PieceContenuVue.getCasePiecetab();
		this.jP = SetupModel.getJoueur();

		Iterator<Truc> pT = jP.getPiece().getTrucs();

		for(int i = 0 ; i < PieceContenuVue.getNbCase(); i++)
		{
			PieceController.caseP[i].addMouseListener(new CasePieceEvent(PieceController.caseP[i]));

			//Ramasser
			PieceContenuEvent ramasserEvent = new PieceContenuEvent(PieceController.caseP[i]);
			PieceController.caseP[i].GetPopupMenu().getMenuItemRamasser().addActionListener(ramasserEvent);

			//Descripion
			PieceController.descriptionEvent[i] = new DescriptionEvent();
			PieceController.caseP[i].GetPopupMenu().getMenuItemDescription().addActionListener(PieceController.descriptionEvent[i]);

			//setMap
			if(pT.hasNext()){
				PieceController.mapCasePieceTruc.put(PieceController.caseP[i], pT.next());
			}
		}

		this.piecePos = this.jP.getPiece();
		if(this.piecePos != null)
		{
			PieceController.changePiece(this.piecePos);
		}

	}

	/**
	* Affiche les éléments de la pièces p dans la vue 
	**/
	public static void changePiece(Piece p)
	{
		if(p != null)
		{
			PieceContenuVue.clear();
			Iterator<Truc> pT = p.getTrucs();
			int i = 0;
			while(pT.hasNext())
			{
				Truc t = pT.next();
				PieceContenuVue.addTruc(t.getTypeTruc(),t.getDescription());
				PieceController.descriptionEvent[i].setDescription(t.getDescription());
				PieceController.mapCasePieceTruc.put(PieceController.caseP[i],t);
				i++;
			}
		}
	}

	/**
	* Action sur le modèle et la vue quand on prend une truc dans une pièce 
	**/
	public static void ramasserPiece(CasePiece cp){

		//Modele
		Truc t = mapCasePieceTruc.get(cp);
		SetupModel.getJoueur().getPiece().removeTruc(t);
		SetupModel.getJoueur().addTruc(t);

		//Vue
		PieceController.mapCasePieceTruc.put(cp,null);
		cp.clearObject();
		InventaireController.addInventaireVue(t);
	}

	/**
	* Ajoute un truc à la vue et set la map du coontroller 
	**/
	public static void addPieceVue(Truc t){
		int i = 0;
		while(!PieceController.caseP[i].isEmpty()){
			i++;
		}
		PieceController.mapCasePieceTruc.put(caseP[i],t);
		PieceController.descriptionEvent[i].setDescription(t.getDescription());
		PieceContenuVue.addTrucAtIndex(t.getTypeTruc(),t.getDescription(),i);
	}
}