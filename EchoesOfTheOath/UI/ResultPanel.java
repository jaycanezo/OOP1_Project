package EchoesOfTheOath.UI;

import java.awt.*;
import javax.swing.*;

public class ResultPanel extends JPanel {
    private GameWindow game;
    private boolean isVictoryCurrent = false;
    private JPanel buttonPanel;
    private Timer animTimer;
    private float animProgress = 0.0f; 
    private java.awt.image.BufferedImage backgroundCapture; 
    
    public ResultPanel(GameWindow game) {
        this.game = game;
        this.setLayout(null); 
        this.setOpaque(false); 
    }

    public void displayResult(boolean isVictory, java.awt.image.BufferedImage bgCapture) {
        this.isVictoryCurrent = isVictory;
        this.backgroundCapture = bgCapture;
        this.removeAll(); 
    
        game.getBgm().playSFX("battle.wav"); 
        
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        buttonPanel.setOpaque(false);
        
        if (isVictory) {
            JButton continueBtn = createStyledButton("Continue Journey");
            continueBtn.addActionListener(e -> game.showScreen("story"));
            buttonPanel.add(continueBtn);
            
        } else {
            JButton retryBtn = createStyledButton("Return to Checkpoint");
            retryBtn.addActionListener(e -> game.loadGame()); 
            
            JButton titleBtn = createStyledButton("Return to Title");
            titleBtn.addActionListener(e -> game.showScreen("start"));
            
            buttonPanel.add(retryBtn);
            buttonPanel.add(titleBtn);
        }
        
        this.add(buttonPanel);
        
        buttonPanel.setVisible(false);
        animProgress = 0.0f;
        
        if (animTimer != null && animTimer.isRunning()) {
            animTimer.stop();
        }
        
        animTimer = new Timer(15, e -> {
            animProgress += 0.03f; 
            
            if (animProgress >= 1.0f) {
                animProgress = 1.0f;
                animTimer.stop();
                buttonPanel.setVisible(true); 
            }
            repaint();
        });
        animTimer.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (backgroundCapture != null) {
            g2.drawImage(backgroundCapture, 0, 0, getWidth(), getHeight(), null);
        }
        
        int bgAlpha = (int)(160 * animProgress); 
        g2.setColor(new Color(0, 0, 0, bgAlpha)); 
        g2.fillRect(0, 0, getWidth(), getHeight());
        
        int bannerHeight = 160;
        int bannerY = (getHeight() - bannerHeight) / 2 - 20; 
        
        if (buttonPanel != null) {
            buttonPanel.setBounds(0, bannerY + bannerHeight + 40, getWidth(), 100); 
        }
        
        float wipeProgress = Math.min(1.0f, animProgress * 1.5f); 
        int currentBannerWidth = (int)(getWidth() * wipeProgress);
        int bannerX = (getWidth() - currentBannerWidth) / 2; 
        
        if (currentBannerWidth > 0) {
            Color brightYellow = new Color(255, 220, 0, 230);
            Color deepOrange = new Color(255, 140, 0, 230);
            
            if (!isVictoryCurrent) {
                brightYellow = new Color(220, 20, 60, 230); 
                deepOrange = new Color(139, 0, 0, 230);    
            }

            GradientPaint gp = new GradientPaint(bannerX, bannerY, brightYellow, bannerX + currentBannerWidth, bannerY, deepOrange);
            g2.setPaint(gp);
            g2.fillRect(bannerX, bannerY, currentBannerWidth, bannerHeight);
        
            g2.setColor(new Color(255, 255, 255, 180));
            g2.fillRect(bannerX, bannerY, currentBannerWidth, 3); 
            g2.fillRect(bannerX, bannerY + bannerHeight - 3, currentBannerWidth, 3); 
        }
        
        float textAlpha = Math.max(0.0f, (animProgress - 0.3f) / 0.7f); 
        
        if (textAlpha > 0) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, textAlpha));
            
            int titleY = bannerY + (isVictoryCurrent ? 85 : 105);
            
            String title = isVictoryCurrent ? "V I C T O R Y" : "D E F E A T";
            g2.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 80));
            FontMetrics fmTitle = g2.getFontMetrics();
            int titleX = (getWidth() - fmTitle.stringWidth(title)) / 2;
            
            g2.setColor(new Color(0, 0, 0, 120)); 
            g2.drawString(title, titleX + 4, titleY + 4); 
            
            g2.setColor(Color.WHITE);
            g2.drawString(title, titleX, titleY);
            
            if (isVictoryCurrent && game.getChosenCharacter() != null) {
                String rewardText = "Level " + game.getChosenCharacter().getLevel() + " Reached   +500 Gold";
                
                g2.setFont(new Font("Georgia", Font.ITALIC, 22));
                FontMetrics fmReward = g2.getFontMetrics();
                
                int rewardY = titleY + 40; 
                int rewardX = (getWidth() - fmReward.stringWidth(rewardText)) / 2;

                g2.setColor(new Color(0, 0, 0, 150));
                g2.drawString(rewardText, rewardX + 2, rewardY + 2);

                g2.setColor(new Color(255, 240, 180)); 
                g2.drawString(rewardText, rewardX, rewardY);
            }
            
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }
    }
    
    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) g2.setColor(new Color(60, 60, 60));
                else if (getModel().isRollover()) g2.setColor(new Color(80, 80, 80));
                else g2.setColor(new Color(40, 40, 40));
                
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
                g2.setColor(new Color(181, 153, 110));
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Georgia", Font.BOLD, 16));
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusable(false);
        btn.setPreferredSize(new Dimension(240, 50));
        return btn;
    }
}