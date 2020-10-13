import javax.swing.*;
import java.awt.*;

public class mainPV
{
	public static void main (String[] args)
	{
		JFrame fenetre = new JFrame("PV");
		PieceVue pv = new PieceVue();

		fenetre.add(pv,BorderLayout.CENTER);	

		fenetre.setSize(500, 500);
        fenetre.setLocation(100, 100);

		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
	}
}