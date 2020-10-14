import javax.swing.*;
import java.awt.*;

public class Main
{
	public static void main (String[] args)
	{
		JFrame fenetre = new JFrame("PieceContenu");
		DialogDescription dialog = new DialogDescription(fenetre);
		PieceContenueVue pcV = new PieceContenueVue(dialog);
		pcV.addTruc(TypeTruc.CLE,"Cle qui ouvre une porte, il est indiquer porte 516");
		pcV.addTruc(TypeTruc.CLE,"Cle qui ouvre une porte, il est indiquer porte 517");
		pcV.addTruc(TypeTruc.EAU,"Gourde d'eau");
		pcV.addTruc(TypeTruc.ALCOOL,"Du bon Rhum");
		pcV.addTruc(TypeTruc.GOODIES,"500 piece d'or");
		pcV.addTruc(TypeTruc.ALCOOL,"du Vin");
		pcV.addTruc(TypeTruc.EAU,"AHHHHHHHHHHH");
		fenetre.setSize(600, 250);
        fenetre.setLocation(100, 100);
		
		fenetre.add(pcV,BorderLayout.CENTER);

		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
	}
}