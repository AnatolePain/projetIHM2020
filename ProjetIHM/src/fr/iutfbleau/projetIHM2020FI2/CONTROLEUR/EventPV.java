package fr.iutfbleau.projetIHM2020FI2.CONTROLEUR;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class EventPV implements MouseListener 
{
	private int numDirection = 0;
	private PieceVue pieceV;
	private MiniCarteVue miniCarteV;
	private Joueur jP;
	private int direction[] = new int[4];

	//Model
	Piece pieceJoueur;
	Passage directionJoueur;
	Piece autrePiece;

	public EventPV(PieceVue pv,MiniCarteVue mcv)
	{
		this.jP = SetupModel.getJoueur();
		this.pieceV = pv;
		this.miniCarteV = mcv;

		this.pieceJoueur = this.jP.getPiece();
		for(int i = 0; i < 4;i++)					
		{
			this.direction[i] = pieceJoueur.getPassage(Direction.values()[i]) == null ? 1 : 0;
		}
		this.pieceV.transition(this.direction);
	}
	
	@Override
    public void mouseClicked(MouseEvent evenement)
    {
		if(!this.pieceV.inAnimation())
		{
			if(evenement.getX() > this.pieceV.getWidth()/3 && evenement.getX() < (this.pieceV.getWidth()/3)*2)//clique milieu donc fondu 
			{
				this.pieceJoueur = this.jP.getPiece();
				this.directionJoueur = this.pieceJoueur.getPassage(Direction.values()[numDirection]);
				this.autrePiece = null;
				if(this.directionJoueur != null)				
				{
					this.autrePiece = this.directionJoueur.getAutrePiece(this.pieceJoueur);
				}
				if(this.autrePiece != null)
				{
					for(int i = 0; i < 4;i++)					
					{
						this.direction[i] = autrePiece.getPassage(Direction.values()[i]) == null ? 1 : 0;
					}
					this.jP.setPiece(this.autrePiece);
					this.miniCarteV.move(Direction.values()[numDirection]);
					this.pieceV.transition(this.direction);
					this.miniCarteV.affichePassage(this.direction);
					PieceController.changePiece(this.autrePiece);
				}
			}
			else if(evenement.getX() > this.pieceV.getWidth()/2)//droite
			{
				numDirection = (numDirection+1)%4;
				this.pieceV.changementThread(true);
			}
			else
			{
				if(--numDirection < 0)
				{
					numDirection = 3;
				}
				this.pieceV.changementThread(false);
			}
		}
    }

    public void mouseEntered(MouseEvent evenement){}
    public void mouseExited(MouseEvent evenement){}
    public void mousePressed(MouseEvent evenement){}
    public void mouseReleased(MouseEvent evenement){}
}