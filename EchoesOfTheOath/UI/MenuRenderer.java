package EchoesOfTheOath.UI;

import EchoesOfTheOath.Characters.Character;
import EchoesOfTheOath.Characters.Item;
import java.awt.*;
import java.util.ArrayList;

public class MenuRenderer {

    public static void drawInventory(Graphics2D g2, Character player, int slotCol, int slotRow, int scrollOffset) {
        int screenW = 1080;
        int totalW = 980;
        int totalH = 360; 
        int x = 50;
        int y = 70;
        int statW = 320;
        int itemW = totalW - statW - 15;

        drawWindow(g2, x, y, statW, totalH, "CHARACTER STATS");
        int sx = x + 30;
        int sy = y + 65;

        g2.setFont(new Font("Georgia", Font.PLAIN, 12));
        g2.setColor(Color.WHITE);

        g2.drawString("NAME:  " + player.getName(), sx, sy);
        g2.drawString("CLASS: " + player.getClassType(), sx, sy + 25);
        g2.drawString("LVL:   " + player.getLevel(), sx, sy + 50);
        g2.drawString("GOLD:  " + player.getGold(), sx, sy + 75);
        g2.drawString("HP:", sx, sy + 100);
        drawSidebarHPBar(g2, sx, sy + 115, player, statW - 50);

        g2.drawString("SKILLS:", sx, sy + 190);

        if (player.getSkillName(1) != null) 
            g2.drawString("• " + player.getSkillName(1) + " (" + player.getSkillDamageRange(1) + ")", sx, sy + 215);

        if (player.getSkillName(2) != null) 
            g2.drawString("• " + player.getSkillName(2) + " (" + player.getSkillDamageRange(2) + ")", sx, sy + 240);

        if (player.getSkillName(3) != null) 
            g2.drawString("• " + player.getSkillName(3) + " (" + player.getSkillDamageRange(3) + ")", sx, sy + 265);

        int ix = x + statW + 15;

        drawWindow(g2, ix, y, itemW, totalH, "INVENTORY");
        renderItems(g2, player.getInventory(), ix + 45, y + 80, slotCol, slotRow, scrollOffset, 5);
    }

    public static void drawShop(Graphics2D g2, int gold, ArrayList<Item> stock, int slotCol, int slotRow, String resultMsg) {
        int screenW = 1080;
        int totalW = 980;
        int totalH = 360;
        int x = (screenW - totalW) / 2;
        int y = 70;

        drawWindow(g2, x, y, totalW, totalH, "SHOP | Gold: " + gold);
        renderItems(g2, stock, x + 40, y + 80, slotCol, slotRow, 0, 5); 

        int itemIndex = slotCol + (slotRow * 5);

        if (itemIndex < stock.size()) {
            Item selected = stock.get(itemIndex);
            int descX = x + 450;
            int descY = y + 100;

            g2.setColor(Color.YELLOW);
            g2.setFont(new Font("Georgia", Font.BOLD, 22));
            g2.drawString(selected.getName(), descX, descY);

            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Georgia", Font.PLAIN, 18));
            g2.drawString("Price: " + selected.getPrice() + "G", descX, descY + 30);
            
            g2.setFont(new Font("Georgia", Font.ITALIC, 16));
            g2.drawString(selected.getDescription(), descX, descY + 70);
            
            if (resultMsg != null && !resultMsg.isEmpty() && !resultMsg.contains("Welcome")) {
                if (resultMsg.contains("bought")) {
                    g2.setColor(new Color(175, 238, 171));
                } else {
                    g2.setColor(new Color(249, 152, 155));
                }

                g2.setFont(new Font("Georgia", Font.BOLD, 20));
                g2.drawString(resultMsg, x + 450, y + 250);
            }
        }
    }

    private static void drawWindow(Graphics2D g2, int x, int y, int w, int h, String title) {
        g2.setColor(new Color(10, 10, 10, 240)); 
        g2.fillRoundRect(x, y, w, h, 15, 15);
        
        g2.setColor(new Color(181, 153, 110));
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x, y, w, h, 15, 15);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Georgia", Font.BOLD, 20));
        g2.drawString(title, x + 20, y + 35);
    }

    private static void renderItems(Graphics2D g2, ArrayList<Item> list, int startX, int startY, int slotCol, int slotRow, int scrollOffset, int cols) {
        if (list == null) 
            return;

        int slotSize = 48;
        int xSpacing = 65; 
        int ySpacing = 65; 
        int maxRows = 4; 

            for (int i = 0; i < cols * maxRows; i++) {
                int col = i % cols;
                int row = i / cols;
                int dx = startX + (col * xSpacing);
                int dy = startY + (row * ySpacing);

                g2.setColor(new Color(40, 40, 40)); 
                g2.fillRoundRect(dx, dy, slotSize, slotSize, 10, 10);
                
                g2.setColor(new Color(181, 153, 110, 80)); 
                g2.setStroke(new BasicStroke(1));
                g2.drawRoundRect(dx, dy, slotSize, slotSize, 10, 10);

                int itemIndex = col + ((row + scrollOffset) * cols);

                if (slotCol == col && slotRow == (row + scrollOffset)) {
                    g2.setColor(Color.YELLOW);
                    g2.setStroke(new BasicStroke(3));
                    g2.drawRoundRect(dx, dy, slotSize, slotSize, 10, 10);
                }

                if (itemIndex < list.size()) {
                    Item item = list.get(itemIndex);

                    if (item != null && item.getImage() != null) {
                        g2.drawImage(item.getImage(), dx + 4, dy + 4, slotSize - 8, slotSize - 8, null);
                    }
                }
            }
    }

    private static void drawSidebarHPBar(Graphics2D g2, int x, int y, Character p, int barMaxWidth) {
        int barHeight = 18;
        double percent = Math.min((double)p.getHp() / p.getMaxHp(), 1.0);

        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(x, y, barMaxWidth, barHeight);
        
        g2.setColor(percent > 0.3 ? Color.GREEN : Color.RED);
        g2.fillRect(x, y, (int)(barMaxWidth * percent), barHeight);
        
        g2.setColor(Color.WHITE);
        g2.drawRect(x, y, barMaxWidth, barHeight);
        g2.drawString(p.getHp() + "/" + p.getMaxHp(), x, y + 40);
    }

    public static void drawSubMenu(Graphics2D g2, int subMenuCursor) {
        int x = 520, y = 150, w = 130, h = 95; 

        g2.setColor(new Color(10, 10, 10, 250));
        g2.fillRoundRect(x, y, w, h, 12, 12);
        
        g2.setColor(new Color(181, 153, 110)); 
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x, y, w, h, 12, 12);
        
        g2.setFont(new Font("Georgia", Font.PLAIN, 16));
        int textX = x + 35;
        int startY = y + 30;
        int spacing = 25; 

        g2.setColor(Color.WHITE);
        g2.drawString("Interact", textX, startY);
        g2.drawString("Sell", textX, startY + spacing);
        g2.drawString("Delete", textX, startY + (spacing * 2));
        
        g2.setColor(Color.YELLOW);
        g2.drawString(">", x + 15, startY + (subMenuCursor * spacing));
    }

    public static void drawOptionsOverlay(Graphics2D g2, int cursorNum) {
        int screenW = 1080, screenH = 720;
        int w = 450, h = 320;
        int x = (screenW - w) / 2;
        int y = (screenH - h) / 2;

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, screenW, screenH);

        g2.setColor(new Color(181, 153, 110));
        g2.fillRoundRect(x, y, w, h, 20, 20);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(x, y, w, h, 20, 20);

        g2.setFont(new Font("Georgia", Font.BOLD, 32));
        g2.drawString("PAUSED", x + 155, y + 60);

        String[] options = {"Restart Nation", "Back to Title", "Quit Desktop"};
        g2.setFont(new Font("Georgia", Font.PLAIN, 24));
        
        for (int i = 0; i < options.length; i++) {
            int textY = y + 130 + (i * 60);
            
            if (i == cursorNum) {
                g2.setColor(Color.YELLOW);
                g2.drawString("> " + options[i], x + 100, textY);
            } else {
                g2.setColor(Color.BLACK);
                g2.drawString("  " + options[i], x + 100, textY);
            }
        }
    }
}