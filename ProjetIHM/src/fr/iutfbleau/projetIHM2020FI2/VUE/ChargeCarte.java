package fr.iutfbleau.projetIHM2020FI2.VUE;
import fr.iutfbleau.projetIHM2020FI2.CONTROLEUR.*;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;


public class ChargeCarte {

	private String[] tabString = new String[50];
	private boolean gaucheOuDroite = true;
	private int num = 0;
	private Piece[] tabAEteParcouru;
	private Piece[] tabPiece;
	MiniCarteVue miniCarte;
	//test

	public ChargeCarte(Piece[] tp, MiniCarteVue mcv){
		this.tabPiece = tp;
		this. miniCarte = mcv;
	}

	public boolean parcoursGraphe(Passage areteSauvegarde, Piece sommetActuel, int avancement, Piece[] tabPiece){

		tabAEteParcouru[avancement] = sommetActuel;

		//System.out.print("num = " + num);

		if(gaucheOuDroite){
			System.out.print("| "+quellePiece(tabPiece, sommetActuel)+" |");
		}else if(num == 9){
			//System.out.print("| "+quellePiece(tabPiece, sommetActuel)+" |");
			//System.out.println("affichage inverse");
			tabString[9] = "| "+quellePiece(tabPiece, sommetActuel)+" |";
			for(int i = 9 ; i >= 0 ; i--){
				System.out.print(tabString[i]);
			}
			System.out.println();
			gaucheOuDroite = true;
		}else{
			//System.out.println("| "+quellePiece(tabPiece, sommetActuel)+" |");
			tabString[num] = "| "+quellePiece(tabPiece, sommetActuel)+" |";
			num++;
		}

		//System.out.println("num = " + num);

		//System.out.println("--------------je vais en profondeur = " + avancement + "-----------");
		//System.out.println(quellePiece(tabPiece, sommetActuel));

		if(avancement == 4 || avancement == 5 || avancement == 15){
			System.out.println();
			gaucheOuDroite = false;
			//System.out.println("saut de ligne");
		}


		for (Direction dir : Direction.values()) {

			//System.out.println(dir);
			Passage newAreteSauvegarde = sommetActuel.getPassage(dir);
			//System.out.println(newAreteSauvegarde.getDescription());

			//System.out.println((newAreteSauvegarde != null)); //y'a t-il une arete en nord sud est west (= dir)
			//System.out.println((areteSauvegarde != newAreteSauvegarde));// je ne peux pas repasser pas l'arete d'ou je viens


			if(newAreteSauvegarde != null && areteSauvegarde != newAreteSauvegarde){

				if ( verifieSiPieceParcouru(tabAEteParcouru, newAreteSauvegarde.getAutrePiece(sommetActuel)) ){

					//System.out.println((verifieSiPieceParcouru(tabAEteParcouru,newAreteSauvegarde.getAutrePiece(sommetActuel)))); // je ne peut pas retourner sur un sommet déjà parcouru 
					//System.out.println("premier if");
					/*System.out.println(areteSauvegarde);
					System.out.println(newAreteSauvegarde.getAutrePiece(sommetActuel));
					System.out.println(tabAEteParcouru);
					System.out.println(avancement+=1); 
					System.out.println(tabPiece);*/
					try{
						Thread.sleep(1000);
					} catch(InterruptedException e){
						System.out.println("Exception");
					}
					if(gaucheOuDroite){
						System.out.print(" -- ");
					}else{
						tabString[num] = " -- ";
						num++;
					}
					parcoursGraphe(newAreteSauvegarde, newAreteSauvegarde.getAutrePiece(sommetActuel),avancement+=1, tabPiece);
					//System.out.println("--------------je recule = " + avancement + "-----------");
					//System.out.println(quellePiece(tabPiece, sommetActuel));


				}else{
					System.out.print("  "+quellePiece(tabPiece, sommetActuel)+"else-- ");
				}
			}else{
				//System.out.println("else = " + quellePiece(tabPiece, sommetActuel));
			}	
		}
		return true;
	}


	public boolean verifieSiPieceParcouru(Piece[] tabAEteParcouru, Piece sommetActuel){

		//System.out.println("je suis dans verifieSiPieceParcouru");

		// for(int i = 0 ; i < tabAEteParcouru.length ; i++){
		// 	System.out.println((tabAEteParcouru[i] == sommetActuel));
		// 	if (tabAEteParcouru[i] == sommetActuel){
		// 		return false;
		// 	}
		// }

		int i = 0; 
		int longeurTab = tabAEteParcouru.length;
		int longeurTabMoins1 = longeurTab - 1;
		//System.out.println(" longeurTab = " + longeurTab );

		while((tabAEteParcouru[i] != sommetActuel) && i < longeurTabMoins1 ){
			//System.out.println(" i = " + i );
			//System.out.println(" tabAEteParcouru[i] " + tabAEteParcouru );

			i++;
		};

		//System.out.println("if ( (i = "+i+") != (longeurTabMoins1 = "+longeurTabMoins1+")");

		if(i != longeurTabMoins1 ){
			//System.out.println("false");
			return false;
		}else{
			//System.out.println("true");
			return true;
		}

	}

	public static String quellePiece(Piece[] tabPiece, Piece reel){

		int i = 0;

		while(reel != tabPiece[i]){
			i++;
		}

		return "piece"+i;
	}

}