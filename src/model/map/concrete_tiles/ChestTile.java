package model.map.concrete_tiles;

import model.inventory.Item;

public class ChestTile extends MovableTile {
    private static String DESCRIPTION = "A glowing chest. "; // The typical description for a chest
    private static String TILE_TYPE = "C"; // The tile representation of a chest tile

    private Item[] contents; // The inventory contents of a chest

    public ChestTile(int x, int y, Item[] contents) {
        super(x, y);
        this.contents = contents;
    }

    public String getContents() {
        String representation = "Chest Contents:\n";
        for(int i = 0; i < contents.length; i++) {
            Item item = contents[i];
            if(item == null) {
                representation += "\t" + (i + 1) + ". No item\n";
            } else {
                representation += "\t" + (i + 1) + ". " + item.toString();
            }
        }

        return representation;
    }

    public Item getItem(int pos) {
        try {
            Item item = contents[pos - 1];
            contents[pos - 1] = null;
            return item;
        } catch(Exception e) {
            return null;
        }
    }

    public boolean depositItem(Item item) {
        for(int i = 0; i < contents.length; i++) {
            if(contents[i] == null) {
                contents[i] = item;
                return true;
            } 
        }

        return false;
    }

    /**
     * The getTileType function from the Tile abstract class,
     * overrides the representation of the chest space if a character is on it
     */
    @Override
    public String getTileType() {
        String characterString = super.getTileType();
    
        if(characterString.length() == 0) {
            return ChestTile.TILE_TYPE;
        } else {
            return characterString;
        }
    }

    /**
     * The getDescription function from the Tile abstract class
     */
    @Override
    public String getDescription() {
        return ChestTile.DESCRIPTION + super.getDescription();
    }
}
