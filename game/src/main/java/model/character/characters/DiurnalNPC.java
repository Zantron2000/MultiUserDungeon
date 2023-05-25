package model.character.characters;

import model.character.inventory.Inventory;
import model.character.stats.StatsManager;
import model.map.Cycle;
import model.map.room.tile.Tile;

public class DiurnalNPC extends NonPlayerCharacter {
    public DiurnalNPC(Inventory inventory, StatsManager manager, Tile tile, String name, String description) {
        super(inventory, manager, tile, name, description, Cycle.DAY);
    }
}
