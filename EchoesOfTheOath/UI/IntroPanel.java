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
    private String fullDialogue = DialogueManager.getIntroDialogue();
    private int charIndex = 0;
    private float alpha = 0.0f;
    private Timer animationTimer;

    private Warrior warrior = new Warrior();
    private Archer archer = new Archer();
    private Mage mage = new Mage();
    private Elarion boss = new Elarion();

    private BattleSidePanel heroSide;
    private BattleSidePanel enemySide;
    private Sprite background;

    public IntroPanel(GameWindow game) {
        this.game = game;
        this.setOpaque(false);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (background != null && background.isLoaded()) {
            g.drawImage(background.getCurrentFrame(), 0, 0, getWidth(), getHeight(), null);
        }

        if (currentState == State.TRIAL) {
            Character activeHero = getActiveHero();
            if (activeHero != null && activeHero.getIdleSprite() != null) {
                Sprite s = activeHero.getIdleSprite();
                if (s.isLoaded()) {
                    g.drawImage(s.getCurrentFrame(), 100, getHeight() - 580, 400, 400, null);
                }
            }
            if (boss != null && boss.getIdleSprite() != null) {
                Sprite s = boss.getIdleSprite();
                if (s.isLoaded()) {
                    g.drawImage(s.getCurrentFrame(), getWidth() - 500, getHeight() - 580, 400, 400, null);
                }
            }
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
                Graphics2D g2 = (Graphics2D) g;
                // Your custom box drawing logic
                g2.setColor(Color.BLACK); 
                g2.fillRoundRect(50, 10, getWidth()-100, getHeight()-20, 20, 20);
                g2.setColor(new Color(181, 153, 110));
                g2.setStroke(new BasicStroke(3)); 
                g2.drawRoundRect(50, 10, getWidth()-100, getHeight()-20, 20, 20);
            }
        };
        bottomContainer.setPreferredSize(new Dimension(1040, 250));
        bottomContainer.setOpaque(false);
        bottomContainer.setFocusable(false); //


        // 1. Setup the Text Area for the Center
        textArea = new JTextArea("");
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setFont(new Font("Monospaced", Font.BOLD, 22));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        // Adjust margins to make room for the buttons below
        textArea.setMargin(new Insets(15, 80, 10, 80)); 
        bottomContainer.add(textArea, BorderLayout.CENTER);

        // 2. Setup the Button Panel for the South (NO WRAPPER)
        buttonPanel = new JPanel(new GridLayout(1, 3, 5, 0)); // 1 row, 3 columns
        buttonPanel.setOpaque(false);
        buttonPanel.setVisible(false);
        // Add an EmptyBorder to give the buttons some "room" from the box edges
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 0, 50)); 

        buttonPanel.add(new SkillButton("Skill 1", 1));
        buttonPanel.add(new SkillButton("Skill 2", 2));
        buttonPanel.add(new SkillButton("Skill 3", 3));

        bottomContainer.add(buttonPanel, BorderLayout.NORTH);
        add(bottomContainer, BorderLayout.SOUTH);
    }

    private void startAnimation() {
        animationTimer = new Timer(50, e -> {
            if (background != null) background.update();
            warrior.updateAnimations();
            archer.updateAnimations();
            mage.updateAnimations();
            boss.updateAnimations();

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
        
            if (currentState == State.TRIAL) {
                heroSide.updateEffects();
                enemySide.updateEffects();
            }

            repaint();
        });
        animationTimer.start();
    }

    private void switchToTrial() {
        currentState = State.TRIAL;
        textArea.setText("The trial begins! Witness the power of the heroes.");
        textArea.setForeground(Color.WHITE);
        splitScreen.setVisible(true);
        buttonPanel.setVisible(true);

        refreshTrialButtons();

        revalidate();
        repaint();
    }

    private void handleTrialAction(int skillNum) {
        Character activeHero = getActiveHero();
        if (activeHero == null) return;

        if (activeHero.isSkillAvailable(skillNum)) {
            Sprite[] effects = activeHero.getSkillSprite(skillNum);
            if (effects != null) {
                for (int i = 0; i < effects.length; i++) {
                    final Sprite s = effects[i];
                    Timer t = new Timer(i * 400, ev -> enemySide.playEffect(s, 50, 50, 450, 450));
                    t.setRepeats(false);
                    t.start();
                }
            }
        }

        String result = activeHero.useSkill(skillNum, boss);
        
        if (activeHero.getHp() <= 0) {
            if (animationTimer != null) animationTimer.stop(); 
            game.showScreen("gameover");
            return; 
        }

        refreshTrialButtons();
        textArea.setText(result);

        if (activeHero == warrior && warrior.allSkillsUsed()) {
            Timer delay = new Timer(1500, e -> {
                textArea.append("\nWarrior has finished. Archer, take aim!");
                heroSide.setOwner(archer); 
                refreshTrialButtons();
            });
            delay.setRepeats(false);
            delay.start();
        } 
        else if (activeHero == archer && archer.allSkillsUsed()) {
            Timer delay = new Timer(1500, e -> {
                textArea.append("\nArcher has finished. Mage, unleash your mana!");
                heroSide.setOwner(mage);
                refreshTrialButtons();
            });
            delay.setRepeats(false);
            delay.start();
        } 
        else if (activeHero == mage && mage.allSkillsUsed()) {
            Timer delay = new Timer(2000, e -> {
                if (animationTimer != null) animationTimer.stop();
                game.showScreen("charSelect");
            });
            delay.setRepeats(false);
            delay.start();
        }
    }

    private void refreshTrialButtons() {
        for (Component c : buttonPanel.getComponents()) {
            if (c instanceof SkillButton btn) {
                btn.updateButtonState();
            }
        }
    }

    private Character getActiveHero() {
        if (!warrior.allSkillsUsed()) return warrior;
        if (!archer.allSkillsUsed()) return archer;
        if (!mage.allSkillsUsed()) return mage;
        return null;
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
            setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
            setForeground(Color.WHITE);
            
            addActionListener(e -> {
                handleTrialAction(skillNum);
                IntroPanel.this.requestFocusInWindow();
            });
        }

        public void updateButtonState() {
            Character activeHero = getActiveHero();
            if (activeHero != null) {
                setEnabled(activeHero.getSkillCooldown(skillNum) == 0);

                setForeground(new Color(175, 238, 171));
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (getModel().isPressed()){ 
                g2.setColor(new Color(60, 60, 60));
            } else if (getModel().isRollover()){
                g2.setColor(new Color(80, 80, 80));
            } else {
                g2.setColor(new Color(40, 40, 40));
            }
            g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
            
            g2.setColor(new Color(181, 153, 110));
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            

            super.paintComponent(g);

            Character activeHero = getActiveHero();
            if (activeHero != null) {
                int cd = (activeHero != null) ? activeHero.getSkillCooldown(skillNum) : 0;
                if (cd > 0) {
                    g2.setColor(new Color(0, 0, 0, 200));
                    g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);

                    g2.setColor(new Color(181, 153, 110));
                    g2.setStroke(new BasicStroke(2));
                    g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

                    g2.setColor(new Color(249, 152, 155));
                    g2.setFont(new Font("Serif", Font.BOLD, 18));
                    String text = "Cooldown: " + String.valueOf(cd) + " turn(s)";
                    FontMetrics fm = g2.getFontMetrics();
                    int tx = (getWidth() - fm.stringWidth(text)) / 2;
                    int ty = (getHeight() + fm.getAscent()) / 2 - 4;
                    g2.drawString(text, tx, ty);
                }
            }
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
            Timer t = new Timer(600, e -> { activeEffects.remove(newEffect); repaint(); });
            t.setRepeats(false);
            t.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (ActiveEffect ae : activeEffects) {
                if (ae.sprite != null && ae.sprite.isLoaded()) {
                    g.drawImage(ae.sprite.getCurrentFrame(), ae.x, ae.y, ae.w, ae.h, null);
                }
            }
        }
    }
}