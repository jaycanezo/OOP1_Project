package EchoesOfTheOath;

import EchoesOfTheOath.Characters.*;
import EchoesOfTheOath.Characters.Character;
import EchoesOfTheOath.Nation1.*;
import EchoesOfTheOath.Nation2.*;
import EchoesOfTheOath.Nation3.*;
import EchoesOfTheOath.Resources.MusicPlayer;
import EchoesOfTheOath.UI.GameWindow;

import java.util.Scanner;

public class OOP_Project {
    public static void main(String[] args) throws InterruptedException {
        new GameWindow();
        
        MusicPlayer bgm = new MusicPlayer();
        bgm.playMusic("intro_bgm.wav");

        Scanner scan = new Scanner(System.in);

        Character chosen = Intro.main(args);

        bgm.stopMusic();

        
        // ============================================================NATION
        // 1============================================================
        
        bgm.playMusic("nation1_bgm.wav");
        MiniQuest1 miniQuest1 = new MiniQuest1();
        MainQuest1 mainQuest1 = new MainQuest1();

        miniQuest1.quest1HumanasQuest(chosen);// Nation 1 Mini Quest
        bgm.stopMusic();

        bgm.playMusic("nation1_fight_bgm.wav");
        babyM babyM = new babyM();// Nation 1 Mini Boss
        //BattleLogic.battleLogic(chosen, babyM);
        bgm.stopMusic();

        bgm.playMusic("nation1_bgm2.wav");
        mainQuest1.VeinsOfHumanas(chosen);// Nation 1 Main Quest
        bgm.stopMusic();

        bgm.playMusic("nation1_fight_bgm2.wav");
        Archivist archivist = new Archivist();// Nation 1 Main Boss
        //BattleLogic.battleLogic(chosen, archivist);
        bgm.stopMusic();

        
        // ============================================================NATION
        // 2============================================================
        bgm.playMusic("nation2_bgm.wav");
        MiniQuest2 miniQuest2 = new MiniQuest2();
        MainQuest2 mainQuest2 = new MainQuest2();

        miniQuest2.whispersBeneathTheBoughs(chosen);
        bgm.stopMusic();

        bgm.playMusic("nation2_fight_bgm.wav");
        Ilaryx ilaryx = new Ilaryx();// Nation 2 Mini Boss
        //BattleLogic.BattleLogic(chosen, ilaryx);
        bgm.stopMusic();

        bgm.playMusic("nation2_bgm2.wav");
        mainQuest2.theRootsOfDespair(chosen);// Nation 2 Main Quest
        bgm.stopMusic();

        bgm.playMusic("nation2_fight_bgm.wav");
        Lunareth lunareth = new Lunareth();// Nation 2 Main Boss
        //BattleLogic.battleLogic(chosen, lunareth);
        bgm.stopMusic();

        // ============================================================NATION
        // 3============================================================
        bgm.playMusic("nation3_bgm.wav");
        MiniQuest3 miniQuest3 = new MiniQuest3();
        MainQuest3 mainQuest3 = new MainQuest3();
        Ending ending = new Ending();
        miniQuest3.theLastBastion(chosen);// Nation 3 Mini Quest
        bgm.stopMusic();

        bgm.playMusic("nation3_fight_bgm.wav");
        Sarukdal sarukdal = new Sarukdal();// Nation 3 Mini Boss
        //BattleLogic.battleLogic(chosen, sarukdal);
        bgm.stopMusic();

        bgm.playMusic("nation3_bgm.wav");
        mainQuest3.theUnboundThroneQuest(chosen);// Nation 3 Main Quest
        bgm.stopMusic();

        bgm.playMusic("nation3_fight_bgm2.wav");
        Elarion elarion = new Elarion();// Nation 3 Main Boss
        //BattleLogic.battleLogic(chosen, elarion);
        bgm.stopMusic();

        bgm.playMusic("ending_bgm.wav");
        ending.endingScene(chosen);
        bgm.stopMusic();

        scan.close();
    }
}
