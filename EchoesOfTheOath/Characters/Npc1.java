package EchoesOfTheOath.Characters;
import EchoesOfTheOath.UI.*;

public class Npc1 extends Character{
    public Npc1() {
        super("Guard", "", 1, 1);
        this.idleSprite = new Sprite("/EchoesOfTheOath/Resources/npc1.png", 458, 545, 1);
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
