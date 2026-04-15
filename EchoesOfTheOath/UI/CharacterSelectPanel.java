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

    public CharacterSelectPanel(GameWindow game) {
        this.game = game;
        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());

        warriorS = new Sprite("/EchoesOfTheOath/Resources/Warrior.png", 523, 477, 1);
        archerS = new Sprite("/EchoesOfTheOath/Resources/Archer.png", 515, 484, 1);
        mageS = new Sprite("/EchoesOfTheOath/Resources/Mage.png", 500, 500, 1);

        setupUI();
    }

    private void setupUI() {
        JPanel selectionGrid = new JPanel(new GridLayout(1, 3, 10, 0));
        selectionGrid.setOpaque(false);

        selectionGrid.add(createCharButton("Warrior", warriorS, new Warrior()));
        selectionGrid.add(createCharButton("Archer", archerS, new Archer()));
        selectionGrid.add(createCharButton("Mage", mageS, new Mage()));

        add(selectionGrid, BorderLayout.CENTER);

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

    private JPanel createCharButton(String name, Sprite s, Character c) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(40, 40, 40));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        JLabel imgLabel = new JLabel(new ImageIcon(s.getCurrentFrame().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
        JButton selectBtn = new JButton(name);

        selectBtn.addActionListener(e -> {
            tempChosen = c;
            textArea.setText(name + " Stats:\nHP: " + c.getHp() + " | Level: " + c.getLevel());
        });

        panel.add(imgLabel, BorderLayout.CENTER);
        panel.add(selectBtn, BorderLayout.SOUTH);
        return panel;
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