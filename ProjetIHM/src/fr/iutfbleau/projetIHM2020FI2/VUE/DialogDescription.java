package fr.iutfbleau.projetIHM2020FI2.VUE;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DialogDescription extends JDialog implements ActionListener
{
	private JDialog jd;
	private JButton boutonOK = new JButton ("OK");
	private JFrame fenetre;
	private static DialogDescription pthis;

	public DialogDescription(JFrame jf)
	{
		this.fenetre = jf;
		pthis = this;
	}

	public static void showD(String type,String dialog)
	{
		if(pthis != null)
		{
			pthis.jd = new JDialog(pthis.fenetre , type, true);
			pthis.jd.setLayout(new FlowLayout());  
			pthis.jd.add(new JLabel(dialog));
			pthis.jd.add(pthis.boutonOK);
			pthis.boutonOK.addActionListener(pthis);
			pthis.jd.setSize(300,100);
			pthis.jd.setLocationRelativeTo(pthis.fenetre);
			pthis.jd.setVisible(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		this.jd.setVisible(false);
	}
}
