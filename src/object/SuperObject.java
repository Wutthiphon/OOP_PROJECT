/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import findthingsgame.GamePanel;
import findthingsgame.UtilityTool;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author woody
 */
public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public boolean user_collect = false;
    public SuperObject in_house_obj[] = new SuperObject[20];
    public int in_house_obj_x = 0;
    public int in_house_obj_y = 0;

    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    UtilityTool uTool = new UtilityTool();

    public void draw(Graphics2D g2d, GamePanel gamepanel) {
        int screenX = worldX - gamepanel.player.worldX + gamepanel.player.screenX;
        int screenY = worldY - gamepanel.player.worldY + gamepanel.player.screenY;

        // STOP MOVING CAMERA
        if (gamepanel.player.worldX < gamepanel.player.screenX) {
            screenX = worldX;
        }
        if (gamepanel.player.worldY < gamepanel.player.screenY) {
            screenY = worldY;
        }
        int rightOffset = gamepanel.display_width - gamepanel.player.screenX;
        if (rightOffset > gamepanel.world_width - gamepanel.player.worldX) {
            screenX = gamepanel.display_width - (gamepanel.world_width - worldX);
        }
        int bottomOffset = gamepanel.display_height - gamepanel.player.screenY;
        if (bottomOffset > gamepanel.world_height - gamepanel.player.worldY) {
            screenY = gamepanel.display_height - (gamepanel.world_height - worldY);
        }

        if (worldX + gamepanel.tile_size > gamepanel.player.worldX - gamepanel.player.screenX
                && worldX - gamepanel.tile_size < gamepanel.player.worldX + gamepanel.player.screenX
                && worldY + gamepanel.tile_size > gamepanel.player.worldY - gamepanel.player.screenY
                && worldY - gamepanel.tile_size < gamepanel.player.worldY + gamepanel.player.screenY) {
            g2d.drawImage(image, screenX, screenY, null);
        } else if (gamepanel.player.worldX < gamepanel.player.screenX
                || gamepanel.player.worldY < gamepanel.player.screenY
                || rightOffset > gamepanel.world_width - gamepanel.player.worldX
                || bottomOffset > gamepanel.world_height - gamepanel.player.worldY) {
            g2d.drawImage(image, screenX, screenY, null);
        }
    }
}
