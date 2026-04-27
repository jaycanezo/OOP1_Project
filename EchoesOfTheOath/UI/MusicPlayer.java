package EchoesOfTheOath.UI;

import java.net.URL;
import javax.sound.sampled.*;

public class MusicPlayer {
    private Clip clip;

    public void playMusic(String fileName) {
        if (clip != null && clip.isRunning()) {
            stopMusic();
        }

        try {
            URL url = getClass().getResource("/EchoesOfTheOath/Resources/" + fileName);

            if (url == null) 
                return;

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
        new Thread(() -> {
            try {
                URL url = getClass().getResource("/EchoesOfTheOath/Resources/" + fileName);

                if (url == null) 
                    return;

                AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                Clip sfxClip = AudioSystem.getClip();

                sfxClip.open(audioIn);

                sfxClip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        sfxClip.close();
                    }
                });
                sfxClip.start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void stopMusic() {
        if (clip != null) {
            clip.stop();
            clip.close();

            clip = null;
        }
    }
}
