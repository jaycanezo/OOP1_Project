package EchoesOfTheOath.Characters;


public class Lunareth extends Character {
    public Lunareth(){
        super("Lunareth","VEYORA < NATION 2 > - MAIN BOSS", 1400, 8);
    }


    @Override
    public void useSkill(int skillNumber, Character enemy) {
        int[] cd = getSkillCooldowns();
        int dmg = 0;


       
        if (cd[skillNumber - 1] > 0 && skillNumber != 1) {
            skillNumber = 1;
        }


        switch (skillNumber) {
            case 1:
                dmg = (random.nextInt(65 - 35 + 1) + 35) * getLevel();
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Basic Skill: Moonpierce Shot" + RESET + "!");
                System.out.println("Lunareth Fires a luminous arrow imbued with lunar energy.");
                break;


            case 2:
                dmg = (random.nextInt(105 - 80 + 1) + 80) * getLevel();
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Advanced Skill: Binding Roots" + RESET + "!");
                System.out.println("Enchanted roots ensnare the hero, pulling them into the ground!");
                cd[skillNumber - 1] = 2;
                break;


            case 3:
                dmg = (random.nextInt(300 - 180 + 1) + 180) * getLevel();
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Ultimate: Eclipse Volley" + RESET + "!");
                System.out.println("Lunareth leaps into the air, summoning the power of the eclipse to fire a storm of arrows that rain upon the battlefield.");
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

