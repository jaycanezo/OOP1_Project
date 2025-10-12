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
        
        // Clear screen using ANSI escape codes
        System.out.print("\033[H\033[2J"); // Move cursor to top-left and clear screen
        System.out.flush();

        String text = "Long ago, three adventurers-a brave warrior, a swift archer, and a wise mage-traveled across worlds, earning fame for vanquishing great evils. Their bond was unbreakable, their deeds legendary...\n\nUntil one mission brought them face to face with a power unlike any other-a force that had slain even the mightiest heroes.";
        
        for (char c : text.toCharArray()) {
            System.out.print(c);
            System.out.flush(); // force immediate output
            Thread.sleep(1);  // paras text delay
        }

        scan.nextLine();
        System.out.println();
        
        Warrior warrior = new Warrior();
        Archer archer = new Archer();
        Mage mage = new Mage();
        Boss boss = new Boss();

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
        System.out.print("\033[H\033[2J"); // Move cursor to top-left and clear screen
        System.out.flush();

        //Start of the journey in the human nation


        System.out.println("[Darkness. Faint echoes of battle—swords clashing, spells bursting, someone screaming the hero’s name. Then silence.]");
        scan.nextLine();
        System.out.println("(soft inhale)\r\n…There it is again.");
        scan.nextLine();
        System.out.println("That same dream. The light, the roar, the pain— then… nothing.");
        scan.nextLine();

        System.out.println("You are now fighting King Bartholomew Monarch (\"Baby M\")");
        System.out.println();
        babyM babyM = new babyM();
        while(chosen.getHp()>0&&babyM.getHp()>0){
            if(chosen.getHp()>0){
                System.out.print("Choose a skill (1, 2, 3): ");
                int skillChoice = scan.nextInt();
                chosen.useSkill(skillChoice, babyM);
                System.out.println();
            } else {
                System.out.println(chosen.getName()+" has been defeated!");
                break;
            }
            
            if(babyM.getHp()>0){
                int randomSkill = babyM.random.nextInt(3) + 1;
                babyM.useSkill(randomSkill, chosen);
                System.out.println();
            } else if(babyM.getHp()<=0){
                System.out.println(babyM.getName()+" has been defeated!");
                chosen.setLevel(chosen.getLevel()+1);
                System.out.println(chosen.getName()+" leveled up to level "+chosen.getLevel()+"!");
                break;
            } else {
                System.out.println(chosen.getName()+" has been defeated!");
                break;
            }
        }

        System.out.println("Level: "+chosen.getLevel());
        System.out.println("You are now fighting The Archivist");
        System.out.println();
        Archivist archivist = new Archivist();
        while(chosen.getHp()>0&&archivist.getHp()>0){
            if(chosen.getHp()>0){
                System.out.print("Choose a skill (1, 2, 3): ");
                int skillChoice = scan.nextInt();
                chosen.useSkill(skillChoice, archivist);
                System.out.println();
            } else {
                System.out.println(chosen.getName()+" has been defeated!");
                break;
            }
            
            if(archivist.getHp()>0){
                int randomSkill = archivist.random.nextInt(3) + 1;
                archivist.useSkill(randomSkill, chosen);
                System.out.println();
            } else if(archivist.getHp()<=0){
                System.out.println(archivist.getName()+" has been defeated!");
                chosen.setLevel(chosen.getLevel()+1);
                System.out.println(chosen.getName()+" leveled up to level "+chosen.getLevel()+"!");
                break;
            } else {
                System.out.println(chosen.getName()+" has been defeated!");
                break;
            }
            
        }

        scan.close();
    }
}