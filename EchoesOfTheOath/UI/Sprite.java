package EchoesOfTheOath.UI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Sprite {

    private BufferedImage[] frames;
    private int currentFrame = 0;
    private boolean loops = true;

    /**
     * Loads a sprite sheet and slices it into frames automatically.
     *
     * @param resourcePath  Classpath resource path e.g. "/EchoesOfTheOath/Resources/TitleScreen.png"
     * @param frameWidth    Width of a single frame in pixels
     * @param frameHeight   Height of a single frame in pixels
     * @param frameCount    Total number of frames to extract (left to right, top to bottom)
     */
    public Sprite(String resourcePath, int frameWidth, int frameHeight, int frameCount) {
        try {
            BufferedImage sheet = ImageIO.read(Sprite.class.getResource(resourcePath));

            if (sheet == null) {
                System.err.println("Sprite sheet not found: " + resourcePath);
                return;
            }

            frames = new BufferedImage[frameCount];
            int cols = sheet.getWidth() / frameWidth; // frames per row

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

    /** Advance to the next frame (call this on a Timer tick) */
    public void update() {
        if (frames == null || frames.length <= 1) return;

        if (currentFrame < frames.length - 1) {
            currentFrame++;
        } else {
            if (loops) {
                currentFrame = 0; // Reset to start if looping
            } else {
                // Stay on the last frame if not looping
                currentFrame = frames.length - 1; 
            }
        }
    }

    /** Get the current frame image to draw */
    public BufferedImage getCurrentFrame() {
        if (frames == null || frames.length == 0) return null;
        return frames[currentFrame];
    }

    public boolean isLoaded() {
        return frames != null && frames.length > 0;
    }
}
