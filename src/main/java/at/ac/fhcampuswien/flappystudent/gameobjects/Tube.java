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
        this.velX = 4;

        tube = GraphicsLoader.loadGraphics("tube_ram.png");

        if (type == TubeType.BOTTOM) {
            tubeBlock = GraphicsLoader.loadGraphics("tubebottomdown.png");
        } else if (type == TubeType.TOP) {
            tubeBlock = GraphicsLoader.loadGraphics("tubebottomtop.png");
        }
    }

    @Override
    public void tick() {
        x -= velX;

        if (x + width < 0) {
            ObjectHandler.removeObject(this);

            if (type == TubeType.BOTTOM) {
                Main.score += 1;
            }
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