package EchoesOfTheOath.UI;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Sprite {
    private BufferedImage[] frames;
    private int currentFrame = 0;
    private boolean loops = true;

    /**
     * @param resourcePath
     * @param frameWidth 
     * @param frameHeight
     * @param frameCount
     */

    public Sprite(String resourcePath, int frameWidth, int frameHeight, int frameCount) {
        try {
            java.net.URL url = Sprite.class.getResource(resourcePath);
            if (url == null) {
                System.err.println("URL Null for: " + resourcePath);
                return;
            }
            
            BufferedImage sheet = ImageIO.read(url);

            if (sheet == null) {
                System.err.println("Sprite sheet not found: " + resourcePath);
                return;
            }

            frames = new BufferedImage[frameCount];
            int cols = sheet.getWidth() / frameWidth;

            for (int i = 0; i < frameCount; i++) {
                int x = (i % cols) * frameWidth;
                int y = (i / cols) * frameHeight;
            
                frames[i] = sheet.getSubimage(x, y, frameWidth, frameHeight);
            }

            System.out.println("Sprite loaded: " + resourcePath + " | Frames: " + frameCount);

        } catch (Exception e) {
            System.err.println("Failed to load sprite: " + resourcePath);
            e.printStackTrace();
        }
    }

    public void setLooping(boolean loops) {
        this.loops = loops;
    }

    public void update() {
        if (frames == null || frames.length <= 1) 
            return;

        if (currentFrame < frames.length - 1) {
            currentFrame++;
        } else if (loops) {
            currentFrame = 0; 
        }
    }

    public boolean isFinished() {
        return !loops && currentFrame == frames.length - 1;
    }

    public BufferedImage getCurrentFrame() {
        if (frames == null || frames.length == 0) return null;
        return frames[currentFrame];
    }

    public boolean isLoaded() {
        return frames != null && frames.length > 0;
    }
}
