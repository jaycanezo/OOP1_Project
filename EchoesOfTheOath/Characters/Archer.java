package EchoesOfTheOath.Characters;

import EchoesOfTheOath.Resources.MusicPlayer;

public class Archer extends Character {

    private boolean[] isUsed = new boolean[3];
    private MusicPlayer bgm = new MusicPlayer();

    public Archer() {
        super("Archer", 1300, 1);
    }

    // ---------------- USE SKILL ----------------
    // Returns a String describing what happened for the GUI to display
    @Override
    public String useSkill(int skillNumber, Character enemy) {
        if (skillNumber < 1 || skillNumber > 3) return "Invalid skill number.\n";

        if (!isSkillAvailable(skillNumber)) {
            return "Skill is on cooldown! " + getSkillCooldown(skillNumber) + " turn(s) remaining.\n";
        }

        int dmg = 0;
        StringBuilder message = new StringBuilder();

        switch (skillNumber) {
            case 1:
                bgm.playSFX("Archer-Piercingshot.wav");
                dmg = (random.nextInt(45 - 15 + 1) + 15 + 25) * getLevel();
                message.append(getName()).append(" uses Basic Skill: Piercing Shot!\n")
                       .append("You shoot a swift arrow at your foe.\n");
                setSkillCooldown(1, 0);
                break;

            case 2:
                bgm.playSFX("Archer-VolleyofNature.wav");
                dmg = (random.nextInt(95 - 70 + 1) + 70 + 20) * getLevel();
                message.append(getName()).append(" uses Advanced Skill: Volley of Nature!\n")
                       .append("You fire multiple arrows, raining them down on your enemy.\n");
                setSkillCooldown(2, 3);
                break;

            case 3:
                bgm.playSFX("Archer-Nature'swrath.wav");
                dmg = (random.nextInt(230 - 105 + 1) + 105 + 80) * getLevel();
                message.append(getName()).append(" uses Ultimate: Nature's Wrath!\n")
                       .append("You unleash four guiding arrows, striking your enemy with precision.\n");
                setSkillCooldown(3, 5);
                break;
        }

        enemy.takeDamage(dmg);
        isUsed[skillNumber - 1] = true;
        reduceCooldowns();

        message.append(enemy.getName()).append(" took ").append(dmg).append(" damage!\n");
        return message.toString();
    }

    // ---------------- POTION ----------------
    @Override
    public String usePotion() {
        if (getPotionCount() <= 0) return "No potions left!\n";
        setPotionCount(getPotionCount() - 1);
        setHp(getMaxHp());
        return getName() + " used a potion and restored HP to full!\n";
    }

    public boolean allSkillsUsed() {
        for (boolean used : isUsed) if (!used) return false;
        return true;
    }

    // ---------------- SKILL INFO ----------------
    public String getSkillName(int skillNumber) {
        switch (skillNumber) {
            case 1: return "Piercing Shot";
            case 2: return "Volley of Nature";
            case 3: return "Nature's Wrath";
            default: return "Unknown Skill";
        }
    }

    public String getSkillDamageRange(int skillNumber) {
        switch (skillNumber) {
            case 1: return (15 * getLevel()) + " - " + (45 * getLevel()) + " + " + (25 * getLevel());
            case 2: return (70 * getLevel()) + " - " + (95 * getLevel()) + " + " + (20 * getLevel());
            case 3: return (105 * getLevel()) + " - " + (230 * getLevel()) + " + " + (80 * getLevel());
            default: return "0";
        }
    }

    public String displayCharacterInfo() {
        StringBuilder msg = new StringBuilder();

        msg.append("\nWielders of ancient knowledge, Mages command the elements and arcane forces. Fragile in body but unmatched in power, they bend magic at will to devastate their enemies from a distance.\n");

        msg.append("SKILLS:\n");
       
        for (int i = 1; i <= 3; i++) {
            String skillName = getSkillName(i);
            String damageRange = getSkillDamageRange(i);
            msg.append("(" + i + ") " + PURPLE + skillName + RESET + "\nDamage: " + damageRange + "\n");
        }

        return msg.toString();
    }

    // ---------------- SKILL DISPLAY FOR GUI ----------------
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

