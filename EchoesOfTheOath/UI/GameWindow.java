package EchoesOfTheOath.UI;

import EchoesOfTheOath.Characters.Character;
import java.awt.*;
import javax.swing.*;

public class GameWindow {

    JFrame window;
    CardLayout cardLayout;
    JPanel container;

    IntroPanel intro;
    StartPanel start;
    StoryPanel story;
    CharacterSelectPanel charSelect;
    BattlePanel battle;
    NewStoryPanel newStory;
    
    private Character chosenCharacter;
    private MusicPlayer bgm = new MusicPlayer();

    public void setChosenCharacter(Character chosenCharacter) {
        this.chosenCharacter = chosenCharacter;
    }

    public Character getChosenCharacter(){
        return this.chosenCharacter;
    }

    public MusicPlayer getBgm() {
        return this.bgm;
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
        battle = new BattlePanel(this);
        newStory = new NewStoryPanel(this);

        // add screens
        container.add(start, "start");
        container.add(intro, "intro");
        container.add(charSelect, "charSelect");
        container.add(story, "story");
        container.add(battle, "battle");
        container.add(newStory, "newStory");

        window.add(container);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        showScreen("start");
    }

    public void showScreen(String name){
        cardLayout.show(container, name);
        
        switch (name) {
            case "start":
                bgm.playMusic("intro_bgm.WAV");
                break;

            case "intro":
                container.getComponent(1).requestFocusInWindow(); 
                break;

            case "newStory":
                // If moving from Start to Story, swap the music
                bgm.stopMusic(); 
                bgm.playMusic("nation1_bgm1.WAV");
                newStory.loadSelectedHero();
                break;

            case "battle":
                // When battle starts, switch to battle music
                bgm.stopMusic();
                bgm.playMusic("nation1_fight_bgm1.WAV");
                battle.loadBattleData();
                battle.requestFocusInWindow();
                break;
                
            case "charSelect":
                // Perhaps keep the intro music or play something calm
                break;
        }
    }
}
