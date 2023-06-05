package controller.gameGenerators;

import model.character.characters.PlayerCharacter;
import model.character.inventory.Inventory;
import model.character.inventory.inventories.MultiInventory;
import model.character.inventory.items.Bag;
import model.character.stats.Stat;
import model.character.stats.StatsManager;
import model.character.stats.stat.BaseStat;
import model.character.stats.statsManager.ComplexStatsManager;

public class PlayerGenerator {
    private static int STARTER_BAG_SIZE = 6;
    private static int INIT_HEALTH = 100;
    private static int INIT_ATTACK = 10;
    private static int INIT_DEFENSE = 0; 

    public static PlayerCharacter generatePlayer(String name, String description) {
        Bag bag = new Bag("Backpack", "A starter's backpack", 30, STARTER_BAG_SIZE);
        Inventory inventory = new MultiInventory(bag);

        Stat baseStat = new BaseStat(INIT_ATTACK, INIT_DEFENSE);
        StatsManager manager = new ComplexStatsManager(INIT_HEALTH, baseStat);

        return new PlayerCharacter(inventory, manager, null, name, description);
    }
}
