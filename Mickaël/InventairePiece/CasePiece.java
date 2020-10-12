import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CasePiece extends JComponent implements MouseListener 
{
	private ImageIcon imageBase;
	private ImageIcon imageSlect;
	private ImageIcon object; 
	private boolean select = false;
	private Map<TypeTruc, ImageIcon> itemImage;
	private JPopupMenu popupMenu;  

	public CasePiece(ImageIcon b, ImageIcon s, Map<TypeTruc, ImageIcon> iI)
	{
		this.imageBase = b;
		this.imageSlect = s;
		this.itemImage = iI;
		this.addMouseListener(this);
		this.popupMenu = this.createPopupMenu();
	}

	public void setObject(TypeTruc tt,String description)
	{
		this.object = this.itemImage.get(tt);
		this.setToolTipText(description);
		repaint();
	}

	public void clearObject()
	{
		this.object = null;
	}

	private void setSelect(boolean b)
	{
		this.select = b;
		repaint();
	}

	private JPopupMenu createPopupMenu() 
	{
        JPopupMenu popupMenu = new JPopupMenu();
        
        JMenuItem mnuUndo = new JMenuItem("Annuler");
        mnuUndo.setIcon(new ImageIcon("../../ProjetIHM/res/images/UI/icon/croix.png"));
        mnuUndo.setMnemonic('U');
        mnuUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        popupMenu.add(mnuUndo);
        
        popupMenu.addSeparator();
        
        JMenuItem mnuCopy = new JMenuItem("Description");
        mnuCopy.setIcon(new ImageIcon("../../ProjetIHM/res/images/UI/icon/info.png"));
        mnuCopy.setMnemonic('C');
        mnuCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));
        popupMenu.add(mnuCopy);
        
        JMenuItem mnuCut = new JMenuItem("Ramasser");
        mnuCut.setIcon(new ImageIcon("../../ProjetIHM/res/images/UI/icon/recup.png"));
        mnuCut.setMnemonic( 't' );
        mnuCut.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK) );
        popupMenu.add(mnuCut);

        return popupMenu;
    }

	@Override public void mouseClicked(MouseEvent evenement){}
	@Override public void mouseEntered(MouseEvent evenement)
	{
		this.setSelect(true);
	}
	@Override public void mouseExited(MouseEvent evenement)
	{
		this.setSelect(false);
	}
	@Override public void mousePressed(MouseEvent evenement)
	{
		popupMenu.show(evenement.getComponent(), evenement.getX(), evenement.getY() );
	}
	@Override public void mouseReleased(MouseEvent evenement){}

	protected void paintComponent(Graphics pinceau) 
  	{
		Graphics secondPinceau = pinceau.create();
		if (this.isOpaque()) 
    	{
      		secondPinceau.setColor(new Color(0,0,0));
      		secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
		if(this.select)
		{
			secondPinceau.drawImage(imageSlect.getImage(), 0, 0, getWidth(), getHeight(), this);
		}
		else
		{
			secondPinceau.drawImage(imageBase.getImage(), 0, 0, getWidth(), getHeight(), this);
		}
		if(this.object != null)
		{
			secondPinceau.drawImage(object.getImage(), 0, 0, getWidth(), getHeight(), this);
		}
	}
}