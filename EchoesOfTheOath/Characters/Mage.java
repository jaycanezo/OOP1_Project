package EchoesOfTheOath.Characters;
import EchoesOfTheOath.UI.*;

public class Mage extends Character{
    boolean[] isUsed = new boolean[3];
    MusicPlayer bgm = new MusicPlayer();

    public Mage() {
        super("Mage", 1500, 1, "Mage");
        this.idleSprite = new Sprite("/EchoesOfTheOath/Resources/Mage.png", 500, 500, 1);
        this.skill1Sprite = new Sprite[]{new Sprite("/EchoesOfTheOath/Resources/Mage_Fireball.png", 128, 128, 7)};
        this.skill2Sprite = new Sprite[]{new Sprite("/EchoesOfTheOath/Resources/Mage_HeatfireSurge.png", 128, 128, 12)};
        this.skill3Sprite = new Sprite[]{new Sprite("/EchoesOfTheOath/Resources/Mage_AstralCataclysm1.png", 192, 128, 12),
                                        new Sprite("/EchoesOfTheOath/Resources/Mage_AstralCataclysm2.png", 128, 128, 15),
                                        new Sprite("/EchoesOfTheOath/Resources/Mage_AstralCataclysm3.png", 128, 128, 7)
        };
    }


    @Override
    public String useSkill(int skillNumber, Character enemy){
        if(skillNumber<1||skillNumber>3){
            return "Invalid Skill display.\n";
        }

        if (!isSkillAvailable(skillNumber)) {
            return "Skill is on cooldown! " + getSkillCooldown(skillNumber) + " turn(s) remaining.";
        }

        int dmg=0;
        StringBuilder msg = new StringBuilder();
        
        switch (skillNumber){
            case 1:
                bgm.playSFX("Mage_Fire_ball.wav");
                dmg = (random.nextInt(550 - 530 + 1) + 530 + 1000) * getLevel();
                msg.append(getName() + " uses Basic Skill: Fireball! ");
                msg.append("You hurl a blazing fireball at your enemy, dealing damage.\n");
                setSkillCooldown(1, 0);
                break;
            case 2:
                bgm.playSFX("Mage_Heatfire_Surge.wav");
                dmg = (random.nextInt(800 - 765 + 1) + 765 + 765) * getLevel();
                msg.append(getName() + " uses Advanced Skill: Heatfire Surge!\n");
                msg.append("You unleash a surge of intense flames, striking your enemy with great force.");
                setSkillCooldown(2, 2);
                break;
            case 3:
                bgm.playSFX("Mage_Astral_Cataclysm.wav");
                dmg = 1580 * getLevel();
                msg.append(getName() + " uses " + "Ultimate: Astral Cataclysm!\n");
                msg.append("You summon a massive fiery rock, obliterating everything in the area around your enemy.");
                setSkillCooldown(3, 4);
                break;
        }
        enemy.takeDamage(dmg);
        isUsed[skillNumber-1]=true;
        reduceCooldowns();

        msg.append(enemy.getName()).append(" took ").append(dmg).append(" damage!\n");
        return msg.toString();
    }


    public boolean allSkillsUsed(){
        for(boolean used:isUsed){
            if (!used){
                return false;
            }
        }
        return true;
    }


    /*@Override 
    public void takeDamage(int dmg){
        super.takeDamage(dmg);
    }*/

    @Override
    public String getSkillName(int skillNumber) {
        switch(skillNumber) {
            case 1: return "Fire Ball";
            case 2: return "Heatfire Surge";
            case 3: return "Astral Cataclysm";
            default: return "Unknown Skill";
        }
    }

    @Override
    public String getSkillDamageRange(int skillNumber) {
        int lvl = getLevel();
        switch(skillNumber) {
            case 1: 
                int min1 = (530 * lvl) + (1000 * lvl) + getSkill1Bonus();
                int max1 = (550 * lvl) + (1000 * lvl) + getSkill1Bonus();
                return min1 + " - " + max1;
            case 2: 
                int min2 = (765 * lvl) + (765 * lvl) + getSkill2Bonus();
                int max2 = (800 * lvl) + (765 * lvl) + getSkill2Bonus();
                return min2 + " - " + max2;
            case 3: 
                return String.valueOf((1580 * lvl) + getSkill3Bonus());
            default: return "0";
        }
    }


    public String displayCharacterInfo() {
        StringBuilder msg = new StringBuilder();

        msg.append("\nWielders of ancient knowledge, Mages command the elements and arcane forces. Fragile in body but unmatched in power, they bend magic at will to devastate their enemies from a distance.\n");

        msg.append("SKILLS:\n");
       
        for (int i = 1; i <= 3; i++) {
            String skillName = getSkillName(i);
            String damageRange = getSkillDamageRange(i);
            msg.append("(" + i + ") " + skillName + "\nDamage: " + damageRange + "\n");
        }

        return msg.toString();
    }


    @Override
    public String displaySkills() {
        StringBuilder msg = new StringBuilder();
        for (int i = 1; i <= 3; i++) {
            msg.append(i).append(". ").append(getSkillName(i))
               .append(" | Damage: ").append(getSkillDamageRange(i))
               .append(" | Cooldown: ").append(getSkillCooldown(i)).append("\n");
        }
        return msg.toString();
    }

}
