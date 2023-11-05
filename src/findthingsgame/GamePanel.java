/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package findthingsgame;

import Entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import object.SuperObject;
import tile.TileManager;

/**
 *
 * @author woody
 */
public class GamePanel extends JPanel implements Runnable {

    // Screen Config
    public int org_tile_size = 16;
    public int scale = 3;
    public int tile_size = org_tile_size * scale;

    public int max_display_col = 16;
    public int max_display_row = 12;

    public int select_global_map = 0;

    public int display_width = tile_size * max_display_col;
    public int display_height = tile_size * max_display_row;

    // World Config
    public int max_world_col = 100;
    public int max_world_row = 100;
    public int world_width = tile_size * max_world_col;
    public int world_height = tile_size * max_world_row;

    // Game FPS
    int frame_per_sec = 60;

    // System
    public TileManager tileManager = new TileManager(this);
    KeyboardHandler keyboardHandler = new KeyboardHandler(this);
    Audio audio = new Audio();
    Audio soundEffect = new Audio();
    public CollisionChecker collision_checker = new CollisionChecker(this);
    public AssetSetter asetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread; 

    // Entity & Object
    public Player player = new Player(this, keyboardHandler);
    public SuperObject obj[] = new SuperObject[20];
    public SuperObject house_obj[] = new SuperObject[20];

    // Game Stage
    public int gameStage;
    public int titleStage = 0;
    public int playStage = 1;
    public int endStage = 2;
    public boolean is_in_house = false;

    public GamePanel() {
        this.setPreferredSize(new Dimension(display_width, display_height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboardHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        gameStage = titleStage;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / frame_per_sec;
        double delta = 0;
        long lastTime = System.nanoTime();
        long cirrentTime;

        // Show FPS
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            cirrentTime = System.nanoTime();
            delta += (cirrentTime - lastTime) / drawInterval;
            timer += (cirrentTime - lastTime);
            lastTime = cirrentTime;

            if (delta >= 1) {
                // update data
                update();

                // update_screen
                repaint();

                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
//                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        if (gameStage == playStage) {
            player.update();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        if (gameStage == titleStage) {
            // title Screen
            ui.draw(g2d);
        } else {
            // tile
            tileManager.draw(g2d);
            // object
            if (is_in_house == false) {
                // Loop Main Map Object 
                for (int i = 0; i < obj.length; i++) {
                    if (obj[i] != null && obj[i].user_collect == false) {
                        obj[i].draw(g2d, this);
                    }
                }
            } else {
                // Loop House Object
                for (int i = 0; i < house_obj.length; i++) {
                    if (house_obj[i] != null && house_obj[i].user_collect == false) {
                        house_obj[i].draw(g2d, this);
                    }
                }
            }
            // player
            player.draw(g2d);

            // UI
            ui.draw(g2d);
        }

        g2d.dispose();
    }

    public void playAudio(int i) {
        audio.setFile(i);
        audio.play();
        audio.loop();
    }

    public void stopAudio() {
        audio.stop();
    }

    public void playSoundEffect(int i) {
        soundEffect.setFile(i);
        soundEffect.play();
    }
}
