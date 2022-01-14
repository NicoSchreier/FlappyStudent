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


    public float gravity;
    public float maxSpeed;

    public Bird(int x, int y, int width, int height) {
        super(x, y, width, height);

        gravity = 0.3f;
        maxSpeed = 12f;

        BufferedImage[] images = new BufferedImage[3];

        for (int i = 0; i < images.length; i++) {
            images[i] = GraphicsLoader.loadGraphics("Bird" + i + ".png");
        }

        animation = new Animation(this, 100, true, images);
        animation.start();

        ObjectHandler.addObject(this);
    }

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

        for (int i = 0; i < ObjectHandler.list.size(); i++) {
            gameObject = ObjectHandler.list.get(i);

            if (gameObject instanceof PowerUps) {
                if (this.getBounds().intersects(gameObject.getBounds())) {
                    Main.powerUpActive = true;

                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                                System.out.println("5 seconds remaining");
                        }
                    }, 0);

                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                                System.out.println("4 seconds remaining");
                        }
                    }, 1000);

                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            System.out.println("3 seconds remaining");
                        }
                    }, 2000);

                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                                System.out.println("2 seconds remaining");

                        }
                    }, 3000);

                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                                System.out.println("1 seconds remaining");
                        }
                    }, 4000);

                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Main.powerUpActive = false;
                            System.out.println("Time is up, back to normal! Take care!");
                        }
                    }, 5000);
                }
            }

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

    @Override
    public void render(Graphics g) {
        animation.render(g);
    }
}