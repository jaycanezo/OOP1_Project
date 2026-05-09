package EchoesOfTheOath.UI;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class CreditsPanel extends JPanel {
    private GameWindow game;
    private Timer scrollTimer;
    private int scrollY; 
    private float fadeAlpha = 0f;
    
    private Font titleFont;
    private Font nameFont;
    private Font descFont;
    private Image backgroundImage;

    private final int TOTAL_MEMBERS = 7;
    private String[] memberNames = {
        "Jay R. Cañezo", "April Anne O. Tandoc", "Eden Agnes L. Olivar", 
        "Kaina B. Padilla", "Althea M. Ambil", "Jackielou C. Abelada", "Kenn Migan Vincent C. Gumonan"
    };
    private String[] memberRoles = {
        "Project Manager and Lead Programmer", "Co-lead Programmer", "Lead Art Designer, Writer", 
        "Art Designer", "QA Tester", "Honorary Member", "Mentor"
    };
    private String[] memberDesc = {
        "Built the core structure, designed the combat,\nand sound systems",
        "Crafted the settings, inventory, shop,\nand auto saving system.",
        "Illustrated the backgrounds, visual effects,\nand revised the storyline",
        "Illustrated the character skill and sprites.",
        "Illustraded the game's UML, and Case Diagram.",
        "Previously contributed in making the game's\ncore structure for which some was adapted for this project.",
        "Guided and Mentored us in learning OOP,\nPaving the way for this project."
    };
    
    private Image[] memberPortraits = new Image[TOTAL_MEMBERS];

    private String[] memberImageFiles = {
        "jay.jpg",    
        "april.png",  
        "eden.jpeg",     
        "kaina.jpeg",  
        "althea.jpg",        
        "jack.jpg",    
        "sir_khai.jpeg"     
    };

    public CreditsPanel(GameWindow game) {
        this.game = game;
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        
        this.titleFont = FontManager.getFont("Jersey10-Regular.ttf", 64f);
        this.nameFont = FontManager.getFont("Jersey10-Regular.ttf", 40f);
        this.descFont = FontManager.getFont("Jersey10-Regular.ttf", 26f);

        java.net.URL imgURL = getClass().getResource("/EchoesOfTheOath/Resources/nation3_bg10.png");
        if (imgURL != null) {
            backgroundImage = new ImageIcon(imgURL).getImage();
        }

        for(int i = 0; i < TOTAL_MEMBERS; i++) {
            java.net.URL imgURL1 = getClass().getResource("/EchoesOfTheOath/Resources/" + memberImageFiles[i]);
            if (imgURL1 != null) {
                memberPortraits[i] = new ImageIcon(imgURL1).getImage();
            } else {
                System.out.println("Could not find image: " + memberImageFiles[i]);
            }
        }

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    endCredits();
                }
            }
        });
    }

    public void startCredits() {
        // --- THE FIX: Start exactly at the bottom of whatever the screen size is! ---
        // (Fallback to 1080 if getHeight() is temporarily 0 during layout phase)
        scrollY = getHeight() > 0 ? getHeight() : 1080; 
        fadeAlpha = 0f; 
        
        if (scrollTimer != null && scrollTimer.isRunning()) {
            scrollTimer.stop();
        }

        scrollTimer = new Timer(16, e -> {
            scrollY -= 1; 
            
            if (fadeAlpha < 1f) {
                fadeAlpha += 0.015f; 
                if (fadeAlpha > 1f) fadeAlpha = 1f;
            }

            int startY = scrollY + 150; 
            int spacing = 220; 
            int finalY = startY + (TOTAL_MEMBERS * spacing) + 100;

            if (finalY < -50) { 
                endCredits();
            }
            
            repaint();
        });
        scrollTimer.start();
        this.requestFocusInWindow();
    }

    private void endCredits() {
        if (scrollTimer != null) scrollTimer.stop();
        game.showScreen("ending");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        }

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fadeAlpha));

        g2.setColor(new Color(0, 0, 0, 210)); 
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

        g2.setFont(titleFont);
        g2.setColor(new Color(181, 153, 110)); 
        FontMetrics fmTitle = g2.getFontMetrics();
        g2.drawString("ECHOES OF THE OATH", (getWidth() - fmTitle.stringWidth("ECHOES OF THE OATH")) / 2, scrollY);
        
        g2.setColor(Color.WHITE);
        g2.setFont(nameFont);
        FontMetrics fmSubtitle = g2.getFontMetrics();
        g2.drawString("Created By", (getWidth() - fmSubtitle.stringWidth("Created By")) / 2, scrollY + 50);

        int startY = scrollY + 150; 
        int spacing = 220; 

        for (int i = 0; i < TOTAL_MEMBERS; i++) {
            int currentY = startY + (i * spacing);
            
            // --- THE FIX: Dynamically center the Portrait + Text Block ---
            int contentBlockWidth = 800; // Estimated total width of image + padding + text
            int boxX = (getWidth() - contentBlockWidth) / 2+80;

            int picSize = 120;
            if (memberPortraits[i] != null) {
                g2.drawImage(memberPortraits[i], boxX, currentY, picSize, picSize, null);
                g2.setColor(new Color(181, 153, 110));
                g2.setStroke(new BasicStroke(2));
                g2.drawRect(boxX, currentY, picSize, picSize);
            } else {
                g2.setColor(new Color(40, 40, 40));
                g2.fillRect(boxX, currentY, picSize, picSize);
                g2.setColor(new Color(181, 153, 110));
                g2.setStroke(new BasicStroke(2));
                g2.drawRect(boxX, currentY, picSize, picSize);
                
                g2.setFont(descFont);
                g2.setColor(Color.GRAY);
                g2.drawString("No Pic", boxX + 35, currentY + 65);
            }

            int textX = boxX + picSize + 40; // Add spacing between image and text

            g2.setColor(Color.WHITE);
            g2.setFont(nameFont);
            g2.drawString(memberNames[i], textX, currentY + 25);
            
            g2.setColor(new Color(175, 238, 171)); 
            g2.setFont(descFont);
            g2.drawString(memberRoles[i], textX, currentY + 55);

            g2.setColor(new Color(200, 200, 200)); 
            g2.setFont(descFont);
            
            String[] descriptionLines = memberDesc[i].split("\n");
            
            int descY = currentY + 90;
            for (String line : descriptionLines) {
                g2.drawString(line, textX, descY);
                descY += 25; 
            }
        }

        int finalY = startY + (TOTAL_MEMBERS * spacing) + 100;
        
        g2.setColor(new Color(181, 153, 110));
        g2.setFont(titleFont);
        g2.drawString("THANK YOU FOR PLAYING", (getWidth() - fmTitle.stringWidth("THANK YOU FOR PLAYING")) / 2, finalY);

        // --- THE FIX: Perfectly measure and center the copyright text! ---
        g2.setColor(Color.GRAY);
        g2.setFont(descFont);
        FontMetrics fmDesc = g2.getFontMetrics(descFont);
        String copyright = "©Mythspire Developers";
        g2.drawString(copyright, (getWidth() - fmDesc.stringWidth(copyright)) / 2, finalY + 40);
    }
}