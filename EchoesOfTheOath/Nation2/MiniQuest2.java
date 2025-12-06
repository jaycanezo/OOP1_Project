package EchoesOfTheOath.Nation2;

import EchoesOfTheOath.Characters.Character;
import java.util.*;

public class MiniQuest2 {
    public void whispersBeneathTheBoughs(Character chosen) { // Nation 2 Mini Quest
        Scanner scanner = new Scanner(System.in);
        int attemptsUsed = 0;
        int maxAttempts = 3;
        boolean questCompleted = false;

        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_RED = "\u001B[31m";

        String name = chosen.getName();
        String userAnswer;

        // Helper method calls (using the actual helper from the previous solution)
        clearScreen();
        System.out.println("---------------------------------------------------------------");
        System.out.println("████    ██   ██████   ██████   ██   ██████   ████    ██    ████");
        System.out.println("██ ██   ██   ██  ██     ██     ██   ██  ██   ██ ██   ██   ██  ██");
        System.out.println("██  ██  ██   ██████     ██     ██   ██  ██   ██  ██  ██      ██");
        System.out.println("██   ██ ██   ██  ██     ██     ██   ██  ██   ██   ██ ██     ██");
        System.out.println("██    ████   ██  ██     ██     ██   ██████   ██    ████     █████");
        System.out.println("----------------------Veyora: Nation of Elves--------------------");
        System.out.println("");
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

        clearScreen();
        System.out.println(
                "With the fall of The Archivist, new paths unfold before you.\n The lands of Veyora call out -- ancient forests where the elves once thrived, now fractured by war and shadowed by forgotten oaths.");
        scanner.nextLine();

        clearScreen();
        System.out.println("Continuing the journey you now have arrived at Veyora: \"The Elven Lands\"");
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

        System.out.println("You encountered a Riddle Game: The Whsipering Riddle!");
        System.out.print("This is an Optional Riddle Game. Press [S] to skip or [Enter] to continue.");
        String skipInput = scanner.nextLine();

        while (true) {
            if (skipInput.equalsIgnoreCase("S")) {
                System.out.println("");
                System.out.println("Are you sure you would like to skip the Challenge?");
                System.out.println("If yes, you will not level up [Y/N]: ");

                String inputSureSkip = scanner.nextLine();
                while (!inputSureSkip.equalsIgnoreCase("Y") && !inputSureSkip.equalsIgnoreCase("N")) {
                    System.out.println("Invalid input. Please enter Y or N:");
                    inputSureSkip = scanner.nextLine();
                }

                if (inputSureSkip.equalsIgnoreCase("Y")) {
                    System.out.println("\nYou have skipped the Challenge, you did not level up");
                    scanner.nextLine();
                    System.out.println("You may now proceed on your journey.");
                    scanner.nextLine();
                    break;
                    // Quest ends here if skipped, execution continues below the while loop
                }else if (inputSureSkip.equalsIgnoreCase("N")){
                    System.out.println("Challenge will not be skipped.");
                    scanner.nextLine();
                    break;
                }
            } else {
                // --- Riddle Loop Logic ---
                while (attemptsUsed < maxAttempts && !questCompleted) {
                    System.out.println("\n--------------------------------");
                    System.out.println("RIDDLE: " + riddle);
                    scanner.nextLine();
                    System.out.println(hint);
                    scanner.nextLine();
                    System.out.print(
                            "Attempts remaining: " + (maxAttempts - attemptsUsed) + " | Enter your answer: ");
                    userAnswer = scanner.nextLine().trim();

                    if(userAnswer.isEmpty()){
                        System.out.println("Input Cannot be Empty. Please Enter a valid String");
                        continue;
                    }

                    if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                        // --- SUCCESS ---
                        questCompleted = true;
                        System.out.println("\n" + ANSI_GREEN
                                + "Correct! The stone slab dissolves into motes of light." + ANSI_RESET);
                        scanner.nextLine();
                        chosen.setLevel(chosen.getLevel() + 1);
                        chosen.setPotionCount(chosen.getPotionCount() + 3);

                        System.out.println("Reward: 3 health potions added to your inventory.");
                        scanner.nextLine();
                        System.out.println(name + " leveled up to level " + chosen.getLevel() + "!");
                        scanner.nextLine();
                        System.out.println("Current Potions: " + chosen.getPotionCount());
                        break;
                    } else {
                        // --- FAILURE attempt ---
                        attemptsUsed++;
                        System.out.println(ANSI_RED + "Wrong answer." + ANSI_RESET);

                        if (attemptsUsed >= maxAttempts) {
                            // --- FINAL FAILURE ---
                            System.out.println(
                                    "\n" + ANSI_RED + "MINI QUEST FAILED (Max attempts reached)" + ANSI_RESET);
                            chosen.setPotionCount(chosen.getPotionCount() + 1); // Minor consolation reward
                            System.out.println("Reward: 1 health potion added to your inventory.");
                            System.out.println(name + " remains level " + chosen.getLevel() + ".");
                            System.out.println("Current Potions: " + chosen.getPotionCount());
                            break;
                            // Quest ends here
                        }
                    }
                    scanner.nextLine(); // Pause for user to read status
                }
                break;
            }
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

        System.out.println("(Your surroundings fade to black — faint heartbeat sound.)");
        scanner.nextLine();

        clearScreen();
        System.out.println("You have finished the Mini Quest: Whispers Beneath the Boughs!");
        scanner.nextLine();

        // Final Quest Summary
        System.out.println("Quest Summary: ");
        System.out
                .println("You arrived in Veyora and found a divided elven land. After overcoming the ancient riddle,");
        System.out.println("you were led to a Healer who is infected by the 'Root of Despair' and has asked you");
        System.out.println("to retrieve the 'Tear of Veyora' from the Silverfang Huntress, Ilaryx.");
        scanner.nextLine();

        System.out.println("Current Level: " + chosen.getLevel());
        System.out.println("Current Potions: " + chosen.getPotionCount());
        scanner.nextLine();
        System.out.println("You may now proceed on your journey.");
        scanner.nextLine();

        // The start of the Ilaryx encounter (transition to Main Quest)
        clearScreen();
        System.out.println("(" + name + " steps into a vast glade. Moonlight cuts through the mist.)");
        scanner.nextLine();
        System.out.println("Ilaryx:\n\"If your resolve is stronger than your lies... prove it.\"");
        scanner.nextLine();

        clearScreen();
    }

    // You need this helper method defined in your Game class
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
