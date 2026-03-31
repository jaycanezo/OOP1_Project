package EchoesOfTheOath.UI;

import java.awt.*;
import javax.swing.*;
import EchoesOfTheOath.Characters.Character;

public class GameWindow {

    JFrame window;
    CardLayout cardLayout;
    JPanel container;

    IntroPanel intro;
    StartPanel start;
    StoryPanel story;
    CharacterSelectPanel charSelect;

    private Character chosenCharacter;

    public void setChosenCharacter(Character chosenCharacter) {
        this.chosenCharacter = chosenCharacter;
    }

    public Character getChosenCharacter(){
        return this.chosenCharacter;
    }


    public GameWindow() {

        window = new JFrame("Echoes of the Oath");
        window.setSize(1080,720);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        // create screens
        start = new StartPanel(this);
        intro = new IntroPanel(this);
        story = new StoryPanel(this);
        charSelect = new CharacterSelectPanel(this);

        // add screens
        container.add(start, "start");
        container.add(intro, "intro");
        container.add(charSelect, "charSelect");
        container.add(story, "story");

        window.add(container);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        showScreen("start");
    }

    public void showScreen(String name){
        cardLayout.show(container, name);
        
        if(name.equals("story")){
            story.loadSelectedCharacter();
        }

        switch (name) {
            case "start":
                start.startScreenMusic();
                break;
            case "intro":
                container.getComponent(1).requestFocusInWindow(); 
                start.stopMusic();
                break;
                
            case "story":
                container.getComponent(2).requestFocusInWindow(); 
                start.stopMusic();
                break;
            default:
                start.stopMusic();
                break;
        }
        

    }
}
