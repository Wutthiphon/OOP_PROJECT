/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package findthingsgame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author woody
 */
public class KeyboardHandler implements KeyListener {

    GamePanel gamepanel;
    public boolean upP, leftP, downP, rightP;

    public KeyboardHandler(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyboard_press = e.getKeyCode();

        // Title Stage
        if (gamepanel.gameStage == gamepanel.titleStage) {
            if (gamepanel.ui.titleScreen == 0) {
                if (keyboard_press == KeyEvent.VK_W || keyboard_press == KeyEvent.VK_UP) {
                    gamepanel.ui.cmdNum--;
                    if (gamepanel.ui.cmdNum < 0) {
                        gamepanel.ui.cmdNum = 1;
                    }
                }
                if (keyboard_press == KeyEvent.VK_S || keyboard_press == KeyEvent.VK_DOWN) {
                    gamepanel.ui.cmdNum++;
                    if (gamepanel.ui.cmdNum > 1) {
                        gamepanel.ui.cmdNum = 0;
                    }
                }
                if (keyboard_press == KeyEvent.VK_ENTER) {
                    if (gamepanel.ui.cmdNum == 0) {
                        gamepanel.ui.titleScreen = 1;
                    }
                    if (gamepanel.ui.cmdNum == 1) {
                        System.exit(0);
                    }
                }
            } else if (gamepanel.ui.titleScreen == 1) {
                if (keyboard_press == KeyEvent.VK_W || keyboard_press == KeyEvent.VK_UP) {
                    gamepanel.ui.cmdNum--;
                    if (gamepanel.ui.cmdNum < 0) {
                        gamepanel.ui.cmdNum = 3;
                    }
                }
                if (keyboard_press == KeyEvent.VK_S || keyboard_press == KeyEvent.VK_DOWN) {
                    gamepanel.ui.cmdNum++;
                    if (gamepanel.ui.cmdNum > 3) {
                        gamepanel.ui.cmdNum = 0;
                    }
                }
                if (keyboard_press == KeyEvent.VK_ENTER) {
                    if (gamepanel.ui.cmdNum == 0) {
                        startGame();
                        gamepanel.tileManager.loadMap("/assets/maps/level1.map", 1);
                    }
                    if (gamepanel.ui.cmdNum == 1) {
                        startGame();
                        gamepanel.tileManager.loadMap("/assets/maps/level2.map", 2);
                    }
                    if (gamepanel.ui.cmdNum == 2) {
                        startGame();
                        gamepanel.tileManager.loadMap("/assets/maps/level3.map", 3);
                    }
                    if (gamepanel.ui.cmdNum == 3) {
                        gamepanel.ui.titleScreen = 0;
                        gamepanel.ui.cmdNum = 0;
                    }
                }
            }
        }
        // Play Stage
        if (gamepanel.gameStage == gamepanel.playStage) {

            if (keyboard_press == KeyEvent.VK_W || keyboard_press == KeyEvent.VK_UP) {
                upP = true;
            }
            if (keyboard_press == KeyEvent.VK_A || keyboard_press == KeyEvent.VK_LEFT) {
                leftP = true;
            }
            if (keyboard_press == KeyEvent.VK_S || keyboard_press == KeyEvent.VK_DOWN) {
                downP = true;
            }
            if (keyboard_press == KeyEvent.VK_D || keyboard_press == KeyEvent.VK_RIGHT) {
                rightP = true;
            }
        }
        // Congratulations Stage
        if (gamepanel.gameStage == gamepanel.endStage) {
            if (keyboard_press == KeyEvent.VK_W || keyboard_press == KeyEvent.VK_UP) {
                gamepanel.ui.cmdNum--;
                if (gamepanel.ui.cmdNum < 0) {
                    gamepanel.ui.cmdNum = 1;
                }
            }
            if (keyboard_press == KeyEvent.VK_S || keyboard_press == KeyEvent.VK_DOWN) {
                gamepanel.ui.cmdNum++;
                if (gamepanel.ui.cmdNum > 1) {
                    gamepanel.ui.cmdNum = 0;
                }
            }
            if (keyboard_press == KeyEvent.VK_ENTER) {
                if (gamepanel.ui.cmdNum == 0) {
                    gamepanel.ui.titleScreen = 0;
                    gamepanel.gameStage = 0;
                }
                if (gamepanel.ui.cmdNum == 1) {
                    System.exit(0);
                }
            }
        }
    }

    public void startGame() {
        gamepanel.gameStage = gamepanel.playStage;
        gamepanel.is_in_house = false;
        gamepanel.player.resetPosition();
        gamepanel.playAudio(0);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyboard_press = e.getKeyCode();

        if (keyboard_press == KeyEvent.VK_W || keyboard_press == KeyEvent.VK_UP) {
            upP = false;
        }
        if (keyboard_press == KeyEvent.VK_A || keyboard_press == KeyEvent.VK_LEFT) {
            leftP = false;
        }
        if (keyboard_press == KeyEvent.VK_S || keyboard_press == KeyEvent.VK_DOWN) {
            downP = false;
        }
        if (keyboard_press == KeyEvent.VK_D || keyboard_press == KeyEvent.VK_RIGHT) {
            rightP = false;
        }
    }

}
