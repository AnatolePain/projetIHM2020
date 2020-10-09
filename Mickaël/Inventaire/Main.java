import javax.swing.*;
import java.awt.*;

public class Main
{
	public static void main (String[] args)
	{
		JFrame fenetre = new JFrame("PieceContenu");
		PieceContenueVue pcV = new PieceContenueVue();
		
		fenetre.setSize(500, 200);
        fenetre.setLocation(100, 100);
		
		fenetre.add(pcV,BorderLayout.CENTER);

		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
	}
}