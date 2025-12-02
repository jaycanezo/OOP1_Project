package EchoesOfTheOath.Characters;


public class Archer extends Character{
    private boolean[] isUsed = new boolean[3];

    public Archer(){
        super("Archer", 1300, 1);
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
                dmg=random.nextInt((21)+30+20)*getLevel();
                System.out.println(BLUE + getName() + RESET + " uses " + PURPLE + "Basic Skill: Piercing Shot" + RESET + "!");
                setSkillCooldown(1, 0);
                break;
            case 2:
                dmg=random.nextInt((70)+50+20)*getLevel();
                System.out.println(BLUE + getName() + RESET + " uses " + PURPLE + "Advanced Skill: Volley of Nature" + RESET + "!");
                setSkillCooldown(2, 2);
                break;
            case 3:
                dmg=random.nextInt((100)+150+80*4)*getLevel();
                System.out.println(BLUE + getName() + RESET + " uses " + PURPLE + "Ultimate: Nature's Wrath" + RESET + "!");
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

        System.out.println(getName() + " has "+getHp()+" HP remaining!");
    }

    public String getSkillName(int skillNumber) {
        switch(skillNumber) {
            case 1: return "Piercing Shot";
            case 2: return "Volley of Nature";
            case 3: return "Nature's Wrath";
            default: return "Unknown Skill";
        }
    }


    public String getSkillDamageRange(int skillNumber) {
        switch(skillNumber) {
            case 1: return (30*getLevel()) + "-" + (50*getLevel()) + "+" + (20*getLevel());
            case 2: return (50*getLevel()) + "-" + (120*getLevel()) + "+" + (20*getLevel());
            case 3: return (150*getLevel()) + "-" + (250*getLevel()) + "+" + (80*getLevel());
            default: return "0";
        }
    }


    public void displayCharacterInfo() {
        System.out.println("\nSwift and precise, Archers strike from afar with deadly accuracy. Masters of the bow, they can pierce armor, control the battlefield, and rain destruction upon enemies before they can even draw near.");
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

