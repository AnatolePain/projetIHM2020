import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CasePieceEvent implements MouseListener 
{
	private CasePiece caseP;

	public CasePieceEvent(CasePiece cp)
	{
		this.caseP = cp;
	}

	@Override public void mouseClicked(MouseEvent evenement){}
	@Override public void mouseEntered(MouseEvent evenement)
	{
		this.caseP.setSelect(true);
	}
	@Override public void mouseExited(MouseEvent evenement)
	{
		this.caseP.setSelect(false);
	}
	@Override public void mousePressed(MouseEvent evenement)
	{
		if (evenement.getButton() == MouseEvent.BUTTON3) 
		{
			this.caseP.GetPopupMenu().show(evenement.getComponent(), evenement.getX(), evenement.getY() );
		}
	}
	@Override public void mouseReleased(MouseEvent evenement){}
}