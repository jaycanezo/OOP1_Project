package EchoesOfTheOath.Characters;

import java.util.Random;

abstract public class Character {
    public Random random = new Random();
    private String name;
    private int hp;
    private int def;
    private int level;

    public Character(String name, int hp, int def, int level) {
        this.name=name;
        this.hp=hp;
        this.def=def;
        this.level=level;
    }

    public int getHp() {
        return this.hp;
    }

    public int getDef() {
        return this.def;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level){
        this.level=level;
    }
    
    public abstract void useSkill(int skillNumber, Character enemy);
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public void takeDamage(int dmg){
        dmg-=def*level;
        hp-=dmg*level;
        System.out.println(name+" takes "+dmg+" damage!");
    }
}

