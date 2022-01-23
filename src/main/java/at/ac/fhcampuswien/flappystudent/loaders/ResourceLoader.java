package at.ac.fhcampuswien.flappystudent.loaders;

import java.io.InputStream;

public class ResourceLoader {

    // mit der Methode ist der Prozess, um zum Resource Ordner zu gelangen, vereinfacht
    public static InputStream load(String path) {
        InputStream input = ResourceLoader.class.getResourceAsStream(path);
        if (input == null) {
            input = ResourceLoader.class.getResourceAsStream("/" + path);
        }
        return input;
    }
}