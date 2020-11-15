package fr.iutfbleau.projetIHM2020FI2.VUE;
import javax.swing.*;
import java.awt.*;

public class ImageClassLoader
{
	private static ImageClassLoader icl;
	private ClassLoader loader;
	private final String cheminSimpleRun = "./res/";

	/*
	* constructor
	* chargeur d'image
	*/
	public ImageClassLoader()
	{
		ImageClassLoader.icl = this;
		this.loader = Thread.currentThread().getContextClassLoader();
	}

	/**
	* Recupere l'image via son path ou dans le jar ou s'il n'est pas compressé dans les fichiers du projet
	* String nom path
	*/
	public static ImageIcon getImage(String nom)
	{
		if(ImageClassLoader.icl.loader.getResource(nom) == null)
		{
			return new ImageIcon(ImageClassLoader.icl.cheminSimpleRun + nom);
		}
		return new ImageIcon(ImageClassLoader.icl.loader.getResource(nom));
	}
}
