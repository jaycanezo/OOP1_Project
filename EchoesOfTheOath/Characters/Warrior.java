package EchoesOfTheOath.Characters;
import EchoesOfTheOath.UI.*;

public class Warrior extends Character {
    private MusicPlayer bgm = new MusicPlayer();
        
    public Warrior() {
        super("Warrior", 1800, 1, "Warrior");
        setIdleSprite(new Sprite("/EchoesOfTheOath/Resources/Warrior.png", 256, 256, 16));
        
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
        if (skillNumber < 1 || skillNumber > 3) return "Invalid Skill Number.\n";
        if (!isSkillAvailable(skillNumber)) return "Skill is on cooldown! " + getSkillCooldown(skillNumber) + " turn(s) remaining.";

        int dmg = 0;
        int bonus = getSkillBonus(skillNumber);
        StringBuilder msg = new StringBuilder();

        switch (skillNumber) {
            case 1:
                // Weaker: 100 to 150 damage
                dmg = ((random.nextInt(150 - 100 + 1) + 100) * getLevel()) + bonus;
                msg.append(getName()).append(" uses Basic Skill: Slash!\n").append("You swing your sword in a swift slash toward your enemy, dealing damage!");
                setSkillCooldown(1, getSkillMaxCooldown(skillNumber));
                break;
            case 2:
                // Weaker: 200 to 300 damage
                dmg = ((random.nextInt(300 - 200 + 1) + 200) * getLevel()) + bonus;
                msg.append(getName()).append(" uses Advanced Skill: Crimson Strike!\n").append("You perform a heavy, sweeping strike, landing a fierce blow on your enemy!");
                setSkillCooldown(2, getSkillMaxCooldown(skillNumber));
                break;
            case 3:
                // Weaker: 400 to 600 damage
                dmg = ((random.nextInt(600 - 400 + 1) + 400) * getLevel()) + bonus;
                msg.append(getName()).append(" uses Ultimate: Blade Quake!\n").append("You unleash devastating strength in a massive ground-splitting attack on your enemy!");
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
            case 1 -> (100*lvl + b) + " - " + (150*lvl + b);
            case 2 -> (200*lvl + b) + " - " + (300*lvl + b);
            case 3 -> (400*lvl + b) + " - " + (600*lvl + b);
            default -> "0";
        };
    }

    @Override
    public String displaySkills() {
        StringBuilder msg = new StringBuilder();
        for (int i = 1; i <= 3; i++) {
            msg.append(i).append(". ").append(getSkillName(i)).append(" | Damage: ").append(getSkillDamageRange(i)).append(" | Cooldown: ").append(getSkillCooldown(i)).append("\n");
        }
        return msg.toString();
    }

    @Override
    public Sprite[] getSkillSprite(int skillNumber) {
        switch (skillNumber) {
            case 1 -> bgm.playSFX("Slash.wav");
            case 2 -> bgm.playSFX("crimsonStrike.wav");
            case 3 -> bgm.playSFX("bladeQuake.wav");
        }
        return super.getSkillSprite(skillNumber);
    }
}