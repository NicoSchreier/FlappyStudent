package at.ac.fhcampuswien.flappystudent.handlers;

import at.ac.fhcampuswien.flappystudent.enums.TubeType;
import at.ac.fhcampuswien.flappystudent.gameobjects.Tube;
import at.ac.fhcampuswien.flappystudent.main.MainWindow;

import java.util.Random;

public class TubeHandler {

    private static Random random = new Random();

    public static int groundSize = 168;
    public static int area = MainWindow.HEIGHT - groundSize;
    public static int spacing = 120;
    public static int minSize = 40;
    public static int maxSize = area - spacing - minSize;
    public static int delay = 1;
    public static int now;

    public static void spawnTube() {
        int heightTop = random.nextInt(maxSize) + 1;

        while (heightTop < minSize) {
            heightTop = random.nextInt(maxSize) + 1;
        }

        int heightBottom = area - spacing - heightTop;

        Tube tubeTop = new Tube(1000, 0, 78, heightTop, TubeType.TOP);
        Tube tubeBottom = new Tube(1000, heightTop + spacing, 78, heightBottom, TubeType.BOTTOM);

        ObjectHandler.addObject(tubeTop);
        ObjectHandler.addObject(tubeBottom);
    }

    public static void tick() {
        if (now < delay) {
            now++;
        } else {
            spawnTube();
            now = 0;
        }
    }
}