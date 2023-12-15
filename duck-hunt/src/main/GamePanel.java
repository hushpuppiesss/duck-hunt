// ==========================================
//               GamePanel Class
//  Author: Rachel Quedding
//  Purpose: "The game screen" so to speak. It
//  contains the screen settings, game clock/loop,
//  the thread for the game, and the function for
//  updating the info and repainting it.
// Jennie - need to add [PowerUps pUps = new PowerUps(this);] somewhere, not sure yet
// ==========================================

package main;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;
// java awt, api for developing gui

public class GamePanel extends JPanel implements Runnable{
    // ----------------------- TILE SETTINGS -----------------------
    // 32x32 tiles
    final int originalTileSize = 32;
    // scale factor
    public final int scale = 2;

    // the size that you'll see on screen - 64x tiles
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16; // n tiles wide
    public final int maxScreenRow = 9; // n tiles tall

    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // ----------------------- WORLD MAP SETTINGS -----------------------
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // ----------------------- FPS -----------------------
    int FPS = 32;

    // ----------------------- CONSTRUCTORS -----------------------a

    // tile manager constructor
    TileManager tileM = new TileManager(this);
    // instantiating the key handler
    KeyHandler keyH = new KeyHandler();
    // sound class
    Sound music = new Sound();
    Sound sfx = new Sound();

    // collision checker
    public CollisionChecker cChecker = new CollisionChecker(this);

    // declaring the position handler
    public PositionHandler PosH = new PositionHandler(this);

    public UI ui = new UI(this);

    // creating the game clock
    Thread gameThread;

    // Allows for usage of key handler and game panel fom player.jav
    public Player player = new Player(this,keyH);

    //declaring the super object class, there can be a maximum of 10 objects appearings at once
    // Jennie
    public SuperObject[] obj = new SuperObject[10];



    // ----------------------- GAME STATE -----------------------
    public  int gameState;
    public final int playState = 1;
    public final int pauseState = 2;

    // ----------------------- SCREEN SETUP, CONSTRUCTOR -----------------------
    public GamePanel()
    {
        // initializing
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK );
        this.setDoubleBuffered(true); // improves rendering performance as drawing will be done in an offscreen painting buffer
        this.addKeyListener(keyH); // so that game panel can recognize key input
        this.setFocusable(true); // "focused" to receive input
    }
    //set object method
    public void setupGame(){

        PosH.setObject();

        // plays the background music
        playMusic(0);
    }


    // ----------------------- GAME THREAD METHOD -----------------------
    public void startGameThread()
    {
        gameThread = new Thread (this); // passing game panel to thread constructor, initiating the thread
        gameThread.start(); // start() will call the run method in Runnable, but
                            // we overrode run() below
    }

    // ----------------------- RUN METHOD -----------------------
    // when we start a game thread, this run method is called
    @Override // <--- just to say that we override the run() method from Runnable. we're using our own implementation.
    public void run()
    {
        // sleep method
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null)
        {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1)
            {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000)
            {
                drawCount = 0;
                timer = 0;
            }
        }
    }

    // ----------------------- UPDATE -----------------------
    public void update()
    { 
        player.update();
    }

    // ----------------------- PAINT -----------------------
    public void paintComponent(Graphics g)
    {
    /* paintComponent is a standard method to draw things on jpanel, Graphics is a class that
    has many functions to draw objects on the screen */

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        // Tile
        tileM.draw(g2);
        // player
        player.draw(g2);
        // buff
        for(int i=0;i<obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2,this);
            }
        }
        // draws the ui
        ui.draw(g2);

        g2.dispose(); // disposes of graphic context & releases systems resources to save memory
    }

    // ----------------------- SOUND METHODS -----------------------
    public void playMusic (int i) {
        // calls setfile from sound class
        music.setFile(i);
        // calls play method and to loop it for background music
        music.play();
        music.loop();
    }

    public void stopMusic() {
        // calls stop method from sound class
        music.stop();
    }

    public void playerSFX(int i) {
        // plays player sound effects
        sfx.setFile(i);
        sfx.play();
    }

    // entity and object
    public ArrayList<Entity> projectileList = new ArrayList <> ();
    public ArrayList<Entity> entityList = new ArrayList <> ();

    // ADD ENTITIES TO THE LIST
//    for (int i = 0; i < projectileList.size(); i++){
//        if(projectileList.get(i) != null) {
//            entityList.add(projectileList.get(i));
//        }
//    }
   
}
