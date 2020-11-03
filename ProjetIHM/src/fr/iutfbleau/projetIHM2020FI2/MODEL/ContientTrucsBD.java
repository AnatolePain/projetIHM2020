package fr.iutfbleau.projetIHM2020FI2.MODEL;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.sql.*;
import java.util.*;
/**
 * Implémentation non persistante.
 * Il s'agit en fait à peu de chose près d'une façade sur Set<Truc>.
 */
public class ContientTrucsBD implements ContientTrucs{

    /**
     * Conformément aux recommandations de la documentation de l'interface, il faut un ensemble.
     */
	private Set<Truc> contenu;
	private Connection cnx;
	private PreparedStatement addTrucPS;
	private ResultSet rs;
	protected PieceBD piece;
	protected JoueurBD joueur;
    /**
     * Constructeur.
     * On utilise un LinkedHashSet qui permet de garantir que l'ordre des trucs reste le même.
     */
    public ContientTrucsBD(){
        this.contenu= new LinkedHashSet<Truc>();	
		this.cnx = ConnectionBD.getConnection();
        if(this.cnx != null)
        {
            try
            {
				this.addTrucPS = this.cnx.prepareStatement("UPDATE API_Truc SET idPiecePos = ?,has = ? WHERE API_Truc.id = ? AND API_Truc.idJoueur = ?;");
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
    public Iterator<Truc> getTrucs(){
    	return this.contenu.iterator();
    }
    
    /**
     * Ajoute le truc si nécessaire
     * @param  t Truc à ajouter
     * @return   vrai si il fallait l'ajouter et faux sinon.
     * @throws NullPointerException si t est null
     */
    @Override
    public boolean addTruc(Truc t){
    	Objects.requireNonNull(t,"On ne peut pas ajouter un truc null.");

		try
        {
			this.addTrucPS.setInt(1,(piece != null) ? GestionIDBD.getID(piece) : 0);
			this.addTrucPS.setInt(2,joueur != null ? 1 : 0);
			this.addTrucPS.setInt(3,GestionIDBD.getID(t));
			this.addTrucPS.setInt(4,JoueurBD.getIdJoueur());
			this.addTrucPS.executeUpdate();
        }
        catch(SQLException se)
        {
			System.err.println(se);
        }   

		return this.contenu.add(t);
    }

    /**
     * Enlève le truc si nécessaire
     * @param  t Truc à enleverer
     * @return   vrai si il fallait l'enlever et faux sinon.
     * @throws NullPointerException si t est null
     */
    @Override
    public boolean removeTruc(Truc t){
    	Objects.requireNonNull(t,"On ne peut pas enlever un truc null.");
    	return this.contenu.remove(t);
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
    	return this.contenu.contains(t);
    }  
   
    /**
     * pour obtenir le nombre de trucs contenus.
     * @return un entier positif ou nul.
     */
    @Override
    public int combienTrucs(){
    	return this.contenu.size();
    }

}
