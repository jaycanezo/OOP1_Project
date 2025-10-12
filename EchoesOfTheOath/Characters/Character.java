package EchoesOfTheOath.Characters;

import java.util.Random;

abstract public class Character {
    public Random random = new Random();
    private String name;
    private int hp;
    private int def;

    public Character(String name, int hp, int def) {
        this.name=name;
        this.hp=hp;
        this.def=def;
    }
    
    abstract void useSkill(int skillNumber, Character enemy);
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public void takeDamage(int dmg){
        System.out.println(name+" takes"+dmg+"damage!");
    }
}

