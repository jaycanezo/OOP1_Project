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
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println("Error at: " + e + ", try again.");
                System.out.println();
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
                if(skillChoice<1||skillChoice>3){
                    System.out.println("Please press [1], [2] or [3] only.");
                }
            } catch (Exception e) {
                System.out.println("Invalid Input. Please press [1], [2] or [3] only.");
                scan.next();
                System.out.println();
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
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println("Error at: " + e + ", try again.");
                System.out.println();
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
                if(skillChoice<1||skillChoice>3){
                    System.out.println("Please press [1], [2] or [3] only.");
                }
            } catch (Exception e) {
                System.out.println("Invalid Input. Please press [1], [2] or [3] only.");
                System.out.println();
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
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println("Error at: " + e + ", try again.");
                System.out.println();
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
                if(skillChoice<1||skillChoice>3){
                    System.out.println("Please press [1], [2] or [3] only.");
                }                
            } catch (Exception e) {
                System.out.println("Invalid Input. Please press [1], [2] or [3] only.");
                System.out.println();
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
                if(choice>3||choice<1){
                    System.out.println("Please enter [1] for Warriorr, [2] for Archer or [3] for Mage only.");
                }
            } catch (Exception e) {
                System.out.println("Input Error. Please enter [1] for Warriorr, [2] for Archer or [3] for Mage only.");
                scan.nextLine();
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
        scan.nextLine();
        System.out.println("Press any key to continue your Journey..."); //JACK ADDED CODE
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

        babyM babyM = new babyM();

        System.out.println();
        System.out.println("You are now fighting "+babyM.getName()+", Humanas Nation - Mini Boss");

        System.out.println("HP: " + babyM.getHp() + " | Level: "+ babyM.getLevel());
        scan.nextLine();
        
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
                    scan.nextLine();
                    System.out.println();
                    continue;
                }
                // END OF ADDED CODE - JACK


                if (skillChoice == 4) {
                    if (chosen.getPotionCount() > 0) {
                        chosen.usePotion();
                        scan.nextLine();
                        validAction = true;
                    } else {
                        System.out.println("No potions left! Choose another action.");
                        scan.nextLine();
                    }
                } else if (skillChoice >= 1 && skillChoice <= 3) {
                    if (chosen.isSkillAvailable(skillChoice)) {
                        chosen.useSkill(skillChoice, babyM);
                        scan.nextLine();
                        validAction = true;
                    } else {
                        System.out.println("Skill is on cooldown! " + chosen.getSkillCooldown(skillChoice) + " turn(s) remaining.");
                        System.out.println("Choose another action.");
                        scan.nextLine();
                    }
                }
            }


            //enemy turn
            if (babyM.getHp() > 0) {
                int randomSkill = babyM.random.nextInt(3) + 1;
                babyM.useSkill(randomSkill, chosen);
                scan.nextLine();
            }


            //defeat condition
            if (babyM.getHp() <= 0) {
                System.out.println(babyM.getName() + " has been defeated!");
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
                System.out.println("Current Level: "+chosen.getLevel());
                System.out.println("Current Potions: " + chosen.getPotionCount());
                scan.nextLine();
                System.out.println("You may now proceed on your journey.");
                scan.nextLine();
                break;
            } // START OF ADDED CODE - JACK
            else if (chosen.getHp() <= 0) {
                System.out.println(chosen.getName() + " has been defeated!");
                scan.nextLine();
                String retryChoice = "";


                while (true) {
                    System.out.print("Would you like to try again? (y/n): ");
                    retryChoice = scan.next();


                    if (retryChoice.equalsIgnoreCase("y")) {
                        chosen.resetCooldowns();
                        chosen.setHp(chosen.getMaxHp());
                        babyM.setHp(babyM.getMaxHp());
                        System.out.println("You have been revived! The battle restarts.");
                        scan.nextLine();
                        scan.nextLine();
                        break;
                    } else if (retryChoice.equalsIgnoreCase("n")) {
                        System.out.println("Game Over. Thank you for playing!");
                        scan.close();
                        return;
                    } else {
                        System.out.println("Invalid choice! Please type only 'y' or 'n'.");
                    }
                }
                
            }
            // END OF ADDED CODE - JACK
        }

        
        //============================================================NATION 1 MAIN QUEST============================================================
        storyline.theVeinsOfHumanasQuest(chosen);

        //============================================================NATION 1 MAIN BOSS============================================================

        Archivist archivist = new Archivist();

        System.out.println();
        System.out.println("You are now fighting "+archivist.getName()+", Humanas Nation - Main Boss");

        System.out.println("HP: " + archivist.getHp() + " | Level: "+ archivist.getLevel());
        scan.nextLine();
        
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
                } else if (skillChoice >= 1 && skillChoice <= 3) {
                    if (chosen.isSkillAvailable(skillChoice)) {
                        chosen.useSkill(skillChoice, archivist);
                        scan.nextLine();
                        validAction = true;
                    } else {
                        System.out.println("Skill is on cooldown! " + chosen.getSkillCooldown(skillChoice) + " turn(s) remaining.");
                        System.out.println("Choose another action.");
                        scan.nextLine();
                    }
                }
            }


            //enemy turn
            if (archivist.getHp() > 0) {
                int randomSkill = archivist.random.nextInt(3) + 1;
                archivist.useSkill(randomSkill, chosen);
                scan.nextLine();
            }


            //defeat condition
            if (archivist.getHp() <= 0) {
                System.out.println(archivist.getName() + " has been defeated!");
                scan.nextLine();
                chosen.resetCooldowns();
                chosen.setHp(chosen.getMaxHp());
                System.out.println(chosen.getName() + "'s HP has been fully restored!");
                scan.nextLine();
                chosen.setLevel(chosen.getLevel() + 1);
                System.out.println(chosen.getName() + " leveled up to level " + chosen.getLevel() + "!");
                scan.nextLine();
                chosen.setPotionCount(chosen.getPotionCount() + 5);
                System.out.println("Reward: 5 Health Potion added to inventory.");
                scan.nextLine();
                System.out.println("Current Level: "+chosen.getLevel());
                System.out.println("Current Potions: " + chosen.getPotionCount());
                scan.nextLine();
                System.out.println("You may now proceed on your journey.");
                scan.nextLine();
                break;
            }
            else if (chosen.getHp() <= 0) {
                System.out.println(chosen.getName() + " has been defeated!");
                scan.nextLine();
                String retryChoice = "";


                while (true) {
                    System.out.print("Would you like to try again? (y/n): ");
                    retryChoice = scan.next();


                    if (retryChoice.equalsIgnoreCase("y")) {
                        chosen.resetCooldowns();
                        chosen.setHp(chosen.getMaxHp());
                        archivist.setHp(archivist.getMaxHp());
                        System.out.println("You have been revived! The battle restarts.");
                        break;
                    } else if (retryChoice.equalsIgnoreCase("n")) {
                        System.out.println("Game Over. Thank you for playing!");
                        scan.close();
                        return;
                    } else {
                        System.out.println("Invalid choice! Please type only 'y' or 'n'.");
                    }
                }
                scan.nextLine();
            }
        }


        //============================================================NATION 2============================================================

        //============================================================NATION 2 MINI QUEST============================================================
        storyline.whispersBeneathTheBoughs(chosen);
        //storyline.theSilverfangTrial(chosen);
        //============================================================NATION 2 MINI BOSS============================================================
        
        Ilaryx ilaryx = new Ilaryx();

        System.out.println();
        System.out.println("You are now fighting "+ilaryx.getName()+", Veyora Nation - Main Boss");

        System.out.println("HP: " + ilaryx.getHp() + " | Level: "+ ilaryx.getLevel());
        scan.nextLine();
        
        while (ilaryx.getHp() > 0) {
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
                } else if (skillChoice >= 1 && skillChoice <= 3) {
                    if (chosen.isSkillAvailable(skillChoice)) {
                        chosen.useSkill(skillChoice, ilaryx);
                        scan.nextLine();
                        validAction = true;
                    } else {
                        System.out.println("Skill is on cooldown! " + chosen.getSkillCooldown(skillChoice) + " turn(s) remaining.");
                        System.out.println("Choose another action.");
                        scan.nextLine();
                    }
                }
            }


            //enemy turn
            if (ilaryx.getHp() > 0) {
                int randomSkill = archivist.random.nextInt(3) + 1;
                ilaryx.useSkill(randomSkill, chosen);
                scan.nextLine();
            }


            //defeat condition
            if (ilaryx.getHp() <= 0) {
                System.out.println(ilaryx.getName() + " has been defeated!");
                scan.nextLine();
                chosen.resetCooldowns();
                chosen.setHp(chosen.getMaxHp());
                System.out.println(chosen.getName() + "'s HP has been fully restored!");
                scan.nextLine();
                chosen.setLevel(chosen.getLevel() + 1);
                System.out.println(chosen.getName() + " leveled up to level " + chosen.getLevel() + "!");
                scan.nextLine();
                chosen.setPotionCount(chosen.getPotionCount() + 2);
                System.out.println("Reward: 2 Health Potion added to inventory.");
                scan.nextLine();
                System.out.println("Current Level: "+chosen.getLevel());
                System.out.println("Current Potions: " + chosen.getPotionCount());
                scan.nextLine();
                System.out.println("You may now proceed on your journey.");
                scan.nextLine();
                break;
            }
            else if (chosen.getHp() <= 0) {
                System.out.println(chosen.getName() + " has been defeated!");
                scan.nextLine();
                String retryChoice = "";


                while (true) {
                    System.out.print("Would you like to try again? (y/n): ");
                    retryChoice = scan.next();


                    if (retryChoice.equalsIgnoreCase("y")) {
                        chosen.resetCooldowns();
                        chosen.setHp(chosen.getMaxHp());
                        ilaryx.setHp(ilaryx.getMaxHp());
                        System.out.println("You have been revived! The battle restarts.");
                        break;
                    } else if (retryChoice.equalsIgnoreCase("n")) {
                        System.out.println("Game Over. Thank you for playing!");
                        scan.close();
                        return;
                    } else {
                        System.out.println("Invalid choice! Please type only 'y' or 'n'.");
                    }
                }
                scan.nextLine();
            }
        }



        //============================================================NATION 2 MAIN QUEST============================================================
        storyline.theRootsOfDespair(chosen);
        //storyline.theWardenOfTheFractured(chosen);
        //============================================================NATION 2 MAIN BOSS============================================================

        Lunareth lunareth = new Lunareth();

        System.out.println();
        System.out.println("You are now fighting "+lunareth.getName()+", Veyora Nation - Main Boss");

        System.out.println("HP: " + lunareth.getHp() + " | Level: "+ lunareth.getLevel());
        scan.nextLine();
        
        while (lunareth.getHp() > 0) {
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
                } else if (skillChoice >= 1 && skillChoice <= 3) {
                    if (chosen.isSkillAvailable(skillChoice)) {
                        chosen.useSkill(skillChoice, lunareth);
                        scan.nextLine();
                        validAction = true;
                    } else {
                        System.out.println("Skill is on cooldown! " + chosen.getSkillCooldown(skillChoice) + " turn(s) remaining.");
                        System.out.println("Choose another action.");
                        scan.nextLine();
                    }
                }
            }


            //enemy turn
            if (lunareth.getHp() > 0) {
                int randomSkill = lunareth.random.nextInt(3) + 1;
                lunareth.useSkill(randomSkill, chosen);
                scan.nextLine();
            }


            //defeat condition
            if (lunareth.getHp() <= 0) {
                System.out.println(lunareth.getName() + " has been defeated!");
                scan.nextLine();
                chosen.resetCooldowns();
                chosen.setHp(chosen.getMaxHp());
                System.out.println(chosen.getName() + "'s HP has been fully restored!");
                scan.nextLine();
                chosen.setLevel(chosen.getLevel() + 1);
                System.out.println(chosen.getName() + " leveled up to level " + chosen.getLevel() + "!");
                scan.nextLine();
                chosen.setPotionCount(chosen.getPotionCount() + 5);
                System.out.println("Reward: 5 Health Potion added to inventory.");
                scan.nextLine();
                System.out.println("Current Level: "+chosen.getLevel());
                System.out.println("Current Potions: " + chosen.getPotionCount());
                scan.nextLine();
                System.out.println("You may now proceed on your journey.");
                scan.nextLine();
                break;
            }
            else if (chosen.getHp() <= 0) {
                System.out.println(chosen.getName() + " has been defeated!");
                scan.nextLine();
                String retryChoice = "";


                while (true) {
                    System.out.print("Would you like to try again? (y/n): ");
                    retryChoice = scan.next();


                    if (retryChoice.equalsIgnoreCase("y")) {
                        chosen.resetCooldowns();
                        chosen.setHp(chosen.getMaxHp());
                        lunareth.setHp(lunareth.getMaxHp());
                        System.out.println("You have been revived! The battle restarts.");
                        break;
                    } else if (retryChoice.equalsIgnoreCase("n")) {
                        System.out.println("Game Over. Thank you for playing!");
                        scan.close();
                        return;
                    } else {
                        System.out.println("Invalid choice! Please type only 'y' or 'n'.");
                    }
                }
                scan.nextLine();
            }
        }


        //============================================================NATION 3 MINI QUEST============================================================
        storyline.theLastBastionQuest(chosen);
        //============================================================NATION 3 MINI BOSS=============================================================
        scan.nextLine();

        System.out.print("\033[H\033[2J"); //clear screen using ANSI escape codes
        System.out.flush();
        
        Sarukdal sarukdal = new Sarukdal();

        System.out.println();
        System.out.println("You are now fighting "+sarukdal.getName()+", Veyora Nation - Main Boss");

        System.out.println("HP: " + sarukdal.getHp() + " | Level: "+ sarukdal.getLevel());
        scan.nextLine();
        
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
                } else if (skillChoice >= 1 && skillChoice <= 3) {
                    if (chosen.isSkillAvailable(skillChoice)) {
                        chosen.useSkill(skillChoice, sarukdal);
                        scan.nextLine();
                        validAction = true;
                    } else {
                        System.out.println("Skill is on cooldown! " + chosen.getSkillCooldown(skillChoice) + " turn(s) remaining.");
                        System.out.println("Choose another action.");
                        scan.nextLine();
                    }
                }
            }


            //enemy turn
            if (sarukdal.getHp() > 0) {
                int randomSkill = sarukdal.random.nextInt(3) + 1;
                sarukdal.useSkill(randomSkill, chosen);
                scan.nextLine();
            }


            //defeat condition
            if (sarukdal.getHp() <= 0) {
                System.out.println(sarukdal.getName() + " has been defeated!");
                scan.nextLine();
                chosen.resetCooldowns();
                chosen.setHp(chosen.getMaxHp());
                System.out.println(chosen.getName() + "'s HP has been fully restored!");
                scan.nextLine();
                chosen.setLevel(chosen.getLevel() + 1);
                System.out.println(chosen.getName() + " leveled up to level " + chosen.getLevel() + "!");
                scan.nextLine();
                chosen.setPotionCount(chosen.getPotionCount() + 2);
                System.out.println("Reward: 2 Health Potion added to inventory.");
                scan.nextLine();
                System.out.println("Current Level: "+chosen.getLevel());
                System.out.println("Current Potions: " + chosen.getPotionCount());
                scan.nextLine();
                System.out.println("You may now proceed on your journey.");
                scan.nextLine();
                break;
            }
            else if (chosen.getHp() <= 0) {
                System.out.println(chosen.getName() + " has been defeated!");
                scan.nextLine();
                String retryChoice = "";


                while (true) {
                    System.out.print("Would you like to try again? (y/n): ");
                    retryChoice = scan.next();


                    if (retryChoice.equalsIgnoreCase("y")) {
                        chosen.resetCooldowns();
                        chosen.setHp(chosen.getMaxHp());
                        sarukdal.setHp(sarukdal.getMaxHp());
                        System.out.println("You have been revived! The battle restarts.");
                        break;
                    } else if (retryChoice.equalsIgnoreCase("n")) {
                        System.out.println("Game Over. Thank you for playing!");
                        scan.close();
                        return;
                    } else {
                        System.out.println("Invalid choice! Please type only 'y' or 'n'.");
                    }
                }
                scan.nextLine();
            }
        }

        //============================================================NATION 3 MAIN QUEST============================================================
       storyline.theUnboundThroneQuest(chosen);

        //============================================================NATION 3 MAIN BOSS============================================================
        Elarion elarion = new Elarion();

        System.out.println();
        System.out.println("You are now fighting "+elarion.getName()+", Veyora Nation - Main Boss");

        System.out.println("HP: " + elarion.getHp() + " | Level: "+ elarion.getLevel());
        scan.nextLine();
        
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
                } else if (skillChoice >= 1 && skillChoice <= 3) {
                    if (chosen.isSkillAvailable(skillChoice)) {
                        chosen.useSkill(skillChoice, elarion);
                        scan.nextLine();
                        validAction = true;
                    } else {
                        System.out.println("Skill is on cooldown! " + chosen.getSkillCooldown(skillChoice) + " turn(s) remaining.");
                        System.out.println("Choose another action.");
                        scan.nextLine();
                    }
                }
            }


            //enemy turn
            if (elarion.getHp() > 0) {
                int randomSkill = elarion.random.nextInt(3) + 1;
                elarion.useSkill(randomSkill, chosen);
                scan.nextLine();
            }


            //defeat condition
            if (elarion.getHp() <= 0) {
                System.out.println(elarion.getName() + " has been defeated!");
                scan.nextLine();
                chosen.resetCooldowns();
                chosen.setHp(chosen.getMaxHp());
                System.out.println(chosen.getName() + "'s HP has been fully restored!");
                scan.nextLine();
                chosen.setLevel(chosen.getLevel() + 1);
                System.out.println(chosen.getName() + " leveled up to level " + chosen.getLevel() + "!");
                scan.nextLine();
                chosen.setPotionCount(chosen.getPotionCount() + 5);
                System.out.println("Reward: 5 Health Potion added to inventory.");
                scan.nextLine();
                System.out.println("Current Level: "+chosen.getLevel());
                System.out.println("Current Potions: " + chosen.getPotionCount());
                scan.nextLine();
                System.out.println("You may now proceed on your journey.");
                scan.nextLine();
                break;
            }
            else if (chosen.getHp() <= 0) {
                System.out.println(chosen.getName() + " has been defeated!");
                scan.nextLine();
                String retryChoice = "";


                while (true) {
                    System.out.print("Would you like to try again? (y/n): ");
                    retryChoice = scan.next();


                    if (retryChoice.equalsIgnoreCase("y")) {
                        chosen.resetCooldowns();
                        chosen.setHp(chosen.getMaxHp());
                        elarion.setHp(elarion.getMaxHp());
                        System.out.println("You have been revived! The battle restarts.");
                        break;
                    } else if (retryChoice.equalsIgnoreCase("n")) {
                        System.out.println("Game Over. Thank you for playing!");
                        scan.close();
                        return;
                    } else {
                        System.out.println("Invalid choice! Please type only 'y' or 'n'.");
                    }
                }
                scan.nextLine();
            }
        }

        System.out.println("As you reach the end");


        scan.close();
    }
}
