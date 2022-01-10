package at.ac.fhcampuswien.flappystudent.loaders;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GraphicsLoader {

    public static BufferedImage loadGraphics(String path) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(ResourceLoader.load("/" + path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
