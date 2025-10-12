package EchoesOfTheOath.Characters;

public class Archivist extends Character {
    public Archivist(){
        super("The Archivist", 3000, 220, 4);
    }

    @Override public void useSkill(int skillNumber, Character enemy){
        int dmg=0;
        switch (skillNumber){
            case 1:
                dmg=random.nextInt(21)+30+20;
                System.out.println(getName() + " uses Basic Skill:The Panoptic Eye!");
                System.out.println("Projects a pale, shimmering after-image of the target two seconds into the future. Instantly drawing blood not at the target, but at its future shadow.");
                break;
            case 2:
                dmg=random.nextInt(70)+50+20;
                System.out.println(getName()+" uses Advanced Skill: Temporary Relief!");
                System.out.println("The Archivist briefly turns invisible by merging with the kingdom’s records, giving enemies a false sense of safety before unleashing a powerful attack that strikes them with the force of accumulated, unseen debts.");
                break;
            case 3:
                dmg=random.nextInt((100)+150+80*4);
                System.out.println(getName()+" uses Ultimate Skill: Enforced Decree!");
                System.out.println("The Archivist summons a devastating storm of spectral scrolls revealing the kingdom’s sins, overwhelming and tearing apart his opponent as he seeks to claim them as his final possession.");
                break;
        }

        enemy.takeDamage(dmg);
    }

    @Override public void takeDamage(int dmg){
        super.takeDamage(dmg);

        System.out.println(getName()+" has "+getHp()+" HP remaining!");
    }
}
