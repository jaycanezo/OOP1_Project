package EchoesOfTheOath.Characters;

public class Boss extends Character{

    public Boss(){
        super("Elarion", "The Dark Lord",1_000_000, 1);
    }


    @Override 
    public String useSkill(int skillNumber, Character enemy){
        return "";
    }


    @Override 
    public String takeDamage(int dmg){
        return getName() + " takes " + dmg + " damage"  + " but remains unfazed!";
    }
}

