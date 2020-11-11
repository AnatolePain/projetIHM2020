package fr.iutfbleau.projetIHM2020FI2.CONTROLEUR;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import fr.iutfbleau.projetIHM2020FI2.MODEL.*;
import fr.iutfbleau.projetIHM2020FI2.MNP.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class PieceController 
{
	private CasePieceEvent[] casePEvent;
	private static CasePiece[] caseP;
	private PieceContenuEvent[] ramasserEvent;
	private static DescriptionEvent[] descriptionEvent;
	
	//model
	private Joueur jP;
	private Piece piecePos;
	private static Map<CasePiece, Truc> mapCasePieceTruc = new HashMap<CasePiece, Truc>();

	public PieceController()
	{
		this.casePEvent = new CasePieceEvent[PieceContenuVue.getNbCase()];
		this.ramasserEvent = new PieceContenuEvent[PieceContenuVue.getNbCase()];
		PieceController.descriptionEvent = new DescriptionEvent[PieceContenuVue.getNbCase()];
		PieceController.caseP = PieceContenuVue.getCasePiecetab();
		this.jP = SetupModel.getJoueur();

		Iterator<Truc> pT = jP.getPiece().getTrucs();

		for(int i = 0 ; i < this.casePEvent.length;i++)
		{
			this.casePEvent[i] = new CasePieceEvent(PieceController.caseP[i]);
			PieceController.caseP[i].addMouseListener(this.casePEvent[i]);

			//Ramasser
			this.ramasserEvent[i] = new PieceContenuEvent(PieceController.caseP[i]);
			PieceController.caseP[i].GetPopupMenu().getMenuItemRamasser().addActionListener(this.ramasserEvent[i]);

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