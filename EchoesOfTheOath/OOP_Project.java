package EchoesOfTheOath;
import java.util.Scanner;


import EchoesOfTheOath.Characters.Archer;
import EchoesOfTheOath.Characters.Boss;
import EchoesOfTheOath.Characters.Character;
import EchoesOfTheOath.Characters.Mage;
import EchoesOfTheOath.Characters.Warrior;
import EchoesOfTheOath.Characters.babyM;
import EchoesOfTheOath.Characters.Archivist;
import EchoesOfTheOath.Characters.Sarukdal;
import EchoesOfTheOath.Characters.Elarion;
import EchoesOfTheOath.Characters.Ilaryx;
import EchoesOfTheOath.Characters.Lunareth;


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


        // START OF ADDED CODE - JACK
        warrior.setLevel(11);
        archer.setLevel(11);
        mage.setLevel(11);
        // END OF ADDED CODE - JACK


        //character trial, can only move to the next character after all skills are used/tested
        System.out.print("You encountered a powerful foe!");
        scan.nextLine();


        // START OF ADDED CODE - JACK
        String input = "";
        System.out.println("\nWarrior engaged with the powerful foe!");
        do {
            System.out.println("Press 'D' to display character's information.");
            try {
                input = scan.nextLine();
                if (!input.equalsIgnoreCase("D")) {
                    System.out.println("Invalid input. Please press 'D' to display character information.");
                }
            } catch (Exception e) {
                System.out.println("Error at: " + e + ", try again.");
                scan.nextLine();
            }
        } while (!input.equalsIgnoreCase("D"));
        warrior.displayCharacterInfo();
        // END OF ADDED CODE - JACK
        while (!warrior.allSkillsUsed()) {
            System.out.print("Choose a skill (1, 2, 3): ");
            int skillChoice=0;
            try {
                skillChoice = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Error at: "+e+", try again.");
                scan.next();
                continue; // JACK ADDED CODE
            }
            warrior.useSkill(skillChoice, boss);
            System.out.println();
        }
        scan.nextLine();


        System.out.println("Archer steps in from a distance!");
        // START OF ADDED CODE - JACK
        do {
            System.out.println("Press 'D' to display character's information.");
            try {
                input = scan.nextLine();
                if (!input.equalsIgnoreCase("D")) {
                    System.out.println("Invalid input. Please press 'D' to display character information.");
                }
            } catch (Exception e) {
                System.out.println("Error at: " + e + ", try again.");
                scan.nextLine();
            }
        } while (!input.equalsIgnoreCase("D"));
        archer.displayCharacterInfo();
        // END OF ADDED CODE - JACK
        while(!archer.allSkillsUsed()){
            System.out.print("Choose a skill (1, 2, 3): ");
            int skillChoice=0;
            try {
                skillChoice = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Error at: "+e+", try again.");
                scan.next();
                continue; // JACK ADDED CODE
            }
            archer.useSkill(skillChoice, boss);
            System.out.println();
        }
        scan.nextLine();


        System.out.println("The Mage steps forward, staff in hand, ready to unleash the power of the elements.");
        // START OF ADDED CODE - JACK
        do {
            System.out.println("Press 'D' to display character's information.");
            try {
                input = scan.nextLine();
                if (!input.equalsIgnoreCase("D")) {
                    System.out.println("Invalid input. Please press 'D' to display character information.");
                }
            } catch (Exception e) {
                System.out.println("Error at: " + e + ", try again.");
                scan.nextLine();
            }
        } while (!input.equalsIgnoreCase("D"));
        mage.displayCharacterInfo();
        // END OF ADDED CODE - JACK
        while(!mage.allSkillsUsed()){
            System.out.print("Choose a skill (1, 2, 3): ");
            int skillChoice=0;
            try {
                skillChoice = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Error at: "+e+", try again.");
                scan.next();
                continue; // JACK ADDED CODE
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




        System.out.print("Enter your hero's name: ");
        String playerName = scan.nextLine();
        chosen.setName(playerName);




        System.out.println("\nYou have chosen the " + chosen.getClass().getSimpleName() + " named " + chosen.getName() + "!");
        System.out.println("\nPress any key to continue your Journey..."); //JACK ADDED CODE
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




        System.out.print("\033[H\033[2J"); // Move cursor to top-left and clear screen
        System.out.flush();


        //============================================================NATION 1============================================================

        chosen.setLevel(1);
        Storyline storyline = new Storyline();

        //============================================================NATION 1MINI QUEST============================================================
        storyline.quest1HumanasQuest(chosen);
        //============================================================NATION 1 MINI BOSS============================================================

        System.out.println();
        System.out.println("You are now fighting King Bartholomew Monarch (\"Baby M\")");
        System.out.println("HP: 3000 | MP: 200 | DEF: 220");  
        System.out.println();
        babyM babyM = new babyM();
        while (babyM.getHp() > 0) {
            //user turn
            boolean validAction = false;
            while (!validAction) {
                // START OF ADDED CODE - JACK
                int skillChoice = -1;
                try {
                    System.out.print("Choose a skill (1, 2, 3) or 4 to use a potion: ");
                    skillChoice = scan.nextInt();
                    scan.nextLine();
                } catch (Exception e) {
                    System.out.println("Error: " + e + ". Please enter a valid number (1-4).");
                    scan.next();
                    System.out.println();
                    continue;
                }
                // END OF ADDED CODE - JACK


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
                scan.nextLine();
                break;
            } // START OF ADDED CODE - JACK
            else if (chosen.getHp() <= 0) {
                System.out.println(chosen.getName() + " has been defeated!");
                String retryChoice = "";


                while (true) {
                    System.out.print("Would you like to try again? (y/n): ");
                    retryChoice = scan.next();


                    if (retryChoice.equalsIgnoreCase("y")) {
                        chosen.resetCooldowns();
                        chosen.setHp(chosen.getMaxHp());
                        babyM.setHp(babyM.getMaxHp());
                        System.out.println("You have been revived! The battle restarts.");
                        System.out.println();
                        break;
                    } else if (retryChoice.equalsIgnoreCase("n")) {
                        System.out.println("Game Over. Thank you for playing!");
                        scan.close();
                        return;
                    } else {
                        System.out.println("Invalid choice! Please type only 'y' or 'n'.");
                        System.out.println();
                    }
                }
            }
            // END OF ADDED CODE - JACK
        }

        
        //============================================================NATION 1 MAIN QUEST============================================================
        storyline.theVeinsOfHumanasQuest(chosen);

        //============================================================NATION 1 MAIN BOSS============================================================

        System.out.println("You are now fighting The Archivist");
        System.out.println();
        Archivist archivist = new Archivist();


        while (archivist.getHp() > 0) {
            //user turn
            boolean validAction = false;
            while (!validAction) {
                int skillChoice = -1;
                try {
                    System.out.print("Choose a skill (1, 2, 3) or 4 to use a potion: ");
                    skillChoice = scan.nextInt();
                    scan.nextLine();
                } catch (Exception e) {
                    System.out.println("Error: " + e + ". Please enter a valid number (1-4).");
                    scan.next();
                    System.out.println();
                    continue;
                }


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
                String retryChoice = "";


                while (true) {
                    System.out.print("Would you like to try again? (y/n): ");
                    retryChoice = scan.next();


                    if (retryChoice.equalsIgnoreCase("y")) {
                        chosen.resetCooldowns();
                        chosen.setHp(chosen.getMaxHp());
                        archivist.setHp(archivist.getMaxHp());
                        System.out.println("You have been revived! The battle restarts.");
                        System.out.println();
                        break;
                    } else if (retryChoice.equalsIgnoreCase("n")) {
                        System.out.println("Game Over. Thank you for playing!");
                        scan.close();
                        return;
                    } else {
                        System.out.println("Invalid choice! Please type only 'y' or 'n'.");
                        System.out.println();
                    }
                }
            }
        }


        //============================================================NATION 2============================================================

        //============================================================NATION 2 MINI QUEST============================================================
        storyline.whispersBeneathTheBoughs(chosen);
        storyline.theSilverfangTrial(chosen);
        //============================================================NATION 2 MINI BOSS============================================================
        System.out.println();
        System.out.println("You are now fighting Ilaryx, The Silverfang Huntress");  
        scan.nextLine();
        Ilaryx ilaryx = new Ilaryx();
        while (ilaryx.getHp() > 0) {


            boolean validAction = false;
            while (!validAction) {
                int skillChoice = -1;
                try {
                    System.out.print("Choose a skill (1, 2, 3) or 4 to use a potion: ");
                    skillChoice = scan.nextInt();
                    scan.nextLine();
                } catch (Exception e) {
                    System.out.println("Error: " + e + ". Please enter a valid number (1-4).");
                    scan.next();
                    System.out.println();
                    continue;
                }


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
                        chosen.useSkill(skillChoice, ilaryx);
                        System.out.println();
                        validAction = true;
                    } else {
                        System.out.println("Skill is on cooldown! " + chosen.getSkillCooldown(skillChoice) + " turn(s) remaining.");
                        System.out.println("Choose another action.");
                        System.out.println();
                    }
                }
            }


            if (ilaryx.getHp() > 0) {
                int randomSkill = ilaryx.random.nextInt(3) + 1;
                ilaryx.useSkill(randomSkill, chosen);
                System.out.println();
            }


            if (ilaryx.getHp() <= 0) {
                System.out.println(ilaryx.getName() + " has been defeated!");
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
                String retryChoice = "";


                while (true) {
                    System.out.print("Would you like to try again? (y/n): ");
                    retryChoice = scan.next();


                    if (retryChoice.equalsIgnoreCase("y")) {
                        chosen.resetCooldowns();
                        chosen.setHp(chosen.getMaxHp());
                        ilaryx.setHp(ilaryx.getMaxHp());
                        System.out.println("You have been revived! The battle restarts.");
                        System.out.println();
                        break;
                    } else if (retryChoice.equalsIgnoreCase("n")) {
                        System.out.println("Game Over. Thank you for playing!");
                        scan.close();
                        return;
                    } else {
                        System.out.println("Invalid choice! Please type only 'y' or 'n'.");
                        System.out.println();
                    }
                }
            }
        }



        //============================================================NATION 2 MAIN QUEST============================================================
        storyline.theRootsOfDespair(chosen);
        storyline.theWardenOfTheFractured(chosen);
        //============================================================NATION 2 MAIN BOSS============================================================

        System.out.println("You are now fighting Lunareth, The Warden of the Fractured Bough");
        scan.nextLine();
        Lunareth lunareth = new Lunareth();

        while (lunareth.getHp() > 0) {
            boolean validAction = false;
            while (!validAction) {
                int skillChoice = -1;
                try {
                    System.out.print("Choose a skill (1, 2, 3) or 4 to use a potion: ");
                    skillChoice = scan.nextInt();
                    scan.nextLine();
                } catch (Exception e) {
                    System.out.println("Error: " + e + ". Please enter a valid number (1-4).");
                    scan.next();
                    System.out.println();
                    continue;
                }


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
                        chosen.useSkill(skillChoice, lunareth);
                        System.out.println();
                        validAction = true;
                    } else {
                        System.out.println("Skill is on cooldown! " + chosen.getSkillCooldown(skillChoice) + " turn(s) remaining.");
                        System.out.println("Choose another action.");
                        System.out.println();
                    }
                }
            }


            if (lunareth.getHp() > 0) {
                int randomSkill = lunareth.random.nextInt(3) + 1;
                lunareth.useSkill(randomSkill, chosen);
                System.out.println();
            }




            if (lunareth.getHp() <= 0) {
                System.out.println(lunareth.getName() + " has been defeated!");
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
                String retryChoice = "";


                while (true) {
                    System.out.print("Would you like to try again? (y/n): ");
                    retryChoice = scan.next();


                    if (retryChoice.equalsIgnoreCase("y")) {
                        chosen.resetCooldowns();
                        chosen.setHp(chosen.getMaxHp());
                        lunareth.setHp(lunareth.getMaxHp());
                        System.out.println("You have been revived! The battle restarts.");
                        System.out.println();
                        break;
                    } else if (retryChoice.equalsIgnoreCase("n")) {
                        System.out.println("Game Over. Thank you for playing!");
                        scan.close();
                        return;
                    } else {
                        System.out.println("Invalid choice! Please type only 'y' or 'n'.");
                        System.out.println();
                    }
                }
            }
        }


        //============================================================NATION 3 MINI QUEST============================================================
        storyline.theLastBastionQuest(chosen);
        //============================================================NATION 3 MINI BOSS============================================================
       
        System.out.println();
        System.out.println("You are now fighting Sarukdal");  
        scan.nextLine();
        Sarukdal sarukdal = new Sarukdal();
        while (sarukdal.getHp() > 0) {
            //user turn
            boolean validAction = false;
            while (!validAction) {
                int skillChoice = -1;
                try {
                    System.out.print("Choose a skill (1, 2, 3) or 4 to use a potion: ");
                    skillChoice = scan.nextInt();
                    scan.nextLine();
                } catch (Exception e) {
                    System.out.println("Error: " + e + ". Please enter a valid number (1-4).");
                    scan.next();
                    System.out.println();
                    continue;
                }


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
                        chosen.useSkill(skillChoice, sarukdal);
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
            if (sarukdal.getHp() > 0) {
                int randomSkill = sarukdal.random.nextInt(3) + 1;
                sarukdal.useSkill(randomSkill, chosen);
                System.out.println();
            }


            //defeat condition
            if (sarukdal.getHp() <= 0) {
                System.out.println(sarukdal.getName() + " has been defeated!");
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
                String retryChoice = "";


                while (true) {
                    System.out.print("Would you like to try again? (y/n): ");
                    retryChoice = scan.next();


                    if (retryChoice.equalsIgnoreCase("y")) {
                        chosen.resetCooldowns();
                        chosen.setHp(chosen.getMaxHp());
                        sarukdal.setHp(sarukdal.getMaxHp());
                        System.out.println("You have been revived! The battle restarts.");
                        System.out.println();
                        break;
                    } else if (retryChoice.equalsIgnoreCase("n")) {
                        System.out.println("Game Over. Thank you for playing!");
                        scan.close();
                        return;
                    } else {
                        System.out.println("Invalid choice! Please type only 'y' or 'n'.");
                        System.out.println();
                    }
                }
            }
        }

        //============================================================NATION 3 MAIN QUEST============================================================
       storyline.theUnboundThroneQuest(chosen);

        //============================================================NATION 3 MAIN BOSS============================================================
        System.out.println("You are now fighting Elarion");
        scan.nextLine();
        Elarion elarion = new Elarion();


        while (elarion.getHp() > 0) {
            //user turn
            boolean validAction = false;
            while (!validAction) {
                int skillChoice = -1;
                try {
                    System.out.print("Choose a skill (1, 2, 3) or 4 to use a potion: ");
                    skillChoice = scan.nextInt();
                    scan.nextLine();
                } catch (Exception e) {
                    System.out.println("Error: " + e + ". Please enter a valid number (1-4).");
                    scan.next();
                    System.out.println();
                    continue;
                }


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
                        chosen.useSkill(skillChoice, elarion);
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
            if (elarion.getHp() > 0) {
                int randomSkill = elarion.random.nextInt(3) + 1;
                elarion.useSkill(randomSkill, chosen);
                System.out.println();
            }


            //defeat condition
            if (elarion.getHp() <= 0) {
                System.out.println(elarion.getName() + " has been defeated!");
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
                String retryChoice = "";


                while (true) {
                    System.out.print("Would you like to try again? (y/n): ");
                    retryChoice = scan.next();


                    if (retryChoice.equalsIgnoreCase("y")) {
                        chosen.resetCooldowns();
                        chosen.setHp(chosen.getMaxHp());
                        elarion.setHp(elarion.getMaxHp());
                        System.out.println("You have been revived! The battle restarts.");
                        System.out.println();
                        break;
                    } else if (retryChoice.equalsIgnoreCase("n")) {
                        System.out.println("Game Over. Thank you for playing!");
                        scan.close();
                        return;
                    } else {
                        System.out.println("Invalid choice! Please type only 'y' or 'n'.");
                        System.out.println();
                    }
                }
            }
        }




        scan.close();
    }
}
