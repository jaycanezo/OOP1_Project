package EchoesOfTheOath.UI;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.*;

public class MusicPlayer {
    private Clip clip;
    private final Map<String, URL> soundCache = new HashMap<>();
    private String currentPlaying = "";

    public void playMusic(String fileName) {
        if (fileName.equals(currentPlaying)) {
            return;
        }

        stopMusic();

        try {
            URL url = soundCache.getOrDefault(fileName, 
                getClass().getResource("/EchoesOfTheOath/Resources/" + fileName));

            if (url == null) return;

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

        currentPlaying = fileName;
    }

    public void playSFX(String fileName) {
        new Thread(() -> {
            try {
                URL url = getClass().getResource("/EchoesOfTheOath/Resources/" + fileName);
                if (url == null) return;

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
        currentPlaying = "";

        if (clip != null) {
            clip.stop();
            clip.flush(); 
            clip.close(); 
            clip = null;
        }
    }
}