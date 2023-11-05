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
public class obj_house2 extends SuperObject {

    GamePanel gamepanel;

    public obj_house2(GamePanel gamepanel) {
        this.gamepanel = gamepanel;

        name = "House";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/assets/objects/house2.png"));
            image = uTool.scaleImage(image, gamepanel.tile_size, gamepanel.tile_size);
        } catch (IOException err) {
            err.printStackTrace();
        }
        collision = true;
        in_house_obj = new SuperObject[20];
    }
}
