package EchoesOfTheOath.Characters;
import EchoesOfTheOath.UI.*;

public class Npc2 extends Character{
    public Npc2() {
        super("Informant", "", 1, 1);
        this.idleSprite = new Sprite("/EchoesOfTheOath/Resources/npc2.png", 2000, 2000, 1);
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
