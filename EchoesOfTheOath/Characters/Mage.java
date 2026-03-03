package EchoesOfTheOath.Characters;
import EchoesOfTheOath.Resources.MusicPlayer;

public class Mage extends Character{
    boolean[] isUsed = new boolean[3];
    MusicPlayer bgm = new MusicPlayer();

    public Mage() {
        super("Mage", 1500, 1);
    }


    @Override
    public String useSkill(int skillNumber, Character enemy){
        if(skillNumber<1||skillNumber>3){
            return "Invalid Skill display.\n";
        }

        if (!isSkillAvailable(skillNumber)) {
            return "Skill is on cooldown! " + getSkillCooldown(skillNumber) + " turn(s) remaining." + RESET;
        }

        int dmg=0;
        StringBuilder msg = new StringBuilder();
        
        switch (skillNumber){
            case 1:
                bgm.playSFX("Mage - Fire ball1.wav");
                dmg = (random.nextInt(550 - 530 + 1) + 530 + 1000) * getLevel();
                msg.append(BLUE + getName() + RESET + " uses " + PURPLE + "Basic Skill: Fireball" + RESET + "!");
                msg.append("You hurl a blazing fireball at your enemy, dealing damage.");
                setSkillCooldown(1, 0);
                break;
            case 2:
                bgm.playSFX("Mage - Heatfire Surge.wav");
                dmg = (random.nextInt(800 - 765 + 1) + 765 + 765) * getLevel();
                msg.append(BLUE + getName() + RESET + " uses " + PURPLE + "Advanced Skill: Heatfire Surge" + RESET + "!");
                msg.append("You unleash a surge of intense flames, striking your enemy with great force.");
                setSkillCooldown(2, 2);
                break;
            case 3:
                bgm.playSFX("Mage-Astral Cataclysm.wav");
                dmg = 1580 * getLevel();
                msg.append(BLUE + getName() + RESET + " uses " + PURPLE + "Ultimate: Astral Cataclysm" + RESET + "!");
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


    public String getSkillName(int skillNumber) {
        switch(skillNumber) {
            case 1: return "Fire Ball";
            case 2: return "Heatfire Surge";
            case 3: return "Astral Cataclysm";
            default: return "Unknown Skill";
        }
    }


    public String getSkillDamageRange(int skillNumber) {
        switch(skillNumber) {
            case 1: return (530*getLevel()) + " - " + (550*getLevel()) + " + " + (1000*getLevel());
            case 2: return (765*getLevel()) + " - " + (800*getLevel()) + " + " + (765*getLevel());
            case 3: return String.valueOf(1580*getLevel());
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
            msg.append("(" + i + ") " + PURPLE + skillName + RESET + "\nDamage: " + damageRange + "\n");
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
