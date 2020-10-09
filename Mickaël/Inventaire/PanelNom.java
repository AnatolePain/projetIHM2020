import javax.swing.*;
import java.awt.*;

public class PanelNom extends JComponent
{
	private ImageIcon imageBase;
	private String nom;
	
	public PanelNom(ImageIcon b,String n)
	{
		this.imageBase = b;
		this.nom = n;
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
	}
}