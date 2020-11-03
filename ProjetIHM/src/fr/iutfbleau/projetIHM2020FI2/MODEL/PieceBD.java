package fr.iutfbleau.projetIHM2020FI2.MODEL;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.sql.*;
import java.util.*;
/**
 * Implémentation non persistante d'une piece
 * Une piece a potentiellement un passage dans chaque direction
 *
 Une piece contient des objets (qu'on appelle des trucs pour bien faire la différence avec Object).
 */
public class PieceBD extends ContientTrucsBD implements Piece{
	
    // Pour stocker les passages on utilise un dictionnaire.
    private  Map<Direction, Passage> sortie;
	
	//SQL
	private Connection cnx;
	private PreparedStatement nouvellePiecePS;
	private PreparedStatement setPassageAPS;
	private PreparedStatement setPassageBPS;
	private PreparedStatement getDescriptionPS;
	private PreparedStatement getPassagePS;
	private ResultSet rs;
	private int idPiece = 0;
    /**
     * Constructeur
     *
     * On unitilise un map spécialisée pour les clés qui sont des enums. 
     * @return [description]
     */
    public PieceBD(int id){
        super();
        this.sortie=new EnumMap<Direction,Passage>(Direction.class);
		piece = this;
		this.idPiece = id;
		this.cnx = ConnectionBD.getConnection();
		if(this.cnx != null)
        {
            try
            {
				this.nouvellePiecePS = this.cnx.prepareStatement("INSERT INTO `API_Piece` (`id`, `idJoueur`, `Visite`) VALUES ('?', '?', '0');");
				this.setPassageAPS = this.cnx.prepareStatement("UPDATE `API_Passage` SET `DirectionA` = ?  WHERE `API_Passage`.`idPieceA` = ? AND `API_Passage`.`id` = ? AND `API_Passage`.`idJoueur` = ?;");
				this.setPassageBPS = this.cnx.prepareStatement("UPDATE `API_Passage` SET `DirectionB` = ?  WHERE `API_Passage`.`idPieceB` = ? AND `API_Passage`.`id` = ? AND `API_Passage`.`idJoueur` = ?;");
				this.getDescriptionPS = this.cnx.prepareStatement("SELECT Description FROM API_Piece WHERE id = ? And idJoueur = ?");
				this.getPassagePS = this.cnx.prepareStatement("SELECT id FROM `API_Passage` WHERE idJoueur = ? AND (idPieceA = ? AND DirectionA = ? or idPieceB = ? AND DirectionB = ?)");
            }
            catch(SQLException se)
            {
                System.err.println(se);
            }   
        }
		newPieceBD();
    }

	private void newPieceBD()
	{
		try
		{
			int idJoueur = JoueurBD.getIdJoueur();
			this.nouvellePiecePS.setInt(1,this.idPiece);
			this.nouvellePiecePS.setInt(2,idJoueur);
			this.rs = this.nouvellePiecePS.executeQuery();
            this.rs.close();
			GestionIDBD.put(this,this.idPiece);
		}
		catch(SQLException se)
		{
			System.err.println(se);
		}   
	}

	public int getPieceId()
	{
		return this.idPiece;
	}

	/**
	 * permet d'associer le passage passé en paramètre dans la direction proposée.
	 * En dehors de l'étape de création des pièces et passages, il faut que les choix soient cohérents.
	 * Les deux arguments ne doivent pas être null.
	 * @throws NullPointerException si un argument est null.
	 * @param p passage à associer
	 * @param d direction du passage recherché
	 */
    @Override
    public void setPassage(Direction d, Passage p){
    	Objects.requireNonNull(d,"On ne peut pas ajouter un passage dans une direction null.");
    	Objects.requireNonNull(p,"On ne peut pas ajouter un passage null.");
    	this.sortie.put(d,p);
		try
		{
			int idJoueur = JoueurBD.getIdJoueur();
			this.setPassageAPS.setString(1,d.toString());
			this.setPassageAPS.setInt(2,idPiece);
			this.setPassageAPS.setInt(3,GestionIDBD.getID(p));
			this.setPassageAPS.setInt(4,idJoueur);
			this.setPassageBPS.setString(1,d.toString());
			this.setPassageBPS.setInt(2,idPiece);
			this.setPassageBPS.setInt(3,GestionIDBD.getID(p));
			this.setPassageBPS.setInt(4,idJoueur);
			this.setPassageAPS.executeUpdate();
			this.setPassageBPS.executeUpdate();
		}
		catch(SQLException se)
		{
			System.err.println(se);
		}  
    }
	
    /**
     * Permet de supprimer un passage (mais seulement pour la Piece).
     * C'est-à-dire que getPassage(d) retournera null après un appel de cette méthode.
     * @param d Direction dans laquelle on veut "annuler" le passage.
     */
    @Override
    public void removePassage(Direction d){
        this.sortie.remove(d);
    }

	/**
	 * permet de renvoyer le passage dans la direction proposée
	 * @param  d direction du passage recherché
	 * @return   un passage si il y en a un et null sinon.
	 */
    @Override
    public Passage getPassage(Direction d){
    	return this.sortie.get(d);
    } 

    /**
     * le nombre de passages (0 n'est pas recommandé pour l'intérêt du jeu, mais est une valeur légale).
     * @return un entier positif ou nul, au maximum le nombre de directions possibles.
     * @see Direction
     */
    @Override
    public int combienPassages(){
    	return this.sortie.size();
    }
    
    /**
     * Accesseur pour la description textuelle de la pièce.
     * @return description
     */
    @Override
    public String getDescription(){
        // Stringbuilder is the most efficient method of building a String like datastructure incrementally. 
        StringBuilder sb = new StringBuilder("");
        int passages = this.combienPassages();
        if (passages == 0)
        	sb.append("La pièce n'a pas de sortie. ");
        else if (passages == 1)
        	sb.append("La pièce a une seule sortie. ");
        else sb.append("La pièce a "+ passages +" sorties. ");
        for (Direction d : Direction.values()){
            if (this.getPassage(d) != null)
            	if (d == Direction.EST || d == Direction.OUEST)
                	sb.append("À l'" + d + ", un " +this.getPassage(d).getDescription()+". ");
                else sb.append("Au " + d + ", un " +this.getPassage(d).getDescription()+". ");
        }
        int goodies = this.combienTrucs();
        if (goodies == 0)
        	sb.append("\n"+"La pièce ne contient pas d'objet. ");
        else if (goodies == 1)	
        	sb.append("\n"+"La pièce contient un objet : ");
        else sb.append("\n"+ "La pièce contient "+ this.combienTrucs()+" objets : ");
        Iterator<Truc> goods = this.getTrucs();
        while (goods.hasNext()){
        	Truc t = goods.next();
        	sb.append("\n _ " + t.getDescription() +".");
        }
        //sb.append("\n");
        return sb.toString();
    }
}
