package EchoesOfTheOath.UI;

import java.awt.*;
import javax.swing.*;

public class GameOverPanel extends JPanel {
    private GameWindow game;

    public GameOverPanel(GameWindow game) {
        this.game = game;
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;

        JLabel deathLabel = new JLabel("The Oath has been Broken");
        deathLabel.setFont(new Font("Georgia", Font.PLAIN, 64));
        deathLabel.setForeground(new Color(150, 0, 0));
        
        JButton retryBtn = createGameOverButton("Retry from Checkpoint");
        JButton menuBtn = createGameOverButton("Return to Title");

        retryBtn.addActionListener(e -> game.loadGame());
        menuBtn.addActionListener(e -> game.showScreen("start"));

        gbc.gridy = 0; gbc.insets = new Insets(0, 0, 50, 0); 
        add(deathLabel, gbc);

        gbc.gridy = 1; gbc.insets = new Insets(10, 0, 10, 0); 
        add(retryBtn, gbc);

        gbc.gridy = 2; add(menuBtn, gbc);
    }

    private JButton createGameOverButton(String text) {
        JButton btn = new JButton(text);

        btn.setFont(new Font("Georgia", Font.PLAIN, 24));
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setFocusable(false);
        btn.setBorder(BorderFactory.createEmptyBorder());
        
        return btn;
    }
}