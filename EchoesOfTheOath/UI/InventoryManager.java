package EchoesOfTheOath.UI;

import EchoesOfTheOath.Characters.Character;
import EchoesOfTheOath.Characters.Item;

public class InventoryManager {
    public static String useItem(Item item, Character player) {
        String result = item.getName() + ": " + item.getRandomLine();
        String itemName = item.getName();

        if (itemName.equals("Wine") || itemName.equals("Milk")) {
            player.setHp(player.getHp() - 20);
        }
        else if (itemName.equals("Potion")) {
            player.setHp(player.getHp() + 50);
        } else if (itemName.equals("Bread")) {
            player.setHp(player.getHp() + 30);
        } else if (itemName.equals("Shrimp")) {
            player.setHp(player.getHp() + 20);
        } else if (itemName.equals("Cinnamon")) {
            player.setHp(player.getHp() + 25);
        } else if (itemName.equals("Latte")) {
            player.setHp(player.getHp() + 45);
        } else if (itemName.equals("Pudding")) {
            player.setHp(player.getHp() + 20);
        }
        else if (itemName.equals("Matcha")) {
            player.addSkillBonus(1, 20);
        }
        else if (itemName.equals("Clock")) {
            result = String.format("Clock: It's %d:%02d %s.", 
                (int)(Math.random()*12)+1, (int)(Math.random()*60), 
                (Math.random()>0.5?"AM":"PM"));
        }

        return result;
    }

    public static String sellItem(Item item, Character player) {
        if (item.getName().equals("Father")) {
            return "Selling your Father is prohibited.";
        }
        int sellPrice = item.getPrice() / 2;
        player.setGold(player.getGold() + sellPrice);
        return "Sold " + item.getName() + " for " + sellPrice + "G.";
    }
}