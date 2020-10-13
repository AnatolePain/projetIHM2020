import javax.swing.*;
import java.awt.*;

public class mainPV
{
	public static void main (String[] args)
	{
		JFrame fenetre = new JFrame("PV");
		PieceVue pv = new PieceVue();
		EventPV mouse;

		fenetre.add(pv,BorderLayout.CENTER);
		mouse = new EventPV(pv,fenetre);
		fenetre.addMouseListener(mouse);		

		fenetre.setSize(500, 500);
        fenetre.setLocation(100, 100);

		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
	}
}