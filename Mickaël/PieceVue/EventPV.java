import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EventPV implements MouseListener 
{
    private PieceVue pieceV;
	private JFrame fenetre;
	private int[] indice = new int[4];

	public EventPV(PieceVue p,JFrame jf)
	{
        this.pieceV = p;
		this.fenetre = jf;
	}
	
    public void mouseClicked(MouseEvent evenement)
    {
		if(evenement.getX() > this.fenetre.getWidth()/3 && evenement.getX() < (this.fenetre.getWidth()/3)*2)
		{
			for(int i = 0; i < indice.length;i++)
			{
				indice[i] = (int)(Math.random()*100)%2;
			}
			this.pieceV.transition(indice);
		}
		else
		{
			this.pieceV.changementThread(evenement.getX() > this.fenetre.getWidth()/2);
		}
    }

    public void mouseEntered(MouseEvent evenement){}
    public void mouseExited(MouseEvent evenement){}
    public void mousePressed(MouseEvent evenement){}
    public void mouseReleased(MouseEvent evenement){}
}