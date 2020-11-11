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
	private static CaseInventaire[] caseI;

	private JeterEvent[] jeterEvent;
	private static Map<CaseInventaire, Truc> mapCaseInventaireTruc = new HashMap<CaseInventaire, Truc>();
	//private static DescriptionEvent[] descriptionEvent;

	private Joueur jP;

	public InventaireController()
	{
		this.caseIEvent = new CaseInventaireEvent[InventaireContenuVue.getNbCase()];
		this.caseI = InventaireContenuVue.getCaseInvtab();
		this.jeterEvent = new JeterEvent[InventaireContenuVue.getNbCase()]; 
		//this.descriptionEvent = new DescriptionEvent[InventaireContenuVue.getNbCase()]; 		

		this.jP = SetupModel.getJoueur();
		Iterator<Truc> iT = jP.getTrucs();

		for(int i = 0 ; i < this.caseIEvent.length;i++)
		{
			//click droit 
			this.caseIEvent[i] = new CaseInventaireEvent(this.caseI[i]);
			this.caseI[i].addMouseListener(this.caseIEvent[i]);

			//Descritption 

			//jeter 
			this.jeterEvent[i] = new JeterEvent(this.caseI[i]);
			this.caseI[i].GetPopupMenu().getMenuItemJeter().addActionListener(this.jeterEvent[i]);
			while(iT.hasNext()){
				mapCaseInventaireTruc.put(this.caseI[i],iT.next());
			}

			//utiliser 
		}

		//get Truc
	}

	public static void jeterInventaire(CaseInventaire ci){

		//model
		Truc t = mapCaseInventaireTruc.get(ci);
		SetupModel.getJoueur().removeTruc(t);
		SetupModel.getJoueur().getPiece().addTruc(t);

		//vue
		mapCaseInventaireTruc.put(ci,null);
		ci.clearObject();
		PieceController.addPieceVue(t);

	}

	public static void addInventaireVue(Truc t){
		int i = 0;
		while(!caseI[i].isEmpty()){
			i++;
		}
		mapCaseInventaireTruc.put(caseI[i],t);
		InventaireContenuVue.addTrucAtIndex(t.getTypeTruc(),t.getDescription(),i);
	}

}