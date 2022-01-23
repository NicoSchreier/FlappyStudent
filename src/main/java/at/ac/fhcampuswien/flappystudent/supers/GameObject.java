package at.ac.fhcampuswien.flappystudent.supers;

import java.awt.*;

public abstract class GameObject {

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    protected float velX;
    protected float velY;

    // Der wohl wichtigste Konstruktor in dem Programm. Er wird in der Klasse Bird, PowerUps und Tube verwendet, um die jeweiligen GameObjects zu erstellen
    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // diese Methode braucht jedes GameObject sowieso, deswegen gibt es hier eine, um sie bei "implements GameObject" nicht zu vergessen!
    public abstract void tick();

    // diese Methode braucht jedes GameObject sowieso, deswegen gibt es hier eine, um sie bei "implements GameObject" nicht zu vergessen!
    public abstract void render(Graphics g);

    // returned die Position des GameObjects. Wir verwenden es, um zu überprüfen, ob unser Birdy mit einem PowerUp kollidiert oder nicht
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    // returned nur x von einem GameObject
    public int getX() {
        return x;
    }

    // returned nur y von einem GameObject
    public int getY() {
        return y;
    }

    // returned velY (velocity vom y Wert)
    public void setVelY(float velY) {
        this.velY = velY;
    }
}