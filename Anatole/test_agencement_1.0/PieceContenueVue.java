import javax.swing.*;
import java.awt.*;
import java.util.*;

public class PieceContenueVue extends JPanel
{
	private String nom = "Piece";
	private GridLayout pieceLayout = new GridLayout(2,5);
	private PanelNom jcomplN;
	private CasePiece[] caseP = new CasePiece[12]; 
	private CasePieceEvent[] casePEvent = new CasePieceEvent[12];
	private JPanel jPanelCasep = new JPanel();
	private GridBagConstraints gbcPanelN = new GridBagConstraints();
	private GridBagConstraints gbcPanelCasep = new GridBagConstraints();
	private ImageIcon imageBase;
	private ImageIcon imageSelect;
	private Map<TypeTruc, ImageIcon> item;
	private int indice = 0;

	public PieceContenueVue(DialogDescription dialog)
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
			this.caseP[i] = new CasePiece(this.imageBase,this.imageSelect,this.item,dialog);
			this.casePEvent[i] = new CasePieceEvent(this.caseP[i]);
			this.caseP[i].addMouseListener(this.casePEvent[i]);
			this.jPanelCasep.add(this.caseP[i]);
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
        gbcPanelCasep.gridheight = 12;
        gbcPanelCasep.fill = GridBagConstraints.BOTH; 
        gbcPanelCasep.weightx = 1.0;
        gbcPanelCasep.weighty = 1.0;

		this.add(this.jcomplN, gbcPanelN);
		this.add(this.jPanelCasep, gbcPanelCasep);
	}

	public void addTruc(TypeTruc type, String description)
	{
		this.caseP[indice++%12].setObject(type,description);
	}

	public void clear()
	{
		indice = 0;
		for(int i = 0; i < 12;i++)
		{
			this.caseP[i].clearObject();
		}
	}
}