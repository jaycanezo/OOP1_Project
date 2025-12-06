package EchoesOfTheOath.Nation3;

import java.util.*;

public class MiniQuest3 {
    Scanner scan = new Scanner(System.in);

    public void BrokenOathTrial() {
        boolean restart = true;

        while (restart) {
            int tries = 3;

            System.out.println("---------------------------------------------------------------");
            System.out.println("████    ██   ██████   ██████   ██   ██████   ████    ██    █████");
            System.out.println("██ ██   ██   ██  ██     ██     ██   ██  ██   ██ ██   ██   █    ██");
            System.out.println("██  ██  ██   ██████     ██     ██   ██  ██   ██  ██  ██      ███");
            System.out.println("██   ██ ██   ██  ██     ██     ██   ██  ██   ██   ██ ██   █    ██");
            System.out.println("██    ████   ██  ██     ██     ██   ██████   ██    ████    ████");
            System.out.println("-------------------------Nation of Demons------------------------");
            System.out.println("");

            System.out.println("\n================================================");
            System.out.println("           TRIAL OF THE BROKEN OATH");
            System.out.println("================================================\n");

            System.out.println("Darkness consumes the hall.");
            System.out.println("A blood-red sigil forms beneath you.");
            System.out.println("Sarukdal whispers: \"IF YOU REMEMBER NOTHING… THEN YOU DESERVE NOTHING.\"\n");

            pause();

            // ------------------------- ROUND 1 -------------------------
            if (!round(
                    "ROUND 1 — The First Fracture",
                    "WHAT HELD THE FOUR OF YOU TOGETHER?",
                    new String[] { "Power", "Fear of Elarion", "The Oath you swore together" },
                    'C', // Correct: C
                    tries)) {
                tries--;
            }
            if (tries == 0) {
                restart = gameOver();
                continue;
            }

            // ------------------------- ROUND 2 -------------------------
            if (!round(
                    "ROUND 2 — The Moment of Failure",
                    "WHY DID HE BREAK?",
                    new String[] { "Because you failed first", "Because he was too weak",
                            "Because the void chose him" },
                    'A', // Correct: A
                    tries)) {
                tries--;
            }
            if (tries == 0) {
                restart = gameOver();
                continue;
            }

            // ------------------------- ROUND 3 -------------------------
            if (!round(
                    "ROUND 3 — The Guardian's Burden",
                    "WHAT WAS SARUKDAL'S TRUE ROLE?",
                    new String[] { "To destroy the enemy", "To endure what the others could not",
                            "To replace the hero" },
                    'B', // Correct: B
                    tries)) {
                tries--;
            }
            if (tries == 0) {
                restart = gameOver();
                continue;
            }

            // ------------------------- FINAL ROUND -------------------------
            if (!round(
                    "FINAL ROUND — The Forbidden Question",
                    "WHAT BROKE THE OATH?",
                    new String[] { "Elarion's power", "The hero's lost memory", "The hero's choice to forget" },
                    'C', // Correct: C
                    tries)) {
                tries--;
            }
            if (tries == 0) {
                restart = gameOver();
                continue;
            }

            // ------------------------- SUCCESS -------------------------
            System.out.println("\nSUCCESS — You survived the Trial.");
            System.out.println("The shadows bow as the truth is accepted.");
            System.out.println("A bridge of black stone rises before you.\n");

            restart = false;
        }
    }

    // =========================================================
    // ROUND HANDLER — WITH LETTER INPUT + INTEGER BLOCKING
    // =========================================================
    private boolean round(String title, String question, String[] options, char correctAnswer, int triesLeft) {

        while (true) {
            System.out.println("\n----------------------------------------");
            System.out.println(title);
            System.out.println("Tries Left: " + triesLeft);
            System.out.println("----------------------------------------\n");

            System.out.println(question + "\n");

            System.out.println("A. " + options[0]);
            System.out.println("B. " + options[1]);
            System.out.println("C. " + options[2]);

            System.out.print("\nYour answer (A/B/C): ");

            String input = scan.nextLine();

            // If NOT a letter → invalid input, ask again
            if (input.length() != 1) {
                System.out.println("Invalid input. Please enter A, B, or C only.");
                continue;
            }

            char ans = input.charAt(0);

            // Accept only A, B, C letters
            if (ans != 'A' && ans != 'B' && ans != 'C') {
                System.out.println("Invalid choice. Please enter A, B, or C only.");
                continue;
            }

            // Valid letter input → check correctness
            if (ans == correctAnswer) {
                System.out.println("\nCorrect.");
                return true;
            } else {
                System.out.println("\nWrong.");
                System.out.println("The shadows whisper: \"You remember nothing.\"");
                return false;
            }
        }
    }

    // =========================================================
    // GAME OVER HANDLER
    // =========================================================
    private boolean gameOver() {
        System.out.println("\n=======================================");
        System.out.println("                GAME OVER");
        System.out.println("=======================================\n");

        System.out.println("The sigil explodes in red light.");
        System.out.println("The shadows engulf you completely.\n");

        System.out.println("Sarukdal roars: \"AND YOU DARE FACE ME WITHOUT MEMORY OR HONOR?\"\n");

        System.out.println("You have forgotten too much.");
        System.out.println("Restarting point: Entrance to the Hall of Broken Oaths\n");

        System.out.println("[1] Restart Trial");
        System.out.println("[2] Exit");
        System.out.print("Choose: ");

        int choice = scan.nextInt();
        return (choice == 1);
    }

    // =========================================================
    // PAUSE EFFECT
    // =========================================================
    private void pause() {
        try {
            Thread.sleep(600);
        } catch (Exception e) {
        }
    }

}
