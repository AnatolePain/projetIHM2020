package fr.iutfbleau.projetIHM2020FI2.MODEL;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.util.*;

public class GestionIDBD
{
	private static Map<Piece, Integer> pieceID = new HashMap<Piece, Integer>();
	private static Map<Joueur, Integer> joueurID = new HashMap<Joueur, Integer>();
	private static Map<Passage, Integer> passageID = new HashMap<Passage, Integer>();
	private static Map<Truc, Integer> trucID = new HashMap<Truc, Integer>();

	public static void putPieceID(Piece p,int id)
	{
		GestionIDBD.pieceID.put(p,id);
	}

	public static int getIdPiece(Piece p)
	{
		return GestionIDBD.pieceID.get(p);
	}

	public static void putPassageID(Passage p,int id)
	{
		GestionIDBD.passageID.put(p,id);
	}

	public static int getIdPassage(Passage p)
	{
		return GestionIDBD.passageID.get(p);
	}
}