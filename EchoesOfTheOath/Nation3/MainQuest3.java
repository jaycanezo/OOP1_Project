package EchoesOfTheOath.Nation3;

import EchoesOfTheOath.Characters.Character;
import java.util.*;

public class MainQuest3 {
    Scanner scan = new Scanner(System.in);

    public void theUnboundThroneQuest(Character chosen) { // Nation 3 Main Quest
        boolean isSuccess = false;
        String input = "";

        while (true) {
            while (true) {
                System.out.println("Press ['s'] to skip dialogue, and [Enter] to continue.");
                input = scan.nextLine();
                if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                    break;
                System.out.println(
                        "You land the final blow. Sarukdal falls to one knee. The dark energy cracks and shatters from his armor. The shadow recedes from his eyes, revealing the true guardian beneath.)");
                input = scan.nextLine();
                if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                    break;
                System.out.println("Sarukdal (voice, now quiet and clear, though weak):");
                System.out.println("\"" + chosen.getName() + "...? You... you came back. You... broke through.\"");
                input = scan.nextLine();
                if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                    break;
                System.out.println(chosen.getName() + " ( kneeling): ");
                System.out.println("\"I'm sorry, Sarukdal. I'm so sorry. I failed you.\"");
                input = scan.nextLine();
                if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                    break;
                System.out.println("Sarukdal (a small, sad smile):");
                System.out.println(
                        "\"No... I failed you. I let the despair... let him... twist my duty. I couldn't hold...\"");
                input = scan.nextLine();
                if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                    break;
                System.out.println(chosen.getName() + " : ");
                System.out.println("\"Where is he? Elarion.\"");
                input = scan.nextLine();
                if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                    break;
                System.out.println("Sarukdal (points upward):");
                System.out.println(
                        "\"The throne... where we swore the Oath. He is... unmaking it. He's using our own power against the world.\"");
                System.out.println("\"You... must... restore... the... Oath...\"");
                input = scan.nextLine();
                if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                    break;
                System.out.print(
                        "(Sarukdal dissolves into motes of dark and light. His greatshield clatters to the ground, now cleansed of corruption. Your final memory is restored.)");
                input = scan.nextLine();
                if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                    break;
                System.out.print("\"I remember everything. The sealing. The fall. The reason.\"");
                input = scan.nextLine();
                if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                    break;
                System.out.print("\"Elarion isn't just a being. He's the shadow we cast.\"");
                input = scan.nextLine();
                if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                    break;
                System.out.print("\"And I'm the only one left to cast it.\"");
                input = scan.nextLine();
                if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                    break;
                System.out.print(
                        "(The chamber behind Sarukdal opens, revealing a spiraling staircase of fractured light, ascending into pure darkness.)");
                input = scan.nextLine();
                if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                    break;
                System.out.print("\"The Forgotten Throne. Where we bound him.\"");
                input = scan.nextLine();
                if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                    break;
                System.out.println(
                        "\"Sarukdal said he's 'unmaking' the Oath. I can feel it. The world's energy is draining... siphoning... right to the room above me.\"");
                input = scan.nextLine();
                if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                    break;
                System.out.println("\"This ends now.\"");
                break;
            }
            for (int i = 3; i > 0; i--) {
                scan.nextLine();
                while (true) {
                    System.out.println("\nQuest Started: The Unbound Throne");
                    System.out.println("Lives: " + i);
                    System.out.println(
                            "Objective: Ascend the labyrinth and confront Elarion in the Forgotten Throne room.");
                    input = scan.nextLine();
                    if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                        break;
                    System.out.println(
                            "(You are in a void, a maze of floating platforms and broken architecture from Humanas and the Elven lands. Elarion's voice echoes from everywhere and nowhere.)");
                    break;
                }
                while (true) {
                    System.out.println(
                            "A chasm opens before you, bridged by three unstable platforms. One path is covered in shimmering Humanas glyphs; another is bathed in a fading Elven light; the third is a straight path unmarred by shadows.");
                    System.out.print("Which path would you choose? [1][2][3]: ");
                    input = scan.nextLine();
                    if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip")) {
                        System.out.println("Cannot skip during a critical path choice.\n");
                    } else if (input.equalsIgnoreCase("1") || input.equalsIgnoreCase("one")) {
                        System.out.println("The path crumbles. You died.");
                        System.out.println("Restarting the quest...");
                        isSuccess = false;
                        break;
                    } else if (input.equalsIgnoreCase("2") || input.equalsIgnoreCase("two")) {
                        System.out.println("\nElarion (Voice, calm, amused): ");
                        System.out.println(
                                "\"My favorite guardian. The key to my prison. You came all this way... just to see what you've wrought.\"");
                        isSuccess = true;
                        break;
                    } else if (input.equalsIgnoreCase("3") || input.equalsIgnoreCase("three")) {
                        System.out.println(
                                "The path disconnects from the ledge the moment step in. It wasn't broken; it was never attached.");
                        System.out.println("Elarion (Voice, distant): \"In this place, safety is the greatest lie.\"");
                        System.out.println("Restarting the quest...");
                        isSuccess = false;
                        break;
                    } else {
                        System.out.println(
                                "Unsure of your footing, you hesitate, and the chasm's instability pulls you down. You died.");
                        System.out.println("Restarting the quest...");
                        isSuccess = false;
                        break;
                    }
                }
                if (!isSuccess)
                    continue;
                while (true) {
                    System.out.println(
                            "(As you navigate, you are attacked by \"Echoes of Guilt\"—shadow-phantoms of the townsfolk from Humanas and the elves from the civil war.)");
                    input = scan.nextLine();
                    if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                        break;
                    System.out.println("Phantom Elf:");
                    System.out.println("\"You let us die!\" ");
                    input = scan.nextLine();
                    if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip"))
                        break;
                    System.out.println("Phantom Human:");
                    System.out.println("\"You abandoned us!\"");
                    break;
                }
                while (true) {
                    System.out.print("Do you feel guilt? [y/n]: ");
                    input = scan.nextLine();
                    if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("skip")) {
                        System.out.println("Cannot skip during a critical event.\n");
                    } else if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                        System.out.print("\"He's using my own memories against me. The people I couldn't save.\"");
                        scan.nextLine();
                        System.out.print("Your ascent fails. You cannot escape your past.");
                        scan.nextLine();
                        System.out.println("Restarting the quest...");
                        isSuccess = false;
                        break;
                    } else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
                        System.out.print(
                                "The echoes recoil in disbelief, their accusations dissolving into wisps of smoke as they cannot latch onto your spirit.");
                        scan.nextLine();
                        System.out.print("\"He's using my own memories against me. The people I couldn't save.\"");
                        scan.nextLine();
                        System.out.print("\"He's trying to break my will. Just like he broke the others.\"");
                        scan.nextLine();
                        System.out.println("\"But I'm not them. Not anymore.\"\n");
                        isSuccess = true;
                        break;
                    } else {
                        System.out.println("\nYou hesitate, unable to formulate an answer.");
                        System.out.println("Your ascent fails due to indecision.");
                        System.out.println("Restarting the quest...");
                        isSuccess = false;
                        break;
                    }
                }
                if (!isSuccess)
                    continue;
                System.out.println(
                        "(You reach the large, circular platform. A swirling portal of shadow blocks the path forward.)");
                System.out.println(
                        "To pass, you must attune your will to the portal's chaotic energy and step through without being consumed.");
                System.out.println(
                        "You focus your energy and attempt to stabilize the shift... Rolling for **Synchronization** (Need 6 or higher)...");
                java.util.Random rand = new java.util.Random();
                int syncRoll = rand.nextInt(8) + 1;
                scan.nextLine();
                System.out.println("Your Synchronization Roll: " + syncRoll);
                scan.nextLine();
                if (syncRoll < 6) {
                    System.out.println("The shadow portal rejects your attempt!");
                    scan.nextLine();
                    System.out.println(
                            "(A figure steps out of the portal. It is you, just as you were at the moment of your first fall—armor broken, weapon shattered, eyes wide with terror.)\n");
                    scan.nextLine();
                    System.out.println("Elarion (Voice):");
                    System.out.print("\"You think you have grown beyond your failures? Look again.\"");
                    scan.nextLine();
                    System.out.print("\"That moment... my fall. My fear. The part of me that gave up.\"");
                    System.out.print("(The shadow reaches out -- You grip your blade tighter.)");
                    scan.nextLine();
                    System.out.println("\"I am not running anymore.\"");
                    System.out.println("Restarting the quest...");
                    isSuccess = false;
                    continue;
                } else if (syncRoll >= 6) {
                    System.out.println("The portal stabilizes around you. You are on the other side.");
                    scan.nextLine();
                    System.out.println(
                            "(It is a vast, empty void. On a throne of fractured light sits ELARION. He appears as a shifting entity of pure shadow, but as you approach, he takes on a form — a perfect, mirror image of you, made of darkness.)");
                }
                scan.nextLine();
                System.out.println("Elarion:");
                System.out.println("\"So. The final piece returns.\"");
                scan.nextLine();
                System.out.println("Narration: \"This is it. The end.\"");
                System.out.println("\"He looks... like me. The shadow I cast.\"");
                break;
            }
            if (!isSuccess)
                System.out.println("\nRefilling lives...\nTry not to die this time...\n");
            else
                break;
        }
        scan.nextLine();
        System.out.println("\nYou have finished the quest: \"The Unbound Throne\"!");
        if (isSuccess) {
            System.out.println("STATUS: Quest Complete!");
            chosen.setLevel(chosen.getLevel() + 1);
            System.out.println(chosen.getName() + " leveled up to level " + chosen.getLevel() + "!");
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
            System.out.print("\"Your friends are gone. Your Oath is shattered. What possible hope do you have?\"");
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
            break;
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
