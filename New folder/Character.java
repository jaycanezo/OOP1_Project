public abstract class Character {//Superclass
    //private int hp; //instance/global Variable
    
    /*public Character(int hp){ //Constructor
        
    }*/
    
    //Setter - Mutator
    public abstract void setHp(int hp);
    
    //Getter - Accessor
    public  abstract int getHp();
    
    public abstract void displayName(); //Abstract method
    public abstract int skill1();
    public abstract int skill2();
    
}