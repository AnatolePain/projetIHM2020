import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DialogDescription extends JDialog implements ActionListener
{
	private JDialog jd;
	private JButton boutonOK = new JButton ("OK");
	private JFrame fenetre;

	public DialogDescription(JFrame jf)
	{
		this.fenetre = jf;
	}

	public void showD(String dialog)
	{
		this.jd = new JDialog(this.fenetre , "Description", true);
		this.jd.setLayout(new FlowLayout());  
		this.jd.add(new JLabel(dialog));
		this.jd.add(this.boutonOK);
		this.boutonOK.addActionListener(this);
		this.jd.setSize(300,100);
		this.jd.setLocationRelativeTo(fenetre);
		this.jd.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		this.jd.setVisible(false);
	}
}
