package fr.iutfbleau.projetIHM2020FI2.CONTROLEUR;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EventPV implements MouseListener 
{
    private PieceVue pieceV;
	private int[] indice = new int[4];//tableaux aléatore qui permet de savoir si une pièce est bloqué ou non (à modifier)
	private int saveNumDirection;
	private Direction saveDirection;
	private MiniCarteVue miniCarte;
	// private Passage passageChoisie;
	private Joueur joueurPrincipal;

	public EventPV(PieceVue pv, Joueur j, MiniCarteVue mcv/*, Passage p*/)
	{
        this.pieceV = pv;
        this.saveNumDirection = 0;
		this.joueurPrincipal = j;
		this.saveDirection = Direction.NORD;
		this.miniCarte = mcv;
		// this.passageChoisie = p;
	}
	
    public void mouseClicked(MouseEvent evenement)
    {
		if(evenement.getX() > this.pieceV.getWidth()/3 && evenement.getX() < (this.pieceV.getWidth()/3)*2)//clique milieu donc fondu 
		{
			// for(int i = 0; i < indice.length;i++) //place les cailloux en random
			// {
			// 	indice[i] = (int)(Math.random()*100)%2;//remplis le tableaux de valeur aléatore : 0 pièce bloqué , 1 pièce accéssible 
			// }
			// for (Direction dir : Direction.values()) {
			// 	//System.out.println(dir);
			// }

			Passage passageChoisie = this.joueurPrincipal.getPiece().getPassage(saveDirection);
			if (passageChoisie != null){ //si le passage choisie existe 
				this.joueurPrincipal.addVisited(joueurPrincipal.getPiece());//la pièce est maintenant visité
				this.joueurPrincipal.setPiece(passageChoisie.getAutrePiece(joueurPrincipal.getPiece()));//change la pièce dans le modèle (=déplacement du joueur)
				this.miniCarte.move(saveDirection);//déplacement dans la mini-carte 
				if( !(joueurPrincipal.isVisited(this.joueurPrincipal.getPiece())) ){ //si la pièce n'a pas déjà été visité on affiche les passages
					this.miniCarte.affichePassage(this.joueurPrincipal.getPiece());
				}
				this.pieceV.transition(/*indice*/);//gère le fondu et change de salle au milieu de ce fondu 
			}else{
				System.out.println("le passage est n'éxiste pas ");
			};

		}		//déplacement

		else//clique droite ou gauche 
		{

			//Change le Modèle
			Boolean droiteOuGauche = (evenement.getX() > this.pieceV.getWidth()/2);
			if(droiteOuGauche){
				if(this.saveNumDirection == Direction.values().length - 1){
					this.saveNumDirection = 0;
				}else{
					this.saveNumDirection++;
				}
				this.saveDirection = Direction.values()[this.saveNumDirection];
			}else{
				if(this.saveNumDirection == 0){
					this.saveNumDirection = 3;
				}else{
					this.saveNumDirection--;
				}
				this.saveDirection = Direction.values()[this.saveNumDirection];
			}

			this.pieceV.changementThread(droiteOuGauche);//change la Vue direction
		}
    }

    public void mouseEntered(MouseEvent evenement){}
    public void mouseExited(MouseEvent evenement){}
    public void mousePressed(MouseEvent evenement){}
    public void mouseReleased(MouseEvent evenement){}
}