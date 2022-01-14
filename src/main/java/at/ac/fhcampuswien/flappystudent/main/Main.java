package at.ac.fhcampuswien.flappystudent.main;

import at.ac.fhcampuswien.flappystudent.gameobjects.Bird;
import at.ac.fhcampuswien.flappystudent.gameobjects.Ground;
import at.ac.fhcampuswien.flappystudent.handlers.*;
import at.ac.fhcampuswien.flappystudent.loaders.GraphicsLoader;
import at.ac.fhcampuswien.flappystudent.supers.Button;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Main extends Canvas implements Runnable {

    public boolean running;
    public static boolean started;
    public static boolean gameOver;
    public static boolean powerUpActive;

    public static BufferedImage background;
    public static BufferedImage start_screen;

    public static Button gameover;
    public static Ground ground;
    public static Bird bird;

    public static int score;
    public int highscore;

    Thread thread;

    public static void main(String[] args) {
        new MainWindow(new Main());
    }

    public synchronized void start() {
        running = true;
        started = false;
        thread = new Thread(this);
        thread.start();
        run();
    }

    public void init() {
        addKeyListener(new KeyHandler());
        addMouseListener(new MouseHandler());

        background = GraphicsLoader.loadGraphics("Background.png");

        ground = new Ground();

        bird = new Bird(250, 250, 51, 36);

        start_screen = GraphicsLoader.loadGraphics("Start.png");

        gameover = new Button(MainWindow.WIDTH / 2 - 288 / 2, 130, 300, 230, GraphicsLoader.loadGraphics("Gameover.png"));
    }

    public void tick() {
        if (!gameOver) {
            ObjectHandler.tick();
            ground.tick();
        }
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(background, 0, 0, null);

        ground.render(g);

        ObjectHandler.render(g);

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(new Color(250, 247, 247, 178));

        g.drawRect(10,19,105,28);
        g.fillRect(10, 19, 105,28);

        g.drawRect(815, 19, 150, 30);
        g.fillRect(815, 19, 150,30);

        g.setColor(new Color(61, 61, 61,250));

        String current_score = "Score: " + score;
        int scoreWidth = g.getFontMetrics().stringWidth(current_score);

        g.drawString(current_score, MainWindow.WIDTH - 900 - scoreWidth, 40);

        if (gameOver) {
            Main.gameover.render(g);
            if (highscore < score) {
                highscore = score;
            }
        }

        String display_highscore = "Highscore: " + highscore;
        int highscoreWidth = g.getFontMetrics().stringWidth(display_highscore);

        g.drawString(display_highscore, MainWindow.WIDTH - 50 - highscoreWidth, 40);

        if (!started) {
            g.drawImage(start_screen, MainWindow.WIDTH / 2 - 250, 50, null);
        }

        g.dispose();
        bs.show();
    }

    @Override
    public void run() {
        init();
        this.requestFocus();

        while (!started) {
            render();
        }

        long pastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();

        while (running) {
            long now = System.nanoTime();
            delta += (now - pastTime) / ns;
            pastTime = now;

            while (delta > 0) {
                tick();
                render();
                delta--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                TubeHandler.tick();
                PowerUpHandler.tick();
            }
        }
    }
}