package EchoesOfTheOath.Characters;
import EchoesOfTheOath.UI.*;

public class Lunareth extends Character {
    private MusicPlayer bgm = new MusicPlayer();

    public Lunareth() {
        super("Lunareth", " VEYORA < NATION 2 > - MAIN BOSS", 1400, 5);
        setIdleSprite(new Sprite("/EchoesOfTheOath/Resources/lunareth.png", 1000, 1000, 1));
        
        Sprite[] s1 = {new Sprite("/EchoesOfTheOath/Resources/lunareth_skill1.png", 1000, 1000, 9)};
        Sprite[] s2 = {new Sprite("/EchoesOfTheOath/Resources/lunareth_skill2.png", 1000, 1000, 9)};
        Sprite[] s3 = {new Sprite("/EchoesOfTheOath/Resources/lunareth_skill3.png", 1000, 1000, 9)};
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
                bgm.playSFX("LUNARETH moonpierce shot.wav");
                dmg = (random.nextInt(80 - 50 + 1) + 50) * getLevel();
                msg.append(getName()).append(" uses Basic Skill: Moonpierce Shot!\n")
                   .append("Lunareth fires a luminous arrow imbued with lunar energy straight at you!");
                setSkillCooldown(1, getSkillMaxCooldown(skillNumber));
                break;
            case 2:
                bgm.playSFX("LUNARETH Binding Roots_noisles.wav");
                dmg = (random.nextInt(180 - 120 + 1) + 120) * getLevel();
                msg.append(getName()).append(" uses Advanced Skill: Binding Roots!\n")
                   .append("Enchanted shoot up from the ground, ensnaring you and pulling you down!");
                setSkillCooldown(2, getSkillMaxCooldown(skillNumber));
                break;
            case 3:
                bgm.playSFX("LUNARETH Eclipse Volley_noisles.wav");
                dmg = (random.nextInt(400 - 250 + 1) + 250) * getLevel();
                msg.append(getName()).append(" uses Ultimate: Eclipse Volley!\n")
                   .append("Lunareth leaps into the air, summoning the eclipse to rain a storm of arrows directly on you. Brace yourself!");
                setSkillCooldown(3, getSkillMaxCooldown(skillNumber));
                break;
        }
        enemy.takeDamage(dmg);
        reduceCooldowns();
        return msg.toString();
    }
}