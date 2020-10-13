import javax.swing.*;
import java.awt.*;

public class MiniCarteVue extends JPanel {

	private final static char VOUSETESICI = 1;
	private final static char PIECE = 2;
	private final static char INCONNU = 3;
	private int vousEtesIciX;
	private int vousEtesIciY;
	private CelluleCarte[][] tabCellule;

	public MiniCarteVue(){
	   
	   	this.setPreferredSize(new Dimension(300, 300));
		GridLayout grille = new GridLayout(6,6);
		this.setLayout(grille);
		tabCellule = new CelluleCarte[6][6];


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
				tabCellule[i][k] = new CelluleCarte();
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
		GestionClavier clavier = new GestionClavier(this);
		this.addKeyListener(clavier);

	}

	public void modifCellule(int x, int y, int status){
		tabCellule[y][x].changeStatus(status);
	}

	public void moveUp(){
		tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(PIECE);
		vousEtesIciY--;
		tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(VOUSETESICI);
	}

	public void moveDown(){
		tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(PIECE);
		vousEtesIciY++;
		tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(VOUSETESICI);
	}

	public void moveLeft(){
		tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(PIECE);
		vousEtesIciX--;
		tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(VOUSETESICI);
	}

	public void moveRight(){
		tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(PIECE);
		vousEtesIciX++;
		tabCellule[vousEtesIciY][vousEtesIciX].changeStatus(VOUSETESICI);
	}


}