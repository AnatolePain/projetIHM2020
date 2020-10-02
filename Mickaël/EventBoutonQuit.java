import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EventBoutonQuit implements ActionListener 
{
    private ComfirmationEvent jd;
    private boolean state;

	public EventBoutonQuit(boolean b,ComfirmationEvent j)
	{
        this.jd = j;
        this.state = b;
	}

    public void actionPerformed(ActionEvent e)
    {
        if(this.state)
        {
            this.jd.quit();
        }
        else
        {
            this.jd.stay();
        }
    }

}