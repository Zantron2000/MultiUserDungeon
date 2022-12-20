package model.map.room.concrete_tiles;

import java.util.ArrayList;

import model.inventory.Item;

public class GraveTile extends MovableTile {
    private final static String DESCRIPTION = "The grave of a once living creature, lost to the dungeon"; // The typical description for a chest
    private final static String TILE_TYPE = "D"; // The tile representation of a chest tile

    private final ArrayList<Item> inventory; // The inventory contents of a chest

    public GraveTile(int x, int y, ArrayList<Item> inventory) {
        super(x, y);
        this.inventory = inventory;
    }

    public String getContents() {
        String representation = "Chest Contents:\n";
        for(int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
                representation += "\t" + (i + 1) + ". " + item.toString();
        }

        return representation;
    }

    public Item getItem(int pos) {
        try {
            Item item = this.inventory.get(pos - 1);
            inventory.set(pos - 1, null);
            return item;
        } catch(Exception e) {
            return null;
        }
    }

    /**
     * The getTileType function from the Tile abstract class,
     * overrides the representation of the chest space if a character is on it
     */
    @Override
    public String getTileType() {
        return GraveTile.TILE_TYPE;
    }

    /**
     * The getDescription function from the Tile abstract class
     */
    @Override
    public String getDescription() {
        return GraveTile.DESCRIPTION;
    }
}
