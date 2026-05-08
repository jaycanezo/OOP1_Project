package EchoesOfTheOath.UI;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Quest4Panel extends JPanel {
    private GameWindow game;
    private Timer gameTimer;
    
    private Font titleFont;
    private Font headerFont;
    private Font normalFont;
    private Font smallFont;

    private int mouseX = -100, mouseY = -100;
    private int sanity = 1000;
    private final int MAX_SANITY = 1000;
    private int shardsFound = 0;
    
    private boolean isJumpscareActive = false;
    private boolean isPaused = false;
    private Random rand = new Random();
    private float pulseTick = 0;

    private ArrayList<Rectangle> shards;
    private ArrayList<Rectangle> phantoms;
    
    private Sprite backgroundSprite;
    private Sprite jumpscareSprite;
    private Sprite eyeballTrapSprite;
    
    private final int INSTRUCTION_STATE = 0;
    private final int PLAY_STATE = 1;
    private int gameState = INSTRUCTION_STATE;

    private String[] shardNames = {"Fragment of Truth", "Fragment of Fear", "Fragment of the Void"};
    private String[] ghostQuestions = {
        "\"You built a cage for the dark, but locked yourself inside. Was it worth it?\"",
        "\"Elarion is not a demon. He is just the part of you that stopped fighting.\"",
        "\"If you kill him, you kill the only thing that remembers your original name.\""
    };
    private String[][] dialogueChoices = {
        {"It was the only way.", "I should have let the world burn."},
        {"I haven't stopped fighting.", "Maybe I am just tired."},
        {"My name doesn't matter anymore.", "I will carry the burden alone."}
    };
    private int[] correctChoices = {0, 0, 0}; 

    public Quest4Panel(GameWindow game) {
        this.game = game;
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        this.titleFont = FontManager.getFont("Jersey10-Regular.ttf", 48f);  
        this.headerFont = FontManager.getFont("Jersey10-Regular.ttf", 30f); 
        this.normalFont = FontManager.getFont("Jersey10-Regular.ttf", 26f); 
        this.smallFont = FontManager.getFont("Jersey10-Regular.ttf", 22f);  

        backgroundSprite = new Sprite("/EchoesOfTheOath/Resources/quest4_bg.png", 1080, 720, 1);
        jumpscareSprite = new Sprite("/EchoesOfTheOath/Resources/void_jumpscare.png", 700, 600, 1);
        eyeballTrapSprite = new Sprite("/EchoesOfTheOath/Resources/eyeball_trap.png", 80, 80, 1);
        
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (!isPaused) {
                    mouseX = e.getX();
                    mouseY = e.getY();
                    checkCollisions();
                    repaint();
                }
            }
        });
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!isPaused) handleInteraction(e.getX(), e.getY());
            }
        });

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (gameState == INSTRUCTION_STATE && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    gameState = PLAY_STATE; 
                    if (gameTimer != null) gameTimer.start(); 
                }
            }
        });
    }

    public void startNewGame() {
        this.requestFocusInWindow();
        this.gameState = INSTRUCTION_STATE;
        sanity = MAX_SANITY;
        shardsFound = 0;
        isPaused = false;
        mouseX = getWidth() / 2;
        mouseY = getHeight() / 2;
        
        generateRoom();
        
        if (gameTimer != null && gameTimer.isRunning()) gameTimer.stop();
        
        gameTimer = new Timer(50, e -> {
            if (!isPaused) {
                pulseTick += 0.15f; 
                sanity -= 2; 
                if (isJumpscareActive) sanity -= 20; 
                if (sanity <= 0) triggerDefeat();
                repaint();
            }
        });
    }

    private void generateRoom() {
        shards = new ArrayList<>();
        phantoms = new ArrayList<>();
        for (int i = 0; i < 3; i++) shards.add(new Rectangle(100 + rand.nextInt(800), 100 + rand.nextInt(500), 60, 60));
        for (int i = 0; i < 20; i++) phantoms.add(new Rectangle(50 + rand.nextInt(900), 50 + rand.nextInt(600), 80, 80));
    }

    private void checkCollisions() {
        boolean touchingPhantom = false;
        for (Rectangle t : phantoms) {
            double distance = Math.hypot(mouseX - (t.x + t.width/2), mouseY - (t.y + t.height/2));
            if (distance < 45) { touchingPhantom = true; break; }
        }

        if (touchingPhantom) {
            if (!isJumpscareActive) { 
                isJumpscareActive = true;
                game.getBgm().playSFX("jumpscare.wav"); 
            }
        } else {
            isJumpscareActive = false;
        }
    }

    private void handleInteraction(int x, int y) {
        for (int i = 0; i < shards.size(); i++) {
            if (shards.get(i).contains(x, y)) {
                triggerDialogueChoice(i);
                return;
            }
        }
    }

    private void triggerDialogueChoice(int shardIndex) {
        isPaused = true;
        Rectangle foundShard = shards.get(shardIndex);
        
        JDialog dialog = new JDialog(game.window, "", true);
        dialog.setUndecorated(true);
        dialog.setBackground(new Color(0, 0, 0, 0));

        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

                g2.setColor(new Color(15, 5, 5, 245)); 
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(new Color(150, 0, 0));
                g2.setStroke(new BasicStroke(3));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);

                g2.setColor(new Color(220, 20, 40)); 
                g2.setFont(headerFont);
                g2.drawString(shardNames[shardIndex], 20, 40);
            }
        };

        JTextArea questionArea = new JTextArea(ghostQuestions[shardIndex]);
        questionArea.setFont(smallFont);
        questionArea.setForeground(Color.WHITE);
        questionArea.setOpaque(false);
        questionArea.setWrapStyleWord(true);
        questionArea.setLineWrap(true);
        questionArea.setEditable(false);
        questionArea.setBounds(20, 60, 460, 60);
        panel.add(questionArea);

        JButton btn1 = createChoiceButton(dialogueChoices[shardIndex][0]);
        btn1.setBounds(20, 130, 460, 40);
        btn1.addActionListener(e -> {
            dialog.dispose();
            resolveChoice(shardIndex, 0, foundShard);
        });

        JButton btn2 = createChoiceButton(dialogueChoices[shardIndex][1]);
        btn2.setBounds(20, 180, 460, 40);
        btn2.addActionListener(e -> {
            dialog.dispose();
            resolveChoice(shardIndex, 1, foundShard);
        });

        panel.add(btn1);
        panel.add(btn2);

        dialog.add(panel);
        dialog.setSize(500, 250);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void resolveChoice(int shardIndex, int chosenIndex, Rectangle shardObj) {
        if (chosenIndex == correctChoices[shardIndex]) {
            shards.remove(shardObj);
            shardsFound++;
            sanity = Math.min(MAX_SANITY, sanity + 300); 
            if (shardsFound >= 3) triggerVictory();
            else isPaused = false;
        } else {
            sanity -= 400; 
            if (sanity <= 0) triggerDefeat();
            else isPaused = false;
        }
    }

    private void triggerDefeat() {
        gameTimer.stop();
        isPaused = true;
        Timer delay = new Timer(500, e -> {
            BufferedImage capture = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            this.paint(capture.getGraphics());
            game.showResultScreen(false, capture); 
        });
        delay.setRepeats(false);
        delay.start();
    }

    private void triggerVictory() {
        gameTimer.stop();
        isPaused = true;
        Timer delay = new Timer(1000, e -> {
            game.autosave();
            game.flashlightBlinkTransition("story");
        });
        delay.setRepeats(false);
        delay.start();
    }

    private JButton createChoiceButton(String text) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

                if (getModel().isRollover()) g2.setColor(new Color(100, 10, 10));
                else g2.setColor(new Color(30, 10, 10)); 
                
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
                g2.setColor(new Color(150, 40, 40));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
                super.paintComponent(g);
            }
        };
        btn.setFont(smallFont);
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusable(false);
        return btn;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

        if (gameState == INSTRUCTION_STATE) {
            drawInstructions(g2);
        } else if (gameState == PLAY_STATE) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (backgroundSprite != null && backgroundSprite.isLoaded()) {
                g2.drawImage(backgroundSprite.getCurrentFrame(), 0, 0, getWidth(), getHeight(), null);
            } else {
                g2.setColor(new Color(10, 0, 0)); 
                g2.fillRect(0, 0, getWidth(), getHeight());
            }

            for (Rectangle s : shards) {
                int centerX = s.x + s.width / 2;
                int centerY = s.y + s.height / 2;

                float pulse = (float) Math.abs(Math.sin(pulseTick));
                int currentRadius = 25 + (int)(15 * pulse);

                float[] dist = {0.0f, 0.3f, 1.0f};
                Color[] colors = {
                    Color.WHITE,                         
                    new Color(255, 200, 200, 180),       
                    new Color(255, 100, 100, 0)          
                };

                RadialGradientPaint rgp = new RadialGradientPaint(
                    new Point(centerX, centerY), currentRadius, dist, colors
                );
                
                g2.setPaint(rgp);
                g2.fillOval(centerX - currentRadius, centerY - currentRadius, currentRadius * 2, currentRadius * 2);
            }

            if (!isJumpscareActive) {
                for (Rectangle t : phantoms) {
                    double dist = Math.hypot(mouseX - (t.x + t.width/2), mouseY - (t.y + t.height/2));
                    if (dist < 110) {
                        if (eyeballTrapSprite != null && eyeballTrapSprite.isLoaded()) {
                            g2.drawImage(eyeballTrapSprite.getCurrentFrame(), t.x, t.y, t.width, t.height, null);
                        } else {
                            g2.setColor(Color.RED);
                            g2.fillOval(t.x, t.y, t.width, t.height);
                        }
                    }
                }
            }

            if (!isJumpscareActive) {
                Area darkOverlay = new Area(new Rectangle(0, 0, getWidth(), getHeight()));
                int lightRadius = 90; 
                if (sanity < 400) lightRadius = 60;
                if (sanity < 150) lightRadius = 40;

                Ellipse2D lightCircle = new Ellipse2D.Double(mouseX - lightRadius, mouseY - lightRadius, lightRadius * 2, lightRadius * 2);
                darkOverlay.subtract(new Area(lightCircle));
                
                g2.setColor(new Color(0, 0, 0, 252)); 
                g2.fill(darkOverlay);
                
                g2.setColor(new Color(255, 240, 180, 35)); 
                g2.fill(lightCircle);
            }

            if (isJumpscareActive) {
                g2.setColor(new Color(0, 0, 0, 220));
                g2.fillRect(0, 0, getWidth(), getHeight());

                if (jumpscareSprite != null && jumpscareSprite.isLoaded()) {
                    int mWidth = 700;
                    int mHeight = 600;
                    int mX = (getWidth() - mWidth) / 2;
                    int mY = getHeight() - mHeight + 30; 
                    
                    g2.drawImage(jumpscareSprite.getCurrentFrame(), mX, mY, mWidth, mHeight, null);
                }
            }

            g2.setColor(Color.WHITE);
            g2.setFont(normalFont);
            g2.drawString("WILLPOWER: " + sanity, 30, 40);
            g2.drawString("SHARDS FOUND: " + shardsFound + "/3", 30, 70);
            
            g2.setColor(Color.DARK_GRAY);
            g2.fillRect(30, 90, 300, 20);

            if (sanity > 500) g2.setColor(new Color(200, 50, 50)); 
            else if (sanity > 200) g2.setColor(new Color(255, 140, 0)); 
            else g2.setColor(new Color(139, 0, 0));
            
            g2.fillRect(30, 90, (int)(300 * ((double)sanity / MAX_SANITY)), 20);
            g2.setColor(Color.WHITE);
            g2.drawRect(30, 90, 300, 20);
        }
    }

    private void drawInstructions(Graphics2D g2) {
        g2.setColor(new Color(10, 10, 10, 240)); 
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(new Color(181, 153, 110)); 
        g2.setFont(titleFont);

        FontMetrics fm = g2.getFontMetrics();
        String title = "Trial of the Shadows";
        g2.drawString(title, (getWidth() - fm.stringWidth(title)) / 2, 250);

        g2.setColor(Color.WHITE);
        g2.setFont(headerFont);
        fm = g2.getFontMetrics();
        String desc = "The darkness feeds on your Willpower.";
        g2.drawString(desc, (getWidth() - fm.stringWidth(desc)) / 2, 320);
        
        g2.setFont(normalFont);
        g2.setColor(new Color(200, 200, 200));
        fm = g2.getFontMetrics();
        String goal = "Goal: Find all 3 hidden shards before your mind breaks.";
        String controls = "Controls: Move your MOUSE to aim your flashlight.";
        g2.drawString(goal, (getWidth() - fm.stringWidth(goal)) / 2, 380);
        g2.drawString(controls, (getWidth() - fm.stringWidth(controls)) / 2, 415);
        
        g2.setColor(new Color(255, 100, 100)); 
        String warn = "Warning: Beware what lurks in the shadows...";
        g2.drawString(warn, (getWidth() - fm.stringWidth(warn)) / 2, 460);

        if (System.currentTimeMillis() % 1000 < 500) { 
            g2.setColor(Color.YELLOW);
            g2.setFont(headerFont);
            fm = g2.getFontMetrics();
            String start = "Press [ ENTER ] to Descend";
            g2.drawString(start, (getWidth() - fm.stringWidth(start)) / 2, 550);
        }
    }
}