/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package findthingsgame;

import object.SuperObject;
import object.obj_key;
import object.obj_chest;
import object.obj_door;
import object.obj_house1;
import object.obj_house2;
import object.obj_bone;
import object.obj_broom;
import object.obj_gear;
import object.obj_hat;
import object.obj_helmet;
import object.obj_horse_shoe;
import object.obj_knife;
import object.obj_love_potion;
import object.obj_magic_wand;
import object.obj_money_bag;
import object.obj_monster_horn;
import object.obj_pipe;
import object.obj_poison;
import object.obj_shield;
import object.obj_storybook;
import object.obj_wine_bottle;

/**
 *
 * @author woody
 */
public class AssetSetter {

    GamePanel gamepanel;
    int map_id = 0;

    public AssetSetter(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
    }

    public void setObject(int map_id) {
        this.map_id = map_id;
        switch (this.map_id) {
            case 1:
                setup(0, "key", 1, 45);
                setup(1, "key", 98, 98);
                setup(2, "chest", 21, 6);
                setup(3, "chest", 62, 29);
                setup(4, "house1", 88, 6);
                setup(5, "house1", 94, 11);
                setup(6, "house1", 94, 14);
                setup(7, "house1", 79, 74);
                setup(8, "house1", 68, 88);
                setup(9, "house2", 5, 7);
                setup(10, "house2", 35, 49);
                setup(11, "house2", 35, 50);
                setup(12, "house2", 4, 55);
                setup(13, "house2", 7, 91);
                setup(14, "bone", 98, 31);
                setup(15, "horse-shoe", 7, 94);
                setup(17, "broom", 5, 56);
                break;
            case 2:
                setup(0, "key", 95, 3);
                setup(1, "chest", 19, 15);
                setup(2, "love-potion", 21, 14);
                setup(3, "shield", 53, 54);
                setup(4, "gear", 1, 98);
                setup(5, "house1", 67, 63);
                setup(6, "house1", 14, 41);
                setup(7, "house2", 56, 8);
                setup(8, "house2", 65, 94);
                setup(9, "house1", 31, 62);
                setup(10, "house2", 97, 41);
                break;
            case 3:
                setup(0, "broom", 47, 19);
                setup(1, "bone", 65, 59);
                setup(2, "hat", 27, 64);
                setup(3, "knife", 36, 39);
                setup(4, "magic-wand", 53, 35);
                setup(5, "poison", 41, 13);
                setup(6, "key", 32, 57);
                setup(7, "key", 47, 22);
                setup(8, "key", 55, 60);
                setup(9, "house2", 65, 49);
                break;
        }
    }

    public void setup(int index, String item_name, int x, int y) {
        switch (item_name) {
            case "key":
                gamepanel.obj[index] = new obj_key(gamepanel);
                break;
            case "chest":
                gamepanel.obj[index] = new obj_chest(gamepanel);
                break;
            case "bone":
                gamepanel.obj[index] = new obj_bone(gamepanel);
                break;
            case "broom":
                gamepanel.obj[index] = new obj_broom(gamepanel);
                break;
            case "gear":
                gamepanel.obj[index] = new obj_gear(gamepanel);
                break;
            case "hat":
                gamepanel.obj[index] = new obj_hat(gamepanel);
                break;
            case "helmet":
                gamepanel.obj[index] = new obj_helmet(gamepanel);
                break;
            case "horse-shoe":
                gamepanel.obj[index] = new obj_horse_shoe(gamepanel);
                break;
            case "knife":
                gamepanel.obj[index] = new obj_knife(gamepanel);
                break;
            case "love-potion":
                gamepanel.obj[index] = new obj_love_potion(gamepanel);
                break;
            case "magic-wand":
                gamepanel.obj[index] = new obj_magic_wand(gamepanel);
                break;
            case "money-bag":
                gamepanel.obj[index] = new obj_money_bag(gamepanel);
                break;
            case "monster-horn":
                gamepanel.obj[index] = new obj_monster_horn(gamepanel);
                break;
            case "pipe":
                gamepanel.obj[index] = new obj_pipe(gamepanel);
                break;
            case "poison":
                gamepanel.obj[index] = new obj_poison(gamepanel);
                break;
            case "shield":
                gamepanel.obj[index] = new obj_shield(gamepanel);
                break;
            case "storybook":
                gamepanel.obj[index] = new obj_storybook(gamepanel);
                break;
            case "wine-bottle":
                gamepanel.obj[index] = new obj_wine_bottle(gamepanel);
                break;
            case "house1":
                gamepanel.obj[index] = new obj_house1(gamepanel);
                gamepanel.obj[index].in_house_obj = setInHouseObject(index);
                break;
            case "house2":
                gamepanel.obj[index] = new obj_house2(gamepanel);
                gamepanel.obj[index].in_house_obj = setInHouseObject(index);
                break;
        }

        gamepanel.obj[index].worldX = x * gamepanel.tile_size;
        gamepanel.obj[index].worldY = y * gamepanel.tile_size;
    }

    public SuperObject[] setInHouseObject(int house_id) {
        SuperObject[] house_obj = new SuperObject[20];
        // Init House Door
        house_obj[0] = new obj_door(gamepanel);
        house_obj[0].in_house_obj_x = 49;
        house_obj[0].in_house_obj_y = 48;
        // Set In House Item
        switch (this.map_id) {
            case 1:
                switch (house_id) {
                    case 4:
                        house_obj[1] = new obj_helmet(gamepanel);
                        house_obj[1].in_house_obj_x = 34;
                        house_obj[1].in_house_obj_y = 49;
                        break;
                    case 7:
                        house_obj[1] = new obj_magic_wand(gamepanel);
                        house_obj[1].in_house_obj_x = 47;
                        house_obj[1].in_house_obj_y = 35;
                        break;
                    case 10:
                        house_obj[1] = new obj_key(gamepanel);
                        house_obj[1].in_house_obj_x = 52;
                        house_obj[1].in_house_obj_y = 52;
                        house_obj[2] = new obj_storybook(gamepanel);
                        house_obj[2].in_house_obj_x = 56;
                        house_obj[2].in_house_obj_y = 49;
                        break;
                    case 13:
                        house_obj[1] = new obj_hat(gamepanel);
                        house_obj[1].in_house_obj_x = 42;
                        house_obj[1].in_house_obj_y = 65;
                        break;
                }
                break;
            case 2:
                switch (house_id) {
                    case 5:
                        house_obj[1] = new obj_monster_horn(gamepanel);
                        house_obj[1].in_house_obj_x = 42;
                        house_obj[1].in_house_obj_y = 65;
                        house_obj[2] = new obj_key(gamepanel);
                        house_obj[2].in_house_obj_x = 42;
                        house_obj[2].in_house_obj_y = 41;
                        break;
                    case 7:
                        house_obj[1] = new obj_poison(gamepanel);
                        house_obj[1].in_house_obj_x = 65;
                        house_obj[1].in_house_obj_y = 49;
                        break;
                    case 9:
                        house_obj[1] = new obj_money_bag(gamepanel);
                        house_obj[1].in_house_obj_x = 47;
                        house_obj[1].in_house_obj_y = 35;
                        house_obj[2] = new obj_chest(gamepanel);
                        house_obj[2].in_house_obj_x = 52;
                        house_obj[2].in_house_obj_y = 35;
                        break;
                    case 10:
                        house_obj[1] = new obj_wine_bottle(gamepanel);
                        house_obj[1].in_house_obj_x = 42;
                        house_obj[1].in_house_obj_y = 49;
                        break;
                }
                break;
            case 3:
                switch (house_id) {
                    case 9:
                        house_obj[1] = new obj_chest(gamepanel);
                        house_obj[1].in_house_obj_x = 52;
                        house_obj[1].in_house_obj_y = 35;
                        house_obj[2] = new obj_chest(gamepanel);
                        house_obj[2].in_house_obj_x = 42;
                        house_obj[2].in_house_obj_y = 43;
                        house_obj[3] = new obj_chest(gamepanel);
                        house_obj[3].in_house_obj_x = 44;
                        house_obj[3].in_house_obj_y = 41;
                        house_obj[4] = new obj_love_potion(gamepanel);
                        house_obj[4].in_house_obj_x = 47;
                        house_obj[4].in_house_obj_y = 35;
                        house_obj[5] = new obj_wine_bottle(gamepanel);
                        house_obj[5].in_house_obj_x = 42;
                        house_obj[5].in_house_obj_y = 41;
                        house_obj[6] = new obj_pipe(gamepanel);
                        house_obj[6].in_house_obj_x = 34;
                        house_obj[6].in_house_obj_y = 49;
                        break;
                }
                break;
        }
        return house_obj;
    }

    public void loadHouseObject(int index) {
        SuperObject house = gamepanel.obj[index];

        // Load From House Object
        int i = 0;
        for (SuperObject house_obj : house.in_house_obj) {
            if (house_obj != null) {
                gamepanel.house_obj[i] = house_obj;
                gamepanel.house_obj[i].worldX = house_obj.in_house_obj_x * gamepanel.tile_size;
                gamepanel.house_obj[i].worldY = house_obj.in_house_obj_y * gamepanel.tile_size;
            }
            i++;
        }
    }
}
