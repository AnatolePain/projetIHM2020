package fr.iutfbleau.projetIHM2020FI2.MODEL;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.sql.*;
import java.util.*;

public class JoueurBD extends ContientTrucsBD implements Joueur
{
    private Piece p;
    private Connection cnx;
    private PreparedStatement nouveauJoueur;
    private PreparedStatement getJoueur;
    private PreparedStatement getCerveau;
    private PreparedStatement setCerveau;
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
                this.nouveauJoueur = ConnectionBD.getConnection().prepareStatement("INSERT INTO `API_Joueur` (`id`, `idPieceActuelle`) VALUES ('?', '?')");
                //this.setCerveau  = ConnectionBD.getConnection().prepareStatement("SELECT * FROM `API_Joueur` WHERE 1");
            }
            catch(SQLException se)
            {
                System.err.println(se);
            }   
        }
   }

   private void chargementDonneBD()
   {
        if(this.nouveauJoueur != null)
        {
            try
            {
                this.rs = nouveauJoueur.executeQuery();
                this.rs.close();
            }
            catch(SQLException se)
            {
                System.err.println(se);
            } 
        }
   }

   @Override
    public Piece getPiece(){
        return this.p;
    }

   @Override
    public void setPiece(Piece next){
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
