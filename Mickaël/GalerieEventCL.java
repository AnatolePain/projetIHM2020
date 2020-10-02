import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GalerieEventCL implements MouseListener 
{
    private GalerieChangementCL galerie;
    private JFrame fenetre;

	public GalerieEventCL(GalerieChangementCL gc,JFrame jf)
	{
        this.galerie = gc;
        this.fenetre = jf;
	}

    public void mouseClicked(MouseEvent evenement)
    {
        this.galerie.changementThread(evenement.getX() > this.fenetre.getWidth()/2);
    }

    public void mouseEntered(MouseEvent evenement){}
    public void mouseExited(MouseEvent evenement){}
    public void mousePressed(MouseEvent evenement){}
    public void mouseReleased(MouseEvent evenement){}
}