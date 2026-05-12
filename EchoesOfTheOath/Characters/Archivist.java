package EchoesOfTheOath.Characters;
import EchoesOfTheOath.UI.*;

public class Archivist extends Character {
    private MusicPlayer bgm = new MusicPlayer();

    public Archivist() {
        super("The Archivist", " HUMANAS < NATION 1 > - MAIN BOSS", 1400, 3);
        setIdleSprite(new Sprite("/EchoesOfTheOath/Resources/Archivist.png", 400, 400, 1));
        
        Sprite[] s1 = {new Sprite("/EchoesOfTheOath/Resources/archivist_skill1.png", 128, 128, 12)};
        Sprite[] s2 = {new Sprite("/EchoesOfTheOath/Resources/archivist_skill2.png", 127, 127, 4)
            , new Sprite("/EchoesOfTheOath/Resources/archivist_skill1.png", 128, 128, 12)
        };
        Sprite[] s3 = {new Sprite("/EchoesOfTheOath/Resources/archivist_skill3.1.png", 128, 128, 8)
            , new Sprite("/EchoesOfTheOath/Resources/archivist_skill3.2.png", 128, 128, 5)
            , new Sprite("/EchoesOfTheOath/Resources/archivist_skill3.3.png", 128, 128, 6) 
        };
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
                dmg = (random.nextInt(80 - 50 + 1) + 50) * getLevel();
                msg.append(getName()).append(" uses Basic Skill: The Panoptic Eye!\n")
                   .append("A pale, shimmering after-image of you appears, and suddenly you feel a sharp cut—not on you, but on your own future shadow!");
                setSkillCooldown(1, getSkillMaxCooldown(skillNumber));
                break;
            case 2:
                dmg = (random.nextInt(180 - 120 + 1) + 120) * getLevel();
                msg.append(getName()).append(" uses Advanced Skill: Temporary Relief!\n")
                   .append("The Archivist strikes you with the crushing force of accumulated, unseen debts, leaving you reeling!");
                setSkillCooldown(2, getSkillMaxCooldown(skillNumber));
                break;
            case 3:
                dmg = (random.nextInt(400 - 250 + 1) + 250) * getLevel();
                msg.append(getName()).append(" Ultimate Skill: The Complete Indictment!\n")
                   .append("A storm of spectral scrolls engulfs you, tearing at your body as the Archivist claims you as his final possession!");
                setSkillCooldown(3, getSkillMaxCooldown(skillNumber));
                break;
        }   
        enemy.takeDamage(dmg);
        reduceCooldowns();
        return msg.toString();
    }

    @Override
    public Sprite[] getSkillSprite(int skillNumber) {
        switch (skillNumber) {
            case 1 -> bgm.playSFX("Archivist-The panoptic eye.wav");
            case 2 -> bgm.playSFX("Archivist-Temporary Relief.wav");
            case 3 -> bgm.playSFX("Archivist-The complete indictment.wav");
        }
        return super.getSkillSprite(skillNumber);
    }
}