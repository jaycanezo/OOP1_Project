package EchoesOfTheOath;

import EchoesOfTheOath.Characters.Character;
import java.util.Scanner;

public class Storyline {
    Scanner scan = new Scanner(System.in);

    public void quest1HumanasQuest(Character chosen) {//Nation 1 Mini Quest
        String name = chosen.getName();
        boolean isSkipped = false;  

        while(true) {
            System.out.println("(" + name + " walks along the worn dirt path leading to the outskirts of a town)");
            scan.nextLine();
            System.out.println("(the wind carries faint echoes of bells and shouting)");
            scan.nextLine();
            System.out.println("(Internal monologue):");
            System.out.println("\"The city comes into view - tall walls, broken gates, and guards that look more like beggars in armor.\"");
            scan.nextLine();
            System.out.println("\"This must be Humanas...\" ");
            scan.nextLine();
            System.out.println("\"Once proud, maybe. But now… it looks like it's just trying to survive.\" ");
            scan.nextLine();
            
            System.out.println("(" + name + " steps through the gates; villagers move quickly, avoiding eye contact; a guard leans lazily by a post, glaring)");
            scan.nextLine();
            System.out.println("Guard:");
            System.out.println("You there. Traveler. What business brings you to Vensvale huh?");
            scan.nextLine();
            
            System.out.println(name +":");
            System.out.println("Oh, just passing through.");
            scan.nextLine();
            
            System.out.println("Guard (grunts):");
            System.out.println("Hmph. Best keep moving then. Outsiders get blamed for enough already.");
            scan.nextLine();
            
            System.out.println("(Internal monologue):");
            System.out.println("\"Blamed? For what, exactly?\"");
            scan.nextLine();
            
            System.out.println("(" + name + " walks deeper into the market; half-empty stalls, broken carts, and desperate murmurs)");
            scan.nextLine();
            
            System.out.println("Merchant (*arguing with a woman):");
            System.out.println("Prices went up again! The collectors came last night-they took half my stock!");
            scan.nextLine();
            
            System.out.println("Woman:");
            System.out.println("Then my children will go hungry again? You think the lord's gonna feed us?");
            scan.nextLine();

            System.out.println("(a heavy silence falls; both avoid the " + name + "'s eyes)");
            scan.nextLine();
            System.out.println("(Internal monologue)");
            System.out.println("\"Collectors...So that's what the stall owner meant earlier.\"");
            scan.nextLine();
            System.out.println("\"Not monsters in the dark--just people in power who've forgotten what mercy looks like.\"");
            scan.nextLine();
            
            System.out.println("(a young boy tugs on " + name + "'s cloak)");
            scan.nextLine();
            
            System.out.println("Boy: ");
            System.out.println("Uhh..E-excuse me mister... c-can you help my mama?");
            scan.nextLine();
            
            System.out.println(name +" (*kneels slightly):");
            System.out.println("What's wrong?");
            scan.nextLine();
            
            System.out.println("Boy (*hesitant):");
            System.out.println("The guards took her. They said s-she didn't pay enough tax... but she already gave them all our coins.");
            scan.nextLine();
            
            System.out.println(name +" (*pauses):");
            System.out.println("...Where did they take her?");
            scan.nextLine();
            
            System.out.println("Boy:");
            System.out.println("To the old chapel. The one the King closed.");
            scan.nextLine();

            System.out.println("(the boy's eyes glisten; " + name +" nods and stands)");
            scan.nextLine();

            System.out.println("(Internal monologue):");
            System.out.println("\"A closed chapel turned into a prison...This kingdom really is rotten to the core.\"");
            scan.nextLine();

            System.out.println("Quest Started: Echoes of a Broken Crown");
            System.out.println("Objective: Investigate the chapel and uncover what's happening to the townsfolk");
            System.out.println("Press ['s'] to skip, and [Enter] to continue.");
            String input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("("+ name + " enters the outskirts of the ruined chapel - torchlight flickers inside. Distant voices echo.)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("("+ name + " hides behind a broken pillar, listening)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("Guard 1:");
            System.out.println("Another one who couldn't pay, huh? Shame. Could've worked it off at the mines.");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("Guard 2 (*snickers):");
            System.out.println("Mines are full anyway. The lord needs examples, not workers.");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("Guard 1:");
            System.out.println("Ha. Maybe the others will learn next time. Fear is cheaper than mercy.");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("(Internal monologue):");
            System.out.println("\"Fear's cheaper than mercy.\"");
            System.out.println("\"Those words... they sting. Like I've heard them before-just from the other side of the sword.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("(" + name + " creeps closer, spots villagers locked inside; quietly breaks the door latch)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("Imprisoned Woman:");
            System.out.println("Who... who are you?");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println(name +":");
            System.out.println("No one important. You're free now. Go...");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("Imprisoned Woman:");
            System.out.println("You shouldn't have come here. They'll kill you for this...");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println(name +" (*softly):");
            System.out.println("They'll have to try.");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("(villagers look at each other)");
            System.out.println("Imprisoned Woman:");
            System.out.println("Thank you... who ever you are.");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println(name +" (*nodds):");
            System.out.println("Now go! before the guards will catch all of you.");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("("+name + " exits the chapel; villagers hurry away into the night)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("(back at the marketplace-word spreads quietly.)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("Merchant (whispering):");
            System.out.println("The stranger freed them. The guards are furious.");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("Old Man:");
            System.out.println("Could it be... a sign? That someone still remembers the old oath?");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("(Internal monologue):");
            System.out.println("\"The 'old oath'... There's that word again. Even the people here whisper it like a ghost story. Could it be connected... to mine?\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("(soft whispers of distorted echoes faintly in the wind)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("\"We vow to protect balance... We vow to...\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println(name + " looks up, eyes narrowing");
            System.out.println("\"That voice again... If helping these people brings me closer to remembering-then maybe that's where I start.\" ");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("(distant horn sounds; soldiers begin searching the streets)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("(Internal monologue):");
            System.out.println("\"They're hunting for me. Figures...\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println(name +" (*readies weapon):");
            System.out.println("Guess the dream's not so calm anymore.");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.print("\033[H\033[2J"); //clear screen
            System.out.flush();

            System.out.println("(night falls; " + name + " walks through the narrow stone alleys of Vensvale. Torches flicker as the sound of marching boots echoes closer)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("(Internal monologue):");
            System.out.println("\"The soldiers are everywhere now. Word travels fast when you cross the wrong kind of men.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("\"But I can't leave yet. Not after what I saw in that chapel.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("\"If this land's dying, someone's pulling the strings.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("\"And every whisper points to the same name...\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("\"King Bartholomew Monarch...\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("(*pauses)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("\"A king who never speaks for himself. A boy, barely old enough to walk.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("\"Something about that doesn’t sit right...\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            break; 
        }
        scan.nextLine();
        System.out.println("You have Finished the quest: Echoes of the broken Crown!");

        if(isSkipped) {
            scan.nextLine();
            System.out.println("Quest Summary:");
            System.out.println("You aided the people of Vensvale and exposed the rot beneath Humanas's golden crown. The whispers of rebellion stir, and somewhere in the wind... a forgotten oath stirs with them.");
            scan.nextLine();
        }

        chosen.setLevel(chosen.getLevel() + 1);
        System.out.println(chosen.getName() + " leveled up to level " + chosen.getLevel() + "!");
        scan.nextLine();
        chosen.setPotionCount(chosen.getPotionCount() + 5);
        System.out.println("Reward: 5 health potions added to your inventory.");
        scan.nextLine();
        System.out.println("Current Level: "+chosen.getLevel());
        System.out.println("Current Potions: " + chosen.getPotionCount());
        scan.nextLine();
        System.out.println("You may now proceed on your journey.");
        scan.nextLine();

        System.out.print("\033[H\033[2J"); //clear screen
        System.out.flush();

        System.out.println("(the next morning dawns--sunlight leaks through the cracked stained glass of the castle courtyard)");
        scan.nextLine();

        System.out.println("(a servant rushes past, nearly bumping into " + name + ")");
        scan.nextLine();
        System.out.println("Servant (*panicked whisper):");
        System.out.println("Get inside, quick! The King's attendants are preparing another royal decree! The lord's collectors want new taxes-on breathing, I think!");
        scan.nextLine();
        System.out.println(name + "(*raises brow):");
        System.out.println("...Breathing.");
        scan.nextLine();
        System.out.println("Servant:");
        System.out.println("Don't question it! Just keep your head down.");
        scan.nextLine();
        System.out.println("(the servant hurries off; "+ name +" glances toward the open gate)");
        scan.nextLine();
            
        System.out.println("(Internal monologue):");
        System.out.println("\"A ruler who doesn't rule. Decrees written by ghosts.\"");
        System.out.println("\"If I want answers, I'll find them in that throne room.\"");
        scan.nextLine();

            
        System.out.println("(The Throne Room)");
        System.out.println("(the chamber is massive but eerily quiet—curtains drawn, nobles frozen in fear. In the center sits a small, golden crib atop the throne. Two hooded attendants stand behind it.)");
        scan.nextLine();
        System.out.println("(Internal monologue):");
        System.out.println("\"That's him...?\"");
        scan.nextLine();

            
        System.out.println("(a tiny toddler inside a crib with a crown far too large clutches a jeweled rattle, drooling on royal silk.)");
        System.out.println("(soft cooing fills the silence.)");
        scan.nextLine();
        System.out.println("Attendant 1 (*bowing):");
        System.out.println("All hail His Radiant Majesty, King Bartholomew Monarch, Heir of the Eternal Crown!");
        scan.nextLine();

            
        System.out.println("(the baby gurgles and throws his rattle; it bounces off the marble and hits a guard in the shin)");
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

        System.out.print("\033[H\033[2J"); //clear screen
        System.out.flush();
    }
    
    public void theVeinsOfHumanasQuest(Character chosen){//Nation 1 Main Quest
        boolean isSkipped = false;
        System.out.print("\033[H\033[2J"); 
        System.out.flush();
        while (true) {
            System.out.println("(Thunder rolls over the castle, a shadowy figure watching from a tower)");
            System.out.println("\"So... the Echo stirs again.\"");
            scan.nextLine();

            System.out.println("Throne Room -- The air is still thick with smoke from battle. The broken crown lies in your hand.");
            scan.nextLine();

            System.out.println(chosen.getName() + " (softly):");
            System.out.println("\"This wasn't just one boy's curse. Someone built an empire on silence.\"");
            scan.nextLine();

            System.out.println("(A hidden passage creaks open behind the throne -- a servant girl steps out, dirt on her face, holding a worn ledger.)");
            scan.nextLine();

            System.out.println("Servant Girl:");
            System.out.println("\"You're not one of them... are you?\"");
            scan.nextLine();

            System.out.println(chosen.getName() + ":");
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
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("(You travel through abandoned tunnels beneath the capital. Broken murals show the once-glorious Humanas -- now cracked and forgotten.)");
            System.out.println("Narration: \"Once, these tunnels carried clean water. Now, they carry secrets... and blood.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("(You encounter starving villagers, chained miners, soldiers hoarding supplies.)");
            System.out.println("Villager (weakly):");
            System.out.println("\"They said the King ordered this... but the King can't even speak.\"");
            System.out.println(chosen.getName() + " (grimly):");
            System.out.println("\"Then someone else is wearing his voice.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("(Angry, you purge the royal guards enforcing 'tax extractions'. Each armor bears a quill -- The Archivist's mark.)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("(You discover a vast golden mechanism pulsing with light beneath the city -- the Heart of the Veins.)");
            System.out.println("Narration: \"Power drawn from despair. The rich drink wine above, while the poor bleed beneath.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("(A voice echoes -- calm, artificial, layered):");
            System.out.println("\"Progress requires sacrifice.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("(A giant projection flickers to life -- The Archivist appears, holding a quill made of bone.)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("The Archivist:");
            System.out.println("\"You freed the King. Admirable... but foolish. The people need guidance. They crave the comfort of command.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println(chosen.getName() + ":");
            System.out.println("\"They need truth. Not chains wrapped in gold.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("(Mechanisms activate -- golems powered by human life awaken. Battle ensues!)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("(After an exhausting battle, you disable the Heart's control core.)");
            System.out.println("Narration: \"The light fades. For the first time in decades, the city above will go dark... and free.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("The Archivist (voice):");
            System.out.println("\"So be it. You've chosen chaos. Now face the cost of your rebellion.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("(Your pendant glows faintly -- a new mark appears: Echo Resonance II.)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("(Above ground, the citizens of Humanas stand confused as the golden light fades.)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("Elder:");
            System.out.println("\"What happened to the sky?\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("Servant Girl (smiling faintly):");
            System.out.println("\"It's not shining on lies anymore.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("Narration:");
            System.out.println("\"Maybe they'll hate me for this. Maybe they'll thank me later.\"");
            System.out.println("\"But at least now, they'll see.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            break;
        }
        scan.nextLine();
        System.out.println("You have finished the quest: The Veins of Humanas!");
        if(isSkipped) {
            scan.nextLine();
            System.out.println("Quest Summary:");
            System.out.println("After freeing the infant King from The Archivist's control, you uncover that the kingdom's true rot runs deep -- the nation of Humanas bleeds from within. The rich bathe in light while the slums drown in darkness. Every decree, every war, every famine, all trace back to the same unseen hand.");
            System.out.println("To restore balance, you must expose the Veins of Control -- an ancient network that feeds power to the corrupted throne.");
            scan.nextLine();
        }


        //battle reward and stat update
        chosen.setLevel(chosen.getLevel() + 1);
        System.out.println(chosen.getName() + " leveled up to level " + chosen.getLevel() + "!");
        scan.nextLine();
        chosen.setPotionCount(chosen.getPotionCount() + 3);
        System.out.println("Reward: 3 health potions added to your inventory.");
        scan.nextLine();
        System.out.println("Current Level: "+chosen.getLevel());
        System.out.println("Current Potions: " + chosen.getPotionCount());
        scan.nextLine();
        System.out.println("You may now proceed on your journey.");
        scan.nextLine();

        //story continuation
        System.out.print("\033[H\033[2J"); //clear screen
        System.out.flush();

        System.out.println("(In a high tower far from the capital -- a cloaked figure dips a quill into ink made of black light.)");
        System.out.println("The Archivist (smiling faintly):");
        System.out.println("They learn... too slowly. But no matter. Every rebellion ends the same way.");
        scan.nextLine();
        System.out.println("(he writes your name into a massive book -- the ink burns with crimson flame.)");
        System.out.println("Let's see if your story ends differently, Echo");
        System.out.println();

        System.out.println("(The sound of dripping ink. Whispering voices overlap faintly: echoes of names, pleas, debts, all fading in and out.)");
        System.out.println("\"They said the kingdom's memory is buried beneath the palace...\"");
        System.out.println("\"But they never said it was alive.\"");
        scan.nextLine();
            
        System.out.println("(A massive, endless library carved beneath the city. Scrolls float through the air like ghosts. Ink seeps across the floor like veins.)");
        System.out.println("Every step feels heavier...");
        System.out.println("Like the air itself is keeping count.");
        scan.nextLine();
            
        System.out.println("(A massive desk in the distance - shadowed, illuminated by a cold, pale light. A figure sits motionless behind towers of parchment.)");
        System.out.println("The Archivist (voice calm, layered, almost kind):");
        System.out.println("\"So. The wanderer finally reaches the end of the record.\"");
        scan.nextLine();
        System.out.println(chosen.getName() + ":");
        System.out.println("\"...So you're the one writing all this.\"");
        scan.nextLine();
            
        System.out.println("The Archivist:");
        System.out.println("\"I don't write. I preserve. Every promise broken, every lie told, every soul sold for convenience. I am merely the ink that remembers.\"");
        scan.nextLine();
        System.out.println(chosen.getName() + " (steps closer):");
        System.out.println("\"You've turned this kingdom into a graveyard. For what? Order?\"");
        scan.nextLine();
            
        System.out.println("The Archivist:");
        System.out.println("\"For continuity. Mortals are unreliable historians. They forget. They dream. They forgive. I ensure the truth endures... even if it must bleed to stay real.\"");
        scan.nextLine();
        System.out.println(chosen.getName() + ":");
        System.out.println("\"You're not preserving truth. You're feeding off it.\"");
        scan.nextLine();
        System.out.println("The Archivist (chuckles softly):");
        System.out.println("\"Is there a difference?\"");
        scan.nextLine();
            
        System.out.println("(a low hum fills the air - the floating scrolls begin circling faster)");
        System.out.println("The Archivist:");
        System.out.println("\"The child was the perfect instrument. A ruler too young to understand is one who never questions. Through his tantrums, I rebuilt the nation's ruin.\"");
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
        System.out.print("\033[H\033[2J"); //clear screen
        System.out.flush();
    }
    
    public void whispersBeneathTheBoughs(Character chosen) {//Nation 2 Mini Quest
        
        String name = chosen.getName(); 
        boolean isSkipped = false; 

        System.out.print("\033[H\033[2J"); //clear screen using ANSI escape codes
        System.out.flush();

        System.out.println("(silence. The library crumbles slowly, scrolls burning into motes of light.)");
        scan.nextLine();
        System.out.println("The Archivist (fading, voice almost human):");
        System.out.println("\"Foolish... thing... You think freedom brings peace?\"");
        scan.nextLine();
        System.out.println("Hero (softly):");
        System.out.println("\"No. But at least it brings choice.\"");
        scan.nextLine();
        System.out.println("(The Archivist's last ink-drop hits the floor — it ripples outward, forming a single, blank page.)");
        scan.nextLine();
        System.out.println("(Hero kneels, picks it up.)");
        scan.nextLine();
        System.out.println("Hero (to self):");
        System.out.println("\"A record without an ending... maybe that's how it should be.\"");
        scan.nextLine();
        System.out.println("(the roof collapses, sunlight pierces through the library for the first time. Music swells with quiet triumph and melancholy.)");
        scan.nextLine();
        System.out.println("\"The people will wake.\"");
        System.out.println("\"The lies will fade.\"");
        System.out.println("\"But the echoes… will remain.\"");

        System.out.print("\033[H\033[2J"); //clear screen
        System.out.flush();

        System.out.println("With the fall of The Archivist, new paths unfold before " + name + ". The lands of Veyora call out — ancient forests where the elves once thrived, now fractured by war and shadowed by forgotten oaths.");
        scan.nextLine();

        System.out.print("\033[H\033[2J"); //clear screen
        System.out.flush();
        
        
        System.out.println("Continuing the journey you now have arrived at Veyora: \"The Elven Lands\"");
        scan.nextLine();
        
        
        // This loop contains the entire quest.
        
        while (true) {
            System.out.println("faint sound of wind rustling leaves; the whisper of distant voices)");
            System.out.println("(Internal monologue):");
            System.out.println("\"The air here feels... alive.\"");
            System.out.println("\"But not with peace.\"");
            scan.nextLine();

            System.out.println(name + " stands before an ancient, overgrown forest. Pale blue motes drift.)");
            System.out.println("(Internal monologue):");
            System.out.println("\"The elves once called this place Veyora, the cradle of all nature.\"");
            System.out.println("\"But the roots twist now — tangled with something that remembers me.\"");
            scan.nextLine();
            
            System.out.println("(" + name + " steps forward; the screen distorts briefly, showing a flash — Lunareth smiling beside " + name + " at the Celestial Gate)");
            System.out.println(name + " (gritting teeth):");
            System.out.println("\"That light again… Why does it hurt to remember?\"");
            scan.nextLine();

            System.out.println("Mini Quest Started: “Whispers Beneath the Boughs”");
            System.out.println("Objective: Investigate the Eastern Grove and uncover the source of the whispers.");
            System.out.println("\n(Press [Enter] to continue or [s] to skip at any time.)\n");
	        String input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true; 
                break; 
            }
            
            System.out.println("(trees loom like cathedral pillars, their bark veined with faint purple light.)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("(" + name + " reaches a ruined elven outpost. Elven warriors argue in the distance.)");
            System.out.println("Elven Scout (snapping):");
            System.out.println("\"We fight because the High Bough commands it! Lunareth’s word is law!\"");
            System.out.println("Elven Rebel:");
            System.out.println("\"Law? You call madness law? He bleeds the forest dry!\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("(" + name + " approaches. Both elves draw their blades slightly.)");
            System.out.println(name + ":");
            System.out.println("\"I don't mean harm. What's happening here?\"");
            System.out.println("Rebel (spits to the ground):");
            System.out.println("\"Outsider. You smell of Humanas — of the ink-born filth.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println(name + ":");
            System.out.println("\"I'm looking for Lunareth.\"");
            System.out.println("(They freeze. The Scout's voice lowers, cautious but reverent.)");
            System.out.println("Scout:");
            System.out.println("\"The Warden does not meet wanderers. Only the chosen — or the condemned.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("Rebel (coldly):");
            System.out.println("\"Then you're already half-dead.\"");
            System.out.println("(The rebel leaves; the scout hesitates, then steps closer.)");
            System.out.println("Scout:");
            System.out.println("\"If you truly seek him, go east. But beware the mists. They whisper truth and lies alike.\"");
            System.out.println("\"The Root watches all who tread here.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("(The forest grows darker. " + name + " hears overlapping whispers...)");
            System.out.println("Whisper 1 (soft, echoing):");
            System.out.println("\"You swore to protect him...\"");
            System.out.println("Whisper 2 (distorted):");
            System.out.println("\"You failed him...\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println(name + " (covers ears):");
            System.out.println("\"Stop… you’re not real.\"");
            System.out.println("(A spectral vision forms — the silhouette of Lunareth appears briefly.)");
            System.out.println("Lunareth (illusory, gentle):");
            System.out.println("\"Do you remember our vow beneath the Gate?\"");
            System.out.println("\"You promised balance… and left me to bear its ruin.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("(" + name + " swings their weapon — the image shatters. Roots from the ground lash upward!)");
            System.out.println("Mini Encounter — “The Root’s Guardians”");
            System.out.println("(Corrupted Dryads & Spirit Beasts emerge!)");
            System.out.println("(After victory, " + name + " kneels, panting. The pendant glows faintly.)");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println(name + " (to self):");
            System.out.println("\"The corruption here… it feels alive. Like it’s searching for something.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("(" + name + " stumbles into a grove. An older elf, cloaked in green-grey, kneels by a dying tree.)");
            System.out.println("Healer (without looking up):");
            System.out.println("\"Another wanderer who thinks they can save us?\"");
            System.out.println(name + ":");
            System.out.println("\"I'm not here to save anyone. I just want to understand what's happening.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("Healer (bitter chuckle):");
            System.out.println("\"Understanding won't heal what's already rotted.\"");
            System.out.println("(" + name + " looks closer — dark veins spread up her arm.)");
            System.out.println(name + ":");
            System.out.println("\"You're infected.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("Healer:");
            System.out.println("\"All of Veyora is. The Root of Despair feeds on division... now it binds us to paranoia.\"");
            System.out.println(name + ":");
            System.out.println("\"Then let me help.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("Healer (eyes narrowing):");
            System.out.println("\"Help? Then bring me a Tear of Veyora — the last pure sap from the forest’s heart.\"");
            System.out.println("\"But beware… the one who guards it no longer knows friend from prey.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("Quest Objective: Retrieve the Tear of Veyora from the Silverfang Huntress's domain.");
            System.out.println("Healer (quietly):");
            System.out.println("\"She was once the pride of Lunareth's guard. If you face her… speak before you strike.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println(name + " (softly):");
            System.out.println("\"I've faced the lost before.\"");
            System.out.println("(The screen fades to black — faint heartbeat sound.)");
            
            break; 
        }
        System.out.println("You have finished the Mini Quest: Whispers Beneath the Boughs!");
        if (isSkipped) {
            System.out.println("Quest Summary: “Whispers Beneath the Boughs”");
            System.out.println("You arrived in Veyora and found a divided elven land. You learned from a scout");
            System.out.println("and a rebel that Lunareth's 'order' is madness. After investigating the mists,");
            System.out.println("you were led to a Healer infected by the 'Root of Despair' who has asked you");
            System.out.println("to retrieve the 'Tear of Veyora' from the Silverfang Huntress.");
        }
        
        //INSERT BATTLE

        // Print rewards and transition regardless of skip status
        //battle reward and stat update
        chosen.setLevel(chosen.getLevel() + 1);
        System.out.println(chosen.getName() + " leveled up to level " + chosen.getLevel() + "!");
        scan.nextLine();
        chosen.setPotionCount(chosen.getPotionCount() + 3);
        System.out.println("Reward: 3 health potions added to your inventory.");
        scan.nextLine();
        System.out.println("Current Level: "+chosen.getLevel());
        System.out.println("Current Potions: " + chosen.getPotionCount());
        scan.nextLine();
        System.out.println("You may now proceed on your journey.");
        scan.nextLine();

        System.out.print("\033[H\033[2J"); 
        System.out.flush();
    }
    
    // --- Quest 6: The Silverfang's Trial (Mini Boss) ---
    public void theSilverfangTrial(Character chosen) {
        
        String name = chosen.getName();
        boolean isSkipped = false;
        String input;
        
        System.out.println("MINI BOSS QUEST: “The Silverfang’s Trial”");
                
        while (true) {
	System.out.println("\n(Press [Enter] to continue or [s] to skip at any time.)\n");
	input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("--- Scene 1 — “The Den of Echoes” ---");
            System.out.println("(camera fades in — " + name + " steps into a vast glade. Moonlight cuts through the mist.)");
            System.out.println("(Internal monologue):");
            System.out.println("\"The forest grows silent.\"");
            System.out.println("\"No birds. No breath. Only a hunter’s patience.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("(A silver arrow strikes the ground at " + name + "'s feet. A second slices past.)");
            System.out.println(name + " (calmly):");
            System.out.println("\"You could’ve aimed for the heart.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("Ilaryx (voice echoes from above):");
            System.out.println("\"If I wanted you dead, you’d already be a memory.\"");
            System.out.println("(Ilaryx leaps down gracefully, landing several feet away, bow drawn.)");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println(name + ":");
            System.out.println("\"You must be the Huntress. Ilaryx.\"");
            System.out.println("Ilaryx:");
            System.out.println("\"Names mean little in Veyora now. You walk in a place where loyalty and betrayal wear the same face.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println(name + ":");
            System.out.println("\"The Healer sent me. She said you guard the Tear of Veyora.\"");
            System.out.println("(Ilaryx’s expression darkens.)");
            System.out.println("Ilaryx:");
            System.out.println("\"Then she’s grown desperate. The Tear belongs to the Warden.\"");
            System.out.println("\"His law keeps the forest alive — even if it must bleed to survive.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println(name + ":");
            System.out.println("\"Alive? Look around you. The trees are dying, the roots are black.\"");
            System.out.println("\"This isn’t life. It’s control.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("Ilaryx (tone sharpens):");
            System.out.println("\"Weakness always looks like mercy to the untested.\"");
            System.out.println("\"Let’s see what you call truth when you’re fighting for breath.\"");
            System.out.println("(She steps back, bow glows with silver aura.)");
            System.out.println("Ilaryx:");
            System.out.println("\"If your resolve is stronger than your lies… prove it.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("BOSS BATTLE: ILARYX, THE SILVERFANG HUNTRESS");
            System.out.println("Music: “Moonlit Precision”");
            System.out.println("--- Phase 1: The Hunt Begins ---");
            System.out.println("(Ilaryx moves fast, using Quick Shot and leaving Hunter’s Traps.)");
            System.out.println("(Internal monologue between dodges):");
            System.out.println("\"She reads every motion — every breath. Like the forest itself is guiding her aim.\"");
            System.out.println("Ilaryx (taunting):");
            System.out.println("\"Your footsteps betray you.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("--- Phase 2: The Silver Trial ---");
            System.out.println("(At 50% HP — she lands on the ground, aura flickering.)");
            System.out.println("Ilaryx:");
            System.out.println("\"You fight well… for one without roots.\"");
            System.out.println(name + " (panting):");
            System.out.println("\"Maybe that’s why I see clearer.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("(Ilaryx glares — her bow glows brilliantly.)");
            System.out.println("Ilaryx (chanting):");
            System.out.println("\"Silverfang — judge the unworthy!\"");
            System.out.println("(Ultimate — Arrow of the Silverfang! A single glowing arrow.)");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("--- Phase 3: The Echo of Doubt ---");
            System.out.println("(Ilaryx staggers slightly. Her eyes flicker.)");
            System.out.println(name + ":");
            System.out.println("\"You’re hesitating. Why?\"");
            System.out.println("Ilaryx (voice trembling):");
            System.out.println("\"He saved us once… Lunareth. I saw chaos burn our kin alive. I won’t see that again.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println(name + ":");
            System.out.println("\"He saved you — but what has he become?\"");
            System.out.println("Ilaryx:");
            System.out.println("\"He says control is mercy… But why does mercy feel like chains?\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("(Ilaryx clutches her head. Corruption surges violently.)");
            System.out.println("Ilaryx (screaming):");
            System.out.println("\"Get out of my mind!\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("--- Final Phase: “The Bound Huntress” ---");
            System.out.println("(Ilaryx fights wildly, her defense lowered.)");
            System.out.println("(Internal monologue):");
            System.out.println("\"The forest binds her — she’s no longer fighting me. She’s fighting herself.\"");
            System.out.println("// TODO: Insert final battle phase logic here");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("(Cinematic slow motion as " + name + "'s final attack shatters Ilaryx’s bow.)");
            System.out.println("(Ilaryx collapses, breathing heavily, corruption dissipating.)");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("--- Scene 2 — “The Tear of Veyora” ---");
            System.out.println("(Ilaryx kneels, eyes clear but full of guilt. " + name + " approaches.)");
            System.out.println("Ilaryx (hoarse):");
            System.out.println("\"The Tear… take it.\"");
            System.out.println("(Ilaryx opens her hand, revealing a glowing crystal drop of pure sap.)");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println(name + ":");
            System.out.println("\"You could’ve killed me.\"");
            System.out.println("Ilaryx (faint smile):");
            System.out.println("\"I tried. But you fought like someone who remembers why they fight.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println(name + ":");
            System.out.println("\"The forest can heal, Ilaryx. But not if you keep obeying a voice that’s forgotten its promise.\"");
            System.out.println("Ilaryx:");
            System.out.println("\"You speak of promises as if they still mean anything.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println(name + ":");
            System.out.println("\"They do. I once made one too.\"");
            System.out.println("Ilaryx (recognition flickers):");
            System.out.println("\"…You. The one from the Oath…\"");
            System.out.println("(Ilaryx collapses before finishing. The Tear glows brighter in " + name + "'s hand.)");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("--- Ending Scene — “A Hunter’s Remorse” ---");
            System.out.println("(Internal monologue):");
            System.out.println("\"Her arrows fell silent. But her doubt... lingered...\"");
            System.out.println(name + " (softly):");
            System.out.println("\"Lunareth… I’m coming.\"");
            
            break; 
        }
        
        if (isSkipped) {
            System.out.println("\n--- QUEST SKIPPED ---");
            System.out.println("Quest Summary: “The Silverfang’s Trial”");
            System.out.println("You confronted Ilaryx, the Silverfang Huntress. After a duel that tested");
            System.out.println("both her resolve and her loyalty to Lunareth, you managed to break the");
            System.out.println("corruption's hold on her. She revealed her own doubts about Lunareth's");
            System.out.println("tyranny and gave you the 'Tear of Veyora' before collapsing.");
        }
        
        //
        // INSERT BATTLE
        //
        
        System.out.println("\nQuest Completed: “The Silverfang’s Trial”");
        System.out.println("Rewards:");
        System.out.println("  Item: Tear of Veyora — Key Item for Main Quest");
        System.out.println("  Lore Entry: Ilaryx’s Confession (Fragment II)");
        
        System.out.println("\nNext Quest Unlocked: MAIN QUEST: “The Roots of Despair”");
        System.out.println("(Press Enter to continue...)");
        scan.nextLine();

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    

    // --- Quest 7: The Roots of Despair (Main Quest) ---
    public void theRootsOfDespair(Character chosen) {
        
        String name = chosen.getName();
        boolean isSkipped = false;
        String input;

        System.out.println("MAIN QUEST: “The Roots of Despair”");
                
        while (true) {
	    System.out.println("\n(Press [Enter] to continue or [s] to skip at any time.)\n");
	    input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("--- Scene 1 — “Return to the Healer” ---");
            System.out.println("(Internal monologue as " + name + " walks with the glowing Tear of Veyora):");
            System.out.println("\"The Huntress’s arrows no longer pierce the air.\"");
            System.out.println("\"But the wound she guarded still bleeds beneath the earth.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("(" + name + " enters the Healer’s Grove. The Tear is placed into the roots.)");
            System.out.println("Healer (weak but smiling):");
            System.out.println("\"You found it… I can feel the forest breathe again.\"");
            System.out.println(name + ":");
            System.out.println("\"Will this heal it?\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("Healer (shakes head):");
            System.out.println("\"No. It will only remind it how to heal.\"");
            System.out.println(name + ":");
            System.out.println("\"Then what’s poisoning it?\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("Healer (looks to the east):");
            System.out.println("\"The Root of Despair. Once, it carried Lunareth’s voice... Now it speaks only madness.\"");
            System.out.println(name + ":");
            System.out.println("\"Then that’s where I’m going.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("Healer:");
            System.out.println("\"Be warned — the closer you get, the louder the Oath will whisper.\"");
            System.out.println("\"It remembers you… even if he does not.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("Quest Objective: Reach the Heart of Veyora and confront the Root of Despair.");
            System.out.println("--- Scene 2 — “The Heartwood Descent” ---");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("(" + name + " moves through corrupted terrain. Vines pulse like veins.)");
            System.out.println("(Internal monologue):");
            System.out.println("\"The deeper I walk, the more the forest changes. It’s not dying… it’s dreaming.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("(Whispers fill the air.)");
            System.out.println("Lunareth (distant, echoing):");
            System.out.println("\"Unity through will. Order through strength.\"");
            System.out.println("Sarukdal (faint):");
            System.out.println("\"He’s lost to it… we all are…\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println(name + " (gritting teeth):");
            System.out.println("\"No. You’re still in there.\"");
            System.out.println("(The forest floor cracks open. " + name + " descends into the darkness.)");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("--- Scene 3 — “The Root Speaks” ---");
            System.out.println("(The Root of Despair looms like a colossal heart. A projection of Lunareth forms from the vines.)");
            System.out.println("Lunareth (illusory):");
            System.out.println("\"So… the Oathbreaker returns.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println(name + " (shocked):");
            System.out.println("\"Lunareth…?\"");
            System.out.println("Lunareth:");
            System.out.println("\"Do you still claim that name? ‘Hero’? You who fell — you who let Elarion’s seal shatter?\"");
            System.out.println(name + ":");
            System.out.println("\"That wasn’t how it happened—\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("Lunareth (cutting " + name + " off):");
            System.out.println("\"Silence!\"");
            System.out.println("(Vines surge outward. The illusion's eyes fill with pain.)");
            System.out.println("Lunareth:");
            System.out.println("\"You left me to watch the world tear itself apart. So I rebuilt it!\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println(name + ":");
            System.out.println("\"By binding them in fear?\"");
            System.out.println("Lunareth:");
            System.out.println("\"By saving them from their own chaos!\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("(The Root shudders. Corrupted spirits erupt!)");
            System.out.println("Combat Encounter: “Echoes of the Root”");
            System.out.println("(Spirit Echoes of Elven Warriors attack!)");
            System.out.println("(Internal monologue during battle:)");
            System.out.println("\"These aren’t enemies. They’re memories — all trapped in the same lie.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("(After the final wave, the Root pulses faintly.)");
            System.out.println(name + " (panting):");
            System.out.println("\"Lunareth… this isn’t you.\"");
            System.out.println("Lunareth (voice distant, fading):");
            System.out.println("\"You don’t understand. I saw what happens when oaths break...\"");
            System.out.println(name + ":");
            System.out.println("\"You already did. When you let it own you.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("Objective: Purify the Root of Despair.");
            System.out.println("--- Scene 4 — “The Hero’s Memory” ---");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("(" + name + " places a hand on the Root’s core. Light bursts—a memory.)");
            System.out.println("(Scene: The Celestial Gate. " + name + ", Lunareth, Sarukdal, and the Archivist stand beneath the stars.)");
            System.out.println("Young Lunareth (smiling):");
            System.out.println("\"We vow to protect balance. Not through fear… but through unity.\"");
            System.out.println(name + " (from memory):");
            System.out.println("\"Together, then. Until the stars forget to shine.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("(The memory fractures. The vision ends. " + name + " collapses.)");
            System.out.println(name + " (whispers):");
            System.out.println("\"I remember now…\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("--- Scene 5 — “The Warden’s Shadow” ---");
            System.out.println("(The Root’s light stabilizes. The forest glows softly. A deep rumble echoes from above.)");
            System.out.println("(Internal monologue):");
            System.out.println("\"The forest breathes again… but its heart still aches.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("(In the distance, a massive citadel carved into a tree trunk looms.)");
            System.out.println(name + " (narrowing eyes):");
            System.out.println("\"The Warden waits.\"");
            System.out.println("Lunareth (disembodied voice):");
            System.out.println("\"If you seek truth, then come. Let us see which of us still remembers the Oath.\"");
            
            break; 
        }
        

        if (isSkipped) {
            System.out.println("\n--- QUEST SKIPPED ---");
            System.out.println("Quest Summary: “The Roots of Despair”");
            System.out.println("You returned the Tear of Veyora to the Healer, who directed you to the");
            System.out.println("'Root of Despair'. You descended into the Heartwood and confronted an");
            System.out.println("illusory Lunareth, who accused you of breaking your oath. By placing your");
            System.out.println("hand on the root, you triggered a memory of the original oath, purifying the");
            System.out.println("forest's corruption and revealing Lunareth's citadel.");
        }
        
        //
        //INSERT BATTLE
        //
        
        System.out.println("\nQuest Completed: “The Roots of Despair”");
        System.out.println("Rewards:");
        System.out.println("  Ability: Echo Resonance IV");
        System.out.println("  Key Item: Fragment of the Celestial Gate");
        System.out.println("  Lore Entry: The Oath Remembered (Fragment III)");
        
        System.out.println("\n(Transition Scene)");
        System.out.println("(Internal monologue):");
        System.out.println("\"I came to heal a forest. But I found a brother in chains.\"");
        
        System.out.println("\nNext Quest: FINAL BOSS — “The Warden of the Fractured Bough”");
        System.out.println("(Press Enter to continue...)");
        scan.nextLine();

        System.out.print("\033[H\033[2J"); 
        System.out.flush();
    }
    
    
    
    // --- Quest 8: The Warden of the Fractured Bough (Final Boss) ---
    public void theWardenOfTheFractured(Character chosen) {
        
        String name = chosen.getName();
        boolean isSkipped = false;
        String input;
        

        System.out.println("FINAL QUEST: “The Warden of the Fractured Bough”");

        while (true) {
	System.out.println("\n(Press [Enter] to continue or [s] to skip at any time.)\n");
	input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("--- Scene 1 — “The Citadel of Thorns” ---");
            System.out.println("(Camera opens to " + name + " approaching Lunareth’s citadel — an enormous fortress of living wood.)");
            System.out.println("(Internal monologue):");
            System.out.println("\"The forest no longer whispers. It watches.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("(The gates open silently. " + name + " steps inside. Lunareth descends from a platform of vines.)");
            System.out.println("Lunareth:");
            System.out.println("\"You came.\"");
            System.out.println("\"Do you remember, Oathbearer, what you swore beneath the Celestial Gate?\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println(name + ":");
            System.out.println("\"That we’d protect the balance — together.\"");
            System.out.println("Lunareth (bitter smile):");
            System.out.println("\"And you broke it.\"");
            System.out.println("\"So now… I’ll break you.\"");
            System.out.println("(He raises his bow — moonlight gathers.)");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("BOSS BATTLE BEGINS: LUNARETH, THE WARDEN OF THE FRACTURED BOUGH");
            System.out.println("--- Phase I — “The Silver Warden” ---");
            System.out.println("Music: “Moonlight Tyrant”");
            System.out.println("Lunareth’s Skills:");
            System.out.println("  Moonpierce Shot (Skill 1): Fires two arrows, ignores 30% defense.");
            System.out.println("  Binding Roots (Skill 2): Immobilizes all enemies for 2 turns.");
            System.out.println("  Passive – Warden’s Focus: Attack increases when you use defensive skills.");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("(Battle animation — Lunareth moves gracefully, like a dance.)");
            System.out.println("Lunareth (during attacks):");
            System.out.println("\"You hesitate — as you always did!\"");
            System.out.println("\"The forest bends to my will!\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println(name + " (gritting teeth):");
            System.out.println("\"You’re not saving them, Lunareth — you’re enslaving them!\"");
            System.out.println("Lunareth:");
            System.out.println("\"Order is mercy. Chaos is death.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("--- Phase Transition — “The Eclipse Breaks” ---");
            System.out.println("(At 50% HP, the citadel trembles. Lunareth kneels, clutching his chest.)");
            System.out.println("(Internal monologue):");
            System.out.println("\"For a moment, the tyrant falters — and the guardian flickers beneath.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println(name + ":");
            System.out.println("\"Lunareth… you can still fight it!\"");
            System.out.println("Lunareth (voice cracking):");
            System.out.println("\"I am fighting it!\"");
            System.out.println("(The moon above darkens. Lunareth’s eyes turn entirely black.)");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("--- Phase II — “Eclipse Ascendant” ---");
            System.out.println("Music: “Fractured Oath”");
            System.out.println("Lunareth’s New Skill:");
            System.out.println("  Ultimate – Eclipse Volley: Leaps and rains down four arrows per target.");
            System.out.println("Environmental Hazard – Lunar Rain: Silver light damages ALL characters on the field.");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("Lunareth (shouting midair):");
            System.out.println("\"If the forest must burn to be reborn — so be it!\"");
            System.out.println(name + " (raising weapon):");
            System.out.println("\"Then I’ll save it from you — even if it means losing you!\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("QTE: Break Lunareth’s Bow");
            System.out.println("(Player input required — a timed sequence to deflect lunar arrows and close the distance.)");
            System.out.println("(Success: " + name + " slashes upward, shattering Lunareth’s silver bow.)");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("--- Final Phase — “The Warden’s Heart” ---");
            System.out.println("(Lunareth collapses to one knee — his armor splintering, fragments of light leaking out.)");
            System.out.println("Lunareth (faintly):");
            System.out.println("\"You were right… I couldn’t protect them.\"");
            System.out.println("\"So I tried to control what was left.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println(name + ":");
            System.out.println("\"You don’t have to carry it alone anymore.\"");
            System.out.println("Lunareth (smiling weakly):");
            System.out.println("\"The forest… remembers your name again.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("(The moonlight fades. Lunareth’s body dissolves into silver motes that drift into the tree’s heart.)");
            System.out.println("(Internal monologue):");
            System.out.println("\"The tyrant fell. The guardian returned to the roots.\"");
            System.out.println("\"And the forest finally slept.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("--- Epilogue — “The Oath Rekindled” ---");
            System.out.println("(Camera pans out — the citadel is now overgrown with living vines. The Healer kneels beside " + name + ".)");
            System.out.println("Healer:");
            System.out.println("\"He found peace, didn’t he?\"");
            System.out.println(name + " (looking at the glowing roots):");
            System.out.println("\"He found his way back.\"");
            
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("(" + name + " opens their palm — a fragment of Lunareth’s bow rests there.)");
            System.out.println("Healer (softly):");
            System.out.println("\"Then Veyora breathes again.\"");
            
            break; 
        }

        if (isSkipped) {
            System.out.println("\n--- QUEST SKIPPED ---");
            System.out.println("Quest Summary: “The Warden of the Fractured Bough”");
            System.out.println("You confronted Lunareth in his citadel. The battle was a tragic duel against a");
            System.out.println("friend lost to grief and corruption. After a multi-phase battle that pushed you");
            System.out.println("to your limit, you shattered his bow, breaking the corruption. In his final");
            System.out.println("moments, Lunareth found peace, remembered his oath, and returned to the forest.");
        }
        
        //
        //INSERT  BATTLE
        //

        System.out.println("\nQuest Completed: “The Warden of the Fractured Bough”");
        System.out.println("Rewards:");
        System.out.println("  Ability: Lunar Requiem — “Increases damage vs. corrupted enemies by 25%.”");
        System.out.println("  Key Item: The Warden’s Fragment");
        System.out.println("  Lore Entry: The Oath Restored (Fragment IV)");
        
        System.out.println("\n(Closing Narration):");
        System.out.println("\"The forest of Veyora stands tall once more.\"");
        System.out.println("\"But the sky above it whispers of storms yet to come.\"");
        System.out.println("\"For in the silence left by Lunareth’s fall… something else begins to stir.\"");
        
        System.out.println("\nNext Quest Preview: NATION 3 — “Ashes of the Sun”");
        System.out.println("(Press Enter to continue...)");
        scan.nextLine();

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    

    public void theLastBastionQuest(Character chosen){//Nation 3 Mini Quest
    String name = chosen.getName();
    boolean isSkipped = false;
   
        while (true) {
            System.out.println(name + " stands at the edge of a massive chasm; the sky is a bruised, permanent twilight; black rock and ash cover the land;");
            System.out.println("on the far side, the Umbral Fortress, a single, colossal obsidian structure, pulses with dark energy.");
            scan.nextLine();


            System.out.print("\"The Umbral Fortress.\"");
            scan.nextLine();
            System.out.print("\"The air itself is poison. It doesn't just feel cold… it feels empty. Like a void.\"");
            scan.nextLine();
            System.out.print("\"This is where it all ended. This is where I fell.\"");
            scan.nextLine();
            System.out.print("\"Elarion is here. I can feel him… waiting.\"");
            scan.nextLine();


            System.out.println("(A flickering, unstable bridge of shadow is the only way across; as "+name+" steps onto it, the ghostly forms of THE ARCHIVIST and LUNARETH appear, their faces strained)");
            scan.nextLine();


            System.out.println("Spirit of Lunareth:");
            System.out.println("\"The fortress… it feeds on our broken Oath. Be careful, Echo. The one who guards it… he was the strongest of us.\"");
            scan.nextLine();
            
            System.out.println("Spirit of The Archivist:");
            System.out.println("\"Sarukdal. Our shield. He never broke. He never bent. What Elarion did to us... it will be nothing compared to what he did to him.\"");
            scan.nextLine();


            System.out.println("(the spirits fade; "+name+" crosses the chasm and enters the fortress)");
            scan.nextLine();
            
            System.out.println("[Scene: The Hall of Broken Oaths]");
            System.out.println("(the interior is a maze of shadowy corridors; as "+name+" walks, phantoms of the past play out on the walls—scenes of "+name+" and the three guardians)");
            System.out.println("(A phantom scene appears: the four guardians—"+name+", ARCHIVIST, LUNARETH, and a massive, armored SARUKDAL—stand together, preparing for the original sealing)");
            scan.nextLine();


            System.out.println("Phantom "+name+" (Voice):");
            System.out.println("\"No matter what, we stand together. The Oath will hold.\"");
            scan.nextLine();
            
            System.out.println("hantom Sarukdal (Voice, deep and certain):");
            System.out.println("\"I will be the shield. I will not falter. Let the void break against me. I will not fail you.\"");
            scan.nextLine();
            
            System.out.println("(the phantom scene shatters as a new, violent one replaces it: the moment "+name+" was struck down, the Oath shattering, Sarukdal screaming in rage and despair as the shadow consumes him)");
            System.out.println("(narration) \"He didn't falter. I did, I was the weak link. My failure… it didn't just break the Oath, it broke him.\"");
            scan.nextLine();
            
            System.out.println("(A deep, grating voice echoes from a chamber ahead)");
            System.out.println("\"TRAITOR. WEAKLING. YOU HAVE RETURNED.\"");
            System.out.println("(narration) \"Sarukdal.\"");
            scan.nextLine();


            System.out.println("Quest Started: The Last Bastion");
            System.out.println("Objective: Navigate the Hall of Broken Oaths and find Sarukdal, the final guardian.");
            System.out.println("Press ['s'] to skip, and [Enter] to continue.");
            String input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
        
            System.out.println("[Scene: The Chasm of Guilt]");
            System.out.println("("+name+" enters a vast chamber; in the center, on a raised dais, kneels SARUKDAL. He is a monstrous being of shadow and armor, his greatshield fused to his body, pulsating with dark energy. He is kneeling before a pulsating dark crystal.)");
            System.out.println("("+name+" finds the path blocked by a chasm; to cross, the hero must answer shadow-riddles that test their memory of the Oath)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("Shadow Voice (Sarukdal's):");
            System.out.println("\"What is the price of a shield?\"");
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println(name+":");
            System.out.println("\"Endurance.\"");
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("Shadow Voice (Sarukdal's):");
            System.out.println("\"What is the price of a memory?\"");
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println(name+":");
            System.out.println("\"Truth.\"");
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println("Shadow Voice (Sarukdal's):");
            System.out.println("\"What is the price of an Oath?\"");
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            System.out.println(name+":");
            System.out.println("\"Everything.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            
            System.out.println("(a bridge of solid shadow forms; the hero crosses)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }

            break;
        }

        scan.nextLine();
        System.out.println("You have finished the quest: The Last Bastion!");

        if(isSkipped) {
            scan.nextLine();
            System.out.println("Quest Summary");
            System.out.println("You have navigated the fortress and found Sarukdal. ");
            System.out.println("He is consumed by his corrupted duty and the memory of your failure. ");
            System.out.println("There is no path forward but through him.");
            scan.nextLine();
        }
    
        chosen.setLevel(chosen.getLevel() + 1);
        System.out.println(chosen.getName() + " leveled up to level " + chosen.getLevel() + "!");
        scan.nextLine();
        chosen.setPotionCount(chosen.getPotionCount() + 3);
        System.out.println("Reward: 3 health potions added to your inventory.");
        scan.nextLine();
        System.out.println("Current Level: "+chosen.getLevel());
        System.out.println("Current Potions: " + chosen.getPotionCount());
        scan.nextLine();
        System.out.println("You may now proceed on your journey.");
        scan.nextLine();

        System.out.println("[Scene: Before the Guardian]");
        System.out.println("(the hero steps onto the dais; Sarukdal slowly rises, his sheer size blotting out the light)");
        System.out.println("Sarukdal (voice, a deep echo of pain): ");
        System.out.println("\"You... came back. After you ran. After you failed.\"");
        scan.nextLine();
        System.out.println(name+":");
        System.out.println("\"I didn't run. I fell. I lost my memory...\"");
        scan.nextLine();
        System.out.println("Sarukdal: ");
        System.out.println("\"LIES! The Oath was bound to your memory! You CHOSE to forget! You left us to rot! I... I held... I held as long as I could... but the shield shattered from within.\"");
        scan.nextLine();
        System.out.println("(narration) \"He's right. Forgetting was a betrayal.\" \"There's no explaining this. Only answering for it.\"");
        System.out.println("(Sarukdal raises his massive, corrupted shield)");
        scan.nextLine();
        System.out.println("Sarukdal: ");
        System.out.println("\"I was the shield. Now, I am the wall. You will not pass. You will not hurt us again.\"");
    }


    public void theUnboundThroneQuest(Character chosen){//Nation 3 Main Quest
        boolean isSkipped = false;

        System.out.println(chosen.getName() + " lands the final blow. Sarukdal falls to one knee. The dark energy cracks and shatters from his armor. The shadow recedes from his eyes, revealing the true guardian beneath.");
        System.out.println("Sarukdal (voice, now quiet and clear, though weak):");
        System.out.println("\"" + chosen.getName() + "...? You... you came back. You... broke through.\"");
        scan.nextLine();


        System.out.println(chosen.getName() + " ( kneeling): ");
        System.out.println("\"I'm sorry, Sarukdal. I'm so sorry. I failed you.\"");
        scan.nextLine();


        System.out.println("Sarukdal (a small, sad smile):");
        System.out.println("\"No... I failed you. I let the despair... let him... twist my duty. I couldn't hold...\"");
        scan.nextLine();


        System.out.println(chosen.getName() + " : ");
        System.out.println("\"Where is he? Elarion.\"");
        scan.nextLine();


        System.out.println("Sarukdal (points upward):");
        System.out.println("\"The throne... where we swore the Oath. He is... unmaking it. He's using our own power against the world.\"");
        System.out.println("\"You... must... restore... the... Oath...\"");
        scan.nextLine();


        System.out.println("Sarukdal dissolves into motes of dark and light. His greatshield clatters to the ground, now cleansed of corruption. " + chosen.getName() + "'s final memory is restored.");
        scan.nextLine();


        System.out.println("Narration: \"I remember everything. The sealing. The fall. The reason.\"");
        System.out.println(":\"Elarion isn't just a being. He's the shadow we cast.\"");
        System.out.println("\"And I'm the only one left to cast it.\"");
        scan.nextLine();


        while (true) {
            System.out.println("The chamber behind Sarukdal opens, revealing a spiraling staircase of fractured light, ascending into pure darkness.");
            scan.nextLine();


            System.out.println("Narration: \"The Forgotten Throne. Where we bound him.\"");
            System.out.println(":\"Sarukdal said he's 'unmaking' the Oath. I can feel it. The world's energy is draining... siphoning... right to the room above me.\"");
            System.out.println("\"This ends now.\"");


            System.out.println("Quest Started: The Unbound Throne");
            System.out.println("Objective: Ascend the labyrinth and confront Elarion in the Forgotten Throne room.");
            System.out.println("Press ['s'] to skip, and [Enter] to continue.");
            String input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }


            System.out.println("(You are in a void, a maze of floating platforms and broken architecture from Humanas and the Elven lands. Elarion's voice echoes from everywhere and nowhere.)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("Elarion (Voice, calm, amused): ");
            System.out.println("\"My favorite guardian. The key to my prison. You came all this way... just to see what you've wrought.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("(As you navigate, you are attacked by \"Echoes of Guilt\"—shadow-phantoms of the townsfolk from Humanas and the elves from the civil war.)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("Phantom Elf:");
            System.out.println("\"You let us die!\" ");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("Phantom Human:");
            System.out.println("\"You abandoned us!\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("Narration: \"He's using my own memories against me. The people I couldn't save.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("\"He's trying to break my will. Just like he broke the others.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("\"But I'm not them. Not anymore.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }


            System.out.println("(You reach the large, circular platform. A swirling portal of shadow blocks the path forward.)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("Elarion (Voice):");
            System.out.println("“You think you are a hero? You think you have fixed anything? You are a catalyst—a walking disaster. Look. Look at the truth.”");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("(A figure steps out of the portal. It is you, just as you were at the moment of your first fall—armor broken, weapon shattered, eyes wide with terror.)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("Elarion (Voice):");
            System.out.println("\"You think you have grown beyond your failures? Look again.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("Narration: \"That moment… my fall. My fear. The part of me that gave up.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("(The shadow reaches out — the hero grips their blade tighter.)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println(chosen.getName() + ":");
            System.out.println("\"I am not running anymore.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("(The reflection shatters like glass. The path ahead opens.)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }


            System.out.println("(You step through the portal and into the throne room. It is a vast, empty void. On a throne of fractured light sits ELARION. He appears as a shifting entity of pure shadow, but as you approach, he takes on a form — a perfect, mirror image of you, made of darkness.)");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("Elarion:");
            System.out.println("\"So. The final piece returns.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            System.out.println("Narration: \"This is it. The end.\"");
            System.out.println("\"He looks… like me. The shadow I cast.\"");
            input = scan.nextLine();
            if (input.equalsIgnoreCase("s")) {
                isSkipped = true;
                break;
            }
            break;
        }

        scan.nextLine();
        System.out.println("You have finished the quest: \"The Unbound Throne\"!");

        if(isSkipped) {
            scan.nextLine();
            System.out.println("Quest Summary:");
            System.out.println("With Sarukdal's spirit finally at peace, the path to the pinnacle of the Umbral Fortress had opened.");
            System.out.println("You ascended through the void-like labyrinth — a manifestation of your own shattered memories and fears — until you reached the Forgotten Throne, where the Oath was first sworn, and confronted Elarion himself.");
            scan.nextLine();
        }

        chosen.setLevel(chosen.getLevel() + 1);
        System.out.println(chosen.getName() + " leveled up to level " + chosen.getLevel() + "!");
        scan.nextLine();
        chosen.setPotionCount(chosen.getPotionCount() + 3);
        System.out.println("Reward: 3 health potions added to your inventory.");
        scan.nextLine();
        System.out.println("Current Level: "+chosen.getLevel());
        System.out.println("Current Potions: " + chosen.getPotionCount());
        scan.nextLine();
        System.out.println("You may now proceed on your journey.");
        scan.nextLine();

        System.out.println("(Elarion stands from the throne, his shadow-form hero smiling condescendingly.)");
        scan.nextLine();
        System.out.println("Elarion: ");
        System.out.println("\"You came all this way to find yourself. And here you are. Pathetic. Weak. Broken.\" ");
        scan.nextLine();
        System.out.println("\"Your friends are gone. Your Oath is shattered. What possible hope do you have?\"");
        scan.nextLine();
        System.out.println(chosen.getName() + " (raises weapon, pendant glowing brightly):");
        scan.nextLine();
        System.out.println("\"I am the Echo.\"");
        scan.nextLine();
        System.out.println("\"I am the Guardian.\"");
        scan.nextLine();
        System.out.println("\"And I am the Oath.\"");
        scan.nextLine();

        System.out.print("\033[H\033[2J"); //clear screen
        System.out.flush();
    }

}
