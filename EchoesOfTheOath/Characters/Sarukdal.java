package EchoesOfTheOath.Characters;
import EchoesOfTheOath.UI.*;

public class Sarukdal extends Character {
    private MusicPlayer bgm = new MusicPlayer();

    public Sarukdal() {
        super("Sarukdal", " DEMON REALM < NATION 3 > - MINI BOSS", 1400, 10);
        setIdleSprite(new Sprite("/EchoesOfTheOath/Resources/Sarukdal.png", 500, 500, 1));
        
        Sprite[] s1 = {new Sprite("/EchoesOfTheOath/Resources/Sarukdal_Skill1.png", 128, 128, 8)};
        Sprite[] s2 = {new Sprite("/EchoesOfTheOath/Resources/Sarukdal_Skill2.png", 128, 128, 8)};
        Sprite[] s3 = {new Sprite("/EchoesOfTheOath/Resources/Sarukdal_Skill3.png", 128, 128, 8)};
        setSkillSprites(s1, s2, s3);
    }

    @Override
    public String useSkill(int skillNumber, Character enemy) {
        if (!isSkillAvailable(skillNumber) && skillNumber != 1) skillNumber = 1;

        int dmg = 0;
        StringBuilder msg = new StringBuilder();

        switch (skillNumber) {
            case 1:
                bgm.playSFX("SARUKDAL -- Warden’s Grasp.wav");
                dmg = (random.nextInt(65 - 35 + 1) + 35) * getLevel();
                msg.append(getName()).append(" uses Basic Skill: Warden's Grasp!\n")
                   .append("Sarukdal hurls spectral chains at you, attempting to seize you.");
                setSkillCooldown(1, 0);
                break;
            case 2:
                bgm.playSFX("SARUKDAL -- Abyssal Lament.wav");
                dmg = (random.nextInt(105 - 80 + 1) + 80) * getLevel();
                msg.append(getName()).append(" uses Advanced Skill: Abyssal Lament!\n")
                   .append("Dark flames spread across the battlefield, scorching everything.");
                setSkillCooldown(2, 2);
                break;
            case 3:
                bgm.playSFX("SARUKDAL -- Judgement of the Forsaken.wav");
                dmg = (random.nextInt(300 - 180 + 1) + 180) * getLevel();
                msg.append(getName()).append(" uses Ultimate: Judgment of the Forsaken!\n")
                   .append("Glowing fire blades descend from the sky with devastating force.");
                setSkillCooldown(3, 3);
                break;
        }
        enemy.takeDamage(dmg);
        reduceCooldowns();
        return msg.toString();
    }
}