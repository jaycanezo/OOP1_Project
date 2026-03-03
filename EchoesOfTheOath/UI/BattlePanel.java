package EchoesOfTheOath.UI;

import javax.swing.*;
import java.awt.*;
import EchoesOfTheOath.Characters.*;
import EchoesOfTheOath.Characters.Character;

public class BattlePanel extends JPanel {

    private Character player;
    private Character enemy;

    private JLabel playerHpLabel;
    private JLabel enemyHpLabel;

    private JButton basicBtn;
    private JButton skillBtn;
    private JButton ultimateBtn;

    public BattlePanel() {

        player = new Archer();
        enemy = new Archer(); // temporary enemy

        setLayout(new BorderLayout());

        // ===== TOP (HP DISPLAY) =====
        JPanel topPanel = new JPanel(new GridLayout(1,2));

        playerHpLabel = new JLabel(player.getName() + " HP: " + player.getHp());
        enemyHpLabel = new JLabel(enemy.getName() + " HP: " + enemy.getHp());

        topPanel.add(playerHpLabel);
        topPanel.add(enemyHpLabel);

        add(topPanel, BorderLayout.NORTH);

        // ===== BOTTOM (SKILL BUTTONS) =====
        JPanel bottomPanel = new JPanel();

        basicBtn = new JButton("Basic Attack");
        skillBtn = new JButton("Special Skill");
        ultimateBtn = new JButton("Ultimate");

        bottomPanel.add(basicBtn);
        bottomPanel.add(skillBtn);
        bottomPanel.add(ultimateBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        // ===== BUTTON LOGIC =====
        basicBtn.addActionListener(e -> playerTurn(1));
        skillBtn.addActionListener(e -> playerTurn(2));
        ultimateBtn.addActionListener(e -> playerTurn(3));
    }

    private void playerTurn(int skillNumber) {

        player.useSkill(skillNumber, enemy);
        updateHpLabels();

        if (enemy.getHp() <= 0) {
            JOptionPane.showMessageDialog(this, "You Win!");
            disableButtons();
            return;
        }

        enemyTurn();
    }

    private void enemyTurn() {
        enemy.useSkill(1, player); // simple AI
        updateHpLabels();

        if (player.getHp() <= 0) {
            JOptionPane.showMessageDialog(this, "You Lose!");
            disableButtons();
        }
    }

    private void updateHpLabels() {
        playerHpLabel.setText(player.getName() + " HP: " + player.getHp());
        enemyHpLabel.setText(enemy.getName() + " HP: " + enemy.getHp());
    }

    private void disableButtons() {
        basicBtn.setEnabled(false);
        skillBtn.setEnabled(false);
        ultimateBtn.setEnabled(false);
    }
}
