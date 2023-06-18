package model.character.characters;

import model.character.Character;
import model.character.inventory.Inventory;
import model.character.stats.StatsManager;
import model.map.room.tile.Tile;

public class PlayerCharacter extends Character {
    private static char ICON = 'P';

    public PlayerCharacter(Inventory inventory, StatsManager manager, Tile tile, String name, String description) {
        super(inventory, manager, tile, name, description);
    }

    public char getIcon() {
        return PlayerCharacter.ICON;
    }
}
