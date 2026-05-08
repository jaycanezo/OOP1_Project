package EchoesOfTheOath.UI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class StartPanel extends JPanel {
    private GameWindow game;
    private Timer animationTimer;
    private JPanel buttonPanel;
    public JButton continueBtn; 

    private BufferedImage bgImage;
    private BufferedImage flowerImg;
    private BufferedImage btnIdleImg;
    private BufferedImage btnHoverImg;

    private ArrayList<Flower> flowers;
    private ArrayList<Orb> orbs; 

    public StartPanel(GameWindow game) {
        this.game = game;
        this.setFocusable(true);
        this.setLayout(null);

        loadImages();
        setupParticleSystem();

        animationTimer = new Timer(16, e -> {
            for (Flower f : flowers) f.update();
            for (Orb o : orbs) o.update();
            repaint();
        });
        animationTimer.start();

        setupMenu();
    }

    private void loadImages() {
        try {
            bgImage = ImageIO.read(getClass().getResourceAsStream("/EchoesOfTheOath/Resources/nation2_bg6.png")); 
            flowerImg = ImageIO.read(getClass().getResourceAsStream("/EchoesOfTheOath/Resources/flower.png"));
            
            btnIdleImg = ImageIO.read(getClass().getResourceAsStream("/EchoesOfTheOath/Resources/button_idle.png"));
            btnHoverImg = ImageIO.read(getClass().getResourceAsStream("/EchoesOfTheOath/Resources/button_hover.png"));
        } catch (Exception e) {
            System.out.println("Could not load some Start Menu images. Falling back to default shapes.");
        }
    }

    private void setupParticleSystem() {
        flowers = new ArrayList<>();
        orbs = new ArrayList<>(); 

        for (int i = 0; i < 40; i++) flowers.add(new Flower());
        for (int i = 0; i < 50; i++) orbs.add(new Orb());
    }

    private void setupMenu() {
        buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(true);
    
        JButton newGameBtn = createMenuButton("New Game", 370, 340);
        continueBtn = createMenuButton("Continue", 370, 415); 
        JButton exitBtn = createMenuButton("Exit", 370, 490);

        refreshMenu(); 

        newGameBtn.addActionListener(e -> {
            game.resetForNewGame(); 
            game.showScreen("intro");
        });

        continueBtn.addActionListener(e -> game.loadGame());
        exitBtn.addActionListener(e -> System.exit(0));

        add(newGameBtn);
        add(continueBtn);
        add(exitBtn);
    }

    public void refreshMenu() {
        File saveFile = new File("autosave.txt");
        if (!saveFile.exists()) {
            continueBtn.setEnabled(false);
        } else {
            continueBtn.setEnabled(true);
        }
    }

    private JButton createMenuButton(String text, int x, int y) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();
                boolean isHovered = getModel().isRollover();
                boolean isEnabled = isEnabled();

                if (btnIdleImg != null && btnHoverImg != null) {
                    if (isHovered && isEnabled) {
                        g2.drawImage(btnHoverImg, 0, 0, w, h, null);
                    } else {
                        g2.drawImage(btnIdleImg, 0, 0, w, h, null);
                    }
                } 
                else { 
                    Color topColor = isHovered && isEnabled ? new Color(60, 60, 60, 180) : new Color(30, 30, 30, 150);
                    Color botColor = isHovered && isEnabled ? new Color(20, 20, 20, 200) : new Color(10, 10, 10, 180);
                    GradientPaint gp = new GradientPaint(0, 0, topColor, 0, h, botColor);
                    g2.setPaint(gp);
                    g2.fillRoundRect(0, 0, w, h, 15, 15);
                }

                if (flowerImg != null && isEnabled && isHovered) {
                    int flowerSize = 22; 
                    int fy = (h - flowerSize) / 2;
                    
                    g2.drawImage(flowerImg, 25, fy, flowerSize, flowerSize, null);
                    g2.drawImage(flowerImg, w - 25 - flowerSize, fy, flowerSize, flowerSize, null);
                }

                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                int textX = (w - fm.stringWidth(text)) / 2;
                int textY = (h - fm.getHeight()) / 2 + fm.getAscent();

                if (isEnabled) {
                    g2.setColor(new Color(20, 5, 25, 180)); 
                    g2.drawString(text, textX + 2, textY + 2);

                    if (isHovered) {
                        g2.setColor(new Color(255, 220, 100, 40)); 
                        g2.drawString(text, textX - 1, textY - 1);
                        g2.drawString(text, textX + 1, textY + 1);
                        g2.drawString(text, textX - 1, textY + 1);
                        g2.drawString(text, textX + 1, textY - 1);
                    }

                    g2.setColor(new Color(255, 250, 230)); 
                } else {
                    g2.setColor(Color.GRAY);
                }
                
                g2.drawString(text, textX, textY);
                g2.dispose();
            }
        };

        btn.setFont(FontManager.getFont("Jersey10-Regular.ttf", 32f));
        btn.setForeground(new Color(0, 0, 0, 0)); 
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusable(false);
        btn.setBounds(x, y, 340, 80); 

        return btn;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (bgImage != null) {
            g2.drawImage(bgImage, 0, 0, getWidth(), getHeight(), null);
        } else {
            g2.setColor(new Color(20, 10, 20)); 
            g2.fillRect(0, 0, getWidth(), getHeight());
        }

        for (Orb o : orbs) {
            g2.setColor(new Color(255, 240, 150, (int)(o.alpha * 255)));
            g2.fillOval((int)o.x, (int)o.y, o.size, o.size);
            
            g2.setColor(new Color(255, 255, 255, (int)(o.alpha * 255)));
            g2.fillOval((int)o.x + 1, (int)o.y + 1, o.size - 2, o.size - 2);
        }

        for (Flower f : flowers) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, f.alpha));
            if (flowerImg != null) {
                g2.drawImage(flowerImg, (int)f.x, (int)f.y, f.size, f.size, null);
            } else {
                g2.setColor(new Color(255, 182, 193)); 
                g2.fillOval((int)f.x, (int)f.y, f.size, f.size);
            }
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

        g2.setColor(new Color(0, 0, 0, 40));
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setFont(FontManager.getFont("Jersey10-Regular.ttf", 120f));
        String title = "Echoes of the Oath";
        FontMetrics fm = g2.getFontMetrics();
        int titleX = (getWidth() - fm.stringWidth(title)) / 2;
        int titleY = 240; 

        g2.setColor(new Color(20, 5, 25, 230)); 
        g2.drawString(title, titleX + 5, titleY + 5);

        g2.setColor(new Color(255, 220, 100, 40));
        g2.drawString(title, titleX - 3, titleY - 3);
        g2.drawString(title, titleX + 3, titleY + 3);
        g2.drawString(title, titleX - 3, titleY + 3);
        g2.drawString(title, titleX + 3, titleY - 3);
        
        g2.setColor(new Color(255, 240, 150, 80));
        g2.drawString(title, titleX - 1, titleY - 1);
        g2.drawString(title, titleX + 1, titleY + 1);

        g2.setColor(new Color(255, 250, 230)); 
        g2.drawString(title, titleX, titleY);
    }

    private class Flower {
        float x, y, speed, sway, swaySpeed, alpha;
        int size;

        Flower() { reset(true); }

        void reset(boolean firstTime) {
            x = (float) (Math.random() * 1080);
            y = firstTime ? (float) (Math.random() * 720) : -50;
            speed = 0.2f + (float) (Math.random() * 0.8f);
            swaySpeed = 0.01f + (float) (Math.random() * 0.02f);
            sway = (float) (Math.random() * Math.PI * 2);
            size = 10 + (int) (Math.random() * 15);
            alpha = 0.3f + (float) (Math.random() * 0.5f);
        }

        void update() {
            y += speed;
            sway += swaySpeed;
            x += Math.sin(sway) * 0.8f;
            if (y > 750) reset(false);
        }
    }

    private class Orb {
        float x, y, speed, pulse, pulseSpeed, alpha;
        int size;

        Orb() { reset(true); }

        void reset(boolean firstTime) {
            x = (float) (Math.random() * 1080);
            y = firstTime ? (float) (Math.random() * 720) : -20;
            speed = 0.1f + (float) (Math.random() * 0.3f); 
            size = 3 + (int) (Math.random() * 4); 
            pulse = (float) (Math.random() * Math.PI * 2); 
            pulseSpeed = 0.02f + (float) (Math.random() * 0.04f);
        }

        void update() {
            y += speed;
            y += Math.sin(pulse) * 0.2f; 
            x += Math.cos(pulse) * 0.3f;
            
            pulse += pulseSpeed;
            alpha = 0.2f + (float) Math.abs(Math.sin(pulse)) * 0.6f;

            if (y > 750) reset(false);
        }
    }
}