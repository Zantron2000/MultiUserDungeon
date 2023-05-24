package model.character.characters;

import model.character.inventory.Inventory;
import model.character.stats.StatsManager;
import model.map.Cycle;

public class DiurnalNPC extends NonPlayerCharacter {
    public DiurnalNPC(Inventory inventory, StatsManager manager, String name, String description) {
        super(inventory, manager, name, description, Cycle.DAY);
    }
}
