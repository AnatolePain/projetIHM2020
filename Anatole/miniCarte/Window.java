import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

	private static int VOUSETESICI = 1;
	private static int PIECE = 2;
	private static int INCONNU = 3;

	public Window(){
		this.setSize(600, 630);
	    this.setLocation(400, 300);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //Inventaire inventaire01 = new Inventaire();
	    //this.add(inventaire01);
	    MiniCarteVue miniCarte = new MiniCarteVue();
	    miniCarte.modifCellule(1, 1, PIECE);
	    this.add(miniCarte,BorderLayout.CENTER);

	}




}