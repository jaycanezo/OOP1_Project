package EchoesOfTheOath.Characters;
import EchoesOfTheOath.Resources.MusicPlayer;

public class Warrior extends Character{
    boolean[] isUsed = new boolean[3];
    MusicPlayer bgm = new MusicPlayer();
        
    public Warrior(){
        super("Warrior", 1500, 1);
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

    public String getSkillName(int skillNumber) {
        switch(skillNumber) {
            case 1: return "Slash";
            case 2: return "Crimson Strike";
            case 3: return "Blade Quake";
            default: return "Unknown Skill";
        }
    }


    public String getSkillDamageRange(int skillNumber) {
        switch(skillNumber) {
            case 1: return (15*getLevel()) + " - " + (45*getLevel()) + " + " + (15*getLevel());
            case 2: return (65*getLevel()) + " - " + (90*getLevel()) + " + " + (10*getLevel());
            case 3: return (135*getLevel()) + " - " + (255*getLevel()) + " + " + (40*getLevel());
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
