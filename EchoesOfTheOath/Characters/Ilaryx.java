package EchoesOfTheOath.Characters;
import EchoesOfTheOath.UI.MusicPlayer;

public class Ilaryx extends Character {
    MusicPlayer bgm = new MusicPlayer();

    public Ilaryx(){
        super("Ilaryx"," VEYORA < NATION 2 > - MINI BOSS", 1400, 6);
    }


    @Override
    public String useSkill(int skillNumber, Character enemy) {
        int[] cd = getSkillCooldowns();
        int dmg = 0;
        StringBuilder msg = new StringBuilder();

        if (cd[skillNumber - 1] > 0 && skillNumber != 1) {
            skillNumber = 1;
        }

        switch (skillNumber) {
            case 1:
                bgm.playSFX("ILARYX quickshot_noisles.wav");
                dmg = (random.nextInt(65 - 35 + 1) + 35) * getLevel();
                msg.append(getName()+ " uses Basic Skill: Quick Shot!\n");
                msg.append("Ilaryx fires a Double Tap! Two arrows blaze toward you.");
                break;
            case 2:
                bgm.playSFX("ILARYX hunters trap_noisles.wav");
                dmg = (random.nextInt(105 - 80 + 1) + 80) * getLevel();
                msg.append(RED + getName() + RESET + " uses " + PURPLE + "Advanced Skill: Hunter's Trap" + RESET + "!");
                msg.append("Ilaryx activates an Invisible Snare! You feel the trap closing in around you.");
                cd[skillNumber - 1] = 2;
                break;
            case 3:
                bgm.playSFX("ILARYX Arrow of the Silverfang_noisles.wav");
                dmg = (random.nextInt(300 - 180 + 1) + 180) * getLevel();
                msg.append(RED + getName() + RESET + " uses " + PURPLE + "Ultimate: Arrow of the Silverfang" + RESET + "!");
                msg.append("Ilaryx channels silver magic into her bow, firing a massive, charged arrow directly at you. Brace yourself!");
                cd[skillNumber - 1] = 3;
                break;
        }
        enemy.takeDamage(dmg);
        reduceCooldowns();

        return msg.toString();
    }


    /*@Override 
    public void takeDamage(int dmg){
        super.takeDamage(dmg);
    }*/
   
}
