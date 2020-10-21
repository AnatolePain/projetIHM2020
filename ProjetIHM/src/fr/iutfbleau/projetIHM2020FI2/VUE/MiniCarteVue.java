package fr.iutfbleau.projetIHM2020FI2.VUE;
import fr.iutfbleau.projetIHM2020FI2.CONTROLEUR.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class MiniCarteVue extends JPanel {

	private int vousEtesIciX;
	private int vousEtesIciY;
	private CelluleCarte[][] tabCellule;
	private final static int TAILLECARTE = 6;

	//Les status possibles:
	private final static int VOUSETESICI = 1;
	private final static int PIECE = 2;
	private final static int INCONNU = 3;
	private Map<Integer, ImageIcon> carteImage = new HashMap<Integer, ImageIcon>();

	public MiniCarteVue(){

		carteImage.put(VOUSETESICI,ImageClassLoader.getImage("images/miniCarte/vous_etes_ici.png"));
		carteImage.put(PIECE,ImageClassLoader.getImage("images/miniCarte/piece.png"));
		carteImage.put(INCONNU,ImageClassLoader.getImage("images/miniCarte/inconnu.png"));

	   	this.setPreferredSize(new Dimension(300, 300));
		GridLayout grille = new GridLayout(TAILLECARTE,TAILLECARTE);
		this.setLayout(grille);
		this.tabCellule = new CelluleCarte[TAILLECARTE][TAILLECARTE];


		//			p2 - p4     
		//          |    |      
		//     p1 - p0 - p3 - p9
		//     |              |
		//     p5 - p6 - p7 - p8

		//	|--|--|--|--|--|--|
		//	|--|--|--|--|--|--|
		//	|--|--|02|04|--|--|
		//	|--|01|00|03|09|--|
		//	|--|05|06|07|08|--|
		//	|--|--|--|--|--|--|

		for(int i = 0 ; i < 6 ; i++){
			for(int k = 0 ; k < 6 ; k++ ){
				tabCellule[i][k] = new CelluleCarte(carteImage);
				this.add(tabCellule[i][k]);
			}
		}

		tabCellule[2][2].changeStatus(PIECE);
		tabCellule[2][3].changeStatus(PIECE);
		tabCellule[3][1].changeStatus(PIECE);

		vousEtesIciX = 2 ; vousEtesIciY = 3;
		tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(VOUSETESICI);

		tabCellule[3][3].changeStatus(PIECE);
		tabCellule[3][4].changeStatus(PIECE);
		tabCellule[4][1].changeStatus(PIECE);
		tabCellule[4][2].changeStatus(PIECE);
		tabCellule[4][3].changeStatus(PIECE);
		tabCellule[4][4].changeStatus(PIECE);

		this.setFocusable(true);
		this.requestFocusInWindow();
		MiniCarteEvent clavier = new MiniCarteEvent(this);
		this.addKeyListener(clavier);

	}

	public void modifCellule(int x, int y, int status){
		tabCellule[y][x].changeStatus(status);
	}

	public void moveUp(){
		if(tabCellule[vousEtesIciY-1][vousEtesIciX].getStatus() != INCONNU){
			tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(PIECE);
			vousEtesIciY--;
			tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(VOUSETESICI);
		}
	}

	public void moveDown(){
		if(tabCellule[vousEtesIciY+1][vousEtesIciX].getStatus() != INCONNU){
			tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(PIECE);
			vousEtesIciY++;
			tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(VOUSETESICI);
		}
	}

	public void moveLeft(){
		if(tabCellule[vousEtesIciY][vousEtesIciX-1].getStatus() != INCONNU){
			tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(PIECE);
			vousEtesIciX--;
			tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(VOUSETESICI);
		}
	}

	public void moveRight(){
		if(tabCellule[vousEtesIciY][vousEtesIciX+1].getStatus() != INCONNU){
			tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(PIECE);
			vousEtesIciX++;
			tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(VOUSETESICI);
		}
	}


}