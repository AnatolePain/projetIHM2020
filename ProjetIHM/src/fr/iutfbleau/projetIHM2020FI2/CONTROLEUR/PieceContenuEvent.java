package fr.iutfbleau.projetIHM2020FI2.CONTROLEUR;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class PieceContenuEvent implements ActionListener {

	private CasePiece caseChoisie;

	//model
	//Truc trucDeLaCase;

	public PieceContenuEvent(CasePiece cp){
		this.caseChoisie = cp;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		PieceController.ramasserPiece(this.caseChoisie);
	}

}