package fr.iutfbleau.projetIHM2020FI2.VUE;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
//import fr.iutfbleau.projetIHM2020FI2.CONTROLEUR.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CasePiece extends JComponent
{
	private ImageIcon imageBase;
	private ImageIcon imageSlect;
	private ImageIcon object; 
	private boolean select = false;
	private Map<TypeTruc, ImageIcon> itemImage;
	private PieceMenuContextuel popupMenu;  
	private Truc trucPiece;

	public CasePiece(ImageIcon b, ImageIcon s, Map<TypeTruc, ImageIcon> iI ,DialogDescription dd,PieceContenuVue pcv)
	{
		this.imageBase = b;
		this.imageSlect = s;
		this.itemImage = iI;
		this.popupMenu = new PieceMenuContextuel(dd,pcv, this);
	}

	public void setObject(Truc t)
	{
		this.trucPiece = t;
		this.object = this.itemImage.get(t.getTypeTruc());
		this.setToolTipText(t.getDescription());
		this.popupMenu.getDescriptionEvent().setDescription(t.getDescription());
		repaint();
	}

	public void clearObject()
	{
		this.object = null;
		this.setToolTipText("");
		repaint();
	}

	public void setSelect(boolean b)
	{
		this.select = b;
		repaint();
	}

    public PieceMenuContextuel GetPopupMenu()
    {
    	return popupMenu;
    }

	public boolean isEmpty()
	{
		return object == null;
	}

	public Truc getTrucCase(){
		if(this.isEmpty()){
			return null;
		}else{
			return this.trucPiece;
		}

	}
	

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