package fr.iutfbleau.projetIHM2020FI2.VUE;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;
import static java.nio.charset.StandardCharsets.*;

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

	public static void showD(String t,String d)
	{
		if(pthis != null)
		{
			byte[] ptext01 = t.getBytes(ISO_8859_1); 
			String type = new String(ptext01, UTF_8);   

			byte[] ptext02 = d.getBytes(ISO_8859_1); 
			String dialog = new String(ptext02, UTF_8); 

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
