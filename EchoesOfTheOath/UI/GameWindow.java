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
    ResultPanel resultScreen; 
    Quest1Panel quest1;
    
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
        resultScreen = new ResultPanel(this);
        quest1 = new Quest1Panel(this);

        container.add(start, "start");
        container.add(intro, "intro");
        container.add(charSelect, "charSelect");
        container.add(story, "story");
        container.add(battle, "battle");
        container.add(resultScreen, "result");
        container.add(quest1, "quest1");

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

    public BattlePanel getBattlePanel() {
        return battle;
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
            System.out.println("Boss defeated. Dialogue continues normally.");
        }
    }

    public void showScreen(String name) {
        cardLayout.show(container, name);
        
        if (bgmPlaylist.containsKey(name)) {
            bgm.stopMusic();
            bgm.playMusic(bgmPlaylist.get(name));
        }

        switch (name) {
            case "start" -> start.refreshMenu();

            case "quest1" -> quest1.startNewGame();

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
        if (chosenCharacter == null) {
            System.out.println("Autosave skipped: No character selected yet.");
            return; 
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("autosave.txt"))) {
            writer.write("Nation:" + story.getCurrentNation() + "\n");
            writer.write("BossIndex:" + currentBossIndex + "\n");
            writer.write("LineIndex:" + story.getLineIndex() + "\n");
            writer.write("CharacterClass:" + chosenCharacter.getClassType() + "\n");
            writer.write("Level:" + chosenCharacter.getLevel() + "\n");
            writer.write("Gold:" + chosenCharacter.getGold() + "\n");
            writer.write("HP:" + chosenCharacter.getHp() + "\n");
        } catch (IOException e) {
            System.out.println("Autosave failed: " + e.getMessage());
        }
    }

    public void loadGame() {
        if (battle != null) {
            battle.loadBattleData();
        }
        
        File saveFile = new File("autosave.txt");
        if (!saveFile.exists()) 
            return;

        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(saveFile))) {
            String line;
            java.util.Map<String, String> saveParams = new java.util.HashMap<>();
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");

                if (parts.length >= 2) 
                    saveParams.put(parts[0], parts[1]);
            }

            String cls = saveParams.get("CharacterClass");
            if (cls != null) {
                if (cls.equalsIgnoreCase("Warrior")) 
                    setChosenCharacter(new Warrior());

                else if (cls.equalsIgnoreCase("Archer")) 
                    setChosenCharacter(new Archer());
                
                else if (cls.equalsIgnoreCase("Mage")) 
                    setChosenCharacter(new Mage());
            }

            if (chosenCharacter != null) {
                if (saveParams.containsKey("Level")) 
                    chosenCharacter.setLevel(Integer.parseInt(saveParams.get("Level")));

                if (saveParams.containsKey("Gold")) 
                    chosenCharacter.setGold(Double.parseDouble(saveParams.get("Gold")));
                
                if (saveParams.containsKey("HP")) 
                    chosenCharacter.setHp(Integer.parseInt(saveParams.get("HP")));
                
                if (saveParams.containsKey("BossIndex"))
                    this.currentBossIndex = Integer.parseInt(saveParams.get("BossIndex"));
                
                if (saveParams.containsKey("Nation")) 
                    story.setNation(Integer.parseInt(saveParams.get("Nation")));

                if (saveParams.containsKey("LineIndex")) 
                    story.setLineIndex(Integer.parseInt(saveParams.get("LineIndex")));
                
                story.loadSelectedHero(); 
                battle.loadBattleData();  
                showScreen("story");  
            }
        } catch (Exception e) {
            System.out.println("Error loading save: " + e.getMessage());
        }
    }

    public void resetForNewGame() {
        this.chosenCharacter = null;
        this.currentBossIndex = 0;
        
        bosses[0] = new babyM();
        bosses[1] = new Archivist();
        bosses[2] = new Ilaryx();
        bosses[3] = new Lunareth();
        bosses[4] = new Sarukdal();
        bosses[5] = new Elarion();

        story.setNation(1);
        story.resetStory(); 
        battle.loadBattleData(); 

        if (intro != null) {
            intro.resetIntro();
        }
    }

    public void showResultScreen(boolean isVictory, java.awt.image.BufferedImage bgCapture) {
        resultScreen.displayResult(isVictory, bgCapture); 
        showScreen("result");
    }
}