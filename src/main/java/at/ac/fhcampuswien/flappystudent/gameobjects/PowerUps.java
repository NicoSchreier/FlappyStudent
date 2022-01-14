package at.ac.fhcampuswien.flappystudent.gameobjects;

import at.ac.fhcampuswien.flappystudent.handlers.ObjectHandler;
import at.ac.fhcampuswien.flappystudent.loaders.GraphicsLoader;
import at.ac.fhcampuswien.flappystudent.main.Main;
import at.ac.fhcampuswien.flappystudent.supers.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PowerUps extends GameObject {

    BufferedImage clock;

    public PowerUps(int x, int y, int width, int height) {
        super(x, y, width, height);

        this.velX = 5;

        clock = GraphicsLoader.loadGraphics("Shield.png");

    }

    @Override
    public void tick() {
        x -= velX;

        if (Main.powerUpActive){
            ObjectHandler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(clock, x, y, width, height, null);
    }
}