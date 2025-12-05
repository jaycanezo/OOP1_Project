package EchoesOfTheOath.Characters;




public class Elarion extends Character{
    public Elarion(){
        super("Elarion"," || Demon Realm - Main Boss", 1400, 12);
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
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Basic Skill: Echo Rend" + RESET + "!");
                System.out.println("Elarion unleashes a wave of distorted echoes.\n");
                break;




            case 2:
                dmg = random.nextInt((70) + 50 + 20) * getLevel();
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Advanced Skill: Memory Shatter" + RESET + "!");
                System.out.println("Elarion summons glowing memory shards from the hero, then crushes them into a shield.\n");
                cd[skillNumber - 1] = 2;
                break;




            case 3:
                dmg = random.nextInt((100) + 230 * 4) * getLevel();
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Ultimate: The Final Vow" + RESET + "!");
                System.out.println("Elarion calls down a pillar of fate that engulfs the hero in blinding light.\n");
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

