package fr.iutfbleau.projetIHM2020FI2.VUE;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import fr.iutfbleau.projetIHM2020FI2.CONTROLEUR.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class InventaireContenuVue extends JPanel
{
	private String nom = "Inventaire";
	private GridLayout pieceLayout = new GridLayout(9,3);
	private PanelNom jcomplN;
	private final static int NOMBRESCASES = 45;
	private static CaseInventaire[] caseP = new CaseInventaire[NOMBRESCASES]; 
	private JPanel jPanelCasep = new JPanel();
	private GridBagConstraints gbcPanelN = new GridBagConstraints();
	private GridBagConstraints gbcPanelCasep = new GridBagConstraints();
	private ImageIcon imageBase;
	private ImageIcon imageSelect;
	private Map<TypeTruc, ImageIcon> item;
	private static int indice = 0;

	public InventaireContenuVue()
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
		Color nuanceGris = new Color(92, 99, 116);
	    this.setBackground(nuanceGris);
		this.jPanelCasep.setBackground(nuanceGris);
		for(int i = 0 ; i < NOMBRESCASES;i++)
		{
			InventaireContenuVue.caseP[i] = new CaseInventaire(this.imageBase,this.imageSelect,this.item);
			this.jPanelCasep.add(this.caseP[i]);
		}
		gbcPanelN.gridx = 0;
        gbcPanelN.gridy = 0;
        gbcPanelN.gridwidth = 1;
        gbcPanelN.gridheight = 1;
        gbcPanelN.fill = GridBagConstraints.BOTH;
        gbcPanelN.weightx = 0.1;
        gbcPanelN.weighty = 0.1;

		gbcPanelCasep.gridx = 0;
        gbcPanelCasep.gridy = 1;
        gbcPanelCasep.gridwidth = 4;
        gbcPanelCasep.gridheight = 40;
        gbcPanelCasep.fill = GridBagConstraints.BOTH; 
        gbcPanelCasep.weightx = 1.0;
        gbcPanelCasep.weighty = 1.0;

		this.add(this.jcomplN, gbcPanelN);
		this.add(this.jPanelCasep, gbcPanelCasep);
	}

	public static int getNbCase()
	{
		return InventaireContenuVue.NOMBRESCASES;
	}

	public static CaseInventaire[] getCaseInvtab()
	{
		return InventaireContenuVue.caseP;
	}

	public static void addTruc(TypeTruc type, String description)
	{
		InventaireContenuVue.caseP[InventaireContenuVue.indice++%NOMBRESCASES].setObject(type,description);
	}

	public static void clear()
	{
		InventaireContenuVue.indice = 0;
		for(int i = 0; i < InventaireContenuVue.NOMBRESCASES;i++)
		{
			InventaireContenuVue.caseP[i].clearObject();
		}
	}
}