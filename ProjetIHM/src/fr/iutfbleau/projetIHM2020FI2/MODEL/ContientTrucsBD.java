package fr.iutfbleau.projetIHM2020FI2.MODEL;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.sql.*;
import java.util.*;
/**
 * Implémentation non persistante.
 * Il s'agit en fait à peu de chose près d'une façade sur Set<Truc>.
 */
public class ContientTrucsBD implements ContientTrucs
{
	private Connection cnx;
	private PreparedStatement addTrucPS;
	private PreparedStatement getTrucsPS;
	private PreparedStatement removeTrucPS;
	private PreparedStatement contientTrucPS;
	private PreparedStatement combienTrucsPS;
	private ResultSet rs;
	protected PieceBD piece;
	protected JoueurBD joueur;
    /**
     * Constructeur.
     * On utilise un LinkedHashSet qui permet de garantir que l'ordre des trucs reste le même.
     */
    public ContientTrucsBD()
	{	
		this.cnx = ConnectionBD.getConnection();
        if(this.cnx != null)
        {
            try
            {
				this.addTrucPS = this.cnx.prepareStatement("UPDATE API_Truc SET idPiecePos = ? WHERE API_Truc.id = ? AND API_Truc.idJoueur = ?;");
				this.getTrucsPS = this.cnx.prepareStatement("SELECT id FROM `API_Truc` WHERE idJoueur = ? AND idPiecePos = ?");
				this.removeTrucPS = this.cnx.prepareStatement("UPDATE API_Truc SET idPiecePos = -1 WHERE id = ? AND idJoueur = ?");
				this.contientTrucPS = this.cnx.prepareStatement("SELECT COUNT(id) FROM `API_Truc` WHERE id = ? AND idJoueur = ? AND idPiecePos = ?");
				this.combienTrucsPS = this.cnx.prepareStatement("SELECT COUNT(id) FROM `API_Truc` WHERE idJoueur = ? AND idPiecePos = ?");
            }
            catch(SQLException se)
            {
                System.err.println(se);
            }   
        }
    }
   
    /**
     * permet d'accéder aux trucs.
     * @return Iterator sur les trucs.
     */
    @Override
    public Iterator<Truc> getTrucs()
	{
		Set<Truc> contenuGT;
		contenuGT = new LinkedHashSet<Truc>();
		if(this.getTrucsPS != null)
		{
			try
            {
				this.getTrucsPS.setInt(1,JoueurBD.getIdJoueur());
				this.getTrucsPS.setInt(2,joueur != null ? -2 : GestionIDBD.getID(piece));
				this.rs = this.getTrucsPS.executeQuery();
				//System.out.println("getTrucs()");
				while(this.rs.next())
				{		
					//System.out.println("Trucs :" + this.rs.getInt(1));
					contenuGT.add((Truc)GestionIDBD.getElement(this.rs.getInt(1),"fr.iutfbleau.projetIHM2020FI2.MODEL.TrucBD"));
				}
				this.rs.close();
				return contenuGT.iterator();
            }
            catch(SQLException se)
            {
                System.err.println(se);
            } 
		}
    	return contenuGT.iterator();
    }
    
    /**
     * Ajoute le truc si nécessaire
     * @param  t Truc à ajouter
     * @return   vrai si il fallait l'ajouter et faux sinon.
     * @throws NullPointerException si t est null
     */
    @Override
    public boolean addTruc(Truc t)
	{
    	Objects.requireNonNull(t,"On ne peut pas ajouter un truc null.");
		if(!JoueurBD.getIsInstantiate() && GameStart.get())
		{
			
			return false;
		}
		if(this.containsTruc(t))
		{			
			return false;
		}
		if(this.addTrucPS != null)
		{
			try
			{
				this.addTrucPS.setInt(1,(piece != null) ? GestionIDBD.getID(piece) : -2);
				this.addTrucPS.setInt(2,GestionIDBD.getID(t));
				this.addTrucPS.setInt(3,JoueurBD.getIdJoueur());
				this.addTrucPS.executeUpdate();
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
     * Enlève le truc si nécessaire
     * @param  t Truc à enleverer
     * @return   vrai si il fallait l'enlever et faux sinon.
     * @throws NullPointerException si t est null
     */
    @Override
    public boolean removeTruc(Truc t)
	{
    	Objects.requireNonNull(t,"On ne peut pas enlever un truc null.");
		if(!JoueurBD.getIsInstantiate() && GameStart.get())
		{
			return false;
		}
		if(!this.containsTruc(t))
		{
			return false;
		}
		if(this.removeTrucPS != null)
		{
			try
			{
				this.removeTrucPS.setInt(1,GestionIDBD.getID(t));
				this.removeTrucPS.setInt(2,JoueurBD.getIdJoueur());
				this.removeTrucPS.executeUpdate();
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
     * @param  t Truc dont il faut tester l'appartenance 
     * @throws NullPointerException si  null.
     * @return vrai ssi le truc est contenu
     */
    @Override
    public boolean containsTruc(Truc t){
    	Objects.requireNonNull(t,"On ne peut pas contenir un truc null");
		if(this.contientTrucPS != null)
		{
			try
			{
				this.contientTrucPS.setInt(1,GestionIDBD.getID(t));
				this.contientTrucPS.setInt(2,JoueurBD.getIdJoueur());
				this.contientTrucPS.setInt(3, piece != null ? GestionIDBD.getID(piece) : -2);
				this.rs = this.contientTrucPS.executeQuery();
				boolean resultC = false;
				while(this.rs.next() && !resultC)
				{
					resultC = this.rs.getInt(1) > 0;
				}
				this.rs.close();
				return resultC;
			}
			catch(SQLException se)
			{
				System.err.println(se);
			}  			
		}
    	return false;
    }  
   
    /**
     * pour obtenir le nombre de trucs contenus.
     * @return un entier positif ou nul.
     */
    @Override
    public int combienTrucs()
	{
		if(this.combienTrucsPS != null)
		{
			try
			{
				this.combienTrucsPS.setInt(1,JoueurBD.getIdJoueur());
				this.combienTrucsPS.setInt(2, piece != null ? GestionIDBD.getID(piece) : -2);
				this.rs = this.combienTrucsPS.executeQuery();
				int resultBD = 0;
				while(this.rs.next())
				{
					resultBD = this.rs.getInt(1);
				}
				this.rs.close();
				return resultBD;
			}
			catch(SQLException se)
			{
				System.err.println(se);
			} 
		}
		return 0;
    }

}
