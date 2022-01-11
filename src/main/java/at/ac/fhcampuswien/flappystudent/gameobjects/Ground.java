package at.ac.fhcampuswien.flappystudent.gameobjects;

import at.ac.fhcampuswien.flappystudent.loaders.GraphicsLoader;
import at.ac.fhcampuswien.flappystudent.main.MainWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ground {

    private BufferedImage image;
    private int x1, x2;

    private float velX;

    public Ground() {
        x1 = 0;
        x2 = MainWindow.WIDTH;

        velX = 3;

        image = GraphicsLoader.loadGraphics("Ground168_kabel2.png");
    }

    public void tick() {

        x1 -= velX;
        x2 -= velX;

        if (x1 + MainWindow.WIDTH < 0) {
            x1 = MainWindow.WIDTH;
        }

        if (x2 + MainWindow.WIDTH < 0) {
            x2 = MainWindow.WIDTH;
        }
    }

    public void render(Graphics g) {
        g.drawImage(image, x1, MainWindow.HEIGHT - 168, null);
        g.drawImage(image, x2, MainWindow.HEIGHT - 168, null);
    }
}