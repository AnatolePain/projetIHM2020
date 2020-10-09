import javax.swing.*;
import java.awt.*;

public class MiniCarteVue extends JPanel {

	private static int VOUSETESICI = 1;
	private static int PIECE = 2;
	private static int INCONNU = 3;
	private CelluleCarte[][] tabCellule;

	public MiniCarteVue(){
	   
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
		tabCellule[3][2].changeStatus(VOUSETESICI);
		tabCellule[3][3].changeStatus(PIECE);
		tabCellule[3][4].changeStatus(PIECE);
		tabCellule[4][1].changeStatus(PIECE);
		tabCellule[4][2].changeStatus(PIECE);
		tabCellule[4][3].changeStatus(PIECE);
		tabCellule[4][4].changeStatus(PIECE);

	}

	public void modifCellule(int x, int y, int status){
		tabCellule[x][y].changeStatus(status);
	}


}