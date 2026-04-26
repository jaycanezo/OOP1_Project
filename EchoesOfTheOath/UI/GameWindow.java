package EchoesOfTheOath.UI;

import EchoesOfTheOath.Characters.*;
import EchoesOfTheOath.Characters.Character;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    GameOverPanel gameOver;
    
    private Character chosenCharacter;
    private MusicPlayer bgm = new MusicPlayer();
    private int currentBossIndex = 0;
    private final Character[] bosses = {
        new babyM(),
        new Archivist(),
        new Ilaryx(),
        new Lunareth(),
        new Sarukdal(),
        new Elarion()
    };

    private final java.util.Map<String, String> bgmPlaylist = java.util.Map.of(
        "start", "intro_bgm.WAV",
        "story", "nation1_bgm1.WAV",
        "battle", "nation1_fight_bgm1.WAV"
    );

    public GameWindow() {
        this.bgm = new MusicPlayer();
        window = new JFrame("Echoes of the Oath");
        window.setSize(1080,720);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        start = new StartPanel(this);
        intro = new IntroPanel(this);
        story = new StoryPanel(this);
        charSelect = new CharacterSelectPanel(this);
        battle = new BattlePanel(this);
        gameOver = new GameOverPanel(this);

        container.add(start, "start");
        container.add(intro, "intro");
        container.add(charSelect, "charSelect");
        container.add(story, "story");
        container.add(battle, "battle");
        container.add(gameOver, "gameover");

        window.add(container);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        showScreen("start");
    }

    public void setChosenCharacter(Character chosenCharacter) {
        this.chosenCharacter = chosenCharacter;
    }

    public Character getChosenCharacter(){
        return this.chosenCharacter;
    }

    public MusicPlayer getBgm() {
        return this.bgm;
    }

    public Character getCurrentBoss() { 
        return switch (currentBossIndex) {
            case 0 -> new babyM();
            case 1 -> new Archivist();
            case 2 -> new Ilaryx();
            case 3 -> new Lunareth();
            case 4 -> new Sarukdal();
            case 5 -> new Elarion();
            default -> new babyM();
        };
    }

    public int getBossIndex() {
        return this.currentBossIndex;
    }

    public void advanceStoryProgress() {
        if (currentBossIndex < bosses.length - 1) {
            currentBossIndex++;
            
            if (currentBossIndex % 2 == 0) {
                int nationNumber = (currentBossIndex / 2) + 1;
                story.setNation(nationNumber); 
            } else {
                // We are still in the same nation, just after a boss.
                // Do NOT call resetStory() if you want to keep the lineIndex.
                // Instead, just clear the speaker to refresh the UI.
                story.resetStory(); 
            }
        }
    }

    public void showScreen(String name) {
        cardLayout.show(container, name);
        
        if (bgmPlaylist.containsKey(name)) {
            bgm.stopMusic();
            bgm.playMusic(bgmPlaylist.get(name));
        }

        switch (name) {
            case "intro" -> container.getComponent(1).requestFocusInWindow();
            
            case "story" -> {
                if (currentBossIndex % 2 == 0 && story.getLineIndex() == 0) {
                    autosave(); 
                }
                story.loadSelectedHero();
                story.requestFocusInWindow();
            }
                    
            case "battle" -> {
                battle.loadBattleData();
                battle.requestFocusInWindow();
            }
        }
    }

    public void autosave() {
        if (chosenCharacter == null) return;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("autosave.txt"))) {
            int nationNumber = (currentBossIndex / 2) + 1;

            writer.write("Nation:" + nationNumber + "\n"); 
            writer.write("CharacterClass:" + chosenCharacter.getClassType() + "\n");
            writer.write("Level:" + chosenCharacter.getLevel() + "\n");
            writer.write("Gold:" + chosenCharacter.getGold() + "\n");
            
            System.out.println("Checkpoint Locked: Nation " + nationNumber);
        } catch (IOException e) {
            System.out.println("Autosave failed: " + e.getMessage());
        }
    }

    public void loadGame() {
        File saveFile = new File("autosave.txt");
        if (!saveFile.exists()) return;

        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(saveFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length < 2) continue;

                String key = parts[0];
                String value = parts[1];

                switch (key) {
                    case "Nation" -> {
                        int nation = Integer.parseInt(value);
                        this.currentBossIndex = (nation - 1) * 2; 
                        story.setNation(nation);
                    }
                    case "CharacterClass" -> {
                        if (value.equalsIgnoreCase("Warrior")) setChosenCharacter(new Warrior());
                        else if (value.equalsIgnoreCase("Archer")) setChosenCharacter(new Archer());
                        else if (value.equalsIgnoreCase("Mage")) setChosenCharacter(new Mage());
                    }
                    case "Level" -> {
                        if (chosenCharacter != null) {
                            chosenCharacter.setLevel(Integer.parseInt(value));
                        }
                    }
                    case "Gold" -> {
                        if (chosenCharacter != null) {
                            chosenCharacter.setGold(Double.parseDouble(value));
                        }
                    }
                }
            }
            story.loadSelectedHero();
            showScreen("story");
            
        } catch (Exception e) {
            System.out.println("Error loading save: " + e.getMessage());
        }
    }
}