package EchoesOfTheOath.UI;

import EchoesOfTheOath.Characters.Character;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;

public class Quest2Panel extends JPanel {
    private GameWindow game;
    private Character player;
    private Sprite playerSprite;
    private Sprite background;

    private Font titleFont;
    private Font headerFont;
    private Font normalFont;
    private Font smallFont;

    // --- NEW: Scaled Player Dimensions ---
    private int playerX = 490;
    private int playerY = 480; 
    private int playerWidth = 150;  // Increased size
    private int playerHeight = 225; // Increased size
    private int moveSpeed = 15;     // Increased speed to cross the wider screen
    
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    private int timeLeft = 15;
    private boolean isGameOver = false;
    private boolean isVictory = false;

    private Timer gameLoopTimer;
    private Timer countdownTimer;
    private List<Arrow> arrows;
    private boolean showingInstructions = true;
    private JPanel instrButtonPanel;
    private Timer instrAnimTimer;
    private float instrAnimProgress = 0.0f;

    public Quest2Panel(GameWindow game) {
        this.game = game;
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.BLACK);

        this.titleFont = FontManager.getFont("Jersey10-Regular.ttf", 64f);
        this.headerFont = FontManager.getFont("Jersey10-Regular.ttf", 40f);
        this.normalFont = FontManager.getFont("Jersey10-Regular.ttf", 30f);
        this.smallFont = FontManager.getFont("Jersey10-Regular.ttf", 24f); 

        try {
            background = new Sprite("/EchoesOfTheOath/Resources/nation2_bg1.png", 1920, 1080, 1);
        } catch (Exception e) {
            System.out.println("Could not load quest background.");
        }

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!isGameOver && !showingInstructions) {
                    if (e.getKeyCode() == KeyEvent.VK_A) leftPressed = true;
                    if (e.getKeyCode() == KeyEvent.VK_D) rightPressed = true;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_A) leftPressed = false;
                if (e.getKeyCode() == KeyEvent.VK_D) rightPressed = false;
            }
        });

        setupTimers();
    }

    public void startQuest() {
        this.player = game.getChosenCharacter();
        if (this.player != null) {
            this.playerSprite = player.getIdleSprite();
        }
        
        // Start precisely in the middle of the dynamic screen
        playerX = Math.max(0, (getWidth() - playerWidth) / 2);
        
        timeLeft = 15;
        isGameOver = false;
        isVictory = false;
        leftPressed = false;
        rightPressed = false;
        arrows = new ArrayList<>();

        if (gameLoopTimer != null) gameLoopTimer.stop();
        if (countdownTimer != null) countdownTimer.stop();

        showingInstructions = true;
        instrAnimProgress = 0.0f;

        if (instrButtonPanel != null) this.remove(instrButtonPanel);
        
        instrButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        instrButtonPanel.setOpaque(false);
        
        JButton startBtn = createInstrButton("Begin Quest");
        startBtn.addActionListener(e -> {
            showingInstructions = false;
            instrButtonPanel.setVisible(false);
            
            gameLoopTimer.start();
            countdownTimer.start();
            this.requestFocusInWindow();
            repaint();
        });
        
        instrButtonPanel.add(startBtn);
        this.add(instrButtonPanel);
        instrButtonPanel.setVisible(false);
        
        if (instrAnimTimer != null && instrAnimTimer.isRunning()) instrAnimTimer.stop();
        
        instrAnimTimer = new Timer(15, e -> {
            instrAnimProgress += 0.03f;
            if (instrAnimProgress >= 1.0f) {
                instrAnimProgress = 1.0f;
                instrAnimTimer.stop();
                instrButtonPanel.setVisible(true);
            }
            repaint();
        });
        instrAnimTimer.start();
    }

    private void setupTimers() {
        gameLoopTimer = new Timer(16, e -> {
            if (!isGameOver) { 
                updatePlayer();
                spawnAndMoveArrows();
                checkCollisions();
                if (playerSprite != null) playerSprite.update();
                repaint();
            }
        });
        
        countdownTimer = new Timer(1000, e -> {
            if (!isGameOver) { 
                timeLeft--;
                if (timeLeft <= 0) {
                    endQuest(true); 
                }
            }
        });
    }

    private void updatePlayer() {
        // --- THE FIX: Anchor player dynamically to the bottom! ---
        playerY = getHeight() - 280; 

        if (leftPressed && playerX > 0) playerX -= moveSpeed;
        if (rightPressed && playerX < getWidth() - playerWidth) playerX += moveSpeed;
    }

    private void spawnAndMoveArrows() {
        // --- NEW: Difficulty scales dynamically with screen size ---
        double heightScale = Math.max(1.0, getHeight() / 720.0);
        double widthScale = Math.max(1.0, getWidth() / 1080.0);

        double baseSpeed = 6.0 * heightScale; 
        double speedMultiplier = (15 - timeLeft) * 0.8 * heightScale; 
        double currentArrowSpeed = baseSpeed + speedMultiplier;

        // Multiply the spawn chance by the width scale so wide screens get more arrows!
        double spawnChance = (0.07 + ((15 - timeLeft) * 0.007)) * widthScale;

        if (Math.random() < spawnChance) {
            arrows.add(new Arrow((int) (Math.random() * (getWidth() - 30)), -100));
        }
        
        Iterator<Arrow> it = arrows.iterator();
        while (it.hasNext()) {
            Arrow a = it.next();
            a.y += currentArrowSpeed;
            if (a.y > getHeight() + 100) it.remove();
        }
    }

    private void checkCollisions() {
        Rectangle playerHitbox = new Rectangle(playerX + 30, playerY + 30, playerWidth - 60, playerHeight - 30);
        
        for (Arrow a : arrows) {
            // Arrow Hitbox scaled up to match the new drawing size
            Rectangle arrowHitbox = new Rectangle(a.x, (int)a.y, 30, 80); 
            if (playerHitbox.intersects(arrowHitbox)) {
                endQuest(false); 
                break;
            }
        }
    }

    private void endQuest(boolean win) {
        isGameOver = true;
        isVictory = win;
        gameLoopTimer.stop();
        countdownTimer.stop();
        
        if (player != null) {
            if (win) player.setGold(player.getGold() + 500);
            else player.setGold(Math.max(0, player.getGold() - 500)); 
        }
        
        repaint();

        Timer popupDelay = new Timer(500, e -> showResultDialog(win));
        popupDelay.setRepeats(false);
        popupDelay.start();
    }

    private void showResultDialog(boolean win) {
        JDialog dialog = new JDialog(game.window, "", true);
        dialog.setUndecorated(true);
        dialog.setBackground(new Color(0, 0, 0, 0));

        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                g2.setColor(new Color(20, 10, 10, 245)); 
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                
                g2.setColor(new Color(181, 153, 110)); 
                g2.setStroke(new BasicStroke(3));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);

                g2.setFont(titleFont);
                String title = win ? "QUEST CLEARED!" : "QUEST FAILED!";
                g2.setColor(win ? new Color(175, 238, 171) : new Color(249, 152, 155));
                FontMetrics fm = g2.getFontMetrics();
                g2.drawString(title, (getWidth() - fm.stringWidth(title)) / 2, 65);

                g2.setFont(normalFont);
                g2.setColor(Color.WHITE);
                String subText = win ? "Reward: +500 Gold" : "Penalty: -500 Gold";
                fm = g2.getFontMetrics();
                g2.drawString(subText, (getWidth() - fm.stringWidth(subText)) / 2, 120);
            }
        };

        JButton continueBtn = createCustomButton("Continue");
        continueBtn.setBounds(150, 160, 300, 50);
        continueBtn.addActionListener(e -> {
            dialog.dispose();
            game.autosave();
            game.showScreen("story");
        });

        panel.add(continueBtn);

        dialog.add(panel);
        dialog.setSize(600, 250);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private JButton createCustomButton(String text) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                if (getModel().isRollover()) g2.setColor(new Color(80, 80, 80)); 
                else g2.setColor(new Color(40, 40, 40)); 
                
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
                g2.setColor(new Color(181, 153, 110));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
                super.paintComponent(g);
            }
        };
        btn.setFont(normalFont);
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusable(false);
        return btn;
    }

    private JButton createInstrButton(String text) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) g2.setColor(new Color(60, 60, 60));
                else if (getModel().isRollover()) g2.setColor(new Color(80, 80, 80));
                else g2.setColor(new Color(40, 40, 40));
                
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
                g2.setColor(new Color(150, 200, 255)); 
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
                super.paintComponent(g);
            }
        };
        btn.setFont(normalFont);
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusable(false);
        btn.setPreferredSize(new Dimension(240, 50)); 
        return btn;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (background != null && background.isLoaded()) {
            g2.drawImage(background.getCurrentFrame(), 0, 0, getWidth(), getHeight(), null);
        }

        if (!showingInstructions) {
            if (playerSprite != null && playerSprite.isLoaded()) {
                // Drawing dimension increased from 200x200 to 300x300 for clarity
                g2.drawImage(playerSprite.getCurrentFrame(), playerX - 75, playerY - 75, 300, 300, null);
            }

            g2.setColor(new Color(0, 0, 150)); 
            for (Arrow a : arrows) {
                drawCustomArrow(g2, a.x, (int)a.y);
            }

            g2.setFont(headerFont);
            g2.setColor(Color.WHITE);
            g2.drawString("Time: " + Math.max(0, timeLeft), 30, 50);
        }
        
        if (showingInstructions) {
            int bgAlpha = (int)(160 * instrAnimProgress); 
            g2.setColor(new Color(0, 0, 0, bgAlpha)); 
            g2.fillRect(0, 0, getWidth(), getHeight());
            
            int bannerHeight = 220;
            int bannerY = (getHeight() - bannerHeight) / 2 - 40; 
            
            if (instrButtonPanel != null) {
                instrButtonPanel.setBounds(0, bannerY + bannerHeight + 40, getWidth(), 100); 
            }
            
            float wipeProgress = Math.min(1.0f, instrAnimProgress * 1.5f); 
            int currentBannerWidth = (int)(getWidth() * wipeProgress);
            int bannerX = (getWidth() - currentBannerWidth) / 2; 
            
            if (currentBannerWidth > 0) {
                Color leftColor = new Color(10, 60, 120, 230);
                Color rightColor = new Color(10, 100, 100, 230);

                GradientPaint gp = new GradientPaint(bannerX, bannerY, leftColor, bannerX + currentBannerWidth, bannerY, rightColor);
                g2.setPaint(gp);
                g2.fillRect(bannerX, bannerY, currentBannerWidth, bannerHeight);
            
                g2.setColor(new Color(255, 255, 255, 180));
                g2.fillRect(bannerX, bannerY, currentBannerWidth, 3); 
                g2.fillRect(bannerX, bannerY + bannerHeight - 3, currentBannerWidth, 3); 
            }
            
            float textAlpha = Math.max(0.0f, (instrAnimProgress - 0.3f) / 0.7f); 
            
            if (textAlpha > 0) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, textAlpha));
                
                String title = "Q U E S T   2";
                g2.setFont(titleFont);
                FontMetrics fmTitle = g2.getFontMetrics();
                int titleX = (getWidth() - fmTitle.stringWidth(title)) / 2;
                int titleY = bannerY + 80;
                
                g2.setColor(new Color(0, 0, 0, 120)); 
                g2.drawString(title, titleX + 4, titleY + 4); 
                g2.setColor(Color.WHITE);
                g2.drawString(title, titleX, titleY);
                
                g2.setFont(headerFont);
                String objective = "Objective: Survive the Unseen Stalker";
                int objX = (getWidth() - g2.getFontMetrics().stringWidth(objective)) / 2;
                g2.setColor(new Color(200, 230, 255)); 
                g2.drawString(objective, objX, titleY + 50);
                
                g2.setFont(smallFont);
                String mechanic = "Use 'A' and 'D' to dodge the arrows for 15 seconds!";
                int mechX = (getWidth() - g2.getFontMetrics().stringWidth(mechanic)) / 2;
                g2.setColor(new Color(150, 180, 200));
                g2.drawString(mechanic, mechX, titleY + 90);
                
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            }
        }
    }

    private void drawCustomArrow(Graphics2D g2, int x, int y) {
        // --- NEW: Arrow scale doubled in size ---
        Path2D.Double arrowShape = new Path2D.Double();
        arrowShape.moveTo(x + 15, y + 80); 
        arrowShape.lineTo(x + 30, y + 50);
        arrowShape.lineTo(x + 20, y + 50);
        arrowShape.lineTo(x + 20, y);     
        arrowShape.lineTo(x + 10, y);      
        arrowShape.lineTo(x + 10, y + 50);
        arrowShape.lineTo(x, y + 50);
        arrowShape.closePath();
        
        g2.fill(arrowShape);
        g2.setColor(Color.WHITE);
        g2.draw(arrowShape);
        g2.setColor(new Color(0, 0, 150)); 
    }

    private class Arrow {
        int x;
        double y;
        public Arrow(int x, double y) { this.x = x; this.y = y; }
    }
}