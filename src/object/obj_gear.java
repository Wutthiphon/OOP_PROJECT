/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import findthingsgame.GamePanel;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author woody
 */
public class obj_gear extends SuperObject {

    GamePanel gamepanel;

    public obj_gear(GamePanel gamepanel) {
        this.gamepanel = gamepanel;

        name = "Gear";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/assets/objects/gear.png"));
            image = uTool.scaleImage(image, gamepanel.tile_size, gamepanel.tile_size);
        } catch (IOException err) {
            err.printStackTrace();
        }
        collision = true;
    }
}
