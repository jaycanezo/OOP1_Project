package EchoesOfTheOath.UI;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel {

    private GameWindow game;
    private Image background;
    private Sprite characterSprite;
    private int currentMap = 1;
    private String dialogue = "Welcome, Player. Use WASD to explore.";

    public GamePanel(GameWindow game) {
        this.game = game;
        this.setFocusable(true);
        setBackground(Color.darkGray);

        loadResources();

        Timer animationTimer = new Timer(150, e -> {
            characterSprite.update();
            repaint();
        });
        animationTimer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleInput(e.getKeyCode());
            }
        });
    }

    private void loadResources() {
        characterSprite = new Sprite("/EchoesOfTheOath/Resources/character.png", 1002, 835, 12);
        updateBackground();
    }

    private void updateBackground() {
        try {
            String path = "/EchoesOfTheOath/Resources/" + currentMap + ".jpg";
            URL url = getClass().getResource(path);
            
            if (url != null) {
                background = ImageIO.read(url);
            } else {
                System.err.println("Resource not found: " + path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleInput(int keyCode) {
        if (keyCode == KeyEvent.VK_D) {
            currentMap = Math.min(4, currentMap + 1);
            dialogue = "You venture forward into a new area.";
            updateBackground();
        } else if (keyCode == KeyEvent.VK_A) {
            currentMap = Math.max(1, currentMap - 1);
            dialogue = "You turn back toward the previous path.";
            updateBackground();
        } else if (keyCode == KeyEvent.VK_W) {
            currentMap = Math.max(1, currentMap - 1);
            dialogue = "The wind whistles through the narrow alley.";
            updateBackground();
        } else if (keyCode == KeyEvent.VK_S) {
            currentMap = Math.max(1, currentMap + 1);
            dialogue = "You move cautiously into the shadows.";
            updateBackground();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (background != null) {
            g2.drawImage(background, 0, 0, 600, 500, null);
        }
        g2.setColor(new Color(25, 25, 25));
        g2.fillRect(600, 0, 480, 500);

        BufferedImage frame = characterSprite.getCurrentFrame();
        if (frame != null) {
            g2.drawImage(frame, 740, 150, 200, 200, null);
        }

        g2.setColor(new Color(30, 30, 30));
        g2.fillRect(0, 500, 1080, 220);
        
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Monospaced", Font.BOLD, 22));
        g2.drawString(dialogue, 50, 580);
    }
}