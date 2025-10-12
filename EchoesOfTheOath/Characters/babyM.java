package EchoesOfTheOath.Characters;

public class babyM extends Character{
    public babyM(){
        super("Baby M", 3000, 220, 2);
    }

    @Override public void useSkill(int skillNumber, Character enemy){
        int dmg=0;
        switch (skillNumber){
            case 1:
                dmg=random.nextInt(21)+30+20;
                System.out.println(getName() + " uses Tantrum Toss!");
                System.out.println("In a fit of screaming paranoia, the King wildly throws his golden rattle at his opponent.");
                break;
            case 2:
                dmg=random.nextInt(70)+50+20;
                System.out.println(getName()+" uses Feigned Faint!");
                System.out.println("Baby M dramatically falls to the ground, pretending to be unconscious, lulling his opponent into a false sense of security, only for the King to suddenly lash out with a surprise attack behind the back.");
                break;
            case 3:
                dmg=random.nextInt((100)+150+80*4);
                System.out.println(getName()+" uses his Ultimate:Enforced Decree!");
                System.out.println("The infant King abruptly stops all movement, his eyes going momentarily blank as The Archivist's will overrides his own. Galvanized by the direct command, the King executes an unnaturally stiff, powerful mechanical charge.");
                break;
        }

        enemy.takeDamage(dmg);
    }

    @Override public void takeDamage(int dmg){
        super.takeDamage(dmg);

        System.out.println(getName()+" has "+getHp()+" HP remaining!");
    }
    
}
