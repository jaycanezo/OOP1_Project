package EchoesOfTheOath;

import EchoesOfTheOath.Characters.Character;
import java.util.Random;

public class BattleLogic {

    private Character player;
    private Character boss;
    private int retryCount = 0;
    private final int MAX_RETRIES = 4;
    private Random random = new Random();

    public BattleLogic(Character player, Character boss) {
        this.player = player;
        this.boss = boss;
    }

    // ---------------- PLAYER TURN ----------------
    // skillChoice: 1,2,3 = skill, 4 = potion
    public String playerTurn(int skillChoice) {
        StringBuilder messages = new StringBuilder();

        if (skillChoice == 4) {
            if (player.getPotionCount() > 0) {
                messages.append(player.usePotion()).append("\n");
            } else {
                messages.append("No potions left!\n");
            }
        } else if (skillChoice >= 1 && skillChoice <= 3) {
            if (player.isSkillAvailable(skillChoice)) {
                messages.append(player.useSkill(skillChoice, boss)).append("\n");
            } else {
                messages.append("Skill is on cooldown! ")
                        .append(player.getSkillCooldown(skillChoice))
                        .append(" turn(s) remaining.\n");
            }
        } else {
            messages.append("Invalid skill choice.\n");
        }

        // Check if boss is defeated
        if (boss.getHp() <= 0) {
            messages.append(handleVictory());
        }

        return messages.toString();
    }

    // ---------------- ENEMY TURN ----------------
    public String enemyTurn() {
        StringBuilder messages = new StringBuilder();

        if (boss.getHp() > 0) {
            int randomSkill = random.nextInt(3) + 1;
            messages.append(boss.useSkill(randomSkill, player)).append("\n");
        }

        // Check if player is defeated
        if (player.getHp() <= 0) {
            messages.append(handleDefeat());
        }

        return messages.toString();
    }

    // ---------------- VICTORY ----------------
    private String handleVictory() {
        StringBuilder messages = new StringBuilder();
        messages.append(boss.getName()).append(" has been defeated!\n");

        player.resetCooldowns();
        player.setHp(player.getMaxHp());
        messages.append(player.getName()).append("'s HP has been fully restored!\n");

        player.setLevel(player.getLevel() + 1);
        messages.append(player.getName())
                .append(" leveled up to level ")
                .append(player.getLevel())
                .append("!\n");

        player.setPotionCount(player.getPotionCount() + 5);
        messages.append("Reward: 5 Health Potions added to inventory.\n");

        return messages.toString();
    }

    // ---------------- DEFEAT ----------------
    private String handleDefeat() {
        StringBuilder messages = new StringBuilder();
        messages.append(player.getName()).append(" has been defeated!\n");

        retryCount++;
        if (retryCount >= MAX_RETRIES) {
            messages.append("You have used all your retries. GAME OVER.\n");
        } else {
            messages.append("You may retry the battle (")
                    .append(MAX_RETRIES - retryCount)
                    .append(" retries left).\n");
        }

        return messages.toString();
    }

    public boolean isBattleOver() {
        return boss.getHp() <= 0 || player.getHp() <= 0;
    }

    public boolean canRetry() {
        return retryCount < MAX_RETRIES;
    }

    public void resetBattle() {
        player.resetCooldowns();
        player.setHp(player.getMaxHp());
        boss.setHp(boss.getMaxHp());
    }

    // Getters for player and boss (for UI to display HP, level, etc.)
    public Character getPlayer() {
        return player;
    }

    public Character getBoss() {
        return boss;
    }
}