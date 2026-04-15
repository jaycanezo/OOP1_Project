package EchoesOfTheOath.UI;

import EchoesOfTheOath.Characters.*;
import EchoesOfTheOath.Characters.Character;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;

public class StoryPanel extends JPanel {
    private GameWindow game;
    private Character player;
    private int optionsCursor = 0;
    private Shop gameShop = new Shop();
    private Npc npcManager = new Npc();
    private boolean showPlayer = false;
    private boolean showNPC = false;
    private Sprite currentBackground;
    private Sprite playerSprite, npcSprite;
    private String currentSpeaker = "";
    private int lineIndex = 0;
    private final int PLAY_STATE = 0, INVENTORY_STATE = 1, SHOP_STATE = 2, SUB_MENU_STATE = 3, OPTIONS_STATE = 4;
    private int gameState = PLAY_STATE;
    private int slotCol = 0, slotRow = 0, subMenuCursor = 0, scrollOffset = 0;
    private java.util.Map<String, Sprite> backgroundRegistry = new java.util.HashMap<>();
    private String[] dialogueText;
    private int currentNation = 1;
   
    public StoryPanel(GameWindow game) {
        this.game = game;
        this.setFocusable(true);
        this.setLayout(null);
        setNation(1);

        loadBG("nation1_bg1.png", 1920, 1080, 1);
        loadBG("nation1_bg2.png", 1920, 1080, 1);
        loadBG("nation1_bg3.png", 1920, 1080, 1);
        loadBG("nation1_bg4.png", 1920, 1080, 1);
        loadBG("nation1_bg5.png", 1920, 1080, 1);
        loadBG("nation1_bg6.png", 1920, 1080, 1);
        loadBG("nation1_bg6.1.png", 1920, 1080, 1);
        loadBG("nation1_bg7.png", 1920, 1080, 1);
        loadBG("nation1_bg8.png", 1920, 1080, 1);
        loadBG("nation1_bg8.1.png", 1920, 1080, 1);
        loadBG("nation1_bg8.2.png", 1920, 1080, 30);
        loadBG("nation1_storyline1.png", 1920, 1080, 7);
        
        Timer animationTimer = new Timer(100, e -> {
            if (playerSprite != null) playerSprite.update();
            if (npcSprite != null) npcSprite.update();
            if (currentBackground != null) currentBackground.update();
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

    public void setNation(int nationNumber) {
        this.currentNation = nationNumber;
        this.dialogueText = DialogueManager.getNationDialogue(nationNumber);
        resetStory();
    }

    public void resetStory() {
        this.lineIndex = 0;
        this.currentSpeaker = "";
        repaint();
    }

    public int getLineIndex() {
        return this.lineIndex;
    }

    public void loadSelectedHero() {
        this.player = game.getChosenCharacter();
        if (this.player != null) { 
            this.playerSprite = this.player.getIdleSprite(); 
        }
        if (currentNation == 1) {
            if (lineIndex >= 59) {
                setBackgroundImage("nation1_bg8.png");
            } else if (lineIndex >= 37) {
                setBackgroundImage("nation1_bg6.png");
            } else {
                setBackgroundImage("nation1_bg1.png");
            }
        }
        this.showPlayer = true;
    }

    private void handleInput(int code) {
        if (gameState == PLAY_STATE) {
            if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ENTER) progressStory();
            if (code == KeyEvent.VK_I) { gameState = INVENTORY_STATE; resetCursor(); }
            if (code == KeyEvent.VK_P) { gameState = SHOP_STATE; resetCursor(); }
            if (code == KeyEvent.VK_O || code == KeyEvent.VK_ESCAPE) { 
                gameState = OPTIONS_STATE; 
                optionsCursor = 0; 
            }
        } 
        else if (gameState == OPTIONS_STATE) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) optionsCursor = (optionsCursor > 0) ? optionsCursor - 1 : 2;
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) optionsCursor = (optionsCursor < 2) ? optionsCursor + 1 : 0;
            if (code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_O) gameState = PLAY_STATE;
            if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) executeOptionsAction();
        }
        else {
            if (code == KeyEvent.VK_I) {
                gameState = (gameState == INVENTORY_STATE) ? PLAY_STATE : INVENTORY_STATE;
                resetCursor();
            }
            else if (code == KeyEvent.VK_P) {
                gameState = (gameState == SHOP_STATE) ? PLAY_STATE : SHOP_STATE;
                resetCursor();
            }
            else if (code == KeyEvent.VK_ESCAPE) {
                gameState = PLAY_STATE;
            }
            else if (gameState == SUB_MENU_STATE) { 
                handleSubMenuInput(code); 
            } 
            else {
                handleGridMovement(code); 
            }
        }
        repaint();
    }

    private void executeOptionsAction() {
        switch (optionsCursor) {
            case 0 -> { 
                gameState = PLAY_STATE; 
                game.loadGame(); 
            }
            case 1 -> { 
                game.autosave(); 
                game.showScreen("start"); 
            }
            case 2 -> { 
                game.autosave(); 
                System.exit(0); 
            }
        }
    }

    private void progressStory() {
        MusicPlayer bgm = game.getBgm();
        lineIndex++;
        if (dialogueText == null || lineIndex >= dialogueText.length) { 
            game.showScreen("battle"); 
            return; 
        }

        if (currentNation == 1) {
            switch (lineIndex) {
                case 0: bgm.stopMusic(); bgm.playMusic("nation1_bgm.WAV"); break;
                case 1: bgm.playSFX("nation1_sfx1.wav"); break;
                case 2: setBackgroundImage("nation1_bg2.png"); currentSpeaker = ""; break;
                case 4: bgm.playSFX("nation1_sfx1.wav"); break;
                case 5: updateScene("Guard", true); bgm.playSFX("nation1_sfx2.wav"); currentSpeaker = ""; break;
                case 6: updateScene("Guard", true); break;
                case 7: currentSpeaker = player.getName(); break;
                case 8: updateScene("Guard", true); break;
                case 9:  currentSpeaker = ""; break;
                case 10: setBackgroundImage("nation1_bg3.png"); bgm.playSFX("nation1_sfx1.wav"); this.showNPC = false; currentSpeaker = ""; break;
                case 11: updateScene("Informant", true); currentSpeaker = ""; break; 
                case 12: currentSpeaker = "Informant"; break;
                case 13: currentSpeaker = player.getName(); break;
                case 14: currentSpeaker = "Informant"; break;
                case 15: currentSpeaker = player.getName(); break;
                case 16: currentSpeaker = "Informant"; break;
                case 17: setBackgroundImage("nation1_bg4.png"); this.showNPC = false; currentSpeaker = " "; break;
                case 18: updateScene(player.getName(), false); break;
                case 20: currentSpeaker = " "; break;
                case 22: bgm.playSFX("nation1_sfx3.wav"); break;
                case 23: setBackgroundImage("nation1_bg5.png"); bgm.stopMusic(); bgm.playMusic("nation1_bgm2.WAV"); updateScene(player.getName(), false); currentSpeaker = ""; break;
                case 25: bgm.playSFX("nation1_sfx4.wav"); break;
                case 26: setBackgroundImage("nation1_bg6.png"); break;
                case 27: updateScene("Attendant", true); currentSpeaker = "Attendant"; break;
                case 28: currentSpeaker = player.getName(); break;
                case 29: currentSpeaker = "Attendant"; break;
                case 30: currentSpeaker = player.getName(); break;
                case 31: currentSpeaker = "Attendant"; break;
                case 32: setBackgroundImage("nation1_bg6.1.png"); showPlayer = false; showNPC = false; break;
                case 33: showPlayer = true; break;
                case 36: setBackgroundImage("nation1_bg6.png"); currentSpeaker = "Attendant"; showNPC = true; break;
                case 37: currentSpeaker = player.getName(); showNPC = false; break;
                case 38: setBackgroundImage("nation1_bg6.png"); currentSpeaker = ""; break;
                case 39: currentSpeaker = player.getName(); break;
                case 40: setBackgroundImage("nation1_bg6.1.png"); currentSpeaker = ""; break;
                case 42: setBackgroundImage("nation1_bg6.png"); currentSpeaker = player.getName(); break;
                case 45: currentSpeaker =""; break;
                case 48: setBackgroundImage("nation1_bg8.2.png"); showPlayer = false; break;
                case 49: setBackgroundImage("nation1_bg7.png"); showPlayer = true; break;
                case 50: setBackgroundImage("nation1_bg8.png"); break;
                case 51: updateScene("Archivist", true); currentSpeaker = "Archivist"; showPlayer = true; break;
                case 52: currentSpeaker = player.getName(); break;
                case 53: currentSpeaker = "Archivist"; break;
                case 54: currentSpeaker = player.getName(); break;
                case 55: currentSpeaker = "Archivist"; break;
                case 56: setBackgroundImage("nation1_storyline1.png"); showNPC = false; showPlayer = false; break;
                case 57: setBackgroundImage("nation1_bg8.png"); currentSpeaker = player.getName(); showPlayer = true; showNPC = true; break;
                case 58: currentSpeaker = "Archivist"; break;
                case 59: game.showScreen("battle"); showPlayer = true; showNPC = true; break;
                case 60: currentSpeaker = "Archivist"; break;
                case 61: currentSpeaker = ""; break; 
                case 62: currentSpeaker = player.getName(); break;
                case 63: currentSpeaker = ""; break;
                case 65: currentSpeaker = player.getName(); break;
                case 68: currentSpeaker = "Archivist"; break;
                case 69: currentSpeaker = player.getName(); break;
                case 70: showNPC=false; break;
            }
        }
        repaint();
    }

    private void updateScene(String speaker, boolean isNPC) {
        this.currentSpeaker = speaker;
        if (isNPC) {
            Character npcData = npcManager.get(speaker);
            if (npcData != null) {
                this.npcSprite = npcData.getIdleSprite();
                this.showNPC = true;
            }
        } else { this.showNPC = false; }
        this.showPlayer = true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (currentBackground != null && currentBackground.isLoaded()) {
            g2.drawImage(currentBackground.getCurrentFrame(), 0, 0, getWidth(), getHeight(), null);
        }

        if (player == null) return;
        String heroName = player.getName();

        if (showPlayer && playerSprite != null) {
            drawCharacter(g2, playerSprite, 50, getHeight() - 550, 450, 450, currentSpeaker.equals(heroName));
        }
        if (showNPC && npcSprite != null) {
            boolean npcIsTalking = !currentSpeaker.equals(heroName) && !currentSpeaker.trim().isEmpty();
            drawCharacter(g2, npcSprite, getWidth() - 500, getHeight() - 550, 450, 450, npcIsTalking);
        }

        if (gameState == PLAY_STATE) {
            drawDialogueBox(g2);
        } else if (gameState == INVENTORY_STATE || gameState == SUB_MENU_STATE) {
            MenuRenderer.drawInventory(g2, player, slotCol, slotRow, scrollOffset);
            drawDialogueBox(g2);
            if (gameState == SUB_MENU_STATE) {
                MenuRenderer.drawSubMenu(g2, subMenuCursor); 
            }
        } else if (gameState == SHOP_STATE) {
            MenuRenderer.drawShop(g2, (int)player.getGold(), gameShop.getStock(), slotCol, slotRow);
            drawDialogueBox(g2);
        }
        if (gameState == OPTIONS_STATE) {
            MenuRenderer.drawOptionsOverlay(g2, optionsCursor);
        }
    }

    private void drawCharacter(Graphics2D g2, Sprite s, int x, int y, int w, int h, boolean isSpeaking) {
        BufferedImage frame = s.getCurrentFrame();
        if (frame != null) {
            float alpha = isSpeaking ? 1.0f : 0.5f; 
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2.drawImage(frame, x, y, w, h, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f)); 
        }
    }

    private void drawDialogueBox(Graphics2D g2) {
        int boxX = 50, boxY = getHeight() - 220, boxW = getWidth() - 100, boxH = 180;
        g2.setColor(new Color(181, 153, 110, 255)); 
        g2.fillRoundRect(boxX, boxY, boxW, boxH, 20, 20);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(boxX, boxY, boxW, boxH, 20, 20);

        if (!currentSpeaker.trim().isEmpty()) { 
            g2.setColor(new Color(111, 78, 55, 255)); 
            g2.fillRect(boxX + 20, boxY - 30, 200, 40);
            g2.setColor(Color.BLACK);
            g2.drawRect(boxX + 20, boxY - 30, 200, 40);
            g2.setFont(new Font("Serif", Font.BOLD, 22));
            g2.drawString(currentSpeaker, boxX + 40, boxY - 2);
        }
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Monospaced", Font.PLAIN, 20));
        if (lineIndex < dialogueText.length) {
            drawWrappedText(g2, dialogueText[lineIndex], boxX + 40, boxY + 50, boxW - 80);
        }
    }

    private void handleGridMovement(int keyCode) {
        ArrayList<Item> currentList = (gameState == INVENTORY_STATE) ? player.getInventory() : gameShop.getStock();
        int cols = 5;
        if (keyCode == KeyEvent.VK_W && slotRow > 0) slotRow--;
        else if (keyCode == KeyEvent.VK_S && (slotRow + 1) * cols < currentList.size()) {
            slotRow++;
            if (slotRow >= scrollOffset + 3) scrollOffset++;
        }
        else if (keyCode == KeyEvent.VK_A && slotCol > 0) slotCol--;
        else if (keyCode == KeyEvent.VK_D && slotCol < cols - 1) slotCol++;

        if (keyCode == KeyEvent.VK_ENTER && !currentList.isEmpty()) {
            if (gameState == SHOP_STATE) {
                String result = gameShop.buyItem(slotCol + (slotRow * cols), player);
                dialogueText[lineIndex] = result;
            } else gameState = SUB_MENU_STATE;
        } else updateHoverDescription();
    }

    private void updateHoverDescription() {
        ArrayList<Item> list = (gameState == INVENTORY_STATE) ? player.getInventory() : gameShop.getStock();
        int cols = (gameState == SHOP_STATE) ? 5 : 3;
        int index = slotCol + (slotRow * cols);
        
        if (index >= 0 && index < list.size()) {
            Item item = list.get(index);
            String info = item.getName() + ": " + item.getDescription();
            if (gameState == SHOP_STATE) info += " | Price: " + item.getPrice() + "G";
            dialogueText[lineIndex] = info;
        }
    }

    private void handleSubMenuInput(int code) {
        if (code == KeyEvent.VK_W && subMenuCursor > 0) subMenuCursor--;
        if (code == KeyEvent.VK_S && subMenuCursor < 2) subMenuCursor++;
        if (code == KeyEvent.VK_ENTER) executeAction();
        if (code == KeyEvent.VK_ESCAPE) gameState = INVENTORY_STATE;
    }

    private void executeAction() {
        int index = slotCol + (slotRow * 2);
        ArrayList<Item> inv = player.getInventory();
        if (index >= inv.size()) return;
        Item selected = inv.get(index);

        if (subMenuCursor == 0) { 
            dialogueText[lineIndex] = InventoryManager.useItem(selected, player);
            if (selected.isConsumable()) inv.remove(index);
        } else if (subMenuCursor == 1) {
            dialogueText[lineIndex] = InventoryManager.sellItem(selected, player);
            if (!selected.getName().equals("Father")) inv.remove(index);
        } else if (subMenuCursor == 2) { 
            inv.remove(index);
            dialogueText[lineIndex] = "Discarded " + selected.getName();
        }
        gameState = INVENTORY_STATE;
        repaint();
    }

    private void resetCursor() { slotCol = 0; slotRow = 0; subMenuCursor = 0; scrollOffset = 0; }

    private void setBackgroundImage(String file) { this.currentBackground = backgroundRegistry.get(file); repaint(); }

    private void loadBG(String name, int frameWidth, int frameHeight, int frameCount) {
        try {
            String path = "/EchoesOfTheOath/Resources/" + name;
            Sprite bgSprite = new Sprite(path, frameWidth, frameHeight, frameCount);

            if(name.equals("nation1_bg8.2.png")) {
                bgSprite.setLooping(false);
            }
            
            if (bgSprite.isLoaded()) {
                backgroundRegistry.put(name, bgSprite);
            } else {
                System.out.println("Failed to load Sprite: " + name);
            }
        } catch (Exception e) { 
            System.out.println("Error pre-loading: " + name); 
        }
    }

    private void drawWrappedText(Graphics2D g2, String line, int x, int y, int maxWidth) {
        g2.setFont(new Font("Monospaced", Font.BOLD, 22));
        FontMetrics fm = g2.getFontMetrics();
        String[] words = line.split(" ");
        StringBuilder currentLine = new StringBuilder();
        int lineHeight = fm.getHeight();
        int drawY = y;

        for (String word : words) {
            if (fm.stringWidth(currentLine.toString() + word) < maxWidth) {
                currentLine.append(word).append(" ");
            } else {
                g2.drawString(currentLine.toString(), x, drawY);
                drawY += lineHeight;
                currentLine = new StringBuilder(word).append(" ");
            }
        }
        g2.drawString(currentLine.toString(), x, drawY);
    }
}
