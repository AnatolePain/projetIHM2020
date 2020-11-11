package fr.iutfbleau.projetIHM2020FI2.MODEL;
import fr.iutfbleau.projetIHM2020FI2.API.*;
//import java.sql.*;
//import java.util.*;

public class GameStart
{
	private static boolean start = false;

	public static boolean get()
	{	
		if(!GameStart.start)
		{
			GameStart.start = JoueurBD.contientThisJoueur();//true si la bd contient deja le joueur
		}
		return GameStart.start;
	}
}