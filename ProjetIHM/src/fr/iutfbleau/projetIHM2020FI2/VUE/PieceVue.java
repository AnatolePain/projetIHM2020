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
		this.cardl.next(this);
		this.cardl.next(this);

		this.reCreate();
	}

	public JPanel getPanel()
	{
		return this;
	}

	public void changementThread(boolean state)
    {
		if(!inAnimChange)
		{
			Thread thread = new Thread(new ChangementThread(this,state,100,4));
			thread.start();
		}
    }

	public boolean inAnimation()
	{
		return this.inAnimChange || this.inTransition;
	}

	public void setAnimChange(boolean b)
	{
		this.inAnimChange = b;
	}

	public void setTransition(boolean b)
	{
		this.inTransition = b;
	}

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

	public void transition(int dir[],int dirO[])//Fait le fondu, a besion de la direction pour savoir quelle salle changer 
	{
		if(!inTransition)
		{
			direction = dir;
			directionOuvert = dirO;
			Thread thread = new Thread(new TransitionThread(swapChain[indice],threadTSpeed,this));
			thread.start();
		}
	}

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

		for(int i = 0; i < 16;i++)
		{
			swapChain[i].repaint();
		}
	}

	private void bloquer(int passage)
	{
		for(int i = 0; i < 5;i++)
		{
			this.swapChain[(i+(passage*4))%16].addImage(rocher[i]);
		}
	}

	public void reCreateOuvert(int dirO[])
	{
		this.directionOuvert = dirO;
		reCreate();
	}

	public void ouvert(int passage)
	{
		for(int i = 0; i < 5;i++)
		{
			this.swapChain[(i+(passage*4))%16].addImage(ouvert[i]);
		}
	}

	public static PieceVue getPieceVue()
	{
		return PieceVue.pthis;
	}
}