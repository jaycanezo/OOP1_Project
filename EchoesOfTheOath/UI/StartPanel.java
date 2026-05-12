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
    
    public JButton continueBtn; 
    private JButton newGameBtn;
    private JButton exitBtn;
    private JButton leaderboardBtn;

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

        for (int i = 0; i < 80; i++) flowers.add(new Flower());
        for (int i = 0; i < 100; i++) orbs.add(new Orb());
    }

    private void setupMenu() {
        newGameBtn = createMenuButton("New Game");
        continueBtn = createMenuButton("Continue"); 
        leaderboardBtn = createMenuButton("Leaderboard"); 
        exitBtn = createMenuButton("Exit");

        newGameBtn.addActionListener(e -> game.resetForNewGame());
        continueBtn.addActionListener(e -> game.loadGame());
        leaderboardBtn.addActionListener(e -> showLeaderboardDialog());
        exitBtn.addActionListener(e -> {
            game.autosave();
            System.exit(0);
        });

        add(newGameBtn);
        add(continueBtn);
        add(leaderboardBtn);
        add(exitBtn);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                int w = getWidth();
                int h = getHeight();
                int centerX = w / 2;
                int startY = h / 2 - 20; 

                int btnWidth = 340;
                int btnHeight = 80;

                if (newGameBtn != null) newGameBtn.setBounds(centerX - btnWidth / 2, startY, btnWidth, btnHeight);
                if (continueBtn != null) continueBtn.setBounds(centerX - btnWidth / 2, startY + 80, btnWidth, btnHeight);
                if (leaderboardBtn != null) leaderboardBtn.setBounds(centerX - btnWidth / 2, startY + 160, btnWidth, btnHeight);
                if (exitBtn != null) exitBtn.setBounds(centerX - btnWidth / 2, startY + 240, btnWidth, btnHeight);

                if (flowers != null) {
                    for (Flower f : flowers) {
                        f.x = (float) (Math.random() * Math.max(1, w));
                    }
                }
                
                if (orbs != null) {
                    for (Orb o : orbs) {
                        o.x = (float) (Math.random() * Math.max(1, w));
                    }
                }
            }
        });
    }

    public void refreshMenu() {
        File saveFile = new File("autosave.txt");
        if (!saveFile.exists()) {
            continueBtn.setEnabled(false);
        } else {
            continueBtn.setEnabled(true);
        }
    }

    private void showLeaderboardDialog() {
        JDialog dialog = new JDialog(game.window, "", true);
        dialog.setUndecorated(true);
        dialog.setBackground(new Color(0, 0, 0, 0));

        ArrayList<String[]> records = new ArrayList<>();
        File file = new File("leaderboard.txt");
        if (file.exists()) {
            try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length >= 3) records.add(data);
                }
            } catch (Exception e) { e.printStackTrace(); }
        }

        records.sort((a, b) -> {
            try {
                double goldA = Double.parseDouble(a[1]);
                double goldB = Double.parseDouble(b[1]);
                return Double.compare(goldB, goldA); 
            } catch (NumberFormatException e) { return 0; }
        });

        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                g2.setColor(new Color(15, 10, 20, 245));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                
                g2.setColor(new Color(181, 153, 110));
                g2.setStroke(new BasicStroke(3));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 25, 25);

                int bannerY = 20;
                GradientPaint gp = new GradientPaint(0, bannerY, new Color(255, 220, 0, 150), getWidth(), bannerY, new Color(255, 140, 0, 150));
                g2.setPaint(gp);
                g2.fillRect(2, bannerY, getWidth() - 4, 60);

                Font titleFont = FontManager.getFont("Jersey10-Regular.ttf", 48f);
                g2.setFont(titleFont);
                FontMetrics fm = g2.getFontMetrics();
                String title = "HALL OF FAME";
                int titleX = (getWidth() - fm.stringWidth(title)) / 2;
                
                g2.setColor(new Color(0, 0, 0, 150)); 
                g2.drawString(title, titleX + 3, bannerY + 45 + 3);
                g2.setColor(Color.WHITE); 
                g2.drawString(title, titleX, bannerY + 45);

                Font headerFont = FontManager.getFont("Jersey10-Regular.ttf", 28f);
                g2.setFont(headerFont);
                g2.setColor(new Color(181, 153, 110));
                g2.drawString("RANK", 50, 120);
                g2.drawString("NAME", 150, 120);
                g2.drawString("GOLD", 350, 120);
                g2.drawString("DEATHS", 500, 120);

                g2.drawLine(40, 130, getWidth() - 40, 130);

                Font normalFont = FontManager.getFont("Jersey10-Regular.ttf", 26f);
                g2.setFont(normalFont);
                int yPos = 170;
                
                if (records.isEmpty()) {
                    g2.setColor(Color.GRAY);
                    g2.drawString("No heroes have conquered the Oath yet...", 120, 200);
                } else {
                    for (int i = 0; i < Math.min(5, records.size()); i++) {
                        String[] p = records.get(i);
                        
                        if (i == 0) g2.setColor(new Color(255, 215, 0)); 
                        else if (i == 1) g2.setColor(new Color(192, 192, 192)); 
                        else if (i == 2) g2.setColor(new Color(205, 127, 50)); 
                        else g2.setColor(Color.WHITE);

                        g2.drawString("#" + (i + 1), 60, yPos);
                        g2.drawString(p[0], 150, yPos); // Name
                        g2.drawString(p[1] + "G", 350, yPos); // Gold
                        
                        g2.setColor(new Color(249, 152, 155)); // Red for deaths
                        g2.drawString(p[2], 520, yPos); // Deaths
                        
                        yPos += 40;
                    }
                }
            }
        };

        JButton closeBtn = new JButton("Close") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isRollover()) g2.setColor(new Color(80, 80, 80));
                else g2.setColor(new Color(40, 40, 40));
                
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
                g2.setColor(new Color(181, 153, 110));
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
                super.paintComponent(g);
            }
        };
        closeBtn.setFont(FontManager.getFont("Jersey10-Regular.ttf", 24f));
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setContentAreaFilled(false);
        closeBtn.setBorderPainted(false);
        closeBtn.setFocusable(false);
        closeBtn.setBounds(250, 400, 140, 40); 
        closeBtn.addActionListener(e -> dialog.dispose());

        panel.add(closeBtn);
        dialog.add(panel);
        dialog.setSize(640, 480); 
        dialog.setLocationRelativeTo(this); 
        dialog.setVisible(true); 
    }

    private JButton createMenuButton(String text) {
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

        String title = "ECHOES OF THE\nOATH";
        String[] lines = title.split("\n");
        
        int titleY = Math.max(100, getHeight() / 2 - 200); 

        for (String line : lines) {
            
            if (line.equals("OATH")) {
                g2.setFont(FontManager.getFont("Jersey10-Regular.ttf", 300f)); 
            } else {
                g2.setFont(FontManager.getFont("Jersey10-Regular.ttf", 100f));
            }
            
            FontMetrics fm = g2.getFontMetrics();
            int titleX = (getWidth() - fm.stringWidth(line)) / 2; 

            g2.setColor(new Color(20, 5, 25, 230)); 
            g2.drawString(line, titleX + 5, titleY + 5);

            g2.setColor(new Color(255, 220, 100, 40));
            g2.drawString(line, titleX - 3, titleY - 3);
            g2.drawString(line, titleX + 3, titleY + 3);
            g2.drawString(line, titleX - 3, titleY + 3);
            g2.drawString(line, titleX + 3, titleY - 3);
            
            g2.setColor(new Color(255, 240, 150, 80));
            g2.drawString(line, titleX - 1, titleY - 1);
            g2.drawString(line, titleX + 1, titleY + 1);

            g2.setColor(new Color(255, 250, 230)); 
            g2.drawString(line, titleX, titleY);
            
            titleY += fm.getHeight() + 70; 
        }
    }

    private class Flower {
        float x, y, speed, sway, swaySpeed, alpha;
        int size;

        Flower() { reset(true); }

        void reset(boolean firstTime) {
            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            int w = Math.max(StartPanel.this.getWidth(), screen.width);
            int h = Math.max(StartPanel.this.getHeight(), screen.height);

            x = (float) (Math.random() * w);
            
            if (firstTime) {
                // Scatter all over the screen when the game first launches
                y = (float) (Math.random() * h);
            } else {
                // Randomize respawn height so they NEVER fall in clumps
                y = -50 - (float)(Math.random() * 600); 
            }
            
            speed = 0.2f + (float) (Math.random() * 0.9f);
            swaySpeed = 0.01f + (float) (Math.random() * 0.02f);
            sway = (float) (Math.random() * Math.PI * 2);
            
            // Slightly larger flowers (15 to 30 pixels)
            size = 19 + (int) (Math.random() * 15); 
            alpha = 0.3f + (float) (Math.random() * 0.5f);
        }

        void update() {
            y += speed; // Flowers fall DOWN
            sway += swaySpeed;
            x += Math.sin(sway) * 0.8f;
            
            // If it falls below the bottom of the screen, reset it at the top
            if (y > StartPanel.this.getHeight() + 50) reset(false);
        }
    }

    private class Orb {
        float x, y, speed, pulse, pulseSpeed, alpha;
        int size;

        Orb() { reset(true); }

        void reset(boolean firstTime) {
            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            int w = Math.max(StartPanel.this.getWidth(), screen.width);
            int h = Math.max(StartPanel.this.getHeight(), screen.height);

            x = (float) (Math.random() * w);
            
            if (firstTime) {
                // Scatter all over the screen when the game first launches
                y = (float) (Math.random() * h);
            } else {
                // Orbs float UP, so they must spawn randomly BELOW the screen
                y = h + 50 + (float)(Math.random() * 400);
            }
            
            speed = 0.1f + (float) (Math.random() * 0.3f);
            size = 4 + (int) (Math.random() * 4); 
            pulse = (float) (Math.random() * Math.PI * 2); 
            pulseSpeed = 0.008f + (float) (Math.random() * 0.015f); 
        }

        void update() {
            // THE FIX: Minus equals makes them float UPWARDS!
            y -= speed; 
            x += Math.cos(pulse) * 0.2f; 
            
            pulse += pulseSpeed;
            alpha = (float) Math.abs(Math.sin(pulse)) * 0.6f;

            // When they float past the TOP of the screen, reset them at the BOTTOM
            if (y < -50) reset(false);
        }
    }
}