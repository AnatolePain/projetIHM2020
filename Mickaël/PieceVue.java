import javax.swing.*;
import java.awt.*;

public class PieceVue
{
	private JPanel galeriePanel = new JPanel();
	private ImageDonjon[] swapChain = new ImageDonjon[16];
	private CardLayout cardl;
	private int indice = 2;
	private Thread thread;

	public PieceVue()
	{
		this.cardl = new CardLayout();
		this.galeriePanel.setLayout(this.cardl);
		for(int i = 0; i < 16;i++)
		{
			swapChain[i] = new ImageDonjon(new ImageIcon("../ProjetIHM/res/images/Salles/image"+i+".png"));
			this.galeriePanel.add(swapChain[i],"image"+i);
		}
		this.cardl.next(this.galeriePanel);
		this.cardl.next(this.galeriePanel);
		bloquer(0);
		bloquer(2);
	}

	public JPanel getPanel()
	{
		return this.galeriePanel;
	}

	public void changementThread(boolean state)
    {
		thread = new Thread(new GalerieThread(this,state,100,4));
		thread.start();
    }

	public void changement(boolean state)
    {
		if(state)
		{
			this.cardl.next(this.galeriePanel);
			indice = ++indice%16;
		}
		else
		{
			this.cardl.previous(this.galeriePanel);
			indice = --indice%16;
			if(indice < 0)
			{
				indice = 15;
			}
		}
    }

	public void transition()
	{
		thread = new Thread(new TransitionThread(swapChain[indice],5));
		thread.start();
	}

	private void bloquer(int passage)
	{
		for(int i = 0; i < 5;i++)
		{
			this.swapChain[(i+(passage*4))%16].addImage(new ImageIcon("../ProjetIHM/res/images/Salles/fermer"+i+".png"));
		}
	}
}