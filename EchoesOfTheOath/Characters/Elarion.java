package EchoesOfTheOath.Characters;

public class Elarion extends Character{
    public Elarion(){
        super("Elarion","DEMON REALM < NATION 3 > - MAIN BOSS", 1400, 12);
    }

    
    @Override
    public void useSkill(int skillNumber, Character enemy) {
        int[] cd = getSkillCooldowns(); // get cooldown array
        int dmg = 0;

        //for skill defaulting if random skill is on cooldown
        if (cd[skillNumber - 1] > 0 && skillNumber != 1) {
            skillNumber = 1; //defaults to basic atk
        }

        switch (skillNumber) {
            case 1:
                dmg = (random.nextInt(65 - 35 + 1) + 35) * getLevel();
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Basic Skill: Echo Rend" + RESET + "!");
                System.out.println("Elarion unleashes a wave of distorted echoes.\n");
                break;
            case 2:
                dmg = (random.nextInt(105 - 80 + 1) + 80) * getLevel();
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Advanced Skill: Memory Shatter" + RESET + "!");
                System.out.println("Elarion summons glowing memory shards from you and crushes them into a protective shield, forcing you to endure the impact.");
                cd[skillNumber - 1] = 2;
                break;
            case 3:
                dmg = (random.nextInt(300 - 180 + 1) + 180) * getLevel();
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Ultimate: The Final Vow" + RESET + "!");
                System.out.println("A pillar of fate descends from above, engulfing you in blinding light and overwhelming your senses.");
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

