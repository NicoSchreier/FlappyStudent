package at.ac.fhcampuswien.flappystudent.supers;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {

    private int x;
    private int y;
    private int currentImage;

    private long delay;
    private long startTime;

    private boolean loop;
    private boolean running;

    private GameObject target;
    private BufferedImage[] images;

    // Der Konstruktor, der in der Bird Klasse aufgerufen wird, um die Animation für den Birdy zu sichern
    public Animation(GameObject target, long delay, boolean loop, BufferedImage[] images) {
        this.x = target.getX();
        this.y = target.getY();
        this.currentImage = 0;
        this.delay = delay;
        this.startTime = 0;
        this.loop = loop;
        this.setTarget(target);
        this.images = images;
    }

    // rendert unseren Birdy
    public void render(Graphics g) {
        if (target == null) {
            g.drawImage(images[currentImage], x, y, null);
        } else {
            g.drawImage(images[currentImage], target.x, target.y, null);
        }
    }

    // tickt in der tick() Methode in der Klasse Bird auch mit
    public void tick() {
        long pastTime = (System.nanoTime() - startTime) / 1000000;

        if (pastTime >= delay && running) {
            currentImage++;
            startTime = System.nanoTime();
        }

        if (currentImage == images.length) {
            currentImage = 0;

            if (!loop) {
                stop();
            }
        }
    }

    // startet die Animation
    public void start() {
        this.running = true;
        this.currentImage = 0;
        this.startTime = 0;
    }

    // stoppt die Animation
    public void stop() {
        this.running = false;
        this.currentImage = 0;
        this.startTime = 0;
    }

    // setzt das Target (das GameObject ist hier gemeint)
    public void setTarget(GameObject target) {
        this.target = target;
    }
}