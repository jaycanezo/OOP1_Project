package EchoesOfTheOath;
import EchoesOfTheOath.Characters.Character;
import java.util.Scanner;


public class BattleLogic{
    public static void battleLogic(Character chosen, Character boss) {

        Scanner scan = new Scanner(System.in);

        System.out.println();
        System.out.println("You are now fighting " + boss.getName() + boss.getTitle());

        System.out.println(boss.getName()+"'s HP: " + boss.getHp() + " | Level: " + boss.getLevel());
        scan.nextLine();

        System.out.println(chosen.getName()+"'s Stats: HP: " + boss.getHp() + " | Level: " + boss.getLevel());
        scan.nextLine();

        while (boss.getHp() > 0) {

            // ---------------- USER TURN ----------------
            boolean validAction = false;
            while (!validAction) {
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
                        System.out.println("No potions left! Choose another action.");
                        scan.nextLine();
                    }
                } 
                else if (skillChoice >= 1 && skillChoice <= 3) {
                    if (chosen.isSkillAvailable(skillChoice)) {
                        chosen.useSkill(skillChoice, boss);
                        scan.nextLine();
                        validAction = true;
                    } else {
                        System.out.println("Skill is on cooldown! " + chosen.getSkillCooldown(skillChoice) + " turn(s) remaining.");
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
                System.out.println(boss.getName() + " has been defeated!");
                scan.nextLine();

                chosen.resetCooldowns();
                chosen.setHp(chosen.getMaxHp());
                System.out.println(chosen.getName() + "'s HP has been fully restored!");
                scan.nextLine();

                chosen.setLevel(chosen.getLevel() + 1);
                System.out.println(chosen.getName() + " leveled up to level " + chosen.getLevel() + "!");
                scan.nextLine();

                chosen.setPotionCount(chosen.getPotionCount() + 2);
                System.out.println("Reward: 5 Health Potion added to inventory.");
                scan.nextLine();

                System.out.println("Current Level: " + chosen.getLevel());
                System.out.println("Current Potions: " + chosen.getPotionCount());
                scan.nextLine();

                System.out.println("You may now proceed on your journey.");
                scan.nextLine();
                break;
            }

            // ---------------- PLAYER DEFEAT ----------------
            if (chosen.getHp() <= 0) {
                System.out.println(chosen.getName() + " has been defeated!");
                scan.nextLine();

                while (true) {
                    System.out.print("Would you like to try again? (y/n): ");
                    String retryChoice = scan.next();

                    if (retryChoice.equalsIgnoreCase("y")) {
                        chosen.resetCooldowns();
                        chosen.setHp(chosen.getMaxHp());
                        boss.setHp(boss.getMaxHp());
                        System.out.println("You have been revived! The battle restarts.");
                        scan.nextLine();
                        scan.nextLine();
                        break;
                    } 
                    else if (retryChoice.equalsIgnoreCase("n")) {
                        System.out.println("Game Over. Thank you for playing!");
                        scan.close();
                        return;
                    } 
                    else {
                        System.out.println("Invalid choice! Please type only 'y' or 'n'.");
                    }
                }
            }
        }
        scan.close();
    }
}


