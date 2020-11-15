package fr.iutfbleau.projetIHM2020FI2.VUE;

public class ChangementThread  implements Runnable 
{
    private PieceVue pieceV;
    private boolean state;// true = droite, false = gauche 
    private long wait = 0;
	private int nbNext = 0;
	private int indice = 0;

	/**
	 * constructeur
	 * @param nb nombre de changement d'image
	 * @param w temps d'attente entre chaque changement
	 * @param b Gauche ou droite
	 * @param pv la vue qui corespond a l'ensemble de la piece
	 */
    public ChangementThread(PieceVue pv,boolean b,long w,int nb) 
    {
        this.pieceV = pv;
        this.state = b;
        this.wait = w;
		this.nbNext = nb;
    }
 
    public void run() 
    {
		this.pieceV.setAnimChange(true);
		for(int i = 0; i < this.nbNext;i++)
		{
			try 
			{
				Thread.sleep(this.wait);
			}
			catch (InterruptedException e) 
			{
				System.out.println(e.getMessage());	
			}
			pieceV.changement(this.state);
		}
		this.pieceV.setAnimChange(false);
    }
 }