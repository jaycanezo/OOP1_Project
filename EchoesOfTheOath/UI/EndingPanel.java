package EchoesOfTheOath.UI;

import java.awt.*;
import javax.swing.*;

public class EndingPanel extends JPanel {
    private GameWindow game;
    private float alpha = 0f;
    private JButton restartBtn;
    private JButton exitBtn;

    public EndingPanel(GameWindow game) {
        this.game = game;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        JPanel buttonContainer = new JPanel();
        buttonContainer.setOpaque(false);
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));

        restartBtn = createEndingButton("START NEW JOURNEY");
        restartBtn.addActionListener(e -> {
            game.resetForNewGame(); 
            game.showScreen("start"); 
        });

        exitBtn = createEndingButton("EXIT GAME");
        exitBtn.addActionListener(e -> System.exit(0));

        buttonContainer.add(restartBtn);
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
                g2.setColor(new Color(181, 153, 110)); // Your signature gold
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
        
        // Prevent clicking while invisible
        restartBtn.setEnabled(false);
        exitBtn.setEnabled(false);

        Timer timer = new Timer(50, e -> {
            alpha += 0.05f;
            if (alpha >= 1f) {
                alpha = 1f;
                // Enable buttons once fully visible
                restartBtn.setEnabled(true);
                exitBtn.setEnabled(true);
                ((Timer)e.getSource()).stop(); 
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // 1. Draw Background (Solid, no fade)
        Image bg = new ImageIcon(getClass().getResource("/EchoesOfTheOath/Resources/nation3_bg10.png")).getImage();
        g2d.drawImage(bg, 0, 0, getWidth(), getHeight(), null);

        // 2. Draw Dark Overlay (Solid, no fade)
        g2d.setColor(new Color(0, 0, 0, 180)); 
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // 3. Apply the Alpha Fade to everything drawn after this point
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

        // Now we can just use solid colors, and Java handles the fading!
        g2d.setColor(Color.WHITE);
        g2d.setFont(FontManager.getFont("Jersey10-Regular.ttf", 36f));
        
        String text = "The journey to remember is over. The journey as Guardian begins.";
        int width = g2d.getFontMetrics().stringWidth(text);
        g2d.drawString(text, (getWidth() - width) / 2, getHeight() / 2);

        g2d.setColor(new Color(255, 215, 0)); // Gold
        g2d.setFont(FontManager.getFont("Jersey10-Regular.ttf", 32f));
        
        String endText = "- THE END -";
        int endWidth = g2d.getFontMetrics().stringWidth(endText);
        g2d.drawString(endText, (getWidth() - endWidth) / 2, (getHeight() / 2) + 220);
    }

    // 4. Override paintChildren to apply the fade to the Swing Buttons!
    @Override
    protected void paintChildren(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        
        // Wrap the button container in the camera fade lens
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        super.paintChildren(g2);
        
        g2.dispose();
    }
}