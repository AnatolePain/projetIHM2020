package fr.iutfbleau.projetIHM2020FI2.VUE;
import fr.iutfbleau.projetIHM2020FI2.CONTROLEUR.*;
import javax.swing.*;
import java.awt.*;

public class PieceVue extends JPanel
{
	private ImageDonjon[] swapChain = new ImageDonjon[16];
	private ImageIcon[] rocher = new ImageIcon[5];
	private CardLayout cardl;
	private int indice = 2;
	private Thread thread;
	private float threadTSpeed = 0.8f;
	private float threadCWait = 0.4f;
	private boolean inAnimChange = false;
	private boolean inTransition = false;
	private EventPV mouse;

	public PieceVue()
	{
		mouse = new EventPV(this);
		this.addMouseListener(mouse);

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
		}
		this.cardl.next(this);
		this.cardl.next(this);
	}

	public JPanel getPanel()
	{
		return this;
	}

	public void changementThread(boolean state)
    {
		if(!inAnimChange)
		{
			thread = new Thread(new ChangementThread(this,state,100,4));
			thread.start();
		}
    }

	public boolean inAnimation()
	{
		return this.inAnimChange && this.inTransition;
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

	public void transition(int[] direction)//Fait le fondu, a besion de la direction pour savoir quelle salle changer 
	{
		if(!inTransition)
		{
			thread = new Thread(new TransitionThread(swapChain[indice],threadTSpeed,this,direction));
			thread.start();
		}
	}

	public void reCreate(int[] direction)//test a modifier apres
	{
		for(int i = 0; i < 16;i++)
		{
			swapChain[i].clearImagePlus(); //enlève toute les pierres sur les images 
		}

		for(int i = 0; i < direction.length;i++)
		{
			if(direction[i] > 0)
			{
				bloquer(i);//mets les images des nouvelles pierres au bonne endroit celon le tableaux des pièces bloqués
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
}