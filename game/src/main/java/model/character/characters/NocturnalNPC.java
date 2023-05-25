package model.character.characters;

import model.character.inventory.Inventory;
import model.character.stats.StatsManager;
import model.map.Cycle;
import model.map.room.tile.Tile;

public class NocturnalNPC extends NonPlayerCharacter{
    public NocturnalNPC(Inventory inventory, StatsManager manager, Tile tile, String name, String description) {
        super(inventory, manager, tile, name, description, Cycle.NIGHT);
    }
}
