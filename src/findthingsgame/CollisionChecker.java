/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package findthingsgame;

import Entity.Entity;

/**
 *
 * @author woody
 */
public class CollisionChecker {

    GamePanel gamepanel;

    public CollisionChecker(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
    }

    public void checkTile(Entity entity) {
        int entity_left_worldX = entity.worldX + entity.solidArea.x;
        int entity_right_worldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entity_top_worldY = entity.worldY + entity.solidArea.y;
        int entity_bottom_worldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entity_left_col = entity_left_worldX / gamepanel.tile_size;
        int entity_right_col = entity_right_worldX / gamepanel.tile_size;
        int entity_top_row = entity_top_worldY / gamepanel.tile_size;
        int entity_bottom_row = entity_bottom_worldY / gamepanel.tile_size;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entity_top_row = (entity_top_worldY - entity.speed) / gamepanel.tile_size;
                tileNum1 = gamepanel.tileManager.mapTileNum[entity_left_col][entity_top_row];
                tileNum2 = gamepanel.tileManager.mapTileNum[entity_right_col][entity_top_row];
                if (gamepanel.tileManager.tile[tileNum1].collision == true || gamepanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "up_left":
                entity_top_row = (entity_top_worldY - entity.speed) / gamepanel.tile_size;
                tileNum1 = gamepanel.tileManager.mapTileNum[entity_left_col][entity_top_row];
                tileNum2 = gamepanel.tileManager.mapTileNum[entity_right_col][entity_top_row];
                if (gamepanel.tileManager.tile[tileNum1].collision == true || gamepanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "up_right":
                entity_top_row = (entity_top_worldY - entity.speed) / gamepanel.tile_size;
                tileNum1 = gamepanel.tileManager.mapTileNum[entity_left_col][entity_top_row];
                tileNum2 = gamepanel.tileManager.mapTileNum[entity_right_col][entity_top_row];
                if (gamepanel.tileManager.tile[tileNum1].collision == true || gamepanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entity_bottom_row = (entity_bottom_worldY + entity.speed) / gamepanel.tile_size;
                tileNum1 = gamepanel.tileManager.mapTileNum[entity_left_col][entity_bottom_row];
                tileNum2 = gamepanel.tileManager.mapTileNum[entity_right_col][entity_bottom_row];
                if (gamepanel.tileManager.tile[tileNum1].collision == true || gamepanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down_left":
                entity_bottom_row = (entity_bottom_worldY + entity.speed) / gamepanel.tile_size;
                tileNum1 = gamepanel.tileManager.mapTileNum[entity_left_col][entity_bottom_row];
                tileNum2 = gamepanel.tileManager.mapTileNum[entity_right_col][entity_bottom_row];
                if (gamepanel.tileManager.tile[tileNum1].collision == true || gamepanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down_right":
                entity_bottom_row = (entity_bottom_worldY + entity.speed) / gamepanel.tile_size;
                tileNum1 = gamepanel.tileManager.mapTileNum[entity_left_col][entity_bottom_row];
                tileNum2 = gamepanel.tileManager.mapTileNum[entity_right_col][entity_bottom_row];
                if (gamepanel.tileManager.tile[tileNum1].collision == true || gamepanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entity_left_col = (entity_left_worldX - entity.speed) / gamepanel.tile_size;
                tileNum1 = gamepanel.tileManager.mapTileNum[entity_left_col][entity_top_row];
                tileNum2 = gamepanel.tileManager.mapTileNum[entity_left_col][entity_bottom_row];
                if (gamepanel.tileManager.tile[tileNum1].collision == true || gamepanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entity_right_col = (entity_right_worldX + entity.speed) / gamepanel.tile_size;
                tileNum1 = gamepanel.tileManager.mapTileNum[entity_right_col][entity_top_row];
                tileNum2 = gamepanel.tileManager.mapTileNum[entity_right_col][entity_bottom_row];
                if (gamepanel.tileManager.tile[tileNum1].collision == true || gamepanel.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkOBJ(Entity entity, boolean player) {
        int index = 999;

        if (gamepanel.is_in_house == false) {
            // Main Map Object Checker
            for (int i = 0; i < gamepanel.obj.length; i++) {
                if (gamepanel.obj[i] != null && gamepanel.obj[i].user_collect == false) {
                    // Get Entity Solid Area Position
                    entity.solidArea.x = entity.worldX + entity.solidArea.x;
                    entity.solidArea.y = entity.worldY + entity.solidArea.y;
                    // Get Object Solid Area Position
                    gamepanel.obj[i].solidArea.x = gamepanel.obj[i].worldX + gamepanel.obj[i].solidArea.x;
                    gamepanel.obj[i].solidArea.y = gamepanel.obj[i].worldY + gamepanel.obj[i].solidArea.y;

                    switch (entity.direction) {
                        case "up":
                            entity.solidArea.y -= entity.speed;
                            if (entity.solidArea.intersects(gamepanel.obj[i].solidArea)) {
                                if (gamepanel.obj[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (player == true) {
                                    index = i;
                                }
                            }
                            break;
                        case "up_left":
                            entity.solidArea.y -= entity.speed;
                            entity.solidArea.x -= entity.speed;
                            if (entity.solidArea.intersects(gamepanel.obj[i].solidArea)) {
                                if (gamepanel.obj[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (player == true) {
                                    index = i;
                                }
                            }
                            break;
                        case "up_right":
                            entity.solidArea.y -= entity.speed;
                            entity.solidArea.x += entity.speed;
                            if (entity.solidArea.intersects(gamepanel.obj[i].solidArea)) {
                                if (gamepanel.obj[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (player == true) {
                                    index = i;
                                }
                            }
                            break;
                        case "down":
                            entity.solidArea.y += entity.speed;
                            if (entity.solidArea.intersects(gamepanel.obj[i].solidArea)) {
                                if (gamepanel.obj[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (player == true) {
                                    index = i;
                                }
                            }
                            break;
                        case "down_left":
                            entity.solidArea.y += entity.speed;
                            entity.solidArea.x -= entity.speed;
                            if (entity.solidArea.intersects(gamepanel.obj[i].solidArea)) {
                                if (gamepanel.obj[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (player == true) {
                                    index = i;
                                }
                            }
                            break;
                        case "down_right":
                            entity.solidArea.y += entity.speed;
                            entity.solidArea.x += entity.speed;
                            if (entity.solidArea.intersects(gamepanel.obj[i].solidArea)) {
                                if (gamepanel.obj[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (player == true) {
                                    index = i;
                                }
                            }
                            break;
                        case "left":
                            entity.solidArea.x -= entity.speed;
                            if (entity.solidArea.intersects(gamepanel.obj[i].solidArea)) {
                                if (gamepanel.obj[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (player == true) {
                                    index = i;
                                }
                            }
                            break;
                        case "right":
                            entity.solidArea.x += entity.speed;
                            if (entity.solidArea.intersects(gamepanel.obj[i].solidArea)) {
                                if (gamepanel.obj[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (player == true) {
                                    index = i;
                                }
                            }
                            break;
                    }
                    entity.solidArea.x = entity.solidAreaDefaultX;
                    entity.solidArea.y = entity.solidAreaDefaultY;
                    gamepanel.obj[i].solidArea.x = gamepanel.obj[i].solidAreaDefaultX;
                    gamepanel.obj[i].solidArea.y = gamepanel.obj[i].solidAreaDefaultY;
                }
            }
        } else {
            // House Obj Checker
            for (int i = 0; i < gamepanel.house_obj.length; i++) {
                if (gamepanel.house_obj[i] != null && gamepanel.house_obj[i].user_collect == false) {
                    // Get Entity Solid Area Position
                    entity.solidArea.x = entity.worldX + entity.solidArea.x;
                    entity.solidArea.y = entity.worldY + entity.solidArea.y;
                    // Get Object Solid Area Position
                    gamepanel.house_obj[i].solidArea.x = gamepanel.house_obj[i].worldX + gamepanel.house_obj[i].solidArea.x;
                    gamepanel.house_obj[i].solidArea.y = gamepanel.house_obj[i].worldY + gamepanel.house_obj[i].solidArea.y;

                    switch (entity.direction) {
                        case "up":
                            entity.solidArea.y -= entity.speed;
                            if (entity.solidArea.intersects(gamepanel.house_obj[i].solidArea)) {
                                if (gamepanel.house_obj[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (player == true) {
                                    index = i;
                                }
                            }
                            break;
                        case "up_left":
                            entity.solidArea.y -= entity.speed;
                            entity.solidArea.x -= entity.speed;
                            if (entity.solidArea.intersects(gamepanel.house_obj[i].solidArea)) {
                                if (gamepanel.house_obj[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (player == true) {
                                    index = i;
                                }
                            }
                            break;
                        case "up_right":
                            entity.solidArea.y -= entity.speed;
                            entity.solidArea.x += entity.speed;
                            if (entity.solidArea.intersects(gamepanel.house_obj[i].solidArea)) {
                                if (gamepanel.house_obj[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (player == true) {
                                    index = i;
                                }
                            }
                            break;
                        case "down":
                            entity.solidArea.y += entity.speed;
                            if (entity.solidArea.intersects(gamepanel.house_obj[i].solidArea)) {
                                if (gamepanel.house_obj[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (player == true) {
                                    index = i;
                                }
                            }
                            break;
                        case "down_left":
                            entity.solidArea.y += entity.speed;
                            entity.solidArea.x -= entity.speed;
                            if (entity.solidArea.intersects(gamepanel.house_obj[i].solidArea)) {
                                if (gamepanel.house_obj[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (player == true) {
                                    index = i;
                                }
                            }
                            break;
                        case "down_right":
                            entity.solidArea.y += entity.speed;
                            entity.solidArea.x += entity.speed;
                            if (entity.solidArea.intersects(gamepanel.house_obj[i].solidArea)) {
                                if (gamepanel.house_obj[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (player == true) {
                                    index = i;
                                }
                            }
                            break;
                        case "left":
                            entity.solidArea.x -= entity.speed;
                            if (entity.solidArea.intersects(gamepanel.house_obj[i].solidArea)) {
                                if (gamepanel.house_obj[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (player == true) {
                                    index = i;
                                }
                            }
                            break;
                        case "right":
                            entity.solidArea.x += entity.speed;
                            if (entity.solidArea.intersects(gamepanel.house_obj[i].solidArea)) {
                                if (gamepanel.house_obj[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (player == true) {
                                    index = i;
                                }
                            }
                            break;
                    }
                    entity.solidArea.x = entity.solidAreaDefaultX;
                    entity.solidArea.y = entity.solidAreaDefaultY;
                    gamepanel.house_obj[i].solidArea.x = gamepanel.house_obj[i].solidAreaDefaultX;
                    gamepanel.house_obj[i].solidArea.y = gamepanel.house_obj[i].solidAreaDefaultY;
                }
            }
        }

        return index;
    }
}
