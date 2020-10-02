import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GalerieChangementCL
{
	private JFrame fenetre;
	private GalerieEventCL mouse;
	private CardLayout cardl;
	private JPanel galeriePanel = new JPanel();
	private int indice = 0;
	private GalerieThread[] gt =  new GalerieThread[4];
	private Thread thread;

	public GalerieChangementCL(JFrame jf)
	{
		this.fenetre = jf;
		this.cardl = new CardLayout();
		this.galeriePanel.setLayout(this.cardl);
		for(int i = 0; i <= 15;i++)
		{
			this.galeriePanel.add(new JLabel(new ImageIcon("../ProjetIHM/res/images/Salles/image"+i+".png")),"image"+i);
		}
		fenetre.add(this.galeriePanel,BorderLayout.CENTER);
		mouse = new GalerieEventCL(this,jf);
		fenetre.addMouseListener(mouse);
	}

	public void changementThread(boolean state)
    {
		for(int i = 0;i < 4;i++)
		{
			gt[i] = new GalerieThread(this,state,((long)i+1)*100);
			thread =  new Thread(gt[i]);
			thread.start();
		}		
    }

	public void changement(boolean state)
    {
		if(state)
		{
			this.cardl.next(this.galeriePanel);
		}
		else
		{
			this.cardl.previous(this.galeriePanel);
		}
    }
}