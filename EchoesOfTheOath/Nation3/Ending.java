package EchoesOfTheOath.Nation3;

import EchoesOfTheOath.Characters.Character;
import java.util.*;

public class Ending {
    public String RESET = "\033[0m";
    public String RED = "\033[31m";      // enemy / damage
    public String GREEN = "\033[32m";    // ready skill / HP
    public String BLUE = "\033[34m";     // player name
    public String YELLOW = "\033[33m";   // skill cooldown / caution
    public String PURPLE = "\033[35m";   // skills 

    Scanner scan = new Scanner(System.in);

    public void endingScene(Character chosen) {
        System.out.println("(Silence. The cast stops. The two mirror forms--you and your shadow--stare at each other.)");
        scan.nextLine();
        System.out.println("Elarion (voice, now a terrified whisper):");
        System.out.println("\"What... what are you doing? Let go! You will destroy us both!\"");
        scan.nextLine();

        System.out.println(chosen.getName() + " (voice, calm and certain): ");
        System.out.println("\"The Oath was broken. Now, it is re-sworn.\"");
        scan.nextLine();

        System.out.println("(Your pendant flares with blinding light. You pull Elarion... into your own body.)");
        scan.nextLine();
        System.out.println(chosen.getName() + " : ");
        System.out.println("\"You aren't meant to be destroyed. You are meant to be balanced.\"");
        System.out.println("\"I will be the sole guardian. I will be the cage.\"");
        scan.nextLine();

        System.out.println(
                "A blinding white light erupts, consuming the screen. The Umbral Fortress crumbles. The darkness over the land recedes.");
        scan.nextLine();

        System.out.println(
                "(You are standing in a green field, identical to the one from your dream. The sun is rising. You look at your hands. Faint, glowing lines of energy—the mark of the restored Oath—are visible on your skin.)");
        scan.nextLine();

        System.out.println("Narration: \"He's... quiet. The void is still there... but it's balanced. By me.\"");
        scan.nextLine();
        System.out.print("\"My friends are gone. Their memories... are safe. Inside me.\"");
        scan.nextLine();
        System.out.print("\"My journey to remember is over.\"");
        scan.nextLine();
        System.out.println("\"My journey as the Guardian of the Restored Oath... has just begun.\"");
        scan.nextLine();

        System.out.print("\033[H\033[2J"); // clear screen
        System.out.flush();

        System.out.println(PURPLE + "---------------------------------------------------------" + RESET);
        System.out.println(BLUE + "██████   ██  ██   █████     █████   ████    ██   █████");
        System.out.println("  ██     ██  ██   ██        ██      ██ ██   ██   ██   ██");
        System.out.println("  ██     ██████   █████     █████   ██  ██  ██   ██    ██");
        System.out.println("  ██     ██  ██   ██        ██      ██   ██ ██   ██   ██");
        System.out.println("  ██     ██  ██   █████     █████   ██    ████   █████" + RESET);
        System.out.println(PURPLE + "---------------------------------------------------------" + RESET);
        System.out.println("");
        System.out.println("Mythspire Developers");
        System.out.println("2025 All Rights Reserved");
    }
}
