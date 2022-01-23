package at.ac.fhcampuswien.flappystudent.supers;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button {

    public int x;
    public int y;
    public int width;
    public int height;

    public boolean pressed;

    private BufferedImage image;

    // Der Konstruktor, der in der Main Klasse aufgerufen wird, um das GameOver als klickbaren Button darstellen zu können
    public Button(int x, int y, int width, int height, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    // hier wird abgeprüft, ob man mit der Maus auf das Bild (den Button) klickt
    public static boolean checkCollision(int mouseX, int mouseY, Button button) {
        return mouseX >= button.x && mouseX <= button.x + button.width && mouseY >= button.y && mouseY <= button.y + button.height;
    }

    // rendert den Button
    public void render(Graphics g) {
        if (pressed) {
            g.drawImage(image, x + 1, y + 1, width - 2, height - 2, null);
        } else {
            g.drawImage(image, x, y, null);
        }
    }
}