package fr.iutfbleau.projetIHM2020FI2.CONTROLEUR;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.util.*;

public class MiniCarteEvent 
{
	private MiniCarteVue miniCarte;
	private Joueur j;
	private Piece fp;
	private Piece joueurPos;
	private List<Piece> isdraw = new ArrayList<Piece>();
	private int countStopDebug = 0;

	public MiniCarteEvent()
	{
		this.miniCarte = MiniCarteVue.getMinicarteVue();
		this.j = SetupModel.getJoueur(); 
		this.fp = SetupModel.getFirstPiece();
		this.joueurPos = this.j.getPiece();
		DrawCarte(this.fp,3,4);
	}

	private void DrawCarte(Piece p,int posX,int posY)//methode pas adapter de MiniCarteVue probleme
	{	
		if(p == null || isdraw.contains(p))
		{
			return;
		}
		isdraw.add(p);
		if(this.j.isVisited(p))
		{
			if(this.joueurPos == p)
			{
				miniCarte.modifCellule(posX,posY,1);
				miniCarte.setJoueurPos(posX,posY);
			}
			else
			{
				miniCarte.modifCellule(posX,posY,2);
			}
			for (Direction dir : Direction.values())
			{
				Passage pass = p.getPassage(dir);
				//System.out.println(dir.toString());
				if(pass != null)
				{
					if(dir ==  Direction.NORD){
						miniCarte.modifCellule(posX,(posY-1),5);
						DrawCarte(pass.getAutrePiece(p),posX,(posY-2));
					}else if(dir ==  Direction.SUD){
						miniCarte.modifCellule(posX,(posY+1),5);						
						DrawCarte(pass.getAutrePiece(p),posX,(posY+2));
					}else if(dir ==  Direction.OUEST){
						miniCarte.modifCellule((posX-1),posY,4);
						DrawCarte(pass.getAutrePiece(p),(posX-2),posY);
					}else if(dir ==  Direction.EST){
						miniCarte.modifCellule((posX+1),posY,4);
						DrawCarte(pass.getAutrePiece(p),(posX+2),posY);
					}
				}
			}
		}

	}
}