package fr.iutfbleau.projetIHM2020FI2.CONTROLEUR;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
import java.awt.event.*;

public class Raccourcie implements KeyListener{

    //Invoked when a key has been pressed.
    @Override
    public void keyPressed(KeyEvent e){

        if(e.getKeyCode() == KeyEvent.VK_D){
            PieceVue.getPieceVue().onOffDaltonien();
        }
    }

    //Invoked when a key has been released.
    @Override
    public void keyReleased(KeyEvent e){}

    //Invoked when a key has been typed.
    @Override
    public void keyTyped(KeyEvent e){}

    
}   