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

    // Der Konstruktor, der in der TubeHandler Klasse in spawnTube() aufgerufen wird
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

    // Tickt permanent mit, um den Spielstatus zu überprüfen und dementsprechend zu handeln
    @Override
    public void tick() {
        x -= velX;

        // hier wird abgefragt, ob der Bird durch den Tube durch ist, oder nicht
        if (this.x == Main.bird.getX() && type == TubeType.TOP) {
            Main.score++;
        }

        // hier werden die GameObjects (Tubes) wieder entfernt, wenn sie einen gewissen Punkt passieren
        // TODO: 23.01.2022: Hier meinte Strommer glaube ich, dass der Fehler mit dem verschieben der Tubes liegt?
        if (x + width < 0) {
            ObjectHandler.removeObject(this);
        }

        if (x + width < 0) {
            ObjectHandler.removeObject(this);
        }
    }

    // rendert die Tubes
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