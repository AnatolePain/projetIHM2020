package fr.iutfbleau.projetIHM2020FI2.MODEL;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.sql.*;
import java.util.*;

public class JoueurBD extends ContientTrucsBD implements Joueur
{
    private Connection cnx;
    private PreparedStatement nouveauJoueurPS;
    private PreparedStatement getPiecePS;
	private PreparedStatement setPiecePS;
    private PreparedStatement getCerveauPS;
	private PreparedStatement getVisitedPS;
	private PreparedStatement setAddVisitedPS;
	private PreparedStatement getIsVisitedPS;
	private PreparedStatement contientThisJoueurPS;
    private PreparedStatement clearViste;
	private static int idJoueur = -1;
	private static int idPosPiece = 0;
    private ResultSet rs;
	private static boolean isInstantiate = false;
	private static String nom = "JoueurBD";

    public JoueurBD()
    {
        super();
		joueur = this;
        this.cnx = ConnectionBD.getConnection();
        if(this.cnx != null)
        {
            try
            {
                this.nouveauJoueurPS = this.cnx.prepareStatement("INSERT INTO `API_Joueur` (`id`, `idPieceActuelle`, `nom`) VALUES (?, '0', ?);");
				this.getPiecePS = this.cnx.prepareStatement("SELECT idPieceActuelle FROM `API_Joueur` WHERE id = ?");
				this.setPiecePS = this.cnx.prepareStatement("UPDATE API_Joueur SET idPieceActuelle = ? WHERE id = ?");
				this.getCerveauPS = this.cnx.prepareStatement("SELECT id,Visite FROM `API_Piece` WHERE idJoueur = ?");
				this.getVisitedPS = this.cnx.prepareStatement("SELECT id FROM `API_Piece` WHERE idJoueur = ? AND Visite = ?");
				this.setAddVisitedPS = this.cnx.prepareStatement("UPDATE API_Piece SET Visite = ? WHERE id = ? AND idJoueur = ?");
				this.getIsVisitedPS = this.cnx.prepareStatement("SELECT Visite FROM API_Piece WHERE id = ? AND idJoueur = ?");
                this.clearViste = this.cnx.prepareStatement("UPDATE API_Piece SET Visite = 0 WHERE idJoueur = ?");
            }
            catch(SQLException se)
            {
                System.err.println(se);
            }   
        }
		if(!GameStart.get())
		{
			newJoueurBD();
		}
		JoueurBD.isInstantiate = true;
   }

   public static boolean getIsInstantiate()
   {
		return JoueurBD.isInstantiate;
   }

   public static boolean contientThisJoueur()
   {
		Connection cnx = ConnectionBD.getConnection();
		PreparedStatement contientThisJoueurPS = null;
		if(cnx != null)
        {
            try
            {
				contientThisJoueurPS = cnx.prepareStatement("SELECT COUNT(id) FROM `API_Joueur` WHERE nom = ?");
		    }
            catch(SQLException se)
            {
                System.err.println(se);
            }   
		}
		ResultSet rs;
		if(contientThisJoueurPS != null)
        {
            try
            {
				contientThisJoueurPS.setString(1,JoueurBD.nom);
				rs = contientThisJoueurPS.executeQuery();
				boolean resultB = false;
				while(rs.next())
				{
					resultB = rs.getInt(1) > 0;
				}
				rs.close();
				return resultB;
            }
            catch(SQLException se)
            {
                System.err.println(se);
            }   
        }
   	   return false;
   }

   public static int getIdJoueur()
   {
		if(JoueurBD.idJoueur < 0 )
		{
			Connection cnxstatic = ConnectionBD.getConnection();
			PreparedStatement getIdPS;
			ResultSet rsGetID;
			try
            {
				getIdPS = cnxstatic.prepareStatement("SELECT id FROM `API_Joueur` WHERE nom = ?");
				getIdPS.setString(1,JoueurBD.nom);
				rsGetID = getIdPS.executeQuery();
				JoueurBD.idJoueur = 0;
				while(rsGetID.next())
				{
					JoueurBD.idJoueur = rsGetID.getInt(1);
				}
				rsGetID.close();
				return JoueurBD.idJoueur; 
            }
            catch(SQLException se)
            {
                System.err.println(se);
            } 
		}
		return JoueurBD.idJoueur;
   }

   private void newJoueurBD()
   {
		if(JoueurBD.contientThisJoueur())
		{
			return;
		}
        if(this.nouveauJoueurPS != null)
        {
			this.addVisited(this.getPiece());
            try
            {
				this.nouveauJoueurPS.setInt(1,JoueurBD.idJoueur);
				this.nouveauJoueurPS.setString(2,JoueurBD.nom);
                this.nouveauJoueurPS.executeUpdate();
            }
            catch(SQLException se)
            {
                System.err.println(se);
            } 
        }
   }

   @Override
    public Piece getPiece()
	{
        return (Piece)GestionIDBD.getElement(this.getPieceID(),"fr.iutfbleau.projetIHM2020FI2.MODEL.PieceBD");
    }

	public int getPieceID()
	{
		if(this.getPiecePS != null)
        {
			try
            {
				this.getPiecePS.setInt(1,JoueurBD.idJoueur);
                this.rs = this.getPiecePS.executeQuery();
				while(this.rs.next())
				{
					JoueurBD.idPosPiece = this.rs.getInt(1);
				}
                this.rs.close();
				return JoueurBD.idPosPiece;
            }
            catch(SQLException se)
            {
                System.err.println(se);
            } 
		}
		return JoueurBD.idPosPiece;
	}

   @Override
    public void setPiece(Piece next)
	{
		if(this.setPiecePS != null)
        {
			this.addVisited(next);
			try
            {
				JoueurBD.idPosPiece = GestionIDBD.getID(next);
				this.setPiecePS.setInt(1,JoueurBD.idPosPiece);
				this.setPiecePS.setInt(2,JoueurBD.idJoueur);
                this.setPiecePS.executeUpdate();
            }
            catch(SQLException se)
            {
                System.err.println(se);
            } 
		}		
    }

    @Override
    public Iterator<Piece> getVisited()
	{
		List<Piece> cervV;
		cervV = new LinkedList<Piece>();
		if(this.getVisitedPS != null)
		{
			try
            {
				this.getVisitedPS.setInt(1,JoueurBD.getIdJoueur());
				this.getVisitedPS.setInt(2,1);
				this.rs = this.getVisitedPS.executeQuery();
				while(this.rs.next())
				{
					cervV.add((Piece)GestionIDBD.getElement(this.rs.getInt(1),"fr.iutfbleau.projetIHM2020FI2.MODEL.PieceBD"));
				}
				this.rs.close();
				return cervV.iterator();
            }
            catch(SQLException se)
            {
                System.err.println(se);
            } 
		}
        return cervV.iterator();
    }

    @Override
    public boolean addVisited(Piece p)
	{
        Objects.requireNonNull(p,"On ne peut pas ajouter une pièce null.");
		if(isVisited(p))
		{
			return false;
		}
		if(this.setAddVisitedPS != null)
		{
			try
            {
				this.setAddVisitedPS.setInt(1,1);
				this.setAddVisitedPS.setInt(2,GestionIDBD.getID(p));
				this.setAddVisitedPS.setInt(3,JoueurBD.getIdJoueur());
				this.setAddVisitedPS.executeUpdate();
				return true;
            }
            catch(SQLException se)
            {
                System.err.println(se);
            } 
		}
        return false;
    }
    

    @Override
    public boolean isVisited(Piece p)
	{
        Objects.requireNonNull(p,"On ne peut pas connaître une pièce null.");
		if(this.getIsVisitedPS != null)
		{
			try
            {
				this.getIsVisitedPS.setInt(1,GestionIDBD.getID(p));
				this.getIsVisitedPS.setInt(2,JoueurBD.getIdJoueur());
				this.rs = this.getIsVisitedPS.executeQuery();
				boolean resultB = false;
				while(this.rs.next() && !resultB)
				{
					resultB = this.rs.getInt(1) == 1;
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

    @Override
    public String getDescription()
    {
        StringBuilder sb = new StringBuilder("");
        int goodies = super.combienTrucs();
        if (goodies == 0)
            sb.append("\n"+"Le sac à dos ne contient pas d'objet. ");
        else if (goodies == 1)  
            sb.append("\n"+"Le sac à dos contient un objet : ");
        else sb.append("\n"+ "Le sac à dos contient "+ super.combienTrucs()+" objets : ");
        Iterator<Truc> goods = super.getTrucs();
        while (goods.hasNext()){
            Truc t = goods.next();
            sb.append("\n _ " + t.getDescription() +".");
        }
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public boolean agir (Truc t)
    {
        if (t == null){
            return false;
        }
		TypeTruc tta = t.getTypeTruc();
        if (!super.containsTruc(t))
		{
            throw new IllegalStateException("le joueur ne porte pas l'objet");
		}
        else if (Objects.equals(tta,TypeTruc.ALCOOL))
		{
            //équivalent de clear() dans JoueurNP
            try
            {
                this.clearViste.setInt(1,JoueurBD.idJoueur);
                this.clearViste.executeUpdate();
            }
            catch(SQLException se)
            {
                System.err.println(se);
            } 
            

            super.removeTruc(t);
            return true;
        }
        else if (Objects.equals(tta,TypeTruc.EAU))
		{
            super.removeTruc(t);
            return true;
        }
     
		return false;
    }
}
