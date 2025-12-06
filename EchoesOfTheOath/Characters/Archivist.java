package EchoesOfTheOath.Characters;


public class Archivist extends Character {
    public Archivist(){
        super("The Archivist", "HUMANAS < NATION 1 > - MAIN BOSS", 1400, 4);
    }


    @Override 
    public void useSkill(int skillNumber, Character enemy){
        int[] cd = getSkillCooldowns(); // get cooldown array
        int dmg = 0;

        //for skill defaulting if random skill is on cooldown
        if (cd[skillNumber - 1] > 0 && skillNumber != 1) {
            skillNumber = 1; //defaults to basic atk
        }

        switch (skillNumber){
            case 1:
                dmg = (random.nextInt(65 - 35 + 1) + 35) * getLevel();
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Basic Skill: The Panoptic Eye" + RESET + "!");
                System.out.println("A pale, shimmering after-image of you appears, and suddenly you feel a sharp cut—not on you, but on your own future shadow.");
                break;
            case 2:
                dmg = (random.nextInt(105 - 80 + 1) + 80) * getLevel();
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Advanced Skill: Temporary Relief" + RESET + "!");
                System.out.println("The Archivist strikes you with the crushing force of accumulated, unseen debts, leaving you reeling.");
                cd[skillNumber - 1] = 2;
                break;
            case 3:
                dmg = (random.nextInt(300 - 180 + 1) + 180) * getLevel();
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Ultimate Skill: Enforced Decree" + RESET + "!");
                System.out.println("A storm of spectral scrolls engulfs you, tearing at your body as the Archivist claims you as his final possession.");
                cd[skillNumber - 1] = 3;
                break;
        }   
        enemy.takeDamage(dmg);
        reduceCooldowns();
    }


    @Override 
    public void takeDamage(int dmg){
        super.takeDamage(dmg);

        System.out.println(RED + getName() + RESET + " takes " + RED + dmg + " damage!" + RESET);
        System.out.println(RED + getName() + RESET + " has " + GREEN + getHp() + " HP remaining!" + RESET);
    }
}
