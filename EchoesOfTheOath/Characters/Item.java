package EchoesOfTheOath.Characters;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Item {
    public String name;
    public BufferedImage image;
    public String description;
    public int price;
    public String[] dialogueLines;
    public boolean isConsumable;

    public Item(String name, String desc, String imagePath, int price, String[] lines, boolean isConsumable) {
        this.name = name;
        this.description = desc;
        this.price = price;
        this.dialogueLines = lines;
        this.isConsumable = isConsumable;
        try {
            this.image = ImageIO.read(getClass().getResource("/EchoesOfTheOath/Resources/" + imagePath));
        } catch (Exception e) {
            System.err.println("Could not load: " + imagePath);
        }
    }

    public String getRandomLine() {
        if (dialogueLines == null || dialogueLines.length == 0) return "...";
        int index = (int)(Math.random() * dialogueLines.length);
        return dialogueLines[index];
    }
}