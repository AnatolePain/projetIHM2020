package fr.iutfbleau.projetIHM2020FI2.CONTROLEUR;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.util.*;

public class SetupController
{
	private InventaireController ic;
	private PieceController pc;

	private EventPV mouseEventPV;
	private PieceVue pv;
	private MiniCarteVue mc;

	public SetupController()
	{
		this.ic = new InventaireController();
		this.pc = new PieceController();
		this.pv = PieceVue.getPieceVue();
		this.mc = MiniCarteVue.getMinicarteVue();
		if(pv != null)
		{
			mouseEventPV = new EventPV(pv,mc);
			pv.addMouseListener(mouseEventPV);
		}
	}
}