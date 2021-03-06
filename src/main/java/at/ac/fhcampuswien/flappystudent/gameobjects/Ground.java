package at.ac.fhcampuswien.flappystudent.gameobjects;

import at.ac.fhcampuswien.flappystudent.loaders.GraphicsLoader;
import at.ac.fhcampuswien.flappystudent.main.MainWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ground {

    private BufferedImage image;
    private int x1, x2;

    private float velX;

    // Der Konstruktor, der in der Main Klasse in init() aufgerufen wird
    public Ground() {
        x1 = 0;
        x2 = MainWindow.WIDTH;

        velX = 5;

        image = GraphicsLoader.loadGraphics("Ground.png");
    }

    // Tickt permanent mit, um den Spielstatus zu überprüfen und dementsprechend zu handeln
    public void tick() {

        x1 -= velX;
        x2 -= velX;

        if (x1 + MainWindow.WIDTH <= 0) {
            x1 = MainWindow.WIDTH;
        }

        if (x2 + MainWindow.WIDTH <= 0) {
            x2 = MainWindow.WIDTH;
        }
    }

    // rendert den Boden
    public void render(Graphics g) {
        g.drawImage(image, x1, MainWindow.HEIGHT - 168, null);
        g.drawImage(image, x2, MainWindow.HEIGHT - 168, null);
    }
}