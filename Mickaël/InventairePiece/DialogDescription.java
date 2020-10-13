import javax.swing.*;
import java.awt.*;

public class DialogDescription extends JDialog
{

	public DialogDescription(String d)
	{
		//this = new JDialog(jf , "Description", true);
		this.setLayout(new FlowLayout());  
		this.add(new JLabel(d));
		this.setSize(400,100);
		this.setVisible(true);
	}

	

}
