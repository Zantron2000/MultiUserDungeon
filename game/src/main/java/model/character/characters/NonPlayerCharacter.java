package model.character.characters;

import model.character.Character;
import model.character.inventory.Inventory;
import model.character.stats.StatsManager;
import model.map.Cycle;

public class NonPlayerCharacter extends Character {
    private Cycle cycle;

    public NonPlayerCharacter(Inventory inventory, StatsManager manager, String name, String description, Cycle cycle) {
        super(inventory, manager, name, description);

        this.cycle = cycle;
    }
}
