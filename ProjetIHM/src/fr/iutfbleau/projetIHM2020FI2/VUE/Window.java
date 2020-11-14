package fr.iutfbleau.projetIHM2020FI2.VUE;
import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

	private static int VOUSETESICI = 1;
	private static int PIECE = 2;
	private static int INCONNU = 3;

	public Window(){
		this.setSize(1300, 900);
	    this.setLocation(100, 100);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setMinimumSize(new Dimension(1200, 740));
	    
	    GameVue vuePrincipale = new GameVue(this);
	    this.add(vuePrincipale,BorderLayout.CENTER);

	}




}