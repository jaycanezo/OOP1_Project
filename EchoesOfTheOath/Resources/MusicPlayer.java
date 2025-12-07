package EchoesOfTheOath.Resources;

import javax.sound.sampled.*;

import java.io.File;
import java.net.URL;

public class MusicPlayer {

    private Clip clip;

    public void playMusic(String fileName) {
        try {
            URL url = getClass().getResource("/EchoesOfTheOath/Resources/" + fileName);

            if (url == null) {
                System.out.println("Could not find file: " + fileName);
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playSFX(String fileName) {
        try {
            URL url = getClass().getResource("/EchoesOfTheOath/Resources/" + fileName);

            if (url == null) {
                System.out.println("SFX file not found: " + fileName);
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip sfxClip = AudioSystem.getClip();
            sfxClip.open(audioIn);

            sfxClip.start(); // Play once (no loop)

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
