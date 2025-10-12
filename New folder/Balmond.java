import java.util.Random;
public class Balmond extends Character {
    private int hp;
    
    public Balmond(int hp){
        this.hp=hp;
    }
    
    @Override
    public void setHp(int hp){ //Concrete method bcs theres no abstract keyword
        this.hp=hp;
    }
    
    //Getter - Accessor
    @Override
    public int getHp(){ //Concrete method bcs theres no abstract keyword
        return hp;
    }
    
    @Override //Annotation - Not Required
    public void displayName(){
        System.out.println("BALMOND");
        System.out.println("HP: "+getHp());
        System.out.println("-----------------");
    }
    
    @Override
    public int skill1(){
        Random random = new Random();
        int damageSkill = random.nextInt(21);
        System.out.println("Balmond used skill 1(Soul Dash!) dealt " + damageSkill +" damage.");
        return damageSkill;
    }
    
    @Override
    public int skill2(){
        Random random = new Random();
        int min = 30;
        int max = 50;
        int damageSkill2 = random.nextInt(max-min)+min;
        System.out.println("Balmond used skill 2(Turn Around!) dealt " + damageSkill2 +" damage.");
        return damageSkill2;
    }
}