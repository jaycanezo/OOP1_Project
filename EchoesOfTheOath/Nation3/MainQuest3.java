package EchoesOfTheOath.Nation3;

import EchoesOfTheOath.Characters.Character;
import java.util.*;

public class MainQuest3 {
    String RESET = "\033[0m";
    String RED = "\033[31m";    
    String YELLOW = "\033[33m";  
    String BOLD = "\033[1m";
    String GREEN = "\033[32m";

    Scanner scan = new Scanner(System.in);

    public void theUnboundThroneQuest(Character chosen) { // Nation 3 Main Quest
        boolean isSuccess = false;
        String input = "";

        while (true) {
            System.out.println("You land the final blow. Sarukdal falls to one knee.");
            scan.nextLine();
            
            System.out.println("The dark energy cracks and shatters from his armor.");
            scan.nextLine();
            System.out.println("The shadow recedes from his eyes, revealing the true guardian beneath.");
            scan.nextLine();
            System.out.println("Sarukdal (voice, now quiet and clear, though weak):");
            System.out.println("\"" + chosen.getName() + "...? You... you came back. You... broke through.\"");
            scan.nextLine();
            System.out.println(chosen.getName() + " ( kneeling): ");
            System.out.println("\"I'm sorry, Sarukdal. I'm so sorry. I failed you.\"");
            scan.nextLine();
            System.out.println("Sarukdal (a small, sad smile):");
            System.out.println("\"No...I failed you. I let the despair...let him...twist my duty. I couldn't hold...\"");
            scan.nextLine();
            System.out.println(chosen.getName() + " : ");
            System.out.println("\"Where is he? Elarion.\"");
            scan.nextLine();
            System.out.println("Sarukdal (points upward):");
            System.out.print("\"The throne...where we swore the Oath. He is...unmaking it.\"");
            scan.nextLine();
            System.out.print("\"He's using our own power against the world.\"");
            scan.nextLine();
            System.out.println("\"You... must... restore... the... Oath...\"");
            scan.nextLine();
            System.out.println("(Sarukdal dissolves into motes of dark and light.)");
            scan.nextLine();
            System.out.println("His greatshield clatters to the ground, now cleansed of corruption.");
            scan.nextLine();
            System.out.println("Your final memory is restored.");
            scan.nextLine();
            System.out.println("\"I remember everything. The sealing. The fall. The reason.\"");
            scan.nextLine();
            System.out.println("\"Elarion isn't just a being. He's the shadow we cast.\"");
            scan.nextLine();
            System.out.println("\"And I'm the only one left to cast it.\"");
            scan.nextLine();
            System.out.println("(The chamber behind Sarukdal opens, revealing a spiraling staircase of fractured light, ascending into pure darkness.)");
            scan.nextLine();
            System.out.println("\"The Forgotten Throne. Where we bound him.\"");
            scan.nextLine();
            System.out.println("\"Sarukdal said he's 'unmaking' the Oath. I can feel it. The world's energy is draining... siphoning... right to the room above me.\"");
            scan.nextLine();
            System.out.print("\"This ends now.\"");
            scan.nextLine();
            break;
        }
        for (int i = 3; i > 0; i--) {
            while (true) {
                System.out.println(YELLOW+"\nMain Quest Started: \"The Unbound Throne\""+RESET);
                System.out.println("Lives: " + i);
                System.out.println("Objective: Ascend the labyrinth and confront Elarion in the Forgotten Throne room.");
                System.out.println(RED+"Final Quest: Skip Function Disabled"+RESET);
                scan.nextLine();
                System.out.println("(You are in a void, a maze of floating platforms and broken architecture from Humanas and the Elven lands)");
                scan.nextLine();
                System.out.println("(Elarion's voice echoes from everywhere and nowhere.)");
                scan.nextLine();
                break;
            }
            while (true) {
                System.out.print("A chasm opens before you, bridged by three unstable platforms.");
                scan.nextLine();
                System.out.print("1. One path is bathed in a fading Elven light");
                scan.nextLine();
                System.out.print("2. Another is covered in shimmering Humanas glyphs");
                scan.nextLine();
                System.out.print("3. A straight path unmarred by shadows");
                scan.nextLine();

                System.out.println();
                System.out.print("\nWhich path would you choose? [1][2][3]: ");
                input = scan.nextLine();
                if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip")) {
                    System.out.println("\nCannot skip during a critical path choice.\n");
                } else if (input.equalsIgnoreCase("1") || input.equalsIgnoreCase("one")) {
                    System.out.println("\nThe path crumbles. You died.");
                    if(i>1){
                        System.out.println(RED + "Restarting the quest..." + RESET);
                    }
                    isSuccess = false;
                    break;
                } else if (input.equalsIgnoreCase("2") || input.equalsIgnoreCase("two")) {
                    System.out.println("\nElarion (Voice, calm, amused): ");
                    System.out.println("\"My favorite guardian. The key to my prison. You came all this way... just to see what you've wrought.\"");
                    isSuccess = true;
                    break;
                } else if (input.equalsIgnoreCase("3") || input.equalsIgnoreCase("three")) {
                    System.out.println("\nThe path disconnects from the ledge the moment step in. It wasn't broken; it was never attached.");
                    System.out.println("In this place, safety is the greatest lie.");
                    if(i>1){
                        System.out.println(RED + "Restarting the quest..." + RESET);
                    }
                    isSuccess = false;
                    break;
                } else {
                    System.out.println("\nUnsure of your footing, you hesitate, and the chasm's instability pulls you down. You died.");
                    if(i>1){
                        System.out.println(RED + "Restarting the quest..." + RESET);
                    }
                    isSuccess = false;
                    break;
                }
            }
            if (!isSuccess)
                continue;
            input = scan.nextLine();
            while (true) {
                System.out.println("(As you navigate, you are attacked by shadow phantoms of the townsfolk from the civil war.)");
                scan.nextLine();
                System.out.println("Phantom Elf:");
                System.out.println("\"You let us die!\" ");
                scan.nextLine();
                System.out.println("Phantom Human:");
                System.out.println("\"You abandoned us!\"");
                scan.nextLine();
                break;
                }
            while (true) {
                System.out.print("Do you feel guilt? [y/n]: ");
                input = scan.nextLine();
                if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                    System.out.println("\n\"He's using my own memories against me. The people I couldn't save.\"");
                    scan.nextLine();
                    System.out.println("Your ascent fails. You cannot escape your past.");
                    if(i>1){
                        System.out.println(RED + "Restarting the quest..." + RESET);
                    }
                    isSuccess = false;
                    break;
                } else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
                    System.out.print("\nThe echoes recoil in disbelief.");
                    scan.nextLine();
                    System.out.print("\n\"He's using my own memories against me. The people I couldn't save.\"");
                    scan.nextLine();
                    System.out.print("\"He's trying to break my will. Just like he broke the others.\"");
                    scan.nextLine();
                    System.out.println("\"But I'm not them. Not anymore.\"");
                    isSuccess = true;
                    break;
                } else {
                    System.out.println("\nYou hesitate, unable to formulate an answer.");
                    scan.nextLine();
                    System.out.println("Your ascent fails due to indecision.");
                    if(i>1){
                        System.out.println(RED + "Restarting the quest..." + RESET);
                    }      
                    isSuccess = false;
                    break;
                }
            }
            scan.nextLine();
            if (!isSuccess)
                continue;
            System.out.println("(You reach the large, circular platform. A swirling portal of shadow blocks the path forward.)");
            input = scan.nextLine();
                if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip")) 
                    System.out.println("\nCannot skip during a critical event.");
            System.out.println("To pass, you must attune your will to the portal's chaotic energy and step through without being consumed.");
            input = scan.nextLine();
                if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip")) 
                    System.out.println("\nCannot skip during a critical event.");
            System.out.println("You focus your energy and attempt to stabilize the shift... Rolling for Synchronization" + YELLOW + " (Need 6 or higher)" + RESET + "..." );
            java.util.Random rand = new java.util.Random();
            int syncRoll = rand.nextInt(8) + 1;
            scan.nextLine();
            System.out.println(BOLD + "Your Synchronization Roll: " + syncRoll + RESET);
            scan.nextLine();
            if (syncRoll < 6) {
                System.out.println("The shadow portal rejects your attempt!");
                scan.nextLine();
                System.out.println("(A figure steps out of the portal. It is you.)");
                scan.nextLine();
                System.out.println("Just as you were at the moment of your first fall, armor broken, weapon shattered, eyes wide with terror.");
                scan.nextLine();
                System.out.println("Elarion (Voice):");
                System.out.print("\"You think you have grown beyond your failures? Look again.\"");
                scan.nextLine();
                System.out.print("\"That moment... my fall. My fear. The part of me that gave up.\"");
                System.out.print("(The shadow reaches out -- You grip your blade tighter.)");
                scan.nextLine();
                System.out.print("\"I am not running anymore.\"");
                scan.nextLine();
                if(i>1){
                    System.out.println(RED + "Restarting the quest..." + RESET);
                }
                isSuccess = false;
                continue;
            } else if (syncRoll >= 6) {
                System.out.println("The portal stabilizes around you. You are on the other side.");
                scan.nextLine();
                System.out.println("(It is a vast, empty void. On a throne of fractured light sits ELARION.)");
                scan.nextLine();
                System.out.println("He appears as a shifting entity of pure shadow");
                scan.nextLine();
                System.out.println("But as you approach, he takes on a form, a perfect, mirror image of you, made of darkness.)");
                isSuccess = true;
            }
            scan.nextLine();
            System.out.println("Elarion:");
            System.out.println("\"So. The final piece returns.\"");
            scan.nextLine();
            System.out.println("Narration: \"This is it. The end.\"");
            System.out.println("\"He looks... like me. The shadow I cast.\"");
            break;
        }
        scan.nextLine();
        System.out.println("You have finished the quest: \"The Unbound Throne\"!");
        scan.nextLine();
        System.out.println("Quest Summary:");
        System.out.println("With Sarukdal's spirit finally at peace, the path to the pinnacle of the Umbral Fortress had opened.");
        System.out.println("You ascended through the void-like labyrinth -- a manifestation of your own shattered memories and fears --");
        System.out.println("Until you reached the Forgotten Throne, where the Oath was first sworn, and confronted Elarion himself.");
        scan.nextLine();
        if (isSuccess) {
            chosen.setLevel(chosen.getLevel() + 1);
            System.out.println(chosen.getName() + " leveled up to level " + chosen.getLevel() + "!");
            scan.nextLine();
        }else{
            System.out.println("You did not level up.");
            scan.nextLine();
        }
        chosen.setPotionCount(chosen.getPotionCount() + 3);
        System.out.println("Reward: 3 health potions added to your inventory.");
        System.out.println("Current Level: " + chosen.getLevel());
        System.out.println("Current Potions: " + chosen.getPotionCount());
        scan.nextLine();
        while (true) {
            System.out.println("You may now proceed on your journey.");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                break;
            System.out.println("(Elarion stands from the throne, his shadow-form hero smiling condescendingly.)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                break;
            System.out.println("Elarion: ");
            System.out.print("\"You came all this way to find yourself. And here you are. Pathetic. Weak. Broken.\" ");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                break;
            System.out.print("\"Your friends are gone. Your Oath is shattered. What possible hope do you have?\"\n");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                break;
            System.out.println("(You raise your weapon, pendant glowing brightly):");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                break;
            System.out.print("\"I am the Echo.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                break;
            System.out.print("\"I am the Guardian.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                break;
            System.out.print("\"And I am the Oath.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                break;
            break;
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
