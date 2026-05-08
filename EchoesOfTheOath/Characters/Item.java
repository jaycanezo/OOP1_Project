package EchoesOfTheOath.Characters;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Item {
    private final String name;
    private final BufferedImage image;
    private final String description;
    private final int price;
    private final String[] dialogueLines;
    private final boolean isConsumable;
    
    private boolean isEquipped = false;

    public Item(String name, String desc, String imagePath, int price, String[] lines, boolean isConsumable) {
        this.name = name;
        this.description = desc;
        this.price = price;
        this.dialogueLines = lines;
        this.isConsumable = isConsumable;
        BufferedImage img = null;

        try {
            img = ImageIO.read(getClass().getResource("/EchoesOfTheOath/Resources/" + imagePath));
        } catch (Exception e) {
            System.err.println("Could not load: " + imagePath);
        }

        this.image = img;
    }

    public String getName() { return name; }
    public BufferedImage getImage() { return image; }
    public String getDescription() { return description; }
    public int getPrice() { return price; }
    public boolean isConsumable() { return isConsumable; }

    public boolean isEquipped() { return isEquipped; }
    public void setEquipped(boolean equipped) { this.isEquipped = equipped; }

    public String getRandomLine() {
        if (dialogueLines == null || dialogueLines.length == 0) 
            return "...";
        
        return dialogueLines[(int)(Math.random() * dialogueLines.length)];
    }
}