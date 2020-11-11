package fr.iutfbleau.projetIHM2020FI2.CONTROLEUR;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class JeterEvent implements ActionListener {

	private CaseInventaire caseChoisie;

	//model
	//Truc trucDeLaCase;

	public JeterEvent(CaseInventaire ci){
		this.caseChoisie = ci;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		InventaireController.jeterInventaire(this.caseChoisie);
	}

}