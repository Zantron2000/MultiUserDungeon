package model.map.room.generators;

import java.util.HashMap;

public enum Rarity {
    COMMON,
    UNCOMMON,
    RARE,
    EPIC,
    LEGENDARY;

    public static final HashMap<String, Rarity> levels = new HashMap<>() {{
        put("common", Rarity.COMMON);
        put("uncommon", Rarity.UNCOMMON);
        put("rare", Rarity.RARE);
        put("epic", Rarity.EPIC);
        put("legendary", Rarity.LEGENDARY);
    }};

    public static final HashMap<Rarity, Integer> chance = new HashMap<>() {{
        put(Rarity.COMMON, 40);
        put(Rarity.UNCOMMON, 30);
        put(Rarity.RARE, 20);
        put(Rarity.EPIC, 7);
        put(Rarity.LEGENDARY, 3);
    }};
}