package EchoesOfTheOath.Characters;
import EchoesOfTheOath.UI.*;

public class Boss extends Character{

    public Boss(){
        super("Elarion", "The Dark Lord",1_000_000, 1);
        this.idleSprite = new Sprite("/EchoesOfTheOath/Resources/Elarion.png", 517, 483, 1);
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

