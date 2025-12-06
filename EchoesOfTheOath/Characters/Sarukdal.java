package EchoesOfTheOath.Characters;

public class Sarukdal extends Character{
    public Sarukdal(){
        super("Sarukdal","DEMON REALM < NATION 3 > - MINI BOSS", 1400, 10);
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
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Basic Skill: Warden's Grasp" + RESET + "!");
                System.out.println("Sarukdal throws spectral chains to the hero.");
                break;
            case 2:
                dmg = (random.nextInt(105 - 80 + 1) + 80) * getLevel();
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Advanced Skill: Abyssal Lament" + RESET + "!");
                System.out.println("Sarukdal releases dark flames that spread across the battlefield.\n");
                cd[skillNumber - 1] = 2;
                break;
            case 3:
                dmg = (random.nextInt(300 - 180 + 1) + 180) * getLevel();
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Ultimate: Judgment of the Forsaken" + RESET + "!");
                System.out.println("Sarukdal summons glowing fire blades from the sky.\n");
                cd[skillNumber - 1] = 3;
                break;
        }
        enemy.takeDamage(dmg);
        reduceCooldowns();
    }


    @Override public void takeDamage(int dmg){
        super.takeDamage(dmg);

        System.out.println(RED + getName() + RESET + " takes " + RED + dmg + " damage!" + RESET);
        System.out.println(RED + getName() + RESET + " has " + GREEN + getHp() + " HP remaining!" + RESET);
    }
   
}

