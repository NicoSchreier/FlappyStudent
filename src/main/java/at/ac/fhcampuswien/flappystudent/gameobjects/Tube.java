package at.ac.fhcampuswien.flappystudent.gameobjects;

import at.ac.fhcampuswien.flappystudent.enums.TubeType;
import at.ac.fhcampuswien.flappystudent.handlers.ObjectHandler;
import at.ac.fhcampuswien.flappystudent.loaders.GraphicsLoader;
import at.ac.fhcampuswien.flappystudent.main.Main;
import at.ac.fhcampuswien.flappystudent.supers.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tube extends GameObject {

    TubeType type;

    BufferedImage tubeBlock;
    BufferedImage tube;

    public Tube(int x, int y, int width, int height, TubeType type) {
        super(x, y, width, height);

        this.type = type;
        this.velX = 5;
        tube = GraphicsLoader.loadGraphics("Tube_ram.png");

        if (type == TubeType.BOTTOM) {
            tubeBlock = GraphicsLoader.loadGraphics("Tube_topping.png");
        } else if (type == TubeType.TOP) {
            tubeBlock = GraphicsLoader.loadGraphics("Tube_topping.png");
        }
    }

    @Override
    public void tick() {
        x -= velX;

        if (this.x == Main.bird.getX() && type == TubeType.TOP) {
            Main.score++;
        }

        if (x + width < 0) {
            ObjectHandler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        if (type == TubeType.BOTTOM) {
            g.drawImage(tube, x, y, 72, height, null);
            g.drawImage(tubeBlock, x - 3, y, null);
        } else if (type == TubeType.TOP) {
            g.drawImage(tube, x, y, 72, height, null);
            g.drawImage(tubeBlock, x - 3, y + height - 36, null);
        }
    }
}