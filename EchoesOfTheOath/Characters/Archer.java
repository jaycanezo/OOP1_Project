package EchoesOfTheOath.Characters;

public class Archer extends Character{
    private boolean[] isUsed = new boolean[3];

    public Archer(){
        super("Archer", 1300, 1);
    }

    @Override
    public void useSkill(int skillNumber, Character enemy){
        if(skillNumber<1||skillNumber>3){
            return;
        }

        if (!isSkillAvailable(skillNumber)) {
            System.out.println("Skill is on cooldown! " + getSkillCooldown(skillNumber) + " turn(s) remaining.");
            return;
        }

        int dmg=0;
        switch (skillNumber){
            case 1:
                dmg=random.nextInt((21)+30+20)*getLevel();
                System.out.println(getName() + " uses Piercing Shot!");
                setSkillCooldown(1, 0);
                break;
            case 2:
                dmg=random.nextInt((70)+50+20)*getLevel();
                System.out.println(getName()+" uses Volley of Nature!");
                setSkillCooldown(2, 2);
                break;
            case 3:
                dmg=random.nextInt((100)+150+80*4)*getLevel();
                System.out.println(getName()+" uses Nature's Wrath!");
                setSkillCooldown(3, 3);
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
