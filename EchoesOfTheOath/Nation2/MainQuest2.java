package EchoesOfTheOath.Nation2;

import EchoesOfTheOath.Characters.Character;
import java.util.*;

public class MainQuest2 {
    private Scanner scan = new Scanner(System.in);
    private boolean isSkipped = false;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    // ==============================
    // MASTER METHOD
    // ==============================
    public void theRootsOfDespair(Character chosen) {
        clearScreen();

        while (true) {
            rootsOfDespair(chosen);
            if (isSkipped) {
                System.out.println("You have skipped the Quest Dialogues, Proceeding forward...");
                scan.nextLine();
            }

            riddle(chosen);

            sceneCitadel(chosen);
            endQuest(chosen);
            break;
        }

        
    }

    // ==============================
    // UNIVERSAL SKIP / WAIT METHOD
    // ==============================
    private boolean waitOrSkip() {
        String input = scan.nextLine();
        if (input.equalsIgnoreCase("s")) {
            isSkipped = true;
            return true;
        }
        return false;
    }

    // ==============================
    // SCENES (NO STORY ARRAYS)
    // ==============================

    private void rootsOfDespair(Character chosen) {
        String name = chosen.getName();

        System.out.println("(Ilaryx kneels, eyes clear but full of guilt. " + name + " approaches.)");
        scan.nextLine();
        System.out.println("Ilaryx (hoarse):");
        System.out.println("\"The Tear... take it.\"");
        System.out.println("(Ilaryx opens her hand, revealing a glowing crystal drop of pure sap.)");
        scan.nextLine();

        System.out.println(name + ":");
        System.out.println("\"You could've killed me.\"");
        scan.nextLine();
        System.out.println("Ilaryx (faint smile):");
        System.out.println("\"I tried. But you fought like someone who remembers why they fight.\"");
        scan.nextLine();

        System.out.println(name + ":");
        System.out.println(
                "\"The forest can heal, Ilaryx. But not if you keep obeying a voice that's forgotten its promise.\"");
        scan.nextLine();
        System.out.println("Ilaryx:");
        System.out.println("\"You speak of promises as if they still mean anything.\"");
        scan.nextLine();

        System.out.println(name + ":");
        System.out.println("\"They do. I once made one too.\"");
        scan.nextLine();
        System.out.println("Ilaryx (recognition flickers):");
        System.out.println("\"...You. The one from the Oath...\"");
        scan.nextLine();
        System.out.println("(Ilaryx collapses before finishing. The Tear glows brighter in " + name + "'s hand.)");
        scan.nextLine();
        System.out.println("(Internal monologue):");
        System.out.println("\"Her arrows fell silent. But her doubt... lingered...\"");
        scan.nextLine();
        System.out.println(name + " (softly):");
        System.out.println("\"Lunareth... I'm coming.\"");
        scan.nextLine();

        System.out.println("Main Quest Started: \"The Roots of Despair\"");
        System.out.println("Objective: Reach the Heart of Veyora and confront the Root of Despair");
        System.out.println("(Press ['s'] to skip and [Enter] to continue.)");
        if (waitOrSkip())
            return;

        System.out.println("(" + name + " walks with the glowing Tear of Veyora.)");
        if (waitOrSkip())
            return;

        System.out.println(
                "The Huntress's arrows no longer pierce the air. But the wound still bleeds beneath the earth.");
        if (waitOrSkip())
            return;

        System.out.println("(" + name + " enters the Healer's Grove. The Tear is placed into the roots.)");
        if (waitOrSkip())
            return;

        System.out.println("Healer (weak but smiling):");
        System.out.println("\"You found it... I can feel the forest breathe again.\"");
        if (waitOrSkip())
            return;

        System.out.println(name + ":");
        System.out.println("\"Will this heal it?\"");
        if (waitOrSkip())
            return;

        System.out.println("Healer (shakes head):");
        System.out.println("\"No. It will only remind it how to heal.\"");
        if (waitOrSkip())
            return;

        System.out.println(name + ":");
        System.out.println("\"Then what's poisoning it?\"");
        if (waitOrSkip())
            return;

        System.out.println("Healer (looks to the east):");
        System.out.println("\"The Root of Despair. Once, it carried Lunareth's voice... Now it speaks only madness.\"");
        if (waitOrSkip())
            return;

        System.out.println(name + ":");
        System.out.println("\"Then that's where I'm going.\"");
        if (waitOrSkip())
            return;

        System.out.println("Healer:");
        System.out.println("\"Be warned -- the closer you get, the louder the Oath will whisper.\"");
        System.out.println("\"It remembers you... even if he does not.\"");
        if (waitOrSkip())
            return;

        System.out.println("(" + name + " moves through corrupted terrain. Vines pulse like veins.)");
        if (waitOrSkip())
            return;

        System.out.println("(Internal monologue):");
        System.out.println("\"The deeper I walk, the more the forest changes. It's not dying… it's dreaming.\"");
        if (waitOrSkip())
            return;

        System.out.println("(Whispers fill the air.)");
        if (waitOrSkip())
            return;

        System.out.println("Lunareth (distant, echoing):");
        System.out.println("\"Unity through will. Order through strength.\"");
        if (waitOrSkip())
            return;

        System.out.println("Sarukdal (faint):");
        System.out.println("\"He's lost to it... we all are...\"");
        if (waitOrSkip())
            return;

        System.out.println(name + " (gritting teeth):");
        System.out.println("\"No. You're still in there.\"");
        if (waitOrSkip())
            return;

        System.out.println("(The forest floor cracks open. " + name + " descends into the darkness.)");
        if (waitOrSkip())
            return;

        System.out.println(
                "(The Root of Despair looms like a colossal heart. A projection of Lunareth forms from the vines.)");
        if (waitOrSkip())
            return;

        System.out.println("Lunareth (illusory):");
        System.out.println("\"So... the Oathbreaker returns.\"");
        if (waitOrSkip())
            return;

        System.out.println(name + " (shocked):");
        System.out.println("\"Lunareth...?\"");
        if (waitOrSkip())
            return;

        System.out.println("Lunareth:");
        System.out.println(
                "\"Do you still claim that name? 'Hero'? You who fell -- you who let Elarion's seal shatter?\"");
        if (waitOrSkip())
            return;

        System.out.println(name + ":");
        System.out.println("\"That wasn't how it happened--\"");
        if (waitOrSkip())
            return;

        System.out.println("Lunareth (cutting " + name + " off):");
        System.out.println("\"Silence!\"");
        if (waitOrSkip())
            return;

        System.out.println("(Vines surge outward. The illusion's eyes fill with pain.)");
        if (waitOrSkip())
            return;

        System.out.println("Lunareth:");
        System.out.println("\"You left me to watch the world tear itself apart. So I rebuilt it!\"");
        if (waitOrSkip())
            return;

        System.out.println(name + ":");
        System.out.println("\"By binding them in fear?\"");
        if (waitOrSkip())
            return;

        System.out.println("Lunareth:");
        System.out.println("\"By saving them from their own chaos!\"");
        if (waitOrSkip())
            return;

        System.out.println("(The Root shudders. Corrupted spirits erupt!)");
        if (waitOrSkip())
            return;

        System.out.println("(Spirit Echoes of Elven Warriors attacks!)");
        if (waitOrSkip())
            return;

        System.out.println(
                "The spectral elves rush forward in a synchronized assault, their forms flickering like fractured memories. "
                        + name
                        + " weaves between their ghostly blades, each strike passing through the air with a chilling hum. With focused resolve, "
                        + name + " shatters their corrupted essence, freeing the trapped echoes one by one.");
        if (waitOrSkip())
            return;

        System.out.println("(Internal monologue during battle:)");
        System.out.println("\"These aren't enemies. They're memories -- all trapped in the same lie.\"");
        if (waitOrSkip())
            return;

        System.out.println("(After the final wave, the Root pulses faintly.)");
        if (waitOrSkip())
            return;

        System.out.println(name + " (panting):");
        System.out.println("\"Lunareth... this isn't you.\"");
        if (waitOrSkip())
            return;

        System.out.println("Lunareth (voice distant, fading):");
        System.out.println("\"You don't understand. I saw what happens when oaths break...\"");
        if (waitOrSkip())
            return;

        System.out.println(name + ":");
        System.out.println("\"You already did. When you let it own you.\"");
        if (waitOrSkip())
            return;

        System.out.println("(" + name + " places a hand on the Root's core. Light bursts--a memory.)");
        if (waitOrSkip())
            return;
    }

    private void sceneCitadel(Character chosen) {
        String name = chosen.getName();

        System.out.println("(Flashback: " + name + ", Lunareth, Sarukdal, and the Archivist stand beneath the stars.)");
        if (waitOrSkip())
            return;

        System.out.println("Young Lunareth (smiling):");
        System.out.println("\"We vow to protect balance. Not through fear... but through unity.\"");
        if (waitOrSkip())
            return;

        System.out.println(name + " (from memory):");
        System.out.println("\"Together, then. Until the stars forget to shine.\"");
        if (waitOrSkip())
            return;

        System.out.println("(The memory fractures. The vision ends. " + name + " collapses.)");
        if (waitOrSkip())
            return;

        System.out.println(name + " (whispers):");
        System.out.println("\"I remember now...\"");
        if (waitOrSkip())
            return;

        System.out.println("(The Root's light stabilizes. The forest glows softly. A deep rumble echoes from above.)");
        if (waitOrSkip())
            return;

        System.out.println("(Internal monologue):");
        System.out.println("\"The forest breathes again... but its heart still aches.\"");
        if (waitOrSkip())
            return;

        System.out.println("(In the distance, a massive citadel carved into a tree trunk looms.)");
        if (waitOrSkip())
            return;

        System.out.println(name + " (narrowing eyes):");
        System.out.println("\"The Warden waits.\"");
        if (waitOrSkip())
            return;

        System.out.println("Lunareth (disembodied voice):");
        System.out.println("\"If you seek truth, then come. Let us see which of us still remembers the Oath.\"");
        if (waitOrSkip())
            return;

        System.out.println("(" + name + " approaches Lunareth's citadel -- an enormous fortress of living wood.)");
        if (waitOrSkip())
            return;

        System.out.println("(Internal monologue):");
        System.out.println("\"The forest no longer whispers. It watches.\"");
        if (waitOrSkip())
            return;

        System.out.println(
                "(The gates open silently. " + name + " steps inside. Lunareth descends from a platform of vines.)");
        if (waitOrSkip())
            return;

        System.out.println("Lunareth:");
        System.out.println("\"You came.\"");
        System.out.println("\"Do you remember, Oathbearer, what you swore beneath the Celestial Gate?\"");
        if (waitOrSkip())
            return;

        System.out.println(name + ":");
        System.out.println("\"That we'd protect the balance -- together.\"");
        if (waitOrSkip())
            return;

        System.out.println("Lunareth (bitter smile):");
        System.out.println("\"And you broke it.\"");
        System.out.println("\"So now... I'll break you.\"");
        if (waitOrSkip())
            return;

        System.out.println("(He raises his bow -- moonlight gathers.)");
        if (waitOrSkip())
            return;
    }

    // ==============================
    // END QUEST + REWARDS
    // ==============================
    private void endQuest(Character chosen) {
        System.out.println("You have finished the Main Quest: 'The Roots of Despair'");
        scan.nextLine();
        System.out.println("Quest Summary: You returned the Tear, confronted illusions, regain your memories, and reached Lunareth.");
        scan.nextLine();
        System.out.println("Current Level: " + chosen.getLevel());
        System.out.println("Current Potions: " + chosen.getPotionCount());
        scan.nextLine();
        System.out.println("You may now proceed on your journey.");
        clearScreen();
    }

    // ==============================
    private void clearScreen() {
        System.out.print("\033[H\033[2J"); // clear screen
        System.out.flush();
    }

    public void riddle(Character chosen) {
        // Use the same scanner to avoid input skipping bugs
        // Scanner scanner = new Scanner(System.in); <-- REMOVE

        String correctAnswer = "OATH";
        int maxAttempts = 3;
        int currentAttempts = 0;
        boolean isSolved = false;

        String shuffledWord = shuffleString(correctAnswer);
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("You Encountered a Scrabble Game!");
        System.out.println("Unscramble the word: "+ANSI_RED+ shuffledWord + ANSI_RESET +" to bring your memories back!");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------");

        while (currentAttempts < maxAttempts && !isSolved) {

            System.out.print("Attempt " + (currentAttempts + 1) + "/" + maxAttempts + " - Enter your answer: ");
            String userInput = scan.nextLine().trim().toUpperCase();

            if(userInput.isEmpty()){
                System.out.println(ANSI_RED+"Input Cannot be Empty."+ANSI_RESET+" Please Enter a valid String.");
                System.out.println();
                continue;
            }

            if (userInput.equals(correctAnswer)) {
                isSolved = true;

                System.out.print("Feedback: ");
                for (char c : userInput.toCharArray()) {
                    System.out.print(ANSI_GREEN + c + ANSI_RESET);
                }
                System.out.println();
                System.out.println();

            } else {
                currentAttempts++;
                printColoredFeedback(userInput, correctAnswer);
            }

            if (!isSolved && currentAttempts >= maxAttempts) {
                System.out.println("\n--- Scrabble Game Failed ---");
                scan.nextLine();
                System.out.println("You have run out of attempts.");
                scan.nextLine();
                System.out.println("The correct answer was: " + ANSI_GREEN +correctAnswer+ ANSI_RESET);
                scan.nextLine();
                System.out.println("You may now proceed on your journey.");
                scan.nextLine();
            } else if (!isSolved) {
                System.out.println(ANSI_RED+"\nIncorrect. Try again."+ANSI_RESET);
            }
        }

        if (isSolved) {
            System.out.println("Scrabble Game Complete!");
            chosen.setLevel(chosen.getLevel() + 1);
            scan.nextLine();
            System.out.println(chosen.getName() + " leveled up to level " + chosen.getLevel() + "!");
            scan.nextLine();
            chosen.setPotionCount(chosen.getPotionCount() + 5);
            System.out.println("Reward: 5 health potions added to your inventory.");
            scan.nextLine();
        }
    }

    private static void printColoredFeedback(String input, String answer) {
        System.out.print("Feedback: ");
        // Loop through the user's input
        for (int i = 0; i < input.length(); i++) {
            char inputChar = input.charAt(i);

            // Check boundaries first (in case input is longer than answer)
            if (i < answer.length()) {
                char answerChar = answer.charAt(i);

                if (inputChar == answerChar) {
                    // Character matches correct spot -> GREEN
                    System.out.print(ANSI_GREEN + inputChar + ANSI_RESET);
                } else {
                    // Character does not match spot -> RED
                    System.out.print(ANSI_RED + inputChar + ANSI_RESET);
                }
            } else {
                // User input is longer than the answer -> RED
                System.out.print(ANSI_RED + inputChar + ANSI_RESET);
            }
        }
        System.out.println(); // End line
    }

    /**
     * Helper method to shuffle the string for Step 1
     */
    private static String shuffleString(String input) {
        char[] chars = input.toCharArray();
        Random rand = new Random();

        // Fisher–Yates shuffle
        for (int i = chars.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }

        return new String(chars);
    }
}