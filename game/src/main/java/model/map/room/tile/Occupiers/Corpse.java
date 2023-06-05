package model.map.room.tile.Occupiers;

import controller.turnMapper.Direction;
import controller.turnMapper.TurnMapper;
import model.character.Character;
import model.character.inventory.Item;
import model.map.room.tile.Terrain;
import model.map.room.tile.Tile;

public class Corpse implements Terrain {
    private Item[] items;
    private Tile tile;
    private int gold;

    public Corpse(Item[] items, Tile tile, int gold) {
        this.items = items;
        this.tile = tile;
        this.gold = gold;
    }

    public void interact(Character character) {
        // TODO implement the interaction interface here
    }

    public void moveOnto(Character character) {
        // TODO implement the interaction interface here
    }

    public void acceptTurnGenerator(TurnMapper generator, Direction direction) {
        if(!isEmpty()) {
            generator.generateCommand(this, direction);
        } else {
            this.tile.removeTerrain();
        }
    }

    private boolean isEmpty() {
        for(int i = 0; i < this.items.length; i++) {
            if(this.items[i] != null) {
                return false;
            }
        }

        return this.gold == 0;
    }
}
