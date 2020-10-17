package fr.iutfbleau.projetIHM2020FI2.VUE;
import javax.swing.*;
import java.awt.*;

public class ImageClassLoader
{
	private static ImageClassLoader icl;
	private ClassLoader loader;
	private final String cheminSimpleRun = "./res/";

	public ImageClassLoader()
	{
		ImageClassLoader.icl = this;
		this.loader = Thread.currentThread().getContextClassLoader();
	}

	public static ImageIcon getImage(String nom)
	{
		if(ImageClassLoader.icl.loader.getResource(nom) == null)
		{
			return new ImageIcon(ImageClassLoader.icl.cheminSimpleRun + nom);
		}
		return new ImageIcon(ImageClassLoader.icl.loader.getResource(nom));
	}
}
