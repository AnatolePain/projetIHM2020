public class TransitionThread  implements Runnable 
{
    private ImageDonjon gc;
    private long wait = 0;
	private float pi = 0;

    public TransitionThread(ImageDonjon g,long w) 
    {
        this.gc = g;
        this.wait = w;
    }
 
    public void run() 
    {
			while(this.pi < Math.PI)
			{
				try 
				{
					Thread.sleep(this.wait);
				}
				catch (InterruptedException e) 
				{
				}
				gc.setAlpha((float)Math.sin(pi));
				this.pi+= 0.01f;
			}
	}
 }