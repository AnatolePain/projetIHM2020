package fr.iutfbleau.projetIHM2020FI2.VUE;
import fr.iutfbleau.projetIHM2020FI2.CONTROLEUR.*;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
import javax.swing.*;
import java.awt.*;

public class PieceVue extends JPanel
{
	private ImageDonjon[] swapChain = new ImageDonjon[16];
	private ImageIcon[] rocher = new ImageIcon[5];
	private ImageIcon[] ouvert = new ImageIcon[5];
	private CardLayout cardl;
	private int indice = 2;
	private final float threadTSpeed = 0.8f;
	private final float threadCWait = 0.4f;
	private boolean inAnimChange = false;
	private boolean inTransition = false;
	private static PieceVue pthis;
	private int direction[] = new int[4];
	private int directionOuvert[] = new int[4];

	private ImageIcon[] modeDaltonien = new ImageIcon[4];
	private boolean daltonien = false;

	/**
	 * constructeur
	 * Chargement des images de la pièce
	 */
	public PieceVue()
	{
		PieceVue.pthis = this;
		this.cardl = new CardLayout();
		this.setLayout(this.cardl);
		for(int i = 0; i < 16;i++)
		{
			swapChain[i] = new ImageDonjon(ImageClassLoader.getImage("images/Salles/image"+i+".png"));
			this.add(swapChain[i],"image"+i);
		}
		for(int i = 0; i < 5;i++)
		{
			rocher[i] = ImageClassLoader.getImage("images/Salles/fermer"+i+".png");
			ouvert[i] = ImageClassLoader.getImage("images/Salles/ouvert"+i+".png");
		}

		for(int i = 0 ; i < 4 ; i++){
			modeDaltonien[i] = ImageClassLoader.getImage("images/Salles/daltonien"+i+".png");
		}

		this.cardl.next(this);
		this.cardl.next(this);

		this.reCreate();
	}
	/**
     * @return on get le JPanel de cette class
     */
	public JPanel getPanel()
	{
		return this;
	}

	/**
     * Permets de tourner dans la pièce à gauche ou à droite,
	 * On lance un Thread pour ne pas bloquer le processus qui s'occupe de changer le CardLayout
     * @param  state Gauche ou droite
     */
	public void changementThread(boolean state)
    {
		if(!inAnimChange)
		{
			Thread thread = new Thread(new ChangementThread(this,state,100,4));
			thread.start();
		}
    }

	/**
     * Permets de savoir si on est en cours d'animation pour éviter d'en lancer une autre
	 * inAnimChange et inTransition sont des booleans modifier par changementThread et TransitionThread
     * @return en animation ?
     */
	public boolean inAnimation()
	{
		return this.inAnimChange || this.inTransition;
	}

	/**
     * @param  b change l'etat
     */
	public void setAnimChange(boolean b)
	{
		this.inAnimChange = b;
	}

	/**
     * @param  b change l'etat
     */
	public void setTransition(boolean b)
	{
		this.inTransition = b;
	}

	/**
     * @param  state  changement gauche ou droite d'une image il y en a 16 en tous
     */
	public void changement(boolean state)
    {
		if(state)
		{
			this.cardl.next(this);
			indice = ++indice%16;
		}
		else
		{
			this.cardl.previous(this);
			indice = --indice%16;
			if(indice < 0)
			{
				indice = 15;
			}
		}
    }

	/**
	 * Fait le fondu, a besoin de la direction pour savoir quelle salle changer 
     * @param dir passage bloquer taille = nombre de passage
	 * @param dirO passage ouvert taille = nombre de passage
     */
	public void transition(int dir[],int dirO[])
	{
		if(!inTransition)
		{
			direction = dir;
			directionOuvert = dirO;
			Thread thread = new Thread(new TransitionThread(swapChain[indice],threadTSpeed,this));
			thread.start();
		}
	}

	/**
	 * Repaint de toute les images du donjon
     */
	public void reCreate()//test a modifier apres
	{
		for(int i = 0; i < 16;i++)
		{
			swapChain[i].clearImagePlus(); //enlève toute les pierres sur les images 
		}

		for(int i = 0; i < this.direction.length;i++)
		{
		 	if(direction[i] > 0)
		 	{
		 		bloquer(i);//mets les images des nouvelles pierres au bonne endroit celon le tableaux des pièces bloqués
			}
		}

		for(int i = 0; i < this.directionOuvert.length;i++)
		{
		 	if(directionOuvert[i] > 0)
		 	{
		 		ouvert(i);
			}
		}

		if(daltonien){

			for(int i = 0 ; i < this.direction.length ; i++){
				this.swapChain[2 + (i*4) ].addImage(modeDaltonien[i]);
			}

		}

		for(int i = 0; i < 16;i++)
		{
			swapChain[i].repaint();
		}
	}

	/**
	*Bloque le passage 0,1,2,3
	*/
	private void bloquer(int passage)
	{
		for(int i = 0; i < 5;i++)
		{
			this.swapChain[(i+(passage*4))%16].addImage(rocher[i]);
		}
	}

	/**
	*Ouvre le passage
	*@param dirO un tableaux de taille 4 qui contient les passage ouvert de la salle
	*/
	public void reCreateOuvert(int dirO[])
	{
		this.directionOuvert = dirO;
		reCreate();
	}

	/**
	* Ouvre le passage 0,1,2,3
	*/
	public void ouvert(int passage)
	{
		for(int i = 0; i < 5;i++)
		{
			this.swapChain[(i+(passage*4))%16].addImage(ouvert[i]);
		}
	}

	/**
	* get cette element
	* @return this
	*/
	public static PieceVue getPieceVue()
	{
		return PieceVue.pthis;
	}

	/*
	*Active ou desactive le mode Daltonien	
	*/
	public void onOffDaltonien(){
		if(this.daltonien){
			this.daltonien = false;
		}else{
			this.daltonien = true;
		}
		reCreate();
	}
}