import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DescriptionEvent implements ActionListener
{	
	private DialogDescription dialog;
	private String description;

	public DescriptionEvent(DialogDescription dd)
	{
		this.dialog = dd;
	}

	public void setDescription(String desc)
	{
		this.description = desc;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		this.dialog.showD(this.description);
	}
}