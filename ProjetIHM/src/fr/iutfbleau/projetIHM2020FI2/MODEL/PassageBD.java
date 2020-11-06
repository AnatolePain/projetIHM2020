package fr.iutfbleau.projetIHM2020FI2.MODEL;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.sql.*;
import java.util.*;
// Un passage relie deux pièces.
// Le passage est ouvert, fermé (par défaut) ou fermé à clé.
// NB : Ce sont les pièces qui gèrent les directions.
public class PassageBD implements Passage 
{
	//SQL
	private Connection cnx;
	private PreparedStatement nouveauPassagePS;
	private PreparedStatement getAutrePassagePS;
	private PreparedStatement reliePiecePS;
	private PreparedStatement getEtatPassagePS;
	private PreparedStatement setEtatPassagePS;
	private ResultSet rs;
	private int idPassage = 0;

	/**
	 * constructeur
	 * Les deux pièces doivent être différentes et ne pas être null.
	 * @param  p1 pièce
	 * @param  p2 pièce distincte
	 * @throws IllegalArgumentException si pièces identiques.
	 */
	public PassageBD(Piece p1, Piece p2,int id)
	{
		Objects.requireNonNull(p1,"On ne peut pas construire un passage vers une pièce null.");
		Objects.requireNonNull(p1,"On ne peut pas construire un passage vers une pièce null.");
		if(p1.equals(p2))
			throw new IllegalArgumentException("Les pièces ne peuvent pas être identiques");
		this.cnx = ConnectionBD.getConnection();
		this.idPassage = id;
		if(this.cnx != null)
        {
            try
            {
				this.nouveauPassagePS = this.cnx.prepareStatement("INSERT INTO `API_Passage`(`id`, `idJoueur`, `idPieceA`, `DirectionA`, `idPieceB`, `DirectionB`, `EtatPassage`) VALUES (?,?,?,0,?,0,?)");
				this.getAutrePassagePS = this.cnx.prepareStatement("SELECT idPieceA,idPieceB FROM API_Passage WHERE id = ? AND idJoueur = ?");
				this.reliePiecePS = this.cnx.prepareStatement("SELECT COUNT(id) FROM `API_Passage` WHERE id = ? AND idJoueur = ? AND (idPieceA = ? OR idPieceB = ?)");
				this.getEtatPassagePS = this.cnx.prepareStatement("SELECT EtatPassage FROM `API_Passage` WHERE id = ? AND idJoueur = ?");
				this.setEtatPassagePS = this.cnx.prepareStatement("UPDATE API_Passage SET EtatPassage = ? WHERE id = ? AND idJoueur = ?");
            }
            catch(SQLException se)
            {
                System.err.println(se);
            }   
        }
		if(!GameStart.get())
		{
			newPassageBd(GestionIDBD.getID(p1),GestionIDBD.getID(p2));
		}
		GestionIDBD.put(this,idPassage);
	}

	private void newPassageBd(int idPieceA,int idPieceB)
	{
		if(this.nouveauPassagePS != null)
		{
			try
			{
				int idJoueur = JoueurBD.getIdJoueur();
				this.nouveauPassagePS.setInt(1,idPassage);
				this.nouveauPassagePS.setInt(2,idJoueur);
				this.nouveauPassagePS.setInt(3,idPieceA);
				this.nouveauPassagePS.setInt(4,idPieceB);
				this.nouveauPassagePS.setString(5,EtatPassage.CLOSED.toString());
				this.nouveauPassagePS.executeUpdate();
			}
			catch(SQLException se)
			{
				System.err.println(se);
			}
		}
	}

	/**
     * Normalement les deux pièces sont différentes. On donne une piece pour obtenir la piece de l'autre côté du passage.
     * @param  p pièce
     * @throws IllegalArgumentException si le passage ne relie pas p à une autre pièce
     * @return l'autre pièce que le passage relie à p.
     */
    @Override
    public Piece getAutrePiece(Piece p)
	{
		int argPiece = GestionIDBD.getID(p);
		if(this.getAutrePassagePS != null)
		{
			try
			{
				int idJoueur = JoueurBD.getIdJoueur();
				this.getAutrePassagePS.setInt(1,idPassage);
				this.getAutrePassagePS.setInt(2,idJoueur);
				this.rs = this.getAutrePassagePS.executeQuery();
				int idPA = 0;
				int idPB = 0;
				while(this.rs.next())
				{
					idPA = this.rs.getInt(1);
					idPB = this.rs.getInt(2);
				}
				this.rs.close();
				if(idPA == argPiece)
				{
					return (Piece)GestionIDBD.getElement(idPB,"fr.iutfbleau.projetIHM2020FI2.MODEL.PieceBD");
				}
				else if(idPB == argPiece)
				{
					return (Piece)GestionIDBD.getElement(idPA,"fr.iutfbleau.projetIHM2020FI2.MODEL.PieceBD");
				}
				else
				{
					throw new IllegalArgumentException("La pièce p ne relie pas ce passage.");
				}
			}
			catch(SQLException se)
			{
				System.err.println(se);
			}
		}
		throw new IllegalStateException("PreparedStatement getAutrePassagePS dans PassageBD n'est pas initialiser");
    }

    /**
     * Normalement un passage relie deux pièces distinctes. 
     * 
     * @return un itérateur sur les pièces du passage.
     */
    @Override
    public Iterator<Piece> getPieces()
	{
		List<Piece> piecesI; 
		piecesI = new LinkedList<Piece>();
		if(this.getAutrePassagePS != null)
		{
			try
			{
				int idJoueur = JoueurBD.getIdJoueur();
				this.getAutrePassagePS.setInt(1,idPassage);
				this.getAutrePassagePS.setInt(2,idJoueur);
				this.rs = this.getAutrePassagePS.executeQuery();
				while(this.rs.next())
				{
					piecesI.add((Piece)GestionIDBD.getElement(this.rs.getInt(1),"fr.iutfbleau.projetIHM2020FI2.MODEL.PieceBD"));
				}
				this.rs.close();
				return piecesI.iterator();
			}
			catch(SQLException se)
			{
				System.err.println(se);
			}
		}
    	return piecesI.iterator();
    }

    /**
     * teste si la pièce est liée à une autre pièce par ce passage
     * @param  p pièce à tester
     * @return vrai ssi c'est le cas.
     */
    @Override
    public boolean reliePiece(Piece p)
	{
		if(this.reliePiecePS != null)
		{
			try
			{
				int idJoueur = JoueurBD.getIdJoueur();
				int idp = GestionIDBD.getID(p);
				this.reliePiecePS.setInt(1,idPassage);
				this.reliePiecePS.setInt(2,idJoueur);
				this.reliePiecePS.setInt(3,idp);
				this.reliePiecePS.setInt(4,idp);
				this.rs = this.reliePiecePS.executeQuery();
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

    @Override
    public EtatPassage getEtatPassage()
	{
		if(this.getEtatPassagePS != null)
		{
			try
			{
				this.getEtatPassagePS.setInt(1,idPassage);
				this.getEtatPassagePS.setInt(2,JoueurBD.getIdJoueur());
				this.rs = this.getEtatPassagePS.executeQuery();
				EtatPassage resultE = EtatPassage.CLOSED;
				while(this.rs.next())
				{
					String result = this.rs.getString(1);

					for (EtatPassage e : EtatPassage.values())
					{
						if (e.toString() == result)
						{
							resultE = e;
						}
					}
				}
				this.rs.close();
				return resultE;
			}
			catch(SQLException se)
			{
				System.err.println(se);
			}
		}
		return EtatPassage.CLOSED;
    }

    /**
     * change l'état du passage
     * e ne doit pas être null 
     * @param e 
     * @throws NullPointerException
     */
    @Override
    public void setEtatPassage(EtatPassage e)
	{
    	Objects.requireNonNull(e,"e doit être un etat qui n'est pas null.");
		if(this.setEtatPassagePS != null)
		{
			try
			{
				this.setEtatPassagePS.setString(1,e.toString());
				this.setEtatPassagePS.setInt(2,idPassage);
				this.setEtatPassagePS.setInt(3,JoueurBD.getIdJoueur());
				this.setEtatPassagePS.executeUpdate();
			}
			catch(SQLException se)
			{
				System.err.println(se);
			}
		}
    }

    /**la méthode Agir est déclenchée par le joueur
     * avec un objet (un truc qui n'est pas null)
     * ou sans objet particulier (null) 
     * sur une classe.
     * La méthode retourne true si l'action a eu un effet et false sinon.
     */
    @Override
    public boolean agir(Truc t)
	{
		EtatPassage etatp = this.getEtatPassage();
     	if (t == null && etatp == EtatPassage.CLOSED)
		{
     		this.setEtatPassage(EtatPassage.OPEN);
     		return true;
     	}
     	else if (t == null && etatp == EtatPassage.OPEN){
     		this.setEtatPassage(EtatPassage.CLOSED);
     		return true;
     	}
     	else if (t == null){
     		return false;
     	}
     	// t != null
     	else if (Objects.equals(t.getTypeTruc(),TypeTruc.CLE) && etatp == EtatPassage.LOCKED){
     		this.setEtatPassage(EtatPassage.OPEN);
     		return true;
	     	}
	     else if (Objects.equals(t.getTypeTruc(),TypeTruc.CLE)){
	     	this.setEtatPassage(EtatPassage.LOCKED);
     		return true;
     	}
     	else return false;
     }
}
