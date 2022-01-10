package at.ac.fhcampuswien.flappystudent.main;

import at.ac.fhcampuswien.flappystudent.gameobjects.Bird;
import at.ac.fhcampuswien.flappystudent.gameobjects.Ground;
import at.ac.fhcampuswien.flappystudent.handlers.KeyHandler;
import at.ac.fhcampuswien.flappystudent.handlers.MouseHandler;
import at.ac.fhcampuswien.flappystudent.handlers.TubeHandler;
import at.ac.fhcampuswien.flappystudent.loaders.GraphicsLoader;
import at.ac.fhcampuswien.flappystudent.handlers.ObjectHandler;
import at.ac.fhcampuswien.flappystudent.supers.Button;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Main extends Canvas implements Runnable {

    public boolean running;
    public static boolean started;
    public static boolean gameOver;

    public static BufferedImage background;
    public static BufferedImage img_gameOver;
    public static Ground ground;
    public static Bird bird;

    public static Button startButton;

    public static int score;
    public int highscore;

    Thread thread;

    // TODO: (GENERELL & BUGS) 06.01.2022:
    //
    // -> Eigene Grafiken einfügen! (Maße bitte mit mir (Nico Schreier) vorher besprechen!)
    // -> Einen Start Screen mit kurzer Erklärung einführen! (Statt SPACEBAR vielleicht ein Bild von einer SPACEBAR?)
    // -> Die Texte ("Score: X" oder "Click to Restart") grafisch verschönern!
    //              --> auf Hintergrund von Kalo warten
    //
    // Bekannte Bugs:
    // - Grafikfehler beim Bild groundBIG beim Laden (alle 2000px / nach 2x Laden) - Kalo fixt das
    // - Press SPACEBAR to start
    //
    // -> WICHTIG: Ich werde in den kommenden Tagen versuchen alle Teile des Codes zu verstehen und mit Kommentaren zu versehen.
    //             Wir können gerne eine extra Besprechung ansetzen, wo wir alles in Ruhe nochmal durchgehen um Fragen zu klären!
    //
    public static void main(String[] args) {
        new MainWindow(new Main());
    }

    // TODO: 05.01.2022: (LOGIK) synchronized recherchieren
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

        img_gameOver = GraphicsLoader.loadGraphics("gameover.png");
        background = GraphicsLoader.loadGraphics("backgroundBIG.png");

        ground = new Ground();

        bird = new Bird(50, 50, 51, 36);

        startButton = new Button(MainWindow.WIDTH / 2 - 156 / 2, 220, 156, 87, GraphicsLoader.loadGraphics("playbutton.png"));
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

        if (gameOver) {
            g.drawImage(img_gameOver, MainWindow.WIDTH / 2 - 288 / 2, 130, null);
            Main.startButton.render(g);
        }

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.BLACK);

        String current_score = "Score: " + score;
        int scoreWidth = g.getFontMetrics().stringWidth(current_score);

        g.drawString(current_score, MainWindow.WIDTH - 900 - scoreWidth, 40);

        if(gameOver) {
            String explanation = "Click to Restart!";
            int explanationWidth = g.getFontMetrics().stringWidth(explanation);

            g.drawString(explanation, MainWindow.WIDTH / 2 - explanationWidth / 2, 340);

            if (highscore < score){
                highscore = score;
            }
        }

        String display_highscore = "Highscore: " + highscore;
        int highscoreWidth = g.getFontMetrics().stringWidth(display_highscore);

        g.drawString(display_highscore, MainWindow.WIDTH - 50 - highscoreWidth, 40);

        // TODO: 07.01.2022: (BUG) Text wird zwar angezeigt und verschwindet auch wenn man SPACEBAR drückt, aber das Game beginnt trotzdem schon davor
        if(!started){
            String start_screen = "Press SPACEBAR to start!";
            int start_screenWidth = g.getFontMetrics().stringWidth(start_screen);

            g.drawString(start_screen, MainWindow.WIDTH / 2 - start_screenWidth / 2, 280);
        }

        g.dispose();
        bs.show();
    }

    // TODO: 05.01.2022: (LOGIK) Mathe Teil verstehen
    @Override
    public void run() {
        init();
        this.requestFocus();

        if(!started){
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
            }
        }
    }
}