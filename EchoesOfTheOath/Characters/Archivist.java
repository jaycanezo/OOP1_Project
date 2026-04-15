package EchoesOfTheOath.Characters;
import EchoesOfTheOath.UI.*;

public class Archivist extends Character {
    private MusicPlayer bgm = new MusicPlayer();

    public Archivist() {
        super("The Archivist", " HUMANAS < NATION 1 > - MAIN BOSS", 1400, 4);
        setIdleSprite(new Sprite("/EchoesOfTheOath/Resources/Archivist.png", 400, 400, 1));
        
        Sprite[] s1 = {new Sprite("/EchoesOfTheOath/Resources/archivist_skill1.png", 151, 187, 12)};
        Sprite[] s2 = {new Sprite("/EchoesOfTheOath/Resources/archivist_skill2.png", 93, 42, 11)};
        Sprite[] s3 = {new Sprite("/EchoesOfTheOath/Resources/archivist_skill3.png", 308, 66, 9)};
        setSkillSprites(s1, s2, s3);
    }

    @Override 
    public String useSkill(int skillNumber, Character enemy) {
        if (!isSkillAvailable(skillNumber) && skillNumber != 1) skillNumber = 1;

        int dmg = 0;
        StringBuilder msg = new StringBuilder();

        switch (skillNumber) {
            case 1:
                bgm.playSFX("Archivist-The panoptic eye.wav");
                dmg = (random.nextInt(65 - 35 + 1) + 35) * getLevel();
                msg.append(getName()).append(" uses Basic Skill: The Panoptic Eye!\n")
                   .append("A shimmering after-image appears, and you feel a sharp cut on your shadow.");
                setSkillCooldown(1, 0);
                break;
            case 2:
                bgm.playSFX("Archivist-Temporary Relief.wav");
                dmg = (random.nextInt(105 - 80 + 1) + 80) * getLevel();
                msg.append(getName()).append(" uses Advanced Skill: Temporary Relief!\n")
                   .append("The Archivist strikes you with the crushing force of unseen debts.");
                setSkillCooldown(2, 2);
                break;
            case 3:
                bgm.playSFX("Archivist-The complete indictment.wav");
                dmg = (random.nextInt(300 - 180 + 1) + 180) * getLevel();
                msg.append(getName()).append(" Ultimate Skill: The Complete Indictment!\n")
                   .append("A storm of spectral scrolls engulfs you, tearing at your body.");
                setSkillCooldown(3, 3);
                break;
        }   
        enemy.takeDamage(dmg);
        reduceCooldowns();
        return msg.toString();
    }
}