package EchoesOfTheOath.Characters;

public class Boss extends Character{
    public Boss(){
        super("King of Demons", 1_000_000, 1_000_000, 1);
    }

    @Override public void useSkill(int skillNumber, Character enemy){

    }

    @Override public void takeDamage(int dmg){
        System.out.println(getName()+" takes "+dmg+" damage but remains unfazed!");
    }
}
