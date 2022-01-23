package at.ac.fhcampuswien.flappystudent.handlers;

import at.ac.fhcampuswien.flappystudent.main.Main;
import at.ac.fhcampuswien.flappystudent.supers.Button;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    // Hier wird abgerufen, was passieren soll, wenn man mit der Maus klickt (egal mit welcher Taste auf der Maus)
    @Override
    public void mousePressed(MouseEvent e) {
        if (Button.checkCollision(e.getX(), e.getY(), Main.gameover)) {
            if (Main.gameOver) {
                Main.gameover.pressed = true;
                ObjectHandler.list.clear();
                ObjectHandler.addObject(Main.bird);
                Main.gameOver = false;
                Main.score = 0;
                Main.gameover.pressed = false;
            }
        }
        Main.bird.setVelY(-5);
        Main.started = true;
    }

    // wurde nur importiert, da es eine Methode des Interface MouseListener ist
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    // wurde nur importiert, da es eine Methode des Interface MouseListener ist
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    // wurde nur importiert, da es eine Methode des Interface MouseListener ist
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    // wurde nur importiert, da es eine Methode des Interface MouseListener ist
    @Override
    public void mouseExited(MouseEvent e) {
    }
}