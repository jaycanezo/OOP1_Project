package EchoesOfTheOath.Characters;

import java.util.Random;

abstract public class Character {
    public Random random = new Random();
    private String name;
    private String title;
    private int hp;
    private int baseHp;
    private int maxHp;
    private int level;
    private int potionCount=0;
    private int[] skillCooldowns;

    public String RESET = "\033[0m";
    public String RED = "\033[31m";      // enemy / damage
    public String GREEN = "\033[32m";    // ready skill / HP
    public String BLUE = "\033[34m";     // player name
    public String YELLOW = "\033[33m";   // skill cooldown / caution
    public String PURPLE = "\033[35m";   // skills 


    public Character(String name, int hp, int level) {
        this.name = name;
        this.baseHp = hp;          
        this.level = level;
        this.maxHp = baseHp * level;
        this.hp = maxHp;          
        this.skillCooldowns = new int[3];
    }

    public Character(String name, String title, int hp, int level) {
        this.name = name;
        this.title = title;
        this.baseHp = hp;        
        this.level = level;
        this.maxHp = baseHp * level;
        this.hp = maxHp;
        this.skillCooldowns = new int[3];
    }



    public int getHp() {
        return this.hp;
    }


    public int getMaxHp() {
        return this.maxHp;
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
        this.hp = hp;
    }


    public void setLevel(int level) {
        this.level = level;
        this.maxHp = baseHp * level;
        this.hp = maxHp; 
    }


    public String getName(){
        return name;
    }


    public void setName(String name){
        this.name=name;
    }

    public String getTitle(){
        return title;
    }


    public abstract void useSkill(int skillNumber, Character enemy);


    public void takeDamage(int dmg){
        if (dmg > 0) {
            hp -= dmg;
            if (hp < 0) hp = 0; // prevent negative HP
        }
        
        System.out.println(name +  " takes " + RED + dmg + " damage!" + RESET);
        System.out.println(name + " has " + GREEN + hp + " HP remaining!" + RESET);
    }


    public void usePotion() {
        if(potionCount>0){
            int missingHp = maxHp - hp;
            int healed = (int)(missingHp * 0.30);
            setHp(Math.min(hp + healed, maxHp));
            System.out.println(BLUE + name + RESET + " uses " + PURPLE + "HP Potion" + RESET + "! Restores " + GREEN + healed + " HP" + RESET + "!");
            potionCount--;
            System.out.println(BLUE + name + RESET + " has " + GREEN + hp + " HP remaining!" + RESET);
            System.out.println(YELLOW + "Potions left: " + potionCount + RESET);
        } else {
            System.out.println(RED + "No potions left!" + RESET);
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


    public void displaySkills() {
        System.out.println(name + " has no skills to display.");
    }

}






