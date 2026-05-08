package EchoesOfTheOath.UI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

public class Quest3Panel extends JPanel {
    private GameWindow game;
    private int yearsAbandoned = 0;
    private int clickCount = 0;
    private int corruptionLevel = 0; 
    
    private Font headerFont;
    private Font normalFont;
    private Font smallFont;

    private String currentEcho = "He promised to be the wall. You promised to return.";

    private int[] memoryTriggers = {3, 7, 12}; 
    private String[] memoryPrompts = {
        "You left him to hold the line against the rift. What was your final order?",
        "The cold set in. His mind reached out to yours through the Oath, begging for an answer. How did you respond?",
        "He has forgotten his own name. He only remembers the weight of the shield. What do you say to him now?"
    };
    
    private String[][] dialogueChoices = {
        {"\"Survive.\"", "\"Do not move.\""},
        {"(Stay silent)", "\"I'm sorry.\""},
        {"\"I release you from the Oath.\"", "\"I'm here to save you.\""}
    };
    
    private String[][] choiceResults = {
        {"He survived. But his mind did not.", "He didn't move. He became a monument to your lie."},
        {"The silence deafened him.", "Your apology was just a whisper he couldn't hear."},
        {"The Oath is already broken.", "He doesn't want to be saved. He wants it to end."}
    };

    private Timer animationTimer;
    private ArrayList<Ripple> ripples;

    public Quest3Panel(GameWindow game) {
        this.game = game;
        this.setLayout(null);
        this.setBackground(Color.BLACK); 
        
        this.headerFont = FontManager.getFont("Jersey10-Regular.ttf", 32f);
        this.normalFont = FontManager.getFont("Jersey10-Regular.ttf", 26f); 
        this.smallFont = FontManager.getFont("Jersey10-Regular.ttf", 22f); 

        ripples = new ArrayList<>();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                progressDecay();
                ripples.add(new Ripple(e.getX(), e.getY()));
            }
        });
    }

    public void startNewGame() {
        yearsAbandoned = 0;
        clickCount = 0;
        corruptionLevel = 0;
        currentEcho = "He promised to be the wall. You promised to return.";
        ripples.clear();
        
        if (animationTimer != null && animationTimer.isRunning()) animationTimer.stop();

        animationTimer = new Timer(30, e -> {
            Iterator<Ripple> it = ripples.iterator();
            while (it.hasNext()) {
                Ripple r = it.next();
                r.update();
                if (r.alpha <= 0) it.remove();
            }
            repaint();
        });
        animationTimer.start();
    }

    private void progressDecay() {
        if (clickCount >= 13) return; 
        
        clickCount++;
        yearsAbandoned += (clickCount * 15); 
        corruptionLevel = Math.min(240, corruptionLevel + 20); 
        
        game.getBgm().playSFX("shield_crack.wav"); 
        currentEcho = "Years Abandoned: " + yearsAbandoned;

        for (int i = 0; i < memoryTriggers.length; i++) {
            if (clickCount == memoryTriggers[i]) {
                triggerGuiltChoice(i);
                break;
            }
        }
    }

    private void triggerGuiltChoice(int memoryIndex) {
        JDialog dialog = new JDialog(game.window, "", true);
        dialog.setUndecorated(true);
        dialog.setBackground(new Color(0, 0, 0, 0));

        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(20, 10, 30, 245)); 
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.setColor(new Color(120, 60, 180)); 
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
            }
        };

        JTextArea promptArea = new JTextArea(memoryPrompts[memoryIndex]);
        promptArea.setFont(normalFont);
        promptArea.setForeground(new Color(200, 200, 200));
        promptArea.setOpaque(false);
        promptArea.setWrapStyleWord(true);
        promptArea.setLineWrap(true);
        promptArea.setEditable(false);
        promptArea.setBounds(30, 30, 540, 80);
        panel.add(promptArea);

        JButton btn1 = createSadButton(dialogueChoices[memoryIndex][0]);
        btn1.setBounds(30, 120, 540, 40);
        btn1.addActionListener(e -> {
            dialog.dispose();
            applyConsequence(choiceResults[memoryIndex][0], memoryIndex);
        });

        JButton btn2 = createSadButton(dialogueChoices[memoryIndex][1]);
        btn2.setBounds(30, 170, 540, 40);
        btn2.addActionListener(e -> {
            dialog.dispose();
            applyConsequence(choiceResults[memoryIndex][1], memoryIndex);
        });

        panel.add(btn1);
        panel.add(btn2);

        dialog.add(panel);
        dialog.setSize(600, 250);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void applyConsequence(String resultText, int memoryIndex) {
        currentEcho = resultText;
        corruptionLevel = Math.min(250, corruptionLevel + 30); 

        if (memoryIndex == 2) {
            Timer delay = new Timer(3000, e -> {
                if (animationTimer != null) animationTimer.stop();
                game.autosave();
                game.flashlightBlinkTransition("story");
            });
            delay.setRepeats(false);
            delay.start();
        }
    }

    private JButton createSadButton(String text) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

                if (getModel().isRollover()) g2.setColor(new Color(80, 40, 120)); 
                else g2.setColor(new Color(30, 15, 50)); 
                
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 5, 5);
                g2.setColor(new Color(100, 50, 140));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 5, 5);
                super.paintComponent(g);
            }
        };
        btn.setFont(smallFont);
        btn.setForeground(new Color(220, 200, 240)); 
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

        for (Ripple r : ripples) {
            g2.setColor(new Color(255, 255, 255, (int) r.alpha));
            g2.setStroke(new BasicStroke(r.thickness));
            g2.drawOval((int)(r.x - r.radius), (int)(r.y - r.radius), (int)(r.radius * 2), (int)(r.radius * 2));
            if (r.radius > 20) {
                g2.setColor(new Color(255, 255, 255, (int) (r.alpha * 0.5)));
                g2.drawOval((int)(r.x - r.radius + 10), (int)(r.y - r.radius + 10), (int)((r.radius - 10) * 2), (int)((r.radius - 10) * 2));
            }
        }

        int shieldSize = 200;
        int sx = (getWidth() - shieldSize) / 2;
        int sy = (getHeight() - shieldSize) / 2 - 50;
        int ironColor = Math.max(20, 180 - corruptionLevel); 
        
        g2.setColor(new Color(ironColor, ironColor, ironColor)); 
        g2.fillOval(sx, sy, shieldSize, shieldSize);
        g2.setColor(new Color(40, 40, 40)); 
        g2.setStroke(new BasicStroke(5));
        g2.drawOval(sx, sy, shieldSize, shieldSize);

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));
        if (clickCount > 2) g2.drawLine(sx + 100, sy, sx + 90, sy + 60);
        if (clickCount > 5) g2.drawLine(sx + 90, sy + 60, sx + 140, sy + 120);
        if (clickCount > 8) g2.drawLine(sx + 140, sy + 120, sx + 70, sy + 180);
        if (clickCount > 11) g2.drawLine(sx + 100, sy + 100, sx + 20, sy + 90);

        g2.setColor(new Color(0, 0, 0, corruptionLevel));
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setFont(headerFont);
        FontMetrics fm = g2.getFontMetrics();
        int textX = (getWidth() - fm.stringWidth(currentEcho)) / 2;
        int textY = getHeight() - 150;
        
        g2.setColor(Color.BLACK); 
        g2.drawString(currentEcho, textX + 2, textY + 2);
        g2.setColor(new Color(220, 220, 220)); 
        g2.drawString(currentEcho, textX, textY);

        if (clickCount < 13) {
            String instruction = "Click the Shield to move forward.";
            g2.setFont(smallFont);
            fm = g2.getFontMetrics();
            g2.setColor(new Color(150, 150, 150));
            g2.drawString(instruction, (getWidth() - fm.stringWidth(instruction)) / 2, getHeight() - 50);
        }
    }

    private class Ripple {
        float x, y, radius = 0, maxRadius, alpha, expansionSpeed, thickness;
        public Ripple(float x, float y) {
            this.x = x; this.y = y;
            this.maxRadius = 250; this.alpha = 255;
            this.expansionSpeed = 2.5f; this.thickness = 3.0f;
        }
        public void update() {
            radius += expansionSpeed;
            alpha -= (alpha / ((maxRadius - radius) / expansionSpeed + 1));
            if (alpha < 0 || radius >= maxRadius) alpha = 0;
        }
    }
}