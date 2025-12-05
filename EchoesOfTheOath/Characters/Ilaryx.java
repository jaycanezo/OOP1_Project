package EchoesOfTheOath.Characters;


public class Ilaryx extends Character {
    public Ilaryx(){
        super("Ilaryx"," || Veyora Nation 2 - Mini Boss", 1400, 6);
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
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Basic Skill: Quick Shot" + RESET + "!");
                System.out.println("Ilaryx fires a Double Tap! Two arrows blaze toward the enemy.");
                break;


            case 2:
                dmg = random.nextInt((70) + 50 + 20) * getLevel();
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Advanced Skill: Hunter's Trap" + RESET + "!");
                System.out.println("Ilaryx activates Invisible Snare! A hidden defense is placed!");
                cd[skillNumber - 1] = 2;
                break;


            case 3:
                dmg = random.nextInt((100) + 230 * 4) * getLevel();
                System.out.println(RED + getName() + RESET + " uses " + PURPLE + "Ultimate: Arrow of the Silverfang" + RESET + "!");
                System.out.println("Ilaryx channels silver magic into her bow, preparing one massive, charged shot. Prepare for impact!");
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
