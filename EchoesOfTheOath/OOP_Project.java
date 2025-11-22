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

        chosen.setLevel(1);
        Storyline storyline = new Storyline();

        //============================================================NATION 1============================================================
        storyline.quest1HumanasQuest(chosen);//Nation 1 Mini Quest
        
        babyM babyM = new babyM();//Nation 1 Mini Boss
        BattleLogic.battleLogic(chosen, babyM);

        storyline.theVeinsOfHumanasQuest(chosen);//Nation 1 Main Quest

        Archivist archivist = new Archivist();//Nation 1 Main Boss
        BattleLogic.battleLogic(chosen, archivist);

        //============================================================NATION 2============================================================
        storyline.whispersBeneathTheBoughs(chosen);//Nation 2 Mini Quest

        Ilaryx ilaryx = new Ilaryx();//Nation 2 Mini Boss
        BattleLogic.battleLogic(chosen, ilaryx);

        storyline.theRootsOfDespair(chosen);//Nation 2 Main Quest
        
        Lunareth lunareth = new Lunareth();//Nation 2 Main Boss
        BattleLogic.battleLogic(chosen, lunareth);

        //============================================================NATION 3============================================================
        storyline.theLastBastionQuest(chosen);//Nation 3 Mini Quest
        
        Sarukdal sarukdal = new Sarukdal();//Nation 3 Mini Boss
        BattleLogic.battleLogic(chosen, sarukdal);

        storyline.theUnboundThroneQuest(chosen);//Nation 3 Main Quest

        Elarion elarion = new Elarion();//Nation 3 Main Boss
        BattleLogic.battleLogic(chosen, elarion);

        System.out.println("As you reach the end");

        scan.close();
    }
}
