package fr.iutfbleau.projetIHM2020FI2.MODEL;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.sql.*;
import java.util.*;
/**
 * Permet de gérer la topologie du dongeon en créant et manipulant des objets 
 * _ pour les pièces (implémentant l'interface Piece) et 
 * _ des objets modélisant les accès d'une pièce à l'autre (instances de classes implémentant l'interface Passage). 
 * 
 * 
 * Cette classe n'offre pas de gestion persistante des objets.
 * 
 * Cette classe utilise de manière indépendante PieceBD et PassageBD.
 * L'implémentation actuelle est en fait quasi-générique et pourrait employer n'importe quelle classe implémentant les interfaces Piece et Passage.
 */
public class PassagePieceFactoryBD implements PassagePieceFactory
{

	//SQL
	private Connection cnx;
	private PreparedStatement contientPiecePS;
	private PreparedStatement removePassagePS;
	private PreparedStatement contientPassagePS;
	private ResultSet rs;
	private int autoIncrementIDPiece = 0;
	private int autoIncrementIDPassage = 0;

	/**
	 * constructeur
	 *
	 */
	public PassagePieceFactoryBD()
	{
		this.cnx = ConnectionBD.getConnection();
        if(this.cnx != null)
        {
            try
            {                
				this.contientPiecePS = this.cnx.prepareStatement("SELECT Count(id) FROM API_Piece WHERE id = ? AND idJoueur = ?");
				this.contientPassagePS  = this.cnx.prepareStatement("SELECT Count(id) FROM API_Passage WHERE id = ? AND idJoueur = ?");
				this.removePassagePS = this.cnx.prepareStatement("DELETE FROM API_Passage WHERE id = ? AND idJoueur = ?");
            }
            catch(SQLException se)
            {
                System.err.println(se);
            }   
        }
	}


	/**
	 * ajoute et retourne une nouvelle pièce.
	 * @return la nouvelle pièce
	 */
	@Override
	public Piece newPiece(){
		Piece p = new PieceBD(autoIncrementIDPiece++);
		return p;
	}

	/**
	 * Enlève une pièce, effet cascade sur tous les passages comportant cette pièce et 
	 * les autres pièces de ces passages.
	 * @param  p1 une pièce
	 * @return   vrai si la pièce était bien présente, faux sinon.
	 */
	@Override
	public boolean removePiece(Piece p1){
		Objects.requireNonNull(p1,"On ne peut pas enlever une pièce null.");
		Piece p2;
		if (this.containsPiece(p1)){//on enlève le sommet de l'univers
			for (Direction d1 : Direction.values()){
            	if (p1.getPassage(d1) != null){//pour tout couloir qui sort
            		// On doit trouver la porte à murer dans l'autrePiece
            		p2 = p1.getPassage(d1).getAutrePiece(p1);// p1 -- p2
            		for (Direction d2 : Direction.values()){
            			if(p2.getPassage(d2)!= null && p2.getPassage(d2).equals(p1.getPassage(d1))){
            				p2.removePassage(d2);// murer le passage dans p2.
            				break;
            			}
            		}
            		p1.removePassage(d1);
            	}
            }	
        	return true;
		}
		else return false;
	}

	private boolean containsPiece(Piece p)
	{
    	Objects.requireNonNull(p,"On ne peut pas contenir une Piece null");
		if(this.contientPiecePS != null)
		{
			try
			{
				this.contientPiecePS.setInt(1,GestionIDBD.getID(p));
				this.contientPiecePS.setInt(2,JoueurBD.getIdJoueur());
				this.rs = this.contientPiecePS.executeQuery();
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
	 * Enlève un passage.
	 * @param  p passage à enlever
	 * @return    vrai ssi le passage devait être enlevé.
	 */
	@Override
	public boolean removePassage(Passage p)
	{
		Objects.requireNonNull(p,"On ne peut pas suprimer un Passage null");
		if(!JoueurBD.getIsInstantiate() && GameStart.get())
		{
			return false;
		}
		if(!this.containsPassage(p))
		{
			return false;
		}
		if(this.removePassagePS != null)
		{
			try
			{
				this.removePassagePS.setInt(1,GestionIDBD.getID(p));
				this.removePassagePS.setInt(2,JoueurBD.getIdJoueur());
				this.removePassagePS.executeUpdate();
				return true;
			}
			catch(SQLException se)
			{
				System.err.println(se);
			}  			
		}
		return false;
	}

	private boolean containsPassage(Passage p)
	{
    	Objects.requireNonNull(p,"On ne peut pas contenir un Passage null");
		if(this.contientPassagePS != null)
		{
			try
			{
				this.contientPassagePS.setInt(1,GestionIDBD.getID(p));
				this.contientPassagePS.setInt(2,JoueurBD.getIdJoueur());
				this.rs = this.contientPassagePS.executeQuery();
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
	 * Ajoute et retourne un nouveau passage
	 * La méthode ne vérifie pas que les directions sont compatibles.
	 * @param  d1 direction d'ajout dans la pièce p1 (normalement libre)
	 * @param  p1 pièce existante, qui n'est pas null
	 * @param  d2 direction d'ajout dans la pièce p1 (normalement libre)
	 * @param  p2 pièce existante, qui n'est pas null
	 * @return    un nouveau passage depuis p1 dans la direction d1 vers la pièce p2 dans la direction d2.
	 * @throws NullPointerException si un argument est null
	 * @throws IllegalArgumentException si la direction d'une pièce n'est pas libre ou si les pièces sont identiques
	 */
	@Override
	public Passage newPassage(Direction d1, Piece p1, Direction d2, Piece p2)
	{
		Objects.requireNonNull(p1,"La pièce p1 ne peut être null.");
		Objects.requireNonNull(d1,"La direction d1 ne peut être null.");
		Objects.requireNonNull(p2,"La pièce p2 ne peut être null.");
		Objects.requireNonNull(d2,"La direction d2 ne peut être null.");
		if (p1.getPassage(d1) != null)
			throw new IllegalArgumentException("La direction d1 n'est pas libre dans la pièce p1");
		if (p2.getPassage(d2) != null)
			throw new IllegalArgumentException("La direction d2 n'est pas libre dans la pièce p2");
		Passage p = new PassageBD(p1,p2,autoIncrementIDPassage++);//throws IllegalArgumentException si pièces identiques
		p1.setPassage(d1,p);
		p2.setPassage(d2,p);
		return p;
	}

}
