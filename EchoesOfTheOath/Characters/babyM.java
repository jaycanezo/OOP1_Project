package EchoesOfTheOath.Characters;


public class babyM extends Character{
    public babyM(){
        super("King Bartholomew Monarch (\"Baby M\")", 1400, 2);
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
                System.out.println(getName() + " uses Tantrum Toss!");
                System.out.println("In a fit of screaming paranoia, the King wildly throws his golden rattle at his opponent.");
                break;


            case 2:
                dmg = random.nextInt((70) + 50 + 20) * getLevel();
                System.out.println(getName() + " uses Feigned Faint!");
                System.out.println("Baby M lulls his opponent into a false sense of security only to launch a surprise strike!");
                cd[skillNumber - 1] = 2;
                break;


            case 3:
                dmg = random.nextInt((100) + 230 * 4) * getLevel();
                System.out.println(getName() + " uses his Ultimate: Enforced Decree!");
                System.out.println("The infant King abruptly stops all movement as Someone overrides his own, charging forward with mechanical precision!");
                cd[skillNumber - 1] = 3;
                break;
        }


        enemy.takeDamage(dmg);
        reduceCooldowns();
    }


    @Override public void takeDamage(int dmg){
        super.takeDamage(dmg);


        System.out.println(getName()+" has "+getHp()+" HP remaining!");
    }
   
}
