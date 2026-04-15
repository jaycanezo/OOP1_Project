package EchoesOfTheOath.UI;

import EchoesOfTheOath.Characters.Character;
import EchoesOfTheOath.Characters.Item;
import java.awt.*;
import java.util.ArrayList;

public class MenuRenderer {

    public static void drawInventory(Graphics2D g2, Character player, int slotCol, int slotRow, int scrollOffset) {
        int screenW = 1080;
        int totalW = 920;
        int totalH = 360; 
        int x = (screenW - totalW) / 2;
        int y = 70;
        int statW = (int)(totalW * 0.35); 
        int itemW = (int)(totalW * 0.65) - 10;

        drawWindow(g2, x, y, statW, totalH, "CHARACTER STATS");
        int sx = x + 25;
        int sy = y + 65;
        g2.setFont(new Font("Serif", Font.BOLD, 14));
        g2.setColor(Color.WHITE);
        g2.drawString("NAME:  " + player.getName(), sx, sy);
        g2.drawString("CLASS: " + player.getClassType(), sx, sy + 25);
        g2.drawString("LVL:   " + player.getLevel(), sx, sy + 50);
        g2.drawString("GOLD:  " + player.getGold(), sx, sy + 75);
        
        g2.drawString("HP:", sx, sy + 115);
        drawSidebarHPBar(g2, sx, sy + 125, player, statW - 50);

        g2.drawString("SKILLS:", sx, sy + 185);
        g2.setFont(new Font("Serif", Font.PLAIN, 12));
        if (player.getSkillName(1) != null) g2.drawString("• " + player.getSkillName(1), sx, sy + 210);
        if (player.getSkillName(2) != null) g2.drawString("• " + player.getSkillName(2), sx, sy + 235);
        if (player.getSkillName(3) != null) g2.drawString("• " + player.getSkillName(3), sx, sy + 260);

        int ix = x + statW + 10;
        drawWindow(g2, ix, y, itemW, totalH, "INVENTORY");
        renderItems(g2, player.getInventory(), ix + 30, y + 70, slotCol, slotRow, scrollOffset, 6);
    }

    public static void drawShop(Graphics2D g2, int gold, ArrayList<Item> stock, int slotCol, int slotRow) {
        int screenW = 1080;
        int totalW = 920;
        int totalH = 360;
        int x = (screenW - totalW) / 2;
        int y = 70;

        drawWindow(g2, x, y, totalW, totalH, "SHOP | Gold: " + gold);
        renderItems(g2, stock, x + 40, y + 80, slotCol, slotRow, 0, 8); 
    }

    private static void drawWindow(Graphics2D g2, int x, int y, int w, int h, String title) {
        g2.setColor(new Color(181, 153, 110, 255)); 
        g2.fillRoundRect(x, y, w, h, 15, 15);
        g2.setColor(Color.BLACK); 
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x, y, w, h, 15, 15);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Serif", Font.BOLD, 20));
        g2.drawString(title, x + 20, y + 35);
    }

    private static void renderItems(Graphics2D g2, ArrayList<Item> list, int startX, int startY, int slotCol, int slotRow, int scrollOffset, int cols) {
        if (list == null) return;

        int slotSize = 48;
        int xSpacing = 65; 
        int ySpacing = 65; 

        for (int i = 0; i < list.size(); i++) {
            int row = i / cols;
            int col = i % cols;

            if (row >= scrollOffset && row < scrollOffset + 4) {
                int dx = startX + (col * xSpacing);
                int dy = startY + ((row - scrollOffset) * ySpacing);

                if (slotCol == col && slotRow == row) {
                    int currentIndex = col + (row * cols);
                    if (currentIndex < list.size()) {
                        g2.setColor(Color.YELLOW);
                    } else {
                        g2.setColor(new Color(0, 0, 0, 80));
                    }
                    g2.setStroke(new BasicStroke(3));
                    g2.drawRoundRect(dx, dy, slotSize, slotSize, 10, 10);
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
        
        g2.setFont(new Font("Serif", Font.PLAIN, 16));
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

        g2.setFont(new Font("Serif", Font.BOLD, 32));
        g2.drawString("PAUSED", x + 155, y + 60);

        String[] options = {"Restart Nation", "Back to Title", "Quit Desktop"};
        g2.setFont(new Font("Serif", Font.PLAIN, 24));
        
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