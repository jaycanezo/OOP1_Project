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
    private boolean isStarting = false;
    private Timer introTimer;
    private List<Arrow> arrows;

    public Quest2Panel(GameWindow game) {
        this.game = game;
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.BLACK);

        try {
            background = new Sprite("/EchoesOfTheOath/Resources/nation2_bg1.png", 1920, 1080, 1);
        } catch (Exception e) {
            System.out.println("Could not load quest background.");
        }

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_A) 
                    leftPressed = true;

                if (e.getKeyCode() == KeyEvent.VK_D) 
                    rightPressed = true;
                
                if (isGameOver && (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE)) {
                    game.autosave();
                    game.showScreen("story");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_A) 
                    leftPressed = false;

                if (e.getKeyCode() == KeyEvent.VK_D) 
                    rightPressed = false;
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
        isStarting = true; 
        leftPressed = false;
        rightPressed = false;
        arrows = new ArrayList<>();

        if (gameLoopTimer != null) 
            gameLoopTimer.stop();

        if (countdownTimer != null) 
            countdownTimer.stop();

        if (introTimer != null) 
            introTimer.stop();

        introTimer.start(); 
        this.requestFocusInWindow();
        repaint();
    }

    private void setupTimers() {
        gameLoopTimer = new Timer(16, e -> {
            if (!isGameOver && !isStarting) { 
                updatePlayer();
                spawnAndMoveArrows();
                checkCollisions();
                if (playerSprite != null) playerSprite.update();
                repaint();
            }
        });

        countdownTimer = new Timer(1000, e -> {
            if (!isGameOver && !isStarting) { 
                timeLeft--;

                if (timeLeft <= 0) {
                    endQuest(true); 
                }
            }
        });

        introTimer = new Timer(3000, e -> {
            isStarting = false;
            gameLoopTimer.start();
            countdownTimer.start();
            repaint();
        });
        introTimer.setRepeats(false); 
    }

    private void updatePlayer() {
        if (leftPressed && playerX > 0) {
            playerX -= moveSpeed;
        }

        if (rightPressed && playerX < getWidth() - playerWidth) {
            playerX += moveSpeed;
        }
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
            if (a.y > getHeight()) {
                it.remove();
            }
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
            if (win) {
                player.setGold(player.getGold() + 500);
            } else {
                player.setGold(Math.max(0, player.getGold() - 500)); 
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (background != null && background.isLoaded()) {
            g2.drawImage(background.getCurrentFrame(), 0, 0, getWidth(), getHeight(), null);
        }

        if (playerSprite != null && playerSprite.isLoaded()) {
            g2.drawImage(playerSprite.getCurrentFrame(), playerX - 50, playerY - 50, 200, 200, null);
        }

        if (isStarting) {
            drawIntroOverlay(g2);
        } else {
            g2.setColor(new Color(0, 0, 150)); 
            for (Arrow a : arrows) {
                drawCustomArrow(g2, a.x, (int)a.y);
            }

            g2.setFont(new Font("Georgia", Font.BOLD, 36));
            g2.setColor(Color.WHITE);
            g2.drawString("Time: " + Math.max(0, timeLeft), 30, 50);

            if (isGameOver) {
                drawResultOverlay(g2);
            }
        }
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

    private void drawResultOverlay(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 180));
        g2.fillRect(0, 0, getWidth(), getHeight());

        int boxW = 600, boxH = 250;
        int boxX = (getWidth() - boxW) / 2;
        int boxY = (getHeight() - boxH) / 2;

        g2.setColor(new Color(30, 30, 30, 240));
        g2.fillRoundRect(boxX, boxY, boxW, boxH, 20, 20);
        
        g2.setColor(new Color(181, 153, 110));
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(boxX, boxY, boxW, boxH, 20, 20);

        g2.setFont(new Font("Georgia", Font.BOLD, 48));
        String title = isVictory ? "QUEST CLEARED!" : "QUEST FAILED!";
        g2.setColor(isVictory ? new Color(175, 238, 171) : new Color(249, 152, 155));
        
        FontMetrics fm = g2.getFontMetrics();
        g2.drawString(title, boxX + (boxW - fm.stringWidth(title)) / 2, boxY + 70);

        g2.setFont(new Font("Georgia", Font.PLAIN, 28));
        g2.setColor(Color.WHITE);
        String subText = isVictory ? "Reward: +500 Gold" : "Penalty: -500 Gold";
        fm = g2.getFontMetrics();
        g2.drawString(subText, boxX + (boxW - fm.stringWidth(subText)) / 2, boxY + 130);

        g2.setFont(new Font("Georgia", Font.ITALIC, 20));
        g2.setColor(Color.LIGHT_GRAY);
        String contText = "Press ENTER or SPACE to continue";
        fm = g2.getFontMetrics();
        g2.drawString(contText, boxX + (boxW - fm.stringWidth(contText)) / 2, boxY + 200);
    }

    private class Arrow {
        int x;
        double y;

        public Arrow(int x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private void drawIntroOverlay(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, getWidth(), getHeight());

        int boxW = 850, boxH = 200;
        int boxX = (getWidth() - boxW) / 2;
        int boxY = (getHeight() - boxH) / 2;

        g2.setColor(new Color(30, 30, 30, 240));
        g2.fillRoundRect(boxX, boxY, boxW, boxH, 20, 20);

        g2.setColor(new Color(181, 153, 110)); 
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(boxX, boxY, boxW, boxH, 20, 20);

        g2.setFont(new Font("Georgia", Font.BOLD, 26));
        g2.setColor(new Color(255, 215, 0)); 
        String title = "MINI-ENCOUNTER: \"THE UNSEEN STALKER\"";
        FontMetrics fm = g2.getFontMetrics();
        g2.drawString(title, boxX + (boxW - fm.stringWidth(title)) / 2, boxY + 60);

        g2.setFont(new Font("Georgia", Font.PLAIN, 22));
        g2.setColor(Color.WHITE);
        String desc1 = "The player must survive 15 seconds of sudden arrow volleys.";
        String desc2 = "Use 'A' and 'D' to move left or right.";
        
        fm = g2.getFontMetrics();
        g2.drawString(desc1, boxX + (boxW - fm.stringWidth(desc1)) / 2, boxY + 110);
        g2.drawString(desc2, boxX + (boxW - fm.stringWidth(desc2)) / 2, boxY + 150);
    }
}