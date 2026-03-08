package EchoesOfTheOath.Characters;
import EchoesOfTheOath.UI.MusicPlayer;

public class Lunareth extends Character {
    MusicPlayer bgm = new MusicPlayer();

    public Lunareth(){
        super("Lunareth"," VEYORA < NATION 2 > - MAIN BOSS", 1400, 8);
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
                bgm.playSFX("LUNARETH moonpierce shot.wav");
                dmg = (random.nextInt(65 - 35 + 1) + 35) * getLevel();
                msg.append(getName() + " uses Basic Skill: Moonpierce Shot!\n");
                msg.append("Lunareth fires a luminous arrow imbued with lunar energy straight at you.");
                break;
            case 2:
                bgm.playSFX("LUNARETH Binding Roots_noisles.wav");
                dmg = (random.nextInt(105 - 80 + 1) + 80) * getLevel();
                msg.append(getName()+ " uses Advanced Skill: Binding Roots!\n");
                msg.append("Enchanted roots shoot up from the ground, ensnaring you and pulling you down!");
                cd[skillNumber - 1] = 2;
                break;
            case 3:
                bgm.playSFX("LUNARETH Eclipse Volley_noisles.wav");
                dmg = (random.nextInt(300 - 180 + 1) + 180) * getLevel();
                msg.append(getName() + " uses Ultimate: Eclipse Volley!\n");
                msg.append("Lunareth leaps into the air, summoning the eclipse to rain a storm of arrows directly on you. Brace yourself!");
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

