package fr.iutfbleau.projetIHM2020FI2.MODEL;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.sql.*;
import java.util.*;

public class JoueurBD extends ContientTrucsBD implements Joueur
{
	private ConnectionBD cBD;	    
    private Piece p;
    private List<Piece> cerveau;
	private String name = "";

	public JoueurBD()
	{
	    super();
        this.cerveau = new LinkedList<Piece>();
		this.cBD = new ConnectionBD();
	}

   @Override
   public Piece getPiece(){return this.p;}

   @Override
   public void setPiece(Piece next){}

   @Override
   public Iterator<Piece> getVisited(){return this.cerveau.iterator();}

   @Override
   public boolean addVisited(Piece p){return false;}

   @Override
   public boolean isVisited(Piece p){return false;}

   @Override
   public String getDescription(){return "";}

   @Override
   public boolean agir (Truc t){return false;}
}