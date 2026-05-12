package EchoesOfTheOath.Characters;
import EchoesOfTheOath.UI.*;

public class Elarion extends Character {
    private MusicPlayer bgm = new MusicPlayer();

    public Elarion() {
        super("Elarion", " DEMON REALM < NATION 3 > - MAIN BOSS", 1400, 7);
        setIdleSprite(new Sprite("/EchoesOfTheOath/Resources/elarion.png", 517, 483, 1));
        
        Sprite[] s1 = {new Sprite("/EchoesOfTheOath/Resources/Elarion_Skill1.png", 248, 198, 11)};
        Sprite[] s2 = {new Sprite("/EchoesOfTheOath/Resources/Elarion_Skill2.png", 257, 221, 12)};
        Sprite[] s3 = {new Sprite("/EchoesOfTheOath/Resources/Elarion_Skill3.png", 500, 666, 9)};
        setSkillSprites(s1, s2, s3);
    }

    @Override
    public String useSkill(int skillNumber, Character enemy) {
        if (!isSkillAvailable(skillNumber) && skillNumber != 1) skillNumber = 1;

        int dmg = 0;
        StringBuilder msg = new StringBuilder();

        switch (skillNumber) {
            case 1:
                bgm.playSFX("ELARION -- Echo Rend.wav");
                dmg = (random.nextInt(80 - 50 + 1) + 50) * getLevel();
                msg.append(getName()).append(" uses Basic Skill: Echo Rend!\n")
                   .append("Elarion unleashes a wave of distorted echoes, leaving you disoriented!");
                setSkillCooldown(1, getSkillMaxCooldown(skillNumber));
                break;
            case 2:
                bgm.playSFX("ELARION --Memory Shatter.wav");
                dmg = (random.nextInt(180 - 120 + 1) + 120) * getLevel();   
                msg.append(getName()).append(" uses Advanced Skill: Memory Shatter!\n")
                   .append("Elarion summons glowing memory shards from you and crushes them into a protective shield, forcing you to endure the impact!");
                setSkillCooldown(2, getSkillMaxCooldown(skillNumber));
                break;
            case 3:
                bgm.playSFX("ELARION -- The Final Vow.wav");
                dmg = (random.nextInt(400 - 250 + 1) + 250) * getLevel();
                msg.append(getName()).append(" uses Ultimate: The Final Vow!\n")
                   .append("A pillar of fate descends from above, engulfing you in blinding light and overwhelming your senses!");
                setSkillCooldown(3, getSkillMaxCooldown(skillNumber));
                break;
        }
        enemy.takeDamage(dmg);
        reduceCooldowns();
        return msg.toString();
    }
}