package EchoesOfTheOath.Characters;


public class babyM extends Character{
    public babyM(){
        super("King Bartholomew Monarch (\"Baby M\")", " HUMANAS < NATION 1 > - MINI BOSS", 1400, 2);
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
                System.out.println("The infant King shrieks furiously and hurls his golden rattle straight at you.");
                break;
            case 2:
                dmg = (random.nextInt(105 - 80 + 1) + 80) * getLevel();
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Advanced Skill: Feigned Faint" + RESET + "!");
                System.out.println("Baby M suddenly collapses, tricking you into lowering your guard before striking you with a vicious surprise attack.");
                cd[skillNumber - 1] = 2;
                break;
            case 3:
                dmg = (random.nextInt(300 - 180 + 1) + 180) * getLevel();
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Ultimate: Enforced Decree" + RESET + "!");
                System.out.println("The infant King freezes mid-air as if controlled by an unseen force, then hurtles toward you with unstoppable, mechanical precision.");
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
