package fr.iutfbleau.projetIHM2020FI2.VUE;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import static java.nio.charset.StandardCharsets.*;

public class InventaireMenuContextuel extends JPopupMenu
{
        private JMenuItem mnuCopy;
        private JMenuItem mnuCut;
        private JMenuItem mnuUtil;

	public InventaireMenuContextuel()
	{  
                JMenuItem mnuUndo = new JMenuItem("Annuler");
                mnuUndo.setIcon(ImageClassLoader.getImage("images/UI/icon/croix.png"));
                mnuUndo.setMnemonic('U');
                mnuUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
                this.add(mnuUndo);
                
                this.addSeparator();
                
                this.mnuCopy = new JMenuItem("Inspecter");
                mnuCopy.setIcon(ImageClassLoader.getImage("images/UI/icon/info.png"));
                mnuCopy.setMnemonic('C');
                mnuCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));
                this.add(mnuCopy);
                
                //accent dÉposer
                String s = "Déposer";
                byte[] bs = s.getBytes(ISO_8859_1); 
                String sUTF8 = new String(bs, UTF_8); 

                this.mnuCut = new JMenuItem(sUTF8);
                mnuCut.setIcon(ImageClassLoader.getImage("images/UI/icon/recup.png"));
                mnuCut.setMnemonic( 'J' );
                mnuCut.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK) );
                this.add(mnuCut);

        	this.mnuUtil = new JMenuItem("Utiliser");
                mnuUtil.setIcon(ImageClassLoader.getImage("images/UI/icon/use.png"));
                mnuUtil.setMnemonic( 'E' );
                mnuUtil.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK) );
                this.add(mnuUtil);
	}

        public JMenuItem getMenuItemDescription(){
                return this.mnuCopy;
        }

        public JMenuItem getMenuItemJeter(){
                return this.mnuCut;
        }

        public JMenuItem getMenuItemUtiliser(){
                return this.mnuUtil;
        }
}