package model.map.room.concrete_tiles;

import model.inventory.Item;
import model.map.room.Tile;

public class MerchantTile extends Tile {
    private final static String DESCRIPTION = "An eager merchant with a few glowing items";
    private final static String TILE_TYPE = "M";
    private final static double RATE = 0.5;

    private final Item[] store;
    private boolean isOpen;

    public MerchantTile(int x, int y, Item[] store) {
        super(x, y);
        this.store = store;
        this.isOpen = true;
    }

    public MerchantTile(int x, int y, Item[] store, boolean isDay) {
        super(x, y);
        this.store = store;
        this.isOpen = isDay;
    }

    public String getContents() {
        String representation = "Store Contents:\n";
        for(int i = 0; i < store.length; i++) {
            Item item = store[i];
            if(item == null) {
                representation += "\t" + (i + 1) + ". No item\n";
            } else {
                representation += "\t" + (i + 1) + ". " + item.toString();
            }
        }

        return representation;
    }

    public int buyItem(Item item) {
        for(int i = 0; i < this.store.length; i++) {
            if(this.store[i] == null) {
                this.store[i] = item;
                break;
            }
        }

        return (int) (item.getGoldValue() * MerchantTile.RATE);
    }

    public void returnItem(Item item) {
        for(int i = 0; i < this.store.length; i++) {
            if(this.store[i] == null) {
                this.store[i] = item;
                break;
            }
        }
    }

    public Item sellItem(int i) {
        Item item = this.store[i];
        this.store[i] = null;

        return item;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    /**
     * The getTileType function from the Tile abstract class,
     */
    @Override
    public String getTileType() {
        return MerchantTile.TILE_TYPE;
    }

    /**
     * The getDescription function from the Tile abstract class
     */
    @Override
    public String getDescription() {
        return MerchantTile.DESCRIPTION;
    }
}
