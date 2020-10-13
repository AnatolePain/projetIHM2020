import javax.swing.*;
import java.awt.*;

public class CelluleCarte extends JComponent {

	private final static char VOUSETESICI = 1;
	private final static char PIECE = 2;
	private final static char INCONNU = 3;
	private final static String LOCATIONIMAGE = "../../ProjetIHM/res/images/miniCarte/";
	private ImageIcon imageBase;
	private final static ImageIcon imagePiece = new ImageIcon(LOCATIONIMAGE + "piece.png");
	private final static ImageIcon imageVousEtesIci = new ImageIcon(LOCATIONIMAGE + "vous_etes_ici.png");
	private final static ImageIcon imageInconnu = new ImageIcon(LOCATIONIMAGE + "inconnu.png");

	public CelluleCarte(int status){

		if(status == VOUSETESICI){
	   		imageBase = imageVousEtesIci;
	   }else if(status == PIECE){
	   		imageBase = imagePiece;
	   }else if(status == INCONNU){
	   		imageBase = imageInconnu;
	   }

	}

	public CelluleCarte(){
		imageBase = imageInconnu;
	}

	public void changeStatus(int status){
	   
	   if(status == VOUSETESICI){
	   		imageBase = imageVousEtesIci;
	   }else if(status == PIECE){
	   		imageBase = imagePiece;
	   }else if(status == INCONNU){
	   		imageBase = imageInconnu;
	   }

	   this.repaint();
		
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