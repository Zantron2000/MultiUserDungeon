package model.map.room.tile.Occupiers;

import controller.turnMapper.Direction;
import controller.turnMapper.TurnMapper;
import model.character.Character;
import model.character.inventory.Item;
import model.map.room.tile.Occupier;

public class Chest implements Occupier {
    private Item[] items;

    public Chest(Item[] items) {
        this.items = items;
    }

    public void interact(Character character) {
        // TODO implement the interaction interface here
    }

    private Item takeItem(int pos) {
        if(pos >= 0 && pos < items.length) {
            Item item = items[pos];
            items[pos] = null;

            return item;
        }

        return null;
    }

    public void acceptTurnGenerator(TurnMapper generator, Direction direction) {
        generator.generateCommand(this, direction);
    }
}
