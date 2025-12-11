package EchoesOfTheOath.Nation2;

import EchoesOfTheOath.Characters.Character;
import java.util.*;

public class MiniQuest2 {
    public void whispersBeneathTheBoughs(Character chosen) { // Nation 2 Mini Quest
        Scanner scanner = new Scanner(System.in);
        int attemptsUsed = 0;
        int maxAttempts = 3;
        boolean questCompleted = false;
        
        String YELLOW = "\033[33m";

        String ANSI_RESET = "\u001B[0m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_RED = "\u001B[31m";

        String name = chosen.getName();
        String userAnswer;

        // Helper method calls (using the actual helper from the previous solution)
        clearScreen();
        
        // --- Narrative Introduction ---
        System.out.println("(silence. The library crumbles slowly, scrolls burning into motes of light.)");
        scanner.nextLine();
        System.out.println("The Archivist (fading, voice almost human):");
        System.out.println("\"Foolish... thing... You think freedom brings peace?\"");
        scanner.nextLine();
        System.out.println("\"The lies will fade.\"");
        scanner.nextLine();
        System.out.println("\"But the echoes... will remain.\""); // This line hints at the riddle answer
        scanner.nextLine();
        System.out.println("With the fall of The Archivist, new paths unfold before you.\nThe lands of Veyora call out -- ancient forests where the elves once thrived, now fractured by war and shadowed by forgotten oaths.");
        scanner.nextLine();
        System.out.println();

        clearScreen();
        System.out.println(YELLOW+"---------Continuing the Journey You have now Arrived at:---------"+ANSI_RESET);
        System.out.println(ANSI_GREEN+"████    ██   ██████   ██████   ██   ██████   ████    ██    ████");
        System.out.println("██ ██   ██   ██  ██     ██     ██   ██  ██   ██ ██   ██   ██  ██");
        System.out.println("██  ██  ██   ██████     ██     ██   ██  ██   ██  ██  ██      ██");
        System.out.println("██   ██ ██   ██  ██     ██     ██   ██  ██   ██   ██ ██     ██");
        System.out.println("██    ████   ██  ██     ██     ██   ██████   ██    ████     █████"+ANSI_RESET);
        System.out.println(YELLOW+"--------------------Veyora: Nation of Elves----------------------"+ANSI_RESET);
        scanner.nextLine();

        // --- NEW PLOT POINT INTRODUCING THE RIDDLE ---
        System.out.println("(faint sound of wind rustling leaves; the whisper of distant voices)");
        scanner.nextLine();
        System.out.println("(" + name + " stands before an ancient, overgrown forest. Pale blue motes drift.)");
        scanner.nextLine();
        System.out.println("Internal Monologue:\n\"The air here feels... alive, but weighted with sorrow.\"");
        scanner.nextLine();
        System.out.println("As you step into the forest, your path is blocked by an **ancient stone slab**, carved with Elven symbols and a single, glowing inscription.");
        scanner.nextLine();

        // --- Riddle Mini-Game Setup ---
        clearScreen();
        String riddle = "I speak without a mouth and hear without ears. I have no body, but I come alive with wind. What am I?";
        String hint = "[Hint: You often hear me in a canyon]";
        String correctAnswer = "ECHO";

        System.out.println("You encountered a Riddle Game: The Whispering Riddle!");
        System.out.print("This is an Optional Riddle Game. Press [S] to skip or [Enter] to continue: ");
        String skipInput = scanner.nextLine();

        while (true) {

            // --- SKIP OPTION ---
            if (skipInput.equalsIgnoreCase("S")) {

                System.out.println("\nAre you sure you would like to skip the Challenge?");
                System.out.print(YELLOW+"If yes, you will not level up [Y/N]: "+ANSI_RESET);
                String inputSureSkip = scanner.nextLine();
                System.out.println();

                while (!inputSureSkip.equalsIgnoreCase("Y") && !inputSureSkip.equalsIgnoreCase("N")) {
                    System.out.print(ANSI_RED+"Invalid input. Please enter Y or N:"+ANSI_RESET);
                    inputSureSkip = scanner.nextLine();
                    System.out.println();
                }

                if (inputSureSkip.equalsIgnoreCase("Y")) {
                    System.out.println("\nYou have skipped the Challenge, you did not level up");
                    scanner.nextLine();
                    System.out.println(ANSI_GREEN+"You may now proceed on your journey."+ANSI_RESET);
                    scanner.nextLine();
                    break;   // Exit whole skip/riddle system
                } else {
                    // Player changed their mind → continue to riddle
                    System.out.println("\nChallenge will not be skipped.");
                    skipInput = "";   // Important: reset so the riddle path starts
                }
            }

            // --- RIDDLE GAME STARTS HERE ---
            while (attemptsUsed < maxAttempts && !questCompleted) {

                System.out.println("\n--------------------------------");
                System.out.println("RIDDLE: " + riddle);
                System.out.println(hint);
                System.out.println();
                System.out.print("Attempts remaining: " + (maxAttempts - attemptsUsed) + 
                                " | Enter your answer: ");
                userAnswer = scanner.nextLine().trim();

                if (userAnswer.isEmpty()) {
                    System.out.println(ANSI_RED+"Input cannot be empty. Try again."+ANSI_RESET);
                    continue;
                }

                if (userAnswer.equalsIgnoreCase(correctAnswer)) {

                    // SUCCESS
                    questCompleted = true;
                    System.out.println("\n" + ANSI_GREEN +
                            "Correct! The stone slab dissolves into motes of light." +
                            ANSI_RESET);
                    scanner.nextLine();

                    chosen.setLevel(chosen.getLevel() + 1);
                    chosen.setPotionCount(chosen.getPotionCount() + 3);

                    System.out.println(chosen.getName() + " leveled up to level " + chosen.getLevel() + "!\n");

                    System.out.println("Reward: 3 health potions added to your inventory.\n");

                    System.out.println("Current Level: " + chosen.getLevel());
                    System.out.println("Current Potions: " + chosen.getPotionCount() + "\n");

                    System.out.println("You may now proceed on your journey.");
                    scanner.nextLine();
                    break;
                } else {

                    // WRONG ANSWER
                    attemptsUsed++;
                    System.out.println(ANSI_RED + "Wrong answer." + ANSI_RESET);

                    // FAILURE END
                    if (attemptsUsed >= maxAttempts) {
                        System.out.println("\n" + ANSI_RED +
                                "RIDDLE FAILED (Max attempts reached)" +
                                ANSI_RESET);
                        scanner.nextLine();
                        chosen.setPotionCount(chosen.getPotionCount() + 1);
                        System.out.println("Reward: 1 health potion added.");
                        scanner.nextLine();
                        System.out.println(name + " remains level " + chosen.getLevel() + ".");
                        System.out.println("Current Potions: " + chosen.getPotionCount());
                        scanner.nextLine();
                        break;
                    }
                }
            }

            break;
        }


        // --- Narrative Conclusion and Transition (Setting up the Main Quest) ---
        clearScreen();
        System.out.println("With the inscription answered (or bypassed), the ancient forest path opens up to you.");
        scanner.nextLine();
        System.out.println(name + " finds a ruined elven outpost.");
        scanner.nextLine();

        // Dialogue sequence
        System.out.println("Elven Rebel:\n\"Outsider! You smell of Humanas -- of the ink-born filth!\"");
        scanner.nextLine();
        System.out.println(name + ":\n\"I'm looking for Lunareth.\"");
        scanner.nextLine();
        System.out.println("(The Rebel leaves; a Healer, cloaked in green-grey, steps out from the outpost.)");
        scanner.nextLine();
        System.out.println(
                "Healer (bitter chuckle):\n\"Understanding won't heal what's already rotted. Lunareth's law is poison.\"");
        scanner.nextLine();
        System.out.println(name + ":\n\"Then let me help.\"");
        scanner.nextLine();
        System.out.println(
                "Healer (eyes narrowing):\n\"Help? Then bring me a Tear of Veyora -- the last pure sap from the fores's heart. You must get it from the Silverfang Huntress.\"");
        scanner.nextLine();
        System.out.println(
                "Healer (quietly):\n\"She was once the pride of Lunareth's guard. If you face her... speak before you strike.\"");
        scanner.nextLine();

        System.out.println("(Your surroundings fade to black -- faint heartbeat sound.)");
        scanner.nextLine();

        clearScreen();

        // The start of the Ilaryx encounter (transition to Main Quest)
        clearScreen();
        System.out.println("(" + name + " steps into a vast glade. Moonlight cuts through the mist.)");
        scanner.nextLine();
        System.out.println("Ilaryx:\n\"If your resolve is stronger than your lies... prove it.\"");
        scanner.nextLine();

        clearScreen();
    }

    // You need this helper method defined in your Game class
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
