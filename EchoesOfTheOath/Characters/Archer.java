package EchoesOfTheOath.Characters;
import EchoesOfTheOath.UI.*;

public class Archer extends Character {
    private MusicPlayer bgm = new MusicPlayer();

    public Archer() {
        super("Archer", 1600, 1, "Archer");
        setIdleSprite(new Sprite("/EchoesOfTheOath/Resources/Archer.png", 264, 330, 12));
        
        Sprite[] s1 = {new Sprite("/EchoesOfTheOath/Resources/archer_skill1.png", 176, 144, 8)};
        Sprite[] s2 = {new Sprite("/EchoesOfTheOath/Resources/archer_skill2.png", 159, 239, 10)};
        Sprite[] s3 = {new Sprite("/EchoesOfTheOath/Resources/archer_skill3.png", 143, 213, 12)};
        setSkillSprites(s1, s2, s3);
    }   

    @Override
    public String useSkill(int skillNumber, Character enemy) {
        if (skillNumber < 1 || skillNumber > 3) 
            return "Invalid skill number.\n";

        if (!isSkillAvailable(skillNumber)) {
            return "Skill on cooldown! " + getSkillCooldown(skillNumber) + " turn(s) remaining.\n";
        }

        int dmg = 0;
        int bonus = getSkillBonus(skillNumber);
        StringBuilder msg = new StringBuilder();

        switch (skillNumber) {
            case 1:
                bgm.playSFX("Archer_Piercingshot.wav");
                dmg = ((random.nextInt(45 - 15 + 1) + 15 + 25) * getLevel()) + bonus;
                msg.append(getName()).append(" uses Basic Skill: Piercing Shot!\n")
                   .append("You shoot a swift arrow at your foe!");
                setSkillCooldown(1, getSkillMaxCooldown(skillNumber));
                break;
            case 2:
                bgm.playSFX("Archer_VolleyofNature.wav");
                dmg = ((random.nextInt(95 - 70 + 1) + 70 + 20) * getLevel()) + bonus;
                msg.append(getName()).append(" uses Advanced Skill: Volley of Nature!\n")
                   .append("You fire multiple arrows, raining them down on your enemy!");
                setSkillCooldown(2, getSkillMaxCooldown(skillNumber));
                break;
            case 3:
                bgm.playSFX("Archer_Nature'swrath.wav");
                dmg = ((random.nextInt(230 - 105 + 1) + 105 + 80) * getLevel()) + bonus;
                msg.append(getName()).append(" uses Ultimate: Nature's Wrath!\n")
                   .append("You unleash four guiding arrows, striking your enemy with precision!");
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
        return switch (skillNumber) {
            case 1 -> "Piercing Shot";
            case 2 -> "Volley of Nature";
            case 3 -> "Nature's Wrath";
            default -> "Unknown Skill";
        };
    }

    @Override
    public String getSkillDamageRange(int skillNumber) {
        int lvl = getLevel();
        int b = getSkillBonus(skillNumber);
        return switch (skillNumber) {
            case 1 -> (15*lvl + 25*lvl + b) + " - " + (45*lvl + 25*lvl + b);
            case 2 -> (70*lvl + 20*lvl + b) + " - " + (95*lvl + 20*lvl + b);
            case 3 -> (105*lvl + 80*lvl + b) + " - " + (230*lvl + 80*lvl + b);
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
