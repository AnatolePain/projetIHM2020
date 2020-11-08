package fr.iutfbleau.projetIHM2020FI2.CONTROLEUR;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import fr.iutfbleau.projetIHM2020FI2.MODEL.*;
import fr.iutfbleau.projetIHM2020FI2.MNP.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class InventaireController 
{
	private CaseInventaireEvent[] caseIEvent;
	private CaseInventaire[] caseI;

	public InventaireController()
	{
		this.caseIEvent = new CaseInventaireEvent[InventaireContenuVue.getNbCase()];
		this.caseI = InventaireContenuVue.getCaseInvtab();

		for(int i = 0 ; i < this.caseIEvent.length;i++)
		{
			this.caseIEvent[i] = new CaseInventaireEvent(this.caseI[i]);
			this.caseI[i].addMouseListener(this.caseIEvent[i]);
		}

		//get Truc
	}


}