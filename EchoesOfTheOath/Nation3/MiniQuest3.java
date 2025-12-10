package EchoesOfTheOath.Nation3;

import EchoesOfTheOath.Characters.Character;
import java.util.*;

public class MiniQuest3 {
    private boolean isSkipped = false;
    Scanner scan = new Scanner(System.in);

    public void theLastBastion(Character chosen) {
        String name = chosen.getName();
        intro(chosen);

        while (true) {
            questStart(chosen);
            if (isSkipped) {
                System.out.println("You have skipped the Quest Dialogues, Proceeding forward...");
                scan.nextLine();
            }

            BrokenOathTrial(chosen);
            questOutro(chosen);
            break;
        }
    }

    private boolean waitOrSkip() {
        String input = scan.nextLine();
        if (input.equalsIgnoreCase("s")) {
            isSkipped = true;
            return true;
        }
        return false;
    }

    public void intro(Character chosen) {
        String name = chosen.getName();

        System.out.println(
                "(Lunareth collapses to one knee -- his armor splintering, fragments of light leaking out.)");
        scan.nextLine();
        System.out.println("Lunareth (faintly):");
        System.out.println("\"You were right... I couldn't protect them.\"");
        System.out.println("\"So I tried to control what was left.\"");
        scan.nextLine();

        System.out.println(name + ":");
        System.out.println("\"You don't have to carry it alone anymore.\"");
        scan.nextLine();
        System.out.println("Lunareth (smiling weakly):");
        System.out.println("\"The forest... remembers your name again.\"");
        scan.nextLine();

        System.out.println(
                "(The moonlight fades. Lunareth's body dissolves into silver motes that drift into the tree's heart.)");
        scan.nextLine();
        System.out.println("(Internal monologue):");
        System.out.println("\"The tyrant fell. The guardian returned to the roots.\"");
        System.out.println("\"And the forest finally slept.\"");
        scan.nextLine();

        System.out.println(
                "(The citadel is now overgrown with living vines. The Healer kneels beside " + name + ".)");
        scan.nextLine();
        System.out.println("Healer:");
        System.out.println("\"He found peace, didn't he?\"");
        scan.nextLine();
        System.out.println(name + " (looking at the glowing roots):");
        System.out.println("\"He found his way back.\"");
        scan.nextLine();

        System.out.println("(" + name + " opens their palm -- a fragment of Lunareth's bow rests there.)");
        scan.nextLine();
        System.out.println("Healer (softly):");
        System.out.println("\"Then Veyora breathes again.\"");
        scan.nextLine();

        System.out.println("\"The forest of Veyora stands tall once more.\"");
        scan.nextLine();
        System.out.println("\"But the sky above it whispers of storms yet to come.\"");
        scan.nextLine();
        System.out.println("\"For in the silence left by Lunareth's fall... something else begins to stir.\"");
        scan.nextLine();

        System.out.println(name
                + " stands at the edge of a massive chasm; the sky is a bruised, permanent twilight; black rock and ash cover the land;");
        System.out.println(
                "on the far side, the Umbral Fortress, a single, colossal obsidian structure, pulses with dark energy.");
        scan.nextLine();
        System.out.print("\"The Umbral Fortress.\"");
        scan.nextLine();

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("---------Continuing the Journey You have now Arrived at:----------");
        System.out.println("████    ██   ██████   ██████   ██   ██████   ████    ██    █████");
        System.out.println("██ ██   ██   ██  ██     ██     ██   ██  ██   ██ ██   ██   █    ██");
        System.out.println("██  ██  ██   ██████     ██     ██   ██  ██   ██  ██  ██      ███");
        System.out.println("██   ██ ██   ██  ██     ██     ██   ██  ██   ██   ██ ██   █    ██");
        System.out.println("██    ████   ██  ██     ██     ██   ██████   ██    ████    ████");
        System.out.println("------------------------Nation of Demons--------------------------");
        scan.nextLine();

        System.out.print("\"The air itself is poison. It doesn't just feel cold... it feels empty. Like a void.\"");
        scan.nextLine();
        System.out.print("\"This is where it all ended. This is where I fell.\"");
        scan.nextLine();
        System.out.println("\"Elarion is here. I can feel him... waiting.\"");
        scan.nextLine();

        System.out.println("(A flickering, unstable bridge of shadow is the only way across; as " + name
                + " steps onto it, the ghostly forms of THE ARCHIVIST and LUNARETH appear, their faces strained)");
        scan.nextLine();

        System.out.println("Spirit of Lunareth:");
        System.out.println(
                "\"The fortress... it feeds on our broken Oath. Be careful, Echo. The one who guards it... he was the strongest of us.\"");
        scan.nextLine();

        System.out.println("Spirit of The Archivist:");
        System.out.println(
                "\"Sarukdal. Our shield. He never broke. He never bent. What Elarion did to us... it will be nothing compared to what he did to him.\"");
        scan.nextLine();

        System.out.println("(the spirits fade; " + name + " crosses the chasm and enters the fortress)");
        scan.nextLine();

        System.out.println("(The Hall of Broken Oaths; the interior is a maze of shadowy corridors; as " + name
                + " walks, phantoms of the past play out on the walls—scenes of " + name + " and the three guardians)");
        scan.nextLine();

        System.out.println("(A phantom scene appears: the four guardians--" + name
                + ", ARCHIVIST, LUNARETH, and a massive, armored SARUKDAL--stand together, preparing for the original sealing)");
        scan.nextLine();

        System.out.println("Phantom " + name + " (Voice):");
        System.out.println("\"No matter what, we stand together. The Oath will hold.\"");
        scan.nextLine();

        System.out.println("hantom Sarukdal (Voice, deep and certain):");
        System.out.println(
                "\"I will be the shield. I will not falter. Let the void break against me. I will not fail you.\"");
        scan.nextLine();

        System.out.println("(the phantom scene shatters as a new, violent one replaces it: the moment " + name
                + " was struck down, the Oath shattering, Sarukdal screaming in rage and despair as the shadow consumes him)");
        scan.nextLine();
        System.out.println(
                "\"He didn't falter. I did, I was the weak link. My failure... it didn't just break the Oath, it broke him.\"");
        scan.nextLine();

        System.out.println("(A deep, grating voice echoes from a chamber ahead)");
        scan.nextLine();
        System.out.println("\"TRAITOR. WEAKLING. YOU HAVE RETURNED.\"");
        scan.nextLine();
        System.out.println("\"Sarukdal.\"");
        scan.nextLine();
    }

    public void questStart(Character chosen) {
        String name = chosen.getName();

        System.out.println("Quest Started: The Last Bastion");
        scan.nextLine();
        System.out.println("Objective: Navigate the Hall of Broken Oaths and find Sarukdal, the final guardian.");
        System.out.println("Press ['s'] to skip, and [Enter] to continue.");
        if (waitOrSkip())
            return;

        System.out.println("(The Chasm of Guilt; " + name
                + " enters a vast chamber; in the center, on a raised dais, kneels SARUKDAL. He is a monstrous being of shadow and armor, his greatshield fused to his body, pulsating with dark energy. He is kneeling before a pulsating dark crystal.)");
        if (waitOrSkip())
            return;
        System.out.println("(" + name
                + " finds the path blocked by a chasm; to cross, the hero must answer shadow-riddles that test their memory of the Oath)");
        if (waitOrSkip())
            return;
        System.out.print("(Darkness consumes the hall.)");
        if (waitOrSkip())
            return;
        System.out.print("(A blood-red sigil forms beneath you.)");
        if (waitOrSkip())
            return;
        System.out.print("Sarukdal whispers: \"IF YOU REMEMBER NOTHING... THEN YOU DESERVE NOTHING.\"");
        if (waitOrSkip())
            return;
    }

    public void BrokenOathTrial(Character chosen) {
        String name = chosen.getName();
        boolean restart = true;

        while (restart) {
            int tries = 3;

            System.out.println("================================================");
            System.out.println("           TRIAL OF THE BROKEN OATH");
            System.out.println("================================================");

            System.out.println();
            // ----------------------------- ROUND 1 ---------------------------------
            if (!round("ROUND 1: The First Fracture",
                    "WHAT HELD THE FOUR OF YOU TOGETHER?",
                    "Power",
                    "Fear of Elarion",
                    "The Oath you swore together",
                    "C",
                    tries)) {
                tries--;
            }
            if (tries == 0) {
                restart = gameOver();
                continue;
            }

            // ----------------------------- ROUND 2 ---------------------------------
            if (!round("ROUND 2: The Moment of Failure",
                    "WHY DID HE BREAK?",
                    "Because you failed first",
                    "Because he was too weak",
                    "Because the void chose him",
                    "A",
                    tries)) {
                tries--;
            }
            if (tries == 0) {
                restart = gameOver();
                continue;
            }

            // ----------------------------- ROUND 3 ---------------------------------
            if (!round("ROUND 3: The Guardian's Burden",
                    "WHAT WAS SARUKDAL'S TRUE ROLE?",
                    "To destroy the enemy",
                    "To endure what the others could not",
                    "To replace you",
                    "B",
                    tries)) {
                tries--;
            }
            if (tries == 0) {
                restart = gameOver();
                continue;
            }

            // ----------------------------- FINAL ROUND -----------------------------
            if (!round("FINAL ROUND: The Forbidden Question",
                    "WHAT BROKE THE OATH?",
                    "Elarion's power",
                    "Your lost memory",
                    "Your choice to forget",
                    "C",
                    tries)) {
                tries--;
            }
            if (tries == 0) {
                restart = gameOver();
                continue;
            }

            // ----------------------------- SUCCESS ---------------------------------
            System.out.println("SUCCESS -- You survived the Trial.");
            System.out.println("The shadows bow as the truth is accepted.");
            System.out.println("A bridge of black stone rises before you.");
            scan.nextLine();
            chosen.setLevel(chosen.getLevel() + 1);
            System.out.println(chosen.getName() + " leveled up to level " + chosen.getLevel() + "!");
            scan.nextLine();
            restart = false;
        }
    }

    public void questOutro(Character chosen) {
        String name = chosen.getName();
        System.out.println("(a bridge of solid shadow forms; the hero crosses)");
        scan.nextLine();

        System.out.println("You have finished the quest: The Last Bastion!");
        scan.nextLine();
        System.out.println("Quest Summary");
        System.out.println("You have navigated the fortress and found Sarukdal. ");
        System.out.println("He is consumed by his corrupted duty and the memory of your failure. ");
        System.out.println("There is no path forward but through him.");
        scan.nextLine();

        chosen.setPotionCount(chosen.getPotionCount() + 3);
        System.out.println("Reward: 3 health potions added to your inventory.");
        scan.nextLine();
        System.out.println("Current Level: " + chosen.getLevel());
        System.out.println("Current Potions: " + chosen.getPotionCount());
        scan.nextLine();
        System.out.println("You may now proceed on your journey.");
        scan.nextLine();

        System.out.println(
                "(Before the Guardian; the hero steps onto the dais; Sarukdal slowly rises, his sheer size blotting out the light)");
        scan.nextLine();
        System.out.println("Sarukdal (voice, a deep echo of pain): ");
        System.out.println("\"You... came back. After you ran. After you failed.\"");
        scan.nextLine();
        System.out.println(name + ":");
        System.out.println("\"I didn't run. I fell. I lost my memory...\"");
        scan.nextLine();
        System.out.println("Sarukdal: ");
        System.out.println(
                "\"LIES! The Oath was bound to your memory! You CHOSE to forget! You left us to rot! I... I held... I held as long as I could... but the shield shattered from within.\"");
        scan.nextLine();
        System.out.println(
                "\"He's right. Forgetting was a betrayal.\" \"There's no explaining this. Only answering for it.\"");
        scan.nextLine();
        System.out.println("(Sarukdal raises his massive, corrupted shield)");
        scan.nextLine();
        System.out.println("Sarukdal: ");
        System.out.println("\"I was the shield. Now, I am the wall. You will not pass. You will not hurt us again.\"");

        scan.nextLine();

        System.out.print("\033[H\033[2J"); // clear screen using ANSI escape codes
        System.out.flush();
    }

    // ============================================================================
    // SIMPLE ROUND HANDLER (String inputs, no arrays)
    // ============================================================================
    private boolean round(String title, String question,
            String optionA, String optionB, String optionC,
            String correctAnswer, int triesLeft) {

        while (true) {
            System.out.println("----------------------------------------");
            System.out.println(title);
            System.out.println("Tries Left: " + triesLeft);
            System.out.println("----------------------------------------");
            scan.nextLine();

            System.out.println(question);
            scan.nextLine();

            System.out.println("A. " + optionA);
            scan.nextLine();

            System.out.println("B. " + optionB);
            scan.nextLine();

            System.out.println("C. " + optionC);
            scan.nextLine();

            System.out.print("\nYour answer (A/B/C): ");
            String input = scan.nextLine().trim();

            // FIXED VALIDATION LOOP
            while (!input.equalsIgnoreCase("A") &&
                    !input.equalsIgnoreCase("B") &&
                    !input.equalsIgnoreCase("C")) {

                System.out.println("Invalid choice. Please enter A, B, or C only.");
                System.out.print("Your answer (A/B/C): ");
                input = scan.nextLine().trim();
            }

            // Correct?
            if (input.equalsIgnoreCase(correctAnswer)) {
                System.out.println("Correct.");
                scan.nextLine();
                return true;
            } else {
                System.out.println("Wrong.");
                System.out.println("The shadows whisper: \"You remember nothing.\"");
                scan.nextLine();
                return false;
            }
        }
    }

    // ============================================================================
    // GAME OVER SCREEN
    // ============================================================================
    private boolean gameOver() {

        System.out.println("\n=======================================");
        System.out.println("                GAME OVER");
        System.out.println("=======================================");
        scan.nextLine();
        System.out.println("The sigil explodes in red light.");
        System.out.println("The shadows engulf you completely.");
        scan.nextLine();
        System.out.println("Sarukdal roars: \"AND YOU DARE FACE ME WITHOUT MEMORY OR HONOR?\"");
        scan.nextLine();
        System.out.println("You have forgotten too much.");
        System.out.println("Restarting point: Entrance to the Hall of Broken Oaths\n");

        while (true) {
            try {
                System.out.println("[1] Restart Trial");
                System.out.println("[2] Exit Trial, Continue the Journey");
                System.out.print("Choose: ");

                int choice = scan.nextInt();
                scan.nextLine(); 

                if (choice == 1 || choice == 2) {
                    return (choice == 1);
                } else {
                    System.out.println("Invalid choice. Press 1 or 2 only.");
                }
            } catch (Exception e) {
                System.out.println("Invalid choice. Press 1 or 2 only.");
                scan.nextLine(); 
            }
        }
    }
}
