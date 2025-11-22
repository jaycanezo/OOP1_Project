package EchoesOfTheOath.Characters;


public class Mage extends Character{
    private boolean[] isUsed = new boolean[3];


    public Mage() {
        super("Mage", 1500, 1);
    }


    @Override
    public void useSkill(int skillNumber, Character enemy){
        if(skillNumber<1||skillNumber>3){
            return;
        }


        if (!isSkillAvailable(skillNumber)) {
            System.out.println("Skill is on cooldown! " + getSkillCooldown(skillNumber) + " turn(s) remaining.");
            return;
        }


        int dmg=0;
        switch (skillNumber){
            case 1:
                dmg=random.nextInt((21)+30+200)*getLevel();
                System.out.println(getName() + " uses Basic Skill: Fireball!");
                setSkillCooldown(1, 0);
                break;
            case 2:
                dmg=random.nextInt((70)+50+100)*getLevel();
                System.out.println(getName()+" uses Advanced Skill: Heat Surge!");
                setSkillCooldown(2, 2);
                break;
            case 3:
                dmg=random.nextInt((100)+1400+100)*getLevel();
                System.out.println(getName()+" uses Ultimate: Astral Cataclysm!");
                setSkillCooldown(3, 3);
                break;
        }


        enemy.takeDamage(dmg);
        isUsed[skillNumber-1]=true;
        reduceCooldowns();
    }


    public boolean allSkillsUsed(){
        for(boolean used:isUsed){
            if (!used){
                return false;
            }
        }
        return true;
    }


    @Override public void takeDamage(int dmg){
        super.takeDamage(dmg);


        System.out.println(getName()+" has "+getHp()+" HP remaining!");
    }


    public void displayCharacterInfo(){
        System.out.println("\nSwift and precise, Archers strike from afar with deadly accuracy. Masters of the bow, they can pierce armor, control the battlefield, and rain destruction upon enemies before they can even draw near.");
        System.out.println();
       
        System.out.println("SKILLS:");
       
        System.out.println("(1)Piercing Shot\n" + "Damage: " + 30*getLevel() + " - " + 50*getLevel() + " + " + 20*getLevel());
        System.out.println();
        System.out.println("(2)Volley of Nature\n" + "Damage: " + 50*getLevel() + " - " + 120*getLevel() + " + " + 20*getLevel());
        System.out.println();
        System.out.println("(3)Nature’s Wrath\n" + "Damage: " + 150*getLevel() + " - " + 250*getLevel() + " + " + 80*getLevel());
        System.out.println();
    }


}
