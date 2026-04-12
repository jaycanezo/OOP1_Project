package EchoesOfTheOath.UI;

import EchoesOfTheOath.Characters.*;
import EchoesOfTheOath.Characters.Character;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class IntroPanel extends JPanel {
    private GameWindow game;
    private JTextArea textArea;
    private JPanel splitScreen;
    private JPanel buttonPanel;
    
    private enum State { STORY, TRIAL }
    private State currentState = State.STORY;

    private String fullDialogue = "Long ago, three adventurers—a brave warrior, a swift archer, and a wise mage—" +
                                  "traveled across worlds, earning fame for vanquishing great evils. " +
                                  "Their bond was unbreakable, their deeds legendary...";
    private int charIndex = 0;
    private float alpha = 0.0f;
    private Timer animationTimer;

    private Warrior warrior = new Warrior();
    private Archer archer = new Archer();
    private Mage mage = new Mage();
    private Boss boss = new Boss();

    private BattleSidePanel heroSide;
    private BattleSidePanel enemySide;
    private Sprite background;

    public IntroPanel(GameWindow game) {
        this.game = game;
        this.setOpaque(false); // Critical for custom background
        this.setLayout(new BorderLayout());
        this.setFocusable(true);
        background = new Sprite("/EchoesOfTheOath/Resources/intro_bg.png", 426, 240, 121);

        warrior.setLevel(12);
        archer.setLevel(12);
        mage.setLevel(12);

        setupComponents();
        startAnimation();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (currentState == State.STORY && (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE)) {
                    switchToTrial();
                }
            }
        });
    }

    // MAIN BACKGROUND DRAWING GOES HERE
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null && background.isLoaded()) {
            g.drawImage(background.getCurrentFrame(), 0, 0, getWidth(), getHeight(), null);
        }
    }

    private void setupComponents() {
        heroSide = new BattleSidePanel(warrior, 50, 50, 450, 450);
        enemySide = new BattleSidePanel(boss, 50, 50, 450, 450);

        splitScreen = new JPanel(new GridLayout(1, 2)); 
        splitScreen.setOpaque(false);
        splitScreen.setVisible(false);
        splitScreen.add(heroSide);
        splitScreen.add(enemySide);
        add(splitScreen, BorderLayout.CENTER);

        JPanel bottomContainer = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                // Draw a solid dark box here to act as a "shield" 
                // This prevents the character sprites from bleeding into the text area
                g.setColor(new Color(0, 0, 0, 180)); 
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        bottomContainer.setOpaque(false); // We are painting the background manually now
        bottomContainer.setPreferredSize(new Dimension(1080, 250));

        textArea = new JTextArea("");
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setFont(new Font("Monospaced", Font.BOLD, 22));
        textArea.setBackground(new Color(0, 0, 0, 0)); 
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setMargin(new Insets(15, 40, 30, 40));
        bottomContainer.add(textArea, BorderLayout.CENTER);

        buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.setVisible(false);
        
        for (int i = 1; i <= 3; i++) {
            JButton btn = new JButton("Skill " + i);
            int skillNum = i;
            btn.addActionListener(e -> handleTrialAction(skillNum));
            buttonPanel.add(btn);
        }
        bottomContainer.add(buttonPanel, BorderLayout.EAST);
        add(bottomContainer, BorderLayout.SOUTH);
    }

    private void startAnimation() {
        // 50ms (20 FPS) is good for background and UI stability
        animationTimer = new Timer(50, e -> {
            // Always update background and characters
            if (background != null) background.update();
            warrior.updateAnimations();
            archer.updateAnimations();
            mage.updateAnimations();
            boss.updateAnimations();

            // Typewriter only during Story
            if (currentState == State.STORY) {
                if (charIndex < fullDialogue.length()) {
                    textArea.append(String.valueOf(fullDialogue.charAt(charIndex)));
                    charIndex++;
                }
                if (alpha < 1.0f) {
                    alpha += 0.02f;
                    textArea.setForeground(new Color(1f, 1f, 1f, Math.min(alpha, 1.0f)));
                }
            }
            
            // Update BattleSidePanels if they are visible
            if (currentState == State.TRIAL) {
                heroSide.updateEffects();
                enemySide.updateEffects();
            }

            repaint();
        });
        animationTimer.start();
    }

    private void switchToTrial() {
        // Instantly finish story and show battle components
        currentState = State.TRIAL;
        
        textArea.setText("The trial begins! Witness the power of the heroes.");
        textArea.setForeground(Color.WHITE);
        
        splitScreen.setVisible(true);
        buttonPanel.setVisible(true);
        
        // START NEW TIMER
        
        heroSide.updateEffects(); 
        enemySide.updateEffects();
        
        revalidate();
        repaint();
    }

    private void handleTrialAction(int skillNum) {
        Character activeHero = null;

        // 1. Determine who is currently attacking
        if (!warrior.allSkillsUsed()) {
            activeHero = warrior;
        } else if (!archer.allSkillsUsed()) {
            activeHero = archer;
        } else if (!mage.allSkillsUsed()) {
            activeHero = mage;
        }

        // 2. Safety check: if no one has turns left, exit
        if (activeHero == null) return;

        // 3. Play the visual effects owned by the character
        if (activeHero.isSkillAvailable(skillNum)) {
        Sprite[] effects = activeHero.getSkillSprite(skillNum);
        
        if (effects != null) {
                for (int i = 0; i < effects.length; i++) {
                    final Sprite s = effects[i];
                    Timer t = new Timer(i * 400, e -> {
                        enemySide.playEffect(s, 50, 50, 450, 450);
                    });
                    t.setRepeats(false);
                    t.start();
                }
            }
        }

        // 4. Run the actual battle logic
        String result = activeHero.useSkill(skillNum, boss);
        textArea.setText(result); // Display the return string from your Character class
        textArea.getParent().repaint();

        // 5. Handle character swapping visually
        // Inside handleTrialAction in IntroPanel.java
        if (activeHero == warrior && warrior.allSkillsUsed()) {
            // Create a 1.5-second delay before switching to Archer
            Timer delay = new Timer(1500, e -> {
                textArea.append("\nWarrior has finished. Archer, take aim!");
                heroSide.setOwner(archer); 
            });
            delay.setRepeats(false);
            delay.start();
        } 
        else if (activeHero == archer && archer.allSkillsUsed()) {
            // Create a 1.5-second delay before switching to Mage
            Timer delay = new Timer(1500, e -> {
                textArea.append("\nArcher has finished. Mage, unleash your mana!");
                heroSide.setOwner(mage);
            });
            delay.setRepeats(false);
            delay.start();
        } 
        else if (activeHero == mage && mage.allSkillsUsed()) {
            // Create a longer delay for the final boss death/transition
            Timer delay = new Timer(2000, e -> {
                if (animationTimer != null) animationTimer.stop();
                game.showScreen("charSelect");
            });
            delay.setRepeats(false);
            delay.start();
        }
    }

    /**
     * Inner class to handle drawing specific sprites in the split-screen view.
     */
    // Create a small helper class to store effect data
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
            for (ActiveEffect ae : activeEffects) {
                if (ae.sprite != null) ae.sprite.update();
            }
        }
    
        public void playEffect(Sprite effect, int ex, int ey, int ew, int eh) {
            ActiveEffect newEffect = new ActiveEffect(effect, ex, ey, ew, eh);
            activeEffects.add(newEffect);
            Timer t = new Timer(600, e -> { activeEffects.remove(newEffect); repaint(); });
            t.setRepeats(false);
            t.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // REMOVED BACKGROUND DRAWING FROM HERE TO PREVENT DOUBLE BACKGROUNDS
            
            if (owner != null && owner.getIdleSprite() != null) {
                Sprite s = owner.getIdleSprite();
                if (s.isLoaded()) {
                    g.drawImage(s.getCurrentFrame(), x, y, w, h, null);
                }
            }
            for (ActiveEffect ae : activeEffects) {
                if (ae.sprite != null && ae.sprite.isLoaded()) {
                    g.drawImage(ae.sprite.getCurrentFrame(), ae.x, ae.y, ae.w, ae.h, null);
                }
            }
        }
    }
}

