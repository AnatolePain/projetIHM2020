package fr.iutfbleau.projetIHM2020FI2.MODEL;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.util.*;
/**
 * Impl�mentation non persistante d'un Joueur
 */
public class JoueurBD extends ContientTrucsBD implements Joueur
{

	private ConnectionBD cBD;
    //piece actuelle
    private Piece p;

    // conteneur de Pi�ces.
    private List<Piece> cerveau;
    
    /**
    * Constructeur
    * 
    * On utilise un ensemble LinkedList pour g�rer la chronologie.
    *
    * Le joueur n'est pas dans une pi�ce � sa cr�ation.
    * @see setPiece() 
    */
    public JoueurBD(){
        super();
        this.cerveau = new LinkedList<Piece>();
		this.cBD = new ConnectionBD();
   }


   /** 
     * @return la piece dans laquelle le joueur se trouve
     * (null si la pi�ce n'est pas renseign�e)
    */
   @Override
    public Piece getPiece(){
    	return this.p;
    }
	
	/** 
     * Met � jour la piece dans laquelle le joueur se trouve
     * Demande l'ajout de l'ancienne pi�ce au "cerveau".
     * NB. il n'y a pas de v�rification dans Joueur.
    */
   @Override
    public void setPiece(Piece next){
    	if (this.getPiece() != null) 
            this.addVisited(this.getPiece());
    	this.p=next;
    }
    
    /**
     * @return les pieces que le joueur a deja visit�.
     */
    @Override
    public Iterator<Piece> getVisited(){
    	return this.cerveau.iterator();
    }
    
    /**
     * Ajoute la Piece si n�cessaire (si l'it�rateur retourn� par 
     * getVisited ne permet pas d'it�rer sur la Piece).
     * Une pi�ce devient visit�e quand on la quitte.
     * @param  p Pi�ce qu'on vient de (re)visiter
     * @throws NullPointerException la Piece ne peut pas �tre null
     * @return vrai si il fallait l'ajouter et faux sinon.
     */
    @Override
    public boolean addVisited(Piece p){
    	Objects.requireNonNull(p,"On ne peut pas ajouter une pi�ce null.");
    	return this.cerveau.add(p);
    }
    
    /**
 	* Teste si piece a �t� visit�e
 	* @param  p pi�ce
 	* @throws NullPointerException si la pi�ce est null 
 	* @return vrai si la pi�ce est connue.
 	*/
    @Override
    public boolean isVisited(Piece p){
    	Objects.requireNonNull(p,"On ne peut pas conna�tre une pi�ce null.");
    	return this.cerveau.contains(p);
    }
	
	/**
     * Accesseur pour la description textuelle du sac � dos.
     * @return description
     */
    @Override
    public String getDescription(){
        // Stringbuilder is the most efficient method of building a String like datastructure incrementally. 
        StringBuilder sb = new StringBuilder("");
        int goodies = super.combienTrucs();
        if (goodies == 0)
        	sb.append("\n"+"Le sac � dos ne contient pas d'objet. ");
        else if (goodies == 1)	
        	sb.append("\n"+"Le sac � dos contient un objet : ");
        else sb.append("\n"+ "Le sac � dos contient "+ super.combienTrucs()+" objets : ");
        Iterator<Truc> goods = super.getTrucs();
        while (goods.hasNext()){
        	Truc t = goods.next();
        	sb.append("\n _ " + t.getDescription() +".");
        }
        sb.append("\n");
        return sb.toString();
    }
    /**
     * Le joueur non persistent n'est pas tout � fait comme le buveur du petit prince.
     * Il ne boit pas du rhum pour oublier qu'il en boit mais pour oublier les pi�ces qu'il conna�t.
     * @param  t un truc que le joueur doit poss�der
     * @throws IllegalStateException  sinon
     * @return vrai si l'objet a un effet sur le joueur
     */
    @Override
    public boolean agir (Truc t){
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
