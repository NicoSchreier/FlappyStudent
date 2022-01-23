package at.ac.fhcampuswien.flappystudent.handlers;

import at.ac.fhcampuswien.flappystudent.main.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    // Hier wird abgerufen, was passieren soll, wenn man SPACEBAR drückt
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Main.bird.setVelY(-5);
            Main.started = true;
        }
    }

    // wurde nur importiert, da es eine Methode des Interface KeyListener ist
    @Override
    public void keyTyped(KeyEvent e) {
    }

    // wurde nur importiert, da es eine Methode des Interface KeyListener ist
    @Override
    public void keyReleased(KeyEvent e) {

    }
}