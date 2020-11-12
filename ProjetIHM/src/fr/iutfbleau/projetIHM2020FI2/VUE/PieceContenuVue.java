package fr.iutfbleau.projetIHM2020FI2.VUE;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class PieceContenuVue extends JPanel
{
	private String nom = "Piece";
	private GridLayout pieceLayout = new GridLayout(3,16);
	private PanelNom jcomplN;
	private final static int NOMBRESCASES = 48;
	private static CasePiece[] caseP = new CasePiece[NOMBRESCASES]; 
	private JPanel jPanelCasep = new JPanel();
	private GridBagConstraints gbcPanelN = new GridBagConstraints();
	private GridBagConstraints gbcPanelCasep = new GridBagConstraints();
	private ImageIcon imageBase;
	private ImageIcon imageSelect;
	private Map<TypeTruc, ImageIcon> item;
	private static int indice = 0;

	public PieceContenuVue()
	{
		this.item = new EnumMap<TypeTruc, ImageIcon>(TypeTruc.class);
		this.item.put(TypeTruc.CLE,ImageClassLoader.getImage("images/UI/items/cle.png"));
		this.item.put(TypeTruc.ALCOOL,ImageClassLoader.getImage("images/UI/items/alcool.png"));
		this.item.put(TypeTruc.EAU,ImageClassLoader.getImage("images/UI/items/eau.png"));
		this.item.put(TypeTruc.GOODIES,ImageClassLoader.getImage("images/UI/items/tresors.png"));

		this.imageBase = ImageClassLoader.getImage("images/UI/cadre/panel.png");
		this.imageSelect = ImageClassLoader.getImage("images/UI/cadre/panelSelect.png");
		this.setLayout(new GridBagLayout());
		this.jcomplN = new PanelNom(ImageClassLoader.getImage("images/UI/cadre/panelText.png"),this.nom);
		this.jPanelCasep.setLayout(this.pieceLayout);
		this.jPanelCasep.setBackground(new Color(97,99,116));
		for(int i = 0 ; i < NOMBRESCASES;i++)
		{
			PieceContenuVue.caseP[i] = new CasePiece(this.imageBase,this.imageSelect,this.item);
			this.jPanelCasep.add(PieceContenuVue.caseP[i]);
		}
		gbcPanelN.gridx = 0;
        gbcPanelN.gridy = 0;
        gbcPanelN.gridwidth = 1;
        gbcPanelN.gridheight = 1;
        gbcPanelN.fill = GridBagConstraints.BOTH;
        gbcPanelN.weightx = 0.3;
        gbcPanelN.weighty = 0.3;

		gbcPanelCasep.gridx = 0;
        gbcPanelCasep.gridy = 1;
        gbcPanelCasep.gridwidth = 1;
        gbcPanelCasep.gridheight = NOMBRESCASES;
        gbcPanelCasep.fill = GridBagConstraints.BOTH; 
        gbcPanelCasep.weightx = 1.0;
        gbcPanelCasep.weighty = 1.0;

		this.add(this.jcomplN, gbcPanelN);
		this.add(this.jPanelCasep, gbcPanelCasep);
	}

	public static int getNbCase()
	{
		return PieceContenuVue.NOMBRESCASES;
	}

	public static CasePiece[] getCasePiecetab()
	{
		return PieceContenuVue.caseP;
	}

	public static void addTruc(TypeTruc type, String description)
	{
		PieceContenuVue.caseP[PieceContenuVue.indice++%NOMBRESCASES].setObject(type,description);
	}

	public static void addTrucAtIndex(TypeTruc type, String description, int i)
	{
		PieceContenuVue.caseP[i%NOMBRESCASES].setObject(type,description);
	}

	public static void clear()
	{
		PieceContenuVue.indice = 0;
		for(int i = 0; i < PieceContenuVue.NOMBRESCASES;i++)
		{
			PieceContenuVue.caseP[i].clearObject();
		}
	}

}