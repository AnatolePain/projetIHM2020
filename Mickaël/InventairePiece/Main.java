import javax.swing.*;
import java.awt.*;

public class Main
{
	public static void main (String[] args)
	{
		JFrame fenetre = new JFrame("PieceContenu");
		PieceContenueVue pcV = new PieceContenueVue();
		pcV.setTruc(TypeTruc.CLE,"Clé qui ouvre une porte, il est indiquer porte 516",0);
		pcV.setTruc(TypeTruc.CLE,"Clé qui ouvre une porte, il est indiquer porte 517",1);
		pcV.setTruc(TypeTruc.EAU,"Gourde d'eau",2);
		pcV.setTruc(TypeTruc.ALCOOL,"Du bon Rhum",3);
		pcV.setTruc(TypeTruc.GOODIES,"500 piece d'or",4);
		fenetre.setSize(500, 250);
        fenetre.setLocation(100, 100);
		
		fenetre.add(pcV,BorderLayout.CENTER);

		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
	}
}