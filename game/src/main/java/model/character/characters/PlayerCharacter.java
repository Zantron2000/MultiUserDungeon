package model.character.characters;

import model.character.Character;
import model.character.inventory.Inventory;
import model.character.stats.StatsManager;

public class PlayerCharacter extends Character {
    public PlayerCharacter(Inventory inventory, StatsManager manager, String name, String description) {
        super(inventory, manager, name, description);
    }
}
