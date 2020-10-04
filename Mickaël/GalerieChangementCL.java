import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GalerieChangementCL
{
	private JFrame fenetre;
	private GalerieEventCL mouse;
	private PieceVue pv = new PieceVue();

	public GalerieChangementCL(JFrame jf)
	{
		this.fenetre = jf;
		fenetre.add(pv.getPanel(),BorderLayout.CENTER);
		mouse = new GalerieEventCL(this.pv,jf);
		fenetre.addMouseListener(mouse);
	}
}