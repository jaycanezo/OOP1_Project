package EchoesOfTheOath.Characters;
import EchoesOfTheOath.UI.*;

public class babyM extends Character {
    private MusicPlayer bgm = new MusicPlayer();

    public babyM() {
        super("King Bartholomew Monarch (\"Baby M\")", " HUMANAS < NATION 1 > - MINI BOSS", 1400, 1);
        setIdleSprite(new Sprite("/EchoesOfTheOath/Resources/BabyM.png", 862, 725, 1));
        
        Sprite[] s1 = {new Sprite("/EchoesOfTheOath/Resources/BabyM_TantrumToss.png", 128, 128, 12)};
        Sprite[] s2 = {new Sprite("/EchoesOfTheOath/Resources/BabyM_FeignedFaint.png", 128, 128, 11)};
        Sprite[] s3 = {new Sprite("/EchoesOfTheOath/Resources/BabyM_EnforcedDecree.png", 192, 128, 9)};
        setSkillSprites(s1, s2, s3);
    }

    @Override
    public String useSkill(int skillNumber, Character enemy) {
        if (!isSkillAvailable(skillNumber) && skillNumber != 1) 
            skillNumber = 1;

        int dmg = 0;
        StringBuilder msg = new StringBuilder();

        switch (skillNumber) {
            case 1:
                bgm.playSFX("BabyM-Tantrum Toss.wav");
                dmg = (random.nextInt(80 - 50 + 1) + 50) * getLevel();
                msg.append(getName()).append(" uses Basic Skill: Tantrum Toss!\n")
                   .append("The infant king shrieks furiously and hurls his golden rattle straight at you!");
                setSkillCooldown(1, getSkillMaxCooldown(skillNumber));
                break;
            case 2:
                bgm.playSFX("BabyM-Feigned Faint.wav");
                dmg = (random.nextInt(180 - 120 + 1) + 120) * getLevel();
                msg.append(getName()).append(" uses Advanced Skill: Feigned Faint!\n")
                   .append("Baby M suddenly collapses, tricking you into lowering your guard before striking you with a vicious surprise attack!");
                setSkillCooldown(2, getSkillMaxCooldown(skillNumber));
                break;
            case 3:
                bgm.playSFX("BabyM-Enforced Decree.wav");
                dmg = (random.nextInt(400 - 250 + 1) + 250) * getLevel();
                msg.append(getName()).append(" uses Ultimate: Enforced Decree!\n")
                   .append("The infant King freezes mid-air as if controlled by an unseen force, then hurtles toward you with unstoppable, mechanical precision!");
                setSkillCooldown(3, getSkillMaxCooldown(skillNumber));
                break;
        }
        enemy.takeDamage(dmg);
        reduceCooldowns();
        return msg.toString();
    }
}