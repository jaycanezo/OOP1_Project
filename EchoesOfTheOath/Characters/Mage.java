package EchoesOfTheOath.Characters;

public class Mage extends Character{
    private boolean[] isUsed = new boolean[3];

    public Mage() {
        super("Mage", 1500, 110, 1);
    }

    @Override
    public void useSkill(int skillNumber, Character enemy){
        if(skillNumber<1||skillNumber>3){
            return;
        }

        int dmg=0;
        switch (skillNumber){
            case 1:
                dmg=random.nextInt(21)+30+200;
                System.out.println(getName() + " uses Fireball!");
                break;
            case 2:
                dmg=random.nextInt(70)+50+100;
                System.out.println(getName()+" uses Heat Surge!");
                break;
            case 3:
                dmg=random.nextInt(100)+1400+100;
                System.out.println(getName()+" uses Astral Cataclysm!");
                break;
        }

        enemy.takeDamage(dmg);
        isUsed[skillNumber-1]=true;
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
