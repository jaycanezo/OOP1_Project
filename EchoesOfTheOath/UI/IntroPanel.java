package EchoesOfTheOath.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import EchoesOfTheOath.Characters.*;
import EchoesOfTheOath.Characters.Character;

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
    private Sprite warriorSprite, archerSprite, mageSprite, bossSprite, slashSprite, CrimsonStrikeSprite, BladeQuake1Sprite, BladeQuake2Sprite, fireballSprite, heatfireSurgeSprite, astralCataclysm1Sprite, astralCataclysm2Sprite, astralCataclysm3Sprite;

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
        warriorSprite = new Sprite("/EchoesOfTheOath/Resources/Warrior_Sprite.png", 1280, 720, 160);
        archerSprite = new Sprite("/EchoesOfTheOath/Resources/Archer.png", 515, 484, 1);
        mageSprite = new Sprite("/EchoesOfTheOath/Resources/Mage.png", 500, 500, 1);
        bossSprite = new Sprite("/EchoesOfTheOath/Resources/placeholder.png", 400, 400, 1); 
        slashSprite = new Sprite("/EchoesOfTheOath/Resources/SlashEffect.png", 128, 128, 9);
        CrimsonStrikeSprite = new Sprite("/EchoesOfTheOath/Resources/CrimsonStrikeEffect.png", 128, 128, 7);
        BladeQuake1Sprite = new Sprite("/EchoesOfTheOath/Resources/BladeQuake1Effect.png", 256, 128, 14);
        BladeQuake2Sprite = new Sprite("/EchoesOfTheOath/Resources/BladeQuake2Effect.png", 256, 128, 11);
        fireballSprite = new Sprite("/EchoesOfTheOath/Resources/FireBallEffect.png", 128, 128, 7);
        heatfireSurgeSprite = new Sprite("/EchoesOfTheOath/Resources/HeatfireSurgeEffect.png", 128, 128, 12);
        astralCataclysm1Sprite = new Sprite("/EchoesOfTheOath/Resources/AstralCataclysm1Effect.png", 192, 128, 12);
        astralCataclysm2Sprite = new Sprite("/EchoesOfTheOath/Resources/AstralCataclysm2Effect.png", 128, 128, 15);
        astralCataclysm3Sprite = new Sprite("/EchoesOfTheOath/Resources/AstralCataclysm3Effect.png", 128, 128, 7);

        // Instantiate custom panels that draw sprites
        heroSide = new BattleSidePanel(warriorSprite, 50, 50, 450, 450);
        enemySide = new BattleSidePanel(bossSprite, 50, 50, 450, 450);

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
        textArea.setFont(new Font("Serif", Font.BOLD, 26));
        textArea.setForeground(new Color(1f, 1f, 1f, 0f)); // Start transparent
        textArea.setBackground(Color.BLACK);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setMargin(new Insets(30, 40, 30, 40));
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
            warriorSprite.update();
            archerSprite.update();
            mageSprite.update();
            bossSprite.update();
            slashSprite.update();
            CrimsonStrikeSprite.update();
            BladeQuake1Sprite.update();
            BladeQuake2Sprite.update();
            fireballSprite.update();
            heatfireSurgeSprite.update();
            astralCataclysm1Sprite.update();
            astralCataclysm2Sprite.update();
            astralCataclysm3Sprite.update();
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
            warriorSprite.update();
            archerSprite.update();
            mageSprite.update();
            bossSprite.update();
            slashSprite.update();
            CrimsonStrikeSprite.update();
            BladeQuake1Sprite.update();
            BladeQuake2Sprite.update();
            fireballSprite.update();
            heatfireSurgeSprite.update();
            astralCataclysm1Sprite.update();
            astralCataclysm2Sprite.update();
            astralCataclysm3Sprite.update();
            repaint();
        });
        animationTimer.start();
        
        revalidate();
    }

    private void handleTrialAction(int skillNum) {
        // Logic adapted from your console character trial
        if (!warrior.allSkillsUsed()) {
            warrior.useSkill(skillNum, boss);

            if(skillNum == 1) {
                enemySide.playEffect(slashSprite, 50, 50, 450, 450); // Play slash effect on hero side
            } else if (skillNum ==2) {
                enemySide.playEffect(CrimsonStrikeSprite, 50, 50, 450, 450); // Play Crimson Strike effect on hero side
            } else if(skillNum ==3) {
                heroSide.playEffect(BladeQuake1Sprite, 50, 50, 450, 450); // Play Crimson Strike effect on enemy side

                Timer delay = new Timer(500, e -> {
                    enemySide.playEffect(BladeQuake2Sprite, 50, 50, 450, 450); // Play Blade Quake effect on enemy side
                });
                delay.setRepeats(false);
                delay.start();
            }

            textArea.setText("Warrior uses Skill " + skillNum + "!");
            if (warrior.allSkillsUsed()) {
                textArea.append("\nWarrior has finished. Archer, take aim!");
                heroSide.setSprite(archerSprite); // Swap sprite to Archer
            }
        } 
        else if (!archer.allSkillsUsed()) {
            archer.useSkill(skillNum, boss);



            textArea.setText("Archer uses Skill " + skillNum + "!");
            if (archer.allSkillsUsed()) {
                textArea.append("\nArcher has finished. Mage, unleash your mana!");
                heroSide.setSprite(mageSprite); // Swap sprite to Mage
            }
        } 
        else if (!mage.allSkillsUsed()) {
            mage.useSkill(skillNum, boss);

            if(skillNum == 1) {
                enemySide.playEffect(fireballSprite, 50, 50, 450, 450); // Play slash effect on hero side
            } else if (skillNum ==2) {
                enemySide.playEffect(heatfireSurgeSprite, 50, 50, 450, 450); // Play Crimson Strike effect on hero side
            } else if(skillNum ==3) {
                enemySide.playEffect(astralCataclysm1Sprite, 50, 50, 450, 450); // Play Crimson Strike effect on enemy side

                Timer delay = new Timer(800, e -> {
                    enemySide.playEffect(astralCataclysm2Sprite, 50, 50, 450, 450); // Play Blade Quake effect on enemy side
                });

                delay.setRepeats(false);
                delay.start();

                Timer delay2 = new Timer(1600, e -> {
                    enemySide.playEffect(astralCataclysm3Sprite, 50, 50, 450, 450); // Play Blade Quake effect on enemy side
                });
                
                delay2.setRepeats(false);
                delay2.start();
            }

            textArea.setText("Mage uses Skill " + skillNum + "!");
            if (mage.allSkillsUsed()) {
                // All trials done, move to next scene
                Timer delay = new Timer(3000, e -> {
                    game.showScreen("charSelect"); // Transition to story or next scene
                });
                delay.setRepeats(false);
                delay.start();
            }
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
        private Sprite currentSprite;
        // Use a List to hold multiple effects at once
        private java.util.List<ActiveEffect> activeEffects = new java.util.ArrayList<>();
        private int x, y, w, h;

        public BattleSidePanel(Sprite s, int x, int y, int w, int h) {
            this.currentSprite = s;
            this.x = x; this.y = y; this.w = w; this.h = h;
            this.setOpaque(false);
        }

        public void setSprite(Sprite s) { this.currentSprite = s; }

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
            if (currentSprite != null && currentSprite.isLoaded()) {
                g.drawImage(currentSprite.getCurrentFrame(), x, y, w, h, null);
            }
            // 2. Draw ALL active effects in the list
            for (int i = 0; i < activeEffects.size(); i++) {
                ActiveEffect ae = activeEffects.get(i);
                if (ae.sprite != null && ae.sprite.isLoaded()) {
                    g.drawImage(ae.sprite.getCurrentFrame(), ae.x, ae.y, ae.w, ae.h, null);
                }
            }
        }
    }
}

