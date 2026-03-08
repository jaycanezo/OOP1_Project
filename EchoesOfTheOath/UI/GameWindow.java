package EchoesOfTheOath.UI;

import java.awt.*;
import javax.swing.*;

public class GameWindow {

    JFrame window;
    CardLayout cardLayout;
    JPanel container;

    StartScreen start;

    public GameWindow() {

        window = new JFrame("Echoes of the Oath");
        window.setSize(1080,720);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        // create screens
        start = new StartScreen(this);
        GamePanel game = new GamePanel(this);

        // add screens
        container.add(start, "start");
        container.add(game, "game");

        window.add(container);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        showScreen("start");
    }

    public void showScreen(String name){
        cardLayout.show(container, name);
        
        switch (name) {
            case "start":
                start.startScreenMusic();
                break;
            case "game":
                container.getComponent(1).requestFocusInWindow(); 
                start.stopMusic();
                break;
            default:
                start.stopMusic();
                break;
        }
        

    }
}
