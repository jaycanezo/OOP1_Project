package EchoesOfTheOath.UI;

import EchoesOfTheOath.Characters.*;
import EchoesOfTheOath.Characters.Character;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class NewStoryPanel extends JPanel {
    private GameWindow game;
    private Image currentBackground;
    private Sprite playerSprite;
    private Sprite npcSprite; // New: Reserved for NPCs on the right
    
    private String currentSpeaker = ""; // Tracks who is talking
    private String dialogueText = "Welcome to the legend...";
    
    // VN State Management
    private boolean showPlayer = false;
    private boolean showNPC = false;

    public NewStoryPanel(GameWindow game) {
        this.game = game;
        this.setFocusable(true);
        this.setLayout(null); // Manual positioning for VN elements

        // Start the animation motor for both sprites
        Timer animationTimer = new Timer(100, e -> {
            if (playerSprite != null) playerSprite.update();
            if (npcSprite != null) npcSprite.update();
            repaint();
        });
        animationTimer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Space to progress or other inputs
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    progressStory();
                }
            }
        });
    }

    /**
     * Called by GameWindow when switching to this panel to ensure
     * the hero picked in CharacterSelectPanel is displayed.
     */
    public void loadSelectedHero() {
        Character chosen = game.getChosenCharacter();
        if (chosen != null) {
            this.playerSprite = chosen.getIdleSprite();
        }
        setBackgroundImage("forest_path.jpg"); // Initial setting
    }

    public void setBackgroundImage(String fileName) {
        try {
            this.currentBackground = new ImageIcon(getClass().getResource("/EchoesOfTheOath/Resources/" + fileName)).getImage();
        } catch (Exception e) { System.out.println("BG Load Error: " + fileName); }
    }

    /**
     * Set the current scene visuals and text.
     */
    public void setScene(String speaker, String text, boolean playerActive, Sprite npc) {
        this.currentSpeaker = speaker;
        this.dialogueText = text;
        this.showPlayer = playerActive;
        this.npcSprite = npc;
        this.showNPC = (npc != null);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 1. DRAW BACKGROUND (Full Screen)
        if (currentBackground != null) {
            g2.drawImage(currentBackground, 0, 0, getWidth(), getHeight(), null);
        }

        // 2. DRAW CHARACTER SPRITES (VN Style)
        // Player on the Left
        if (showPlayer && playerSprite != null) {
            drawCharacter(g2, playerSprite, 50, getHeight() - 550, 450, 450, true);
        }

        // NPC on the Right
        if (showNPC && npcSprite != null) {
            drawCharacter(g2, npcSprite, getWidth() - 500, getHeight() - 550, 450, 450, false);
        }

        // 3. DRAW DIALOGUE UI (Bottom)
        drawDialogueBox(g2);
    }

    private void drawCharacter(Graphics2D g2, Sprite s, int x, int y, int w, int h, boolean isSpeaker) {
        BufferedImage frame = s.getCurrentFrame();
        if (frame != null) {
            // Dim the character if they aren't the one speaking
            if (!currentSpeaker.equals(isSpeaker ? game.getChosenCharacter().getName() : "NPC")) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            } else {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            }
            g2.drawImage(frame, x, y, w, h, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f)); // Reset
        }
    }

    private void drawDialogueBox(Graphics2D g2) {
        int boxX = 50;
        int boxY = getHeight() - 220;
        int boxW = getWidth() - 100;
        int boxH = 180;

        // Semi-transparent box
        g2.setColor(new Color(0, 0, 0, 200));
        g2.fillRoundRect(boxX, boxY, boxW, boxH, 20, 20);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(boxX, boxY, boxW, boxH, 20, 20);

        // Name Tag
        if (!currentSpeaker.isEmpty()) {
            g2.setColor(new Color(50, 50, 150));
            g2.fillRect(boxX + 20, boxY - 30, 200, 40);
            g2.setColor(Color.WHITE);
            g2.drawRect(boxX + 20, boxY - 30, 200, 40);
            g2.setFont(new Font("Serif", Font.BOLD, 22));
            g2.drawString(currentSpeaker, boxX + 40, boxY - 2);
        }

        // Dialogue text
        g2.setFont(new Font("Monospaced", Font.PLAIN, 20));
        drawWrappedText(g2, dialogueText, boxX + 40, boxY + 50, boxW - 80);
    }

    private void drawWrappedText(Graphics2D g2, String text, int x, int y, int maxWidth) {
        g2.setFont(new Font("Monospaced", Font.BOLD, 22)); // Set your preferred VN font
        FontMetrics fm = g2.getFontMetrics();
        String[] words = text.split(" ");
        StringBuilder currentLine = new StringBuilder();
        int lineHeight = fm.getHeight();
        int drawY = y;

        for (String word : words) {
            // Check if adding the next word exceeds the box width
            if (fm.stringWidth(currentLine.toString() + word) < maxWidth) {
                currentLine.append(word).append(" ");
            } else {
                // Draw current line and move down to the next row
                g2.drawString(currentLine.toString(), x, drawY);
                drawY += lineHeight;
                currentLine = new StringBuilder(word).append(" ");
            }
        }
        // Draw the very last line that was in the buffer
        g2.drawString(currentLine.toString(), x, drawY);
    }
    
    private void progressStory() {
        // Logic to switch scenes/text based on a story counter
        System.out.println("Progressing Dialogue...");
    }
}
