package fr.iutfbleau.projetIHM2020FI2.CONTROLEUR;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.util.*;

public class SetupController
{
	private InventaireController ic;
	private PieceController pc;
	private PieceVueController pvc;

	private EventPV mouseEventPV;
	private PieceVue pv;
	private MiniCarteEvent mcEvent;

	public SetupController()
	{
		this.ic = new InventaireController();
		this.pc = new PieceController();
		this.pvc = new PieceVueController();
		this.mcEvent = new MiniCarteEvent();

		this.pv = PieceVue.getPieceVue();
		if(pv != null)
		{
			mouseEventPV = new EventPV(pv);
			pv.addMouseListener(mouseEventPV);
		}
	}
}