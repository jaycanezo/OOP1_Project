package EchoesOfTheOath.UI;

public class DialogueManager {
    
    public static String getIntroDialogue() {
        return "Long ago, three adventurers—a brave warrior, a swift archer, and a wise mage—" +
               "traveled across worlds, earning fame for vanquishing great evils. " +
               "Their bond was unbreakable, their deeds legendary...";
    }

    public static String[] getNationDialogue(int nation) {
        return switch (nation) {
            case 1 -> new String[] {
                "Press \"Enter/Space\" to progress or 'I' for Inventory, and 'P' to visit the Shop anytime.",
                "(The hero walks along a dirt path toward the outskirts. The air is thick with the smell of wet stone and smoke.)", 
                "Humanas. From a distance, the walls look strong. Up close, they're just holding up a graveyard.",
                "Gates are broken. Guards look half-starved. This isn't a kingdom; it's a cage.",
                "(The hero enters the city. The streets are empty. No trade, no talk—only the sound of the hero's footsteps on the stone.)",
                "(A Gatekeeper sits on a crate, tiredly sharpening a rusted blade. He looks up as the hero approaches.)", 
                "“Whatever you’re looking for, traveler, we don't have it. Food’s gone. Gold’s gone. Move on.”", 
                "“Where is everyone? The streets are dead.”", 
                "Hiding. Collectors are making their rounds. If you want to keep that gear, stay out of the light. They take everything to the castle for the ‘King’s blessing.",
                "The King’s blessing. In a place this miserable, that sounds like a threat.",
                "(The hero walks into the central plaza. Rows of empty gallows line the square, and in the center stands a massive, polished gold statue of a toddler—**King Bartholomew**—shining unnaturally against the grey buildings.)",
                "(A **cloaked man** stands by the statue, watching the hero.)",
                "“Beautiful, isn't it? A golden boy for a leaden kingdom”",
                "“Why is a golden child standing in the middle of a slum?”",
                "“That’s King Bartholomew. Or at least, the version they want us to see. Every coin stolen from these streets goes into that castle to keep him 'happy.' They say he hasn't aged a day in years. They say his voice can stop your heart.”",
                "“A child king who never grows up... and steals from his own people. I need to see him for myself.”",
                "“Many do. None come back. If you’re that eager to lose your head, go to the Old Chapel. That’s where the collectors bring the 'offerings' before they head to the castle. You’ll find a way in there—if the guards don’t find you first.”",
                "(The Informant slips away into a side street.)",
                "“A child king who doesn't age. There’s something wrong with that throne.”",
                "“The 'Old Oath' is pulling at me. It’s not just curiosity anymore. I need to know who—or what—is actually wearing that crown.”",
                "(The hero reaches the Chapel. The doors are barred with heavy iron. There’s no singing inside—only the cold sound of metal coins being sorted and the occasional muffled shout.)",
                "“The Chapel is just a counting house now. But it’s the only path that leads to the castle.”",
                "“Bartholomew... let's see if you're as golden as your statue.”",
                "\"The castle is too quiet. No servants, no life. Just the sound of my own breath.\"",
                "\"They say King Bartholomew hasn't spoken a word in years, yet his decrees have starved half the nation.\"",
                "\"Someone is whispering to be standing behind the silk curtains. Someone who remembers every debt this city owes.\"",
                "(The room is massive. Thick curtains block all natural light. In the center, a golden crib sits atop a high marble dais. Two masked Attendants stand perfectly still behind it.)",
                "“You aren't on the guest list, traveler.”",
                "“I’m not here for a party. I’m here to talk to the King.”",
                "“The King doesn't 'talk.' He decides. And according to the records, your life is currently in arrears.”",
                "“Who’s writing those records? The boy can’t even hold a pen.”",
                "“His Majesty is... displeased with your tone.”",
                "(Inside the crib, the toddler sits up. His eyes are milky and vacant, glowing with a green light. He doesn't cry or scream. The massive crown on his head hums, and the shadows of the pillars begin to crawl across the floor like living ink.)",
                "\"That’s not a child’s anger. That’s a programmed response.\"",
                "\"Whatever is in that crown is using him like a conduit.\"",
                "“The decree is signed. Clear the debt.”",
                "(The Hero draws their weapon as the shadows solidify into tall, faceless sentinels.)",
                "(The shadows melt back into the floor. The red glow leaves the child’s eyes, and he collapses back into the pillows, looking small and exhausted. The attendants have vanished into the dark corners of the room.)",
                "“He’s just a kid. He didn't even know he was fighting.”",
                "(The Hero reaches into the crib. The boy doesn't pull away; he just stares, confused. The Hero’s pendant pulses with a cold, warning light.)",
                "\"The 'King' is an empty shell. He wasn't the one signing those death warrants.\"",
                "\"The Attendants weren't looking at the boy for orders—they were looking at the shadows behind the throne.\"",
                "“The library. The Gatekeeper mentioned a place where the 'debts' are kept.”",
                "“If the King isn't the one pulling the strings, then I’ve been hunting the wrong man.”",
                "(The Hero looks at a trail of black ink leading from the base of the throne toward a heavy, reinforced trapdoor in the floor.)",
                "\"The records. Every coin, every soul, every drop of blood... it all leads down there.\"",
                "\"To the one who keeps the books.\"",
                " “Your oath was to protect the innocent… find the one who stole his voice.”",
                "(A single candle flickers in the distance, revealing a pair of unblinking eyes watching the Hero...)",
                "(The Hero goes down a tight spiral staircase. The walls are stained with black ink that seems to pulse. The sound of a scratching quill fills the air, echoing like a heartbeat.)",
                "(A massive, cold vault. Rows of scrolls stretch into the dark. The Archivist sits behind a desk of stacked parchment. He doesn't move; only his eyes follow the Hero.)",
                "“You’ve caused a lot of damage upstairs. My ledger hasn't been this messy in centuries.”",
                "“The King is a puppet. You’ve been using a child to bleed this city dry.”",
                "“I don't use anyone. I record. If a King chooses to let his fear dictate the law, I simply write it down. The people of Humanas are a collection of debts. I am the only one who ensures they aren't forgotten.”",
                "“At the cost of their lives? That’s not a record. It’s a prison.”",
                "“Truth is a weight most can't carry. You should know that better than anyone, Wanderer. Your own story is nothing but torn pages and broken promises.”",
                "(The Hero winces. A flash of white light hits their mind: two shadows standing beside them—an archer, a mage—and a looming darkness that swallowed them both. The Hero grips their head.)",
                "The others... where are they? Why does it feel like I left something behind?”",
                "“Forgetfulness is a mercy. But the ink remembers. Let’s see what yours says about your ending.”",
                "(The Archivist stands, his robes unfurling like thousands of sharp scrolls. The Hero is panting, leaning on their weapon.)",
                "“The records demand completion! You cannot run from a debt you’ve already signed!”",
                "(A scroll flies past the Hero’s face. For a split second, they see a word written in glowing light: *ELARION*.)",
                "“That name... I’ve heard it before. In the dream. We were supposed to... we were supposed to bind it.”",
                "(The Hero’s pendant glows with a violent, flickering light. A voice, distant and distorted, echoes in their mind.)",
                "“We vow to bind the darkness... within ourselves...”",
                "“The oath... it’s not just a story. It’s why I’m here. I didn't just survive. I failed.”",
                "(The Hero’s weapon ignites with a raw, unstable energy. They lung forward, driving the blade through the center of the Archivist’s desk and into his chest.)",
                "(The library begins to dissolve into ash. The scrolls turn into black flakes that drift toward the ceiling.)",
                "“You think... freeing them... changes anything? You only broke... one link... in a chain... you helped forge.”",
                "“Maybe. But I’m the one who’s going to break the rest.”",
                "(The Archivist vanishes. The Hero picks up a single blank page. Their hand is shaking.)",
                "\"The library is gone, but the weight in my chest is heavier than ever.\"",
                "\"I saw faces in those scrolls. Faces I should know. An archer... a mage...\"",
                "\"Whatever we promised to do, we didn't finish it. This nation was just the start.\"",
                "(The Hero looks up. The sun shines through the rubble. Far off in the distance, across the sea, a purple forest glows on the horizon.)",
                "“Veyora. The Elves. If the fragments of what I lost are there... I have to find them.”",
                "(The screen fades to black as the Hero walks toward the light.)"
            };
            case 2 -> new String[] { "placeholder dialogue...", "To be continued..." };
            case 3 -> new String[] { "placeholder dialogue...", "To be continued..." };
            default -> new String[] { "End of the road for now." };
        };
    }
}