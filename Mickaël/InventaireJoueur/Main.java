import javax.swing.*;
import java.awt.*;

public class Main
{
	public static void main (String[] args)
	{
		JFrame fenetre = new JFrame("PieceContenu");
		DialogDescription dialog = new DialogDescription(fenetre);
		InventaireContenueVue icV = new InventaireContenueVue(dialog);
		icV.addTruc(TypeTruc.CLE,"Cle qui ouvre une porte, il est indiquer porte 516");
		icV.addTruc(TypeTruc.CLE,"Cle qui ouvre une porte, il est indiquer porte 517");
		icV.addTruc(TypeTruc.EAU,"Gourde d'eau");
		icV.addTruc(TypeTruc.ALCOOL,"Du bon Rhum");
		icV.addTruc(TypeTruc.GOODIES,"500 piece d'or");
		icV.addTruc(TypeTruc.ALCOOL,"Vin");
		icV.addTruc(TypeTruc.EAU,"AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
		fenetre.setSize(300, 600);
        fenetre.setLocation(100, 100);
		
		fenetre.add(icV,BorderLayout.CENTER);

		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
	}
}