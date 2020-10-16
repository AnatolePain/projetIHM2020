public class ChangementThread  implements Runnable 
{
    private PieceVue pieceV;
    private boolean state;
    private long wait = 0;
	private int nbNext = 0;
	private int indice = 0;

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