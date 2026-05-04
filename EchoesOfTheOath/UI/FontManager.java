package EchoesOfTheOath.UI;

import java.awt.Font;
import java.io.InputStream;
import java.util.HashMap;

public class FontManager {
    private static HashMap<String, Font> fontCache = new HashMap<>();

    /**
     * @param fileName
     * @param size
     * @return
     */

    public static Font getFont(String fileName, float size) {
        String cacheKey = fileName + "_" + size;

        if (fontCache.containsKey(cacheKey)) {
            return fontCache.get(cacheKey);
        }

        try {
            InputStream is = FontManager.class.getResourceAsStream("/EchoesOfTheOath/Resources/" + fileName);
            if (is == null) {
                System.out.println("Warning: Font file " + fileName + " not found!");
                return new Font("Georgia", Font.PLAIN, (int)size);
            }

            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
            
            Font sizedFont = customFont.deriveFont(size);
            
            fontCache.put(cacheKey, sizedFont);
            return sizedFont;

        } catch (Exception e) {
            System.out.println("Error loading font: " + fileName);
            e.printStackTrace();
            return new Font("Georgia", Font.PLAIN, (int)size);
        }
    }
}
