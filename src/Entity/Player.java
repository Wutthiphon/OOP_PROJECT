/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import findthingsgame.GamePanel;
import findthingsgame.KeyboardHandler;
import findthingsgame.UtilityTool;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author woody
 */
public class Player extends Entity {
    
    GamePanel gamepanel;
    KeyboardHandler keyboardHandler;
    
    public int screenX;
    public int screenY;
    
    public int inMainMap_screenX;
    public int inMainMap_screenY;
    
    public int active_house_id;

    // obj counter
    public int hasKey = 0;
    
    public Player(GamePanel gamepanel, KeyboardHandler keyboardHandler) {
        this.gamepanel = gamepanel;
        this.keyboardHandler = keyboardHandler;
        
        screenX = gamepanel.display_width / 2 - (gamepanel.tile_size / 2);
        screenY = gamepanel.display_height / 2 - (gamepanel.tile_size / 2);
        
        inMainMap_screenX = 0;
        inMainMap_screenY = 0;
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        
        setDefaultValue();
        getPlayerImage();
    }
    
    public void setDefaultValue() {
        worldX = gamepanel.tile_size * (gamepanel.max_world_col / 2 - 1);
        worldY = gamepanel.tile_size * (gamepanel.max_world_row / 2 - 1);
        speed = 4;
        direction = "back";
    }
    
    public void getPlayerImage() {
        back1 = setup("back1.png");
        back2 = setup("back2.png");
        front1 = setup("front1.png");
        front2 = setup("front2.png");
        left1 = setup("left1.png");
        left2 = setup("left2.png");
        right1 = setup("right1.png");
        right2 = setup("right2.png");
    }
    
    public BufferedImage setup(String image_name) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/assets/player/" + image_name));
            image = uTool.scaleImage(image, gamepanel.tile_size, gamepanel.tile_size);
        } catch (IOException err) {
            err.printStackTrace();
        }
        return image;
    }
    
    public void update() {
        if (keyboardHandler.upP == true || keyboardHandler.downP == true || keyboardHandler.leftP == true || keyboardHandler.rightP == true) {
            if (keyboardHandler.upP == true && keyboardHandler.leftP == true) {
                direction = "up_left";
            } else if (keyboardHandler.upP == true && keyboardHandler.rightP == true) {
                direction = "up_right";
            } else if (keyboardHandler.downP == true && keyboardHandler.leftP == true) {
                direction = "down_left";
            } else if (keyboardHandler.downP == true && keyboardHandler.rightP == true) {
                direction = "down_right";
            } else if (keyboardHandler.upP == true) {
                direction = "up";
            } else if (keyboardHandler.downP == true) {
                direction = "down";
            } else if (keyboardHandler.leftP == true) {
                direction = "left";
            } else if (keyboardHandler.rightP == true) {
                direction = "right";
            }

            // Check Tile Collision
            collisionOn = false;
            gamepanel.collision_checker.checkTile(this);

            // Check Object Collision
            int objIndex = gamepanel.collision_checker.checkOBJ(this, true);
            pickupOBJ(objIndex);

            // Collision Logic
            if (collisionOn == false) {
                switch (direction) {
                    case "up_left":
                        worldY -= speed;
                        worldX -= speed / 2;
                        break;
                    case "up_right":
                        worldY -= speed;
                        worldX += speed / 2;
                        break;
                    case "down_left":
                        worldY += speed;
                        worldX -= speed / 2;
                        break;
                    case "down_right":
                        worldY += speed;
                        worldX += speed / 2;
                        break;
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    
    public void pickupOBJ(int index) {
        if (index != 999) {
            String objectName = "";
            if (gamepanel.is_in_house == false) {
                objectName = gamepanel.obj[index].name;
            } else {
                objectName = gamepanel.house_obj[index].name;
            }
            
            switch (objectName) {
                case "Key":
                    gamepanel.playSoundEffect(1);
                    hasKey++;
                    // remove obj on the map
                    if (gamepanel.is_in_house == false) {
                        gamepanel.obj[index].user_collect = true;
                    } else {
                        gamepanel.house_obj[index].user_collect = true;
                    }
                    checkAllFoundObject();
                    System.out.println("have key: " + hasKey);
                    break;
                case "Chest":
                    if (hasKey > 0) {
                        gamepanel.playSoundEffect(2);
                        if (gamepanel.is_in_house == false) {
                            gamepanel.obj[index].user_collect = true;
                        } else {
                            gamepanel.house_obj[index].user_collect = true;
                        }
                        hasKey--;
                    }
                    checkAllFoundObject();
                    System.out.println("have key: " + hasKey);
                    break;
                case "House":
                    gamepanel.is_in_house = true;
                    gamepanel.tileManager.loadMap("/assets/maps/house.map", 4);
                    // Save Location in Main Map
                    inMainMap_screenX = worldX;
                    inMainMap_screenY = worldY;
                    // Set House ID
                    active_house_id = index;
                    gamepanel.asetter.loadHouseObject(active_house_id);
                    // Set Camera Position
                    resetPosition();
                    System.out.println("Enter The House (id: " + active_house_id + ")");
                    break;
                case "Door":
                    setPosition(inMainMap_screenX, inMainMap_screenY);
                    active_house_id = 0;
                    gamepanel.tileManager.loadMainMap();
                    System.out.println("Exit The House");
                    gamepanel.is_in_house = false;
                    break;
                default:
                    gamepanel.playSoundEffect(2);
                    if (gamepanel.is_in_house == false) {
                        gamepanel.obj[index].user_collect = true;
                    } else {
                        gamepanel.house_obj[index].user_collect = true;
                    }
                    checkAllFoundObject();
                    break;
            }
        }
    }
    
    public void checkAllFoundObject() {
        if (gamepanel.ui.checkisFindAllObject() == true) {
            gamepanel.gameStage = gamepanel.endStage;
            gamepanel.stopAudio();
            gamepanel.ui.cmdNum = 0;
        }
    }
    
    public void setPosition(int x, int y) {
        worldX = x;
        worldY = y;
    }
    
    public void resetPosition() {
        worldX = gamepanel.tile_size * (gamepanel.max_world_col / 2 - 1);
        worldY = gamepanel.tile_size * (gamepanel.max_world_row / 2 - 1);
    }
    
    public void draw(Graphics2D g2d) {
        BufferedImage image = back1;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = back1;
                } else if (spriteNum == 2) {
                    image = back2;
                }
                break;
            case "up_left":
                if (spriteNum == 1) {
                    image = back1;
                } else if (spriteNum == 2) {
                    image = back2;
                }
                break;
            case "up_right":
                if (spriteNum == 1) {
                    image = back1;
                } else if (spriteNum == 2) {
                    image = back2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = front1;
                } else if (spriteNum == 2) {
                    image = front2;
                }
                break;
            case "down_left":
                if (spriteNum == 1) {
                    image = front1;
                } else if (spriteNum == 2) {
                    image = front2;
                }
                break;
            case "down_right":
                if (spriteNum == 1) {
                    image = front1;
                } else if (spriteNum == 2) {
                    image = front2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        
        int x = screenX;
        int y = screenY;
        
        if (screenX > worldX) {
            x = worldX;
        }
        if (screenY > worldY) {
            y = worldY;
        }
        int right_offset = gamepanel.display_width - screenX;
        if (right_offset > gamepanel.world_width - worldX) {
            x = gamepanel.display_width - (gamepanel.world_width - worldX);
        }
        int bottom_offset = gamepanel.display_height - screenY;
        if (bottom_offset > gamepanel.world_height - worldY) {
            y = gamepanel.display_height - (gamepanel.world_height - worldY);
        }
        
        g2d.drawImage(image, x, y, null);
    }
}
