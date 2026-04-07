package EchoesOfTheOath.UI;

import EchoesOfTheOath.Characters.*;
import EchoesOfTheOath.Characters.Character;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * IntroPanel handles the cinematic opening of the game.
 * It transitions from a story typewriter effect to an interactive trial.
 */
public class IntroPanel extends JPanel {
    private GameWindow game;
    private JTextArea textArea;
    private JPanel splitScreen;
    private JPanel buttonPanel;
    
    // State Management: Determines if we are reading story or fighting
    private enum State { STORY, TRIAL }
    private State currentState = State.STORY;

    // Animation & Typewriter variables
    private String fullDialogue = "Long ago, three adventurers—a brave warrior, a swift archer, and a wise mage—" +
                                  "traveled across worlds, earning fame for vanquishing great evils. " +
                                  "Their bond was unbreakable, their deeds legendary...";
    private int charIndex = 0;
    private float alpha = 0.0f;
    private Timer animationTimer;

    // Character Objects from your Trial logic
    private Warrior warrior = new Warrior();
    private Archer archer = new Archer();
    private Mage mage = new Mage();
    private Boss boss = new Boss();

    // Sprite and Visual variables
    private BattleSidePanel heroSide;
    private BattleSidePanel enemySide;
    private Sprite currentHeroSprite, currentEnemySprite;

    public IntroPanel(GameWindow game) {
        this.game = game;
        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());
        this.setFocusable(true);

        // Set character levels as per original console code
        warrior.setLevel(12);
        archer.setLevel(12);
        mage.setLevel(12);

        setupComponents();
        startAnimation();

        // Key Listener to skip the intro or progress text
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (currentState == State.STORY && (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE)) {
                    switchToTrial();
                }
            }
        });
    }

    private void setupComponents() {
        // 1. Initialize all Sprites using your Sprite class
        // Note: Ensure these paths match your resource folder exactly.

        // Instantiate custom panels that draw sprites
        heroSide = new BattleSidePanel(warrior, 50, 50, 450, 450);
        enemySide = new BattleSidePanel(boss, 50, 50, 450, 450);

        splitScreen = new JPanel(new GridLayout(1, 2)); 
        splitScreen.setOpaque(false);
        splitScreen.setVisible(false);
        splitScreen.add(heroSide);
        splitScreen.add(enemySide);
        add(splitScreen, BorderLayout.CENTER);

        // 3. Create the Dialogue Box (South)
        JPanel bottomContainer = new JPanel(new BorderLayout());
        bottomContainer.setPreferredSize(new Dimension(1080, 250));
        bottomContainer.setBackground(new Color(15, 15, 15));

        textArea = new JTextArea("");
        textArea.setFont(new Font("Monospaced", Font.BOLD, 22));
        textArea.setForeground(new Color(1f, 1f, 1f, 0f)); // Start transparent
        textArea.setBackground(Color.BLACK);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setMargin(new Insets(15, 40, 30, 40));
        bottomContainer.add(textArea, BorderLayout.CENTER);

        // 4. Create Skill Buttons (Hidden during story)
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
        // The core loop for typewriter, fading, and sprite updates
        animationTimer = new Timer(40, e -> {
            // Typewriter logic
            if (charIndex < fullDialogue.length()) {
                textArea.append(String.valueOf(fullDialogue.charAt(charIndex)));
                charIndex++;
            }

            // Global Fade logic
            if (alpha < 1.0f) {
                alpha += 0.02f;
                textArea.setForeground(new Color(1f, 1f, 1f, Math.min(alpha, 1.0f)));
            }

            // Update all sprites so they are ready when shown
            warrior.updateAnimations();
            archer.updateAnimations();
            mage.updateAnimations();
            boss.updateAnimations();
            // Refresh visuals
            repaint();
        });
        animationTimer.start();
    }

    private void switchToTrial() {
        // Instantly finish story and show battle components
        if (animationTimer != null) animationTimer.stop();
        currentState = State.TRIAL;
        
        textArea.setText("The trial begins! Witness the power of the heroes.");
        textArea.setForeground(Color.WHITE);
        
        splitScreen.setVisible(true);
        buttonPanel.setVisible(true);
        
        // Re-start timer at a slightly slower pace for general animation
        animationTimer = new Timer(100, e -> {
            warrior.updateAnimations();
            archer.updateAnimations();
            mage.updateAnimations();
            boss.updateAnimations();
            repaint();
        });
        animationTimer.start();
        heroSide.updateEffects(); 
        enemySide.updateEffects();
        
        revalidate();
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
                    ae.sprite.update(); // Moves the effect to its next animation frame
                }
            }
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
}

