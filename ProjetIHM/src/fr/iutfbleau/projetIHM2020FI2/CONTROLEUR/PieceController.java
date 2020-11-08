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
	private CasePiece[] caseP;
	//model
	private Joueur jP;
	private Piece piecePos;

	public PieceController()
	{
		this.casePEvent = new CasePieceEvent[PieceContenuVue.getNbCase()];
		this.caseP = PieceContenuVue.getCasePiecetab();
		this.jP = SetupModel.getJoueur();

		for(int i = 0 ; i < this.casePEvent.length;i++)
		{
			this.casePEvent[i] = new CasePieceEvent(this.caseP[i]);
			this.caseP[i].addMouseListener(this.casePEvent[i]);
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
			while(pT.hasNext())
			{
				Truc t = pT.next();
				PieceContenuVue.addTruc(t.getTypeTruc(),t.getDescription());
			}
		}
	}
}