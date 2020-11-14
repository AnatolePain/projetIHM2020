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
	private CaseInventaireEvent[] caseIEvent; //clik droit sur une case
	private static CaseInventaire[] caseI;

	private JeterEvent[] jeterEvent;
	private static Map<CaseInventaire, Truc> mapCaseInventaireTruc = new HashMap<CaseInventaire, Truc>();
	private static DescriptionEvent[] descriptionEvent;

	private Joueur jP;

	public InventaireController()
	{
		this.caseIEvent = new CaseInventaireEvent[InventaireContenuVue.getNbCase()];
		this.caseI = InventaireContenuVue.getCaseInvtab();
		this.jeterEvent = new JeterEvent[InventaireContenuVue.getNbCase()]; 
		this.descriptionEvent = new DescriptionEvent[InventaireContenuVue.getNbCase()]; 		

		this.jP = SetupModel.getJoueur();
		Iterator<Truc> iT = jP.getTrucs();

		for(int i = 0 ; i < this.caseIEvent.length;i++)
		{
			//click droit 
			this.caseIEvent[i] = new CaseInventaireEvent(InventaireController.caseI[i]);
			InventaireController.caseI[i].addMouseListener(this.caseIEvent[i]);

			//Descritption 
			InventaireController.descriptionEvent[i] = new DescriptionEvent();
			InventaireController.caseI[i].GetPopupMenu().getMenuItemDescription().addActionListener(InventaireController.descriptionEvent[i]);

			//jeter 
			this.jeterEvent[i] = new JeterEvent(InventaireController.caseI[i]);
			InventaireController.caseI[i].GetPopupMenu().getMenuItemJeter().addActionListener(this.jeterEvent[i]);

			//utiliser
			InventaireController.caseI[i].GetPopupMenu().getMenuItemUtiliser().addActionListener(new UtiliserEvent(InventaireController.caseI[i]));


			//set 
			if(iT.hasNext()){
				Truc t = iT.next();
				mapCaseInventaireTruc.put(InventaireController.caseI[i],t);
				InventaireContenuVue.addTrucAtIndex(t.getTypeTruc(),t.getDescription(),i);
				descriptionEvent[i].setDescription(t.getDescription());
			}
		}

		//get Truc
	}

	public static void jeterInventaire(CaseInventaire ci){

		//model
		Truc t = InventaireController.mapCaseInventaireTruc.get(ci);
		SetupModel.getJoueur().removeTruc(t);
		SetupModel.getJoueur().getPiece().addTruc(t);

		//vue
		InventaireController.mapCaseInventaireTruc.put(ci,null);
		ci.clearObject();
		PieceController.addPieceVue(t);

	}

	public static void utiliserInventaire(CaseInventaire ci){

		Truc t = InventaireController.mapCaseInventaireTruc.get(ci);

		Iterator<Truc> it =  SetupModel.getJoueur().getTrucs();

		if (Objects.equals(t.getTypeTruc(),TypeTruc.CLE)){
			PieceVueController.agirAvecTruc(t);
		}else if(SetupModel.getJoueur().agir(t)){

			//VUE
			InventaireController.mapCaseInventaireTruc.put(ci,null);
			ci.clearObject();
			if(Objects.equals(t.getTypeTruc(),TypeTruc.ALCOOL) ){
				MiniCarteVue.getMinicarteVue().clearCarte();
				DialogDescription.showD("Malheur!","Vous ne vous souvenez plus de rien");
			}else{
				DialogDescription.showD("Gloups","Vous avez bus de l'eau");
			}

		}/*else if(!Objects.equals(t.getTypeTruc(),TypeTruc.GOODIES)){
			DialogDescription.showD("...","je peux peut-être revendre ça cher");
		}*/
	}

	public static void addInventaireVue(Truc t){
		int i = 0;
		while(!caseI[i].isEmpty()){
			i++;
		}
		InventaireController.mapCaseInventaireTruc.put(InventaireController.caseI[i],t);
		InventaireContenuVue.addTrucAtIndex(t.getTypeTruc(),t.getDescription(),i);
		descriptionEvent[i].setDescription(t.getDescription());
	}

}