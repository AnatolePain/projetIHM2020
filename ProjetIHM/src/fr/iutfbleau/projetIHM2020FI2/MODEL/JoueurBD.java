package fr.iutfbleau.projetIHM2020FI2.MODEL;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.sql.*;
import java.util.*;

public class JoueurBD extends ContientTrucsBD implements Joueur
{
    private Piece p;
    private Connection cnx;
    private PreparedStatement nouveauJoueurPS;
    private PreparedStatement getPiecePS;
	private PreparedStatement setPiecePS;
    private PreparedStatement getCerveauPS;
	private static int idJoueur = 0;
    private ResultSet rs;

    private List<Piece> cerveau;

    public JoueurBD()
    {
        super();
        this.cerveau = new LinkedList<Piece>();
        this.cnx = ConnectionBD.getConnection();
        if(this.cnx != null)
        {
            try
            {
                this.nouveauJoueurPS = this.cnx.prepareStatement("INSERT INTO `API_Joueur` (`id`, `idPieceActuelle`) VALUES ('0', '0')");
				this.getPiecePS = this.cnx.prepareStatement("SELECT idPieceActuelle FROM `API_Joueur` WHERE id = ?");
				this.setPiecePS = this.cnx.prepareStatement("UPDATE API_Joueur SET idPieceActuelle = ? WHERE id = ?");
				this.getCerveauPS = this.cnx.prepareStatement("SELECT id,Visite FROM `API_Piece` WHERE idJoueur = ?");
            }
            catch(SQLException se)
            {
                System.err.println(se);
            }   
        }
		newJoueurBD();
   }

   public static int getIdJoueur()
   {
		/*Connection cnxstatic = ConnectionBD.getConnection();
		PreparedStatement getIdPS = cnxstatic.prepareStatement("");*/
		return JoueurBD.idJoueur;
   }

   private void newJoueurBD()
   {
        if(this.nouveauJoueurPS != null)
        {
            try
            {
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
		if(this.getPiecePS != null)
        {
			try
            {
                this.rs = getPiecePS.executeQuery();
                this.rs.close();
            }
            catch(SQLException se)
            {
                System.err.println(se);
            } 
		}
        return this.p;
    }

   @Override
    public void setPiece(Piece next)
	{
		if(this.setPiecePS != null)
        {
			try
            {
				this.setPiecePS.setInt(1,GestionIDBD.getIdPiece(next));
				this.setPiecePS.setInt(2,idJoueur);
                this.setPiecePS.executeUpdate();
            }
            catch(SQLException se)
            {
                System.err.println(se);
            } 
		}
        if (this.getPiece() != null) 
            this.addVisited(this.getPiece());
        this.p=next;
    }

    @Override
    public Iterator<Piece> getVisited(){
        return this.cerveau.iterator();
    }

    @Override
    public boolean addVisited(Piece p){
        Objects.requireNonNull(p,"On ne peut pas ajouter une pièce null.");
        return this.cerveau.add(p);
    }
    

    @Override
    public boolean isVisited(Piece p){
        Objects.requireNonNull(p,"On ne peut pas connaître une pièce null.");
        return this.cerveau.contains(p);
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
        else if (super.containsTruc(t))
            throw new IllegalStateException("le joueur ne porte pas l'objet");
        else if (Objects.equals(t.getTypeTruc(),TypeTruc.ALCOOL)){
            super.removeTruc(t);
            this.cerveau.clear();
            return true;
        }
        else if (Objects.equals(t.getTypeTruc(),TypeTruc.EAU)){
            super.removeTruc(t);
            return true;
        }
        else return false;
    }
}
