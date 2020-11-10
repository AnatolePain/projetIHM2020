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
	private int directionO[] = new int[4];

	//Model
	Piece pieceJoueur;
	Passage directionJoueur;
	Piece autrePiece;//DialogDescription.showD("test");
	Passage autrePassage;
	EtatPassage etatPassage;

	public EventPV(PieceVue pv,MiniCarteVue mcv)
	{
		this.jP = SetupModel.getJoueur();
		this.pieceV = pv;
		this.miniCarteV = mcv;

		this.pieceJoueur = this.jP.getPiece();
		for(int i = 0; i < 4;i++)					
		{
			this.autrePassage = pieceJoueur.getPassage(Direction.values()[i]);
			this.direction[i] = this.autrePassage == null ? 1 : 0;
			this.directionO[i] = this.autrePassage == null ? 0 : this.autrePassage.getEtatPassage() == EtatPassage.OPEN ? 1 : 0;
		}
		this.pieceV.transition(this.direction,this.directionO);
	}
	
	@Override
    public void mouseClicked(MouseEvent evenement)
    {
		if(evenement.getButton() == MouseEvent.BUTTON1)
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
					
						if(this.autrePiece != null)
						{
							this.etatPassage = this.directionJoueur.getEtatPassage();
							if(this.etatPassage == EtatPassage.OPEN)//true a enlever le click droit a coder
							{
								for(int i = 0; i < 4;i++)					
								{
									this.autrePassage = autrePiece.getPassage(Direction.values()[i]);
									this.direction[i] = this.autrePassage == null ? 1 : 0;
									this.directionO[i] = this.autrePassage == null ? 0 : this.autrePassage.getEtatPassage() == EtatPassage.OPEN ? 1 : 0;
								}
								this.jP.setPiece(this.autrePiece);
								this.miniCarteV.move(Direction.values()[numDirection]);
								this.pieceV.transition(this.direction,this.directionO);
								this.miniCarteV.affichePassage(this.direction);
								PieceController.changePiece(this.autrePiece);
							}
							else if(this.etatPassage == EtatPassage.CLOSED)
							{
								DialogDescription.showD("Porte","La porte " + Direction.values()[this.numDirection].toString() + " est fermer");
							}
							else if(this.etatPassage == EtatPassage.LOCKED)
							{
								DialogDescription.showD("Porte","La porte " + Direction.values()[this.numDirection].toString() + " est fermer a cle");
							}
						}
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
		else if(evenement.getButton() == MouseEvent.BUTTON3)
		{
			this.pieceJoueur = this.jP.getPiece();
			this.directionJoueur = this.pieceJoueur.getPassage(Direction.values()[numDirection]);
			if(this.directionJoueur != null)				
			{
				this.etatPassage = this.directionJoueur.getEtatPassage();
				if(this.etatPassage == EtatPassage.OPEN)
				{
					this.directionJoueur.setEtatPassage(EtatPassage.CLOSED);
					this.directionO[this.numDirection] = 0;
					this.pieceV.reCreateOuvert(this.directionO);
					DialogDescription.showD("Porte","la porte " + Direction.values()[this.numDirection].toString() + " est maintenant fermer");
				}
				else if(this.etatPassage == EtatPassage.CLOSED)
				{
					this.directionJoueur.setEtatPassage(EtatPassage.OPEN);
					this.directionO[this.numDirection] = 1;
					this.pieceV.reCreateOuvert(this.directionO);
					DialogDescription.showD("Porte","la porte " + Direction.values()[this.numDirection].toString() + " est maintenant ouverte");
				}
				else if(this.etatPassage == EtatPassage.LOCKED)
				{

				}
			}
			else
			{
				DialogDescription.showD("Porte","le passage " + Direction.values()[this.numDirection].toString() + " est bloque par un rocher");
			}
			
		}
    }

    public void mouseEntered(MouseEvent evenement){}
    public void mouseExited(MouseEvent evenement){}
    public void mousePressed(MouseEvent evenement){}
    public void mouseReleased(MouseEvent evenement){}
}