package EchoesOfTheOath;

import java.util.Scanner;

public class Storyline {
    Scanner scan = new Scanner(System.in);
    
    public void theVeinsOfHumanasQuest(String name){
        while (true) {
            System.out.println("(fade out; thunder rolls over the castle as the camera pans up—distant shadowy figure watching from a tower)");
            System.out.println("“So… the Echo stirs again.”");
            scan.nextLine();

            System.out.println("Throne Room -- The air is still thick with smoke from battle. The broken crown lies in your hand.");
            scan.nextLine();

            System.out.println(name + " (softly):");
            System.out.println("\"This wasn't just one boy's curse. Someone built an empire on silence.\"");
            scan.nextLine();

            System.out.println("(A hidden passage creaks open behind the throne -- a servant girl steps out, dirt on her face, holding a worn ledger.)");
            scan.nextLine();

            System.out.println("Servant Girl:");
            System.out.println("\"You're not one of them... are you?\"");
            scan.nextLine();

            System.out.println(name + ":");
            System.out.println("\"Not anymore.\"");
            scan.nextLine();

            System.out.println("Servant Girl (whispers):");
            System.out.println("\"They call it The Veins. It's how they keep the kingdom alive -- by draining it. You can find the roots below.\"");
            scan.nextLine();

            System.out.println("(She hands you the ledger -- pages filled with names, taxes, disappearances, falsified decrees.)");
            System.out.println("Narration: \"Every name here... was rewritten by the same hand.\"");
            System.out.println("\"The Archivist's...\"");
            scan.nextLine();

            System.out.println("Quest Started: The Veins of Humanas");
            System.out.println("Objective: Descend into the Undercity and destroy the power network that feeds the nation's corruption.");
            System.out.println("Press ['s'] to skip, and [Enter] to continue.");
            String input = scan.nextLine();

            System.out.println("Phase 1 -- Descent to the Undercity");
            System.out.println("(You travel through abandoned tunnels beneath the capital. Broken murals show the once-glorious Humanas -- now cracked and forgotten.)");
            System.out.println("Narration: \"Once, these tunnels carried clean water. Now, they carry secrets... and blood.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                break;
            }

            System.out.println("(You encounter starving villagers, chained miners, soldiers hoarding supplies.)");
            System.out.println("Villager (weakly):");
            System.out.println("\"They said the King ordered this... but the King can't even speak.\"");
            System.out.println(name + " (grimly):");
            System.out.println("\"Then someone else is wearing his voice.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                break;
            }

            System.out.println("(Fight Sequence: You purge the royal guards enforcing 'tax extractions'. Each armor bears a quill -- The Archivist's mark.)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                break;
            }

            System.out.println("(You discover a vast golden mechanism pulsing with light beneath the city -- the Heart of the Veins.)");
            System.out.println("Phase 2 -- The Heart of the Veins");
            System.out.println("Narration: \"Power drawn from despair. The rich drink wine above, while the poor bleed beneath.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                break;
            }

            System.out.println("(A voice echoes -- calm, artificial, layered)");
            System.out.println("\"Progress requires sacrifice.\"");
            System.out.println("(A giant projection flickers to life -- The Archivist appears, holding a quill made of bone.)");
            System.out.println("The Archivist:");
            System.out.println("\"You freed the King. Admirable... but foolish. The people need guidance. They crave the comfort of command.\"");
            System.out.println(name + ":");
            System.out.println("\"They need truth. Not chains wrapped in gold.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                break;
            }

            System.out.println("(Mechanisms activate -- golems powered by human life awaken. Battle ensues!)");
            System.out.println("Sub-Bosses: The Collectors -- The Tax Collector and The Enforcer emerge!");
            System.out.println("Tax Collector Skill: Burden of the Crown -- steals 10% of your HP per hit.");
            System.out.println("Enforcer Skill: Law of Silence -- disables your skills for 10 seconds.");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                break;
            }

            System.out.println("(After an exhausting battle, you disable the Heart's control core.)");
            System.out.println("Narration: \"The light fades. For the first time in decades, the city above will go dark... and free.\"");
            System.out.println("The Archivist (voice):");
            System.out.println("\"So be it. You've chosen chaos. Now face the cost of your rebellion.\"");
            System.out.println("(Your pendant glows faintly -- a new mark appears: Echo Resonance II.)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                break;
            }

            System.out.println("Phase 3 -- The People Rise");
            System.out.println("(Above ground, the citizens of Humanas stand confused as the golden light fades.)");
            System.out.println("Elder:");
            System.out.println("\"What happened to the sky?\"");
            System.out.println("Servant Girl (smiling faintly):");
            System.out.println("\"It's not shining on lies anymore.\"");
            System.out.println("Narration:");
            System.out.println("\"Maybe they'll hate me for this. Maybe they'll thank me later.\"");
            System.out.println("\"But at least now, they'll see.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                break;
            }

            if(input.equalsIgnoreCase("s")) {
            System.out.println("Quest Summary:");
            System.out.println("After freeing the infant King from The Archivist's control, you uncover that the kingdom's true rot runs deep -- the nation of Humanas bleeds from within. The rich bathe in light while the slums drown in darkness. Every decree, every war, every famine, all trace back to the same unseen hand.");
            System.out.println("To restore balance, you must expose the Veins of Control -- an ancient network that feeds power to the corrupted throne.");
            System.out.println();
            System.out.println("You have finished the quest.");
            System.out.println("Reward: 3 health potions added to your inventory.");
            }
            break;
        }

        System.out.println("(camera pans to a high tower far from the capital — a cloaked figure dips a quill into ink made of black light.)");
        System.out.println("The Archivist (smiling faintly):");
        System.out.println("They learn… too slowly. But no matter. Every rebellion ends the same way.");
        scan.nextLine();
        System.out.println("(he writes your name into a massive book — the ink burns with crimson flame.)");
        System.out.println("Let's see if your story ends differently, Echo");

        System.out.print("\033[H\033[2J"); //clear screen
        System.out.flush();
    }
}
