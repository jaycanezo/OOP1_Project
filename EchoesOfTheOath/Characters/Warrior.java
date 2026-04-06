package EchoesOfTheOath.Characters;
import EchoesOfTheOath.UI.MusicPlayer;

public class Warrior extends Character{
    boolean[] isUsed = new boolean[3];
    MusicPlayer bgm = new MusicPlayer();
        
    public Warrior(){
        super("Warrior", 1500, 1, "Warrior");
    }


    @Override
    public String useSkill(int skillNumber, Character enemy) {
        if (skillNumber < 1 || skillNumber > 3) {
            return "Invalid Skill Number.\n";
        }

        if (!isSkillAvailable(skillNumber)) {
            return "Skill is on cooldown! " + getSkillCooldown(skillNumber) + " turn(s) remaining.";
            
        }

        int dmg = 0;
        StringBuilder msg = new StringBuilder();

        switch (skillNumber) {
            case 1:
                bgm.playSFX("Slash.wav");
                dmg = (random.nextInt(45 - 15 + 1) + 15 + 15) * getLevel();
                msg.append(getName()+" uses Basic Skill: Slash!\n");
                msg.append("You swing your sword in a swift slash toward your enemy, dealing damage.");
                setSkillCooldown(1, 0);
                break;
            case 2:
                bgm.playSFX("crimsonStrike.wav");
                dmg = (random.nextInt(90 - 65 + 1) + 65 + 10) * getLevel();
                msg.append(getName() + " uses Advanced Skill: Crimson Strike!\n");
                msg.append("You perform a heavy, sweeping strike, landing a fierce blow on your enemy.");
                setSkillCooldown(2, 3);
                break;
            case 3:
                bgm.playSFX("bladeQuake.wav");
                dmg = (random.nextInt(255 - 135 + 1) + 135 + 40) * getLevel();
                msg.append(getName() + " uses Ultimate: Blade Quake!\n");
                msg.append("You unleash devastating strength in a massive ground-splitting attack on your enemy.");
                setSkillCooldown(3, 5);
                break;
        }
        enemy.takeDamage(dmg);
        isUsed[skillNumber-1]=true;
        reduceCooldowns();

        msg.append(enemy.getName()).append(" took ").append(dmg).append(" damage!\n");
        return msg.toString();
    }


    public boolean allSkillsUsed(){
        for(boolean used:isUsed){
            if (!used){
                return false;
            }
        }
        return true;
    }


    /*@Override 
    public void takeDamage(int dmg){
        super.takeDamage(dmg);
    }*/

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
        switch(skillNumber) {
            case 1: 
                int min1 = (15 * lvl) + (15 * lvl) + getSkill1Bonus();
                int max1 = (45 * lvl) + (15 * lvl) + getSkill1Bonus();
                return min1 + " - " + max1;
            case 2: 
                int min2 = (65 * lvl) + (10 * lvl) + getSkill2Bonus();
                int max2 = (90 * lvl) + (10 * lvl) + getSkill2Bonus();
                return min2 + " - " + max2;
            case 3: 
                int min3 = (135 * lvl) + (40 * lvl) + getSkill3Bonus();
                int max3 = (255 * lvl) + (40 * lvl) + getSkill3Bonus();
                return min3 + " - " + max3;
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
