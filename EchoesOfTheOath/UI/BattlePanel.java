package EchoesOfTheOath.UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import EchoesOfTheOath.Characters.*;
import EchoesOfTheOath.Characters.Character;

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

    public BattlePanel(GameWindow game) {
        this.game = game;
        this.enemy = new babyM(); // Initialize enemy first
        
        // We try to get the player, but it might be null at startup
        this.player = game.getChosenCharacter(); 

        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());

        // setupUI calls createHPBar, so 'enemy' must not be null here!
        setupVisuals();
        setupUI();
        startAnimation();
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

        // Use a placeholder if player is null
        String playerName = (player != null) ? player.getName() : "Hero"; 
        topPanel.add(createLabeledBar(playerName, playerHpBar));
        topPanel.add(createLabeledBar(enemy.getName(), enemyHpBar));
        add(topPanel, BorderLayout.NORTH);

        // --- BOTTOM: Controls & Logs ---
        JPanel bottomContainer = new JPanel(new BorderLayout());
        bottomContainer.setPreferredSize(new Dimension(1080, 200));
        bottomContainer.setBackground(new Color(20, 20, 20));

        logArea = new JTextArea("The battle begins! Your turn.");
        // ... (rest of logArea setup) ...

        buttonPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        buttonPanel.setOpaque(false);
        
        // Use generic text initially; loadBattleData will update these later
        addButton("Skill 1", 1);
        addButton("Skill 2", 2);
        addButton("Ultimate", 3);
        
        bottomContainer.add(buttonPanel, BorderLayout.EAST);
        add(bottomContainer, BorderLayout.SOUTH);
    }

    private void addButton(String text, int skillNum) {
        JButton btn = new JButton(text);
        btn.addActionListener(e -> playerTurn(skillNum));
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
            player.updateAnimations();
            enemy.updateAnimations();
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
        updateUI();

        if (checkGameOver()) return;

        // 3. Delay before Enemy Turn
        Timer delay = new Timer(1500, e -> enemyTurn());
        delay.setRepeats(false);
        delay.start();
    }

    private void enemyTurn() {
        int randomSkill = (int)(Math.random() * 3) + 1;
        
        // 1. Play Enemy Effect on Player
        Sprite[] effects = enemy.getSkillSprite(randomSkill);
        if (effects != null) {
            for (Sprite s : effects) playerSide.playEffect(s, 50, 50, 400, 400);
        }

        // 2. Battle Logic
        String result = enemy.useSkill(randomSkill, player);
        logArea.append("\n" + result);
        updateUI();

        if (checkGameOver()) return;

        disableButtons(false);
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
            playerHpBar.setForeground(Color.RED);
        } else {
            playerHpBar.setForeground(Color.GREEN);
        }

        if (eCurrentHP < (eMaxHP * 0.3)) {
            enemyHpBar.setForeground(Color.RED);
        } else {
            enemyHpBar.setForeground(Color.GREEN);
        }
        
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

        // Updated playEffect adds to the list instead of overwriting
        public void playEffect(Sprite effect, int ex, int ey, int ew, int eh) {
            ActiveEffect newEffect = new ActiveEffect(effect, ex, ey, ew, eh);
            activeEffects.add(newEffect);
            
            // Remove THIS specific effect after 600ms
            Timer t = new Timer(600, e -> { 
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
}
