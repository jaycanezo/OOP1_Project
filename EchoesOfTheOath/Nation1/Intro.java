package EchoesOfTheOath.Nation1;

import java.util.Scanner;

import EchoesOfTheOath.Characters.*;
import EchoesOfTheOath.Characters.Character;

public class Intro {
    
    public static Character main(String[] args) throws InterruptedException {

        Scanner scan = new Scanner(System.in);

        displayGameIntro(scan);
        storylineIntro(scan);

        // Character creation
        Warrior warrior = new Warrior();
        Archer archer = new Archer();
        Mage mage = new Mage();

        warrior.setLevel(11);
        archer.setLevel(11);
        mage.setLevel(11);

        // Trials
        try {
            runCharacterTrial(scan);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        storyCutscene(scan);

        // Character selection
        Character chosen = chooseCharacter(scan);

        String playerName = askPlayerName(scan);
        chosen.setName(playerName);

        System.out.println("\nYou have chosen the " + chosen.getClass().getSimpleName() +
                           " named " + chosen.getName() + "!");
        scan.nextLine();
        System.out.println("Press enter to continue your Journey...");
        scan.nextLine();

        finalDemonScene(scan, chosen);

        clearScreen();
        chosen.setLevel(1);

        return chosen;
    }

    // -------------------------------------------------------------
    // METHODS SECTION
    // -------------------------------------------------------------

    private static void displayGameIntro(Scanner scan) {
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

        scan.nextLine();
        clearScreen();
    }

    private static void storylineIntro(Scanner scan) throws InterruptedException {
        String text =
            "Long ago, three adventurers-a brave warrior, a swift archer, and a wise mage-" +
            "traveled across worlds, earning fame for vanquishing great evils. Their bond was unbreakable, " +
            "their deeds legendary...\n\nUntil one mission brought them face to face with a power unlike any other-" +
            "a force that had slain even the mightiest heroes.";

        for (char c : text.toCharArray()) {
            System.out.print(c);
            System.out.flush();
            Thread.sleep(1);
        }

        scan.nextLine();
        System.out.println();
    }

    public static void runCharacterTrial(Scanner scan) throws Exception {

        // Create characters
        Warrior warrior = new Warrior();
        Archer archer = new Archer();
        Mage mage = new Mage();
        Boss boss = new Boss();

        // SET LEVEL (as in your original code)
        warrior.setLevel(11);
        archer.setLevel(11);
        mage.setLevel(11);

        String input = "";

        // ---------------------------
        // WARRIOR TRIAL
        // ---------------------------
        System.out.print("You encountered a powerful foe!");
        scan.nextLine();

        System.out.println("\nWarrior engaged with the powerful foe!");
        do {
            System.out.println("Press 'D' to display character's information.");
            try {
                input = scan.nextLine();
                if (!input.equalsIgnoreCase("D")) {
                    System.out.println("Invalid input. Please press 'D' to display character information.\n");
                }
            } catch (Exception e) {
                System.out.println("Error at: " + e + ", try again.\n");
                scan.nextLine();
            }
        } while (!input.equalsIgnoreCase("D"));

        warrior.displayCharacterInfo();

        while (!warrior.allSkillsUsed()) {
            System.out.print("Choose a skill (1, 2, 3): ");
            int skillChoice = 0;
            try {
                skillChoice = scan.nextInt();
                if (skillChoice < 1 || skillChoice > 3) {
                    System.out.println("Please press [1], [2] or [3] only.");
                }
            } catch (Exception e) {
                System.out.println("Invalid Input. Please press [1], [2] or [3] only.\n");
                scan.next();
                continue;
            }
            warrior.useSkill(skillChoice, boss);
            System.out.println();
        }
        scan.nextLine();


        // ---------------------------
        // ARCHER TRIAL
        // ---------------------------
        System.out.println("Archer steps in from a distance!");
        do {
            System.out.println("Press 'D' to display character's information.");
            try {
                input = scan.nextLine();
                if (!input.equalsIgnoreCase("D")) {
                    System.out.println("Invalid input. Please press 'D' to display character information.\n");
                }
            } catch (Exception e) {
                System.out.println("Error at: " + e + ", try again.\n");
                scan.nextLine();
            }
        } while (!input.equalsIgnoreCase("D"));

        archer.displayCharacterInfo();

        while (!archer.allSkillsUsed()) {
            System.out.print("Choose a skill (1, 2, 3): ");
            int skillChoice = 0;
            try {
                skillChoice = scan.nextInt();
                if (skillChoice < 1 || skillChoice > 3) {
                    System.out.println("Please press [1], [2] or [3] only.");
                }
            } catch (Exception e) {
                System.out.println("Invalid Input. Please press [1], [2] or [3] only.\n");
                scan.next();
                continue;
            }
            archer.useSkill(skillChoice, boss);
            System.out.println();
        }
        scan.nextLine();


        // ---------------------------
        // MAGE TRIAL
        // ---------------------------
        System.out.println("The Mage steps forward, staff in hand, ready to unleash the power of the elements.");
        do {
            System.out.println("Press 'D' to display character's information.");
            try {
                input = scan.nextLine();
                if (!input.equalsIgnoreCase("D")) {
                    System.out.println("Invalid input. Please press 'D' to display character information.\n");
                }
            } catch (Exception e) {
                System.out.println("Error at: " + e + ", try again.\n");
                scan.nextLine();
            }
        } while (!input.equalsIgnoreCase("D"));

        mage.displayCharacterInfo();

        while (!mage.allSkillsUsed()) {
            System.out.print("Choose a skill (1, 2, 3): ");
            int skillChoice = 0;
            try {
                skillChoice = scan.nextInt();
                if (skillChoice < 1 || skillChoice > 3) {
                    System.out.println("Please press [1], [2] or [3] only.");
                }
            } catch (Exception e) {
                System.out.println("Invalid Input. Please press [1], [2] or [3] only.\n");
                scan.next();
                continue;
            }
            mage.useSkill(skillChoice, boss);
            System.out.println();
        }
        scan.nextLine();
    }


    private static void storyCutscene(Scanner scan) {
        System.out.println("Archer: Oh no the Demon is preparing to launch an attack!");
        scan.nextLine();
        System.out.println("Warrior: Lets use our power together");
        scan.nextLine();
        System.out.println("The heroes then leaped forward the Demon combining their strengths in hopes of defeating the enemy.");
        scan.nextLine();
    }

    private static Character chooseCharacter(Scanner scan) {
        Character chosen = null;
        int choice = 0;

        System.out.println("You have witnessed the power of the heroes! Choose a Character in order to continue the story.");
        System.out.print("[1]Warrior [2]Archer [3]Mage: ");

        while (true) {
            try {
                choice = scan.nextInt();
                scan.nextLine();

                if (choice < 1 || choice > 3) {
                    System.out.println("\nPlease enter [1] for Warriorr, [2] for Archer or [3] for Mage only.");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("\nInput Error. Please enter [1] for Warrior, [2] for Archer or [3] for Mage only:");
                scan.nextLine();
                continue;
            }

            switch (choice) {
                case 1: chosen = new Warrior(); break;
                case 2: chosen = new Archer(); break;
                case 3: chosen = new Mage(); break;
            }
            break;
        }

        return chosen;
    }

    private static String askPlayerName(Scanner scan) {
        String playerName;

        while (true) {
            System.out.println();
            System.out.print("Enter your hero's name: ");
            playerName = scan.nextLine();

            if (playerName != null && playerName.trim().isEmpty()) {
                System.out.println("\nInput does not accept empty, or space only String");
                continue;
            }
            break;
        }

        return playerName;
    }

    private static void finalDemonScene(Scanner scan, Character chosen) {
        System.out.println("Demon: You will not defeat me you weaklings! HaHAhaHA");
        scan.nextLine();
        System.out.println("The Demon uses its Ultimate: \"The Final Vow\"");
        System.out.println();
        System.out.println("Demon: Your destiny cannot escape the Darkness.");
        scan.nextLine();
        System.out.println("The Demon calls down a pillar of fate that engulfs the heroes in Darkness.");
        scan.nextLine();
        System.out.println(chosen.getName() + ": Noooo! I won't let this be the end of us!");
        scan.nextLine();
        System.out.println("As the Darkness surrounds you, a mysterious light begins to glow within your heart.");
        scan.nextLine();
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

