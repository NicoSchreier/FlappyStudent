package at.ac.fhcampuswien.flappystudent.gameobjects;

import at.ac.fhcampuswien.flappystudent.handlers.ObjectHandler;
import at.ac.fhcampuswien.flappystudent.loaders.GraphicsLoader;
import at.ac.fhcampuswien.flappystudent.main.Main;
import at.ac.fhcampuswien.flappystudent.main.MainWindow;
import at.ac.fhcampuswien.flappystudent.supers.Animation;
import at.ac.fhcampuswien.flappystudent.supers.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.TimerTask;
import java.util.Timer;

public class Bird extends GameObject {

    Animation animation;

    private final float gravity;
    private final float maxSpeed;

    // Der Konstruktor, der in der Main Klasse in init() aufgerufen wird
    public Bird(int x, int y, int width, int height) {
        super(x, y, width, height);

        gravity = 0.3f;
        maxSpeed = 12f;

        BufferedImage[] images = new BufferedImage[3];

        // Wir haben 3 verschieden Bilder von unserem Birdy (wenn man es schnell abspielt, sieht es so aus als würde er die Flügel verwenden um zu fliegen)
        // Hier wird sichergestellt, dass die Bilder richtig geladen werden.
        for (int i = 0; i < images.length; i++) {
            images[i] = GraphicsLoader.loadGraphics("Bird" + i + ".png");
        }

        animation = new Animation(this, 100, true, images);
        animation.start();

        ObjectHandler.addObject(this);
    }

    // Tickt permanent mit, um den Spielstatus zu überprüfen und dementsprechend zu handeln
    @Override
    public void tick() {

        velY += gravity;
        y += velY;

        if (velY > maxSpeed) {
            velY = maxSpeed;
        }

        // untere Begrenzung
        if (y + height > MainWindow.HEIGHT - 166) {
            y = MainWindow.HEIGHT - 166 - height;
            setVelY(0);
        }

        // obere Begrenzung
        if (y < 0) {
            y = 0;
            setVelY(0);
        }

        GameObject gameObject;

        // läuft so lange, wie in der LinkedList Objekte sind (also quasi bis die Liste in MouseHandler gecleared wird)
        for (int i = 0; i < ObjectHandler.list.size(); i++) {
            gameObject = ObjectHandler.list.get(i);

            // hier wird überprüft, ob das GameObject Bird mit einem PowerUp kollidiert und was dann passieren soll
            if (gameObject instanceof PowerUps) {
                if (this.getBounds().intersects(gameObject.getBounds())) {
                    Main.powerUpActive = true;

                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            System.out.println("Time is up, back to normal! Take care!");
                            Main.powerUpActive = false;
                        }
                    }, 5000);
                }
            }

            // hier wird überprüft, ob das GameObject Bird mit einer Tube kollidiert und was dann passieren soll
            if (!Main.powerUpActive) {
                if (gameObject instanceof Tube) {
                    if (this.getBounds().intersects(gameObject.getBounds())) {
                       Main.gameOver = true;
                    }
                }
            }
        }
        animation.tick();
    }

    // rendert die Animation des Vogels
    @Override
    public void render(Graphics g) {
        animation.render(g);
    }
}