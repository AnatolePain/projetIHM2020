import javax.swing.*;
import java.awt.*;

public class CasePiece extends JComponent
{
	private ImageIcon imageBase;
	private ImageIcon object; 

	public CasePiece(ImageIcon b)
	{
		this.imageBase = b;
	}

	public void setObject(ImageIcon i)
	{
		this.object = i;
	}

	public void clearObject()
	{
		this.object = null;
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