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

    public TransitionThread(ImageDonjon g,float s,PieceVue pv,int[] d) 
    {
        this.gc = g;
        this.speed = s;
		this.pieceV = pv;
		this.direction = d;
    }
 
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
			if(this.sinusPi == 1)
			{
				this.pieceV.reCreate(this.direction);
			}
		}
		this.pieceV.setTransition(false);
	}
 }