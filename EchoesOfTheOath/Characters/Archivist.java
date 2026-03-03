package EchoesOfTheOath.Characters;
import EchoesOfTheOath.Resources.MusicPlayer;

public class Archivist extends Character {
    MusicPlayer bgm = new MusicPlayer();

    public Archivist(){
        super("The Archivist", " HUMANAS < NATION 1 > - MAIN BOSS", 1400, 4);
    }


    @Override 
    public String useSkill(int skillNumber, Character enemy){
        int[] cd = getSkillCooldowns(); // get cooldown array
        int dmg = 0;
        StringBuilder msg = new StringBuilder();

        //for skill defaulting if random skill is on cooldown
        if (cd[skillNumber - 1] > 0 && skillNumber != 1) {
            skillNumber = 1; //defaults to basic atk
        }

        switch (skillNumber){
            case 1:
                bgm.playSFX("Archivist-The panoptic eye.wav");
                dmg = (random.nextInt(65 - 35 + 1) + 35) * getLevel();
                msg.append(getName()).append(" uses Basic Skill: The Panoptic Eye!\n")
                .append("A pale, shimmering after-image of you appears, and suddenly you feel a sharp cut—not on you, but on your own future shadow.");
                break;
            case 2:
                bgm.playSFX("Archivist-Temporary Relief.wav");
                dmg = (random.nextInt(105 - 80 + 1) + 80) * getLevel();
                msg.append(getName()).append(" uses Advanced Skill: Temporary Relief!\n")
                .append("The Archivist strikes you with the crushing force of accumulated, unseen debts, leaving you reeling.");
                cd[skillNumber - 1] = 2;
                break;
            case 3:
                bgm.playSFX("Archivist-The complete indictment.wav");
                dmg = (random.nextInt(300 - 180 + 1) + 180) * getLevel();
                msg.append(getName()).append(" Ultimate Skill: The Complete Indictment!\n")
                .append("A storm of spectral scrolls engulfs you, tearing at your body as the Archivist claims you as his final possession.");
                cd[skillNumber - 1] = 3;
                break;
        }   
        enemy.takeDamage(dmg);
        reduceCooldowns();

        return msg.toString();
    }
}
