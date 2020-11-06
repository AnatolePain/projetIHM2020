package fr.iutfbleau.projetIHM2020FI2.MODEL;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.sql.*;
import java.util.*;
/**
 * Permet de modéliser un conteneur d'objets (qu'on appelle des trucs pour bien faire la différence avec Object).
 * Ce conteneur permet de créer des trucs.
 * 
 * @see Joueur, Piece, Truc
 */
public class TrucFactoryBD implements TrucFactory {

	private Connection cnx;
	private PreparedStatement getTrucsPS;
	private PreparedStatement addRemoveTrucPS;
	private PreparedStatement containsTrucPS;
	private PreparedStatement combienTrucsPS;
    private ResultSet rs;
	private int autoIncrementeTruc = 0;

	public TrucFactoryBD()
	{
		this.cnx = ConnectionBD.getConnection();
		try
		{
			this.getTrucsPS = this.cnx.prepareStatement("SELECT id FROM `API_Truc` WHERE idJoueur = ? AND idPiecePos = -2 or idPiecePos >= -1");
			this.addRemoveTrucPS = this.cnx.prepareStatement("UPDATE API_Truc SET idPiecePos = ? WHERE id = ? AND idJoueur = ?");
			this.containsTrucPS = this.cnx.prepareStatement("SELECT COUNT(id) FROM `API_Truc` WHERE id = ? AND idJoueur = ?");
			this.combienTrucsPS = this.cnx.prepareStatement("SELECT COUNT(id) FROM `API_Truc` WHERE idJoueur = ?");
		}
        catch(SQLException se)
        {
			System.err.println(se);
        }   
	}
     /**
     * permet d'accéder aux trucs.
     */
    public Iterator<Truc> getTrucs()
	{
		List<Truc> trucI; 
		trucI = new LinkedList<Truc>();
		if(this.getTrucsPS != null)
		{
			try
			{
				this.getTrucsPS.setInt(1,JoueurBD.getIdJoueur());
				this.rs = this.getTrucsPS.executeQuery();
				while(this.rs.next())
				{
					trucI.add((Truc)GestionIDBD.getElement(this.rs.getInt(1),"fr.iutfbleau.projetIHM2020FI2.MODEL.TrucBD"));
				}
				this.rs.close();
			}
			catch(SQLException se)
			{
				System.err.println(se);
			}   
		}
    	return trucI.iterator();
    }

    /**
     * Le truc ne peut pas être null sinon la méthode lève une NullPointerException
     * Ajoute le truc si nécessaire.
     * retourne vrai si il fallait l'ajouter et faux sinon.
     */
    public boolean addTruc(Truc t)
	{
		if(this.containsTruc(t))
		{
			return false;
		}
		if(this.addRemoveTrucPS != null)
		{
			try
			{
				this.addRemoveTrucPS.setInt(1,0);
				this.addRemoveTrucPS.setInt(2,GestionIDBD.getID(t));
				this.addRemoveTrucPS.setInt(3,JoueurBD.getIdJoueur());
				this.addRemoveTrucPS.executeUpdate();
				return true;
			}
			catch(SQLException se)
			{
				System.err.println(se);
			}   
		}
        return false;//return this.univers.addTruc(t);
    }

    /**
     * Le truc ne peut pas être null sinon la méthode lève une NullPointerException
     * Enlève le truc si nécessaire.
     * retourne vrai si on pouvait l'enlever et faux sinon.
     */
    public boolean removeTruc(Truc t)
	{	
		if(!this.containsTruc(t))
		{
			return false;
		}
		if(this.addRemoveTrucPS != null)
		{
			try
			{
				this.addRemoveTrucPS.setInt(1,-1);
				this.addRemoveTrucPS.setInt(2,GestionIDBD.getID(t));
				this.addRemoveTrucPS.setInt(3,JoueurBD.getIdJoueur());
				this.addRemoveTrucPS.executeUpdate();
				return true;
			}
			catch(SQLException se)
			{
				System.err.println(se);
			}   
		}
        return false;
    }
    
    /**
     * test d'appartenance
     * @param  t ne peut pas être null sinon lève une NullPointerException
     * @return vrai ssi le truc est contenu
     */
    public boolean containsTruc(Truc t)
	{
		if(this.containsTrucPS != null)
		{
			try
			{
				this.containsTrucPS.setInt(1,GestionIDBD.getID(t));
				this.containsTrucPS.setInt(2,JoueurBD.getIdJoueur());
				this.rs = this.containsTrucPS.executeQuery();
				boolean resultB = false;
				while(this.rs.next() && !resultB)
				{
					resultB = this.rs.getInt(1) > 0;
				}
				this.rs.close();
				return resultB;
			}
			catch(SQLException se)
			{
				System.err.println(se);
			}   
		}
        return false;
    }

    /**
     * le nombre de trucs contenus dans le sac à dos du joueur
     * @return un entier positif ou nul.
     */
    public int combienTrucs()
	{
		if(this.combienTrucsPS != null)
		{
			try
			{
				this.combienTrucsPS.setInt(1,JoueurBD.getIdJoueur());
				this.rs = this.combienTrucsPS.executeQuery();
				int valeur = 0;
				while(this.rs.next())
				{
					valeur = this.rs.getInt(1);
				}
				this.rs.close();
				return valeur;
			}
			catch(SQLException se)
			{
				System.err.println(se);
			}   
		}
        return 0;//return this.univers.combienTrucs();
    }
    
    /**
     * ajoute et retourne un nouvel objet (on délègue à TrucBD)
     * @param  tt          type de l'objet
     * @param  description sa description
     * @return Truc        le nouvel objet
     */
    public Truc newTruc(TypeTruc tt, String description){
        Truc t = new TrucBD(tt,description,autoIncrementeTruc++);
        //this.addTruc(t);
        return t;
    }
}
