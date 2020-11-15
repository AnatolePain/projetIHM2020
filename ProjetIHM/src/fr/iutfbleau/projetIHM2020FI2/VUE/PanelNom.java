package fr.iutfbleau.projetIHM2020FI2.VUE;
import javax.swing.*;
import java.awt.*;

public class PanelNom extends JComponent
{
	private ImageIcon imageBase;
	private String nom;
	private Font font;
	
	/**
	 * constructeur
	 * @param b image de fond
	 * @param n titre
	 */
	public PanelNom(ImageIcon b,String n)
	{
		this.imageBase = b;
		this.nom = n;
		this.font = new Font("Verdana", Font.BOLD, 30);
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
		secondPinceau.setFont(this.font);
		drawCenteredString(secondPinceau,this.nom,this.font);
	}

	/**
	* Affiche un texte centrer 
	* @param g paintComponent Graphics 
	* @param text Titre à centrer 
	* @Param font police et taille d'ecriture
	*/
	public void drawCenteredString(Graphics g, String text, Font font) 
	{
		FontMetrics metrics = g.getFontMetrics(font);
		int x = (getWidth() - metrics.stringWidth(text)) / 2;    
		int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
		g.setFont(font);
		g.drawString(text, x, y);
	}
}