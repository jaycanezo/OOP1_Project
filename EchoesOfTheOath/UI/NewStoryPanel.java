package EchoesOfTheOath.UI;

import EchoesOfTheOath.Characters.*;
import EchoesOfTheOath.Characters.Character;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class NewStoryPanel extends JPanel {
    private GameWindow game;
    private Image currentBackground;
    private Sprite playerSprite;
    private Sprite npcSprite; 
    private java.util.Map<String, Npc> npcRegistry = new java.util.HashMap<>();
    private java.util.Map<String, Image> backgroundRegistry = new java.util.HashMap<>();
    
    private String currentSpeaker = ""; 
    private String[] dialogueText = {
        "Press \"Enter/Space\" to progress or 'I' for Inventory, and 'S' to visit the Shop anytime.",
        "(The hero walks along a dirt path toward the outskirts. The air is thick with the smell of wet stone and smoke.)", 
        "Humanas. From a distance, the walls look strong. Up close, they're just holding up a graveyard.",
        "Gates are broken. Guards look half-starved. This isn't a kingdom; it's a cage.",
        "(The hero enters the city. The streets are empty. No trade, no talk—only the sound of the hero's footsteps on the stone.)",
        "(A Gatekeeper sits on a crate, tiredly sharpening a rusted blade. He looks up as the hero approaches.)", 
        "“Whatever you’re looking for, traveler, we don't have it. Food’s gone. Gold’s gone. Move on.”", 
        "“Where is everyone? The streets are dead.”", 
        "Hiding. Collectors are making their rounds. If you want to keep that gear, stay out of the light. They take everything to the castle for the ‘King’s blessing.",
        "The King’s blessing. In a place this miserable, that sounds like a threat.",
        "(The hero walks into the central plaza. Rows of empty gallows line the square, and in the center stands a massive, polished gold statue of a toddler—**King Bartholomew**—shining unnaturally against the grey buildings.)",
        "(A **cloaked man** stands by the statue, watching the hero.)",
        "“Beautiful, isn't it? A golden boy for a leaden kingdom”",
        "“Why is a golden child standing in the middle of a slum?”",
        "“That’s King Bartholomew. Or at least, the version they want us to see. Every coin stolen from these streets goes into that castle to keep him 'happy.' They say he hasn't aged a day in years. They say his voice can stop your heart.”",
        "“A child king who never grows up... and steals from his own people. I need to see him for myself.”",
        "“Many do. None come back. If you’re that eager to lose your head, go to the Old Chapel. That’s where the collectors bring the 'offerings' before they head to the castle. You’ll find a way in there—if the guards don’t find you first.”",
        "(The Informant slips away into a side street.)",
        "“A child king who doesn't age. There’s something wrong with that throne.”",
        "“The 'Old Oath' is pulling at me. It’s not just curiosity anymore. I need to know who—or what—is actually wearing that crown.”",
        "(The hero reaches the Chapel. The doors are barred with heavy iron. There’s no singing inside—only the cold sound of metal coins being sorted and the occasional muffled shout.)",
        "“The Chapel is just a counting house now. But it’s the only path that leads to the castle.”",
        "“Bartholomew... let's see if you're as golden as your statue.”",
        "\"The castle is too quiet. No servants, no life. Just the sound of my own breath.\"",
        "\"They say King Bartholomew hasn't spoken a word in years, yet his decrees have starved half the nation.\"",
        "\"Someone is whispering to be standing behind the silk curtains. Someone who remembers every debt this city owes.\"",
        "(The room is massive. Thick curtains block all natural light. In the center, a golden crib sits atop a high marble dais. Two masked Attendants stand perfectly still behind it.)",
        "“You aren't on the guest list, traveler.”",
        "“I’m not here for a party. I’m here to talk to the King.”",
        "“The King doesn't 'talk.' He decides. And according to the records, your life is currently in arrears.”",
        "“Who’s writing those records? The boy can’t even hold a pen.”",
        "“His Majesty is... displeased with your tone.”",
        "(Inside the crib, the toddler sits up. His eyes are milky and vacant, glowing with a green light. He doesn't cry or scream. The massive crown on his head hums, and the shadows of the pillars begin to crawl across the floor like living ink.)",
        "\"That’s not a child’s anger. That’s a programmed response.\"",
        "\"Whatever is in that crown is using him like a conduit.\"",
        "“The decree is signed. Clear the debt.”",
        "(The Hero draws their weapon as the shadows solidify into tall, faceless sentinels.)",
        "(The shadows melt back into the floor. The red glow leaves the child’s eyes, and he collapses back into the pillows, looking small and exhausted. The attendants have vanished into the dark corners of the room.)"
    };
    private int lineIndex = 0;
    
    private boolean showPlayer = false;
    private boolean showNPC = false;

    public NewStoryPanel(GameWindow game) {
        this.game = game;
        
        // 1. Initialize Registries
        npcRegistry.put("Guard", new Npc1()); 
        npcRegistry.put("Informant", new Npc2()); 
        npcRegistry.put("Attendant", new Npc3());

        loadBG("nation1_bg1.png");
        loadBG("nation1_bg2.png");
        loadBG("nation1_bg3.png");
        loadBG("nation1_bg4.png");
        loadBG("nation1_bg5.png");
        loadBG("nation1_bg6.png");
        loadBG("nation1_bg6.1.png");

        this.setFocusable(true);
        this.setLayout(null); 

        // 2. Animation Timer
        Timer animationTimer = new Timer(100, e -> {
            if (playerSprite != null) playerSprite.update();
            if (npcSprite != null) npcSprite.update();
            repaint();
        });
        animationTimer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
                    progressStory();
                }

                if(e.getKeyCode() == KeyEvent.VK_I) {
                    game.showScreen("inventory");
                }

                if(e.getKeyCode() == KeyEvent.VK_S) {
                    game.showScreen("shop");
                }
            }
        });
    }

    private void loadBG(String name) {
        try {
            Image img = new ImageIcon(getClass().getResource("/EchoesOfTheOath/Resources/" + name)).getImage();
            backgroundRegistry.put(name, img);
        } catch (Exception e) { System.out.println("Error pre-loading: " + name); }
    }

    public void loadSelectedHero() {
        Character chosen = game.getChosenCharacter();
        if (chosen != null) {
            this.playerSprite = chosen.getIdleSprite();
        }
        setBackgroundImage("nation1_bg1.png"); 
        this.showPlayer = true; // Ensure player is visible at start
    }

    public void setBackgroundImage(String fileName) {
        this.currentBackground = backgroundRegistry.get(fileName);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Clears the previous frame
        Graphics2D g2 = (Graphics2D) g;

        // 1. DRAW BACKGROUND FIRST
        if (currentBackground != null) {
            g2.drawImage(currentBackground, 0, 0, getWidth(), getHeight(), null);
        }

        String heroName = (game.getChosenCharacter() != null) ? game.getChosenCharacter().getName() : "";

        // 2. DRAW PLAYER
        if (showPlayer && playerSprite != null) {
            drawCharacter(g2, playerSprite, 50, getHeight() - 550, 450, 450, 
                        currentSpeaker.equals(heroName)); 
        }

        // 3. DRAW NPC
        if (showNPC && npcSprite != null) {
            // NPC highlights if the current speaker is NOT the hero and is NOT empty
            boolean npcIsTalking = !currentSpeaker.equals(heroName) && !currentSpeaker.trim().isEmpty();
            drawCharacter(g2, npcSprite, getWidth() - 500, getHeight() - 550, 450, 450, npcIsTalking); 
        }

        // 4. DRAW UI
        drawDialogueBox(g2);
    }

    private void drawCharacter(Graphics2D g2, Sprite s, int x, int y, int w, int h, boolean isSpeaking) {
        BufferedImage frame = s.getCurrentFrame();
        if (frame != null) {
            float alpha = isSpeaking ? 1.0f : 0.5f; 
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2.drawImage(frame, x, y, w, h, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f)); 
        }
    }

    private void drawDialogueBox(Graphics2D g2) {
        int boxX = 50, boxY = getHeight() - 220, boxW = getWidth() - 100, boxH = 180;

        g2.setColor(new Color(0, 0, 0, 200)); 
        g2.fillRoundRect(boxX, boxY, boxW, boxH, 20, 20);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(boxX, boxY, boxW, boxH, 20, 20);

        if (!currentSpeaker.trim().isEmpty()) { 
            g2.setColor(new Color(50, 50, 150)); 
            g2.fillRect(boxX + 20, boxY - 30, 200, 40);
            g2.setColor(Color.WHITE);
            g2.drawRect(boxX + 20, boxY - 30, 200, 40);
            g2.setFont(new Font("Serif", Font.BOLD, 22));
            g2.drawString(currentSpeaker, boxX + 40, boxY - 2);
        }

        g2.setFont(new Font("Monospaced", Font.PLAIN, 20));
        if (lineIndex < dialogueText.length) {
            drawWrappedText(g2, dialogueText[lineIndex], boxX + 40, boxY + 50, boxW - 80);
        }
    }

    private void drawWrappedText(Graphics2D g2, String line, int x, int y, int maxWidth) {
        g2.setFont(new Font("Monospaced", Font.BOLD, 22));
        FontMetrics fm = g2.getFontMetrics();
        String[] words = line.split(" ");
        StringBuilder currentLine = new StringBuilder();
        int lineHeight = fm.getHeight();
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
    
    private void progressStory() {
        lineIndex++; 

        if (lineIndex == 35) {
            game.showScreen("battle");
            return;
        }

        if(lineIndex >= dialogueText.length) {
            game.showScreen("battle");
            return; 
        }
        
        switch (lineIndex) {
            case 2:
                setBackgroundImage("nation1_bg2.png");
                currentSpeaker = ""; // Narrator
                break;
            case 5:
                updateScene("Guard", true);
                currentSpeaker = "";
                break;
            case 6:
                updateScene("Guard", true);
                break;
            case 7:
                currentSpeaker = game.getChosenCharacter().getName();
                break;
            case 8:
                updateScene("Guard", true);
                break;
            case 9:
                currentSpeaker = ""; // Narration line for plaza
                break;
            case 10:
                setBackgroundImage("nation1_bg3.png");
                this.showNPC = false;
                currentSpeaker = "";
                break;
            case 11:
                updateScene("Informant", true);
                currentSpeaker = ""; // Narration line for cloaked man
                break;
            case 12:
                currentSpeaker = "Informant";
                break;
            case 13:
                currentSpeaker = game.getChosenCharacter().getName();
                break;
            case 14:
                currentSpeaker = "Informant";
                break;
            case 15:
                currentSpeaker = game.getChosenCharacter().getName();
                break;
            case 16:
                currentSpeaker = "Informant";
                break;
            case 17:
                setBackgroundImage("nation1_bg4.png");
                
                currentSpeaker = " ";
                break;
            case 18:
                updateScene(game.getChosenCharacter().getName(), false);
                break;
            case 20:
                currentSpeaker = " ";
                break;
            case 23:
                setBackgroundImage("nation1_bg5.png");
                updateScene(game.getChosenCharacter().getName(), false);
                currentSpeaker = "";
                break;
            case 25:
                setBackgroundImage("nation1_bg6.png");
                break;
            case 26:
                updateScene("Attendant", true);
                currentSpeaker = "Attendant";
                break;
            case 27:
                currentSpeaker = game.getChosenCharacter().getName();
                break;
            case 28:
                currentSpeaker = "Attendant";
                break;
            case 29:
                currentSpeaker = game.getChosenCharacter().getName();
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
                setBackgroundImage("nation1_bg6.png");
                updateScene(game.getChosenCharacter().getName(), false);
                currentSpeaker = "";
                break;
            case 34:
                currentSpeaker = "Attendant";
                break;
            case 35:
                currentSpeaker = game.getChosenCharacter().getName();
                break;
        }
        repaint();
    }

    private void updateScene(String speaker, boolean isNPC) {
        this.currentSpeaker = speaker;
        if (isNPC && npcRegistry.containsKey(speaker)) {
            this.npcSprite = npcRegistry.get(speaker).getIdleSprite();
            this.showNPC = true;
        } else {
            this.showNPC = false;
        }
        this.showPlayer = true; 
    }
}
