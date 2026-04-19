package EchoesOfTheOath.Characters;
import EchoesOfTheOath.UI.*;

public class Mage extends Character {
    private MusicPlayer bgm = new MusicPlayer();

    public Mage() {
        super("Mage", 1500, 1, "Mage");
        setIdleSprite(new Sprite("/EchoesOfTheOath/Resources/Mage.png", 200, 200, 25));
        
        Sprite[] s1 = {new Sprite("/EchoesOfTheOath/Resources/Mage_Fireball.png", 128, 128, 7)};
        Sprite[] s2 = {new Sprite("/EchoesOfTheOath/Resources/Mage_HeatfireSurge.png", 128, 128, 12)};
        Sprite[] s3 = {
            new Sprite("/EchoesOfTheOath/Resources/Mage_AstralCataclysm1.png", 192, 128, 12),
            new Sprite("/EchoesOfTheOath/Resources/Mage_AstralCataclysm2.png", 128, 128, 15),
            new Sprite("/EchoesOfTheOath/Resources/Mage_AstralCataclysm3.png", 128, 128, 7)
        };
        setSkillSprites(s1, s2, s3);
    }

    @Override
    public String useSkill(int skillNumber, Character enemy) {
        if (skillNumber < 1 || skillNumber > 3) return "Invalid Skill.\n";
        if (!isSkillAvailable(skillNumber)) {
            return "Skill is on cooldown! " + getSkillCooldown(skillNumber) + " turn(s) remaining.";
        }

        int dmg = 0;
        int bonus = getSkillBonus(skillNumber);
        StringBuilder msg = new StringBuilder();
        
        switch (skillNumber) {
            case 1:
                bgm.playSFX("Mage_Fire_ball.wav");
                dmg = ((random.nextInt(550 - 530 + 1) + 530 + 1000) * getLevel()) + bonus;
                msg.append(getName()).append(" uses Basic Skill: Fireball!\n")
                   .append("You hurl a blazing fireball at your enemy.");
                setSkillCooldown(1, getSkillMaxCooldown(skillNumber));
                break;
            case 2:
                bgm.playSFX("Mage_Heatfire_Surge.wav");
                dmg = ((random.nextInt(800 - 765 + 1) + 765 + 765) * getLevel()) + bonus;
                msg.append(getName()).append(" uses Advanced Skill: Heatfire Surge!\n")
                   .append("You unleash a surge of intense flames.");
                setSkillCooldown(2, getSkillMaxCooldown(skillNumber));
                break;
            case 3:
                bgm.playSFX("Mage_Astral_Cataclysm.wav");
                dmg = (1580 * getLevel()) + bonus;
                msg.append(getName()).append(" uses Ultimate: Astral Cataclysm!\n")
                   .append("You summon a massive fiery rock from the heavens.");
                setSkillCooldown(3, getSkillMaxCooldown(skillNumber));
                break;
        }

        enemy.takeDamage(dmg);
        markSkillUsed(skillNumber);
        reduceCooldowns();
        msg.append("\n").append(enemy.getName()).append(" took ").append(dmg).append(" damage!");
        return msg.toString();
    }

    @Override
    public String getSkillName(int skillNumber) {
        return switch(skillNumber) {
            case 1 -> "Fire Ball";
            case 2 -> "Heatfire Surge";
            case 3 -> "Astral Cataclysm";
            default -> "Unknown Skill";
        };
    }

    @Override
    public String getSkillDamageRange(int skillNumber) {
        int lvl = getLevel();
        int b = getSkillBonus(skillNumber);
        return switch(skillNumber) {
            case 1 -> (1530*lvl + b) + " - " + (1550*lvl + b);
            case 2 -> (1530*lvl + b) + " - " + (1565*lvl + b);
            case 3 -> String.valueOf(1580*lvl + b);
            default -> "0";
        };
    }

    public String displayCharacterInfo() {
        StringBuilder msg = new StringBuilder();

        msg.append("\nWielders of ancient knowledge, Mages command the elements and arcane forces. Fragile in body but unmatched in power, they bend magic at will to devastate their enemies from a distance.\n");

        msg.append("SKILLS:\n");
       
        for (int i = 1; i <= 3; i++) {
            String skillName = getSkillName(i);
            String damageRange = getSkillDamageRange(i);
            msg.append("(" + i + ") " + skillName + "\nDamage: " + damageRange + "\n");
        }

        return msg.toString();
    }

    @Override
    public String displaySkills() {
        StringBuilder msg = new StringBuilder();
        for (int i = 1; i <= 3; i++) {
            msg.append(i).append(". ").append(getSkillName(i))
               .append(" | Damage: ").append(getSkillDamageRange(i))
               .append(" | Cooldown: ").append(getSkillCooldown(i)).append("\n");
        }
        return msg.toString();
    }
}