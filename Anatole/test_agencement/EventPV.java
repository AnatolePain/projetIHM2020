import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EventPV implements MouseListener 
{
    private PieceVue pieceV;
	private int[] indice = new int[4];

	public EventPV(PieceVue p)
	{
        this.pieceV = p;
	}
	
    public void mouseClicked(MouseEvent evenement)
    {
		if(evenement.getX() > this.pieceV.getWidth()/3 && evenement.getX() < (this.pieceV.getWidth()/3)*2)
		{
			for(int i = 0; i < indice.length;i++)
			{
				indice[i] = (int)(Math.random()*100)%2;
			}
			this.pieceV.transition(indice);
		}
		else
		{
			this.pieceV.changementThread(evenement.getX() > this.pieceV.getWidth()/2);
		}
    }

    public void mouseEntered(MouseEvent evenement){}
    public void mouseExited(MouseEvent evenement){}
    public void mousePressed(MouseEvent evenement){}
    public void mouseReleased(MouseEvent evenement){}
}