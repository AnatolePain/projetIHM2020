import javax.swing.*;
import java.awt.*;

public class GameVue  extends  JPanel{


	public GameVue(JFrame f){

		//JPanel panPrincipale =  new JPanel();
	    //this.add(panPrincipale);



	    GridBagLayout theLayout = new GridBagLayout();
	    this.setLayout(theLayout);

	    //Vue en fausse 3D
	    //JButton b = new JButton("0");
	    PieceVue b = new PieceVue();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;      // la plage de cellules commence à la première colonne
		gbc.gridy = 0;      // la plage de cellules commence à la deuxième ligne
		gbc.gridwidth = 1;  // la plage de cellules englobe deux colonnes
		gbc.gridheight = 2; // la plage de cellules englobe une seule ligne
		gbc.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
		//gbc.anchor = GridBagConstraints.CENTER; // se place au centre de la plage
		gbc.weightx = 1.0;  // souhaite plus de largeur si possible
		gbc.weighty = 1.0;  // n'a pas besoin de hauteur supplémentaire
		//gbc.insets = new Insets(5, 5, 5, 5);    // laisse 5 pixels de vide autour du composant
		this.add(b, gbc);

		//Mini-carte
		//JButton c = new JButton("1");ù
		MiniCarteVue c = new MiniCarteVue();
		GridBagConstraints gbc01 = new GridBagConstraints();
		gbc01.gridx = 1;      // la plage de cellules commence à la première colonne
		gbc01.gridy = 0;      // la plage de cellules commence à la deuxième ligne
		gbc01.gridwidth = 1;  // la plage de cellules englobe deux colonnes
		gbc01.gridheight = 1; // la plage de cellules englobe une seule ligne
		gbc01.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
		//gbc.anchor = GridBagConstraints.CENTER; // se place au centre de la plage
		gbc01.weightx = 0.0;  // souhaite plus de largeur si possible
		gbc01.weighty = 0.0;  // n'a pas besoin de hauteur supplémentaire
		//gbc.insets = new Insets(5, 5, 5, 5);    // laisse 5 pixels de vide autour du composant
		this.add(c, gbc01);


		//Inventaire joueur
		DialogDescription dialog = new DialogDescription(f);
		InventaireContenueVue d = new InventaireContenueVue(dialog);
		d.setPreferredSize(new Dimension(300, 200));
		GridBagConstraints gbc02 = new GridBagConstraints();
		gbc02.gridx = 1;      // la plage de cellules commence à la première colonne
		gbc02.gridy = 1;      // la plage de cellules commence à la deuxième ligne
		gbc02.gridwidth = 1;  // la plage de cellules englobe deux colonnes
		gbc02.gridheight = 2; // la plage de cellules englobe une seule ligne
		gbc02.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
		//gbc.anchor = GridBagConstraints.CENTER; // se place au centre de la plage
		gbc02.weightx = 0.0;  // souhaite plus de largeur si possible
		gbc02.weighty = 0.0;  // n'a pas besoin de hauteur supplémentaire
		//gbc.insets = new Insets(5, 5, 5, 5);    // laisse 5 pixels de vide autour du composant
		this.add(d, gbc02);


		//Contenu de la pièce
		//JButton e = new JButton("3");
        PieceContenueVue e = new PieceContenueVue(dialog);
		e.setPreferredSize(new Dimension(800, 250));
		GridBagConstraints gbc03 = new GridBagConstraints();
		gbc03.gridx = 0;      // la plage de cellules commence à la première colonne
		gbc03.gridy = 2;      // la plage de cellules commence à la deuxième ligne
		gbc03.gridwidth = 1;  // la plage de cellules englobe deux colonnes
		gbc03.gridheight = 1; // la plage de cellules englobe une seule ligne
		gbc03.fill = GridBagConstraints.BOTH;     // n'occupe pas tout l'espace de la plage
		//gbc.anchor = GridBagConstraints.CENTER; // se place au centre de la plage
		gbc03.weightx = 0.0;  // souhaite plus de largeur si possible
		gbc03.weighty = 0.0;  // n'a pas besoin de hauteur supplémentaire
		//gbc.insets = new Insets(5, 5, 5, 5);    // laisse 5 pixels de vide autour du composant
		this.add(e, gbc03);

		e.addTruc(TypeTruc.CLE,"Cle qui ouvre une porte, il est indiquer porte 516");
        e.addTruc(TypeTruc.CLE,"Cle qui ouvre une porte, il est indiquer porte 517");
        e.addTruc(TypeTruc.EAU,"Gourde d'eau");
        e.addTruc(TypeTruc.ALCOOL,"Du bon Rhum");
        e.addTruc(TypeTruc.GOODIES,"500 piece d'or");
        e.addTruc(TypeTruc.ALCOOL,"Du vieux vin");
        e.addTruc(TypeTruc.EAU,"AHHHHHHHHHHHHHHHHHHHHHHHHHHH");

	}




}