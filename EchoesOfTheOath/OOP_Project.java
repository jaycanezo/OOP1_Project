package EchoesOfTheOath;

import java.util.Scanner;

import EchoesOfTheOath.Characters.*;
import EchoesOfTheOath.Characters.Character;

import EchoesOfTheOath.Nation1.*;
import EchoesOfTheOath.Nation2.*;
import EchoesOfTheOath.Nation3.*;

public class OOP_Project {
    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);

        Character chosen = Intro.main(args);

        Storyline storyline = new Storyline();

        // ============================================================NATION
        // 1============================================================
        MiniQuest1 miniQuest1 = new MiniQuest1();
        MainQuest1 mainQuest1 = new MainQuest1();

        miniQuest1.quest1HumanasQuest(chosen);// Nation 1 Mini Quest

        babyM babyM = new babyM();// Nation 1 Mini Boss
        BattleLogic.battleLogic(chosen, babyM);

        mainQuest1.VeinsOfHumanas(chosen);// Nation 1 Main Quest

        Archivist archivist = new Archivist();// Nation 1 Main Boss
        BattleLogic.battleLogic(chosen, archivist);

        // ============================================================NATION
        // 2============================================================
        MiniQuest2 miniQuest2 = new MiniQuest2();
        MainQuest2 mainQuest2 = new MainQuest2();

        miniQuest2.whispersBeneathTheBoughs(chosen);

        Ilaryx ilaryx = new Ilaryx();// Nation 2 Mini Boss
        BattleLogic.battleLogic(chosen, ilaryx);

        mainQuest2.theRootsOfDespair(chosen);// Nation 2 Main Quest

        Lunareth lunareth = new Lunareth();// Nation 2 Main Boss
        BattleLogic.battleLogic(chosen, lunareth);

        // ============================================================NATION
        // 3============================================================
        MiniQuest3 miniQuest3 = new MiniQuest3();
        MainQuest3 mainQuest3 = new MainQuest3();
        Ending ending = new Ending();
        miniQuest3.BrokenOathTrial();// Nation 3 Mini Quest

        Sarukdal sarukdal = new Sarukdal();// Nation 3 Mini Boss
        BattleLogic.battleLogic(chosen, sarukdal);

        mainQuest3.theUnboundThroneQuest(chosen);// Nation 3 Main Quest

        Elarion elarion = new Elarion();// Nation 3 Main Boss
        BattleLogic.battleLogic(chosen, elarion);

        ending.endingScene(chosen);

        scan.close();
    }
}
