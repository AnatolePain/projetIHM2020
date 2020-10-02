import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ComfirmationEvent implements WindowListener 
{
	private JFrame fenetre;
	private JDialog jd;
	private JButton boutonOUI = new JButton ("oui");  
	private JButton boutonNON = new JButton ("non");  
	
	public ComfirmationEvent (JFrame jf)
	{
		this.fenetre = jf;
		this.jd =  new JDialog(jf , "Dialog Example", true); 
		this.jd.setLayout(new FlowLayout());  
		this.jd.add(new JLabel("Etes vous sure de vouloir quitter le programme."));
		this.jd.setSize(400,100); 
		this.boutonOUI.addActionListener(new EventBoutonQuit(true,this));
		this.boutonNON.addActionListener(new EventBoutonQuit(false,this));
		this.jd.add(this.boutonOUI);
		this.jd.add(this.boutonNON);
	}

	public void quit()
	{
		this.fenetre.dispose();
	}
	public void stay()
	{
		this.jd.setVisible(false); 
		this.fenetre.setVisible(true); 
	}

	public void	windowActivated(WindowEvent e){}
	public void	windowClosed(WindowEvent e){}
	public void	windowClosing(WindowEvent e)
	{
		this.jd.setLocation(this.fenetre.getX()+this.fenetre.getHeight()/10,this.fenetre.getY()+this.fenetre.getWidth()/3);
		this.jd.setVisible(true);  
	}
	public void	windowDeactivated(WindowEvent e){}
	public void	windowDeiconified(WindowEvent e){}
	public void	windowIconified(WindowEvent e){}
	public void	windowOpened(WindowEvent e){}
}