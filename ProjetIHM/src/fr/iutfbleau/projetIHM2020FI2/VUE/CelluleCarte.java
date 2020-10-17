package fr.iutfbleau.projetIHM2020FI2.VUE;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class CelluleCarte extends JComponent {

	private ImageIcon imageBase;
	private Map<Integer, ImageIcon> carteImage;
	private int status;

	public CelluleCarte(Map<Integer, ImageIcon> ci, int s){
		this.status = s;
		this.carteImage = ci;
		this.imageBase = this.carteImage.get(this.status);
	}

	public CelluleCarte(Map<Integer, ImageIcon> ci)
	{
		this.carteImage = ci;
		this.status = 3;
		this.imageBase = this.carteImage.get(this.status);
	}

	public void changeStatus(int s){
	   this.status = s;
	   this.imageBase = this.carteImage.get(this.status);
	   this.repaint();
		
	}

	public int getStatus(){
		return this.status;
	}

	protected void paintComponent(Graphics pinceau) {

		Graphics secondPinceau = pinceau.create();
		if (this.isOpaque()){
      		secondPinceau.setColor(new Color(0,0,0));
      		secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
		secondPinceau.drawImage(imageBase.getImage(), 0, 0, getWidth(), getHeight(), this);

	}

}