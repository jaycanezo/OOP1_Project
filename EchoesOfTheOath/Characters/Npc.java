package EchoesOfTheOath.Characters;

import EchoesOfTheOath.UI.Sprite;
import java.util.HashMap;
import java.util.Map;

public class Npc {
    private final Map<String, Character> registry = new HashMap<>();

    public Npc() {
        initializeNpcs();
    }

    private void initializeNpcs() {
        register("Guard", "/EchoesOfTheOath/Resources/npc1.png", 458, 545);
        register("Informant", "/EchoesOfTheOath/Resources/npc2.png", 2000, 2000);
        register("Attendant", "/EchoesOfTheOath/Resources/npc3.png", 2000, 2000);
        registry.put("Archivist", new Archivist());
    }

    private void register(String name, String resPath, int width, int height) {
        Character npcChar = new Character(name, "NPC", 1, 1) {
            @Override
            public String useSkill(int skillNumber, Character enemy) {
                return ""; 
            }
        };
        
        npcChar.setIdleSprite(new Sprite(resPath, width, height, 1));
        registry.put(name, npcChar);
    }

    public Character get(String name) {
        return registry.getOrDefault(name, null);
    }
}