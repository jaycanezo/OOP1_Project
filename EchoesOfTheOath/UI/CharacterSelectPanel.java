package EchoesOfTheOath.UI;

import EchoesOfTheOath.Characters.*;
import EchoesOfTheOath.Characters.Character;
import java.awt.*;
import javax.swing.*;

public class CharacterSelectPanel extends JPanel {

    private GameWindow game;
    private JTextArea textArea;
    private Character tempChosen;
    private Sprite warriorS, archerS, mageS;

    // 1. Declare Labels as fields so updateDisplayLabels can see them
    private JLabel warriorLabel, archerLabel, mageLabel;

    public CharacterSelectPanel(GameWindow game) {
        this.game = game;
        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());

        Warrior w = new Warrior();
        Archer a = new Archer();
        Mage m = new Mage();

        warriorS = w.getIdleSprite(); 
        archerS = a.getIdleSprite();
        mageS = m.getIdleSprite();

        setupUI(w, a, m);

        // Animation Timer
        Timer animationTimer = new Timer(100, e -> {
            if (warriorS != null) warriorS.update();
            if (archerS != null) archerS.update();
            if (mageS != null) mageS.update();
            
            updateDisplayLabels(); // Now works because labels are fields
            repaint(); 
        });
        animationTimer.start();
    }

    private void setupUI(Warrior w, Archer a, Mage m) {
        JPanel selectionGrid = new JPanel(new GridLayout(1, 3, 10, 0));
        selectionGrid.setOpaque(false);

        // Initialize the labels before adding them to buttons
        warriorLabel = new JLabel();
        archerLabel = new JLabel();
        mageLabel = new JLabel();

        selectionGrid.add(createCharButton("Warrior", warriorS, w, warriorLabel));
        selectionGrid.add(createCharButton("Archer", archerS, a, archerLabel));
        selectionGrid.add(createCharButton("Mage", mageS, m, mageLabel));

        add(selectionGrid, BorderLayout.CENTER);

        // ... Bottom Panel code remains the same ...
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(1080, 200));
        bottomPanel.setBackground(new Color(20, 20, 20));

        textArea = new JTextArea("Choose a Character in order to continue the story.");
        textArea.setFont(new Font("Serif", Font.BOLD, 22));
        textArea.setForeground(Color.WHITE);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setMargin(new Insets(20, 20, 20, 20));
        bottomPanel.add(textArea, BorderLayout.CENTER);

        JButton confirmBtn = new JButton("Confirm Hero");
        confirmBtn.addActionListener(e -> handleConfirmation());
        bottomPanel.add(confirmBtn, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // 2. Updated method signature to accept the specific label field
    private JPanel createCharButton(String name, Sprite s, Character c, JLabel imgLabel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(40, 40, 40));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        if (s != null && s.isLoaded()) {
            s.update(); 
            Image scaledImg = s.getCurrentFrame().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            imgLabel.setIcon(new ImageIcon(scaledImg));
        }

        JButton selectBtn = new JButton(name);
        selectBtn.addActionListener(e -> {
            tempChosen = c;
            textArea.setText(name + " Stats:\nHP: " + c.getHp() + " | Level: " + c.getLevel());
        });

        panel.add(imgLabel, BorderLayout.CENTER);
        panel.add(selectBtn, BorderLayout.SOUTH);
        return panel;
    }

    // 3. Logic to refresh the icons every tick
    private void updateDisplayLabels() {
        updateSingleLabel(warriorS, warriorLabel);
        updateSingleLabel(archerS, archerLabel);
        updateSingleLabel(mageS, mageLabel);
    }

    private void updateSingleLabel(Sprite s, JLabel lbl) {
        if (s != null && s.isLoaded() && lbl != null) {
            // Re-scale the current frame (this is what creates the "movement")
            Image scaled = s.getCurrentFrame().getScaledInstance(300, 300, Image.SCALE_FAST);
            lbl.setIcon(new ImageIcon(scaled));
        }
    }

    private void handleConfirmation() {
        if (tempChosen == null) {
            JOptionPane.showMessageDialog(this, "Please select a hero first!");
            return;
        }
        String name = JOptionPane.showInputDialog(this, "Enter your hero's name:");
        if (name != null && !name.trim().isEmpty()) {
            tempChosen.setName(name);
            game.setChosenCharacter(tempChosen); 
            game.showScreen("story");
        }
    }
}