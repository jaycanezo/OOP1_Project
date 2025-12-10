package EchoesOfTheOath.Characters;
import EchoesOfTheOath.Resources.MusicPlayer;
public class Archer extends Character{
    private boolean[] isUsed = new boolean[3];
    MusicPlayer bgm = new MusicPlayer();

    public Archer(){
        super("Archer", 1300, 1);
    }


    @Override
    public void useSkill(int skillNumber, Character enemy){
        if(skillNumber<1||skillNumber>3){
            return;
        }

        if (!isSkillAvailable(skillNumber)) {
            System.out.println(YELLOW + "Skill is on cooldown! " + getSkillCooldown(skillNumber) + " turn(s) remaining." + RESET);
            return;
        }

        int dmg=0;
        switch (skillNumber){
            case 1:
                bgm.playSFX("Archer-Piercingshot.wav");
                dmg = (random.nextInt(45 - 15 + 1) + 15 + 25) * getLevel();
                System.out.println(BLUE + getName() + RESET + " uses " + PURPLE + "Basic Skill: Piercing Shot" + RESET + "!");
                System.out.println("You shoot a swift arrow at your foe.");
                setSkillCooldown(1, 0);
                break;
            case 2:
                bgm.playSFX("Archer-VolleyofNature.wav");
                dmg = (random.nextInt(95 - 70 + 1) + 70 + 20) * getLevel();
                System.out.println(BLUE + getName() + RESET + " uses " + PURPLE + "Advanced Skill: Volley of Nature" + RESET + "!");
                System.out.println("You fire multiple arrows, raining them down on your enemy.");
                setSkillCooldown(2, 3);
                break;
            case 3:
                bgm.playSFX("Archer-Nature'swrath.wav");
                dmg = (random.nextInt(230 - 105 + 1) + 105 + 80) * getLevel();
                System.out.println(BLUE + getName() + RESET + " uses " + PURPLE + "Ultimate: Nature's Wrath" + RESET + "!");
                System.out.println("You unleash four guiding arrows, striking your enemy with precision.");
                setSkillCooldown(3, 5);
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


    @Override 
    public void takeDamage(int dmg){
        super.takeDamage(dmg);
    }

    public String getSkillName(int skillNumber) {
        switch(skillNumber) {
            case 1: return "Piercing Shot";
            case 2: return "Volley of Nature";
            case 3: return "Nature's Wrath";
            default: return "Unknown Skill";
        }
    }


    public String getSkillDamageRange(int skillNumber) {
        switch(skillNumber) {
            case 1: return (15*getLevel()) + " - " + (45*getLevel()) + " + " + (25*getLevel());
            case 2: return (70*getLevel()) + " - " + (95*getLevel()) + " + " + (20*getLevel());
            case 3: return (105*getLevel()) + " - " + (230*getLevel()) + " + " + (80*getLevel());
            default: return "0";
        }
    }


    public void displayCharacterInfo() {
        System.out.println("\nSwift and precise, Archers strike from afar with deadly accuracy. Masters of the bow, they can pierce armor, control the battlefield, and rain destruction upon enemies before they can even draw near.");
        System.out.println();

        System.out.println("SKILLS:");
       
        for (int i = 1; i <= 3; i++) {
            String skillName = getSkillName(i);
            String damageRange = getSkillDamageRange(i);
            System.out.println("(" + i + ") " + PURPLE + skillName + RESET + "\nDamage: " + damageRange + "\n");
        }
    }

    @Override
    public void displaySkills() {
        System.out.println(BLUE + "------------------ " + getName() + "'s Skills ------------------" + RESET);
        System.out.println();

        for (int i = 1; i <= 3; i++) {
            String skillName = getSkillName(i);
            String damageRange = getSkillDamageRange(i);
            int cooldown = getSkillCooldown(i);
            String status;

            if (isSkillAvailable(i)) {
                status = GREEN + "Ready" + RESET;
            } else {
                status = YELLOW + "Cooldown: " + cooldown + " turn(s)" + RESET;
            }

            System.out.println(i + ". " + skillName + " | Damage: " + damageRange + " | " + status);
        }
    }
    
}

