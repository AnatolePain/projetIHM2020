package fr.iutfbleau.projetIHM2020FI2.VUE;
import javax.swing.*;
import javax.swing.JComponent;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ImageDonjon extends JComponent
{
	private ImageIcon imageBase;
	private List<ImageIcon> imagePlus = new ArrayList<ImageIcon>(); 
	private int i = 0;
	private float alpha = 0f;

	/**
	 * constructeur
	 * une image de la fausse vue 3d d la piece 
	 */
	public ImageDonjon(ImageIcon b)
	{
		this.imageBase = b;
	}

	/**
	 * Image à superposer au paint component pour bloquer ou ouvrir le passage
	 * @param i imageIcon 
	 */
	public void addImage(ImageIcon i)
	{
		imagePlus.add(i);
	}

	/**
	 * Enleve toutes les images ajouter superposer à l'image de base
	 */
	public void clearImagePlus()
	{
		imagePlus.clear();
	}

	/**
	* Change l'opacité  de l'image de base pour cree un effet de fondu aux 
	* noires afin de donner l'illusion que l'on change de salle
	*/
	public void setAlpha(float value) 
	{
        this.alpha = Math.min(Math.max(0f, value), 1f);
		repaint();
    }

	protected void paintComponent(Graphics pinceau) 
  	{
		Graphics secondPinceau = pinceau.create();
		if (this.isOpaque()) 
    	{
      		secondPinceau.setColor(new Color(0,0,0));
      		secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
		secondPinceau.drawImage(imageBase.getImage(), 0, 0, getWidth(), getHeight(), this);
		for(i = 0; i < imagePlus.size(); i++)
		{
			secondPinceau.drawImage(imagePlus.get(i).getImage(), 0, 0, getWidth(), getHeight(), this);
		}
		Graphics2D g2d = (Graphics2D) pinceau;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, getWidth(), getHeight());
	}
}