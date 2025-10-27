package EchoesOfTheOath.Characters;

public class Warrior extends Character{
    private boolean[] isUsed = new boolean[3];

    public Warrior(){
        super("Warrior", 1500, 1);
    }

    @Override
    public void useSkill(int skillNumber, Character enemy) {
        if (skillNumber < 1 || skillNumber > 3) {
            return;
        }

        if (!isSkillAvailable(skillNumber)) {
            System.out.println("Skill is on cooldown! " + getSkillCooldown(skillNumber) + " turn(s) remaining.");
            return;
        }

        int dmg = 0;
        switch (skillNumber) {
            case 1:
                dmg = random.nextInt(21) + 30 * getLevel();
                System.out.println(getName() + " uses Slash!");
                setSkillCooldown(1, 0);
                break;

            case 2:
                dmg = random.nextInt(71) + 50 * getLevel();
                System.out.println(getName() + " uses Crimson Strike!");
                setSkillCooldown(2, 2);//1-turn cooldown
                break;

            case 3:
                dmg = random.nextInt(101) + 800 * getLevel();
                System.out.println(getName() + " uses Blade Quake!");
                setSkillCooldown(3, 3);//2-turn cooldown
                break;
        }

        enemy.takeDamage(dmg);
        isUsed[skillNumber-1]=true;
        reduceCooldowns();
    }

    public boolean allSkillsUsed(){
        for(boolean used:isUsed){
            if (!used){
                return false;
            }
        }
        return true;
    }

    @Override public void takeDamage(int dmg){
        super.takeDamage(dmg);
        
        System.out.println(getName()+" has "+getHp()+" HP remaining!");
    }
}
