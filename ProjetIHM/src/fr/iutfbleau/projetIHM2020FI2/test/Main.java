package fr.iutfbleau.projetIHM2020FI2.test;
//import fr.iutfbleau.projetIHM2020FI2.API.*;
//import fr.iutfbleau.projetIHM2020FI2.MODEL.*;
import fr.iutfbleau.projetIHM2020FI2.CONTROLEUR.*;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
public class Main {

	public static void main(String[] args) {
		SetupModel modele = new SetupModel();
		SetupController controleur = new SetupController(modele); 
		Window g = new Window();
		ControlleurJeux cj = new ControlleurJeux();
		g.setVisible(true);
	}

}