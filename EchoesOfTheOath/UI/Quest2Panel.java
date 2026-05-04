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

    // --- FONT VARIABLES ---
    private Font titleFont;
    private Font headerFont;
    private Font normalFont;
    private Font smallFont;

    private int playerX = 490;
    private final int playerY = 480; 
    private final int playerWidth = 100;
    private final int playerHeight = 150;
    
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private final int moveSpeed = 8;

    private int timeLeft = 15;
    private boolean isGameOver = false;
    private boolean isVictory = false;

    private Timer gameLoopTimer;
    private Timer countdownTimer;
    private List<Arrow> arrows;

    public Quest2Panel(GameWindow game) {
        this.game = game;
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.BLACK);

        // --- LOAD FONTS ---
        this.titleFont = FontManager.getFont("Jersey10-Regular.ttf", 54f);
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
                if (!isGameOver) {
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
        playerX = 490;
        timeLeft = 15;
        isGameOver = false;
        isVictory = false;
        leftPressed = false;
        rightPressed = false;
        arrows = new ArrayList<>();

        if (gameLoopTimer != null) gameLoopTimer.stop();
        if (countdownTimer != null) countdownTimer.stop();

        // Trigger the intro popup to start the quest
        repaint();
        SwingUtilities.invokeLater(() -> showIntroDialog());
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
        if (leftPressed && playerX > 0) playerX -= moveSpeed;
        if (rightPressed && playerX < getWidth() - playerWidth) playerX += moveSpeed;
    }

    private void spawnAndMoveArrows() {
        double baseSpeed = 5.0;
        double speedMultiplier = (15 - timeLeft) * 0.6; 
        double currentArrowSpeed = baseSpeed + speedMultiplier;

        if (Math.random() < 0.05 + ((15 - timeLeft) * 0.005)) {
            arrows.add(new Arrow((int) (Math.random() * (getWidth() - 20)), -50));
        }
        Iterator<Arrow> it = arrows.iterator();
        while (it.hasNext()) {
            Arrow a = it.next();
            a.y += currentArrowSpeed;
            if (a.y > getHeight()) it.remove();
        }
    }

    private void checkCollisions() {
        Rectangle playerHitbox = new Rectangle(playerX + 20, playerY + 20, playerWidth - 40, playerHeight - 20);
        for (Arrow a : arrows) {
            Rectangle arrowHitbox = new Rectangle(a.x, (int)a.y, 15, 40);
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

        // Slight delay so the player can see what hit them before the popup appears
        Timer popupDelay = new Timer(500, e -> showResultDialog(win));
        popupDelay.setRepeats(false);
        popupDelay.start();
    }

    // --- POPUP DIALOG METHODS ---

    private void showIntroDialog() {
        JDialog dialog = new JDialog(game.window, "", true);
        dialog.setUndecorated(true);
        dialog.setBackground(new Color(0, 0, 0, 0));

        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

                g2.setColor(new Color(20, 10, 10, 245)); 
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                
                g2.setColor(new Color(181, 153, 110)); 
                g2.setStroke(new BasicStroke(3));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);

                g2.setColor(new Color(255, 215, 0));
                g2.setFont(headerFont);
                String title = "MINI-ENCOUNTER: THE UNSEEN STALKER";
                FontMetrics fm = g2.getFontMetrics();
                g2.drawString(title, (getWidth() - fm.stringWidth(title)) / 2, 45);
            }
        };

        JTextArea descArea = new JTextArea("Survive 15 seconds of sudden arrow volleys.\n\nUse 'A' and 'D' to move left or right.");
        descArea.setFont(normalFont);
        descArea.setForeground(Color.WHITE);
        descArea.setOpaque(false);
        descArea.setWrapStyleWord(true);
        descArea.setLineWrap(true);
        descArea.setEditable(false);
        descArea.setBounds(40, 80, 520, 100);
        panel.add(descArea);

        JButton startBtn = createCustomButton("Start Encounter");
        startBtn.setBounds(150, 190, 300, 50);
        startBtn.addActionListener(e -> {
            dialog.dispose();
            gameLoopTimer.start();
            countdownTimer.start();
            this.requestFocusInWindow();
        });

        panel.add(startBtn);

        dialog.add(panel);
        dialog.setSize(600, 280);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
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
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

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
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

        if (background != null && background.isLoaded()) {
            g2.drawImage(background.getCurrentFrame(), 0, 0, getWidth(), getHeight(), null);
        }
        if (playerSprite != null && playerSprite.isLoaded()) {
            g2.drawImage(playerSprite.getCurrentFrame(), playerX - 50, playerY - 50, 200, 200, null);
        }

        g2.setColor(new Color(0, 0, 150)); 
        for (Arrow a : arrows) {
            drawCustomArrow(g2, a.x, (int)a.y);
        }

        g2.setFont(headerFont);
        g2.setColor(Color.WHITE);
        g2.drawString("Time: " + Math.max(0, timeLeft), 30, 50);
    }

    private void drawCustomArrow(Graphics2D g2, int x, int y) {
        Path2D.Double arrowShape = new Path2D.Double();
        arrowShape.moveTo(x + 7, y + 40); 
        arrowShape.lineTo(x + 15, y + 25);
        arrowShape.lineTo(x + 10, y + 25);
        arrowShape.lineTo(x + 10, y);     
        arrowShape.lineTo(x + 4, y);      
        arrowShape.lineTo(x + 4, y + 25);
        arrowShape.lineTo(x, y + 25);
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