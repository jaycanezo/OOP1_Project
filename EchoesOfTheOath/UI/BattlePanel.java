package EchoesOfTheOath.UI;

import EchoesOfTheOath.Characters.*;
import EchoesOfTheOath.Characters.Character;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;

public class BattlePanel extends JPanel {
    private GameWindow game;
    private Character player;
    private Character enemy;

    private JProgressBar playerHpBar, enemyHpBar;
    private JTextArea logArea;
    private JPanel buttonPanel;
    private JLabel playerNameLabel, enemyNameLabel;
    private Timer animationTimer;

    private final int BATTLE_STATE = 0;
    private final int INVENTORY_STATE = 1;
    private int currentBattleState = BATTLE_STATE;
    private int slotCol = 0, slotRow = 0;
    private int scrollOffset = 0;
    private boolean isPlayerTurn = true;
    private Sprite background; 
    private boolean battleOver = false;
    
    // NEW: Replaces BattleSidePanel for full-screen unclipped effects[cite: 11]
    private java.util.List<ActiveEffect> activeEffects = new java.util.ArrayList<>();
    
    // NEW: Variables to track hit overlays
    private int playerFlash = 0;
    private int enemyFlash = 0;

    private final String[] backgroundPaths = {
        "/EchoesOfTheOath/Resources/nation1_bg6.png",
        "/EchoesOfTheOath/Resources/nation1_bg8.png",
        "/EchoesOfTheOath/Resources/nation2_bg4.png",
        "/EchoesOfTheOath/Resources/nation2_bg9.png",
        "/EchoesOfTheOath/Resources/nation3_bg5.png", 
        "/EchoesOfTheOath/Resources/nation3_bg9.png"
    };

    public BattlePanel(GameWindow game) {
        this.game = game;
        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());
        this.setFocusable(true); 

        this.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyInput(e);
            }
        });

        setupUI();
        startAnimation();
    }

    public void loadBattleData() {
        if (animationTimer != null && animationTimer.isRunning()) {
            animationTimer.stop();
        }
        
        this.battleOver = false;
        this.player = game.getChosenCharacter();
        this.enemy = game.getCurrentBoss();
        this.isPlayerTurn = true;
        this.activeEffects.clear(); // Clear lingering effects
        this.playerFlash = 0;
        this.enemyFlash = 0;
        
        logArea.setText("The battle begins! " + enemy.getName() + " stands before you.");
        
        try {
            this.background = new Sprite(backgroundPaths[game.getBossIndex()], 1920, 1080, 1);
        } catch (Exception e) {
            this.background = null;
        }

        if (player != null && enemy != null) {
            playerNameLabel.setText(player.getName()); 
            enemyNameLabel.setText(enemy.getName());

            player.resetCooldowns();
            playerHpBar.setMaximum(player.getMaxHp());
            playerHpBar.setValue(player.getHp());
            playerHpBar.setString(player.getHp() + " / " + player.getMaxHp());
            playerHpBar.setForeground(Color.GREEN);

            enemyHpBar.setMaximum(enemy.getMaxHp());
            enemyHpBar.setValue(enemy.getHp());
            enemyHpBar.setString(enemy.getHp() + " / " + enemy.getMaxHp());
            enemyHpBar.setForeground(Color.GREEN);

            disableButtons(false); 
            refreshSkillButtons();
            startAnimation();
            repaint();
        }
    }

    private void playerTurn(int skillNum) {
        if (battleOver || !isPlayerTurn || !player.isSkillAvailable(skillNum)) {
            if (!battleOver && !player.isSkillAvailable(skillNum)) {
                logArea.setText(player.getSkillName(skillNum) + " is on cooldown!");
            }
            return;
        }

        isPlayerTurn = false;
        disableButtons(true); 
        
        // Play effect on enemy coordinates
        Sprite[] effects = player.getSkillSprite(skillNum);
        if (effects != null) {
            for (Sprite s : effects) 
                playEffect(s, getWidth() - 525, getHeight() - 600, 450, 450);
        }

        String result = player.useSkill(skillNum, enemy);
        enemyFlash = 10; // Trigger red overlay on enemy (10 frames = 500ms)
        logArea.setText(result);
        refreshStatus();

        if (checkGameOver()) 
            return;

        Timer delay = new Timer(2000, e -> enemyTurn());
        delay.setRepeats(false);
        delay.start();
    }

    private void enemyTurn() {
        if (battleOver) 
            return;

        int randomSkill = (int)(Math.random() * 3) + 1;
        logArea.append("\n" + enemy.getName() + " prepares to strike!");

        // Play effect on player coordinates
        Sprite[] effects = enemy.getSkillSprite(randomSkill);
        if (effects != null) {
            for (Sprite s : effects) 
                playEffect(s, 75, getHeight() - 600, 450, 450);
        }

        // FIX: Trigger the flash immediately when the attack effect starts!
        playerFlash = 10; 

        Timer actionDelay = new Timer(1500, e -> {
            if (battleOver) 
                return;

            String result = enemy.useSkill(randomSkill, player);
            logArea.append("\n" + result);
            
            player.reduceCooldowns(); 
            refreshStatus();

            if (!checkGameOver()) {
                isPlayerTurn = true;
                disableButtons(false); 
                this.requestFocusInWindow();
            }
        });

        actionDelay.setRepeats(false);
        actionDelay.start();
    }

    private boolean checkGameOver() {
        if (battleOver) 
            return true;

        if (enemy.getHp() <= 0) {
            battleOver = true;
            logArea.setText("Victory! " + enemy.getName() + " falls.");
            // REMOVED: animationTimer.stop() - so effects keep playing

            player.setLevel(player.getLevel() + 1); 
            player.setGold(player.getGold() + 500); 

            game.advanceStoryProgress(); 
            player.setHp(player.getMaxHp());
            player.resetCooldowns();
            game.autosave();

            Timer delay = new Timer(1500, e -> {
                animationTimer.stop(); // Stop right before capture[cite: 11]
                java.awt.image.BufferedImage capture = new java.awt.image.BufferedImage(
                    getWidth(), getHeight(), java.awt.image.BufferedImage.TYPE_INT_ARGB);
                this.paint(capture.getGraphics());
                
                game.showResultScreen(true, capture);
            });

            delay.setRepeats(false);
            delay.start();
            
            return true;
        }
        
        if (player.getHp() <= 0) {
            battleOver = true;
            logArea.setText("You have fallen...");
            // REMOVED: animationTimer.stop() - so effects keep playing
            
            Timer delay = new Timer(1500, e -> {
                animationTimer.stop(); // Stop right before capture[cite: 11]
                java.awt.image.BufferedImage capture = new java.awt.image.BufferedImage(getWidth(), getHeight(), java.awt.image.BufferedImage.TYPE_INT_ARGB);
                this.paint(capture.getGraphics());
                
                game.showResultScreen(false, capture);
            });

            delay.setRepeats(false);
            delay.start();
            
            return true;
        }
        
        return false;
    }

    private void handleKeyInput(KeyEvent e) {
        if (battleOver) 
            return;
        
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_I || (code == KeyEvent.VK_ESCAPE && currentBattleState == INVENTORY_STATE)) {
            currentBattleState = (currentBattleState == INVENTORY_STATE) ? BATTLE_STATE : INVENTORY_STATE;
            slotCol = 0; slotRow = 0; scrollOffset = 0; 
            repaint();
            return;
        }

        if (currentBattleState == INVENTORY_STATE) {
            ArrayList<Item> inv = player.getInventory();
            int cols = 5;

            if (code == KeyEvent.VK_W && slotRow > 0) slotRow--;
            else if (code == KeyEvent.VK_S && (slotRow + 1) * cols < inv.size()) slotRow++;
            else if (code == KeyEvent.VK_A && slotCol > 0) slotCol--;
            else if (code == KeyEvent.VK_D && slotCol < cols - 1) slotCol++;

            if (code == KeyEvent.VK_ENTER && !inv.isEmpty()) {
                int index = slotCol + (slotRow * cols);
                if (index < inv.size()) {
                    useItemInBattle(inv.get(index), index);
                    currentBattleState = BATTLE_STATE;
                }
            }
            repaint();
        }
    }

    // NEW: Method replaces BattleSidePanel for full-screen drawing
    public void playEffect(Sprite effect, int ex, int ey, int ew, int eh) {
        ActiveEffect newEffect = new ActiveEffect(effect, ex, ey, ew, eh);
        activeEffects.add(newEffect);

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
        Graphics2D g2 = (Graphics2D) g;

        if (background != null && background.isLoaded()) {
            g.drawImage(background.getCurrentFrame(), 0, 0, getWidth(), getHeight(), null);
        }

        // 1. Draw Idle Characters[cite: 11]
        if (player != null && player.getIdleSprite() != null && player.getIdleSprite().isLoaded()) {
            g.drawImage(player.getIdleSprite().getCurrentFrame(), 100, getHeight() - 580, 400, 400, null);
        }

        if (enemy != null && enemy.getIdleSprite() != null && enemy.getIdleSprite().isLoaded()) {
            g.drawImage(enemy.getIdleSprite().getCurrentFrame(), getWidth() - 500, getHeight() - 580, 400, 400, null);
        }

        // 2. Draw Full-Screen Red Overlay if ANY character is hit
        if (playerFlash > 0 || enemyFlash > 0) {
            // Using 100 opacity so the background is still slightly visible through the red tint
            g2.setColor(new Color(255, 0, 0, 100)); 
            g2.fillRect(0, 0, getWidth(), getHeight());
        }

        // 3. Draw Unclipped Effects OVER the red tint, but UNDER the UI[cite: 11]
        for (ActiveEffect ae : activeEffects) {
            if (ae.sprite != null && ae.sprite.isLoaded()) {
                g.drawImage(ae.sprite.getCurrentFrame(), ae.x, ae.y, ae.w, ae.h, null);
            }
        }
    }

    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g); 
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (currentBattleState == INVENTORY_STATE && player != null) {
            g2.setColor(new Color(0, 0, 0, 180)); 
            g2.fillRect(0, 0, getWidth(), getHeight());

            MenuRenderer.drawInventory(g2, player, slotCol, slotRow, scrollOffset);
        }
    }

    private void useItemInBattle(Item item, int index) {
        String flavorText = InventoryManager.useItem(item, player);
        logArea.setText(player.getName() + " used " + item.getName() + "!");
        logArea.append("\n" + flavorText);

        if (item.isConsumable()) { 
            player.getInventory().remove(index); 
        }

        refreshStatus();   

        if (checkGameOver()) 
            return;

        disableButtons(false);
        this.requestFocusInWindow(); 
    }

    private void setupUI() {
        playerHpBar = createHPBar();
        enemyHpBar = createHPBar();

        JPanel topPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        topPanel.add(createLabeledBar("PLAYER", playerHpBar, true));
        topPanel.add(createLabeledBar("ENEMY", enemyHpBar, false));
        
        add(topPanel, BorderLayout.NORTH);

        JPanel bottomContainer = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                g2.setColor(Color.BLACK); 
                g2.fillRoundRect(50, 10, getWidth()-100, getHeight()-20, 20, 20);
                
                g2.setColor(new Color(181, 153, 110));
                g2.setStroke(new BasicStroke(3)); 
                g2.drawRoundRect(50, 10, getWidth()-100, getHeight()-20, 20, 20);
            }
        };

        bottomContainer.setPreferredSize(new Dimension(1040, 250));
        bottomContainer.setOpaque(false);

        logArea = new JTextArea("The battle begins!");
        logArea.setOpaque(false);
        logArea.setEditable(false);
        logArea.setFocusable(false);
        logArea.setFont(new Font("Monospaced", Font.BOLD, 22));
        logArea.setForeground(Color.WHITE);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        logArea.setMargin(new Insets(15, 80, 10, 80));
    
        buttonPanel = new JPanel(new GridLayout(1, 3, 5, 0)); 
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 0, 50)); 

        addButton("Skill 1", 1);
        addButton("Skill 2", 2);
        addButton("Ultimate", 3);

        bottomContainer.add(buttonPanel, BorderLayout.NORTH);
        bottomContainer.add(logArea, BorderLayout.CENTER);
        
        add(bottomContainer, BorderLayout.SOUTH);
    }

    private void addButton(String text, int skillNum) {
        SkillButton btn = new SkillButton(text, skillNum); 
        btn.addActionListener(e -> {
            playerTurn(skillNum);
            this.requestFocusInWindow();
        });
        buttonPanel.add(btn);
    }

    private JProgressBar createHPBar() {
        JProgressBar bar = new JProgressBar(0, 100);
        bar.setForeground(Color.GREEN);
        bar.setBackground(new Color(60, 0, 0));
        bar.setStringPainted(true);
        return bar;
    }

    private javax.swing.JPanel createLabeledBar(String initialName, JProgressBar bar, boolean isPlayer) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        JLabel lbl = new JLabel(initialName);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Monospaced", Font.BOLD, 18));

        if (isPlayer) playerNameLabel = lbl;
        else enemyNameLabel = lbl;

        p.add(lbl, BorderLayout.NORTH);
        p.add(bar, BorderLayout.CENTER);
        return p;
    }

    private void startAnimation() {
        animationTimer = new Timer(50, e -> {
            if (player != null) player.updateAnimations();
            if (enemy != null) enemy.updateAnimations();
            
            // Advance active effect sprites
            for (ActiveEffect ae : activeEffects) {
                if (ae.sprite != null) ae.sprite.update();
            }

            // Reduce flash overlay times
            if (playerFlash > 0) playerFlash--;
            if (enemyFlash > 0) enemyFlash--;

            repaint();
        });
        animationTimer.start();
    }

    private void refreshStatus() {
        if (player == null || enemy == null) return;

        playerNameLabel.setText(player.getName()); 
        enemyNameLabel.setText(enemy.getName());
        
        playerHpBar.setMaximum(player.getMaxHp());
        playerHpBar.setValue(player.getHp());
        playerHpBar.setString(player.getHp() + " / " + player.getMaxHp());

        enemyHpBar.setMaximum(enemy.getMaxHp());
        enemyHpBar.setValue(enemy.getHp());
        enemyHpBar.setString(enemy.getHp() + " / " + enemy.getMaxHp());
        
        playerHpBar.setForeground(player.getHp() < player.getMaxHp() * 0.3 ? Color.RED : Color.GREEN);
        enemyHpBar.setForeground(enemy.getHp() < enemy.getMaxHp() * 0.3 ? Color.RED : Color.GREEN);
        
        refreshSkillButtons(); 
    }

    private void refreshSkillButtons() {
        Component[] btns = buttonPanel.getComponents();
        for (int i = 0; i < btns.length; i++) {
            if (btns[i] instanceof JButton btn && player != null) {
                int skillIdx = i + 1;
                btn.setText(player.getSkillName(skillIdx));
                
                int cd = player.getSkillCooldown(skillIdx);
                boolean canUse = (isPlayerTurn && cd == 0);
                btn.setEnabled(canUse);
                
                if (!isPlayerTurn || cd > 0) {
                    btn.setForeground(Color.GRAY); 
                } else {
                    btn.setForeground(new Color(175, 238, 171)); 
                }
            }
        }
    }

    private void disableButtons(boolean disable) {
        for (Component c : buttonPanel.getComponents()) {
            if (c instanceof JButton btn) {
                btn.setEnabled(!disable);
                if (disable) btn.setForeground(Color.GRAY); 
                else btn.setForeground(new Color(175, 238, 171)); 
            }
        }
    }

    private class ActiveEffect {
        Sprite sprite;
        int x, y, w, h;

        public ActiveEffect(Sprite s, int x, int y, int w, int h) {
            this.sprite = s; 
            this.x = x; 
            this.y = y; 
            this.w = w; 
            this.h = h;
        }
    }

    private class SkillButton extends JButton {
        private int skillNum;

        public SkillButton(String text, int skillNum) {
            super(text);
            this.skillNum = skillNum;

            setFocusable(false);
            setFont(new Font("Serif", Font.BOLD, 18));
            setContentAreaFilled(false);
            setBorderPainted(false); 
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            if (getModel().isPressed()) g2.setColor(new Color(60, 60, 60));
            else if (getModel().isRollover()) g2.setColor(new Color(80, 80, 80));
            else g2.setColor(new Color(40, 40, 40));
            
            g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
        
            g2.setColor(new Color(181, 153, 110));
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        
            if (player != null && player.getSkillCooldown(skillNum) == 0) {
                setForeground(new Color(175, 238, 171)); 
            }

            super.paintComponent(g);

            int cd = (player != null) ? player.getSkillCooldown(skillNum) : 0;
            if (cd > 0) {
                g2.setColor(new Color(0, 0, 0, 200));
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);

                g2.setColor(new Color(249, 152, 155)); 
                g2.setFont(new Font("Serif", Font.BOLD, 18));
                String text = "Cooldown: " + String.valueOf(cd) + " turn(s)";
                FontMetrics fm = g2.getFontMetrics();
                g2.drawString(text, (getWidth() - fm.stringWidth(text)) / 2, (getHeight() + fm.getAscent()) / 2 - 4);
            }
        }
    }
}