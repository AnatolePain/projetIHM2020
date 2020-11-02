package fr.iutfbleau.projetIHM2020FI2.CONTROLEUR;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class PieceContenuEvent implements ActionListener {

	private CasePiece caseChoisie;
	private PieceContenuVue contenuPiece;

	public PieceContenuEvent(PieceContenuVue pcv, CasePiece cp){
		this.contenuPiece = pcv;
		this.caseChoisie = cp;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		this.contenuPiece.ramasser(caseChoisie);
	}

}