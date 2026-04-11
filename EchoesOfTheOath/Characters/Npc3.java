package EchoesOfTheOath.Characters;
import EchoesOfTheOath.UI.*;

public class Npc3 extends Character{
    public Npc3() {
        super("Attendant", "", 1, 1);
        this.idleSprite = new Sprite("/EchoesOfTheOath/Resources/npc3.png", 2000, 2000, 1);
    }

    @Override
    public String useSkill(int skillNumber, Character enemy) {
        return "";
    }

    @Override
    public String takeDamage(int dmg) {
        return getName() + " takes " + dmg + " damage.";
    }
}
