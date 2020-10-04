public class GalerieThread  implements Runnable 
{
    private PieceVue gc;
    private boolean state;
    private long wait = 0;
	private int nbNext = 0;

    public GalerieThread(PieceVue g,boolean b,long w,int nb) 
    {
        this.gc = g;
        this.state = b;
        this.wait = w;
		this.nbNext = nb;
    }
 
    public void run() 
    {
		for(int i = 0; i < this.nbNext;i++)
		{
			try 
			{
				Thread.sleep(this.wait);
			}
			catch (InterruptedException e) 
			{
			}
			gc.changement(this.state);
		}
    }
 }