package fr.iutfbleau.projetIHM2020FI2.CONTROLEUR;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DescriptionEvent implements ActionListener//appelé par InventaireMenuContextuel
{	
	private String description = "";

	public void setDescription(String desc)
	{
		this.description = desc;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		DialogDescription.showD("Description",this.description);
	}
}