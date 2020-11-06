package fr.iutfbleau.projetIHM2020FI2.MODEL;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.sql.*;
import java.util.*;
/**
 * Un objet pouvant être dans une pièce ou porté par le joueur.
 * Version non persistante.
 */

public class TrucBD implements Truc 
{
	private Connection cnx;
	private PreparedStatement getDescriptionTrucPS;
	private PreparedStatement getTypeTrucPS;
	private PreparedStatement nouveauTrucPS;
	private PreparedStatement deleteTrucPS;
	private ResultSet rs;
	private int idTruc = 0;

	/**
	 * type de l'objet
	 * @return type
	 */
    public TypeTruc getTypeTruc()
	{	
		if(this.getTypeTrucPS != null)
		{
			try
            {
				this.getTypeTrucPS.setInt(1,idTruc);
				this.getTypeTrucPS.setInt(2,JoueurBD.getIdJoueur());
				this.rs = this.getTypeTrucPS.executeQuery();
				TypeTruc ttPS = TypeTruc.EAU;
				while(this.rs.next())
				{
					String result = this.rs.getString(1);

					for (TypeTruc tt : TypeTruc.values())
					{
						if (tt.toString() == result)
						{
							ttPS = tt;
						}
					}
				}
				this.rs.close();
				return ttPS;
            }
            catch(SQLException se)
            {
                System.err.println(se);
            } 
		}
    	return TypeTruc.EAU;
    }

	/**
	 * description textuelle de l'objet.
	 * @return description.
	 */
    public String getDescription()
	{
		if(this.getDescriptionTrucPS != null)
		{
			try
            {
				this.getDescriptionTrucPS.setInt(1,idTruc);
				this.getDescriptionTrucPS.setInt(2,JoueurBD.getIdJoueur());
				this.rs = this.getDescriptionTrucPS.executeQuery();
				String descriptionPS = "";
				while(this.rs.next())
				{
					descriptionPS = this.rs.getString(1);
				}
				this.rs.close();
				return descriptionPS;
            }
            catch(SQLException se)
            {
                System.err.println(se);
            } 
		}
    	return "";
    }
	/**
	 * constructeur
	 * @param  tt type de l'objet à créer
	 * @param  d sa description
	 * @throws NullPointerException si un paramètre est null
	 */
    public TrucBD(TypeTruc tt, String d, int id)
	{
		Objects.requireNonNull(tt,"On ne peut pas créer un truc avec un TypeTruc null.");
		Objects.requireNonNull(d,"On ne peut pas créer un truc avec une description null.");
		this.cnx = ConnectionBD.getConnection();
		this.idTruc = id;
		if(this.cnx != null)
        {
            try
            {
                this.nouveauTrucPS = this.cnx.prepareStatement("INSERT INTO `API_Truc` (`id`, `idJoueur`, `idPiecePos`, `Description`, `TypeTruc`) VALUES (?, ?, 0, ?, ?);");
				this.getDescriptionTrucPS = this.cnx.prepareStatement("SELECT Description FROM `API_Truc` WHERE id = ? AND idJoueur = ?");
				this.getTypeTrucPS = this.cnx.prepareStatement("SELECT TypeTruc FROM `API_Truc` WHERE id = ?  AND idJoueur = ?");
            }
            catch(SQLException se)
            {
                System.err.println(se);
            }   
        }
		GestionIDBD.put(this,this.idTruc);
		if(!GameStart.get())
		{
			newtrucBD(tt,d);
		}
    }

	private void newtrucBD(TypeTruc tt, String d)
	{
		if(this.nouveauTrucPS != null)
        {
            try
            {
				this.nouveauTrucPS.setInt(1,this.idTruc);
				this.nouveauTrucPS.setInt(2,JoueurBD.getIdJoueur());
				this.nouveauTrucPS.setString(3,d);
				this.nouveauTrucPS.setString(4,tt.toString());
                this.nouveauTrucPS.executeUpdate();
            }
            catch(SQLException se)
            {
                System.err.println(se);
            } 
        }		
	}
    
}
