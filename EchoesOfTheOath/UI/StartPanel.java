package EchoesOfTheOath.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class StartPanel extends JPanel {
    GameWindow game;

    // Replace raw spriteSheet + frames[] with a single Sprite object
    Sprite titleSprite;

    Timer animationTimer;

    public StartPanel(GameWindow game) {
        this.game = game;
        setFocusable(true);
        requestFocusInWindow();

        // --- Adjust frameWidth, frameHeight, frameCount to match YOUR sheet ---
        titleSprite = new Sprite(
            "/EchoesOfTheOath/Resources/StartScreen.png",
            1080,   // width of one frame  ← change this
            720,   // height of one frame ← change this
            240      // total frame count   ← change this
        );

        animationTimer = new Timer(30, e -> {
            titleSprite.update();
            repaint();
        });
        animationTimer.start();

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    game.showScreen("intro");
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        BufferedImage frame = titleSprite.getCurrentFrame();

        if (frame != null) {
            g.drawImage(frame, 0, 0, 1080, 720, null);
        } else {
            // Fallback background so the screen isn't blank while debugging
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("Serif", Font.BOLD, 50));
            g.drawString("Echoes of the Oath", 180, 200);

            g.setFont(new Font("Serif", Font.PLAIN, 25));
            g.drawString("Press ENTER to Start", 260, 350);
            g.fillRect(0, 0, 1080, 720);
        }
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 25));
        g.drawString("Press ENTER to Start", 400, 540);
    }
}
