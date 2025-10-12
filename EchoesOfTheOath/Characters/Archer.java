package EchoesOfTheOath.Characters;

public class Archer extends Character{
    private boolean[] isUsed = new boolean[3];

    public Archer(){
        super("Archer", 1300, 130, 1);
    }

    @Override
    public void useSkill(int skillNumber, Character enemy){
        if(skillNumber<1||skillNumber>3){
            return;
        }

        int dmg=0;
        switch (skillNumber){
            case 1:
                dmg=random.nextInt(21)+30+20;
                System.out.println(getName() + " uses Piercing Shot!");
                break;
            case 2:
                dmg=random.nextInt(70)+50+20;
                System.out.println(getName()+" uses Volley of Nature!");
                break;
            case 3:
                dmg=random.nextInt((100)+150+80*4);
                System.out.println(getName()+" uses Nature's Wrath!");
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
