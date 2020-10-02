public class GalerieThread  implements Runnable 
{
    private GalerieChangementCL gc;
    private boolean state;
    private long wait = 0;

    public GalerieThread(GalerieChangementCL g,boolean b,long w) 
    {
        this.gc = g;
        this.state = b;
        this.wait = w;
    }
 
    public void run() 
    {
        try {
      
            Thread.sleep(this.wait) ;
         }  catch (InterruptedException e) {
         
             // gestion de l'erreur
         }
        gc.changement(this.state);
    }
 }