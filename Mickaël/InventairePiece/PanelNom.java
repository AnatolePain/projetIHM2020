import javax.swing.*;
import java.awt.*;

public class PanelNom extends JComponent
{
	private ImageIcon imageBase;
	private String nom;
	private Font font;
	
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
		secondPinceau.drawString(this.nom, (int)(getWidth()/2.2f), (int)(getHeight()/1.5f));
	}
}