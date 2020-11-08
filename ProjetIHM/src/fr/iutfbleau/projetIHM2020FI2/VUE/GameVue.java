package fr.iutfbleau.projetIHM2020FI2.VUE;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import fr.iutfbleau.projetIHM2020FI2.MNP.*;
import javax.swing.*;
import java.awt.*;

public class GameVue  extends  JPanel{

	private ImageClassLoader icl = new ImageClassLoader();

	public GameVue(JFrame f)
	{
	    GridBagLayout theLayout = new GridBagLayout();
	    this.setLayout(theLayout);
	    Color nuanceGris = new Color(92, 99, 116);
	    this.setBackground(Color.lightGray);

		//Mini-carte
		MiniCarteVue mcv = new MiniCarteVue();
		GridBagConstraints gbc01 = new GridBagConstraints();
		gbc01.gridx = 1;      // la plage de cellules commence à la première colonne
		gbc01.gridy = 0;      // la plage de cellules commence à la deuxième ligne
		gbc01.gridwidth = 1;  // la plage de cellules englobe deux colonnes
		gbc01.gridheight = 1; // la plage de cellules englobe une seule ligne
		gbc01.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
		//gbc.anchor = GridBagConstraints.CENTER; // se place au centre de la plage
		gbc01.weightx = 0.0;  // souhaite plus de largeur si possible
		gbc01.weighty = 0.0;  // n'a pas besoin de hauteur supplémentaire
		gbc01.insets = new Insets(16, 16, 0, 16);    // laisse 5 pixels de vide autour du composant
		this.add(mcv, gbc01);


		//Inventaire joueur
		DialogDescription dialog = new DialogDescription(f);
		InventaireContenuVue icv = new InventaireContenuVue(dialog);
		icv.setPreferredSize(new Dimension(300, 200));
		GridBagConstraints gbc02 = new GridBagConstraints();
		gbc02.gridx = 1;      // la plage de cellules commence à la première colonne
		gbc02.gridy = 1;      // la plage de cellules commence à la deuxième ligne
		gbc02.gridwidth = 1;  // la plage de cellules englobe deux colonnes
		gbc02.gridheight = 2; // la plage de cellules englobe une seule ligne
		gbc02.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
		//gbc.anchor = GridBagConstraints.CENTER; // se place au centre de la plage
		gbc02.weightx = 0.0;  // souhaite plus de largeur si possible
		gbc02.weighty = 0.0;  // n'a pas besoin de hauteur supplémentaire
		gbc02.insets = new Insets(16, 16, 16, 16);    // laisse 5 pixels de vide autour du composant
		
		this.add(icv, gbc02);

		//Contenu de la pièce
        PieceContenuVue pcv = new PieceContenuVue(dialog);
		pcv.setPreferredSize(new Dimension(800, 250));
		GridBagConstraints gbc03 = new GridBagConstraints();
		gbc03.gridx = 0;      // la plage de cellules commence à la première colonne
		gbc03.gridy = 2;      // la plage de cellules commence à la deuxième ligne
		gbc03.gridwidth = 1;  // la plage de cellules englobe deux colonnes
		gbc03.gridheight = 1; // la plage de cellules englobe une seule ligne
		gbc03.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
		//gbc.anchor = GridBagConstraints.CENTER; // se place au centre de la plage
		gbc03.weightx = 0.0;  // souhaite plus de largeur si possible
		gbc03.weighty = 0.0;  // n'a pas besoin de hauteur supplémentaire
		gbc03.insets = new Insets(16, 16, 16, 0);    // laisse 5 pixels de vide autour du composant
		this.add(pcv, gbc03);

	    //Vue en fausse 3D
	    PieceVue pv = new PieceVue();
		GridBagConstraints gbc00 = new GridBagConstraints();
		gbc00.gridx = 0;      // la plage de cellules commence à la première colonne
		gbc00.gridy = 0;      // la plage de cellules commence à la deuxième ligne
		gbc00.gridwidth = 1;  // la plage de cellules englobe deux colonnes
		gbc00.gridheight = 2; // la plage de cellules englobe une seule ligne
		gbc00.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
		//gbc.anchor = GridBagConstraints.CENTER; // se place au centre de la plage
		gbc00.weightx = 1.0;  // souhaite plus de largeur si possible
		gbc00.weighty = 1.0;  // n'a pas besoin de hauteur supplémentaire
		gbc00.insets = new Insets(16, 16, 0, 0);    // laisse 5 pixels de vide autour du composant
		this.add(pv, gbc00);


	}




}