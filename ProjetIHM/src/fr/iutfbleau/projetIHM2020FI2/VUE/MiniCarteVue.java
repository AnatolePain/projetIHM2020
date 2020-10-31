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

	public MiniCarteVue(){

		carteImage.put(VOUSETESICI,ImageClassLoader.getImage("images/miniCarte/vous_etes_ici.png"));
		carteImage.put(PIECE,ImageClassLoader.getImage("images/miniCarte/piece.png"));
		carteImage.put(INCONNU,ImageClassLoader.getImage("images/miniCarte/inconnu.png"));
		carteImage.put(PASSAGE_H,ImageClassLoader.getImage("images/miniCarte/passageH.png"));
		carteImage.put(PASSAGE_V,ImageClassLoader.getImage("images/miniCarte/passageV.png"));

	   	this.setPreferredSize(new Dimension(300, 300));
		GridLayout grille = new GridLayout(TAILLECARTE,TAILLECARTE);
		this.setLayout(grille);
		this.tabCellule = new CelluleCarte[TAILLECARTE][TAILLECARTE];


		//			p2 - p4     
		//          |    |      
		//     p1 - p0 - p3 - p9
		//     |              |
		//     p5 - p6 - p7 - p8
		//

		//		|-----------------|
		//	   p00 - p01   p02   p03     
		//            |      
		//     p04   p05   p06   p07
		//                   
		//     p08   p09   p10   p11
		//

		//	|  |  |  |  |  |  |  |  |  |
		//	|  |  |  |  |  |  |  |  |  |
		//	|  |  |  |02|--|04|  |  |  |
		//	|  |  |  | !|  | !|  |  |  |
		//	|  |01|--|00|--|03|--|09|  |
		//	|  | !|  |  |  |  |  | !|  |
		//	|  |05|--|06|--|07|--|08|  |
		//	|  |  |  |  |  |  |  |  |  |
		//	|  |  |  |  |  |  |  |  |  |

		//	|--|--|--|--|--|--|
		//	|--|--|--|--|--|--|
		//	|--|--|02|04|--|--|
		//	|--|01|00|03|09|--|
		//	|--|05|06|07|08|--|
		//	|--|--|--|--|--|--|

		for(int i = 0 ; i < TAILLECARTE ; i++){
			for(int k = 0 ; k < TAILLECARTE ; k++ ){
				tabCellule[i][k] = new CelluleCarte(carteImage);
				this.add(tabCellule[i][k]);
			}
		}

		//Affiche des pièces
		vousEtesIciX = 3 ; vousEtesIciY = 4;
		tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(VOUSETESICI);//00

		// tabCellule[4][1].changeStatus(PIECE);//01
		// tabCellule[2][3].changeStatus(PIECE);//02
		// tabCellule[4][5].changeStatus(PIECE);//03
		// tabCellule[2][5].changeStatus(PIECE);//04
		// tabCellule[4][7].changeStatus(PIECE);//09
		// tabCellule[6][7].changeStatus(PIECE);//08
		// tabCellule[6][5].changeStatus(PIECE);//07
		// tabCellule[6][3].changeStatus(PIECE);//06
		// tabCellule[6][1].changeStatus(PIECE);//05

		// //Affichage des passages Horizontale
		// tabCellule[4][2].changeStatus(PASSAGE_H);
		// tabCellule[6][2].changeStatus(PASSAGE_H);
		// tabCellule[2][4].changeStatus(PASSAGE_H);
		// tabCellule[4][4].changeStatus(PASSAGE_H);
		// tabCellule[6][4].changeStatus(PASSAGE_H);
		// tabCellule[4][6].changeStatus(PASSAGE_H);
		// tabCellule[6][6].changeStatus(PASSAGE_H);

		// //Affichage des passages Verticale
		// tabCellule[5][1].changeStatus(PASSAGE_V);
		// tabCellule[5][7].changeStatus(PASSAGE_V);
		// tabCellule[3][3].changeStatus(PASSAGE_V);
		// tabCellule[3][5].changeStatus(PASSAGE_V);



		this.setFocusable(true);
		this.requestFocusInWindow();
		MiniCarteEvent clavier = new MiniCarteEvent(this);
		this.addKeyListener(clavier);

	}

	public void modifCellule(int x, int y, int status){
		tabCellule[y][x].changeStatus(status);
	}

	public void moveUp(){
		if(tabCellule[vousEtesIciY-1][vousEtesIciX].getStatus() == PASSAGE){
			tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(PIECE);
			vousEtesIciY-=2;
			tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(VOUSETESICI);
		}
	}

	public void moveDown(){
		if(tabCellule[vousEtesIciY+1][vousEtesIciX].getStatus() == PASSAGE){
			tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(PIECE);
			vousEtesIciY+=2;
			tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(VOUSETESICI);
		}
	}

	public void moveLeft(){
		if(tabCellule[vousEtesIciY][vousEtesIciX-1].getStatus() == PASSAGE){
			tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(PIECE);
			vousEtesIciX-=2;
			tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(VOUSETESICI);
		}
	}

	public void moveRight(){
		if(tabCellule[vousEtesIciY][vousEtesIciX+1].getStatus() == PASSAGE){
			tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(PIECE);
			vousEtesIciX+=2;
			tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(VOUSETESICI);
		}
	}

	public void move(Direction dir){


		if(dir ==  Direction.NORD){
			tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(PIECE);
			vousEtesIciY-=2;
			tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(VOUSETESICI);
		}else if(dir ==  Direction.SUD){
			tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(PIECE);
			vousEtesIciY+=2;
			tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(VOUSETESICI);
		}else if(dir ==  Direction.OUEST){
			tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(PIECE);
			vousEtesIciX-=2;
			tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(VOUSETESICI);
		}else if(dir ==  Direction.EST){
			tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(PIECE);
			vousEtesIciX+=2;
			tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(VOUSETESICI);
		}
	}

	public void affichePassage(Piece p){

		if(p.getPassage(Direction.NORD) != null){
			tabCellule[vousEtesIciY-1][vousEtesIciX].changeStatus(PASSAGE_V);
		}

		if (p.getPassage(Direction.EST) != null){
			tabCellule[vousEtesIciY][vousEtesIciX+1].changeStatus(PASSAGE_H);
		}

		if (p.getPassage(Direction.SUD) != null){
			tabCellule[vousEtesIciY+1][vousEtesIciX].changeStatus(PASSAGE_V);
		}

		if (p.getPassage(Direction.OUEST) != null){
			tabCellule[vousEtesIciY][vousEtesIciX-1].changeStatus(PASSAGE_H);
		}

	}


}