package EchoesOfTheOath.Characters;




public class Sarukdal extends Character{
    public Sarukdal(){
        super("Sarukdal"," || Demon Realm - Mini Boss", 1400, 10);
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
                dmg = random.nextInt((21) + 30 + 20) * getLevel();
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Basic Skill: Warden's Grasp" + RESET + "!");
                System.out.println("Sarukdal throws spectral chains to the hero.");
                break;




            case 2:
                dmg = random.nextInt((70) + 50 + 20) * getLevel();
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Advanced Skill: Abyssal Lament" + RESET + "!");
                System.out.println("Sarukdal releases dark flames that spread across the battlefield.\n");
                cd[skillNumber - 1] = 2;
                break;




            case 3:
                dmg = random.nextInt((100) + 230 * 4) * getLevel();
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

