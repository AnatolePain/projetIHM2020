package fr.iutfbleau.projetIHM2020FI2.CONTROLEUR;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EventPV implements MouseListener 
{
    private PieceVue pieceV;
	private int[] indice = new int[4];//tableaux aléatore qui permet de savoir si une pièce est bloqué ou non (à modifier)

	public EventPV(PieceVue p)
	{
        this.pieceV = p;
	}
	
    public void mouseClicked(MouseEvent evenement)
    {
		if(evenement.getX() > this.pieceV.getWidth()/3 && evenement.getX() < (this.pieceV.getWidth()/3)*2)//clique milieu donc fondu 
		{
			for(int i = 0; i < indice.length;i++) //place les cailloux en random
			{
				indice[i] = (int)(Math.random()*100)%2;//remplis le tableaux de valeur aléatore : 0 pièce bloqué , 1 pièce accéssible 
			}
			this.pieceV.transition(indice);//gère le fondu et change de salle au milieu de ce fondu 
		}
		else//clique droite ou gauche 
		{
			//if(agir == n)
			//------------------------------------------------------------------------//Change Modèle
			this.pieceV.changementThread(evenement.getX() > this.pieceV.getWidth()/2);//change Vue Image
			//------------------------------------------------------------------------//Change Vue carte
		}
    }

    public void mouseEntered(MouseEvent evenement){}
    public void mouseExited(MouseEvent evenement){}
    public void mousePressed(MouseEvent evenement){}
    public void mouseReleased(MouseEvent evenement){}
}