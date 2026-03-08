package EchoesOfTheOath.Characters;
import EchoesOfTheOath.UI.MusicPlayer;

public class Sarukdal extends Character{
    MusicPlayer bgm = new MusicPlayer();

    public Sarukdal(){
        super("Sarukdal"," DEMON REALM < NATION 3 > - MINI BOSS", 1400, 10);
    }


    @Override
    public String useSkill(int skillNumber, Character enemy) {
        int[] cd = getSkillCooldowns(); // get cooldown array
        int dmg = 0;
        StringBuilder msg = new StringBuilder();
        //for skill defaulting if random skill is on cooldown
        if (cd[skillNumber - 1] > 0 && skillNumber != 1) {
            skillNumber = 1; //defaults to basic atk
        }

        switch (skillNumber) {
            case 1:
                bgm.playSFX("SARUKDAL -- Warden’s Grasp.wav");
                dmg = (random.nextInt(65 - 35 + 1) + 35) * getLevel();
                msg.append(getName()+ " uses Basic Skill: Warden's Grasp!\n");
                msg.append("Sarukdal hurls spectral chains at you, attempting to seize you.");
                break;
            case 2:
                bgm.playSFX("SARUKDAL -- Abyssal Lament.wav");
                dmg = (random.nextInt(105 - 80 + 1) + 80) * getLevel();
                msg.append(getName()+ " uses Advanced Skill: Abyssal Lament!\n");
                msg.append("Dark flames spread across the battlefield, scorching everything around you.");
                cd[skillNumber - 1] = 2;
                break;
            case 3:
                bgm.playSFX("SARUKDAL -- Judgement of the Forsaken.wav");
                dmg = (random.nextInt(300 - 180 + 1) + 180) * getLevel();
                msg.append(getName() + " uses Ultimate: Judgment of the Forsaken!\n");
                msg.append("Glowing fire blades descend from the sky, targeting you with devastating force.");
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

