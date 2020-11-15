package fr.iutfbleau.projetIHM2020FI2.CONTROLEUR;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.util.*;

public class MiniCarteController 
{
	private MiniCarteVue miniCarte;
	private Joueur j;
	private Piece fp;
	private Piece joueurPos;
	private List<Piece> isdraw = new ArrayList<Piece>();
	private int countStopDebug = 0;
	
	private int newPosX, newPosY;
	private Piece newPiece;

	public MiniCarteController()
	{
		this.miniCarte = MiniCarteVue.getMinicarteVue();
		this.j = SetupModel.getJoueur(); 
		this.fp = SetupModel.getFirstPiece();
		this.joueurPos = this.j.getPiece();
		this.newPiece = null;

		if(!this.j.isVisited(this.fp)){
			this.miniCarte.modifCellule(3,4,3);
			DrawCarteFound(this.fp,3,4);
			this.isdraw.clear();
			DrawCarte(this.newPiece,this.newPosX,newPosY);
		}else{
			DrawCarte(this.fp,3,4);
		}
	}

	//Cherche les pièces visitées récursibement et les affiche
	private void DrawCarte(Piece p,int posX,int posY)
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

	//Cherche la pièce du joeur récursivement
	private void DrawCarteFound(Piece p,int posX,int posY){

		if(p == null || isdraw.contains(p))
		{
			return;
		}
		this.isdraw.add(p);
		if(this.joueurPos == p){
			this.newPosX = posX;
			this.newPosY = posY;
			this.newPiece = p;
			this.miniCarte.setJoueurPos(posX,posY);
			this.miniCarte.modifCellule(posX,posY,1);
		}else{
			for (Direction dir : Direction.values()){

				Passage pass = p.getPassage(dir);
				if(pass != null)
				{
					if(dir ==  Direction.NORD){
						DrawCarteFound(pass.getAutrePiece(p),posX,(posY-2));
					}else if(dir ==  Direction.SUD){					
						DrawCarteFound(pass.getAutrePiece(p),posX,(posY+2));
					}else if(dir ==  Direction.OUEST){
						DrawCarteFound(pass.getAutrePiece(p),(posX-2),posY);
					}else if(dir ==  Direction.EST){
						DrawCarteFound(pass.getAutrePiece(p),(posX+2),posY);
					}
				}

			}
		}

	}
}