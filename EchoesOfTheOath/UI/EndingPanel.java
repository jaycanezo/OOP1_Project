package EchoesOfTheOath.UI;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;

public class EndingPanel extends JPanel {
    private GameWindow game;
    private float alpha = 0f;
    private JButton restartBtn;
    private JButton leaderboardBtn; // --- NEW BUTTON ---
    private JButton exitBtn;

    public EndingPanel(GameWindow game) {
        this.game = game;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        // The default FlowLayout will naturally center all items horizontally
        JPanel buttonContainer = new JPanel();
        buttonContainer.setOpaque(false);
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));

        restartBtn = createEndingButton("START NEW JOURNEY");
        restartBtn.addActionListener(e -> {
            game.resetForNewGame(); 
            game.showScreen("start"); 
        });

        // --- NEW LEADERBOARD BUTTON ---
        leaderboardBtn = createEndingButton("LEADERBOARD");
        leaderboardBtn.addActionListener(e -> showLeaderboardDialog());

        exitBtn = createEndingButton("EXIT GAME");
        exitBtn.addActionListener(e -> System.exit(0));

        // Add them in order with equal 20px spacing between them
        buttonContainer.add(restartBtn);
        buttonContainer.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonContainer.add(leaderboardBtn);
        buttonContainer.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonContainer.add(exitBtn);

        this.add(buttonContainer, BorderLayout.SOUTH);
    }

    private JButton createEndingButton(String text) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(40, 40, 40, 200));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.setColor(new Color(181, 153, 110)); 
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
                super.paintComponent(g);
            }
        };
        btn.setFont(FontManager.getFont("Jersey10-Regular.ttf", 26f));
        btn.setForeground(Color.WHITE);
        btn.setPreferredSize(new Dimension(250, 45));
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusable(false);
        return btn;
    }

    public void startEnding() {
        alpha = 0f; 
        
        // Prevent clicking any buttons while the screen is fading in
        restartBtn.setEnabled(false);
        leaderboardBtn.setEnabled(false); // Disable new button
        exitBtn.setEnabled(false);

        Timer timer = new Timer(50, e -> {
            alpha += 0.05f;
            if (alpha >= 1f) {
                alpha = 1f;

                // Re-enable all buttons once fade is complete
                restartBtn.setEnabled(true);
                leaderboardBtn.setEnabled(true); // Enable new button
                exitBtn.setEnabled(true);
                ((Timer)e.getSource()).stop(); 
            }
            repaint();
        });
        timer.start();
    }

    // --- IMPORTED LEADERBOARD METHOD FROM STARTPANEL ---
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        Image bg = new ImageIcon(getClass().getResource("/EchoesOfTheOath/Resources/nation3_bg10.png")).getImage();
        g2d.drawImage(bg, 0, 0, getWidth(), getHeight(), null);

        g2d.setColor(new Color(0, 0, 0, 180)); 
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

        g2d.setColor(Color.WHITE);
        g2d.setFont(FontManager.getFont("Jersey10-Regular.ttf", 36f));
        
        String text = "The journey to remember is over. The journey as Guardian begins.";
        int width = g2d.getFontMetrics().stringWidth(text);
        g2d.drawString(text, (getWidth() - width) / 2, getHeight() / 2);

        g2d.setColor(new Color(255, 215, 0)); 
        g2d.setFont(FontManager.getFont("Jersey10-Regular.ttf", 32f));
        
        String endText = "- THE END -";
        int endWidth = g2d.getFontMetrics().stringWidth(endText);
        g2d.drawString(endText, (getWidth() - endWidth) / 2, (getHeight() / 2) + 220);
    }

    @Override
    protected void paintChildren(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        super.paintChildren(g2);
        
        g2.dispose();
    }
}