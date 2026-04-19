package EchoesOfTheOath.Characters;
import EchoesOfTheOath.UI.*;
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
    private int[] skillCooldowns = new int[3];;
    private double gold = 1000; 
    private int[] skillBonuses = new int[3];
    private boolean[] isUsed = new boolean[3];
    public ArrayList<Item> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    private Sprite idleSprite;
    private Sprite[] skill1Sprite, skill2Sprite, skill3Sprite;

    public Character(String name, int hp, int level, String classType) {
        this.name = name;
        this.baseHp = hp;          
        this.level = level;
        this.maxHp = baseHp * level;
        this.hp = maxHp;   
        this.classType = classType;
        this.title = "";
    }

    public Character(String name, String title, int hp, int level) {
        this.name = name;
        this.title = title;
        this.baseHp = hp;        
        this.level = level;
        this.maxHp = baseHp * level;
        this.hp = maxHp;
    }

    public int getHp() { return this.hp; }
    public int getMaxHp() { return this.maxHp; }
    public int getLevel() { return this.level; }
    public String getClassType() { return this.classType; }
    public double getGold() { return this.gold; }
    public String getName() { return name; }
    public String getTitle() { return title; }
    public Sprite getIdleSprite() { return idleSprite; }
    public String getSkillName(int skillNumber) { return "Unknown Skill"; }
    public String getSkillDamageRange(int skillNumber) { return "Unknown Damage Range"; }  
    public ArrayList<Item> getInventory() {
        return this.inventory;
    }
    public int getSkillBonus(int skillSlot) {
        if (skillSlot >= 1 && skillSlot <= 3) {
            return this.skillBonuses[skillSlot - 1];
        }
        return 0;
    }

    public int[] getSkillCooldowns() { return skillCooldowns; }

    public int getSkillCooldown(int skillNumber) {
        return skillCooldowns[skillNumber - 1];
    }

    public int getSkillMaxCooldown(int skillNumber) {
        return switch(skillNumber) {
            case 1 -> 0; 
            case 2 -> 2; 
            case 3 -> 4;
            default -> 0;
        };
    }
    public Sprite[] getSkillSprite(int skillNumber){
        return switch(skillNumber){
            case 1 -> skill1Sprite;
            case 2 -> skill2Sprite;
            case 3 -> skill3Sprite;
            default -> null;
        };
    }

    public void setIdleSprite(Sprite s) { this.idleSprite = s; }
    public void setGold(double gold) { this.gold = gold; }
    public void setName(String name){ this.name = name; }
    public void setLevel(int level) {
        this.level = level;
        this.maxHp = baseHp * level;
        this.hp = maxHp; 
    }
    public void setHp(int newHp){
        this.hp = Math.min(newHp, maxHp);
        if (this.hp < 0) this.hp = 0;
    }
    public void setSkillCooldown(int skillNumber, int turns) {
        skillCooldowns[skillNumber - 1] = turns;
    }
    public void setSkillSprites(Sprite[] s1, Sprite[] s2, Sprite[] s3) {
        this.skill1Sprite = s1;
        this.skill2Sprite = s2;
        this.skill3Sprite = s3;
    }
    
    public void addSkillBonus(int skillSlot, int amount) {
        if (skillSlot >= 1 && skillSlot <= 3) {
            this.skillBonuses[skillSlot - 1] += amount;
            if (this.skillBonuses[skillSlot - 1] < 0) this.skillBonuses[skillSlot - 1] = 0;
        }
    }
    public void markSkillUsed(int skillSlot) {
        if (skillSlot >= 1 && skillSlot <= 3) isUsed[skillSlot - 1] = true;
    }
    public boolean allSkillsUsed() {
        for (boolean used : isUsed) if (!used) return false;
        return true;
    }
    public abstract String useSkill(int skillNumber, Character enemy);
    public String takeDamage(int dmg){
        if (dmg > 0) {
            hp -= dmg;
            if (hp < 0) hp = 0;
        }
        return name +  " takes " + dmg + " damage!\n" + name + " has "  + hp + " HP remaining!";
    }
    public boolean isSkillAvailable(int skillNumber) {
        return skillCooldowns[skillNumber - 1] == 0;
    }
    public void reduceCooldowns() {
        for (int i = 0; i < skillCooldowns.length; i++) {
            if (skillCooldowns[i] > 0) {
                skillCooldowns[i]--;
            }
        }
    }
    public void resetCooldowns() {
        for (int i = 0; i < skillCooldowns.length; i++) {
            skillCooldowns[i] = 0;
        }
    }
    public void updateAnimations(){
        if(idleSprite != null) idleSprite.update();
        updateSpriteArray(skill1Sprite);
        updateSpriteArray(skill2Sprite);
        updateSpriteArray(skill3Sprite);
    }
    private void updateSpriteArray(Sprite[] s) {
        if (s != null) { for (Sprite frame : s) frame.update(); }
    }
    public String displaySkills() { return name + " has no skills to display."; }
}






