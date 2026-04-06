package EchoesOfTheOath.Characters;
import EchoesOfTheOath.UI.*;

public class babyM extends Character{
    MusicPlayer bgm = new MusicPlayer();

    public babyM(){
        super("King Bartholomew Monarch (\"Baby M\")", " HUMANAS < NATION 1 > - MINI BOSS", 1400, 2);
        this.idleSprite = new Sprite("/EchoesOfTheOath/Resources/BabyM.png", 862, 725, 1);
        this.skill1Sprite = new Sprite[]{new Sprite("/EchoesOfTheOath/Resources/BabyM_TantrumToss.png", 128, 128, 12)};
        this.skill2Sprite = new Sprite[]{new Sprite("/EchoesOfTheOath/Resources/BabyM_FeignedFaint.png", 128, 128, 11)};
        this.skill3Sprite = new Sprite[]{new Sprite("/EchoesOfTheOath/Resources/BabyM_EnforcedDecree.png", 192, 128, 9)};
    }

    
    @Override
    public String useSkill(int skillNumber, Character enemy) {
        int[] cd = getSkillCooldowns(); // get cooldown array
        int dmg = 0;
        StringBuilder msg = new StringBuilder();

        //for skill defaulting if random skill is on cooldown
        if (cd[skillNumber - 1] > 0 && skillNumber != 1) {
            skillNumber = 1; //defaults to basic atk
        }

        switch (skillNumber) {
            case 1:
                bgm.playSFX("BabyM-Tantrum Toss.wav");
                dmg = (random.nextInt(65 - 35 + 1) + 35) * getLevel();
                msg.append(getName()).append(" uses Basic Skill: Tantrum Toss!\n")
                .append("The infant king shrieks furiously and hurls his golden rattle straight at you.");
                break;
            case 2:
                bgm.playSFX("BabyM-Feigned Faint.wav");
                dmg = (random.nextInt(105 - 80 + 1) + 80) * getLevel();
                msg.append(getName()).append(" uses Advanced Skill: Feigned Faint!\n")
                .append("Baby M suddenly collapses, tricking you into lowering your guard before striking you with a vicious surprise attack.");

                cd[skillNumber - 1] = 2;
                break;
            case 3:
                bgm.playSFX("BabyM-Enforced Decree.wav");
                dmg = (random.nextInt(300 - 180 + 1) + 180) * getLevel();
                msg.append(getName()).append(" uses Ultimate: Enforced Decree!\n")
                .append("The infant King freezes mid-air as if controlled by an unseen force, then hurtles toward you with unstoppable, mechanical precision.");

                cd[skillNumber - 1] = 3;
                break;
        }
        enemy.takeDamage(dmg);
        reduceCooldowns();

        return msg.toString();
    }
}
