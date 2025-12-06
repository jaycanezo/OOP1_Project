package EchoesOfTheOath;

import EchoesOfTheOath.Characters.Character;
import java.util.Scanner;

public class BattleLogic {
    public static void battleLogic(Character chosen, Character boss) {

        Scanner scan = new Scanner(System.in);

        final String RESET = "\033[0m";
        final String RED = "\033[31m"; // enemy / damage / low HP
        final String GREEN = "\033[32m"; // ready skill / good HP
        final String BLUE = "\033[34m"; // player name
        final String YELLOW = "\033[33m"; // skill cooldown / caution
        final String PURPLE = "\033[35m"; // special skill / potion

        System.out.println();
        System.out.println(boss.getTitle());
        System.out.println();

        System.out.println("You are now fighting " + RED + boss.getName() + RESET + "!");
        scan.nextLine();

        System.out.println(RED + boss.getName() + RESET);
        System.out.println("HP: " + boss.getHp() + " | Level: " + boss.getLevel());
        scan.nextLine();

        System.out.println(BLUE + chosen.getName() + RESET);
        System.out.println("HP: " + chosen.getMaxHp() + " | Level: "+ chosen.getLevel());
        scan.nextLine();

        int retryCount = 0; // ADDED CODE JACK
        final int MAX_RETRIES = 4; // ADDED CODE JACK

        while (boss.getHp() > 0) {

            // ---------------- USER TURN ----------------
            boolean validAction = false;
            while (!validAction) {
                chosen.displaySkills(); // ADDED CODE JACK

                System.out.println();
                System.out.println(RED + boss.getName() + "'s Current HP: " + RESET + boss.getHp() + "/" + boss.getMaxHp());
                System.out.println(BLUE + chosen.getName() + "'s Current HP: " + RESET + chosen.getHp() + "/" + chosen.getMaxHp());
                scan.nextLine();

                int skillChoice = -1;
                try {
                    System.out.print("Choose a skill (1, 2, 3) or 4 to use a potion: ");
                    skillChoice = scan.nextInt();
                    scan.nextLine();
                } catch (Exception e) {
                    System.out.println("Error: " + e + ". Please enter a valid number (1-4).");
                    scan.nextLine();
                    System.out.println();
                    continue;
                }

                if (skillChoice == 4) {
                    if (chosen.getPotionCount() > 0) {
                        chosen.usePotion();
                        scan.nextLine();
                        validAction = true;
                    } else {
                        System.out.println(RED + "No potions left! Choose another action." + RESET);
                        scan.nextLine();
                    }
                } else if (skillChoice >= 1 && skillChoice <= 3) {
                    if (chosen.isSkillAvailable(skillChoice)) {
                        chosen.useSkill(skillChoice, boss);
                        scan.nextLine();
                        validAction = true;
                    } else {
                        System.out.println(YELLOW + "Skill is on cooldown! " + chosen.getSkillCooldown(skillChoice)
                                + " turn(s) remaining." + RESET);
                        System.out.println("Choose another action.");
                        scan.nextLine();
                    }
                }
            }

            // ---------------- ENEMY TURN ----------------
            if (boss.getHp() > 0) {
                int randomSkill = boss.random.nextInt(3) + 1;
                boss.useSkill(randomSkill, chosen);
                scan.nextLine();
            }

            // ---------------- DEFEAT CHECK ----------------
            if (boss.getHp() <= 0) {
                System.out.println(GREEN + boss.getName() + " has been defeated!" + RESET);
                scan.nextLine();

                chosen.resetCooldowns();
                chosen.setHp(chosen.getMaxHp());
                System.out.println(chosen.getName() + "'s HP has been fully restored!");
                scan.nextLine();

                chosen.setLevel(chosen.getLevel() + 1);
                System.out
                        .println(chosen.getName() + " leveled up to level " + chosen.getLevel() + "!");
                scan.nextLine();

                chosen.setPotionCount(chosen.getPotionCount() + 5);
                System.out.println("Reward: 5 Health Potion added to inventory.");
                scan.nextLine();

                System.out.println("Current Level: " + chosen.getLevel());
                System.out.println("Current Potions: " + chosen.getPotionCount());
                scan.nextLine();

                System.out.println(GREEN + "You may now proceed on your journey." + RESET);
                scan.nextLine();
                break;
            }

            // ---------------- PLAYER DEFEAT ----------------
            if (chosen.getHp() <= 0) {
                System.out.println(RED + chosen.getName() + " has been defeated!" + RESET);
                scan.nextLine();

                retryCount++;

                if (retryCount >= MAX_RETRIES) {
                    System.out.println(RED + "You have used all your retries." + RESET);
                    System.out.println(RED + "GAME OVER. The journey ends here." + RESET);
                    scan.close();
                    return; // End entire game
                }

                while (true) {
                    System.out.print("Would you like to try again? (" + YELLOW + (MAX_RETRIES - retryCount) + RESET
                            + " retries left) (y/n): ");
                    String retryChoice = scan.next();

                    if (retryChoice.equalsIgnoreCase("y")) {
                        chosen.resetCooldowns();
                        chosen.setHp(chosen.getMaxHp());
                        boss.setHp(boss.getMaxHp());
                        System.out.println(GREEN + "You have been revived! The battle restarts." + RESET);
                        scan.nextLine();
                        scan.nextLine();
                        break;
                    } else if (retryChoice.equalsIgnoreCase("n")) {
                        System.out.println(RED + "Game Over. Thank you for playing!" + RESET);
                        scan.close();
                        return;
                    } else {
                        System.out.println(YELLOW + "Invalid choice! Please type only 'y' or 'n'." + RESET);
                    }
                }
            }
        }
        System.out.print("\033[H\033[2J"); // clear screen
        System.out.flush();
    }
}