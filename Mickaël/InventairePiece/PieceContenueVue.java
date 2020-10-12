import javax.swing.*;
import java.awt.*;
import java.util.*;

public class PieceContenueVue extends JPanel
{
	private String nom = "Piece";
	private GridLayout pieceLayout = new GridLayout(2,4);
	private PanelNom jcomplN;
	private CasePiece[] caseP = new CasePiece[10]; 
	private JPanel jPanelCasep = new JPanel();
	private GridBagConstraints gbcPanelN = new GridBagConstraints();
	private GridBagConstraints gbcPanelCasep = new GridBagConstraints();
	private ImageIcon imageBase;
	private ImageIcon imageSelect;
	private Map<TypeTruc, ImageIcon> item;

	public PieceContenueVue()
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
		this.jPanelCasep.setBackground(Color.lightGray);
		for(int i = 0 ; i < caseP.length;i++)
		{
			this.caseP[i] = new CasePiece(this.imageBase,this.imageSelect,this.item);
			this.jPanelCasep.add(this.caseP[i]);
		}
		gbcPanelN.gridx = 0;      // la plage de cellules commence à la première colonne
        gbcPanelN.gridy = 0;      // la plage de cellules commence à la deuxième ligne
        gbcPanelN.gridwidth = 1;  // la plage de cellules englobe deux colonnes
        gbcPanelN.gridheight = 1; // la plage de cellules englobe une seule ligne
        gbcPanelN.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
        gbcPanelN.weightx = 0.3;
        gbcPanelN.weighty = 0.3;

		gbcPanelCasep.gridx = 0;      // la plage de cellules commence à la première colonne
        gbcPanelCasep.gridy = 1;      // la plage de cellules commence à la deuxième ligne
        gbcPanelCasep.gridwidth = 1;  // la plage de cellules englobe deux colonnes
        gbcPanelCasep.gridheight = 10; // la plage de cellules englobe une seule ligne
        gbcPanelCasep.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
        gbcPanelCasep.weightx = 1.0;
        gbcPanelCasep.weighty = 1.0;

		this.add(this.jcomplN, gbcPanelN);
		this.add(this.jPanelCasep, gbcPanelCasep);
	}

	public void setTruc(TypeTruc type, String description,int pos)
	{
		this.caseP[pos%10].setObject(type,description);
	}

	public void clear()
	{
		
	}
}