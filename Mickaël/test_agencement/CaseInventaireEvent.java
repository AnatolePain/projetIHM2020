import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CaseInventaireEvent implements MouseListener 
{
	private CaseInventaire caseI;
	private InventaireMenuContextuel pm;

	public CaseInventaireEvent(CaseInventaire cp)
	{
		this.caseI = cp;
	}

	@Override public void mouseClicked(MouseEvent evenement){}
	@Override public void mouseEntered(MouseEvent evenement)
	{
		this.caseI.setSelect(true);
	}
	@Override public void mouseExited(MouseEvent evenement)
	{
		this.caseI.setSelect(false);
	}
	@Override public void mousePressed(MouseEvent evenement)
	{
		if (evenement.getButton() == MouseEvent.BUTTON3) 
		{
			this.pm = this.caseI.GetPopupMenu();
			this.pm.show(evenement.getComponent(), evenement.getX(), evenement.getY() );
		}
	}
	@Override public void mouseReleased(MouseEvent evenement){}
}