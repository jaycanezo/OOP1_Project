package EchoesOfTheOath.Characters;

import java.util.ArrayList;
import java.util.Random; 

abstract public class Character {
    public Random random = new Random();
    private String name;
    private String title;
    private int hp;
    private int baseHp;
    private int maxHp;
    private int level;
    private String classType;
    private int potionCount=0;
    private int[] skillCooldowns;
    private double gold = 1000; 
    private int skill1Bonus = 0;
    private int skill2Bonus = 0;
    private int skill3Bonus = 0;

    public ArrayList<Item> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;

    public String RESET = "\033[0m";
    public String RED = "\033[31m";      // enemy / damage
    public String GREEN = "\033[32m";    // ready skill / HP
    public String BLUE = "\033[34m";     // player name
    public String YELLOW = "\033[33m";   // skill cooldown / caution
    public String PURPLE = "\033[35m";   // skills 


    public Character(String name, int hp, int level, String classType) {
        this.name = name;
        this.baseHp = hp;          
        this.level = level;
        this.maxHp = baseHp * level;
        this.hp = maxHp;   
        this.classType = classType;       
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

    public String getClassType() {
        return this.classType;
    }

    public int getPotionCount() {
        return this.potionCount;
    }

    public double getGold() {
        return this.gold;
    }

    public int getSkill1Bonus() {
        return skill1Bonus;
    }

    public int getSkill2Bonus() {
        return skill2Bonus;
    }

    public int getSkill3Bonus() {
        return skill3Bonus;
    }

    public void addSkill1Bonus(int amount) {
        this.skill1Bonus += amount;
        if (this.skill1Bonus < 0) this.skill1Bonus = 0; 
    }

    public void addSkill2Bonus(int amount) {
        this.skill2Bonus += amount;
        if (this.skill2Bonus < 0) this.skill2Bonus = 0; 
    }

    public void addSkill3Bonus(int amount) {
        this.skill3Bonus += amount;
        if (this.skill3Bonus < 0) this.skill3Bonus = 0; 
    }

    public void setPotionCount(int potionCount) {
        this.potionCount = potionCount;
    }

    public void setHp(int newHp){
        this.hp = Math.min(newHp, maxHp);
        if (this.hp < 0) this.hp = 0;
    }

    public void setGold(double gold) {
        this.gold = gold;
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


    public abstract String useSkill(int skillNumber, Character enemy);


    public String takeDamage(int dmg){
        if (dmg > 0) {
            hp -= dmg;
            if (hp < 0) 
                hp = 0; // prevent negative HP
        }
        
        return name +  " takes " + RED + dmg + " damage!" + RESET + "\n" + name + " has " + GREEN + hp + " HP remaining!" + RESET;
    }


    public String usePotion() {
        if(potionCount>0){
            int missingHp = maxHp - hp;
            int healed = (int)(missingHp * 0.30);
            setHp(Math.min(hp + healed, maxHp));
            potionCount--;

            return BLUE + name + RESET + " uses " + PURPLE + "HP Potion" + RESET + "! Restores " + GREEN + healed + " HP" + RESET + "!\n"
            +BLUE + name + RESET + " has " + GREEN + hp + " HP remaining!" + RESET
            + YELLOW + "Potions left: " + potionCount + RESET;
        } else {
            return RED + "No potions left!" + RESET;
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


    public String displaySkills() {
        return name + " has no skills to display.";
    }

    public String getSkillName(int skillNumber) {
        return "Unknown Skill";
    }

    public String getSkillDamageRange(int skillNumber) {
        return "Unknown Damage Range";
    }   
}






