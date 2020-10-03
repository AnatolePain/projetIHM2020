import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GalerieChangementCL
{
	private JFrame fenetre;
	private GalerieEventCL mouse;
	private CardLayout cardl;
	private JPanel galeriePanel = new JPanel();
	private ImageDonjon[] imageD = new ImageDonjon[16];
	private int indice = 0;
	private GalerieThread[] gt =  new GalerieThread[4];
	private Thread thread;

	public GalerieChangementCL(JFrame jf)
	{
		this.fenetre = jf;
		this.cardl = new CardLayout();
		this.galeriePanel.setLayout(this.cardl);
		for(int i = 0; i < 16;i++)
		{
			imageD[i] = new ImageDonjon(new ImageIcon("../ProjetIHM/res/images/Salles/image"+i+".png"),new ImageIcon("../ProjetIHM/res/images/Salles/fermer"+((i+2)%4)+".png"));
			this.galeriePanel.add(imageD[i],"image"+i);
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
			thread = new Thread(gt[i]);
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