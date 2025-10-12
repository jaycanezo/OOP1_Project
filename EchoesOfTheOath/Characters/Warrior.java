package EchoesOfTheOath.Characters;

public class Warrior extends Character{
    private boolean[] isUsed = new boolean[3];

    public Warrior(){
        super("Warrior", 1500, 200, 1);
    }

    @Override
    public void useSkill(int skillNumber, Character enemy){
        if(skillNumber<1||skillNumber>3){
            return;
        }

        int dmg=0;
        switch (skillNumber){
            case 1:
                dmg=random.nextInt(21)+30+10;
                System.out.println(getName() + " uses Slash!");
                break;
            case 2:
                dmg=random.nextInt(70)+50+10;
                System.out.println(getName()+" uses Crimson Strike!");
                break;
            case 3:
                dmg=random.nextInt(100)+800+100;
                System.out.println(getName()+" uses Blade Quake!");
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
