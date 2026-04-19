package EchoesOfTheOath.UI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.*;

public class StartPanel extends JPanel {
    GameWindow game;
    Sprite titleSprite;
    Timer animationTimer;
    JPanel buttonPanel;

    public StartPanel(GameWindow game) {
        this.game = game;
        this.setFocusable(true);
        this.setLayout(null);

        titleSprite = new Sprite(
            "/EchoesOfTheOath/Resources/StartScreen.png",
            1080, 720, 240 
        );

        animationTimer = new Timer(30, e -> {
            titleSprite.update();
            repaint();
        });
        animationTimer.start();

        setupMenu();

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    game.showScreen("intro");
                }
            }
        });
    }

    private void setupMenu() {
        buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(true); // Make sure we see the sprite behind

        JButton newGameBtn = createMenuButton("New Game", 440, 400);
        JButton continueBtn = createMenuButton("Continue", 440, 450);
        JButton exitBtn = createMenuButton("Exit", 440, 500);

        File saveFile = new File("autosave.txt");
        if (!saveFile.exists()) {
            continueBtn.setEnabled(false);
            continueBtn.setForeground(Color.GRAY);
        }

        newGameBtn.addActionListener(e -> game.showScreen("intro"));
        continueBtn.addActionListener(e -> game.loadGame());
        exitBtn.addActionListener(e -> System.exit(0));

        add(newGameBtn);
        add(continueBtn);
        add(exitBtn);
    }

    private JButton createMenuButton(String text, int x, int y) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Serif", Font.BOLD, 28));
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusable(false);
        btn.setBounds(x, y, 200, 50);

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setForeground(new Color(255, 215, 0)); }
            public void mouseExited(MouseEvent e) { 
                if (btn.isEnabled()) btn.setForeground(Color.WHITE);
                else btn.setForeground(Color.GRAY);
            }
        });
        return btn;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage frame = titleSprite.getCurrentFrame();

        if (frame != null) {
            g.drawImage(frame, 0, 0, getWidth(), getHeight(), null);
        } else {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        g.setColor(new Color(0, 0, 0, 80));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}