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
    JButton continueBtn; 

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
    }

    private void setupMenu() {
        buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(true);

        JButton newGameBtn = createMenuButton("New Game", 440, 400);
        continueBtn = createMenuButton("Continue", 440, 450); 
        JButton exitBtn = createMenuButton("Exit", 440, 500);

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
            continueBtn.setForeground(Color.GRAY);
        } else {
            continueBtn.setEnabled(true);
            continueBtn.setForeground(Color.WHITE);
        }
    }

    private JButton createMenuButton(String text, int x, int y) {
        JButton btn = new JButton(text);

        btn.setFont(new Font("Georgia", Font.PLAIN, 28));
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusable(false);
        btn.setBounds(x, y, 200, 50);

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { 
                btn.setForeground(new Color(255, 215, 0)); 
            }

            public void mouseExited(MouseEvent e) { 
                if (btn.isEnabled()){
                    btn.setForeground(Color.WHITE);
                }else {
                    btn.setForeground(Color.GRAY);
                }
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