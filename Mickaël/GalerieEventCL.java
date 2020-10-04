import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GalerieEventCL implements MouseListener 
{
    private PieceVue galerie;
    private JFrame fenetre;

	public GalerieEventCL(PieceVue gc,JFrame jf)
	{
        this.galerie = gc;
        this.fenetre = jf;
	}
	
    public void mouseClicked(MouseEvent evenement)
    {
		if(evenement.getX() > this.fenetre.getWidth()/3 && evenement.getX() < (this.fenetre.getWidth()/3)*2)
		{
			this.galerie.transition();
		}
		else
		{
			this.galerie.changementThread(evenement.getX() > this.fenetre.getWidth()/2);
		}
    }

    public void mouseEntered(MouseEvent evenement){}
    public void mouseExited(MouseEvent evenement){}
    public void mousePressed(MouseEvent evenement){}
    public void mouseReleased(MouseEvent evenement){}
}