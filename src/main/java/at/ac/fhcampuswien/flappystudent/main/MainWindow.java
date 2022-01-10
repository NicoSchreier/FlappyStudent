package at.ac.fhcampuswien.flappystudent.main;

import javax.swing.*;

public class MainWindow extends JFrame {

    public static final int WIDTH = 1000, HEIGHT = 600;

    public MainWindow(Main game) {

        pack();
        setTitle("Flappy Student");
        setSize(WIDTH, HEIGHT);

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);

        add(game);
        game.start();
    }
}