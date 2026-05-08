package EchoesOfTheOath.UI;

import EchoesOfTheOath.Characters.Character;
import EchoesOfTheOath.Characters.Item;

public class InventoryManager {
    public static String useItem(Item item, Character player) {
        String result = item.getName() + ": " + item.getRandomLine();
        String itemName = item.getName();

        if (itemName.equals("Wine") || itemName.equals("Milk")) {
            player.setHp(Math.max(player.getHp() - 50, 0));
        }
        else if (itemName.equals("Jay's Potion")) {
            player.setHp(Math.max(player.getHp() - 60, 0));
        }
        else if (itemName.equals("Potion") || itemName.equals("Kaina's Coffee")) {
            player.setHp(Math.min(player.getHp() + 50, player.getMaxHp()));
        } 
        else if (itemName.equals("Bread")) {
            player.setHp(Math.min(player.getHp() + 30, player.getMaxHp()));
        } 
        else if (itemName.equals("Cinnamon")) {
            player.setHp(Math.min(player.getHp() + 25, player.getMaxHp()));
        }
        else if (itemName.equals("Pudding")) {
            player.setHp(Math.min(player.getHp() + 20, player.getMaxHp()));
        }
        else if (itemName.equals("Ring of Focus")) {
            if (!item.isEquipped()) {
                boolean alreadyEquipped = false;
                for (Item i : player.getInventory()) {
                    if (i.getName().equals("Ring of Focus") && i.isEquipped()) {
                        alreadyEquipped = true;
                        break;
                    }
                }
                
                if (alreadyEquipped) {
                    result = "You already have a Ring of Focus equipped!";
                } else {
                    item.setEquipped(true);
                    player.addSkillBonus(1, 20); 
                    result = "Ring of Focus: Equipped! (+20 Skill 1 Bonus)";
                }
            } else {
                item.setEquipped(false);
                player.addSkillBonus(1, -20);
                result = "Ring of Focus: Unequipped! (-20 Skill 1 Bonus)";
            }
        }

        else if (itemName.equals("Eden's Wand")) {
            if (!item.isEquipped()) {
                boolean alreadyEquipped = false;
                for (Item i : player.getInventory()) {
                    if (i.getName().equals("Eden's Wand") && i.isEquipped()) {
                        alreadyEquipped = true;
                        break;
                    }
                }
                
                if (alreadyEquipped) {
                    result = "You already have Eden's Wand equipped!";
                } else {
                    item.setEquipped(true);
                    player.addSkillBonus(3, 40); 
                    result = "Eden's Wand: Equipped! (+40 Ultimate Bonus)";
                }
            } else {
                item.setEquipped(false);
                player.addSkillBonus(3, -40);
                result = "Eden's Wand: Unequipped! (-40 Ultimate Bonus)";
            }
        }
        else if (itemName.equals("Clock")) {
            result = String.format("Clock: It's %d:%02d %s.", 
                (int)(Math.random()*12)+1, (int)(Math.random()*60), 
                (Math.random()>0.5?"AM":"PM"));
        }

        return result;
    }

    public static String sellItem(Item item, Character player) {
        if (item.getName().equals("Ring of Focus") && item.isEquipped()) {
            player.addSkillBonus(1, -20); 
            item.setEquipped(false);
        }
        if (item.getName().equals("Eden's Wand") && item.isEquipped()) {
            player.addSkillBonus(3, -40); 
            item.setEquipped(false);
        }
        
        int sellPrice = item.getPrice() / 2;
        player.setGold(player.getGold() + sellPrice);
        return "Sold " + item.getName() + " for " + sellPrice + "G.";
    }
}