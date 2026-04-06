package EchoesOfTheOath.UI;

import EchoesOfTheOath.Characters.Character;
import EchoesOfTheOath.Characters.Item;
import java.util.ArrayList;

public class Shop {
    private final ArrayList<Item> stock = new ArrayList<>();

    public Shop() {
        setupStock();
    }

    private void setupStock() {
        stock.add(new Item("Father", "Your father, he's watching your progress.", "father.png", 100, 
            new String[]{"I'm not mad, son. Just... disappointed.", "Go ahead. Keep playing. I'll just sit here and think about where I went wrong.", "You did great son.", "Don't look at me, look at your stats. Pitiful.", "....mhmm..."}, false));
        stock.add(new Item("Mystic Cat", "A very fluffy cat.", "cat.png", 200, 
            new String[]{"Meow.", "Purrr...", "*Stares at a ghost behind you*"}, false));
        stock.add(new Item("Ice", "It's cold... and dripping.", "ice.png", 10, 
            new String[]{"The ice melted in your hands and is gone now."}, true));
        stock.add(new Item("Wine", "A fine vintage. Use with caution.", "wine.png", 80, 
            new String[]{"You drink the wine. Your head spins... (HP Reduced)", "Ouch, that's a strong kick! (HP Reduced)"}, true));
        stock.add(new Item("Milk", "Fresh milk.", "milk.png", 15, 
            new String[]{"Your stomach gurgles aggressively... (HP Reduced)", "Lactose is your true enemy. (HP Reduced)"}, true));
        stock.add(new Item("Bird", "A colorful little friend.", "bird.png", 150, 
            new String[]{"The bird chirped once and flew away into the sky!"}, true));
        stock.add(new Item("Eyeball", "A squishy, floating eye.", "eyeball.png", 300, 
            new String[]{"The truth is revealed: it's all just Java code."}, false));
        stock.add(new Item("News", "The Daily Drifter.", "news.png", 5, 
            new String[]{"Witnesses claim the adventurer spent three hours jumping against a wall. Experts call it 'concussion-based training'.", "Potions prices are up 200%. Apparently, 'Magic Water' doesn't just fall from the sky.", "Local Merchant caught selling 'Invisibility Cloaks' that were actually just... nothing. He is currently invisible to the police."}, false));
        stock.add(new Item("Mirror", "A shiny silver mirror.", "mirror.png", 50, 
            new String[]{"Gosh, you look spectacular today!", "You look like a protagonist."}, false));
        stock.add(new Item("Clock", "An ancient timepiece.", "clock.png", 100, 
            new String[]{"The time is: " + (int)(Math.random()*12+1) + ":" + (int)(Math.random()*59) + " AM.", "Tick... tock..."}, false));
        stock.add(new Item("Bouquet", "Smells nice.", "bouquet.png", 40, 
            new String[]{"It smells incredibly nice.", "The flowers seem to hum a soft tune."}, false));
        stock.add(new Item("Duck", "It looks suspicious.", "duck.png", 20, 
            new String[]{"Quack! (It's laughing at you)", "The duck judgingly watches you."}, false));
        stock.add(new Item("Capy", "The chillest creature alive.", "capy.png", 500, 
            new String[]{"Ahh, so cute!", "Maximum chill achieved."}, false));
        stock.add(new Item("Bread", "Warm fresh bread.", "bread.png", 30, 
            new String[]{"Technically edible. If you close your eyes, it almost tastes like hope. Almost. (HP Increased)"}, true));
        stock.add(new Item("Shrimp", "A tiny crustacean.", "shrimp.png", 30, 
            new String[]{"You ate the shrimp. It was... shrimply delicious. (HP Increased)", "You feel a tiny bit faster. (HP Increased)"}, true));
        stock.add(new Item("Cinnamon", "Sweet and spicy roll.", "cinnamon.png", 25, 
            new String[]{"The sugar rush gives you energy! (HP Increased)", "Deliciously sticky. (HP Increased)"}, true));
        stock.add(new Item("Latte", "Caffeine for the soul.", "latte.png", 45, 
            new String[]{"You drank the latte. (HP Increased)", "Energy restored! (HP Increased)"}, true));
        stock.add(new Item("Pudding", "A wobbly treat.", "pudding.png", 20, 
            new String[]{"*Wiggle wiggle* (HP Increased)", "It slides down your throat. So smooth. (HP Increased)"}, true));
        stock.add(new Item("Matcha", "Pure green tea power.", "matcha.png", 55, 
            new String[]{"You feel calm and focused. (+20 Skill 1 Bonus)", "The bitter taste of productivity.(+20 Skill 1 Bonus)"}, true));
        stock.add(new Item("Potion", "Standard Health Potion.", "potion.png", 50, 
            new String[]{"Your wounds close up. Tastes like cherries. (HP Increased)"}, true));
    }

    public String buyItem(int index, Character player) {
        if (index < stock.size()) {
            Item item = stock.get(index);
            if (player.getGold() >= item.price) {
                if (player.inventory.size() < player.maxInventorySize) {
                    player.setGold(player.getGold() - item.price);
                    player.inventory.add(item);
                    return "You bought " + item.name + "!";
                } else {
                    return "Your inventory is full!";
                }
            } else {
                return "Not enough gold!";
            }
        }
        return "";
    }

    public ArrayList<Item> getStock() {
        return stock;
    }
}