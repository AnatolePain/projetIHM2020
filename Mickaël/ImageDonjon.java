import javax.swing.*;
import javax.swing.JComponent;
import java.awt.*;

public class ImageDonjon extends JComponent
{
	private ImageIcon imageBase;
	private ImageIcon imageFermer;
	
	public ImageDonjon(ImageIcon b,ImageIcon f)
	{
		this.imageBase = b;
		this.imageFermer = f;
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
		secondPinceau.drawImage(imageFermer.getImage(), 0, 0, getWidth(), getHeight(), this);
	}
}