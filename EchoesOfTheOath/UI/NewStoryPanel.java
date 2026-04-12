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
    private Sprite currentBackground;
    private Sprite playerSprite;
    private Sprite npcSprite; 
    private java.util.Map<String, Character> npcRegistry = new java.util.HashMap<>();
    private java.util.Map<String, Sprite> backgroundRegistry = new java.util.HashMap<>();
    
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
        "(The shadows melt back into the floor. The red glow leaves the child’s eyes, and he collapses back into the pillows, looking small and exhausted. The attendants have vanished into the dark corners of the room.)",
        "“He’s just a kid. He didn't even know he was fighting.”",
        "(The Hero reaches into the crib. The boy doesn't pull away; he just stares, confused. The Hero’s pendant pulses with a cold, warning light.)",
        "\"The 'King' is an empty shell. He wasn't the one signing those death warrants.\"",
        "\"The Attendants weren't looking at the boy for orders—they were looking at the shadows behind the throne.\"",
        "“The library. The Gatekeeper mentioned a place where the 'debts' are kept.”",
        "“If the King isn't the one pulling the strings, then I’ve been hunting the wrong man.”",
        "(The Hero looks at a trail of black ink leading from the base of the throne toward a heavy, reinforced trapdoor in the floor.)",
        "\"The records. Every coin, every soul, every drop of blood... it all leads down there.\"",
        "\"To the one who keeps the books.\"",
        " “Your oath was to protect the innocent… find the one who stole his voice.”",
        "(A single candle flickers in the distance, revealing a pair of unblinking eyes watching the Hero...)",
        "(The Hero goes down a tight spiral staircase. The walls are stained with black ink that seems to pulse. The sound of a scratching quill fills the air, echoing like a heartbeat.)",
        "(A massive, cold vault. Rows of scrolls stretch into the dark. The Archivist sits behind a desk of stacked parchment. He doesn't move; only his eyes follow the Hero.)",
        "“You’ve caused a lot of damage upstairs. My ledger hasn't been this messy in centuries.”",
        "“The King is a puppet. You’ve been using a child to bleed this city dry.”",
        "“I don't use anyone. I record. If a King chooses to let his fear dictate the law, I simply write it down. The people of Humanas are a collection of debts. I am the only one who ensures they aren't forgotten.”",
        "“At the cost of their lives? That’s not a record. It’s a prison.”",
        "“Truth is a weight most can't carry. You should know that better than anyone, Wanderer. Your own story is nothing but torn pages and broken promises.”",
        "(The Hero winces. A flash of white light hits their mind: two shadows standing beside them—an archer, a mage—and a looming darkness that swallowed them both. The Hero grips their head.)",
        "The others... where are they? Why does it feel like I left something behind?”",
        "“Forgetfulness is a mercy. But the ink remembers. Let’s see what yours says about your ending.”",
        "(The Archivist stands, his robes unfurling like thousands of sharp scrolls. The Hero is panting, leaning on their weapon.)",
        "“The records demand completion! You cannot run from a debt you’ve already signed!”",
        "(A scroll flies past the Hero’s face. For a split second, they see a word written in glowing light: *ELARION*.)",
        "“That name... I’ve heard it before. In the dream. We were supposed to... we were supposed to bind it.”",
        "(The Hero’s pendant glows with a violent, flickering light. A voice, distant and distorted, echoes in their mind.)",
        "“We vow to bind the darkness... within ourselves...”",
        "“The oath... it’s not just a story. It’s why I’m here. I didn't just survive. I failed.”",
        "(The Hero’s weapon ignites with a raw, unstable energy. They lung forward, driving the blade through the center of the Archivist’s desk and into his chest.)",
        "(The library begins to dissolve into ash. The scrolls turn into black flakes that drift toward the ceiling.)",
        "“You think... freeing them... changes anything? You only broke... one link... in a chain... you helped forge.”",
        "“Maybe. But I’m the one who’s going to break the rest.”",
        "(The Archivist vanishes. The Hero picks up a single blank page. Their hand is shaking.)",
        "\"The library is gone, but the weight in my chest is heavier than ever.\"",
        "\"I saw faces in those scrolls. Faces I should know. An archer... a mage...\"",
        "\"Whatever we promised to do, we didn't finish it. This nation was just the start.\"",
        "(The Hero looks up. The sun shines through the rubble. Far off in the distance, across the sea, a purple forest glows on the horizon.)",
        "“Veyora. The Elves. If the fragments of what I lost are there... I have to find them.”",
        "(The screen fades to black as the Hero walks toward the light.)",
        "",

    };
    private int lineIndex = 0;
    
    private boolean showPlayer = false;
    private boolean showNPC = false;

    public NewStoryPanel(GameWindow game) {
        this.game = game;
        // 1. Initialize Registries
        // Correct concrete instantiation
        npcRegistry.put("Guard", new Npc1()); 
        npcRegistry.put("Informant", new Npc2()); 
        npcRegistry.put("Attendant", new Npc3());
        npcRegistry.put("Archivist", new Archivist());

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
        loadBG("nation1_storyline1.png", 1920, 1080, 7);

        this.setFocusable(true);
        this.setLayout(null); 

        // 2. Animation Timer
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

    private void loadBG(String name, int frameWidth, int frameHeight, int frameCount) {
        try {
            // Construct the full path
            String path = "/EchoesOfTheOath/Resources/" + name;
            
            // Create a new Sprite object
            // Assuming your standard window size is 1920x1080 and it's a 1-frame image
            Sprite bgSprite = new Sprite(path, frameWidth, frameHeight, frameCount);

            if(name.equals("nation1_bg8.2.png")) {
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

    public void loadSelectedHero() {
        Character chosen = game.getChosenCharacter();
        if (chosen != null) {
            this.playerSprite = chosen.getIdleSprite();
        }

        setBackgroundImage("nation1_bg1.png");

        if(lineIndex == 37) {
            setBackgroundImage("nation1_bg6.png");
        }

        if(lineIndex == 59) {
            setBackgroundImage("nation1_bg8.png");
        }


        this.showPlayer = true; // Ensure player is visible at start
    }

    public void setBackgroundImage(String fileName) {
        // Look up the Sprite from the new registry
        this.currentBackground = backgroundRegistry.get(fileName);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Clears the previous frame
        Graphics2D g2 = (Graphics2D) g;

        // 1. DRAW BACKGROUND FIRST
            if (currentBackground != null) {
            BufferedImage frame = currentBackground.getCurrentFrame();
            if (frame != null) {
                g2.drawImage(frame, 0, 0, getWidth(), getHeight(), null);
            }
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

        g2.setColor(new Color(181, 153, 110, 200)); 
        g2.fillRoundRect(boxX, boxY, boxW, boxH, 20, 20);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(boxX, boxY, boxW, boxH, 20, 20);

        if (!currentSpeaker.trim().isEmpty()) { 
            g2.setColor(new Color(111, 78, 55)); 
            g2.fillRect(boxX + 20, boxY - 30, 200, 40);
            g2.setColor(Color.BLACK);
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
        MusicPlayer bgm = new MusicPlayer();
        lineIndex++; 

        if(lineIndex >= dialogueText.length) {
            game.showScreen("battle");
            return; 
        }
        
        switch (lineIndex) {
            case 0:
                bgm.stopMusic();
                bgm.playMusic("nation1_bgm.WAV");
                break;
            case 1:
                bgm.playSFX("nation1_sfx1.wav");
                break;
            case 2:
                setBackgroundImage("nation1_bg2.png");
                currentSpeaker = ""; // Narrator
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
                bgm.playSFX("nation1_sfx1.wav");
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
            case 22:
                bgm.playSFX("nation1_sfx3.wav");
                break;
            case 23:
                setBackgroundImage("nation1_bg5.png");
                game.getBgm().stopMusic();
                game.getBgm().playMusic("nation1_bgm2.WAV");
                updateScene(game.getChosenCharacter().getName(), false);
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
                currentSpeaker = game.getChosenCharacter().getName();
                break;
            case 29:
                currentSpeaker = "Attendant";
                break;
            case 30:
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
                showPlayer = true;
                break;
            case 36:
                setBackgroundImage("nation1_bg6.png");
                currentSpeaker = "Attendant";
                showNPC = true;
                break;
            case 37:
                //game.showScreen("battle");
                currentSpeaker = game.getChosenCharacter().getName();
                showNPC = false;
                break;
            case 38:
                setBackgroundImage("nation1_bg6.png");
                currentSpeaker = "";
                break;
            case 39:
                currentSpeaker = game.getChosenCharacter().getName();
                break;
            case 40:
                setBackgroundImage("nation1_bg6.1.png");
                currentSpeaker = "";
                break;
            case 42:
                setBackgroundImage("nation1_bg6.png");
                currentSpeaker = game.getChosenCharacter().getName();
                break;
            case 45:
                currentSpeaker ="";
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
                currentSpeaker = game.getChosenCharacter().getName();
                break;
            case 53:
                currentSpeaker = "Archivist";
                break;
            case 54:
                currentSpeaker = game.getChosenCharacter().getName();
                break;
            case 55:
                currentSpeaker = "Archivist";
                break;
            case 56:
                setBackgroundImage("nation1_storyline1.png");
                showNPC = false;
                showPlayer = false;
                break;
            case 57:
                setBackgroundImage("nation1_bg8.png");
                currentSpeaker = game.getChosenCharacter().getName();
                showPlayer = true;
                showNPC = true;
                break;
            case 58:
                currentSpeaker = "Archivist";
                break;
            case 59:
                game.showScreen("battle");
                showPlayer = true;
                showNPC = true;
                break;
            case 60:
                currentSpeaker = "Archivist";
                break;
            case 61:
                currentSpeaker = "";
                break;
            case 62:
                currentSpeaker = game.getChosenCharacter().getName();
                break;
            case 63:
                currentSpeaker = "";
                break;
            case 65:
                currentSpeaker = game.getChosenCharacter().getName();
                break;
            case 68:
                currentSpeaker = "Archivist";
                break;
            case 69:
                currentSpeaker = game.getChosenCharacter().getName();
                break;
            case 70:
                showNPC=false;
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
