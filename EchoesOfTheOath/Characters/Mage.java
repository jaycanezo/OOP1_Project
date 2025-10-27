package EchoesOfTheOath.Characters;

public class Mage extends Character{
    private boolean[] isUsed = new boolean[3];

    public Mage() {
        super("Mage", 1500, 1);
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
                dmg=random.nextInt((21)+30+200)*getLevel();
                System.out.println(getName() + " uses Fireball!");
                setSkillCooldown(1, 0);
                break;
            case 2:
                dmg=random.nextInt((70)+50+100)*getLevel();
                System.out.println(getName()+" uses Heat Surge!");
                setSkillCooldown(2, 2);
                break;
            case 3:
                dmg=random.nextInt((100)+1400+100)*getLevel();
                System.out.println(getName()+" uses Astral Cataclysm!");
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
