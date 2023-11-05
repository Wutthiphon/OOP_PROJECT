/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package findthingsgame;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author woody
 */
public class UtilityTool {

    public BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaleImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2d = scaleImage.createGraphics();
        g2d.drawImage(original, 0, 0, width, height, null);
        g2d.dispose();

        return scaleImage;
    }
}
