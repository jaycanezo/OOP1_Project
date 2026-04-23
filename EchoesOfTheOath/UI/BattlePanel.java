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
    private BattleSidePanel playerSide, enemySide;
    private Timer animationTimer;

    private final int BATTLE_STATE = 0;
    private final int INVENTORY_STATE = 1;
    private int currentBattleState = BATTLE_STATE;
    private int slotCol = 0, slotRow = 0;
    private int scrollOffset = 0;

    private Sprite background; // Current active background
    private int currentLevel = 0; // Local index to match GameWindow's boss index

    // Array of paths matching the boss sequence in GameWindow
    private final String[] backgroundPaths = {
        "/EchoesOfTheOath/Resources/nation1_bg6.png",
        "/EchoesOfTheOath/Resources/nation2_bg8.png",
        "/EchoesOfTheOath/Resources/battle_bg_Ilaryx.png",
        "/EchoesOfTheOath/Resources/battle_bg_Lunareth.png",
        "/EchoesOfTheOath/Resources/battle_bg_Sarukdal.png",
        "/EchoesOfTheOath/Resources/battle_bg_Elarion.png"
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

        setupVisuals();
        setupUI();
        startAnimation();
    }

    public void loadBattleData() {
        this.player = game.getChosenCharacter();
        this.enemy = game.getCurrentBoss();
        
        // Sync local level with GameWindow's boss index
        // Since GameWindow increments this index, we use it to select the BG
        int bossIndex = 0; // Default
        try {
            // Reflection or a public getter in GameWindow for the current index is best
            // For now, we load based on the enemy name or currentBossIndex
            this.background = new Sprite(backgroundPaths[game.getBossIndex()], 1920, 1080, 1);
        } catch (Exception e) {
            this.background = null; // Fallback to black if file missing
        }

        if (player != null && enemy != null) {
            player.resetCooldowns();
            playerHpBar.setMaximum(player.getMaxHp());
            playerHpBar.setValue(player.getHp());
            playerHpBar.setString(player.getHp() + " / " + player.getMaxHp());
            playerHpBar.setMaximum(player.getMaxHp());

            enemyHpBar.setMaximum(enemy.getMaxHp());
            enemyHpBar.setMaximum(enemy.getMaxHp());
            enemyHpBar.setValue(enemy.getHp());
            enemyHpBar.setString(enemy.getHp() + " / " + enemy.getMaxHp());

            playerSide.setOwner(player);
            enemySide.setOwner(enemy);
            
            refreshSkillButtons();
            
            logArea.setText("The path is blocked by " + enemy.getName() + "!");
            repaint();
        }
    }

    private void playerTurn(int skillNum) {
        if (!player.isSkillAvailable(skillNum)) {
            logArea.setText(player.getSkillName(skillNum) + " is on cooldown!");
            return;
        }

        disableButtons(true);
        
        Sprite[] effects = player.getSkillSprite(skillNum);
        if (effects != null) {
            for (Sprite s : effects) enemySide.playEffect(s, 50, 50, 400, 400);
        }

        String result = player.useSkill(skillNum, enemy);
        logArea.setText(result);
        refreshStatus();

        if (checkGameOver()) return;

        Timer delay = new Timer(1500, e -> enemyTurn());
        delay.setRepeats(false);
        delay.start();
    }

    private void enemyTurn() {
        int randomSkill = (int)(Math.random() * 3) + 1;
        
        Sprite[] effects = enemy.getSkillSprite(randomSkill);
        if (effects != null) {
            for (Sprite s : effects) playerSide.playEffect(s, 50, 50, 400, 400);
        }

        String result = enemy.useSkill(randomSkill, player);
        logArea.append("\n" + result);
        
        player.reduceCooldowns(); 
        refreshStatus();

        if (!checkGameOver()) {
            disableButtons(false); 
        }
    }

    private boolean checkGameOver() {
        if (enemy.getHp() <= 0) {
            logArea.setText("Victory! " + enemy.getName() + " falls.");
            animationTimer.stop();
            game.advanceStoryProgress(); 
            JOptionPane.showMessageDialog(this, "Victory!");
            game.showScreen("story"); 
            return true;
        }
        if (player.getHp() <= 0) {
            animationTimer.stop();
            game.showScreen("gameover"); 
            return true;
        }
        
        return false;
    }

    private void handleKeyInput(KeyEvent e) {
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // 1. Draw the SINGLE background first
        if (background != null && background.isLoaded()) {
            g.drawImage(background.getCurrentFrame(), 0, 0, getWidth(), getHeight(), null);
        }

        // 2. Draw the Characters at fixed positions
        if (player != null && player.getIdleSprite() != null) {
            Sprite s = player.getIdleSprite();
            if (s.isLoaded()) {
                g.drawImage(s.getCurrentFrame(), 100, getHeight() - 580, 400, 400, null);
            }
        }
        if (enemy != null && enemy.getIdleSprite() != null) {
            Sprite s = enemy.getIdleSprite();
            if (s.isLoaded()) {
                g.drawImage(s.getCurrentFrame(), getWidth() - 500, getHeight() - 580, 400, 400, null);
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
        disableButtons(true);
        String flavorText = InventoryManager.useItem(item, player);
        logArea.setText(player.getName() + " used " + item.getName() + "!");
        logArea.append("\n" + flavorText);
        if (item.isConsumable()) { 
            player.getInventory().remove(index); 
        }
        refreshStatus();   
        if (checkGameOver()) return;
        Timer delay = new Timer(1500, e -> {
            enemyTurn();
            this.requestFocusInWindow(); 
        });
        delay.setRepeats(false);
        delay.start();
    }

    private void setupVisuals() {
        JPanel splitScreen = new JPanel(new GridLayout(1, 2));
        splitScreen.setOpaque(false);
        playerSide = new BattleSidePanel(null, 50, 50, 400, 400);
        enemySide = new BattleSidePanel(null, 50, 50, 400, 400);
        splitScreen.add(playerSide);
        splitScreen.add(enemySide);
        add(splitScreen, BorderLayout.CENTER);
    }

    private void setupUI() {
        JPanel topPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        playerHpBar = createHPBar();
        enemyHpBar = createHPBar();

        topPanel.add(createLabeledBar("PLAYER", playerHpBar));
        topPanel.add(createLabeledBar("ENEMY", enemyHpBar));
        add(topPanel, BorderLayout.NORTH);

        JPanel bottomContainer = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Draw the black rounded box
                g2.setColor(Color.BLACK); 
                g2.fillRoundRect(50, 10, getWidth()-100, getHeight()-20, 20, 20);
                
                // Draw the light brown "vintage" border
                g2.setColor(new Color(181, 153, 110));
                g2.setStroke(new BasicStroke(3)); 
                g2.drawRoundRect(50, 10, getWidth()-100, getHeight()-20, 20, 20);
            }
        };
        bottomContainer.setPreferredSize(new Dimension(1040, 250));
        bottomContainer.setOpaque(false); // Ensure we see the main background

        // Update logArea (JTextArea) transparency and margins
        logArea = new JTextArea("The battle begins!");
        logArea.setOpaque(false);
        logArea.setEditable(false);
        logArea.setFocusable(false);
        logArea.setFont(new Font("Monospaced", Font.BOLD, 22));
        logArea.setForeground(Color.WHITE);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        logArea.setMargin(new Insets(15, 80, 10, 80)); // Matching IntroPanel margins

        // Switch Skill buttons to a horizontal 1x3 grid
        buttonPanel = new JPanel(new GridLayout(1, 3, 5, 0)); 
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 0, 50)); // Padding

        addButton("Skill 1", 1);
        addButton("Skill 2", 2);
        addButton("Ultimate", 3);

        // Layout the box: Buttons on top, Text in the middle
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

    private JPanel createLabeledBar(String name, JProgressBar bar) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        JLabel lbl = new JLabel(name);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Monospaced", Font.BOLD, 14));
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

    private void refreshStatus() {
        if (player == null || enemy == null) return;
        
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
                btn.setText(player.getSkillName(i + 1));
                btn.setEnabled(player.getSkillCooldown(i + 1) == 0);
            }
        }
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
        private java.util.List<ActiveEffect> activeEffects = new java.util.ArrayList<>();
        private int x, y, w, h;

        public BattleSidePanel(Character owner, int x, int y, int w, int h) {
            this.owner = owner;
            this.x = x; this.y = y; this.w = w; this.h = h;
            this.setOpaque(false);
        }

        public void setOwner(Character owner) { this.owner = owner; repaint(); }

        public void updateEffects() {
            for (ActiveEffect ae : activeEffects) if (ae.sprite != null) ae.sprite.update();
        }

        public void playEffect(Sprite effect, int ex, int ey, int ew, int eh) {
            ActiveEffect newEffect = new ActiveEffect(effect, ex, ey, ew, eh);
            activeEffects.add(newEffect);
            Timer t = new Timer(1200, e -> { activeEffects.remove(newEffect); repaint(); });
            t.setRepeats(false);
            t.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            // DO NOT call super.paintComponent(g) - we need this layer transparent
            
            // Draw ONLY the skill effects (slashes, fireballs, etc.)
            for (ActiveEffect ae : activeEffects) {
                if (ae.sprite != null && ae.sprite.isLoaded()) {
                    g.drawImage(ae.sprite.getCurrentFrame(), ae.x, ae.y, ae.w, ae.h, null);
                }
            }
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
            setBorderPainted(false); // Crucial for custom round painting
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Background logic
            if (getModel().isPressed()) g2.setColor(new Color(60, 60, 60));
            else if (getModel().isRollover()) g2.setColor(new Color(80, 80, 80));
            else g2.setColor(new Color(40, 40, 40));
            
            g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
            
            // Brown Border
            g2.setColor(new Color(181, 153, 110));
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

            // Update text color based on cooldown state
            if (player != null && player.getSkillCooldown(skillNum) == 0) {
                setForeground(new Color(175, 238, 171)); // Light Green
            }

            super.paintComponent(g);

            // Cooldown overlay
            int cd = (player != null) ? player.getSkillCooldown(skillNum) : 0;
            if (cd > 0) {
                g2.setColor(new Color(0, 0, 0, 200));
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);

                g2.setColor(new Color(249, 152, 155)); // Light Red text
                g2.setFont(new Font("Serif", Font.BOLD, 24));
                String text = String.valueOf(cd);
                FontMetrics fm = g2.getFontMetrics();
                g2.drawString(text, (getWidth() - fm.stringWidth(text)) / 2, (getHeight() + fm.getAscent()) / 2 - 4);
            }
        }
    }
}