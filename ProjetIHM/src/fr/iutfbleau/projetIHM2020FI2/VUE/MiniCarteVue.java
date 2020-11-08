package fr.iutfbleau.projetIHM2020FI2.VUE;
import fr.iutfbleau.projetIHM2020FI2.CONTROLEUR.*;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class MiniCarteVue extends JPanel {

	private int vousEtesIciX;
	private int vousEtesIciY;
	private CelluleCarte[][] tabCellule;
	private final static int TAILLECARTE = 9;

	//Les status possibles:
	private final static int VOUSETESICI = 1;
	private final static int PIECE = 2;
	private final static int INCONNU = 3;
	private final static int PASSAGE_H = 4;
	private final static int PASSAGE_V = 5;
	private final static int PASSAGE = 6;

	private Map<Integer, ImageIcon> carteImage = new HashMap<Integer, ImageIcon>();

	private static MiniCarteVue mcv;

	public MiniCarteVue(){

		MiniCarteVue.mcv = this;
		carteImage.put(VOUSETESICI,ImageClassLoader.getImage("images/miniCarte/vous_etes_ici.png"));
		carteImage.put(PIECE,ImageClassLoader.getImage("images/miniCarte/piece.png"));
		carteImage.put(INCONNU,ImageClassLoader.getImage("images/miniCarte/inconnu.png"));
		carteImage.put(PASSAGE_H,ImageClassLoader.getImage("images/miniCarte/passageH.png"));
		carteImage.put(PASSAGE_V,ImageClassLoader.getImage("images/miniCarte/passageV.png"));

	   	this.setPreferredSize(new Dimension(300, 300));
		GridLayout grille = new GridLayout(TAILLECARTE,TAILLECARTE);
		this.setLayout(grille);
		this.tabCellule = new CelluleCarte[TAILLECARTE][TAILLECARTE];

		for(int i = 0 ; i < TAILLECARTE ; i++){
			for(int k = 0 ; k < TAILLECARTE ; k++ ){
				tabCellule[i][k] = new CelluleCarte(carteImage);
				this.add(tabCellule[i][k]);
			}
		}

		//Affiche des pièces
		vousEtesIciX = 3 ; vousEtesIciY = 4;
		tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(VOUSETESICI);//00

		this.setFocusable(true);
		this.requestFocusInWindow();
		//MiniCarteEvent clavier = new MiniCarteEvent(this);
		//this.addKeyListener(clavier);

	}

	public void modifCellule(int x, int y, int status){
		tabCellule[y][x].changeStatus(status);
	}

	public void move(Direction dir){

		tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(PIECE);
		if(dir ==  Direction.NORD){
			vousEtesIciY-=2;
		}else if(dir ==  Direction.SUD){
			vousEtesIciY+=2;
		}else if(dir ==  Direction.OUEST){
			vousEtesIciX-=2;
		}else if(dir ==  Direction.EST){
			vousEtesIciX+=2;
		}
		tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(VOUSETESICI);
	}

	public static MiniCarteVue getMinicarteVue()
	{
		return MiniCarteVue.mcv;
	}

	public void affichePassage(int dir[])
	{
		if(dir[0] == 0)
		{
			tabCellule[vousEtesIciY-1][vousEtesIciX].changeStatus(PASSAGE_V);
		}

		if (dir[1] == 0){
			tabCellule[vousEtesIciY][vousEtesIciX+1].changeStatus(PASSAGE_H);
		}

		if (dir[2] == 0){
			tabCellule[vousEtesIciY+1][vousEtesIciX].changeStatus(PASSAGE_V);
		}

		if (dir[3] == 0){
			tabCellule[vousEtesIciY][vousEtesIciX-1].changeStatus(PASSAGE_H);
		}
	}


}