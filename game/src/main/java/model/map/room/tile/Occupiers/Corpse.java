package model.map.room.tile.Occupiers;

import model.character.Character;
import model.character.inventory.Item;
import model.map.room.tile.Terrain;

public class Corpse implements Terrain {
    private Item[] items;

    public Corpse(Item[] items) {
        this.items = items;
    }

    public void interact(Character character) {
        // TODO implement the interaction interface here
    }

    public void moveOnto(Character character) {
        // TODO implement the interaction interface here
    }
}
