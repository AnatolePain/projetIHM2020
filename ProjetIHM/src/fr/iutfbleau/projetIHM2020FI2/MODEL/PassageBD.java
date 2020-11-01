package fr.iutfbleau.projetIHM2020FI2.MODEL;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.sql.*;
import java.util.*;
// Un passage relie deux pièces.
// Le passage est ouvert, fermé (par défaut) ou fermé à clé.
// NB : Ce sont les pièces qui gèrent les directions.
public class PassageBD implements Passage 
{

    // ne contient que deux pièces.
	private List<Piece> pieces; 

	private EtatPassage e;

	//SQL
	private Connection cnx;
	private PreparedStatement nouveauPassagePS;
	private PreparedStatement getPassageIDPS;
	private ResultSet rs;
	private int idPassage = 0;

	/**
	 * constructeur
	 * Les deux pièces doivent être différentes et ne pas être null.
	 * @param  p1 pièce
	 * @param  p2 pièce distincte
	 * @throws IllegalArgumentException si pièces identiques.
	 */
	public PassageBD(Piece p1, Piece p2)
	{
		Objects.requireNonNull(p1,"On ne peut pas construire un passage vers une pièce null.");
		Objects.requireNonNull(p1,"On ne peut pas construire un passage vers une pièce null.");
		if(p1.equals(p2))
			throw new IllegalArgumentException("Les pièces ne peuvent pas être identiques");
        pieces = new LinkedList<Piece>();
		this.cnx = ConnectionBD.getConnection();
		if(this.cnx != null)
        {
            try
            {
				this.nouveauPassagePS = this.cnx.prepareStatement("INSERT INTO `API_Passage`(`id`, `idJoueur`, `idPieceA`, `DirectionA`, `idPieceB`, `DirectionB`) VALUES (0,?,?,0,?,0)");
				this.getPassageIDPS = this.cnx.prepareStatement("SELECT MAX(id) FROM API_Passage");
            }
            catch(SQLException se)
            {
                System.err.println(se);
            }   
        }

		newPassageBd(GestionIDBD.getIdPiece(p1),GestionIDBD.getIdPiece(p2));

		this.pieces.add(p1);
		this.pieces.add(p2);
		this.e = EtatPassage.CLOSED;
	}

	private void newPassageBd(int idPieceA,int idPieceB)
	{
		try
		{
			int idJoueur = JoueurBD.getIdJoueur();
			this.nouveauPassagePS.setInt(1,idJoueur);
			this.rs = this.nouveauPassagePS.executeQuery();
            this.rs.close();
			this.rs = getPassageIDPS.executeQuery();
			while(rs.next())
			{
				this.idPassage = rs.getInt(1);
			}
			this.rs.close();
			GestionIDBD.putPassageID(this,idPassage);
		}
		catch(SQLException se)
		{
			System.err.println(se);
		}   
	}

	/**
     * Normalement les deux pièces sont différentes. On donne une piece pour obtenir la piece de l'autre côté du passage.
     * @param  p pièce
     * @throws IllegalArgumentException si le passage ne relie pas p à une autre pièce
     * @return l'autre pièce que le passage relie à p.
     */
    @Override
    public Piece getAutrePiece(Piece p){
    	if (!this.reliePiece(p))
    		throw new IllegalArgumentException("La pièce p ne relie pas ce passage.");
    	else if (this.pieces.get(0).equals(p))
    		return this.pieces.get(1);
    	else return this.pieces.get(0);
    }

    /**
     * Normalement un passage relie deux pièces distinctes. 
     * 
     * @return un itérateur sur les pièces du passage.
     */
    @Override
    public Iterator<Piece> getPieces(){
    	return this.pieces.iterator();
    }

    /**
     * teste si la pièce est liée à une autre pièce par ce passage
     * @param  p pièce à tester
     * @return vrai ssi c'est le cas.
     */
    @Override
    public boolean reliePiece(Piece p){
    	return (this.pieces.contains(p));
    }

    @Override
    public EtatPassage getEtatPassage(){
    	return this.e;
    }

    /**
     * change l'état du passage
     * e ne doit pas être null 
     * @param e 
     * @throws NullPointerException
     */
    @Override
    public void setEtatPassage(EtatPassage e){
    	Objects.requireNonNull(e,"e doit être un etat qui n'est pas null.");
    	this.e=e;
    }

    /**la méthode Agir est déclenchée par le joueur
     * avec un objet (un truc qui n'est pas null)
     * ou sans objet particulier (null) 
     * sur une classe.
     * La méthode retourne true si l'action a eu un effet et false sinon.
     */
    @Override
     public boolean agir(Truc t){
     	if (t == null && this.getEtatPassage() == EtatPassage.CLOSED){
     		this.setEtatPassage(EtatPassage.OPEN);
     		return true;
     	}
     	else if (t == null && this.getEtatPassage() == EtatPassage.OPEN){
     		this.setEtatPassage(EtatPassage.CLOSED);
     		return true;
     	}
     	else if (t == null){
     		return false;
     	}
     	// t != null
     	else if (Objects.equals(t.getTypeTruc(),TypeTruc.CLE) && this.getEtatPassage() == EtatPassage.LOCKED){
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
