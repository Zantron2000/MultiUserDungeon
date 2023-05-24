package model.character.characters;

import model.map.Cycle;

public class NocturnalNPC extends NonPlayerCharacter{
    public NocturnalNPC(Inventory inventory, StatsManager manager, String name, String description) {
        super(inventory, manager, name, description, Cycle.NIGHT);
    }
}
