package at.ac.fhcampuswien.flappystudent.handlers;

import at.ac.fhcampuswien.flappystudent.gameobjects.PowerUps;
import at.ac.fhcampuswien.flappystudent.main.Main;

import java.util.Random;

public class PowerUpHandler {

    private static Random random = new Random();

    public static int maxSizeHeight = 400;
    public static int maxSizeWidth = 600;
    public static int delay = 20;
    public static int now;

    public static void spawnPowerUp() {
        int random_width = random.nextInt(maxSizeWidth) + 400;
        int random_height = random.nextInt(maxSizeHeight) + 50;

        if(!Main.gameOver) {
            PowerUps powerUps = new PowerUps(random_width, random_height, 40, 40);
            ObjectHandler.addObject(powerUps);
        }
    }

    public static void tick() {
        if (now < delay) {
            now++;
        } else {
            spawnPowerUp();
            now = 0;
        }
    }
}
