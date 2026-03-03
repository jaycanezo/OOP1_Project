package EchoesOfTheOath.UI;

import javax.swing.*;

import java.awt.*;

public class GameWindow extends JFrame {

    public GameWindow() {
        setTitle("Echoes of the Oath");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center screen
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Echoes of the Oath", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 30));

        add(new BattlePanel());

        setVisible(true);
    }
}
