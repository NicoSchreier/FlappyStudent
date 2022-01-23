package at.ac.fhcampuswien.flappystudent.handlers;

import at.ac.fhcampuswien.flappystudent.supers.GameObject;

import java.awt.*;
import java.util.LinkedList;

public class ObjectHandler {

    public static LinkedList<GameObject> list = new LinkedList<>();

    // fügt Objekte der LinkedList hinzu
    public static void addObject(GameObject go) {
        list.add(go);
    }

    // entfernt Objekte der LinkedList
    public static void removeObject(GameObject go) {
        list.remove(go);
    }

    // rendert alle GameObjects (Bird, PowerUps, Tubes)
    public static void render(Graphics g) {
        GameObject gameOb;

        for (int i = 0; i < list.size(); i++) {
            gameOb = list.get(i);
            gameOb.render(g);
        }
    }

    // tickt für alle GameObjects (Bird, PowerUps, Tubes)
    public static void tick() {
        GameObject gameOb;

        for (int i = 0; i < list.size(); i++) {
            gameOb = list.get(i);
            gameOb.tick();
        }
    }
}