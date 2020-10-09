import javax.swing.*;
import java.awt.*;

public class CelluleCarte extends JLabel {


	private static int VOUSETESICI = 1;
	private static int PIECE = 2;
	private static int INCONNU = 3;
	private static String LOCATIONIMAGE = "../../ProjetIHM/res/images/miniCarte/";

	public CelluleCarte(int status){
	   
	   if(status == VOUSETESICI){
	   		//JLabel image1 = new JLabel(new ImageIcon("1.jpg"));
	   		this.setIcon((new ImageIcon(LOCATIONIMAGE + "vous_etes_ici.png")));
	   }else if(status == PIECE){
	   		this.setIcon((new ImageIcon(LOCATIONIMAGE + "piece.png")));
	   }else if(status == INCONNU){
	   		this.setIcon((new ImageIcon(LOCATIONIMAGE + "inconnu.png")));
	   }
		
	}

	public CelluleCarte(){
		this.setIcon((new ImageIcon(LOCATIONIMAGE + "inconnu.png")));
	}

	public void changeStatus(int status){
	   
	   if(status == VOUSETESICI){
	   		//JLabel image1 = new JLabel(new ImageIcon("1.jpg"));
	   		this.setIcon((new ImageIcon(LOCATIONIMAGE + "vous_etes_ici.png")));
	   }else if(status == PIECE){
	   		this.setIcon((new ImageIcon(LOCATIONIMAGE + "piece.png")));
	   }else if(status == INCONNU){
	   		this.setIcon((new ImageIcon(LOCATIONIMAGE + "inconnu.png")));
	   }
		
	}



}