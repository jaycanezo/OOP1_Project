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
        register("Elven Scout", "/EchoesOfTheOath/Resources/npc4.png", 1000, 1000);
        register("Elven Rebel", "/EchoesOfTheOath/Resources/npc5.png", 1000, 1000);
        register("Healer", "/EchoesOfTheOath/Resources/npc6.png", 1000, 1000);
        register("Shadow Voice", "/EchoesOfTheOath/Resources/shadow_voice.png", 4968, 4808);
        register("Sarukdal Defeated", "/EchoesOfTheOath/Resources/sarukdal_after.png", 8888, 7552); 
        register("Spirit of The Archivist", "/EchoesOfTheOath/Resources/spirit_archivist.png", 3200, 3200);
        register("Spirit of Lunareth", "/EchoesOfTheOath/Resources/spirit_lunareth.png", 9328, 7168);
        register("Random NPC", "/EchoesOfTheOath/Resources/randomNPC.png", 865, 817);

        registry.put("Archivist", new Archivist());
        registry.put("Ilaryx", new Ilaryx());
        registry.put("Lunareth", new Lunareth());
        registry.put("Sarukdal", new Sarukdal()); 
        registry.put("Elarion", new Elarion());
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