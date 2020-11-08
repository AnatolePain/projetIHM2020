package fr.iutfbleau.projetIHM2020FI2.CONTROLEUR;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.util.*;

public class SetupController{

	private List<CaseInventaireEvent> caseIventaireEventList;
	private List<CasePieceEvent> casePieceEventList;

	private static ControlleurJeux controleurJeux;
	private DescriptionEvent descriptionEvent;
	private EventPV eventPV;
	private MiniCarteEvent miniCarteEvent;
	private PieceContenuEvent pieceContenuEvent;

	private static SetupModel model;

	private static SetupController scThis;

	public SetupController(SetupModel sm){

		SetupController.scThis = this;

		caseIventaireEventList = new ArrayList<CaseInventaireEvent>();
		casePieceEventList = new ArrayList<CasePieceEvent>();

		this.model = sm;

	}

	// public InventaireContenuEvent getInventaireContenuEvent(){
	// 	return inventaireContenuEvent;
	// }

	// public static MiniCarteEvent getMiniCarteEvent(){
	// 	return miniCarteEvent;
	// }


	public static PieceContenuEvent  getPieceContenuEvent(){
		return SetupController.scThis.pieceContenuEvent;
	}

	public static CaseInventaireEvent getCaseInventaireEvent(CaseInventaire ci){
		CaseInventaireEvent cie = new CaseInventaireEvent(ci);
		SetupController.scThis.caseIventaireEventList.add(cie);
		return cie;
	}

	public static CasePieceEvent getCasePieceEvent(CasePiece cp){
		CasePieceEvent cpe = new CasePieceEvent(cp);
		SetupController.scThis.casePieceEventList.add(cpe);
		return cpe;
	}

	public static DescriptionEvent getDescriptionEvent(DialogDescription dd){
		//this.casePieceEvent = new CasePieceEvent(cp);
		SetupController.scThis.descriptionEvent = new DescriptionEvent(dd);
		return SetupController.scThis.descriptionEvent;
	}

	public static EventPV getEvenPv(PieceVue pv, MiniCarteVue mcv, PieceContenuVue pcv){
		SetupController.scThis.eventPV = new EventPV(pv, model.getJoueur(), mcv, pcv);
		return SetupController.scThis.eventPV;
	}



}