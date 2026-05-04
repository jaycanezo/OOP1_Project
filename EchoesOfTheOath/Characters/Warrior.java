package EchoesOfTheOath.Characters;
import EchoesOfTheOath.UI.*;

public class Warrior extends Character {
    private MusicPlayer bgm = new MusicPlayer();
        
    public Warrior() {
        super("Warrior", 1800, 1, "Warrior");
        setIdleSprite(new Sprite("/EchoesOfTheOath/Resources/Warrior.png", 300, 297, 12));
        
        Sprite[] s1 = {new Sprite("/EchoesOfTheOath/Resources/Warrior_Slash.png", 128, 128, 9)};
        Sprite[] s2 = {new Sprite("/EchoesOfTheOath/Resources/Warrior_CrimsonStrike.png", 128, 128, 7)};
        Sprite[] s3 = {
            new Sprite("/EchoesOfTheOath/Resources/Warrior_BladeQuake1.png", 256, 128, 14),
            new Sprite("/EchoesOfTheOath/Resources/Warrior_BladeQuake2.png", 256, 128, 11)
        };
        setSkillSprites(s1, s2, s3);
    }

    @Override
    public String useSkill(int skillNumber, Character enemy) {
        if (skillNumber < 1 || skillNumber > 3) 
            return "Invalid Skill Number.\n";
        
        if (!isSkillAvailable(skillNumber)) {
            return "Skill is on cooldown! " + getSkillCooldown(skillNumber) + " turn(s) remaining.";
        }

        int dmg = 0;
        int bonus = getSkillBonus(skillNumber);
        StringBuilder msg = new StringBuilder();

        switch (skillNumber) {
            case 1:
                bgm.playSFX("Slash.wav");
                dmg = ((random.nextInt(45 - 15 + 1) + 15 + 15) * getLevel()) + bonus;
                msg.append(getName()).append(" uses Basic Skill: Slash!\n")
                   .append("You swing your sword in a swift slash toward your enemy, dealing damage!");
                setSkillCooldown(1, getSkillMaxCooldown(skillNumber));
                break;
            case 2:
                bgm.playSFX("crimsonStrike.wav");
                dmg = ((random.nextInt(90 - 65 + 1) + 65 + 10) * getLevel()) + bonus;
                msg.append(getName()).append(" uses Advanced Skill: Crimson Strike!\n")
                   .append("You perform a heavy, sweeping strike, landing a fierce blow on your enemy!");
                setSkillCooldown(2, getSkillMaxCooldown(skillNumber));
                break;
            case 3:
                bgm.playSFX("bladeQuake.wav");
                dmg = ((random.nextInt(255 - 135 + 1) + 135 + 40) * getLevel()) + bonus;
                msg.append(getName()).append(" uses Ultimate: Blade Quake!\n")
                   .append("You unleash devastating strength in a massive ground-splitting attack on your enemy!");
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
            case 1 -> "Slash";
            case 2 -> "Crimson Strike";
            case 3 -> "Blade Quake";
            default -> "Unknown Skill";
        };
    }

    @Override
    public String getSkillDamageRange(int skillNumber) {
        int lvl = getLevel();
        int b = getSkillBonus(skillNumber);
        return switch(skillNumber) {
            case 1 -> (15*lvl + 15*lvl + b) + " - " + (45*lvl + 15*lvl + b);
            case 2 -> (65*lvl + 10*lvl + b) + " - " + (90*lvl + 10*lvl + b);
            case 3 -> (135*lvl + 40*lvl + b) + " - " + (255*lvl + 40*lvl + b);
            default -> "0";
        };
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