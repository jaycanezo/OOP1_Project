package EchoesOfTheOath.Characters;
import EchoesOfTheOath.UI.*;

public class Ilaryx extends Character {
    private MusicPlayer bgm = new MusicPlayer();

    public Ilaryx() {
        super("Ilaryx", " VEYORA < NATION 2 > - MINI BOSS", 1400, 4);
        setIdleSprite(new Sprite("/EchoesOfTheOath/Resources/illaryx.png", 1000, 1000, 1));
        
        Sprite[] s1 = {new Sprite("/EchoesOfTheOath/Resources/ilaryx_quickshot.png", 100, 136, 5)};
        Sprite[] s2 = {new Sprite("/EchoesOfTheOath/Resources/ilaryx_huntersTrap.png", 204, 268, 5)};
        Sprite[] s3 = {new Sprite("/EchoesOfTheOath/Resources/ilaryx_arrowOfTheSilverfang.png", 464, 464, 5)};
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
                bgm.playSFX("ILARYX quickshot_noisles.wav");
                dmg = (random.nextInt(65 - 35 + 1) + 35) * getLevel();
                msg.append(getName()).append(" uses Basic Skill: Quick Shot!\n")
                   .append("Ilaryx fires a Double Tap! Two arrows blaze toward you!");
                setSkillCooldown(1, getSkillMaxCooldown(skillNumber));
                break;
            case 2:
                bgm.playSFX("ILARYX hunters trap_noisles.wav");
                dmg = (random.nextInt(105 - 80 + 1) + 80) * getLevel();
                msg.append(getName()).append(" uses Advanced Skill: Hunter's Trap!\n")
                   .append("Ilaryx activates an Invisible Snare! You feel the trap closing in around you!");
                setSkillCooldown(2, getSkillMaxCooldown(skillNumber));
                break;
            case 3:
                bgm.playSFX("ILARYX Arrow of the Silverfang_noisles.wav");
                dmg = (random.nextInt(300 - 180 + 1) + 180) * getLevel();
                msg.append(getName()).append(" uses Ultimate: Arrow of the Silverfang!\n")
                   .append("Ilaryx channels silver magic into her bow, firing a massive, charged arrow directly at you. Brace yourself!");
                setSkillCooldown(3, getSkillMaxCooldown(skillNumber));
                break;
        }
        enemy.takeDamage(dmg);
        reduceCooldowns();
        return msg.toString();
    }
}