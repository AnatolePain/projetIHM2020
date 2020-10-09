import javax.swing.*;
import java.awt.*;

public class CelluleCarte extends JComponent {


	/*private static int VOUSETESICI = 1;
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
		
	}*/

	private static int VOUSETESICI = 1;
	private static int PIECE = 2;
	private static int INCONNU = 3;
	private static String LOCATIONIMAGE = "../../ProjetIHM/res/images/miniCarte/";
	private ImageIcon imageBase;

	public CelluleCarte(int status){

		if(status == VOUSETESICI){
	   		imageBase = new ImageIcon(LOCATIONIMAGE + "vous_etes_ici.png");
	   }else if(status == PIECE){
	   		imageBase = new ImageIcon(LOCATIONIMAGE + "piece.png");
	   }else if(status == INCONNU){
	   		imageBase = new ImageIcon(LOCATIONIMAGE + "inconnu.png");
	   }

	}

	public CelluleCarte(){
		imageBase = new ImageIcon(LOCATIONIMAGE + "inconnu.png");
	}

	public void changeStatus(int status){
	   
	   if(status == VOUSETESICI){
	   		//JLabel image1 = new JLabel(new ImageIcon("1.jpg"));
	   		imageBase = new ImageIcon(LOCATIONIMAGE + "vous_etes_ici.png");
	   		//this.repaint();
	   		System.out.println("HAAA1");
	   }else if(status == PIECE){
	   		imageBase = new ImageIcon(LOCATIONIMAGE + "piece.png");
	   		//this.repaint();
	   }else if(status == INCONNU){
	   		imageBase = new ImageIcon(LOCATIONIMAGE + "inconnu.png");
	   		//this.repaint();
	   }
		
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