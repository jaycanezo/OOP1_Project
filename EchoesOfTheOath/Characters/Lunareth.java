package EchoesOfTheOath.Characters;


public class Lunareth extends Character {
    public Lunareth(){
        super("Lunareth"," || Veyora Nation 2 - Main Boss", 1400, 8);
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
                dmg = random.nextInt((21) + 30 + 20) * getLevel();
                System.out.println(getName() + " uses Basic Skill: Moonpierce Shot!");
                System.out.println("Lunareth Fires a luminous arrow imbued with lunar energy.");
                break;


            case 2:
                dmg = random.nextInt((70) + 50 + 20) * getLevel();
                System.out.println(getName() + " uses Advanced Skill: Binding Roots!");
                System.out.println("Enchanted roots ensnare the hero, pulling them into the ground!");
                cd[skillNumber - 1] = 2;
                break;


            case 3:
                dmg = random.nextInt((100) + 230 * 4) * getLevel();
                System.out.println(getName() + " uses Ultimate: Eclipse Volley!");
                System.out.println("Lunareth leaps into the air, summoning the power of the eclipse to fire a storm of arrows that rain upon the battlefield.");
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

