package EchoesOfTheOath.UI;

import EchoesOfTheOath.Characters.*;
import EchoesOfTheOath.Characters.Character;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class IntroPanel extends JPanel {
    private GameWindow game;
    private JTextArea textArea;
    private JPanel buttonPanel;
    private JButton continueBtn;
    
    private enum State { 
        STORY, TRIAL 
    }
    
    private State currentState = State.STORY;
    private String fullDialogue = DialogueManager.getIntroDialogue();
    private int charIndex = 0;
    private float alpha = 0.0f;
    private Timer animationTimer;
    private Font smallText;
    private Font mediumText;

    private Warrior warrior = new Warrior();
    private Archer archer = new Archer();
    private Mage mage = new Mage();
    private Elarion boss = new Elarion();
    private Character currentHero;

    private Sprite background;
    private List<ActiveEffect> activeEffects = new ArrayList<>();

    public IntroPanel(GameWindow game) {
        this.game = game;
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.setFocusable(true);
        background = new Sprite("/EchoesOfTheOath/Resources/intro_bg.png", 426, 240, 121);
        smallText = FontManager.getFont("Jersey10-Regular.ttf", 22f);
        mediumText = FontManager.getFont("Jersey10-Regular.ttf", 26f);

        warrior.setLevel(12);
        archer.setLevel(12);
        mage.setLevel(12);
        currentHero = warrior;

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

    public void resetIntro() {
        this.currentState = State.STORY;
        this.charIndex = 0;
        this.alpha = 0.0f;
        this.activeEffects.clear();
        
        textArea.setText("");
        buttonPanel.setVisible(false);
        continueBtn.setVisible(false);
        continueBtn.setText("Next Hero:");
        
        this.warrior = new Warrior(); 
        warrior.setLevel(12);
        this.currentHero = warrior;

        this.archer = new Archer(); 
        archer.setLevel(12);

        this.mage = new Mage();
        mage.setLevel(12);

        this.boss = new Elarion();
        
        if (animationTimer != null && !animationTimer.isRunning()) {
            animationTimer.start();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

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

            for (ActiveEffect ae : activeEffects) {
                if (ae.sprite != null && ae.sprite.isLoaded()) {
                    g.drawImage(ae.sprite.getCurrentFrame(), ae.x, ae.y, ae.w, ae.h, null);
                }
            }
        }
    }

    private void setupComponents() {
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
        bottomContainer.setFocusable(false);

        textArea = new JTextArea("");
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setFont(mediumText);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMargin(new Insets(15, 80, 10, 80)); 
        bottomContainer.add(textArea, BorderLayout.CENTER);

        buttonPanel = new JPanel(new GridLayout(1, 3, 5, 0)); 
        buttonPanel.setOpaque(false);
        buttonPanel.setVisible(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 0, 50)); 

        buttonPanel.add(new SkillButton("Skill 1", 1));
        buttonPanel.add(new SkillButton("Skill 2", 2));
        buttonPanel.add(new SkillButton("Ultimate", 3));
        bottomContainer.add(buttonPanel, BorderLayout.NORTH);

        JPanel continuePanel = new JPanel(new BorderLayout());
        continuePanel.setOpaque(false);
        continuePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 70)); 
        
        continueBtn = new JButton("Next Hero:") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) g2.setColor(new Color(60, 60, 60));
                else if (getModel().isRollover()) g2.setColor(new Color(80, 80, 80));
                else g2.setColor(new Color(40, 40, 40));
                
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
                g2.setColor(new Color(175, 238, 171));
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
                super.paintComponent(g);
            }
        };
        continueBtn.setFont(smallText);
        continueBtn.setForeground(Color.WHITE);
        continueBtn.setContentAreaFilled(false);
        continueBtn.setBorderPainted(false);
        continueBtn.setFocusable(false);
        continueBtn.setPreferredSize(new Dimension(160, 40));
        continueBtn.setVisible(false);
        continueBtn.addActionListener(e -> advanceHero());
        
        continuePanel.add(continueBtn, BorderLayout.SOUTH);
        bottomContainer.add(continuePanel, BorderLayout.EAST);

        add(bottomContainer, BorderLayout.SOUTH);
    }

    private void advanceHero() {
        if (currentHero == warrior) {
            currentHero = archer;
            currentHero.resetCooldowns();
            textArea.setText("Warrior steps back. Archer, take aim!\nFeel free to test skills, then click Next Hero.");
            refreshTrialButtons();
        } else if (currentHero == archer) {
            currentHero = mage;
            currentHero.resetCooldowns();
            textArea.setText("Archer steps back. Mage, unleash your mana!\nFeel free to test skills, then click Finish Trial.");
            continueBtn.setText("Finish Trial ➔");
            refreshTrialButtons();
        } else if (currentHero == mage) {
            if (animationTimer != null) animationTimer.stop();
            game.showScreen("charSelect");
        }
        repaint();
    }

    private void playEffect(Sprite effect, int ex, int ey, int ew, int eh) {
        ActiveEffect newEffect = new ActiveEffect(effect, ex, ey, ew, eh);
        activeEffects.add(newEffect);

        Timer t = new Timer(600, e -> { 
            activeEffects.remove(newEffect); 
            repaint(); 
        });
        t.setRepeats(false);
        t.start();
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
                for (ActiveEffect ae : activeEffects) {
                    if (ae.sprite != null) ae.sprite.update();
                }
            }

            repaint();
        });
        animationTimer.start();
    }

    private void switchToTrial() {
        currentState = State.TRIAL;
        textArea.setText("The trial begins! Witness the power of the heroes.\nFeel free to test skills, then click Next Hero.");
        textArea.setForeground(Color.WHITE);
        buttonPanel.setVisible(true);
        continueBtn.setVisible(true); 

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
                    Timer t = new Timer(i * 400, ev -> playEffect(s, getWidth() - 525, getHeight() - 600, 450, 450));
                    t.setRepeats(false);
                    t.start();
                }
            }
        }

        String result = activeHero.useSkill(skillNum, boss);
        
        activeHero.reduceCooldowns();

        refreshTrialButtons();
        textArea.setText(result);
    }

    private void refreshTrialButtons() {
        for (Component c : buttonPanel.getComponents()) {
            if (c instanceof SkillButton btn) {
                btn.updateButtonState();
            }
        }
    }

    private Character getActiveHero() {
        return currentHero;
    }

    private class SkillButton extends JButton {
        private int skillNum;

        public SkillButton(String text, int skillNum) {
            super(text);
            this.skillNum = skillNum;

            setFocusable(false);
            setFont(smallText);
            setContentAreaFilled(false);
            setBorderPainted(false);
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
                    g2.setFont(smallText);

                    String text = "Cooldown (" + cd + ")";
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
            this.sprite = s; 
            this.x = x; 
            this.y = y; 
            this.w = w; 
            this.h = h;
        }
    }
}