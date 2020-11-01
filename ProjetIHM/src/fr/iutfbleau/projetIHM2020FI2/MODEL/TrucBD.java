package fr.iutfbleau.projetIHM2020FI2.MODEL;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.sql.*;
import java.util.*;
/**
 * Un objet pouvant être dans une pièce ou porté par le joueur.
 * Version non persistante.
 */

public class TrucBD implements Truc {

	private TypeTruc tt;

	private Connection cnx;
	private PreparedStatement getDescriptionTrucPS;
	private PreparedStatement getTypeTrucPS;
	private PreparedStatement nouveauTrucPS;
	private PreparedStatement deleteTrucPS;
	private ResultSet rs;
	/**
	 * type de l'objet
	 * @return type
	 */
    public TypeTruc getTypeTruc(){
    	return tt;
    }

    private String description;

	/**
	 * description textuelle de l'objet.
	 * @return description.
	 */
    public String getDescription(){
    	return description;
    }
	/**
	 * constructeur
	 * @param  tt type de l'objet à créer
	 * @param  d sa description
	 * @throws NullPointerException si un paramètre est null
	 */
    public TrucBD(TypeTruc tt, String d)
	{
		Objects.requireNonNull(tt,"On ne peut pas créer un truc avec un TypeTruc null.");
		Objects.requireNonNull(d,"On ne peut pas créer un truc avec une description null.");
		this.cnx = ConnectionBD.getConnection();
		if(this.cnx != null)
        {
            try
            {
                this.nouveauTrucPS = this.cnx.prepareStatement("INSERT INTO `API_Truc` (`id`, `idJoueur`, `idPiecePos`, `Description`, `TypeTruc`) VALUES (?, ?, ?, ?, ?);");
				this.getDescriptionTrucPS = this.cnx.prepareStatement("SELECT Description FROM `API_Truc` WHERE id = ?");
				this.deleteTrucPS = this.cnx.prepareStatement("DELETE FROM `API_Truc` WHERE `API_Truc`.`id` = ?");
				this.getTypeTrucPS = this.cnx.prepareStatement("SELECT TypeTruc FROM `API_Truc` WHERE id = ?");
            }
            catch(SQLException se)
            {
                System.err.println(se);
            }   
        }

		this.tt=tt;
		this.description=d;
    }

    
}
