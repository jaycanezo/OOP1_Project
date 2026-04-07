package EchoesOfTheOath.UI;

import EchoesOfTheOath.Characters.*;
import EchoesOfTheOath.Characters.Character;
import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;

public class BattlePanel extends JPanel {
    
    private GameWindow game;
    private Character player;
    private Character enemy;

    // UI Components
    private JProgressBar playerHpBar, enemyHpBar;
    private JTextArea logArea;
    private JPanel buttonPanel;
    private BattleSidePanel playerSide, enemySide;
    private Timer animationTimer;

    private final int BATTLE_STATE = 0;
    private final int INVENTORY_STATE = 1;
    private int currentBattleState = BATTLE_STATE;
    private int slotCol = 0, slotRow = 0;

    public BattlePanel(GameWindow game) {
        this.game = game;
        this.enemy = new babyM(); 
        this.player = game.getChosenCharacter(); 

        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());

        this.setFocusable(true); 
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();

                if (code == KeyEvent.VK_I) {
                    currentBattleState = (currentBattleState == INVENTORY_STATE) ? BATTLE_STATE : INVENTORY_STATE;
                    BattlePanel.this.requestFocusInWindow(); // Ensure focus remains
                    repaint();
                    return;
                }

                if (currentBattleState == INVENTORY_STATE) {
                    // Use the list size to prevent moving to empty slots
                    int maxIndex = player.inventory.size() - 1;

                    if (code == KeyEvent.VK_W && slotRow > 0) {
                        slotRow--;
                    } else if (code == KeyEvent.VK_S) {
                        // Only move down if there's an item in the next row
                        if ((slotRow + 1) * 2 + slotCol <= maxIndex) slotRow++;
                    } else if (code == KeyEvent.VK_A && slotCol > 0) {
                        slotCol--;
                    } else if (code == KeyEvent.VK_D && slotCol < 1) {
                        // Only move right if there's an item there
                        if (slotRow * 2 + 1 <= maxIndex) slotCol++;
                    }

                    if (code == KeyEvent.VK_ENTER) {
                        int index = slotCol + (slotRow * 2);
                        if (index < player.inventory.size()) {
                            useItemInBattle(player.inventory.get(index), index);
                            currentBattleState = BATTLE_STATE;
                        }
                    }
                    repaint(); // CRITICAL: This updates the yellow box position
                }
            }
        });

        setupVisuals();
        setupUI();
        startAnimation();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
    }

    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g); 
        
        // ADD THIS CHECK: Only draw if player and their inventory actually exist
        if (currentBattleState == INVENTORY_STATE && player != null && player.inventory != null) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(0, 0, 0, 150));
            g2.fillRect(0, 0, getWidth(), getHeight());
            
            drawGridWindow(g2, "BATTLE INVENTORY", player.inventory);
        }
    }

    private void useItemInBattle(Item item, int index) {
        disableButtons(true);
    
        if (item.name.equals("Potion")) player.setHp(player.getHp() + 50);
        else if (item.name.equals("Bread")) player.setHp(player.getHp() + 30);
        else if (item.name.equals("Latte")) player.setHp(player.getHp() + 45);
        else if (item.name.equals("Matcha")) player.addSkill1Bonus(20);
        else if (item.name.equals("Bread")) player.setHp(player.getHp() + 30);
        else if (item.name.equals("Wine") || item.name.equals("Milk")) player.setHp(player.getHp() - 20);

        String message = player.getName() + " used " + item.name + "!\n" + item.getRandomLine();
        logArea.setText(message);

        // Remove if it's a consumable
        if (item.isConsumable) {
            player.inventory.remove(index);
        }

        refreshStatus();    
        disableButtons(false);
    }

    private void setupVisuals() {
        // Split screen for sprites like the Intro
        JPanel splitScreen = new JPanel(new GridLayout(1, 2));
        splitScreen.setOpaque(false);

        playerSide = new BattleSidePanel(player, 50, 50, 400, 400);
        enemySide = new BattleSidePanel(enemy, 50, 50, 400, 400);

        splitScreen.add(playerSide);
        splitScreen.add(enemySide);
        add(splitScreen, BorderLayout.CENTER);
    }

    private void setupUI() {
        // --- TOP: HP Progress Bars ---
        JPanel topPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        playerHpBar = createHPBar(player);
        enemyHpBar = createHPBar(enemy);

        String playerName = (player != null) ? player.getName() : "Hero"; 
        topPanel.add(createLabeledBar(playerName, playerHpBar));
        topPanel.add(createLabeledBar(enemy.getName(), enemyHpBar));
        add(topPanel, BorderLayout.NORTH);

        // --- BOTTOM: Controls & Logs ---
        JPanel bottomContainer = new JPanel(new BorderLayout());
        bottomContainer.setPreferredSize(new Dimension(1080, 200));
        bottomContainer.setBackground(new Color(20, 20, 20));

        // 1. STYLE THE LOG AREA (Like the Trial)
        logArea = new JTextArea("The battle begins! Your turn.");
        logArea.setFont(new Font("Monospaced", Font.BOLD, 18));
        logArea.setForeground(Color.WHITE);
        logArea.setBackground(Color.BLACK);
        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        logArea.setMargin(new Insets(10, 10, 10, 10));

        // 2. ADD TO SCROLLPANE (So text doesn't disappear if it's too long)
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        bottomContainer.add(scrollPane, BorderLayout.CENTER); // <--- ADDED THIS

        buttonPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        buttonPanel.setOpaque(false);
        
        addButton("Skill 1", 1);
        addButton("Skill 2", 2);
        addButton("Ultimate", 3);
        
        bottomContainer.add(buttonPanel, BorderLayout.EAST);
        
        // 3. ATTACH TO PANEL
        add(bottomContainer, BorderLayout.SOUTH); // <--- ADDED THIS
    }

    private void addButton(String text, int skillNum) {
        JButton btn = new JButton(text);
        btn.setFocusable(false);
        
        btn.addActionListener(e -> {
            playerTurn(skillNum);
            BattlePanel.this.requestFocusInWindow();
        });
        buttonPanel.add(btn);
    }

    private JProgressBar createHPBar(Character c) {
        // Default values if character isn't loaded yet
        int max = (c != null) ? c.getMaxHp() : 100; 
        int current = (c != null) ? c.getHp() : 100;

        JProgressBar bar = new JProgressBar(0, max);
        bar.setValue(current);
        bar.setForeground(Color.GREEN);
        bar.setBackground(Color.RED);
        bar.setStringPainted(true);
        return bar;
    }

    private JPanel createLabeledBar(String name, JProgressBar bar) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        JLabel lbl = new JLabel(name);
        lbl.setForeground(Color.WHITE);
        p.add(lbl, BorderLayout.NORTH);
        p.add(bar, BorderLayout.CENTER);
        return p;
    }

    private void startAnimation() {
        animationTimer = new Timer(50, e -> {
            if (player != null) player.updateAnimations();
            if (enemy != null) enemy.updateAnimations();

            if (playerSide != null) playerSide.updateEffects(); 
            if (enemySide != null) enemySide.updateEffects();
            
            repaint();
        });
        animationTimer.start();
    }

    private void playerTurn(int skillNum) {
        if (!player.isSkillAvailable(skillNum)) {
            logArea.setText("Skill is on cooldown!");
            return;
        }

        disableButtons(true);
        
        // 1. Play Player Effect on Enemy
        Sprite[] effects = player.getSkillSprite(skillNum);
        if (effects != null) {
            for (Sprite s : effects) enemySide.playEffect(s, 50, 50, 400, 400);
        }

        // 2. Battle Logic
        String result = player.useSkill(skillNum, enemy);
        logArea.setText(result);
        refreshStatus();

        if (checkGameOver()) return;

        // 3. Delay before Enemy Turn
        Timer delay = new Timer(1500, e -> enemyTurn());
        delay.setRepeats(false);
        delay.start();
    }

    private void enemyTurn() {
        int randomSkill = (int)(Math.random() * 3) + 1;
        try {
            Sprite[] effects = enemy.getSkillSprite(randomSkill);
            if (effects != null) {
                for (Sprite s : effects) playerSide.playEffect(s, 50, 50, 400, 400);
            }
            String result = enemy.useSkill(randomSkill, player);
            logArea.append("\n" + result);
            refreshStatus();
            if (checkGameOver()) return;
        } catch (Exception e) {
            System.err.println("Error during enemy turn: " + e.getMessage());
            e.printStackTrace();
        } finally {
            disableButtons(false); 
        }
    }

    private void refreshStatus() {
    // 1. Update Player Bar
        int currentHP = player.getHp();
        int maxHP = player.getMaxHp();
        playerHpBar.setMaximum(maxHP);
        playerHpBar.setValue(currentHP);
        playerHpBar.setString(currentHP + " / " + maxHP); // Shows text on the bar

        // 2. Update Enemy Bar
        int eCurrentHP = enemy.getHp();
        int eMaxHP = enemy.getMaxHp();
        enemyHpBar.setMaximum(eMaxHP);
        enemyHpBar.setValue(eCurrentHP);
        enemyHpBar.setString(eCurrentHP + " / " + eMaxHP);

        // 3. Dynamic Color Logic
        // Changes to Red if health is below 30%
        if (currentHP < (maxHP * 0.3)) {
            playerHpBar.setForeground(Color.ORANGE);
        } else {
            playerHpBar.setForeground(Color.GREEN);
        }
        playerHpBar.setBackground(new Color(60, 0, 0));
        if (eCurrentHP < (eMaxHP * 0.3)) {
            enemyHpBar.setForeground(Color.ORANGE);
        } else {
            enemyHpBar.setForeground(Color.GREEN);
        }
        enemyHpBar.setBackground(new Color(60, 0, 0));
        // Force the UI to refresh these specific components
        playerHpBar.repaint();
        enemyHpBar.repaint();
    }

    private boolean checkGameOver() {
        if (enemy.getHp() <= 0) {
            logArea.setText("Victory! The Infant King has been defeated.");
            animationTimer.stop();
            JOptionPane.showMessageDialog(this, "Victory!");
            game.showScreen("story"); // Return to story
            return true;
        }
        if (player.getHp() <= 0) {
            logArea.setText("You have fallen in battle...");
            JOptionPane.showMessageDialog(this, "Game Over");
            System.exit(0);
            return true;
        }
        return false;
    }

    private void disableButtons(boolean disable) {
        for (Component c : buttonPanel.getComponents()) {
            c.setEnabled(!disable);
        }
    }

    private class ActiveEffect {
        Sprite sprite;
        int x, y, w, h;
        public ActiveEffect(Sprite s, int x, int y, int w, int h) {
            this.sprite = s; this.x = x; this.y = y; this.w = w; this.h = h;
        }
    }

    private class BattleSidePanel extends JPanel {
        private Character owner;
        // Use a List to hold multiple effects at once
        private java.util.List<ActiveEffect> activeEffects = new java.util.ArrayList<>();
        private int x, y, w, h;

        public BattleSidePanel(Character owner, int x, int y, int w, int h) {
            this.owner = owner;
            this.x = x; this.y = y; this.w = w; this.h = h;
            this.setOpaque(false);
        }

        public void setOwner(Character owner) { 
            this.owner = owner; 
            repaint();
        }

        public void updateEffects() {
            for (ActiveEffect ae : activeEffects) {
                if (ae.sprite != null) {
                    ae.sprite.update(); // This moves the effect to the next frame
                }
            }
        }

        // Updated playEffect adds to the list instead of overwriting
        public void playEffect(Sprite effect, int ex, int ey, int ew, int eh) {
            ActiveEffect newEffect = new ActiveEffect(effect, ex, ey, ew, eh);
            activeEffects.add(newEffect);
            
            // Remove THIS specific effect after 600ms
            Timer t = new Timer(1200, e -> { 
                activeEffects.remove(newEffect); 
                repaint(); 
            });
            t.setRepeats(false);
            t.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 1. Draw Character
            if (owner != null && owner.getIdleSprite() != null) {
                Sprite s = owner.getIdleSprite();
                if (s.isLoaded()) {
                    g.drawImage(s.getCurrentFrame(), x, y, w, h, null);
                }
            }
            // 2. Draw ALL active effects in the list
            for (ActiveEffect ae : activeEffects) {
                if (ae.sprite != null && ae.sprite.isLoaded()) {
                    g.drawImage(ae.sprite.getCurrentFrame(), ae.x, ae.y, ae.w, ae.h, null);
                }
            }
        }
    }

    public void loadBattleData() {
        this.player = game.getChosenCharacter();
        if (player != null) {
            // Update HP Bar
            playerHpBar.setMaximum(player.getMaxHp());
            playerHpBar.setValue(player.getHp());
            playerHpBar.setString(player.getHp() + " / " + player.getMaxHp());

            // Update Sprite
            playerSide.setOwner(player);
            
            // Update Buttons with real skill names
            Component[] btns = buttonPanel.getComponents();
            if (btns.length >= 3) {
                ((JButton)btns[0]).setText(player.getSkillName(1));
                ((JButton)btns[1]).setText(player.getSkillName(2));
                ((JButton)btns[2]).setText(player.getSkillName(3));
            }
            
            logArea.setText("Challenge accepted, " + player.getName() + "!");
            repaint();
        }
    }

    private void drawGridWindow(Graphics2D g2, String title, java.util.ArrayList<Item> list) {
        // 1. Draw Background Box (Increased height to fit items better)
        int x = 200, y = 80, w = 680, h = 420; 
        g2.setColor(new Color(20, 20, 20, 250)); 
        g2.fillRoundRect(x, y, w, h, 20, 20);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x, y, w, h, 20, 20);
        g2.drawString(title + " | Use W/S to Scroll", x + 20, y + 30);

        // 2. SET CLIPPING: This prevents items from drawing outside the box
        Shape oldClip = g2.getClip();
        g2.setClip(new Rectangle(x + 10, y + 40, w - 20, h - 60));

        // 3. Draw Items with Scrolling Logic
        if (list != null) {
            // We only want to show about 4 rows at a time
            int maxVisibleRows = 4; 
            
            for (int i = 0; i < list.size(); i++) {
                int row = i / 2;
                int col = i % 2;
                
                // Only draw if the row is within the current scroll view
                if (row >= scrollOffset && row < scrollOffset + maxVisibleRows) {
                    int dx = x + 150 + (col * 200); // Spread out columns more
                    int dy = y + 60 + ((row - scrollOffset) * 90); // Adjusted for scroll
                    
                    g2.drawImage(list.get(i).image, dx, dy, 64, 64, null);
                    
                    // Draw selection cursor
                    if (slotCol == col && slotRow == row) {
                        g2.setColor(Color.YELLOW);
                        g2.setStroke(new BasicStroke(3));
                        g2.drawRect(dx, dy, 64, 64);
                    }
                }
            }
        }
        
        // Reset clip so other UI elements (like HP bars) can draw normally
        g2.setClip(oldClip);
    }
}
