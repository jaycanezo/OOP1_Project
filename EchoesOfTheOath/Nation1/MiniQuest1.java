package EchoesOfTheOath.Nation1;

import EchoesOfTheOath.Characters.Character;
import java.util.*;

public class MiniQuest1 {
    Scanner scan = new Scanner(System.in);

    String RESET = "\033[0m";
    String RED = "\033[31m";    
    String YELLOW = "\033[33m";  
    String BLUE = "\033[34m";
    String GREEN = "\033[32m";

    public void quest1HumanasQuest(Character chosen) {
        String name = chosen.getName();
        boolean isSkipped = false;
        boolean sceneFinished = false;

        while (!sceneFinished) {

            // --- INTRO SCENE ---
            isSkipped = introScene(name);
            if (isSkipped) {

                break;
            }

            // --- CHAPEL BUILD UP ---
            isSkipped = chapelSneakScene(name);
            if (isSkipped)
                break;

            // --- PASSCODE MINI GAME ---
            boolean passSuccess = passcodeChallenge(chosen);
            if (!passSuccess)
                continue; // restart quest
            sceneFinished = true;
        }

        // --- AFTER PASSCODE ---
        imprisonedRescueScene(name);

        // --- QUEST COMPLETE ---
        finishQuest(chosen, name);

        // --- NEXT MORNING SCENE ---
        nextMorningScene(name);
    }

    public boolean introScene(String name) {
        System.out.println("(" + name + " walks along the worn dirt path leading to the outskirts of a town)");
        scan.nextLine();
        System.out.println("(the wind carries faint echoes of bells and shouting)");
        scan.nextLine();
        System.out.println("(Internal monologue):");
        System.out.println(
                "\"The city comes into view - tall walls, broken gates, and guards that look more like beggars in armor.\"");
        scan.nextLine();
        System.out.println("\"This must be Humanas...\" ");
        scan.nextLine();

        System.out.println(YELLOW+"------------------You Have Just Arrived at:-------------------"+RESET);
        System.out.println(BLUE+"████    ██   ██████   ██████   ██   ██████   ████    ██   ███");
        System.out.println("██ ██   ██   ██  ██     ██     ██   ██  ██   ██ ██   ██    ██");
        System.out.println("██  ██  ██   ██████     ██     ██   ██  ██   ██  ██  ██    ██");
        System.out.println("██   ██ ██   ██  ██     ██     ██   ██  ██   ██   ██ ██    ██");
        System.out.println("██    ████   ██  ██     ██     ██   ██████   ██    ████   ████"+RESET);
        System.out.println(YELLOW+"----------------------Nation of Humanas-----------------------"+RESET);
        scan.nextLine();

        System.out.println("\"Once proud, maybe. But now… it looks like it's just trying to survive.\" ");
        scan.nextLine();

        System.out.println("(" + name
                + " steps through the gates; villagers move quickly, avoiding eye contact; a guard leans lazily by a post, glaring)");
        scan.nextLine();
        System.out.println("Guard:");
        System.out.println("\"You there. Traveler. What business brings you to Vensvale huh?");
        scan.nextLine();

        System.out.println(name + ":");
        System.out.println("Oh, just passing through.");
        scan.nextLine();

        System.out.println("Guard (grunts):");
        System.out.println("Hmph. Best keep moving then. Outsiders get blamed for enough already.");
        scan.nextLine();

        System.out.println("(Internal monologue):");
        System.out.println("\"Blamed? For what, exactly?\"");
        scan.nextLine();

        System.out.println("(" + name
                + " walks deeper into the market; half-empty stalls, broken carts, and desperate murmurs)");
        scan.nextLine();

        System.out.println("Merchant (*arguing with a woman):");
        System.out.println("Prices went up again! The collectors came last night-they took half my stock!");
        scan.nextLine();

        System.out.println("Woman:");
        System.out.println("Then my children will go hungry again? You think the lord's gonna feed us?");
        scan.nextLine();

        System.out.println("(a heavy silence falls; both avoid " + name + "'s eyes)");
        scan.nextLine();
        System.out.println("(Internal monologue)");
        System.out.println("\"Collectors... So that's what the stall owner meant earlier.\"");
        scan.nextLine();
        System.out.println(
                "\"Not monsters in the dark--just people in power who've forgotten what mercy looks like.\"");
        scan.nextLine();

        System.out.println("(a young boy tugs on " + name + "'s cloak)");
        scan.nextLine();

        System.out.println("Boy: ");
        System.out.println("Uhh.. E-excuse me mister... c-can you help my mama?");
        scan.nextLine();

        System.out.println(name + " (*kneels slightly):");
        System.out.println("What's wrong?");
        scan.nextLine();

        System.out.println("Boy (*hesitant):");
        System.out.println(
                "The guards took her. They said s-she didn't pay enough tax... but she already gave them all our coins.");
        scan.nextLine();

        System.out.println(name + " (*pauses):");
        System.out.println("...Where did they take her?");
        scan.nextLine();

        System.out.println("Boy:");
        System.out.println("To the old chapel. The one the King closed.");
        scan.nextLine();

        System.out.println("(the boy's eyes glisten; " + name + " nods and stands)");
        scan.nextLine();

        System.out.println("(Internal monologue):");
        System.out
                .println("\"A closed chapel turned into a prison... This kingdom really is rotten to the core.\"");
        scan.nextLine();

        while (true) {

            System.out.println("Quest Started: Echoes of a Broken Crown");
            System.out.println(
                    "Objective: Investigate the chapel, help the kid find his mom and uncover what's happening to the townsfolk.");
            System.out.println(YELLOW+"Press ['s'] to skip, and [Enter] to continue."+RESET);
            String input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                System.out.println("");
                System.out.println("Are you sure you would like to skip the Challenge?");
                System.out.println(YELLOW+"If yes, you will not level up [Y/N]: "+RESET);

                String inputSureSkip = scan.nextLine();
                while (!inputSureSkip.equalsIgnoreCase("Y") && !inputSureSkip.equalsIgnoreCase("N")) {
                    System.out.println(RED+"Invalid input. Please enter Y or N:"+RESET);
                    inputSureSkip = scan.nextLine();
                }

                if (inputSureSkip.equalsIgnoreCase("Y")) {
                    System.out.println("\nYou have skipped the Challenge, you did not level up");
                    scan.nextLine();
                    System.out.println(GREEN+"You may now proceed on your journey."+RESET);
                    scan.nextLine();
                    return true;
                }

                System.out.println();
            }
            return false;
        }
    }

    public boolean chapelSneakScene(String name) {
        String input;

        System.out.println("(" + name
                + " enters the outskirts of the ruined chapel -- torchlight flickers inside. Distant voices echo.)");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s")) {
                System.out.println("");
                System.out.println("Are you sure you would like to skip the Challenge?");
                System.out.println(YELLOW+"If yes, you will not level up [Y/N]: "+RESET);

                String inputSureSkip = scan.nextLine();
                while (!inputSureSkip.equalsIgnoreCase("Y") && !inputSureSkip.equalsIgnoreCase("N")) {
                    System.out.println(RED+"Invalid input. Please enter Y or N:"+RESET);
                    inputSureSkip = scan.nextLine();
                }

                if (inputSureSkip.equalsIgnoreCase("Y")) {
                    System.out.println("\nYou have skipped the Challenge, you did not level up");
                    scan.nextLine();
                    System.out.println(GREEN+"You may now proceed on your journey."+RESET);
                    scan.nextLine();
                    return true;
                }

                System.out.println();
            }

        System.out.println("(" + name + " hides behind a broken pillar, listening)");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s")) {
                System.out.println("");
                System.out.println("Are you sure you would like to skip the Challenge?");
                System.out.println(YELLOW+"If yes, you will not level up [Y/N]: "+RESET);

                String inputSureSkip = scan.nextLine();
                while (!inputSureSkip.equalsIgnoreCase("Y") && !inputSureSkip.equalsIgnoreCase("N")) {
                    System.out.println(RED+"Invalid input. Please enter Y or N:"+RESET);
                    inputSureSkip = scan.nextLine();
                }

                if (inputSureSkip.equalsIgnoreCase("Y")) {
                    System.out.println("\nYou have skipped the Challenge, you did not level up");
                    scan.nextLine();
                    System.out.println(GREEN+"You may now proceed on your journey."+RESET);
                    scan.nextLine();
                    return true;
                }

                System.out.println();
            }

        System.out.println("Guard 1:");
        System.out.println("Another one who couldn't pay, huh? Shame. Could've worked it off at the mines.");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s")) {
                System.out.println("");
                System.out.println("Are you sure you would like to skip the Challenge?");
                System.out.println(YELLOW+"If yes, you will not level up [Y/N]: "+RESET);

                String inputSureSkip = scan.nextLine();
                while (!inputSureSkip.equalsIgnoreCase("Y") && !inputSureSkip.equalsIgnoreCase("N")) {
                    System.out.println(RED+"Invalid input. Please enter Y or N:"+RESET);
                    inputSureSkip = scan.nextLine();
                }

                if (inputSureSkip.equalsIgnoreCase("Y")) {
                    System.out.println("\nYou have skipped the Challenge, you did not level up");
                    scan.nextLine();
                    System.out.println(GREEN+"You may now proceed on your journey."+RESET);
                    scan.nextLine();
                    return true;
                }

                System.out.println();
            }

        System.out.println("Guard 2 (*snickers):");
        System.out.println("Mines are full anyway. The lord needs examples, not workers.");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s")) {
                System.out.println("");
                System.out.println("Are you sure you would like to skip the Challenge?");
                System.out.println(YELLOW+"If yes, you will not level up [Y/N]: "+RESET);

                String inputSureSkip = scan.nextLine();
                while (!inputSureSkip.equalsIgnoreCase("Y") && !inputSureSkip.equalsIgnoreCase("N")) {
                    System.out.println(RED+"Invalid input. Please enter Y or N:"+RESET);
                    inputSureSkip = scan.nextLine();
                }

                if (inputSureSkip.equalsIgnoreCase("Y")) {
                    System.out.println("\nYou have skipped the Challenge, you did not level up");
                    scan.nextLine();
                    System.out.println(GREEN+"You may now proceed on your journey."+RESET);
                    scan.nextLine();
                    return true;
                }

                System.out.println();
            }

        System.out.println("Guard 1:");
        System.out.println("Ha. Maybe the others will learn next time. Fear is cheaper than mercy.");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s")) {
                System.out.println("");
                System.out.println("Are you sure you would like to skip the Challenge?");
                System.out.println(YELLOW+"If yes, you will not level up [Y/N]: "+RESET);

                String inputSureSkip = scan.nextLine();
                while (!inputSureSkip.equalsIgnoreCase("Y") && !inputSureSkip.equalsIgnoreCase("N")) {
                    System.out.println(RED+"Invalid input. Please enter Y or N:"+RESET);
                    inputSureSkip = scan.nextLine();
                }

                if (inputSureSkip.equalsIgnoreCase("Y")) {
                    System.out.println("\nYou have skipped the Challenge, you did not level up");
                    scan.nextLine();
                    System.out.println(GREEN+"You may now proceed on your journey."+RESET);
                    scan.nextLine();
                    return true;
                }

                System.out.println();
            }

        System.out.println("(Internal monologue):");
        System.out.println("\"Fear's cheaper than mercy.\"");
        System.out.println(
                "\"Those words... they sting. Like I've heard them before--just from the other side of the sword.\"");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s")) {
                System.out.println("");
                System.out.println("Are you sure you would like to skip the Challenge?");
                System.out.println(YELLOW+"If yes, you will not level up [Y/N]: "+RESET);

                String inputSureSkip = scan.nextLine();
                while (!inputSureSkip.equalsIgnoreCase("Y") && !inputSureSkip.equalsIgnoreCase("N")) {
                    System.out.println(RED+"Invalid input. Please enter Y or N:"+RESET);
                    inputSureSkip = scan.nextLine();
                }

                if (inputSureSkip.equalsIgnoreCase("Y")) {
                    System.out.println("\nYou have skipped the Challenge, you did not level up");
                    scan.nextLine();
                    System.out.println(GREEN+"You may now proceed on your journey."+RESET);
                    scan.nextLine();
                    return true;
                }

                System.out.println();
            }

        System.out.println("(" + name + " creeps closer, spots villagers locked inside; quietly, " + name
                + " tries to decrypt the passcode in the lock.)");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s")) {
                System.out.println("");
                System.out.println("Are you sure you would like to skip the Challenge?");
                System.out.println(YELLOW+"If yes, you will not level up [Y/N]: "+RESET);

                String inputSureSkip = scan.nextLine();
                while (!inputSureSkip.equalsIgnoreCase("Y") && !inputSureSkip.equalsIgnoreCase("N")) {
                    System.out.println(RED+"Invalid input. Please enter Y or N:"+RESET);
                    inputSureSkip = scan.nextLine();
                }

                if (inputSureSkip.equalsIgnoreCase("Y")) {
                    System.out.println("\nYou have skipped the Challenge, you did not level up");
                    scan.nextLine();
                    System.out.println(GREEN+"You may now proceed on your journey."+RESET);
                    scan.nextLine();
                    return true;
                }

                System.out.println();
            }

        return false;
    }

    public boolean passcodeChallenge(Character chosen) {
        int turns = 3;
        int password = 0;
        int randomPass = new Random().nextInt(4) + 1;

        while (turns != 0) {
            while (true) {
                try {
                    System.out.print(YELLOW+"Decrypting Lock... Enter a random number from [1-4]: "+RESET);
                    password = scan.nextInt();
                    scan.nextLine();
                    if (password < 1 || password > 4) {
                        System.out.println(RED+"Invalid! Only enter numbers from 1 to 4.\n"+RESET);
                        continue;
                    }

                    break;
                } catch (InputMismatchException e) {
                    System.out.println(RED+"Input Invalid. Only enter integers from 1 to 4"+RESET);
                    System.out.println();
                    scan.nextLine();
                }
            }

            if (password == randomPass) {
                System.out.println(GREEN+"Passcode Correct, Lock opens!"+RESET);
                scan.nextLine();

                chosen.setLevel(chosen.getLevel() + 1);
                System.out.println(chosen.getName() + " leveled up to level " + chosen.getLevel() + "!");
                scan.nextLine();

                System.out.println(GREEN+"You may now proceed on your journey."+RESET);
                scan.nextLine();
                return true;
            } else {
                System.out.println(RED+"Passcode Incorrect, Try again!"+RESET);
                turns--;
                System.out.println("Turns left: " + turns + ", once 0 return back to the beginning of the quest.");
                System.out.println();
            }

            if (turns == 0) {
                System.out.println(RED+"You failed the passcode challenge. Restarting from the beginning..."+RESET);
                scan.nextLine();
                return false;
            }
        }

        return false;
    }

    public void imprisonedRescueScene(String name) {
        String input;

        System.out.println("Imprisoned Woman:");
        System.out.println("Who... who are you?");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s"))
            return;

        System.out.println(name + ":");
        System.out.println("No one important. You're free now. Go...");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s"))
            return;

        System.out.println("Imprisoned Woman:");
        System.out.println("You shouldn't have come here. They'll kill you for this...");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s"))
            return;

        System.out.println(name + " (*softly):");
        System.out.println("They'll have to try.");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s"))
            return;

        System.out.println("(villagers look at each other)");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s"))
            return;

        System.out.println("Imprisoned Woman:");
        System.out.println("Thank you... who ever you are.");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s"))
            return;

        System.out.println(name + " (*nodds):");
        System.out.println("Now go! before the guards will catch all of you.");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s"))
            return;

        System.out.println("(" + name + " exits the chapel; villagers hurry away into the night)");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s"))
            return;

        System.out.println("(back at the marketplace-word spreads quietly.)");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s"))
            return;

        System.out.println("Merchant (whispering):");
        System.out.println("The stranger freed them. The guards are furious.");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s"))
            return;

        System.out.println("Old Man:");
        System.out.println("Could it be... a sign? That someone still remembers the old oath?");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s"))
            return;

        System.out.println("(Internal monologue):");
        System.out.println(
                "\"The 'old oath'... There's that word again. Even the people here whisper it like a ghost story. Could it be connected... to mine?\"");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s"))
            return;

        System.out.println("(soft whispers of distorted echoes faintly in the wind)");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s"))
            return;

        System.out.println("\"We vow to protect balance... We vow to...\"");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s"))
            return;

        System.out.println(name + " looks up, eyes narrowing");
        System.out.println(
                "\"That voice again... If helping these people brings me closer to remembering-then maybe that's where I start.\" ");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s"))
            return;

        System.out.println("(distant horn sounds; soldiers begin searching the streets)");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s"))
            return;

        System.out.println("(Internal monologue):");
        System.out.println("\"They're hunting for me. Figures...\"");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s"))
            return;

        System.out.println(name + " (*readies weapon):");
        System.out.println("Guess the dream's not so calm anymore.");
        input = scan.nextLine();
        if (input.equalsIgnoreCase("s"))
            return;

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void finishQuest(Character chosen, String name) {
        System.out.println();
        System.out.println(GREEN+"You have Finished the quest: Echoes of the broken Crown!"+RESET);

        scan.nextLine();
        System.out.println("Quest Summary:");
        System.out.println(
                "You aided the people of Vensvale and exposed the rot beneath Humanas's golden crown. The whispers of rebellion stir, and somewhere in the wind... a forgotten oath stirs with them.");
        scan.nextLine();

        chosen.setPotionCount(chosen.getPotionCount() + 2);
        System.out.println("Reward: 2 health potions added to your inventory.");
        scan.nextLine();
        System.out.println("Current Level: " + chosen.getLevel());
        System.out.println("Current Potions: " + chosen.getPotionCount());
        scan.nextLine();
        System.out.println(GREEN+"You may now proceed on your journey."+RESET);
        scan.nextLine();

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void nextMorningScene(String name) {
        System.out.println(
                "(the next morning dawns--sunlight leaks through the cracked stained glass of the castle courtyard)");
        scan.nextLine();

        System.out.println("(a servant rushes past, nearly bumping into " + name + ")");
        scan.nextLine();
        System.out.println("Servant (*panicked whisper):");
        System.out.println(
                "Get inside, quick! The King's attendants are preparing another royal decree! The lord's collectors want new taxes-on breathing, I think!");
        scan.nextLine();
        System.out.println(name + "(*raises brow):");
        System.out.println("...Breathing.");
        scan.nextLine();
        System.out.println("Servant:");
        System.out.println("Don't question it! Just keep your head down.");
        scan.nextLine();
        System.out.println("(the servant hurries off; " + name + " glances toward the open gate)");
        scan.nextLine();

        System.out.println("(Internal monologue):");
        System.out.println("\"A ruler who doesn't rule. Decrees written by ghosts.\"");
        System.out.println("\"If I want answers, I'll find them in that throne room.\"");
        scan.nextLine();

        System.out.println("(The Throne Room)");
        System.out.println(
                "(the chamber is massive but eerily quiet--curtains drawn, nobles frozen in fear. In the center sits a small, golden crib atop the throne. Two hooded attendants stand behind it.)");
        scan.nextLine();
        System.out.println("(Internal monologue):");
        System.out.println("\"That's him...?\"");
        scan.nextLine();

        System.out.println(
                "(a tiny toddler inside a crib with a crown far too large clutches a jeweled rattle, drooling on royal silk.)");
        System.out.println("(soft cooing fills the silence.)");
        scan.nextLine();
        System.out.println("Attendant 1 (*bowing):");
        System.out.println("All hail His Radiant Majesty, King Bartholomew Monarch, Heir of the Eternal Crown!");
        scan.nextLine();

        System.out.println(
                "(the baby gurgles and throws his rattle; it bounces off the marble and hits a guard in the shin)");
        scan.nextLine();
        System.out.println("Guard (*grimacing):");
        System.out.println("T-Tantrum Toss! Take cover!");
        scan.nextLine();
        System.out.println("(Internal monologue):");
        System.out.println("\"...You've got to be kidding me.\"");
        System.out.println("(the attendants whisper nervously)");
        scan.nextLine();

        System.out.println("Attendant 2:");
        System.out.println("Majesty seems displeased. Perhaps.. another decree?");
        scan.nextLine();
        System.out.println("Attendant 1 (*nods solemnly):");
        System.out.println("Yes. The foreign trespasser before him is guilty of-uh-disrupting royal peace.");
        scan.nextLine();

        System.out.println("(the baby claps his hands; golden light flickers from his crown)");
        scan.nextLine();
        System.out.println("(Intenal monlogue)");
        System.out.println("\"That's no ordinary glow. Something else is moving behind those eyes.\"");
        scan.nextLine();
        System.out.println("(a voice in " + name + "'s head whispers)");
        System.out.println(">>>\"Beware.. The Archivist watches through the crown.\"");
        scan.nextLine();

        System.out.println("(sudden shriek-the baby's crown flares, attendants bow low)");
        scan.nextLine();
        System.out.println("Attendant 1:");
        System.out.println("His Majesty has spoken! Execute the decree!");
        scan.nextLine();
        System.out.println(name + "(*draws weapon):");
        System.out.println("Guess diplomacy's off the table.");
        scan.nextLine();

        System.out.print("\033[H\033[2J"); // clear screen
        System.out.flush();
    }

}
