package at.ac.fhcampuswien.flappystudent.handlers;

import at.ac.fhcampuswien.flappystudent.gameobjects.PowerUps;
import at.ac.fhcampuswien.flappystudent.main.Main;

import java.util.Random;

public class PowerUpHandler {

    private static Random random = new Random();

    public static int maxSizeHeight = 400;
    public static int maxSizeWidth = 600;
    public static int delay = 10; // niedrigerer Wert → mehr PowerUps spawnen
    public static int now;

    // Ist für das spawnen von PowerUps zuständig (vor allem wo sie spawnen sollen)
    public static void spawnPowerUp() {
        int random_width = random.nextInt(maxSizeWidth) + 400;
        int random_height = random.nextInt(maxSizeHeight) - 180;

        if(!Main.gameOver) {
            PowerUps powerUps = new PowerUps(random_width, random_height, 40, 40);
            ObjectHandler.addObject(powerUps);
        }
    }

    // Tickt permanent mit, um PowerUps zum richtigen Zeitpunkt zu spawnen
    public static void tick() {
        if (now < delay) {
            now++;
        } else {
            spawnPowerUp();
            now = 0;
        }
    }
}
