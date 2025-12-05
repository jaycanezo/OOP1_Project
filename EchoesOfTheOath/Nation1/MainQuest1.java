package EchoesOfTheOath.Nation1;

import EchoesOfTheOath.Characters.Character;
import java.util.*;

public class MainQuest1 {

    static Scanner scan = new Scanner(System.in);

    // =====================================================
    // MAIN QUEST METHOD (FULLY UPDATED)
    // =====================================================
    public void VeinsOfHumanas(Character chosen) {
        String name = chosen.getName();
        // ============================
        // STORY SECTION
        // ============================

        System.out.println("(" + name + " kneels beside the crying child)");
        scan.nextLine();
        System.out.println(name + " (*softly):");
        System.out.println("Just a child. Never a king.");
        scan.nextLine();
        System.out.println("(the boy giggles faintly, reaching for " + name + "'s hand)");
        scan.nextLine();
        System.out.println("(Internal monologue)");
        System.out.println("\"Innocent. Powerless. A puppet crowned with someone else's ambition.\"");
        System.out.println("\"So this is what Humanas has become.\"");
        scan.nextLine();

        System.out.println(name + " looks out the shattered window toward the city");
        scan.nextLine();
        System.out.println(name + ":");
        System.out
                .println("\"If this nation's going to rise again, someone has to break every chain it hides behind.\"");
        scan.nextLine();

        System.out.println("(thunder rolls over the castle-distant shadowy figure watching from a tower)");
        System.out.println("\"So... the Echo stirs again.\"");
        scan.nextLine();

        System.out.println("Throne Room -- Smoke lingers from battle. The broken crown lies in " + name + "'s hand");
        scan.nextLine();

        System.out.println(name + " (softly):");
        System.out.println("\"This wasn't just one boy's curse. Someone built an empire on silence.\"");
        scan.nextLine();

        System.out.println("(A hidden passage opens - a servant girl steps out with a worn ledger.)");
        scan.nextLine();

        System.out.println("Servant Girl:");
        System.out.println("\"You're not one of them... are you?\"");
        scan.nextLine();

        System.out.println(name + ":");
        System.out.println("\"Not anymore.\"");
        scan.nextLine();

        System.out.println("Servant Girl (whispers):");
        System.out.println(
                "\"They call it The Veins. It's how they keep the kingdom alive - by draining it. You can find the roots below.\"");
        scan.nextLine();

        System.out.println("(The servant girl hands you a battered ledger filled with altered royal records)");
        System.out.println("\"The Archivist's...\"");
        scan.nextLine();

        System.out.println("Quest Started: The Veins of Humanas");
        System.out.println("Objective: Destroy the corrupt power network beneath the city.");
        System.out.println("Skip function is disbled for this quest.");
        scan.nextLine();

        // ============================
        // GAME STARTS
        // ============================
        int strikes = 3;
        int successCount = 0;

        // ---- PHASE 1 ----
        int strikes1 = phase1(strikes);
        if (strikes1 <= 0) {
            failReward(chosen);
        } else {
            successReward(chosen);
            successCount++;
        }

        // ---- PHASE 2 ----
        int strikes2 = phase2(strikes);
        if (strikes2 <= 0) {
            failReward(chosen);
        } else {
            successReward(chosen);
            successCount++;
        }

        // ---- PHASE 3 ----
        int strikes3 = phase3(strikes);
        if (strikes3 <= 0) {
            failReward(chosen);
        } else {
            successReward(chosen);
            successCount++;
        }

        // ---- PHASE 4 ----
        phase4();

        // ============================
        // QUEST SUCCESS
        // ============================
        if (successCount == 3) {
            chosen.setHp(chosen.getLevel() + 1);
            System.out.println("You were successful in " + (successCount + 1) + "/4 Phases.");
            chosen.setLevel(chosen.getLevel() + 1);
            System.out.println("You have leveled up to " + chosen.getLevel() + "!");
            scan.nextLine();
        } else {
            System.out.println(
                    "You did not levelup because you were only successful in " + (successCount + 1) + "/4 Phases.");
        }

        finishQuest(chosen);

        System.out.println(
                "(In a high tower far from the capital -- a cloaked figure dips a quill into ink made of black light.)");
        scan.nextLine();
        System.out.println("The Archivist (smiling faintly):");
        System.out.println("They learn... too slowly. But no matter. Every rebellion ends the same way.");
        scan.nextLine();
        System.out.println("(he writes your name into a massive book -- the ink burns with crimson flame.)");
        System.out.println("Let's see if your story ends differently, Echo");
        scan.nextLine();

        System.out.println(
                "(The sound of dripping ink. Whispering voices overlap faintly: echoes of names, pleas, debts, all fading in and out.)");
        System.out.println("\"They said the kingdom's memory is buried beneath the palace...\"");
        scan.nextLine();
        System.out.println("\"But they never said it was alive.\"");
        scan.nextLine();

        System.out.println(
                "(A massive, endless library carved beneath the city. Scrolls float through the air like ghosts. Ink seeps across the floor like veins.)");
        scan.nextLine();
        System.out.println("Every step feels heavier...");
        System.out.println("Like the air itself is keeping count.");
        scan.nextLine();

        System.out.println(
                "(A massive desk in the distance - shadowed, illuminated by a cold, pale light. A figure sits motionless behind towers of parchment.)");
        scan.nextLine();
        System.out.println("The Archivist (voice calm, layered, almost kind):");
        System.out.println("\"So. The wanderer finally reaches the end of the record.\"");
        scan.nextLine();
        System.out.println(chosen.getName() + ":");
        System.out.println("\"...So you're the one writing all this.\"");
        scan.nextLine();

        System.out.println("The Archivist:");
        System.out.println(
                "\"I don't write. I preserve. Every promise broken, every lie told, every soul sold for convenience. I am merely the ink that remembers.\"");
        scan.nextLine();
        System.out.println(chosen.getName() + " (steps closer):");
        System.out.println("\"You've turned this kingdom into a graveyard. For what? Order?\"");
        scan.nextLine();

        System.out.println("The Archivist:");
        System.out.println(
                "\"For continuity. Mortals are unreliable historians. They forget. They dream. They forgive. I ensure the truth endures... even if it must bleed to stay real.\"");
        scan.nextLine();
        System.out.println(chosen.getName() + ":");
        System.out.println("\"You're not preserving truth. You're feeding off it.\"");
        scan.nextLine();
        System.out.println("The Archivist (chuckles softly):");
        System.out.println("\"Is there a difference?\"");
        scan.nextLine();

        System.out.println("(a low hum fills the air - the floating scrolls begin circling faster)");
        scan.nextLine();
        System.out.println("The Archivist:");
        System.out.println(
                "\"The child was the perfect instrument. A ruler too young to understand is one who never questions. Through his tantrums, I rebuilt the nation's ruin.\"");
        scan.nextLine();
        System.out.println(chosen.getName() + ":");
        System.out.println("\"You rebuilt nothing. You just kept the wound open.\"");
        scan.nextLine();

        System.out.println("The Archivist (quietly):");
        System.out.println("\"Then allow me to write your ending, too.\"");
        scan.nextLine();
        System.out.println("(the quill in his hand lifts into the air, dripping black ink that burns the floor.)");
        scan.nextLine();

        System.out.println();
        System.out.print("\033[H\033[2J"); // clear screen
        System.out.flush();
    }

    // ==========================================================
    // STORY SKIP SYSTEM
    // ==========================================================
    public boolean storyLine(String line) {
        System.out.println(line);
        return checkSkip();
    }

    public boolean checkSkip() {
        System.out.println("Press ['s'] to skip the dialogues, and [Enter] to continue.");
        String input = scan.nextLine();
        return input.equalsIgnoreCase("s");
    }

    public void storyJump() {
        System.out.println("\nSkipping all remaining story dialogue...\n");
    }

    // ==========================================================
    // VALIDATION (LETTER INPUT)
    // ==========================================================
    public String getLetterInput() {
        while (true) {
            String input = scan.nextLine().trim();

            if (input.equalsIgnoreCase("A") ||
                    input.equalsIgnoreCase("B") ||
                    input.equalsIgnoreCase("C") ||
                    input.equalsIgnoreCase("D")) {
                return input;
            } else {
                System.out.print("Invalid. Enter A, B, C, or D: ");
            }
        }
    }

    // ----------------------------------------------------------
    // PHASE 1
    // ----------------------------------------------------------
    public int phase1(int strikes) {

        System.out.println("PHASE 1 - Ashes Beneath the Throne");
        System.out.println("Strikes: " + strikes + "/3");
        scan.nextLine();

        System.out.println("Puzzle: Ledger of Lies");
        scan.nextLine();

        System.out.print("1. Royal Decree: Grain tax increased by 5%. Signed by the King");
        scan.nextLine();
        System.out.print("2. Public Work Order: Repairs for slums approved. Signature missing");
        scan.nextLine();
        System.out.print("3. Royal Execution Warrant: 'Dissenters' captured and executed");
        scan.nextLine();
        System.out.print("4. Harvest Report: Farmland yielded zero crops. Signed by 'Archivist Delegate'");
        scan.nextLine();

        System.out.println();
        System.out.println("Identify which 2 entries are falsified.");
        scan.nextLine();

        System.out.println("Options:");
        System.out.print("A. 1 and 2");
        scan.nextLine();
        System.out.print("B. 2 and 4");
        scan.nextLine();
        System.out.print("C. 1 and 3"); // correct answer cuz the King is baby and he cannot sign decree 1 and execution
                                        // order 3
        scan.nextLine();
        System.out.print("D. 3 and 4");
        System.out.println();
        System.out.println();

        String ans;

        while (true) {
            System.out.print("Your answer: ");
            ans = getLetterInput();
            System.out.println();
            System.out.println();
            if (!ans.equalsIgnoreCase("C")) {
                strikes--;
                System.out.println("Wrong! Strikes left: " + strikes + "/3");
                System.out.println();
                if (strikes == 0) {
                    break; // exit because no strikes left
                }

                continue; // ask again
            }

            // correct answer
            System.out.println("Correct!");
            System.out.println();
            break;
        }

        return strikes;
    }

    // ----------------------------------------------------------
    // PHASE 2
    // ----------------------------------------------------------
    public int phase2(int strikes) {
        System.out.println("Servant Girl: ");
        System.out.println("\"You see it now... He rewrote everything\"");
        scan.nextLine();

        System.out.print("\033[H\033[2J"); // Move cursor to top-left and clear screen
        System.out.flush();

        System.out.println("(After you uncover the puzzle you now start your descend into the Undercity...)");
        scan.nextLine();

        System.out.println("PHASE 2 - Descent to the Undercity");
        System.out.println("Strikes: " + strikes + "/3");
        scan.nextLine();

        System.out.println("Puzzle: The Broken Waterway Maze");
        scan.nextLine();

        System.out.print("(The passage to the Undercity opens)");
        scan.nextLine();
        System.out
                .print("(An abandoned tunnels full of broken pipes and murals. You must navigate a maze-like layout.");
        scan.nextLine();
        System.out.print("(The servants' voices echos)");
        scan.nextLine();
        System.out.print(
                "<hint>\"The King did not choose this.. The people starve while the scribes feast... The Archivist's mark is everywhere..\"\n");
        scan.nextLine();

        System.out.println("Options:");
        System.out.print("A. Tunnel A - Crown symbol"); // a dead end
        scan.nextLine();
        System.out.print("B. Tunnel B - Empty bowl symbol"); // you will collapse if you enter here because of the
                                                             // poisonous dust
        scan.nextLine();
        System.out.print("C. Tunnel C - Quill symbol"); // correct answer cuz this tunnel will lead you to the Archivist
                                                        // lair
        scan.nextLine();

        String ans;

        while (true) {
            System.out.println();
            System.out.print("Your answer: ");
            ans = getLetterInput();
            System.out.println();
            if (!ans.equalsIgnoreCase("C")) {
                strikes--;
                System.out.println("Wrong! Strikes left: " + strikes + "/3");
                System.out.println();
                if (strikes == 0) {
                    break; // exit because no strikes left
                }

                continue; // ask again
            }

            // correct answer
            System.out.println("Correct!");
            System.out.println();
            break;
        }

        return strikes;
    }

    // ----------------------------------------------------------
    // PHASE 3
    // ----------------------------------------------------------
    public int phase3(int strikes) {
        System.out.println("You escaped the maze and proceeds to The Heart of the Veins");
        scan.nextLine();

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("PHASE 3 - The Heart of the Veins");
        System.out.println("Strikes: " + strikes + "/3");
        scan.nextLine();

        System.out.println("Puzzle: Power Conduit Shutdown Order");
        scan.nextLine();

        System.out.println("The Heart of the Veins must be disabled by solving its power circuit.");
        System.out.print("Three energy conduits flow upward into the Capital:");
        scan.nextLine();
        System.out.print("Conduit Red - \"For the Nobles\"");
        scan.nextLine();
        System.out.print("Conduit Blue - \"For the Military\"");
        scan.nextLine();
        System.out.print("Conduit Yellow - \"For the People\"");
        scan.nextLine();
        System.out.print("<hint> The nobles never starve, The soldiers obey the false law, The people suffer last");
        scan.nextLine();

        System.out.println();
        System.out.println("Options:");
        System.out.print("A. Yellow -> Red -> Blue");
        scan.nextLine();
        System.out.print("B. Red -> Blue -> Yellow"); // correct answer cuz you cut off the nobles, then the military,
                                                      // then free the people's conduit
        scan.nextLine();
        System.out.print("C. Blue -> Yellow -> Red");
        scan.nextLine();
        System.out.print("D. Red -> Yellow -> Blue");
        scan.nextLine();
        System.out.println();

        String ans;

        while (true) {
            System.out.print("Your answer: ");
            ans = getLetterInput();
            System.out.println();
            if (!ans.equalsIgnoreCase("B")) {
                strikes--;
                System.out.println("Wrong! Strikes left: " + strikes + "/3");
                System.out.println();
                if (strikes == 0) {
                    break; // exit because no strikes left
                }

                continue; // ask again
            }

            // correct answer
            System.out.println("Correct!");
            System.out.println();
            break;
        }

        return strikes;
    }

    // ----------------------------------------------------------
    // PHASE 4 (NO FAIL)
    // ----------------------------------------------------------
    public void phase4() {

        System.out.println("You got out of the Vein's Heart, and saw the citizens gathering");
        scan.nextLine();

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("PHASE 4 - The People Rise");
        scan.nextLine();

        System.out.print("(The people gather in confusion)");
        scan.nextLine();
        System.out.print("(The golden lights of false property vanish)");
        scan.nextLine();
        System.out.print("(A council of citizens approaches)");
        scan.nextLine();
        System.out.println("What would you tell to the people?");
        scan.nextLine();

        System.out.println("Options:");
        System.out.print("A. \"You were lied to. But now you're free.\"");
        scan.nextLine();
        System.out.print("B. \"The King lives-but the throne must change.\"");
        scan.nextLine();
        System.out.print("C. \"Rise. Today your voices return.\"");
        scan.nextLine();
        // all answers are correct
        System.out.println();
        System.out.print("Your Words: ");
        getLetterInput();

        System.out.println();
        System.out.println("And the crowd listens.");
        scan.nextLine();
    }

    // ----------------------------------------------------------
    // FAILURE REWARD (NO LEVEL UP)
    // ----------------------------------------------------------
    public void failReward(Character chosen) {
        System.out.println("Reward: None.");
        scan.nextLine();
        System.out.println("Current Level: " + chosen.getLevel());
        System.out.println("Current Potions: " + chosen.getPotionCount());
        scan.nextLine();
        System.out.println("You may now proceed on your journey.");
        scan.nextLine();
    }

    public void successReward(Character chosen) {
        chosen.setPotionCount(chosen.getPotionCount() + 1);
        System.out.println("Reward: 1 health potions added to your inventory.");
        scan.nextLine();
        System.out.println("Current Level: " + chosen.getLevel());
        System.out.println("Current Potions: " + chosen.getPotionCount());
        scan.nextLine();
        System.out.println("You may now proceed on your journey.");
        scan.nextLine();
    }

    public void finishQuest(Character chosen) {
        System.out.println();
        System.out.println("You have Finished the quest: The Veins of Humanas!");

        scan.nextLine();
        System.out.println("Quest Summary:");
        System.out.println("You Destroyed the corrupt power network beneath the city.");
        scan.nextLine();

        chosen.setPotionCount(chosen.getPotionCount() + 5);
        System.out.println("Reward: 5 health potions added to your inventory.");
        scan.nextLine();
        System.out.println("Current Level: " + chosen.getLevel());
        System.out.println("Current Potions: " + chosen.getPotionCount());
        scan.nextLine();
        System.out.println("You may now proceed on your journey.");
        scan.nextLine();

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
