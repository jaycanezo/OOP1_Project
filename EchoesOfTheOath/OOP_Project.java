package EchoesOfTheOath;
import java.util.Scanner;

import EchoesOfTheOath.Characters.Archer;
import EchoesOfTheOath.Characters.Boss;
import EchoesOfTheOath.Characters.Character;
import EchoesOfTheOath.Characters.Mage;
import EchoesOfTheOath.Characters.Warrior;
import EchoesOfTheOath.Characters.babyM;
import EchoesOfTheOath.Characters.Archivist;

public class OOP_Project {
    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);

        // Game Intro Display:
        System.out.println("██████████   ██████████   ████    ████   ████████████   ██████████   ██████████");
        System.out.println("██████████   ██████████   ████    ████   ████████████   ██████████   ██████████");
        System.out.println("████         ████         ████    ████   ████    ████   ████         ████      ");
        System.out.println("████         ████         ████    ████   ████    ████   ████         ████      ");
        System.out.println("██████████   ████         ████████████   ████    ████   ██████████   ██████████");
        System.out.println("██████████   ████         ████████████   ████    ████   ██████████   ██████████");
        System.out.println("████         ████         ████    ████   ████    ████   ████               ████");
        System.out.println("████         ████         ████    ████   ████    ████   ████               ████");
        System.out.println("██████████   ██████████   ████    ████   ████████████   ██████████   ██████████");
        System.out.println("██████████   ██████████   ████    ████   ████████████   ██████████   ██████████");
        System.out.println();
        System.out.println("██████████████   ████████████   █  █████████████   ███       ███   ████████████");
        System.out.println("███        ███   ███            █       ███        ███       ███   ███         ");
        System.out.println("███        ███   ████████████   █       ███        █████████████   ████████████");
        System.out.println("███        ███   ███            █       ███        ███       ███   ███         ");
        System.out.println("██████████████   ███            █       ███        ███       ███   ████████████");
        System.out.println();
        System.out.println("█████████████████   █████████████████   ███████████████████   █████       █████");
        System.out.println("█████████████████   █████████████████   ███████████████████   █████       █████");
        System.out.println("████         ████   ████         ████           ████          █████       █████");
        System.out.println("████         ████   ████         ████           ████          █████       █████");
        System.out.println("████         ████   █████████████████           ████          █████████████████");
        System.out.println("████         ████   █████████████████           ████          █████████████████");
        System.out.println("████         ████   ████         ████           ████          █████       █████");
        System.out.println("████         ████   ████         ████           ████          █████       █████");
        System.out.println("█████████████████   ████         ████           ████          █████       █████");
        System.out.println("█████████████████   ████         ████           ████          █████       █████");
        System.out.println("-----------------------Press Enter to Start the Game!--------------------------");
        // Wait for Enter
        scan.nextLine();
        
        System.out.print("\033[H\033[2J"); //clear screen using ANSI escape codes
        System.out.flush();

        //start of the storyline intro, cannot be skipped for immersion/lore purposes
        String text = "Long ago, three adventurers-a brave warrior, a swift archer, and a wise mage-traveled across worlds, earning fame for vanquishing great evils. Their bond was unbreakable, their deeds legendary...\n\nUntil one mission brought them face to face with a power unlike any other-a force that had slain even the mightiest heroes.";
        
        for (char c : text.toCharArray()) {
            System.out.print(c);
            System.out.flush(); // force immediate output
            Thread.sleep(1);  // paras text delay
        }

        scan.nextLine();
        System.out.println();

        //creation of character objects
        Warrior warrior = new Warrior();
        Archer archer = new Archer();
        Mage mage = new Mage();
        Boss boss = new Boss();

        //character trial, can only move to the next character after all skills are used/tested
        System.out.print("You encountered a powerful foe!");
        scan.nextLine();

        System.out.println("\nWarrior engaged with the powerful foe!");
        while (!warrior.allSkillsUsed()) {
            System.out.print("Choose a skill (1, 2, 3): ");
            int skillChoice=0;
            try {
                skillChoice = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Error at: "+e+", try again.");
                scan.next();
            }
            warrior.useSkill(skillChoice, boss);
            System.out.println();
        }

        System.out.println("Archer steps in from a distance!");
        while(!archer.allSkillsUsed()){
            System.out.print("Choose a skill (1, 2, 3): ");
            int skillChoice=0;
            try {
                skillChoice = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Error at: "+e+", try again.");
                scan.next();
            }
            archer.useSkill(skillChoice, boss);
            System.out.println();
        }

        System.out.println("The Mage steps forward, staff in hand, ready to unleash the power of the elements.");
        while(!mage.allSkillsUsed()){
            System.out.print("Choose a skill (1, 2, 3): ");
            int skillChoice=0;
            try {
                skillChoice = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Error at: "+e+", try again.");
                scan.next();
            }
            mage.useSkill(skillChoice, boss);
            System.out.println();
        }
        scan.nextLine();

        System.out.println("Archer: Oh no the Demon is preparing to launch an attack!");
        scan.nextLine();
        System.out.println("Warrior: Lets use our power together");
        scan.nextLine();
        System.out.println("The heroes then leaped forward the Demon combining their strengths in hopes of defeating the enemy.");
        scan.nextLine();

        Character chosen = null;

        System.out.println("You have witnessed the power of the heroes! Choose a Character in order to continue the story.");
        System.out.print("[1]Warrior [2]Archer [3]Mage: ");

        int choice = 0;
        while (true) {
            try {
                choice = scan.nextInt();
                scan.nextLine();
            } catch (Exception e) {
                System.out.println("Error at: "+e+", try again.");
                scan.next();
            }

            switch(choice){
                case 1:
                    chosen = new Warrior();
                    break;
                case 2:
                    chosen = new Archer();
                    break;
                case 3:
                    chosen = new Mage();
                    break;
                default:
                    continue;
            }
            break;
        }

        System.out.println();
        System.out.print("Enter your hero's name: ");
        String playerName = scan.nextLine();
        chosen.setName(playerName);

        System.out.println("\nYou have chosen the " + chosen.getClass().getSimpleName() + " named " + chosen.getName() + "!");
        System.out.println("\nPress Enter to continue your Journey...");
        scan.nextLine();

        System.out.println("Demon: You will not defeat me you weaklings! HaHAhaHA");
        scan.nextLine();       
        System.out.println("The Demon uses its Ultimate: \"The Final Vow\"");
        System.out.println();
        System.out.println("Demon: Your destiny cannot escape the Darkness.");
        scan.nextLine();
        System.out.println("The Demon calls down a pillar of fate that engulfs the heroes in Darkness.");
        scan.nextLine();
        System.out.println(chosen.getName()+": Noooo! I won't let this be the end of us!");
        scan.nextLine();
        System.out.println("As the Darkness surrounds you, a mysterious light begins to glow within your heart.");
        scan.nextLine();

        System.out.print("\033[H\033[2J"); //clear screen
        System.out.flush();

        //Start of the journey in the human nation
        Storyline storyline = new Storyline();

        System.out.println();
        System.out.println("You are now fighting King Bartholomew Monarch (\"Baby M\")");  
        System.out.println();
        babyM babyM = new babyM();
        while (babyM.getHp() > 0) {
            //user turn
            boolean validAction = false;
            while (!validAction) {
                System.out.print("Choose a skill (1, 2, 3) or 4 to use a potion: ");
                int skillChoice = scan.nextInt();

                if (skillChoice == 4) {
                    if (chosen.getPotionCount() > 0) {
                        chosen.usePotion();
                        validAction = true;
                    } else {
                        System.out.println("No potions left! Choose another action.");
                        System.out.println();
                    }
                } else if (skillChoice >= 1 && skillChoice <= 3) {
                    if (chosen.isSkillAvailable(skillChoice)) {
                        chosen.useSkill(skillChoice, babyM);
                        System.out.println();
                        validAction = true;
                    } else {
                        System.out.println("Skill is on cooldown! " + chosen.getSkillCooldown(skillChoice) + " turn(s) remaining.");
                        System.out.println("Choose another action.");
                        System.out.println();
                    }
                }
            }

            //enemy turn
            if (babyM.getHp() > 0) {
                int randomSkill = babyM.random.nextInt(3) + 1;
                babyM.useSkill(randomSkill, chosen);
                System.out.println();
            }

            //defeat condition
            if (babyM.getHp() <= 0) {
                System.out.println(babyM.getName() + " has been defeated!");
                chosen.resetCooldowns();
                chosen.setHp(chosen.getMaxHp());
                System.out.println(chosen.getName() + "'s HP has been fully restored!");
                chosen.setLevel(chosen.getLevel() + 1);
                System.out.println(chosen.getName() + " leveled up to level " + chosen.getLevel() + "!");
                chosen.setPotionCount(chosen.getPotionCount() + 5);
                System.out.println("Reward: 5 Health Potion added to inventory.");
                System.out.println("Current Level: "+chosen.getLevel());
                System.out.println("Current Potions: " + chosen.getPotionCount());
                System.out.println("You may now proceed on your journey.");
                System.out.println();
                break;
            } else if (chosen.getHp() <= 0) {
                System.out.println(chosen.getName() + " has been defeated!");
                System.out.println("Would you like to try again? (y/n): ");
                String retryChoice=scan.next();
                
                if(retryChoice.equalsIgnoreCase("y")){
                    chosen.resetCooldowns();
                    chosen.setHp(chosen.getMaxHp());
                    babyM.setHp( babyM.getMaxHp());
                    System.out.println("You have been revived! The battle restarts.");
                    System.out.println();
                } else {
                    System.out.println("Game Over. Thank you for playing!");
                    scan.close();
                    return;
                }
            }
        }

        storyline.theVeinsOfHumanasQuest(chosen.getName());

        System.out.println();
        System.out.println("You are now fighting The Archivist");
        System.out.println();
        Archivist archivist = new Archivist();

        while (archivist.getHp() > 0) {
            //user turn
            boolean validAction = false;
            while (!validAction) {
                System.out.print("Choose a skill (1, 2, 3) or 4 to use a potion: ");
                int skillChoice = scan.nextInt();

                if (skillChoice == 4) {
                    if (chosen.getPotionCount() > 0) {
                        chosen.usePotion();
                        validAction = true;
                    } else {
                        System.out.println("No potions left! Choose another action.");
                        System.out.println();
                    }
                } else if (skillChoice >= 1 && skillChoice <= 3) {
                    if (chosen.isSkillAvailable(skillChoice)) {
                        chosen.useSkill(skillChoice, archivist);
                        System.out.println();
                        validAction = true;
                    } else {
                        System.out.println("Skill is on cooldown! " + chosen.getSkillCooldown(skillChoice) + " turn(s) remaining.");
                        System.out.println("Choose another action.");
                        System.out.println();
                    }
                }
            }

            //enemy turn
            if (archivist.getHp() > 0) {
                int randomSkill = archivist.random.nextInt(3) + 1;
                archivist.useSkill(randomSkill, chosen);
                System.out.println();
            }

            //defeat condition
            if (archivist.getHp() <= 0) {
                System.out.println(archivist.getName() + " has been defeated!");
                chosen.resetCooldowns();
                chosen.setHp(chosen.getMaxHp());
                System.out.println(chosen.getName() + "'s HP has been fully restored!");
                chosen.setLevel(chosen.getLevel() + 1);
                System.out.println(chosen.getName() + " leveled up to level " + chosen.getLevel() + "!");
                chosen.setPotionCount(chosen.getPotionCount() + 5);
                System.out.println("Reward: 5 Health Potion added to inventory.");
                System.out.println("Current Level: "+chosen.getLevel());
                System.out.println("Current Potions: " + chosen.getPotionCount());
                System.out.println("You may now proceed on your journey.");
                System.out.println();
                break;
            } else if (chosen.getHp() <= 0) {
                System.out.println(chosen.getName() + " has been defeated!");
                System.out.println("Would you like to try again? (y/n): ");
                String retryChoice=scan.next();
                
                if(retryChoice.equalsIgnoreCase("y")){
                    chosen.resetCooldowns();
                    chosen.setHp(chosen.getMaxHp());
                    archivist.setHp(archivist.getMaxHp());
                    System.out.println();
                    System.out.println("You have been revived! The battle restarts.");
                    System.out.println();
                } else if(retryChoice.equalsIgnoreCase("n")){
                    System.out.println("Game Over. Thank you for playing!");
                    scan.close();
                    return;
                }
            }
        }

        scan.close();
    }
}