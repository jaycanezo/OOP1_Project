import java.util.Random;

public class Main {
    public static void main(String args[]){
        //Character objChar = new Character(); already abstract cannot be instantiated
        Layla objLayla = new Layla(300);
        Balmond objBalmond = new Balmond(500);

        /*objChar.displayName();
        objChar.setHp(500);
        System.out.println("HP: "+objChar.getHp());*/
        int num = 1;
        int i=0;
        while(objLayla.getHp()>=0 || objBalmond.getHp()>=0){
            System.out.println("-----------------TURN "+(num+i)+"-----------------");
            System.out.println();
            objLayla.displayName();
            int damage1 = objLayla.skill2();

            System.out.println();

            objBalmond.displayName();
            //Simple AI attack
            Random random = new Random();
            int attack = random.nextInt(2)+1;
            int damage2=0;
            if(attack ==1){
                damage2 = objBalmond.skill1();
            } else if (attack ==2){
                damage2 = objBalmond.skill2();
            }

            //Damage effect
            objLayla.setHp(objLayla.getHp() - damage2);
            objBalmond.setHp(objBalmond.getHp() - damage1);

            System.out.println();
            System.out.println("Updtated Hp:");
            System.out.println();

            //Updated Hp
            objLayla.displayName();

            System.out.println();

            objBalmond.displayName();

            System.out.println();

            //show("Jay");
            //show(19);
            //show(10,20);
            i++;
        }
        
        if(objLayla.getHp()>objBalmond.getHp()){
            System.out.println("Layla Wins!");
        } else {
            System.out.println("Balmond Wins!");
        }
    }
    //Overloading
    public static void show(String name){
        System.out.println("Name: "+name);
    }
    
    public static void show(int age){
        System.out.println("Age: "+age);
    }
    
    public static void show(int num1, int num2){
        System.out.println("Sum: "+(num1+num2));
    }
}


