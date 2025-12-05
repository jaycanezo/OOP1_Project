package EchoesOfTheOath.Characters;


public class babyM extends Character{
    public babyM(){
        super("King Bartholomew Monarch (\"Baby M\")", " || Humanas Nation 1 - Mini Boss", 1400, 2);
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
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Basic Skill: Tantrum Toss" + RESET + "!");
                System.out.println("In a fit of screaming paranoia, the King wildly throws his golden rattle at his opponent.");
                break;
            case 2:
                dmg = (random.nextInt(105 - 80 + 1) + 80) * getLevel();
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Advanced Skill: Feigned Faint!");
                System.out.println("Baby M lulls his opponent into a false sense of security only to launch a surprise strike" + RESET + "!");
                cd[skillNumber - 1] = 2;
                break;
            case 3:
                dmg = (random.nextInt(300 - 180 + 1) + 180) * getLevel();
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Ultimate: Enforced Decree!");
                System.out.println("The infant King abruptly stops all movement as Someone overrides his own, charging forward with mechanical precision" + RESET + "!");
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
