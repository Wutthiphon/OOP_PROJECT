package tile;

import findthingsgame.GamePanel;
import findthingsgame.UtilityTool;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author woody
 */
public class TileManager {

    GamePanel gamepanel;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
        tile = new Tile[50];
        mapTileNum = new int[gamepanel.max_world_col][gamepanel.max_world_row];
        getTileImage();
    }

    public void getTileImage() {
        setup(0, "grass.png", false);
        setup(1, "grass_tree.png", true);
        setup(2, "rock.png", false);
        setup(3, "sand.png", false);
        setup(4, "stone.png", true);
        setup(5, "water.png", true);
        setup(6, "wood.png", false);
        setup(7, "sand2.png", false);
        setup(8, "sand_tree.png", true);
    }

    public void setup(int index, String filename, boolean collision) {

        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/" + filename));
            tile[index].image = uTool.scaleImage(tile[index].image, gamepanel.tile_size, gamepanel.tile_size);
            tile[index].collision = collision;
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public void loadMainMap() {
        switch (gamepanel.select_global_map) {
            case 1:
                loadMap("/assets/maps/level1.map", 1);
                break;
            case 2:
                loadMap("/assets/maps/level2.map", 2);
                break;
            case 3:
                loadMap("/assets/maps/level3.map", 3);
                break;
        }
    }

    public void loadMap(String filePath, int map_id) {
        if (gamepanel.is_in_house == false) {
            switch (map_id) {
                case 1:
                    gamepanel.select_global_map = 1;
                    gamepanel.asetter.setObject(1);
                    break;
                case 2:
                    gamepanel.select_global_map = 2;
                    gamepanel.asetter.setObject(2);
                    break;
                case 3:
                    gamepanel.select_global_map = 3;
                    gamepanel.asetter.setObject(3);
                    break;
            }
        }

        try {
            InputStream input_stream = getClass().getResourceAsStream(filePath);
            BufferedReader buffered_reader = new BufferedReader(new InputStreamReader(input_stream));

            int col = 0;
            int row = 0;

            while (col < gamepanel.max_world_col && row < gamepanel.max_world_row) {
                String line = buffered_reader.readLine();

                while (col < gamepanel.max_world_col) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gamepanel.max_world_col) {
                    col = 0;
                    row++;
                }
            }
            buffered_reader.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d) {
        int world_col = 0;
        int world_row = 0;

        while (world_col < gamepanel.max_world_col && world_row < gamepanel.max_world_row) {

            int tileNum = mapTileNum[world_col][world_row];

            int worldX = world_col * gamepanel.tile_size;
            int worldY = world_row * gamepanel.tile_size;
            int screenX = worldX - gamepanel.player.worldX + gamepanel.player.screenX;
            int screenY = worldY - gamepanel.player.worldY + gamepanel.player.screenY;

            // Stop moving camera if out of map
            if (gamepanel.player.screenX > gamepanel.player.worldX) {
                screenX = worldX;
            }
            if (gamepanel.player.screenY > gamepanel.player.worldY) {
                screenY = worldY;
            }
            int right_offset = gamepanel.display_width - gamepanel.player.screenX;
            if (right_offset > gamepanel.world_width - gamepanel.player.worldX) {
                screenX = gamepanel.display_width - (gamepanel.world_width - worldX);
            }
            int bottom_offset = gamepanel.display_height - gamepanel.player.screenY;
            if (bottom_offset > gamepanel.world_height - gamepanel.player.worldY) {
                screenY = gamepanel.display_height - (gamepanel.world_height - worldY);
            }

            if (worldX + gamepanel.tile_size > gamepanel.player.worldX - gamepanel.player.screenX
                    && worldX - gamepanel.tile_size < gamepanel.player.worldX + gamepanel.player.screenX
                    && worldY + gamepanel.tile_size > gamepanel.player.worldY - gamepanel.player.screenY
                    && worldY - gamepanel.tile_size < gamepanel.player.worldY + gamepanel.player.screenY) {
                g2d.drawImage(tile[tileNum].image, screenX, screenY, null);
            } else if (gamepanel.player.screenX > gamepanel.player.worldX
                    || gamepanel.player.screenY > gamepanel.player.worldY
                    || right_offset > gamepanel.world_width - gamepanel.player.worldX
                    || bottom_offset > gamepanel.world_height - gamepanel.player.worldY) {
                g2d.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            world_col++;

            if (world_col == gamepanel.max_world_col) {
                world_col = 0;
                world_row++;
            }
        }
    }
}
