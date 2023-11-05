/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package findthingsgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import object.SuperObject;
import object.obj_key;

/**
 *
 * @author woody
 */
public class UI {

    GamePanel gamepanel;
    Graphics2D g2d;
    Font ancientModernTales;
    BufferedImage KeyImage;
    public int cmdNum = 0;
    public int titleScreen = 0;

    public UI(GamePanel gamepanel) {
        this.gamepanel = gamepanel;

        obj_key key = new obj_key(gamepanel);
        KeyImage = key.image;
        // Load Font
        try {
            InputStream inputS = getClass().getResourceAsStream("/assets/fonts/AncientModernTales.ttf");
            ancientModernTales = Font.createFont(Font.TRUETYPE_FONT, inputS);
        } catch (FontFormatException err) {
            err.printStackTrace();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d) {
        this.g2d = g2d;
        g2d.setFont(ancientModernTales);

        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setColor(Color.white);

        if (gamepanel.gameStage == gamepanel.titleStage) {
            drawTitleScreen();
        }
        if (gamepanel.gameStage == gamepanel.endStage) {
            drawEndScreen();
        }
        if (gamepanel.gameStage == gamepanel.playStage) {
            g2d.setFont(ancientModernTales);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 14F));
            // Header
            String title_name = "[ == Find This Item == ]";
            // Shadow
            g2d.setColor(Color.gray);
            g2d.drawString(title_name, 12, 22);
            // Header Text
            g2d.setColor(Color.white);
            g2d.drawString(title_name, 10, 20);
            // Have Key Counter
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 40F));
            g2d.drawImage(KeyImage, (gamepanel.display_width - gamepanel.tile_size) - 70, gamepanel.tile_size / 2, gamepanel.tile_size, gamepanel.tile_size, null);
            g2d.drawString("x: " + gamepanel.player.hasKey, (gamepanel.display_width - gamepanel.tile_size) - 20, 65);

            // Set Header Item Title
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 26F));
            int x = 10;
            int y = 55;
            for (SuperObject obj : gamepanel.obj) {
                if (obj != null) {
                    if (obj.name != "House" && obj.name != "Key") {
                        String item_name = "";
                        if (obj.name == "Chest") {
                            item_name = "Open-Chest";
                        } else {
                            item_name = obj.name;
                        }
                        // Shadow
                        g2d.setColor(Color.gray);
                        g2d.drawString(item_name, x + 2, y + 2);
                        int width = g2d.getFontMetrics().stringWidth(item_name);
                        if (obj.user_collect == false) {
                            g2d.setColor(Color.white);
                        } else {
                            g2d.setColor(Color.green);
                        }
                        g2d.drawString(item_name, x, y);
                        x += width + 20;
                        if (x >= gamepanel.display_width - 200) {
                            x = 10;
                            y += 35;
                        }
                    } else if (obj.name == "House") {
                        for (SuperObject house_obj : obj.in_house_obj) {
                            if (house_obj != null) {
                                if (house_obj.name != "Door" && house_obj.name != "Key") {
                                    String item_name = "";
                                    if (house_obj.name == "Chest") {
                                        item_name = "Open-Chest";
                                    } else {
                                        item_name = house_obj.name;
                                    }
                                    // Shadow
                                    g2d.setColor(Color.gray);
                                    g2d.drawString(item_name, x + 2, y + 2);
                                    int width = g2d.getFontMetrics().stringWidth(item_name);
                                    if (house_obj.user_collect == false) {
                                        g2d.setColor(Color.white);
                                    } else {
                                        g2d.setColor(Color.green);
                                    }
                                    g2d.drawString(item_name, x, y);
                                    x += width + 20;
                                    if (x >= gamepanel.display_width - 200) {
                                        x = 10;
                                        y += 35;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean checkisFindAllObject() {
        boolean status = false;

        int all_subject = 0;
        int current_find_subject = 0;
        for (SuperObject obj : gamepanel.obj) {
            if (obj != null) {
                if (obj.name != "House" && obj.name != "Key") {
                    all_subject++;
                    if (obj.user_collect == true) {
                        current_find_subject++;
                    }
                } else if (obj.name == "House") {
                    for (SuperObject house_obj : obj.in_house_obj) {
                        if (house_obj != null) {
                            if (house_obj.name != "Door" && house_obj.name != "Key") {
                                all_subject++;
                                if (house_obj.user_collect == true) {
                                    current_find_subject++;
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println(all_subject + " , " + current_find_subject);
        if (all_subject == current_find_subject) {
            System.out.println("Congratulations!!!");
            status = true;
        }

        return status;
    }

    public void drawTitleScreen() {
        if (titleScreen == 0) {
            // Background
            g2d.setColor(new Color(51, 200, 153));
            g2d.fillRect(0, 0, gamepanel.display_width, gamepanel.display_height);

            // Title name
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 66F));
            String label = "Find Things Game";
            int x = getXforCenterText(label);
            int y = gamepanel.tile_size * 3;

            // Shadow
            g2d.setColor(Color.gray);
            g2d.drawString(label, x + 5, y + 5);

            // Main Color
            g2d.setColor(Color.white);
            g2d.drawString(label, x, y);

            // Image
            x = gamepanel.display_width / 2 - (gamepanel.tile_size * 2) / 2;
            y += gamepanel.tile_size * 1.5;
            g2d.drawImage(gamepanel.player.front1, x, y, gamepanel.tile_size * 2, gamepanel.tile_size * 2, null);

            // Menu
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 34F));

            label = "[ NEW GAME ]";
            x = getXforCenterText(label);
            y += gamepanel.tile_size * 4;
            g2d.drawString(label, x, y);
            if (cmdNum == 0) {
                g2d.drawString(">", x - gamepanel.tile_size, y);
            }

            label = "[ EXIT ]";
            x = getXforCenterText(label);
            y += gamepanel.tile_size;
            g2d.drawString(label, x, y);
            if (cmdNum == 1) {
                g2d.drawString(">", x - gamepanel.tile_size, y);
            }
        } else {
            // Map Selector Screen
            // Background
            g2d.setColor(new Color(51, 200, 153));
            g2d.fillRect(0, 0, gamepanel.display_width, gamepanel.display_height);

            g2d.setColor(Color.white);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 44F));

            String label = "> SELECT MAP <";
            int x = getXforCenterText(label);
            int y = gamepanel.tile_size * 2;
            g2d.drawString(label, x, y);

            // Shadow
            g2d.setColor(Color.gray);
            g2d.drawString(label, x + 5, y + 5);

            // Main Color
            g2d.setColor(Color.white);
            g2d.drawString(label, x, y);

            g2d.setFont(g2d.getFont().deriveFont(42F));
            label = "Map 1";
            x = getXforCenterText(label);
            y += gamepanel.tile_size * 3;
            g2d.drawString(label, x, y);
            if (cmdNum == 0) {
                g2d.drawString(">", x - gamepanel.tile_size, y);
            }

            label = "Map 2";
            x = getXforCenterText(label);
            y += gamepanel.tile_size;
            g2d.drawString(label, x, y);
            if (cmdNum == 1) {
                g2d.drawString(">", x - gamepanel.tile_size, y);
            }

            label = "Map 3";
            x = getXforCenterText(label);
            y += gamepanel.tile_size;
            g2d.drawString(label, x, y);
            if (cmdNum == 2) {
                g2d.drawString(">", x - gamepanel.tile_size, y);
            }

            g2d.setFont(g2d.getFont().deriveFont(30F));
            label = "[ BACK TO MENU ]";
            x = getXforCenterText(label);
            y += gamepanel.tile_size * 3;
            g2d.drawString(label, x, y);
            if (cmdNum == 3) {
                g2d.drawString(">", x - gamepanel.tile_size, y);
            }
        }
    }

    public void drawEndScreen() {
        // Background
        g2d.setColor(new Color(51, 200, 153));
        g2d.fillRect(0, 0, gamepanel.display_width, gamepanel.display_height);

        // Title name
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 66F));
        String label = "Congratulations!";
        int x = getXforCenterText(label);
        int y = gamepanel.tile_size * 3;

        // Shadow
        g2d.setColor(Color.gray);
        g2d.drawString(label, x + 5, y + 5);

        // Main Color
        g2d.setColor(Color.white);
        g2d.drawString(label, x, y);

        label = "YOU WIN";
        x = getXforCenterText(label);
        y += gamepanel.tile_size * 2;
        g2d.drawString(label, x, y);

        g2d.setFont(g2d.getFont().deriveFont(42F));
        label = "[ Main Menu ]";
        x = getXforCenterText(label);
        y += gamepanel.tile_size * 3;
        g2d.drawString(label, x, y);
        if (cmdNum == 0) {
            g2d.drawString(">", x - gamepanel.tile_size, y);
        }

        label = "[ EXIT ]";
        x = getXforCenterText(label);
        y += gamepanel.tile_size;
        g2d.drawString(label, x, y);
        if (cmdNum == 1) {
            g2d.drawString(">", x - gamepanel.tile_size, y);
        }
    }

    public int getXforCenterText(String text) {
        int length = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        int x = gamepanel.display_width / 2 - length / 2;
        return x;
    }
}
