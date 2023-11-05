/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package findthingsgame;

import java.net.URL;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

/**
 *
 * @author woody
 */
public class Audio {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Audio() {
        soundURL[0] = getClass().getResource("/assets/sounds/main-theme.wav");
        // Effect Audio
        soundURL[1] = getClass().getResource("/assets/sounds/get_key.wav");
        soundURL[2] = getClass().getResource("/assets/sounds/open_chest.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream audioFile = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioFile);
        } catch (Exception err) {

        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

}
