package EchoesOfTheOath.UI;

import EchoesOfTheOath.Characters.*;
import EchoesOfTheOath.Characters.Character;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class StoryPanel extends JPanel {
    private int scrollOffset = 0;
    private final int MAX_VISIBLE_ROWS = 4;
    private GameWindow game;
    private Image background;
    private Sprite characterSprite;
    private int currentMap = 1;
    private String dialogue = "Use Enter to progress, I to open inventory, P to visit the shop.";
    private String[] nation1Dialogues = {
        "Humanas. From a distance, the walls look strong. Up close, they're just holding up a graveyard.",
        "Gates are broken. Guards look half-starved. This isn't a kingdom; it's a cage.",
        "Whatever you're looking for, traveler, we don't have it. Food's gone. Gold's gone. Move on.",
        "Where is everyone? The streets are dead.",
        "Hiding. Collectors are making their rounds. If you want to keep that gear, stay out of the light. They take everything to the castle for the ‘King’s blessing.’",
        "The King's blessing. In a place this miserable, that sounds like a threat.",
        "Beautiful, isn't it? A golden boy for a leaden kingdom",
        "Why is a golden child standing in the middle of a slum?",
        "That's King Bartholomew. Or at least, the version they want us to see. Every coin stolen from these streets goes into that castle to keep him 'happy.'",
        "A child king who never grows up... and steals from his own people. I need to see him for myself.",
        "The Chapel is just a counting house now. But it's the only path that leads to the castle.",
        "You aren't on the guest list, traveler.",
        "I'm not here for a party. I'm here to talk to the King.",
        "The King doesn't 'talk.' He decides. And according to the records, your life is currently in arrears.",
        "The decree is signed. Clear the debt.",
        "You've caused a lot of damage upstairs. My ledger hasn't been this messy in centuries.",
        "The King is a puppet. You've been using a child to bleed this city dry.",
        "I don't use anyone. I record. If a King chooses to let his fear dictate the law, I simply write it down.",
        "The records demand completion! You cannot run from a debt you've already signed!"
    };
    private int nation1Index = 0;

    // GAME STATES
    private final int PLAY_STATE = 0;
    private final int INVENTORY_STATE = 1;
    private final int SHOP_STATE = 2;
    private final int SUB_MENU_STATE = 3;
    private int gameState = PLAY_STATE;

    // CURSOR LOGIC
    private int slotCol = 0;
    private int slotRow = 0;
    private int subMenuCursor = 0; 

    private Shop gameShop = new Shop();

    public StoryPanel(GameWindow game) {
        this.game = game;
        this.setFocusable(true);
        this.requestFocusInWindow();
        setBackground(Color.darkGray);

        loadSelectedCharacter();

        Timer animationTimer = new Timer(150, e -> {
            if (characterSprite != null) {
                characterSprite.update();
                repaint();
            }
        });
        animationTimer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleInput(e.getKeyCode());
            }
        });
    }

    void loadSelectedCharacter() {
        Character chosen = game.getChosenCharacter();
        if (chosen instanceof Warrior) {
            characterSprite = new Sprite("/EchoesOfTheOath/Resources/Warrior.png", 523, 477, 1);
        } else if (chosen instanceof Archer) {
            characterSprite = new Sprite("/EchoesOfTheOath/Resources/Archer.png", 515, 484, 1);
        } else if (chosen instanceof Mage) {
            characterSprite = new Sprite("/EchoesOfTheOath/Resources/Mage.png", 500, 500, 1);
        }
        updateBackground();
    }

    private void updateBackground() {
        try {
            String path = "/EchoesOfTheOath/Resources/" + currentMap + ".jpg";
            URL url = getClass().getResource(path);
            if (url != null) background = ImageIO.read(url);
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void handleInput(int keyCode) {
        this.requestFocusInWindow();

        this.requestFocusInWindow();
    
    // Only progress story if we are in the normal PLAY_STATE
        if (gameState == PLAY_STATE && keyCode == KeyEvent.VK_ENTER) {
            nation1Index++;

            if (nation1Index < nation1Dialogues.length) {
                dialogue = nation1Dialogues[nation1Index]; // Show next line
            } else {
                // ALL TEXT FINISHED: Switch to Battle
                dialogue = "Transitioning to battle...";
                
                // Add a small delay for a smoother transition
                Timer transitionDelay = new Timer(1000, e -> {
                    game.showScreen("battle"); // Ensure "battle" is registered in GameWindow
                });
                transitionDelay.setRepeats(false);
                transitionDelay.start();
            }
        }

        if (keyCode == KeyEvent.VK_I) {
            // Toggle Inventory
            gameState = (gameState == INVENTORY_STATE) ? PLAY_STATE : INVENTORY_STATE;
            resetCursor();
        } else if (keyCode == KeyEvent.VK_P) {
            // Toggle Shop
            gameState = (gameState == SHOP_STATE) ? PLAY_STATE : SHOP_STATE;
            resetCursor();
        }

        if (gameState == SUB_MENU_STATE) {
            handleSubMenuInput(keyCode);    
        } else if (gameState == INVENTORY_STATE || gameState == SHOP_STATE) {
            handleGridMovement(keyCode);    
        } else {
            handleMapMovement(keyCode);         
        }
        repaint();      
    }

    private void handleMapMovement(int keyCode) {
        if (keyCode == KeyEvent.VK_D) currentMap = Math.min(4, currentMap + 1);
        else if (keyCode == KeyEvent.VK_A) currentMap = Math.max(1, currentMap - 1);
        updateBackground();
    }

    private void handleGridMovement(int keyCode) {
        ArrayList<Item> currentList = (gameState == INVENTORY_STATE) 
                                    ? game.getChosenCharacter().inventory 
                                    : gameShop.getStock();
        
        int cols = (gameState == SHOP_STATE) ? 5 : 2; 
        
        int nextCol = slotCol;
        int nextRow = slotRow;

        // 2. Handle Directional Input using the 'cols' variable
        if (keyCode == KeyEvent.VK_W && slotRow > 0) nextRow--;
        else if (keyCode == KeyEvent.VK_S) nextRow++; 
        else if (keyCode == KeyEvent.VK_A && slotCol > 0) nextCol--;
        else if (keyCode == KeyEvent.VK_D && slotCol < (cols - 1)) nextCol++;

        int targetIndex = nextCol + (nextRow * cols);

        // 3. Validation and Scrolling
        if (targetIndex < currentList.size()) {
            slotCol = nextCol;
            slotRow = nextRow;
            
            // Only inventory usually needs scrolling
            if (gameState == INVENTORY_STATE) {
                if (slotRow < scrollOffset) {
                    scrollOffset = slotRow;
                } else if (slotRow >= scrollOffset + MAX_VISIBLE_ROWS) {
                    scrollOffset = slotRow - MAX_VISIBLE_ROWS + 1;
                }
            }

            Item selected = currentList.get(targetIndex);
            dialogue = (gameState == SHOP_STATE) 
                    ? selected.description + " | " + selected.price + " Gold" 
                    : selected.description;
        }

        // 4. Action Input (Buying or Sub-menu)
        if (keyCode == KeyEvent.VK_ENTER) {
            int finalIndex = slotCol + (slotRow * cols); // Use 'cols' here too!
            
            if (gameState == SHOP_STATE) {
                dialogue = gameShop.buyItem(finalIndex, game.getChosenCharacter());
            } else if (gameState == INVENTORY_STATE && !currentList.isEmpty()) {
                gameState = SUB_MENU_STATE;
                subMenuCursor = 0;
            }
        }
    }

    private void handleSubMenuInput(int keyCode) {
        if (keyCode == KeyEvent.VK_W && subMenuCursor > 0) subMenuCursor--;
        else if (keyCode == KeyEvent.VK_S && subMenuCursor < 2) subMenuCursor++;
        
        if (keyCode == KeyEvent.VK_ESCAPE) gameState = INVENTORY_STATE;
        
        if (keyCode == KeyEvent.VK_ENTER) {
            executeAction();
        }
    }

    private void executeAction() {
        Character player = game.getChosenCharacter();
        ArrayList<Item> inv = player.inventory;
        int index = slotCol + (slotRow * 2);
        
        if (index >= inv.size()) return; // Safety check
        Item selected = inv.get(index);

        if (subMenuCursor == 0) { // Interact/Consume
            dialogue = selected.name + ": " + selected.getRandomLine();
            
            // Health Logic using setters
            if (selected.name.equals("Wine") || selected.name.equals("Milk")) player.setHp(player.getHp() - 20);
            else if (selected.name.equals("Potion")) player.setHp(player.getHp() + 50);
            else if (selected.name.equals("Bread")) player.setHp(player.getHp() + 30);
            else if (selected.name.equals("Shrimp")) player.setHp(player.getHp() + 20);
            else if (selected.name.equals("Cinnamon")) player.setHp(player.getHp() + 25);
            else if (selected.name.equals("Latte")) player.setHp(player.getHp() + 45);
            else if (selected.name.equals("Pudding")) player.setHp(player.getHp() + 20);
            else if (selected.name.equals("Matcha")) player.addSkill1Bonus(20);
            else if (selected.name.equals("Clock")) dialogue = String.format("Clock: It's %d:%02d %s.", (int)(Math.random()*12)+1, (int)(Math.random()*60), (Math.random()>0.5?"AM":"PM"));

            if (selected.isConsumable) inv.remove(index);
        } 
        else if (subMenuCursor == 1) { // Sell
            if (selected.name.equals("Father")) dialogue = "Selling your Father is prohibited.";
            else { 
                player.setGold(player.getGold() + (selected.price / 2)); 
                inv.remove(index); 
                dialogue = "Sold " + selected.name + " for " + (selected.price/2) + "G.";
            }
        } 
        else if (subMenuCursor == 2) { // Delete
            inv.remove(index);
            dialogue = "Discarded " + selected.name;
        }
        
        gameState = INVENTORY_STATE;
        ensureCursorSafety(inv);
    }

    private void ensureCursorSafety(ArrayList<Item> inv) {
        if (inv.isEmpty()) {
            slotCol = 0; slotRow = 0;
        } else {
            int lastIndex = inv.size() - 1;
            if ((slotCol + (slotRow * 2)) >= inv.size()) {
                slotCol = lastIndex % 2;
                slotRow = lastIndex / 2;
            }
        }
    }

    private void resetCursor() { slotCol = 0; slotRow = 0; subMenuCursor = 0; }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // 1. Draw Background & Character (Always visible)
        if (background != null) g2.drawImage(background, 0, 0, 600, 500, null);
        
        g2.setColor(new Color(25, 25, 25)); // Sidebar
        g2.fillRect(600, 0, 480, 500);

        if (characterSprite != null) {
            BufferedImage frame = characterSprite.getCurrentFrame();
            if (frame != null) g2.drawImage(frame, 740, 150, 200, 200, null);
        }

        // 2. THE FIX: Drawing the Menus
        if (gameState == INVENTORY_STATE || gameState == SUB_MENU_STATE) {
            // This triggers your new 2-window logic
            drawGridWindow(g2, "INVENTORY", game.getChosenCharacter().inventory);
        } else if (gameState == SHOP_STATE) {
            // This triggers your new solid Shop logic
            drawGridWindow(g2, "SHOP", gameShop.getStock());
        }

        // 3. Sub-menu and Dialogue (Always on top)
        if (gameState == SUB_MENU_STATE) drawSubMenu(g2);

        g2.setColor(new Color(30, 30, 30)); // Dialogue Box
        g2.fillRect(0, 500, 1080, 220);
        g2.setColor(Color.WHITE);
        drawWrappedText(g2, dialogue, 50, 545, 980);
    }

    private void drawGridWindow(Graphics2D g2, String title, ArrayList<Item> list) {
        Character player = game.getChosenCharacter();
        // SAFETY: If character isn't loaded yet, don't crash
        if (player == null) return;

        if (gameState == SHOP_STATE) {
            // --- SHOP: FULL SOLID WINDOW ---
            int x = 50, y = 50, w = 500, h = 400;
            g2.setColor(new Color(20, 20, 20)); 
            g2.fillRoundRect(x, y, w, h, 20, 20);
            g2.setColor(Color.WHITE);
            g2.drawRoundRect(x, y, w, h, 20, 20);
            g2.drawString("SHOP | Gold: " + player.getGold(), x + 20, y + 30);

            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    int slotX = x + 20 + (i % 5 * 85);
                    int slotY = y + 50 + (i / 5 * 85);
                    g2.drawImage(list.get(i).image, slotX, slotY, 64, 64, null);
                    if (slotCol == (i % 5) && slotRow == (i / 5)) {
                        g2.setColor(Color.YELLOW);
                        g2.drawRect(slotX, slotY, 64, 64);
                    }
                }
            }
        } else {
            // --- INVENTORY: SPLIT SOLID WINDOWS ---
            // LEFT WINDOW: STATS
            int sx = 50, sy = 50, sw = 240, sh = 400;
            g2.setColor(new Color(30, 30, 30)); 
            g2.fillRoundRect(sx, sy, sw, sh, 20, 20);
            g2.setColor(Color.WHITE);
            g2.drawRoundRect(sx, sy, sw, sh, 20, 20);
            
            g2.drawString("NAME: " + player.getName(), sx + 20, sy + 40);
            g2.drawString("CLASS:  " + player.getClassType(), sx + 20, sy + 60);
            g2.drawString("LVL:  " + player.getLevel(), sx + 20, sy + 80);
            drawSidebarHPBar(g2, sx + 20, sy + 110, player);
            
            g2.drawString("SKILLS:", sx + 20, sy + 180);
            String skillName = player.getSkillName(1);
            if (skillName != null) g2.drawString(skillName + " [" + player.getSkillDamageRange(1) + "]", sx + 20, sy + 200);
            skillName = player.getSkillName(2);
            if (skillName != null) g2.drawString(skillName + " [" + player.getSkillDamageRange(2) + "]", sx + 20, sy + 220);
            skillName = player.getSkillName(3);
            if (skillName != null) g2.drawString(skillName + " [" + player.getSkillDamageRange(3) + "]", sx + 20, sy + 240);

            // RIGHT WINDOW: ITEMS
            int ix = 300, iy = 50, iw = 250, ih = 400;
            g2.setColor(new Color(20, 20, 20)); 
            g2.fillRoundRect(ix, iy, iw, ih, 20, 20);
            g2.setColor(Color.WHITE);
            g2.drawRoundRect(ix, iy, iw, ih, 20, 20);

            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    int row = i / 2;
                    int col = i % 2;
                    if (row >= scrollOffset && row < scrollOffset + 4) {
                        int dx = ix + 30 + (col * 90);
                        int dy = iy + 30 + ((row - scrollOffset) * 90);
                        g2.drawImage(list.get(i).image, dx, dy, 64, 64, null);
                        if (slotCol == col && slotRow == row) {
                            g2.setColor(Color.YELLOW);
                            g2.drawRect(dx, dy, 64, 64);
                        }
                    }
                }
            }
        }
    }

    private void drawSidebarHPBar(Graphics2D g2, int x, int y, Character p) {
        int barMaxWidth = 180; // Keep it smaller than the 240px window
        int barHeight = 15;
        double maxHP = p.getMaxHp(); 
        double percent = Math.min(p.getHp() / maxHP, 1.0);

        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(x, y, barMaxWidth, barHeight);
        
        g2.setColor(percent > 0.3 ? Color.GREEN : Color.RED);
        g2.fillRect(x, y, (int)(barMaxWidth * percent), barHeight);
        
        g2.setColor(Color.WHITE);
        g2.drawRect(x, y, barMaxWidth, barHeight);
        g2.setFont(new Font("Monospaced", Font.PLAIN, 12));
        g2.drawString(p.getHp() + "/" + (int)maxHP, x, y + 30);
    }

    private void drawSubMenu(Graphics2D g2) {
        int x = 520, y = 150, w = 160, h = 115;
        g2.setColor(new Color(0, 0, 0, 245));
        g2.fillRoundRect(x, y, w, h, 15, 15);
        g2.setColor(Color.WHITE);
        g2.drawRoundRect(x + 5, y + 5, w - 10, h - 10, 10, 10);
        g2.drawString("Interact", x + 40, y + 35);
        g2.drawString("Sell", x + 40, y + 65);
        g2.drawString("Delete", x + 40, y + 95);
        
        g2.setColor(Color.YELLOW);
        g2.drawString(">", x + 20, y + 35 + (subMenuCursor * 30));
    }

    private void drawWrappedText(Graphics2D g2, String text, int x, int y, int maxWidth) {
        g2.setFont(new Font("Monospaced", Font.BOLD, 22));
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
                // Draw current line and start a new one
                g2.drawString(currentLine.toString(), x, drawY);
                drawY += lineHeight;
                currentLine = new StringBuilder(word).append(" ");
            }
        }
        // Draw the very last line
        g2.drawString(currentLine.toString(), x, drawY);
    }
}