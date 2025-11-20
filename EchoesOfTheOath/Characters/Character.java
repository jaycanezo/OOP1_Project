package EchoesOfTheOath.Characters;


import java.util.Random;


abstract public class Character {
    public Random random = new Random();
    private String name;
    private int hp;
    private int maxHp;
    private int level;
    private int potionCount=0;
    private int[] skillCooldowns;


    public Character(String name, int hp, int level) {
        this.name=name;
        this.hp=hp*level;//====================================================
        this.maxHp=hp;
        this.level=level;
        this.skillCooldowns = new int[3];
    }


    public int getHp() {
        return this.hp;
    }


    public int getMaxHp() {
        return this.maxHp*level;
    }


    public int getLevel() {
        return this.level;
    }


    public int getPotionCount() {
        return this.potionCount;
    }


    public void setPotionCount(int potionCount) {
        this.potionCount = potionCount;
    }


    public void setHp(int hp){
        this.hp=hp;
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
        if(dmg>0){
            hp-=dmg*level;
        } else {
            dmg=0;
        }
       
        System.out.println(name+" takes "+dmg+" damage!");
    }


    public void usePotion() {
        if(potionCount>0){
            int healed = 1000 * getLevel();
            setHp(getHp() + healed);
            System.out.println(getName() + " uses Hp Potion! Restores " + healed + " HP!");
            potionCount--;
            System.out.println("Potions left: " + (potionCount));
        } else {
            System.out.println("No potions left!");
        }
    }


    public boolean isSkillAvailable(int skillNumber) {
        return skillCooldowns[skillNumber - 1] == 0;
    }


    public int[] getSkillCooldowns() {//for the boss
    return skillCooldowns;
}


    public int getSkillCooldown(int skillNumber) {//for character
        return skillCooldowns[skillNumber - 1];
    }


    public void reduceCooldowns() {
        for (int i = 0; i < skillCooldowns.length; i++) {
            if (skillCooldowns[i] > 0) {
                skillCooldowns[i]--;
            }
        }
    }


    public void setSkillCooldown(int skillNumber, int turns) {
        skillCooldowns[skillNumber - 1] = turns;
    }


    public void resetCooldowns() {
    for (int i = 0; i < skillCooldowns.length; i++) {
        skillCooldowns[i] = 0;
    }


}
}






