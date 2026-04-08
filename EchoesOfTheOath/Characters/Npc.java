package EchoesOfTheOath.Characters;
import EchoesOfTheOath.UI.*;

public class Npc {
    private String name;
    private Sprite idleSprite;

    public Npc(String name, Sprite idleSprite) {
        this.name = name;
        this.idleSprite = idleSprite;
    }

    public void updateAnimations(){
        if(idleSprite != null) idleSprite.update();
    }

    public String getName() {
        return name;
    }

    public Sprite getIdleSprite() {
        return idleSprite;
    }
}
