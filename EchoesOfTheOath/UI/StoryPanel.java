package EchoesOfTheOath.UI;

import EchoesOfTheOath.Characters.*;
import EchoesOfTheOath.Characters.Character;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;

public class StoryPanel extends JPanel {
    private GameWindow game;
    private Character player;

    private int optionsCursor = 0;
    private final int PLAY_STATE = 0, INVENTORY_STATE = 1, SHOP_STATE = 2, SUB_MENU_STATE = 3, OPTIONS_STATE = 4;
    private int gameState = PLAY_STATE;

    private java.util.Map<String, Sprite> backgroundRegistry = new java.util.HashMap<>();
    private Sprite currentBackground;
    private int currentNation = 1;
    private Sprite playerSprite, npcSprite;
    private float uiAlpha = 1.0f;
    private boolean isFadingOut = false;
    private Timer fadeOutTimer;

    private String[] dialogueText;
    private Font smallText;
    private Font mediumText;
    private int lineIndex = 0;
    private Npc npcManager = new Npc();
    private String currentSpeaker = "";
    private boolean showPlayer = false;
    private boolean showNPC = false;
    
    private Shop gameShop = new Shop();
    private int slotCol = 0, slotRow = 0, subMenuCursor = 0, scrollOffset = 0;
    private String shopGreeting = "Welcome, traveler! What can I get for you today?";
    private String shopResultMessage = "";
    
    public StoryPanel(GameWindow game) {
        this.game = game;
        this.setFocusable(true);
        this.setLayout(null);
        
        setNation(1);
        smallText = FontManager.getFont("Jersey10-Regular.ttf", 22f);
        mediumText = FontManager.getFont("Jersey10-Regular.ttf", 26f);

        Timer animationTimer = new Timer(100, e -> {
            if (playerSprite != null) playerSprite.update();
            if (npcSprite != null) npcSprite.update();
            if (currentBackground != null) currentBackground.update();
            repaint();
        });
        animationTimer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleInput(e.getKeyCode());
            }
        });
    }

    private void loadNationBackgrounds(int nation) {
        backgroundRegistry.clear();
        currentBackground = null;
        System.gc(); 

        if (nation == 1) {
            loadBG("nation1_bg1.png", 1920, 1080, 1);
            loadBG("nation1_bg2.png", 1920, 1080, 1);
            loadBG("nation1_bg3.png", 1920, 1080, 1);
            loadBG("nation1_bg4.png", 1920, 1080, 1);
            loadBG("nation1_bg5.png", 1920, 1080, 1);
            loadBG("nation1_bg6.png", 1920, 1080, 1);
            loadBG("nation1_bg6.1.png", 1920, 1080, 1);
            loadBG("nation1_bg7.png", 1920, 1080, 1);
            loadBG("nation1_bg8.png", 1920, 1080, 1);
            loadBG("nation1_bg8.1.png", 1920, 1080, 1);
            loadBG("nation1_bg8.2.png", 1920, 1080, 30);
            loadBG("nation1_bg9.png", 2730, 1536, 1);
            loadBG("nation1_storyline1.png", 1280, 720, 29);
        } else if (nation == 2) {
            loadBG("nation2_bg1.png", 1920, 1080, 1);
            loadBG("nation2_bg2.png", 1920, 1080, 1);
            loadBG("nation2_bg3.png", 1920, 1080, 1);
            loadBG("nation2_bg4.png", 1920, 1080, 1);
            loadBG("nation2_bg5.png", 1920, 1080, 1);
            loadBG("nation2_bg6.png", 1920, 1080, 1);
            loadBG("nation2_bg7.png", 1920, 1080, 1);
            loadBG("nation2_bg8.png", 1920, 1080, 1);
            loadBG("nation2_bg9.png", 1920, 1080, 1);
        } else if (nation == 3) {
            loadBG("nation3_bg1.png", 1920, 1080, 1); 
            loadBG("nation3_bg2.png", 1920, 1080, 1); 
            loadBG("nation3_bg3.png", 1920, 1080, 1); 
            loadBG("nation3_bg4.png", 1920, 1080, 1); 
            loadBG("nation3_bg5.png", 1920, 1080, 1); 
            loadBG("nation3_bg6.png", 1920, 1080, 1); 
            loadBG("nation3_bg7.png", 1920, 1080, 1); 
            loadBG("nation3_bg8.png", 1920, 1080, 1); 
            loadBG("nation3_bg9.png", 1920, 1080, 1); 
            loadBG("nation3_bg10.png", 1920, 1080, 1); 
        }
    }

    public void setNation(int nationNumber) {
        this.currentNation = nationNumber;
        this.dialogueText = DialogueManager.getNationDialogue(nationNumber);
        
        loadNationBackgrounds(nationNumber);

        resetStory();
        this.showPlayer = true;
        
        if (nationNumber == 1) setBackgroundImage("nation1_bg1.png");
        else if (nationNumber == 2) setBackgroundImage("nation2_bg1.png");
        else if (nationNumber == 3) setBackgroundImage("nation3_bg1.png");
    }

    public void resetStory() {
        this.lineIndex = 0;
        this.currentSpeaker = "";
        this.gameState = PLAY_STATE;
        this.showNPC = false;
        this.showPlayer = false;
        this.npcSprite = null;
        repaint();
    }

    public int getLineIndex() {
        return this.lineIndex;
    }

    public void setLineIndex(int index) {
        this.lineIndex = index;
        repaint();
    }

    public int getCurrentNation() {
        return this.currentNation;
    }

    public void loadSelectedHero() {
        this.player = game.getChosenCharacter();
        this.gameState = PLAY_STATE; 
        this.optionsCursor = 0;

        if (this.player != null) { 
            this.playerSprite = this.player.getIdleSprite(); 
            if (currentSpeaker.equals("Warrior") || currentSpeaker.equals("Mage") || currentSpeaker.equals("Archer")) {
                currentSpeaker = player.getName();
            }
        }
        
        if (backgroundRegistry.isEmpty()) {
            loadNationBackgrounds(currentNation);
        }

        if (currentNation == 1) {
            if (lineIndex >= 59) setBackgroundImage("nation1_bg8.png");
            else if (lineIndex >= 37) setBackgroundImage("nation1_bg6.png");
            else setBackgroundImage("nation1_bg1.png");
            
        } else if (currentNation == 2) {
            if(lineIndex >=74) setBackgroundImage("nation2_bg9.png");
            else if(lineIndex >=47) setBackgroundImage("nation2_bg4.png");
            else setBackgroundImage("nation2_bg1.png");
            
        } else if (currentNation == 3) {
            if (lineIndex >= 67) setBackgroundImage("nation3_bg10.png"); 
            else if (lineIndex >= 49) setBackgroundImage("nation3_bg9.png"); 
            else if (lineIndex >= 44) setBackgroundImage("nation3_bg8.png"); 
            else if (lineIndex >= 33) setBackgroundImage("nation3_bg6.png"); 
            else if (lineIndex >= 27) setBackgroundImage("nation3_bg5.png"); 
            else if (lineIndex >= 18) setBackgroundImage("nation3_bg4.png"); 
            else if (lineIndex >= 10) setBackgroundImage("nation3_bg3.png"); 
            else if (lineIndex >= 4) setBackgroundImage("nation3_bg2.png");
            else setBackgroundImage("nation3_bg1.png"); 
        }

        this.showPlayer = true;
    }

    private void handleInput(int code) {
        if (isFadingOut) return; 

        if (gameState == PLAY_STATE) {
            if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ENTER) {
                progressStory();
            }
            
            if (code == KeyEvent.VK_I) { 
                gameState = INVENTORY_STATE;
                game.getBgm().playSFX("inventory_sfx.wav"); 
                resetCursor(); 
            }

            if (code == KeyEvent.VK_P) { 
                gameState = SHOP_STATE; 
                game.getBgm().playSFX("shop_sfx.wav"); 
                resetCursor();
                shopResultMessage = "";
            }

            if (code == KeyEvent.VK_O || code == KeyEvent.VK_ESCAPE) { 
                gameState = OPTIONS_STATE; 
                game.getBgm().playSFX("options_sfx.wav");
                shopResultMessage = "";
                optionsCursor = 0; 
            }
        } 
        else if (gameState == OPTIONS_STATE) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) 
                optionsCursor = (optionsCursor > 0) ? optionsCursor - 1 : 2;

            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) 
                optionsCursor = (optionsCursor < 2) ? optionsCursor + 1 : 0;

            if (code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_O) 
                gameState = PLAY_STATE;

            if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) 
                executeOptionsAction();
        }
        else {
            if (code == KeyEvent.VK_I) {
                gameState = (gameState == INVENTORY_STATE) ? PLAY_STATE : INVENTORY_STATE;
                resetCursor();
            }
            else if (code == KeyEvent.VK_P) {
                gameState = (gameState == SHOP_STATE) ? PLAY_STATE : SHOP_STATE;
                resetCursor();
            }
            else if (code == KeyEvent.VK_ESCAPE) {
                gameState = PLAY_STATE;
                shopResultMessage = "";
            }
            else if (gameState == SUB_MENU_STATE) { 
                handleSubMenuInput(code); 
            } 
            else {
                handleGridMovement(code); 
            }
        }

        repaint();
    }

    private void executeOptionsAction() {
        switch (optionsCursor) {
            case 0 -> { 
                gameState = PLAY_STATE; 
                game.loadGame(); 
            }
            case 1 -> { 
                game.autosave(); 
                game.showScreen("start"); 
            }
            case 2 -> { 
                game.autosave(); 
                System.exit(0); 
            }
        }
    }

    private void progressStory() {
        MusicPlayer bgm = game.getBgm();
        lineIndex++;

        if (dialogueText == null || lineIndex >= dialogueText.length) {
            if (game.getBossIndex() % 2 == 0 && game.getBossIndex() > 0) {
                int nextNation = (game.getBossIndex() / 2) + 1;
                game.startNationTransition(nextNation);
                game.autosave(); 
            } else {
                game.showScreen("battle");
            }
            return;
        }

        if (currentNation == 1) {
            switch (lineIndex) {
                case 0: 
                    break;
                case 1: 
                    bgm.playSFX("nation1_sfx1.wav"); 
                    break;
                case 2: 
                    setBackgroundImage("nation1_bg2.png"); 
                    currentSpeaker = ""; 
                break;
                case 4: 
                    bgm.playSFX("nation1_sfx1.wav"); 
                    break;
                case 5: 
                    updateScene("Guard", true); 
                    bgm.playSFX("nation1_sfx2.wav"); 
                    currentSpeaker = ""; 
                    break;
                case 6: 
                    updateScene("Guard", true); 
                    break;
                case 7: 
                    currentSpeaker = player.getName(); 
                    break;
                case 8: 
                    updateScene("Guard", true); 
                    break;
                case 9: 
                    currentSpeaker = ""; 
                    break;
                case 10: 
                    setBackgroundImage("nation1_bg3.png"); 
                    bgm.playSFX("nation1_sfx1.wav"); 
                    this.showNPC = false; 
                    currentSpeaker = ""; 
                    break;
                case 11: 
                    updateScene("Informant", true); 
                    currentSpeaker = ""; 
                    break; 
                case 12: 
                    currentSpeaker = "Informant"; 
                    break;
                case 13: 
                    currentSpeaker = player.getName(); 
                    break;
                case 14: 
                    currentSpeaker = "Informant"; 
                    break;
                case 15: 
                    currentSpeaker = player.getName(); 
                    break;
                case 16: 
                    currentSpeaker = "Informant"; 
                    break;
                case 17: 
                    setBackgroundImage("nation1_bg4.png"); 
                    this.showNPC = false; currentSpeaker = " "; 
                    break;
                case 18: 
                    updateScene(player.getName(), false); 
                    break;
                case 20: 
                    currentSpeaker = " "; 
                    break;
                case 23: 
                    setBackgroundImage("nation1_bg5.png"); 
                    updateScene(player.getName(), false); 
                    bgm.playSFX("nation1_sfx3.wav"); 
                    currentSpeaker = ""; 
                    break;
                case 25: 
                    bgm.playSFX("nation1_sfx4.wav"); 
                    break;
                case 26: 
                    setBackgroundImage("nation1_bg6.png"); 
                    break;
                case 27: 
                    updateScene("Attendant", true); 
                    currentSpeaker = "Attendant"; 
                    break;
                case 28: 
                    currentSpeaker = player.getName(); 
                    break;
                case 29: 
                    currentSpeaker = "Attendant"; 
                    break;
                case 30: 
                    currentSpeaker = player.getName(); 
                    break;
                case 31: 
                    currentSpeaker = "Attendant"; 
                    break;
                case 32: 
                    setBackgroundImage("nation1_bg6.1.png"); 
                    showPlayer = false; 
                    showNPC = false; 
                    break;
                case 33: 
                    currentSpeaker = ""; 
                    showPlayer = true; 
                    break;
                case 35: 
                    setBackgroundImage("nation1_bg6.png"); 
                    currentSpeaker = "Attendant"; 
                    showNPC = true; 
                    break;
                case 37: 
                    game.showScreen("battle"); 
                    currentSpeaker = ""; 
                    showNPC = false; 
                    break;
                case 38: 
                    setBackgroundImage("nation1_bg6.png"); 
                    currentSpeaker = player.getName(); 
                    break;
                case 39: 
                    currentSpeaker = ""; 
                    break;
                case 40: 
                    setBackgroundImage("nation1_bg6.1.png"); 
                    currentSpeaker = ""; 
                    break;
                case 42: 
                    setBackgroundImage("nation1_bg6.png"); 
                    currentSpeaker = player.getName(); 
                    break;
                case 44:
                    currentSpeaker = ""; 
                    break;
                case 45: 
                    game.showScreen("quest1"); 
                    currentSpeaker = ""; 
                    break;
                case 48: 
                    setBackgroundImage("nation1_bg8.2.png"); 
                    showPlayer = false; 
                    break;
                case 49: 
                    setBackgroundImage("nation1_bg7.png"); 
                    showPlayer = true; 
                    break;
                case 50: 
                    setBackgroundImage("nation1_bg8.png"); 
                    break;
                case 51: 
                    updateScene("Archivist", true); 
                    currentSpeaker = "Archivist"; 
                    showPlayer = true; 
                    break;
                case 52: 
                    currentSpeaker = player.getName(); 
                    break;
                case 53: 
                    currentSpeaker = "Archivist"; 
                    break;
                case 54: 
                    currentSpeaker = player.getName(); 
                    break;
                case 55: 
                    currentSpeaker = "Archivist"; 
                    break;
                case 56: 
                    setBackgroundImage("nation1_storyline1.png"); 
                    currentSpeaker = ""; 
                    showNPC = false; showPlayer = false; 
                    break;
                case 57: 
                    setBackgroundImage("nation1_bg8.png"); 
                    updateScene("Archivist", true);
                    currentSpeaker = player.getName(); 
                    break;
                case 58: 
                    currentSpeaker = "Archivist"; 
                    break;
                case 59: 
                    game.showScreen("battle"); 
                    currentSpeaker = "";
                    break;
                case 60: 
                    updateScene("Archivist", true);
                    currentSpeaker = "Archivist";  
                    break;
                case 61: 
                    currentSpeaker = ""; 
                    break; 
                case 62: 
                    currentSpeaker = player.getName(); 
                    break;
                case 63: 
                    currentSpeaker = ""; 
                    break;
                case 65: 
                    currentSpeaker = player.getName(); 
                    break;
                case 66:
                    currentSpeaker = ""; 
                    break;
                case 67: 
                    setBackgroundImage("nation1_bg9.png");
                    break;
                case 68: 
                    currentSpeaker = "Archivist"; 
                    break;
                case 69: 
                    currentSpeaker = player.getName(); 
                    break;
                case 70: 
                    showNPC=false; 
                    break;
            }
        }

        if (currentNation == 2) {
            switch (lineIndex) {
                case 0: setBackgroundImage("nation2_bg1.png"); break;
                case 4: currentSpeaker = player.getName(); break;
                case 5: setBackgroundImage("nation2_bg2.png"); currentSpeaker = ""; break;
                case 6: updateScene("Elven Scout", true); currentSpeaker = "Elven Scout"; break;
                case 7: updateScene("Elven Rebel",true); currentSpeaker = "Elven Rebel"; break;
                case 9: currentSpeaker = player.getName(); break;
                case 10: updateScene("Elven Rebel",true); currentSpeaker = "Elven Rebel"; break;
                case 11: updateScene("Elven Scout",true); currentSpeaker = "Elven Scout"; break;
                case 12: currentSpeaker = player.getName(); break;
                case 13: updateScene("Elven Scout",true); currentSpeaker = "Elven Scout"; break;
                case 14: showNPC = false; currentSpeaker =""; break;
                case 16: setBackgroundImage("nation2_bg1.png"); break;
                case 17: setBackgroundImage(""); setBackground(Color.BLACK); currentSpeaker = "A Whisper"; break;
                case 18: setBackgroundImage("nation2_bg1.png"); currentSpeaker = player.getName(); break;
                case 19: currentSpeaker = ""; break;
                case 20: currentSpeaker = player.getName(); break;
                case 21: currentSpeaker = ""; break;
                case 22: game.showScreen("quest2"); setBackgroundImage("nation2_bg3.png"); bgm.playSFX(""); break;/////////////////////////////////
                case 23: updateScene("Healer", true); currentSpeaker = "Healer"; break;
                case 24: currentSpeaker = player.getName(); break;
                case 25: currentSpeaker = "Healer"; break;
                case 26: currentSpeaker = player.getName(); break;
                case 27: currentSpeaker = "Healer"; break;
                case 28: currentSpeaker = player.getName(); break;
                case 29: currentSpeaker = "Healer"; break;
                case 30: currentSpeaker = ""; break;
                case 32: setBackgroundImage(""); setBackground(Color.BLACK); showNPC = false; break;
                case 33: setBackgroundImage("nation2_bg4.png"); break;
                case 37: currentSpeaker = player.getName(); break;
                case 38: updateScene("Ilaryx", true); currentSpeaker = ""; break;
                case 39: currentSpeaker = "Ilaryx"; break;
                case 40: currentSpeaker = player.getName(); break;
                case 41: currentSpeaker = "Ilaryx"; break;
                case 42: currentSpeaker = player.getName(); break;
                case 43: currentSpeaker = "Ilaryx"; break;
                case 44: currentSpeaker = player.getName(); break;
                case 45: currentSpeaker = "Ilaryx"; break;
                case 47: game.showScreen("battle"); break;
                case 48: updateScene("Ilaryx", true); currentSpeaker = "Ilaryx"; break;
                case 49: currentSpeaker = player.getName(); break;
                case 50: currentSpeaker = ""; break;
                case 51: currentSpeaker = "Ilaryx"; break;
                case 52: currentSpeaker = player.getName(); break;
                case 53: currentSpeaker = "Ilaryx"; break;
                case 54: currentSpeaker = ""; break;
                case 56: currentSpeaker = player.getName(); break;
                case 58: currentSpeaker = ""; break;
                case 60: setBackgroundImage("nation2_bg5.png"); showNPC = false; showPlayer = false; break;
                case 61: updateScene("Lunareth", true); showPlayer=false; currentSpeaker = "Lunareth"; break;
                case 62: setBackgroundImage("nation2_bg6.png"); currentSpeaker = ""; showNPC = false; showPlayer = false; break;
                case 63: updateScene(player.getName(), false); currentSpeaker = ""; break;
                case 64: setBackgroundImage("nation2_bg7.png"); currentSpeaker = ""; showPlayer = false; break;
                case 66: setBackgroundImage("nation2_bg8.png"); showPlayer = true; break;
                case 67: updateScene("Lunareth", true); currentSpeaker = "Lunareth"; break;
                case 68: currentSpeaker = player.getName(); break;
                case 69: currentSpeaker = "Lunareth"; break;
                case 70: currentSpeaker = player.getName(); break;
                case 71: currentSpeaker = "Lunareth"; break;
                case 72: currentSpeaker = player.getName(); break;
                case 73: currentSpeaker = "Lunareth"; break;
                case 74: game.showScreen("battle"); updateScene("Lunareth", true); currentSpeaker = ""; break;
                case 75: currentSpeaker = "Lunareth"; break;
                case 76: currentSpeaker = player.getName(); break;
                case 77: currentSpeaker = "Lunareth"; break;
                case 78: currentSpeaker = ""; break;
                case 79: currentSpeaker = player.getName(); showNPC=false; break;
                case 80: currentSpeaker = ""; break;
                case 82: setBackgroundImage("nation2_bg9.png"); currentSpeaker = player.getName(); break;
            }
        }

        if (currentNation == 3) {
            switch (lineIndex) {
                case 0: setBackgroundImage("nation3_bg1.png"); currentSpeaker = ""; break;
                case 1:
                case 2:
                case 3: currentSpeaker = player.getName(); break;
                case 4: setBackgroundImage("nation3_bg2.png"); currentSpeaker = ""; break;
                case 5: updateScene("Spirit of Lunareth", true); currentSpeaker = "??Lunareth??"; break;
                case 6: updateScene("Spirit of The Archivist", true); currentSpeaker = "??Archivist??"; break;
                case 7: currentSpeaker = player.getName(); break;
                case 8: updateScene("Spirit of Lunareth", true); currentSpeaker = "??Lunareth??"; break;
                case 9: currentSpeaker = ""; showNPC = false; break;
                case 10: setBackgroundImage("nation3_bg3.png"); currentSpeaker = ""; break;
                case 11: updateScene("Sarukdal", true); currentSpeaker = "??Sarukdal??"; break;
                case 12: currentSpeaker = ""; break;
                case 13:
                case 14:
                case 15: currentSpeaker = player.getName(); break;
                case 16: currentSpeaker = ""; break;
                case 17: updateScene("Random NPC", true); currentSpeaker = "????"; break;
                case 18: setBackgroundImage("nation3_bg4.png"); updateScene("Sarukdal", true); currentSpeaker = ""; break;
                case 19: currentSpeaker = ""; break;
                case 20: updateScene("Shadow Voice", true); currentSpeaker = "....."; break;
                case 21: currentSpeaker = player.getName(); break;
                case 22: currentSpeaker = "....."; break;
                case 23: currentSpeaker = player.getName(); break;
                case 24: currentSpeaker = "....."; break;
                case 25: currentSpeaker = player.getName(); break;
                case 26: currentSpeaker = ""; showNPC = false; break;
                case 27: setBackgroundImage("nation3_bg5.png"); updateScene("Sarukdal", true); currentSpeaker = "Sarukdal"; break;
                case 28: currentSpeaker = player.getName(); break;
                case 29: currentSpeaker = "Sarukdal"; break;
                case 30:
                case 31: currentSpeaker = player.getName(); break;
                case 32: currentSpeaker = ""; showNPC = false; game.showScreen("battle"); break; 
                case 33: currentSpeaker = ""; showNPC = false; game.flashlightBlinkTransition("quest3"); break;
                case 34: setBackgroundImage("nation3_bg6.png"); currentSpeaker = ""; break;
                case 35: updateScene("Sarukdal Defeated", true); currentSpeaker = "Sarukdal"; break;
                case 36: currentSpeaker = player.getName(); break;
                case 37: currentSpeaker = "Sarukdal"; break;
                case 38: currentSpeaker = player.getName(); break;
                case 39: currentSpeaker = "Sarukdal"; break;
                case 40: currentSpeaker = ""; showNPC = false; break;
                case 41:
                case 42: currentSpeaker = player.getName(); break;
                case 43: setBackgroundImage("nation3_bg7.png"); currentSpeaker = player.getName(); break;
                case 44: currentSpeaker = ""; game.flashlightBlinkTransition("quest4"); break;
                case 45: setBackgroundImage("nation3_bg8.png"); currentSpeaker = player.getName(); break;
                case 46:
                case 47:
                case 48: currentSpeaker = player.getName(); break;
                case 49: bgm.playSFX("jumpscare.wav"); currentSpeaker = ""; break;
                case 50: setBackgroundImage("nation3_bg9.png"); updateScene("Elarion", true); currentSpeaker = "Elarion"; break;
                case 51: currentSpeaker = ""; showNPC = false; break;
                case 52:
                case 53: currentSpeaker = player.getName(); break;
                case 54: updateScene("Elarion", true); currentSpeaker = "Elarion"; break;
                case 55: currentSpeaker = player.getName(); break;
                case 56: currentSpeaker = "Elarion"; break;
                case 57: currentSpeaker = player.getName(); break;
                case 58: currentSpeaker = "Elarion"; break;
                case 59: currentSpeaker = player.getName(); break;
                case 60: currentSpeaker = "Elarion"; break;
                case 61: currentSpeaker = ""; showNPC = false; game.showScreen("battle"); break; 
                case 62: currentSpeaker = ""; break;
                case 63: updateScene("Elarion", true); currentSpeaker = "Elarion"; break;
                case 64: currentSpeaker = player.getName(); break;
                case 65: currentSpeaker = ""; bgm.playSFX("nation1_sfx3.wav"); showNPC = false; break;
                case 66:
                case 67: currentSpeaker = player.getName(); break;
                case 68: setBackgroundImage("nation3_bg10.png"); currentSpeaker = ""; break;
                case 69: currentSpeaker = ""; break;
                case 70:
                case 71:
                case 72:
                case 73: currentSpeaker = player.getName(); break;
                case 74: startEndingTransition(); break;
            }
        }
        repaint();
    }

    private void updateScene(String speaker, boolean isNPC) {
        this.currentSpeaker = speaker;
        if (isNPC) {
            Character npcData = npcManager.get(speaker);
            if (npcData != null) {
                this.npcSprite = npcData.getIdleSprite();
                this.showNPC = true;
            } else {
                this.showNPC = false; 
            }
        } else { 
            this.showNPC = false; 
        }
        this.showPlayer = true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // 1. Draw Background normally (NO FADE)
        if (currentBackground != null && currentBackground.isLoaded()) {
            g2.drawImage(currentBackground.getCurrentFrame(), 0, 0, getWidth(), getHeight(), null);
        }

        if (player == null) return;
        String heroName = player.getName();

        // Characters fade automatically because we updated drawCharacter above!
        if (showPlayer && playerSprite != null) {
            drawCharacter(g2, playerSprite, 50, getHeight() - 550, 450, 450, currentSpeaker.equals(heroName));
        }

        if (showNPC && npcSprite != null) {
            boolean npcIsTalking = !currentSpeaker.equals(heroName) && !currentSpeaker.trim().isEmpty();
            drawCharacter(g2, npcSprite, getWidth() - 500, getHeight() - 550, 450, 450, npcIsTalking);
        }

        // 2. Wrap all UI/Dialogue boxes in the Alpha Composite
        if (uiAlpha > 0) {
            Composite oldComp = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, uiAlpha));
            
            // Keep your existing drawing logic here
            if (gameState == PLAY_STATE) {
                drawDialogueBox(g2);
            } else if (gameState == INVENTORY_STATE || gameState == SUB_MENU_STATE) {
                MenuRenderer.drawInventory(g2, player, slotCol, slotRow, scrollOffset);
                drawDialogueBox(g2);
                if (gameState == SUB_MENU_STATE) MenuRenderer.drawSubMenu(g2, subMenuCursor); 
            } else if (gameState == SHOP_STATE) {
                MenuRenderer.drawShop(g2, (int)player.getGold(), gameShop.getStock(), slotCol, slotRow, shopResultMessage);
                drawDialogueBox(g2);
            }
            if (gameState == OPTIONS_STATE) {
                MenuRenderer.drawOptionsOverlay(g2, optionsCursor);
            }
            
            g2.setComposite(oldComp); // Reset it
        }
    }

    private void drawCharacter(Graphics2D g2, Sprite s, int x, int y, int w, int h, boolean isSpeaking) {
        BufferedImage frame = s.getCurrentFrame();
        if (frame != null) {
            // Multiply the standard alpha by the fading UI alpha
            float baseAlpha = isSpeaking ? 1.0f : 0.5f; 
            float finalAlpha = baseAlpha * uiAlpha; 
            if (finalAlpha < 0) finalAlpha = 0;
            
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, finalAlpha));
            g2.drawImage(frame, x, y, w, h, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f)); 
        }
    }

    private void drawDialogueBox(Graphics2D g2) {
        int boxX = 50, boxY = getHeight() - 220, boxW = getWidth() - 100, boxH = 180;
        g2.setColor(Color.BLACK); 
        g2.fillRoundRect(boxX, boxY, boxW, boxH, 20, 20);
        g2.setColor(new Color(181, 153, 110, 255));
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(boxX, boxY, boxW, boxH, 20, 20);

        if (lineIndex == 0 && currentNation == 1 && gameState != SHOP_STATE) {
            int iconY = boxY + (boxH / 2) + 12; 
            int itemSpacing = 220; 
            int startX = boxX + (boxW - (itemSpacing * 3 + 150)) / 2;

            drawInstructionKey(g2, "ENTER", "Progress", startX, iconY);
            drawInstructionKey(g2, "I", "Inventory", startX + 230, iconY);
            drawInstructionKey(g2, "P", "Shop", startX + 430, iconY);
            drawInstructionKey(g2, "O", "Options", startX + 610, iconY);
            
            return;
        }

        if (gameState == SHOP_STATE) {
            drawWrappedText(g2, shopGreeting, boxX + 40, boxY + 50, boxW - 80);
            return; 
        }
            
        if (!currentSpeaker.trim().isEmpty()) { 
            g2.setColor(Color.BLACK); 
            g2.fillRect(boxX + 20, boxY - 30, 200, 40);
            g2.setColor(new Color(181, 153, 110, 255));
            g2.drawRect(boxX + 20, boxY - 30, 200, 40);
            g2.setColor(Color.WHITE);
            g2.setFont(mediumText);
            g2.drawString(currentSpeaker, boxX + 40, boxY - 2);
        }

        g2.setColor(Color.WHITE);
        g2.setFont(smallText);

        if (lineIndex < dialogueText.length) {
            drawWrappedText(g2, dialogueText[lineIndex], boxX + 25 + 15, boxY + 25 + 25, boxW - (25 * 2) - 30);
        }
    }

    private void drawInstructionKey(Graphics2D g2, String key, String desc, int x, int y) { 
        g2.setFont(smallText);
        FontMetrics fmKey = g2.getFontMetrics();
        
        int keyWidth = Math.max(45, fmKey.stringWidth(key) + 20); 
        int boxHeight = 40;

        g2.setColor(Color.BLACK); 
        g2.fillRoundRect(x, y - 28, keyWidth, boxHeight, 10, 10);
        
        g2.setColor(Color.WHITE); 
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x, y - 28, keyWidth, boxHeight, 10, 10);

        g2.setColor(Color.WHITE);
        int textX = x + (keyWidth - fmKey.stringWidth(key)) / 2;
        g2.drawString(key, textX, y);

        g2.setFont(mediumText);
        g2.setColor(new Color(40, 20, 10)); 
        g2.drawString(desc, x + keyWidth + 15, y);
    }

    private void handleGridMovement(int keyCode) {
        ArrayList<Item> currentList = (gameState == INVENTORY_STATE) ? player.getInventory() : gameShop.getStock();

        if (keyCode == KeyEvent.VK_W && slotRow > 0) slotRow--;
        else if (keyCode == KeyEvent.VK_S && slotRow < 3) slotRow++;
        else if (keyCode == KeyEvent.VK_A && slotCol > 0) slotCol--;
        else if (keyCode == KeyEvent.VK_D && slotCol < 4) slotCol++;
        
        if (keyCode == KeyEvent.VK_ENTER && !currentList.isEmpty()) {
            int index = slotCol + (slotRow * 5); 

            if (index < currentList.size()) {
                if (gameState == SHOP_STATE) {
                    shopResultMessage = gameShop.buyItem(index, player);
                } 
                else if (gameState == INVENTORY_STATE) {
                    gameState = SUB_MENU_STATE;
                    subMenuCursor = 0;
                }
            }
        } else {
            updateHoverDescription();
        }
    }

    private void updateHoverDescription() {
        ArrayList<Item> list = (gameState == INVENTORY_STATE) ? player.getInventory() : gameShop.getStock();
        int cols = 5; 
        int index = slotCol + (slotRow * cols);
        
        if (index >= 0 && index < list.size()) {
            Item item = list.get(index);
            if (gameState != SHOP_STATE) {
                String info = item.getName() + ": " + item.getDescription();
                dialogueText[lineIndex] = info;
            }
        }
    }

    private void handleSubMenuInput(int code) {
        if (code == KeyEvent.VK_W && subMenuCursor > 0) subMenuCursor--;
        if (code == KeyEvent.VK_S && subMenuCursor < 2) subMenuCursor++;
        if (code == KeyEvent.VK_ENTER) executeAction();
        if (code == KeyEvent.VK_ESCAPE) gameState = INVENTORY_STATE;
    }

    private void executeAction() {
        int index = slotCol + (slotRow * 5);
        ArrayList<Item> inv = player.getInventory();

        if (index >= inv.size()) return;

        Item selected = inv.get(index);

        if (subMenuCursor == 0) { 
            dialogueText[lineIndex] = InventoryManager.useItem(selected, player);
            if (selected.isConsumable()) inv.remove(index);
        } 
        else if (subMenuCursor == 1) {
            dialogueText[lineIndex] = InventoryManager.sellItem(selected, player);
            if (!selected.getName().equals("Father")) inv.remove(index);
        } 
        else if (subMenuCursor == 2) { 
            inv.remove(index);
            dialogueText[lineIndex] = "Discarded " + selected.getName();
        }

        gameState = INVENTORY_STATE;
        repaint();
    }

    private void resetCursor() { slotCol = 0; slotRow = 0; subMenuCursor = 0; scrollOffset = 0; }

    private void setBackgroundImage(String file) { 
        this.currentBackground = backgroundRegistry.get(file); 
        repaint(); 
    }

    private void loadBG(String name, int frameWidth, int frameHeight, int frameCount) {
        try {
            String path = "/EchoesOfTheOath/Resources/" + name;
            Sprite bgSprite = new Sprite(path, frameWidth, frameHeight, frameCount);

            if(name.equals("nation1_bg8.2.png")||name.equals("nation1_storyline1.png")) {
                bgSprite.setLooping(false);
            }
            if (bgSprite.isLoaded()) {
                backgroundRegistry.put(name, bgSprite);
            } else {
                System.out.println("Failed to load Sprite: " + name);
            }
        } catch (Exception e) { 
            System.out.println("Error pre-loading: " + name); 
        }
    }

    private void drawWrappedText(Graphics2D g2, String line, int x, int y, int maxWidth) {
        g2.setFont(mediumText);
        FontMetrics fm = g2.getFontMetrics();
        String[] words = line.split(" ");
        StringBuilder currentLine = new StringBuilder();

        int lineHeight = fm.getHeight() + 5;
        int drawY = y;

        for (String word : words) {
            if (fm.stringWidth(currentLine.toString() + word) < maxWidth) {
                currentLine.append(word).append(" ");
            } else {
                g2.drawString(currentLine.toString(), x, drawY);
                drawY += lineHeight;
                currentLine = new StringBuilder(word).append(" ");
            }
        }
        g2.drawString(currentLine.toString(), x, drawY);
    }

    private void startEndingTransition() {
        isFadingOut = true;
        
        fadeOutTimer = new Timer(50, e -> {
            uiAlpha -= 0.05f;
            
            if (uiAlpha <= 0f) {
                uiAlpha = 0f;
                fadeOutTimer.stop();
                game.showScreen("credits"); // Move to credits when UI is fully gone
            }
            repaint();
        });
        fadeOutTimer.start();
    }
}