package fr.iutfbleau.projetIHM2020FI2.VUE;

public class TransitionThread  implements Runnable 
{
	private PieceVue pieceV;
    private ImageDonjon gc;
    private float speed = 0;
	private float pi = 0;
	private long last_time = System.nanoTime();
	private long time = System.nanoTime();
	private float delta_time = 0;// temps entre chaque execution
	private int[] direction;
	private float sinusPi;

	/**
	 * constructeur
	 * @param g l'element sur lequel appliquer le fondu c'est celui que regarde le joueur
	 * @param s la vitesse du fondu
	 * @param pv la vue qui corespond a l'ensemble de la piece
	 */
    public TransitionThread(ImageDonjon g,float s,PieceVue pv) //ImageDonjon , pour appliquer la bonne transition 
    {
        this.gc = g;
        this.speed = s;
		this.pieceV = pv;
    }
 	/**
	 * On effectue le fondu aux noire via Sinus et temps entre chaque execution( delta_time) 
	 * ce calcule permet meme avec un pc tres lents de ne pas ralentire l'execution de l'annimation.
	 */
    public void run() 
    {
		this.pieceV.setTransition(true);
		while(this.pi < Math.PI)
		{
			this.time = System.nanoTime();
			this.delta_time = (float) ((float)(this.time - this.last_time) / 1000000000f);
			this.last_time = this.time;
			this.sinusPi = (float)Math.sin(this.pi);
			gc.setAlpha(this.sinusPi);
			this.pi +=  Math.PI * this.speed * this.delta_time;
			if(this.sinusPi == 1)//quand le fondu est tout noir je change les images de pièces
			{
				this.pieceV.reCreate();
			}
		}
		this.pieceV.setTransition(false);
	}
 }