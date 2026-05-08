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
        stock.add(new Item("Mystic Cat", "A very fluffy cat.", "cat.png", 20, 
            new String[]{"Meow.", "Purrr...", "*Stares at a ghost behind you*"}, false));
        
        stock.add(new Item("Ice", "It's cold... and dripping.", "ice.png", 10, 
            new String[]{"The ice melted in your hands and is gone now."}, true));
        
        stock.add(new Item("Wine", "A fine vintage. Use with caution.", "wine.png", 50, 
            new String[]{"You drink the wine. Your head spins... (HP Reduced)", "Ouch, that's a strong kick! (HP Reduced)"}, true));
        
        stock.add(new Item("Milk", "Fresh milk.", "milk.png", 15, 
            new String[]{"Your stomach gurgles aggressively, you're lactose intolerant... (HP Reduced)", "Lactose is your true enemy. (HP Reduced)"}, true));

        stock.add(new Item("Bird", "A colorful little friend.", "bird.png", 50, 
            new String[]{"The bird chirped once and flew away into the sky!"}, true));

        stock.add(new Item("Eyeball", "A squishy, floating eye.", "eyeball.png", 30, 
            new String[]{"The truth is revealed: it's all just Java code."}, false));

        stock.add(new Item("News", "The Daily Drifter.", "news.png", 5, 
            new String[]{"Witnesses claim the adventurer spent three hours jumping against a wall. Experts call it 'concussion-based training'.", "Potions prices are up 200%. Apparently, 'Magic Water' doesn't just fall from the sky.", "Local Merchant caught selling 'Invisibility Cloaks' that were actually just... nothing. He is currently invisible to the police."}, false));
        
        stock.add(new Item("Mirror", "A shiny silver mirror.", "mirror.png", 50, 
            new String[]{"Gosh, you look spectacular today!", "You look like a protagonist."}, false));

        stock.add(new Item("Clock", "An ancient timepiece.", "clock.png", 10, 
            new String[]{"The time is: " + (int)(Math.random()*12+1) + ":" + (int)(Math.random()*59) + " AM.", "Tick... tock..."}, false));

        stock.add(new Item("Bread", "Warm fresh bread.", "bread.png", 30, 
            new String[]{"Technically edible. If you close your eyes, it almost tastes like hope. Almost. (HP Increased)"}, true));

        stock.add(new Item("Cinnamon", "Sweet and spicy roll.", "cinnamon.png", 25, 
            new String[]{"The sugar rush gives you energy! (HP Increased)", "Deliciously sticky. (HP Increased)"}, true));

        stock.add(new Item("Pudding", "A wobbly treat.", "pudding.png", 20, 
            new String[]{"*Wiggle wiggle* (HP Increased)", "It slides down your throat. So smooth. (HP Increased)"}, true));
            
        stock.add(new Item("Potion", "Standard Health Potion.", "potion.png", 50, 
            new String[]{"Your wounds close up. Tastes like cherries. (HP Increased)"}, true));

        stock.add(new Item("Jay's Potion", "A glowing blue liquid. The merchant looked nervous selling this.", "blue_potion.png", 50, 
            new String[]{"Ack! This isn't a health potion, it's poison! Jay mixed up the labels! (HP Reduced)"}, true));

        stock.add(new Item("Kaina's Coffee", "A perfectly brewed cup with a cute latte art heart.", "coffee.png", 50, 
            new String[]{"Warm, caffeinated perfection. You feel completely awake! (HP Increased)", "The espresso kicks in immediately! (HP Increased)"}, true));

        stock.add(new Item("Althea's Astral Compass", "A mystical compass that points to... somewhere.", "compass.png", 20, 
            new String[]{"The needle spins wildly before pointing directly at your heart.", "The compass whispers: 'Turn left at the next shadowy figure.'", "It's pointing firmly to the right direction."}, false));

        stock.add(new Item("Forgotten Key", "An ornate key. Which door does this even open?", "key.png", 30, 
            new String[]{"You try putting it in an imaginary keyhole. Nothing happens.", "Maybe the real treasure is the keys we found along the way."}, false));

        stock.add(new Item("Eden's Wand", "A beautifully carved staff radiating pure magic.", "wand.png", 250, 
            new String[]{"Sparks fly as you grip the handle. (+40 Ultimate Bonus)", "You feel a surge of devastating power! (+40 Ultimate Bonus)"}, false));

        stock.add(new Item("Ring of Focus", "A golden dragon band that sharpens the mind and expands your range.", "ring.png", 200, 
            new String[]{"The dragon's eyes glow. (+20 Skill 1 Bonus)", "You slip the ring on. Your combat range expands. (+20 Skill 1 Bonus)"}, false));

        stock.add(new Item("April's Words of Wisdom", "Someone that occasionally whispers to you.", "weirdo.png", 5, 
            new String[]{
                "When life gives you lemons... squeeze them directly into the eyes of your enemies.", "If you ever feel alone, don’t", "The sooner you get behind, the more time you have to catch up.",
                "It's never too late to give up.", "Trying is the first step to failing!", "You tried your best and you failed. The lesson is: never try.",
                "Don’t be a part of the problem. Be the whole problem." 
            }, false));
    }

    public String buyItem(int index, Character player) {
        if (index < stock.size()) {
            Item item = stock.get(index);
            
            boolean alreadyOwned = false;
            for (Item invItem : player.getInventory()) {
                if (invItem.getName().equals(item.getName())) {
                    alreadyOwned = true;
                    break;
                }
            }

            if (alreadyOwned && !item.isConsumable()) {
                return "You already own the " + item.getName() + "!";
            }

            if (player.getGold() >= item.getPrice()) {
                if (player.getInventory().size() < player.maxInventorySize) {
                    player.setGold(player.getGold() - item.getPrice());
                    item.setEquipped(false); 
                    player.getInventory().add(item);
                    return "You bought " + item.getName() + "!";
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