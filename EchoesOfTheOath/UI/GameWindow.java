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
    Quest2Panel quest2;
    Quest3Panel quest3;
    Quest4Panel quest4;
    EndingPanel ending;
    NationTransitionPanel nationTransition;
    private CreditsPanel creditsPanel;
    
    private Character chosenCharacter;
    private MusicPlayer bgm = new MusicPlayer();
    private int currentBossIndex = 0;
    private int blinkTick = 0;

    private final Character[] bosses = {
        new babyM(),
        new Archivist(),
        new Ilaryx(),
        new Lunareth(),
        new Sarukdal(),
        new Elarion()
    };

    public GameWindow() {
        this.bgm = new MusicPlayer();

        window = new JFrame("Echoes of the Oath");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Force the window to be exactly that size immediately
        window.setSize(screenSize.width, screenSize.height);

        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                autosave(); 
                System.exit(0); 
            }
        });

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        start = new StartPanel(this);
        intro = new IntroPanel(this);
        story = new StoryPanel(this);
        charSelect = new CharacterSelectPanel(this);
        battle = new BattlePanel(this);
        resultScreen = new ResultPanel(this);
        quest1 = new Quest1Panel(this);
        quest2 = new Quest2Panel(this);
        quest3 = new Quest3Panel(this);
        quest4 = new Quest4Panel(this);
        ending = new EndingPanel(this);
        nationTransition = new NationTransitionPanel(this);
        creditsPanel = new CreditsPanel(this);


        container.add(start, "start");
        container.add(intro, "intro");
        container.add(charSelect, "charSelect");
        container.add(story, "story");
        container.add(battle, "battle");
        container.add(resultScreen, "result");
        container.add(quest1, "quest1");
        container.add(quest2, "quest2");
        container.add(quest3, "quest3");
        container.add(quest4, "quest4");
        container.add(ending, "ending");
        container.add(nationTransition, "nationTransition");
        container.add(creditsPanel, "credits");

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
        if (currentBossIndex >= 0 && currentBossIndex < bosses.length) {
            return bosses[currentBossIndex];
        }
        return bosses[0];
    }

    public int getBossIndex() {
        return this.currentBossIndex;
    }

    public void startNationTransition(int nation) {
        showScreen("nationTransition"); 
        nationTransition.startTransition(nation);
    }

    public void advanceStoryProgress() {
        if (currentBossIndex < bosses.length - 1) {
            currentBossIndex++;
        }
    }

    public void showScreen(String name) {
        cardLayout.show(container, name);

        if (name.equals("start")) {
            bgm.playMusic("intro_bgm.WAV");
        }
        else if (name.equals("quest1")) {
            bgm.playMusic("quest1_music.WAV");
        } 
        else if(name.equals("quest2")){
            bgm.playMusic("quest2_bgm.WAV");
        }
        else if (name.equals("quest3")) {
            bgm.playMusic("quest3_music.wav");
        }
        else if (name.equals("quest4")) {
            bgm.playMusic("quest4_music.wav");
        }
        else if (name.equals("battle")) {
            int boss = getBossIndex(); 
            switch (boss) {
                case 0 -> bgm.playMusic("nation1_fight_bgm1.WAV");
                case 1 -> bgm.playMusic("nation1_fight_bgm2.WAV");
                case 2 -> bgm.playMusic("nation2_fight_bgm.WAV");
                case 3 -> bgm.playMusic("nation2_fight_bgm.WAV");
                case 4 -> bgm.playMusic("nation3_fight_bgm.WAV");
                case 5 -> bgm.playMusic("nation3_fight_bgm2.WAV");
                default -> bgm.playMusic("nation1_fight_bgm1.WAV");
            }
        } 
        else if (name.equals("story")) {
            int nation = story.getCurrentNation();
            int line = story.getLineIndex(); 
            
            if (nation == 1) {
                if (line >= 23) bgm.playMusic("nation1_bgm2.WAV");
                else bgm.playMusic("nation1_bgm1.wav");
            }
            else if (nation == 2) {
                bgm.playMusic("nation2_bgm.WAV");      
            }
            else if (nation == 3) {
                if (line >= 68) bgm.playMusic("intro_bgm.WAV");
                else if (getBossIndex() == 5) bgm.playMusic("nation3_bgm2.wav"); 
                else bgm.playMusic("nation3_bgm1.wav");
            }
        }
        else if (name.equals("ending")) {
            ending.startEnding();
        }else if (name.equals("credits")) {
            creditsPanel.startCredits();
            this.chosenCharacter = null; 
            
            java.io.File saveFile = new java.io.File("autosave.txt");
            if (saveFile.exists()) {
                saveFile.delete();
            }
        }

        switch (name) {
            case "start" -> start.refreshMenu();
            case "quest1" -> quest1.startNewGame();
            case "quest2" -> quest2.startQuest();
            case "quest3" -> quest3.startNewGame();
            case "quest4" -> quest4.startNewGame();
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

    public void flashlightBlinkTransition(String nextScreen) {
        blinkTick = 0;
        final int[] currentAlpha = {0}; 
        JPanel darknessPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0, 0, 0, currentAlpha[0])); 
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        darknessPanel.setOpaque(false);
        window.setGlassPane(darknessPanel);
        darknessPanel.setVisible(true);

        int[] blinkSequence = {0, 180, 50, 230, 100, 255, 150, 255, 255};
        javax.swing.Timer blinkTimer = new javax.swing.Timer(120, null);
        blinkTimer.addActionListener(e -> {
            if (blinkTick < blinkSequence.length) {
                currentAlpha[0] = blinkSequence[blinkTick]; 
                darknessPanel.repaint();            
                blinkTick++;
            } else {
                blinkTimer.stop();
                darknessPanel.setVisible(false);
                showScreen(nextScreen);
            }
        });
        blinkTimer.start();
    }

    public void autosave() {
        if (chosenCharacter == null) return;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("autosave.txt"))) {
            writer.write("Nation:" + story.getCurrentNation() + "\n");
            writer.write("BossIndex:" + currentBossIndex + "\n");
            writer.write("LineIndex:" + story.getLineIndex() + "\n");
            writer.write("Name:" + chosenCharacter.getName() + "\n");
            writer.write("CharacterClass:" + chosenCharacter.getClassType() + "\n");
            writer.write("Level:" + chosenCharacter.getLevel() + "\n");
            writer.write("Gold:" + chosenCharacter.getGold() + "\n");
            writer.write("HP:" + chosenCharacter.getHp() + "\n");
        
            StringBuilder invText = new StringBuilder();
            for (EchoesOfTheOath.Characters.Item item : chosenCharacter.getInventory()) {
                invText.append(item.getName()).append(",");
            }
            writer.write("Inventory:" + invText.toString() + "\n");
            
            System.out.println("Autosave successful!");
        } catch (IOException e) { 
            e.printStackTrace(); 
        }
    }

    public void loadGame() {
        File saveFile = new File("autosave.txt");
        if (!saveFile.exists()) return;

        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(saveFile))) {
            String line;
            java.util.Map<String, String> saveParams = new java.util.HashMap<>();
        
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2); 
                if (parts.length >= 2) saveParams.put(parts[0], parts[1]);
            }

            String cls = saveParams.get("CharacterClass");
            if (cls != null) {
                if (cls.equalsIgnoreCase("Warrior")) setChosenCharacter(new Warrior());
                else if (cls.equalsIgnoreCase("Archer")) setChosenCharacter(new Archer());
                else if (cls.equalsIgnoreCase("Mage")) setChosenCharacter(new Mage());
            }

            if (chosenCharacter != null) {
                int savedNation = Integer.parseInt(saveParams.getOrDefault("Nation", "1"));

                SwingWorker<Void, Void> loader = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        story.preloadNationBackgrounds(savedNation);
                        return null;
                    }

                    @Override
                    protected void done() {
                        story.applyPreloadedNation();
                        
                        chosenCharacter.setLevel(Integer.parseInt(saveParams.getOrDefault("Level", "1")));
                        chosenCharacter.setGold(Double.parseDouble(saveParams.getOrDefault("Gold", "0")));
                        chosenCharacter.setHp(Integer.parseInt(saveParams.getOrDefault("HP", "100")));
                        currentBossIndex = Integer.parseInt(saveParams.getOrDefault("BossIndex", "0"));
                        story.setLineIndex(Integer.parseInt(saveParams.getOrDefault("LineIndex", "0")));
                        chosenCharacter.setName(saveParams.getOrDefault("Name", "Hero"));

                        chosenCharacter.getInventory().clear();
                        if (saveParams.containsKey("Inventory") && !saveParams.get("Inventory").trim().isEmpty()) {
                            String[] savedItems = saveParams.get("Inventory").split(",");
                            Shop referenceShop = new Shop(); 
                            
                            for (String itemName : savedItems) {
                                itemName = itemName.trim();
                                for (Item shopItem : referenceShop.getStock()) {
                                    if (shopItem.getName().equals(itemName)) {
                                        chosenCharacter.getInventory().add(shopItem);
                                        break;
                                    }
                                }
                            }
                        }
                    
                        story.loadSelectedHero(); 
                        showScreen("story");  
                    }
                };
                loader.execute(); 
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void resetForNewGame() {
        this.chosenCharacter = null;
        this.currentBossIndex = 0;

        java.io.File saveFile = new java.io.File("autosave.txt");
        if (saveFile.exists()) {
            saveFile.delete();
        }

        story.preloadNationBackgrounds(1);
        story.applyPreloadedNation();
        story.resetStory(); 
        if (intro != null) intro.resetIntro();
        showScreen("intro"); 
    }

    public void showResultScreen(boolean isVictory, java.awt.image.BufferedImage bgCapture) {
        resultScreen.displayResult(isVictory, bgCapture); 
        showScreen("result");
    }
}