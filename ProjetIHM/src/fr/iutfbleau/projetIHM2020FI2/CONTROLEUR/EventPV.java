package fr.iutfbleau.projetIHM2020FI2.CONTROLEUR;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class EventPV implements MouseListener 
{

	private PieceVue pieceV;

	public EventPV(PieceVue pv)
	{
		this.pieceV = pv;
	}
	
	@Override
    public void mouseClicked(MouseEvent evenement)
    {
		if(evenement.getButton() == MouseEvent.BUTTON1)
		{
			if(!this.pieceV.inAnimation()){

				//clique gauche au milieu =  setPiece si c'est possible 
				if(evenement.getX() > this.pieceV.getWidth()/3 && evenement.getX() < (this.pieceV.getWidth()/3)*2)
				{
					PieceVueController.setPieceController();
				}
				//clique à droite
				else if(evenement.getX() > this.pieceV.getWidth()/2)
				{
					PieceVueController.orientationADroite();
				}
				//clique à gauche
				else
				{
					PieceVueController.orientationAGauche();
				}
			}
		}
		// clique droit = agir avec rien sur un passage
		else if(evenement.getButton() == MouseEvent.BUTTON3) 
		{
			PieceVueController.agirNull();	
		}
    }

    public void mouseEntered(MouseEvent evenement){}
    public void mouseExited(MouseEvent evenement){}
    public void mousePressed(MouseEvent evenement){}
    public void mouseReleased(MouseEvent evenement){}
}