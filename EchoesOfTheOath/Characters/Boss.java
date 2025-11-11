package EchoesOfTheOath.Characters;

public class Boss extends Character{
    public Boss(){
        super("King of Demons", 1_000_000, 1);
    }


    @Override public void useSkill(int skillNumber, Character enemy){


    }


    @Override public void takeDamage(int dmg){
        System.out.println(getName()+" takes "+dmg+" damage but remains unfazed!");
    }


    public void displayCharacterInfo(){
        System.out.println("ELARION");
        System.out.println("A primordial demon born from broken oaths, Elarion thrives on despair and lost promises. As the Harbinger of Forgotten Oaths, it tests the hero’s resolve, blurring the line between corruption and redemption.");
        System.out.println();
       
        System.out.println("SKILLS:");
        System.out.println();
       
        System.out.println("Echo Rend\nDamage: 990 – 1210");
        System.out.println();
        System.out.println("Memory Shatter\nDrains 15% of the hero’s current HP and converts it into a shield for Elarion ");
        System.out.println();
        System.out.println("The Final Vow\nSummons a fiery rock dealing great damage & obliterates everything in the area.");
    }


}

