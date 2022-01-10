package at.ac.fhcampuswien.flappystudent.handlers;

import at.ac.fhcampuswien.flappystudent.main.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Main.bird.setVelY(-5);
            Main.started = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}