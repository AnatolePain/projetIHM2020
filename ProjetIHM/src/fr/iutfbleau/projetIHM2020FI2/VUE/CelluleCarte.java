package fr.iutfbleau.projetIHM2020FI2.VUE;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class CelluleCarte extends JComponent {

	private final static char VOUSETESICI = 1;
	private final static char PIECE = 2;
	private final static char INCONNU = 3;
	private int status;

	public CelluleCarte(int s){

		this.status = s;
		if(this.status == this.VOUSETESICI){
	   		this.imageBase = this.imageVousEtesIci;
	   }else if(this.status == this.PIECE){
	   		this.imageBase = this.imagePiece;
	   }else if(this.status == this.INCONNU){
	   		this.imageBase = this.imageInconnu;
	   }

	}

	public CelluleCarte(){
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		System.out.println("loader.toString() = " + loader.getResource("images/miniCarte/inconnu.png"));

		this.status = this.INCONNU;
		this.imageBase = this.imageInconnu;
	}

	public void changeStatus(int s){
	   this.status = s;
	   if(this.status == this.VOUSETESICI){
	   		this.imageBase = this.imageVousEtesIci;
	   }else if(this.status == this.PIECE){
	   		this.imageBase = this.imagePiece;
	   }else if(this.status == this.INCONNU){
	   		this.imageBase = this.imageInconnu;
	   }

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