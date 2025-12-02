package EchoesOfTheOath.Characters;


public class Warrior extends Character{
    private boolean[] isUsed = new boolean[3];


    public Warrior(){
        super("Warrior", 1500, 1);
    }


    @Override
    public void useSkill(int skillNumber, Character enemy) {
        if (skillNumber < 1 || skillNumber > 3) {
            return;
        }


        if (!isSkillAvailable(skillNumber)) {
            System.out.println("Skill is on cooldown! " + getSkillCooldown(skillNumber) + " turn(s) remaining.");
            return;
        }


        int dmg = 0;
        switch (skillNumber) {
            case 1:
                dmg = random.nextInt(21) + 30 * getLevel();
                System.out.println(getName() + " uses Basic Skill: Slash!");
                setSkillCooldown(1, 0);
                break;


            case 2:
                dmg = random.nextInt(71) + 50 * getLevel();
                System.out.println(getName() + " uses Advanced Skill: Crimson Strike!");
                setSkillCooldown(2, 2);//1-turn cooldown
                break;


            case 3:
                dmg = random.nextInt(101) + 800 * getLevel();
                System.out.println(getName() + " uses Ultimate: Blade Quake!");
                setSkillCooldown(3, 3);//2-turn cooldown
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
       
        System.out.println(getName()+" has "+getHp()+" HP remaining!");
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
            case 1: return (30*getLevel()) + "-" + (50*getLevel()) + "+" + (10*getLevel());
            case 2: return (50*getLevel()) + "-" + (120*getLevel()) + "+" + (10*getLevel());
            case 3: return (800*getLevel()) + "-" + (900*getLevel()) + "+" + (100*getLevel());
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
                status = "Ready";
            } else {
                status = "Cooldown: " + cooldown + " turn(s)";
            }

            System.out.println(i + ". " + skillName + " | Damage: " + damageRange + " | " + status);
            System.out.println();
        }
    }

}
