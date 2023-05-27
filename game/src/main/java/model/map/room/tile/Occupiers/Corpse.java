package model.map.room.tile.Occupiers;

import controller.turnMapper.Direction;
import controller.turnMapper.TurnElement;
import controller.turnMapper.TurnGenerator;
import model.character.Character;
import model.character.inventory.Item;
import model.map.room.tile.Terrain;
import model.map.room.tile.Tile;

public class Corpse implements Terrain {
    private Item[] items;
    private Tile tile;

    public Corpse(Item[] items, Tile tile) {
        this.items = items;
        this.tile = tile;
    }

    public void interact(Character character) {
        // TODO implement the interaction interface here
    }

    public void moveOnto(Character character) {
        // TODO implement the interaction interface here
    }

    public void acceptTurnGenerator(TurnGenerator generator, Direction direction) {
        generator.generateCommand(this, direction);
    }
}
