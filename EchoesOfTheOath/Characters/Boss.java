package EchoesOfTheOath.Characters;

public class Boss extends Character{

    public Boss(){
        super("Elarion", 1_000_000, 1);
    }


    @Override 
    public void useSkill(int skillNumber, Character enemy){


    }


    @Override 
    public void takeDamage(int dmg){
        System.out.println(RED + getName() + RESET + " takes " + RED + dmg + " damage" + RESET + " but remains unfazed!");
    }
}

