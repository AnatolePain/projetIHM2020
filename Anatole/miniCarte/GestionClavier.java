import java.awt.event.*;

public class GestionClavier implements KeyListener {


	private MiniCarteVue miniCarte;

	public GestionClavier(MiniCarteVue mc){
		miniCarte = mc;
	}


	//Invoked when a key has been pressed.
	@Override
	public void 	keyPressed(KeyEvent e){
		//VK_UP VK_DOWN VK_LEFT VK_RIGHT

		if(e.getKeyCode()== KeyEvent.VK_UP ){
			miniCarte.moveUp();
			System.out.println("UP");
		}else if(e.getKeyCode()== KeyEvent.VK_DOWN){
			miniCarte.moveDown();
		}else if(e.getKeyCode()== KeyEvent.VK_LEFT){
			miniCarte.moveLeft();
		}else if(e.getKeyCode()== KeyEvent.VK_RIGHT){
			miniCarte.moveRight();
		}
	}

	//Invoked when a key has been released.
	public void 	keyReleased(KeyEvent e){}

	//Invoked when a key has been typed.
	public void 	keyTyped(KeyEvent e){}
}