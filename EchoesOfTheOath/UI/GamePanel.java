package EchoesOfTheOath.UI;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    GameWindow game;

    public GamePanel(GameWindow game){
        this.game = game;
        setBackground(Color.darkGray);
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.white);
        g.drawString("Game Started!", 350, 300);
    }
}
