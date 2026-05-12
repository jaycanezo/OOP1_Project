package EchoesOfTheOath.UI;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.sound.sampled.*;

public class MusicPlayer {
    private Clip clip;
    private final Map<String, URL> soundCache = new HashMap<>();
    private String currentPlaying = "";

    private static final Map<String, Clip> sfxClipCache = new ConcurrentHashMap<>();

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
                Clip sfxClip = sfxClipCache.get(fileName);
                
                if (sfxClip == null) {
                    URL url = getClass().getResource("/EchoesOfTheOath/Resources/" + fileName);
                    if (url == null) {
                        System.out.println("SFX missing: " + fileName);
                        return;
                    }

                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                    sfxClip = AudioSystem.getClip();
                    sfxClip.open(audioIn);
                    
                    sfxClipCache.put(fileName, sfxClip);
                }
                
                sfxClip.setFramePosition(0);
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
    
    public static void preloadSFX(String fileName) {
        try {
            if (!sfxClipCache.containsKey(fileName)) {
                URL url = MusicPlayer.class.getResource("/EchoesOfTheOath/Resources/" + fileName);
                if (url != null) {
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                    Clip c = AudioSystem.getClip();
                    c.open(audioIn);
                    sfxClipCache.put(fileName, c);
                }
            }
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
    }
}