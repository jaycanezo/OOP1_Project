package EchoesOfTheOath.UI;

import java.awt.*;
import javax.swing.*;

public class NationTransitionPanel extends JPanel {
    private GameWindow game;
    private Sprite background;
    private String nationTitle = "";
    private String nationName = "";
    private float textAlpha = 0f;
    private float bgAlpha = 0f;
    private int phase = 0; 
    private Timer transitionTimer;
    private Font titleFont = FontManager.getFont("Jersey10-Regular.ttf", 72f);
    private Font headerFont = FontManager.getFont("Jersey10-Regular.ttf", 42f);

    public NationTransitionPanel(GameWindow game) {
        this.game = game;
        this.setLayout(null);
        this.setBackground(Color.BLACK);
    }

    private boolean isLoadingDone = false; 

    public void startTransition(int nation) {
        if (transitionTimer != null && transitionTimer.isRunning()) {
            transitionTimer.stop();
        }

        game.getBgm().stopMusic();
        isLoadingDone = false; 

        if (nation == 1) {
            background = new Sprite("/EchoesOfTheOath/Resources/nation1_bg1.png", 1920, 1080, 1);
            nationTitle = "NATION 1";
            nationName = "HUMANAS";
            game.getBgm().playMusic("nation1_bgm1.wav"); 
        } else if (nation == 2) {
            background = new Sprite("/EchoesOfTheOath/Resources/nation2_bg1.png", 1920, 1080, 1);
            nationTitle = "NATION 2";
            nationName = "VEYORA";
            game.getBgm().playMusic("nation2_bgm.WAV"); 
        } else if (nation == 3) {
            background = new Sprite("/EchoesOfTheOath/Resources/nation3_bg1.png", 1920, 1080, 1);
            nationTitle = "NATION 3";
            nationName = "DEMON REALMS";
            game.getBgm().playMusic("nation3_bgm1.wav"); 
        }

        textAlpha = 0f;
        bgAlpha = 0f;
        phase = 0;

        SwingWorker<Void, Void> loader = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                game.story.preloadNationBackgrounds(nation);
                return null;
            }

            @Override
            protected void done() {
                isLoadingDone = true; 
            }
        };
        loader.execute();

        transitionTimer = new Timer(50, new java.awt.event.ActionListener() {
            int holdTicks = 0;
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (phase == 0) { 
                    bgAlpha += 0.04f;
                    if (bgAlpha >= 1f) { 
                        bgAlpha = 1f; 
                        phase = 1; 
                    }
                } 
                else if (phase == 1) { 
                    textAlpha += 0.05f;
                    if (textAlpha >= 1f) { 
                        textAlpha = 1f; 
                        phase = 2; 
                    }
                } 
                else if (phase == 2) { 
                    holdTicks++;
                    if (holdTicks > 40 && isLoadingDone) { 
                        phase = 3; 
                    } 
                } 
                else if (phase == 3) { 
                    textAlpha -= 0.05f;
                    if (textAlpha <= 0f) { 
                        textAlpha = 0f; 
                        phase = 4; 
                        transitionTimer.stop();

                        game.story.applyPreloadedNation(); 
                        game.showScreen("story"); 
                    }
                }
                repaint();
            }
        });
        transitionTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());

        if (background != null && background.isLoaded()) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.min(1f, Math.max(0f, bgAlpha))));
            g2.drawImage(background.getCurrentFrame(), 0, 0, getWidth(), getHeight(), null);
        }

        g2.setColor(new Color(0, 0, 0, 160)); 
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.min(1f, Math.max(0f, textAlpha))));

        g2.setFont(headerFont);
        g2.setColor(new Color(181, 153, 110));
        FontMetrics fmTitle = g2.getFontMetrics();
        int titleX = (getWidth() - fmTitle.stringWidth(nationTitle)) / 2;
        g2.drawString(nationTitle, titleX, getHeight() / 2 - 30);

        g2.setFont(titleFont);
        g2.setColor(Color.WHITE);
        FontMetrics fmName = g2.getFontMetrics();
        int nameX = (getWidth() - fmName.stringWidth(nationName)) / 2;
        g2.drawString(nationName, nameX, getHeight() / 2 + 40);
    }
}