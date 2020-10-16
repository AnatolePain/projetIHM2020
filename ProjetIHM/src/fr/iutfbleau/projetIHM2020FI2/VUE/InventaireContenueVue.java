package fr.iutfbleau.projetIHM2020FI2.VUE;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import fr.iutfbleau.projetIHM2020FI2.CONTROLEUR.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class InventaireContenueVue extends JPanel
{
	private String nom = "Inventaire";
	private GridLayout pieceLayout = new GridLayout(9,3);
	private PanelNom jcomplN;
	private final static int NOMBRESCASES = 45;
	private CaseInventaire[] caseP = new CaseInventaire[NOMBRESCASES]; 
	private CaseInventaireEvent[] casePEvent = new CaseInventaireEvent[NOMBRESCASES];
	private JPanel jPanelCasep = new JPanel();
	private GridBagConstraints gbcPanelN = new GridBagConstraints();
	private GridBagConstraints gbcPanelCasep = new GridBagConstraints();
	private ImageIcon imageBase;
	private ImageIcon imageSelect;
	private Map<TypeTruc, ImageIcon> item;
	private int indice = 0;

	public InventaireContenueVue(DialogDescription dialog)
	{
		this.item = new EnumMap<TypeTruc, ImageIcon>(TypeTruc.class);
		this.item.put(TypeTruc.CLE,new ImageIcon("../../ProjetIHM/res/images/UI/items/cle.png"));
		this.item.put(TypeTruc.ALCOOL,new ImageIcon("../../ProjetIHM/res/images/UI/items/alcool.png"));
		this.item.put(TypeTruc.EAU,new ImageIcon("../../ProjetIHM/res/images/UI/items/eau.png"));
		this.item.put(TypeTruc.GOODIES,new ImageIcon("../../ProjetIHM/res/images/UI/items/tresors.png"));

		this.imageBase = new ImageIcon("../../ProjetIHM/res/images/UI/cadre/panel.png");
		this.imageSelect = new ImageIcon("../../ProjetIHM/res/images/UI/cadre/panelSelect.png");
		this.setLayout(new GridBagLayout());
		this.jcomplN = new PanelNom(new ImageIcon("../../ProjetIHM/res/images/UI/cadre/panelText.png"),this.nom);
		this.jPanelCasep.setLayout(this.pieceLayout);
		Color nuanceGris = new Color(92, 99, 116);
	    this.setBackground(nuanceGris);
		this.jPanelCasep.setBackground(nuanceGris);
		for(int i = 0 ; i < caseP.length;i++)
		{
			this.caseP[i] = new CaseInventaire(this.imageBase,this.imageSelect,this.item,dialog);
			this.casePEvent[i] = new CaseInventaireEvent(this.caseP[i]);
			this.caseP[i].addMouseListener(this.casePEvent[i]);
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

	public void addTruc(TypeTruc type, String description)
	{
		this.caseP[indice++%NOMBRESCASES].setObject(type,description);
	}

	public void clear()
	{
		indice = 0;
		for(int i = 0; i < NOMBRESCASES;i++)
		{
			this.caseP[i].clearObject();
		}
	}
}