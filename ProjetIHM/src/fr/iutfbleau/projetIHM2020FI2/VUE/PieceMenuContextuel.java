package fr.iutfbleau.projetIHM2020FI2.VUE;
import fr.iutfbleau.projetIHM2020FI2.CONTROLEUR.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class PieceMenuContextuel extends JPopupMenu
{
	private DescriptionEvent descE;

	public PieceMenuContextuel(DialogDescription dd)
	{  
		this.descE = new DescriptionEvent(dd);
        JMenuItem mnuUndo = new JMenuItem("Annuler");
        mnuUndo.setIcon(new ImageIcon("../../ProjetIHM/res/images/UI/icon/croix.png"));
        mnuUndo.setMnemonic('U');
        mnuUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        this.add(mnuUndo);
        
        this.addSeparator();
        
        JMenuItem mnuCopy = new JMenuItem("Description");
        mnuCopy.setIcon(new ImageIcon("../../ProjetIHM/res/images/UI/icon/info.png"));
        mnuCopy.setMnemonic('C');
        mnuCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));
        this.add(mnuCopy);
		mnuCopy.addActionListener(descE);
        
        JMenuItem mnuCut = new JMenuItem("Ramasser");
        mnuCut.setIcon(new ImageIcon("../../ProjetIHM/res/images/UI/icon/recup.png"));
        mnuCut.setMnemonic( 't' );
        mnuCut.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK) );
        this.add(mnuCut);
	}
	public DescriptionEvent getDescriptionEvent()
	{
		return this.descE;
	}
}