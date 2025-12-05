package EchoesOfTheOath.Characters;


public class Mage extends Character{
    private boolean[] isUsed = new boolean[3];


    public Mage() {
        super("Mage", 1500, 1);
    }


    @Override
    public void useSkill(int skillNumber, Character enemy){
        if(skillNumber<1||skillNumber>3){
            return;
        }


        if (!isSkillAvailable(skillNumber)) {
            System.out.println(YELLOW + "Skill is on cooldown! " + getSkillCooldown(skillNumber) + " turn(s) remaining." + RESET);
            return;
        }


        int dmg=0;
        switch (skillNumber){
            case 1:
                dmg=random.nextInt((21)+30+200)*getLevel();
                System.out.println(BLUE + getName() + RESET + " uses " + PURPLE + "Basic Skill: Fireball" + RESET + "!");
                setSkillCooldown(1, 0);
                break;
            case 2:
                dmg=random.nextInt((70)+50+100)*getLevel();
                System.out.println(BLUE + getName() + RESET + " uses " + PURPLE + "Advanced Skill: Heat Surge" + RESET + "!");
                setSkillCooldown(2, 2);
                break;
            case 3:
                dmg=random.nextInt((100)+1400+100)*getLevel();
                System.out.println(BLUE + getName() + RESET + " uses " + PURPLE + "Ultimate: Astral Cataclysm" + RESET + "!");
                setSkillCooldown(3, 3);
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


    @Override public void takeDamage(int dmg){
        super.takeDamage(dmg);

        System.out.println(BLUE + getName() + RESET + " takes " + RED + dmg + " damage!" + RESET);
        System.out.println(BLUE + getName() + RESET + " has " + GREEN + getHp() + " HP remaining!" + RESET);
    }

    public String getSkillName(int skillNumber) {
        switch(skillNumber) {
            case 1: return "Fire Ball";
            case 2: return "Heatfire Surge";
            case 3: return "Astral";
            default: return "Unknown Skill";
        }
    }


    public String getSkillDamageRange(int skillNumber) {
        switch(skillNumber) {
            case 1: return (30*getLevel()) + "-" + (50*getLevel()) + "+" + (200*getLevel());
            case 2: return (100*getLevel()) + "-" + (300*getLevel()) + "+" + (100*getLevel());
            case 3: return String.valueOf(1500*getLevel());
            default: return "0";
        }
    }


    public void displayCharacterInfo() {
        System.out.println("\nWielders of ancient knowledge, Mages command the elements and arcane forces. Fragile in body but unmatched in power, they bend magic at will to devastate their enemies from a distance.");
        System.out.println();


        System.out.println("SKILLS:");
       
        for (int i = 1; i <= 3; i++) {
            String skillName = getSkillName(i);
            String damageRange = getSkillDamageRange(i);
            System.out.println("(" + i + ")" + skillName + "\nDamage: " + damageRange + "\n");
        }
    }

    @Override
    public void displaySkills() {
        System.out.println("\n--- " + getName() + "'s Skills ---");
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
            System.out.println();
        }
    }

}
