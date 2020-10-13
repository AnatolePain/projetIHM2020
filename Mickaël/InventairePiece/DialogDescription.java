import javax.swing.*;
import java.awt.*;

public class DialogDescription
{
	private JDialog jd;

	public DialogDescription(JFrame jf,String d)
	{
		this.jd =  new JDialog(jf , "Description", true);
		this.jd.setLayout(new FlowLayout());  
		this.jd.add(new JLabel(d));
		this.jd.setSize(400,100);
		this.jd.setVisible(true);
	}

}
