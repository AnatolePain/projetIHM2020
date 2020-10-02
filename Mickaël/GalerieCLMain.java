import javax.swing.*;
import java.awt.*;

public class GalerieCLMain
{
	public static void main (String[] args)
	{
		JFrame fenetre = new JFrame("Galerie");
		GalerieChangementCL gc = new GalerieChangementCL(fenetre);

		fenetre.setSize(500, 500);
        fenetre.setLocation(100, 100);

		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
	}
}