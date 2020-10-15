import java.awt.event.*;

public class GestionClavier implements KeyListener {


	private MiniCarteVue miniCarte;

	public GestionClavier(MiniCarteVue mc){
		this.miniCarte = mc;
	}


	//Invoked when a key has been pressed.
	@Override
	public void 	keyPressed(KeyEvent e){
		//VK_UP VK_DOWN VK_LEFT VK_RIGHT
		if(e.getKeyCode()== KeyEvent.VK_UP ){
			this.miniCarte.moveUp();
		}else if(e.getKeyCode()== KeyEvent.VK_DOWN){
			this.miniCarte.moveDown();
		}else if(e.getKeyCode()== KeyEvent.VK_LEFT){
			this.miniCarte.moveLeft();
		}else if(e.getKeyCode()== KeyEvent.VK_RIGHT){
			this.miniCarte.moveRight();
		}
	}

	//Invoked when a key has been released.
	public void keyReleased(KeyEvent e){}

	//Invoked when a key has been typed.
	public void keyTyped(KeyEvent e){}
}