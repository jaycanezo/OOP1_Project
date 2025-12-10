package EchoesOfTheOath.Characters;
import EchoesOfTheOath.Resources.MusicPlayer;

public class Warrior extends Character{
    private boolean[] isUsed = new boolean[3];
    MusicPlayer bgm = new MusicPlayer();
        
    public Warrior(){
        super("Warrior", 1500, 1);
    }


    @Override
    public void useSkill(int skillNumber, Character enemy) {
        if (skillNumber < 1 || skillNumber > 3) {
            return;
        }

        if (!isSkillAvailable(skillNumber)) {
            System.out.println(YELLOW + "Skill is on cooldown! " + getSkillCooldown(skillNumber) + " turn(s) remaining." + RESET);
            return;
        }

        int dmg = 0;
        switch (skillNumber) {
            case 1:
                bgm.playSFX("Slash.wav");
                dmg = (random.nextInt(45 - 15 + 1) + 15 + 15) * getLevel();
                System.out.println(BLUE + getName() + RESET + " uses " + PURPLE + "Basic Skill: Slash" + RESET + "!");
                System.out.println("You swing your sword in a swift slash toward your enemy, dealing damage.");
                setSkillCooldown(1, 0);
                break;
            case 2:
                bgm.playSFX("crimsonStrike.wav");
                dmg = (random.nextInt(90 - 65 + 1) + 65 + 10) * getLevel();
                System.out.println(BLUE + getName() + RESET + " uses " + PURPLE + "Advanced Skill: Crimson Strike" + RESET + "!");
                System.out.println("You perform a heavy, sweeping strike, landing a fierce blow on your enemy.");
                setSkillCooldown(2, 2);//1-turn cooldown
                break;
            case 3:
                bgm.playSFX("bladeQuake.wav");
                dmg = (random.nextInt(255 - 135 + 1) + 135 + 40) * getLevel();
                System.out.println(BLUE + getName() + RESET + " uses " + PURPLE + "Ultimate: Blade Quake" + RESET + "!");
                System.out.println("You unleash devastating strength in a massive ground-splitting attack on your enemy.");
                setSkillCooldown(3, 4);//3-turn cooldown
                break;
        }
        enemy.takeDamage(dmg);
        isUsed[skillNumber-1]=true;
        reduceCooldowns();
    }


    public boolean allSkillsUsed(){
        for(boolean used:isUsed){
            if (!used){
                return false;
            }
        }
        return true;
    }


    @Override 
    public void takeDamage(int dmg){
        super.takeDamage(dmg);
    }

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

    public void displayCharacterInfo() {
        System.out.println("\nWarriors rely on strength and resilience to crush their foes. With unmatched endurance and devastating melee power, they embody the shield and sword of any battle.");
        System.out.println();

        System.out.println("SKILLS:");
       
        for (int i = 1; i <= 3; i++) {
            String skillName = getSkillName(i);
            String damageRange = getSkillDamageRange(i);
            System.out.println("(" + i + ") " + PURPLE + skillName + RESET + "\nDamage: " + damageRange + "\n");
        }
    }

    @Override
    public void displaySkills() {
        System.out.println(BLUE+"------------------ " + getName() + "'s Skills ------------------"+RESET);
        for (int i = 1; i <= 3; i++) {
            String skillName = getSkillName(i);
            String damageRange = getSkillDamageRange(i);
            int cooldown = getSkillCooldown(i);
            String status;

            if (isSkillAvailable(i)) {
                status = GREEN + "Ready" + RESET;
            } else {
                status = YELLOW + "Cooldown: " + cooldown + " turn(s)" + RESET;
            }

            System.out.println(i + ". " + skillName + " | Damage: " + damageRange + " | " + status);
        }
    }

}
