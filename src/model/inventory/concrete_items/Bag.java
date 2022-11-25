package model.inventory.concrete_items;

import model.attribute.StatManager;
import model.inventory.Inventory;
import model.inventory.Item;

public class Bag extends Item {
    private final static String TYPE = "bag"; // The type of every bag
    private final static boolean EQUIPPABLE = true; // Equippable status of all bags

    private Item[] items; // The items within the bag

    /**
     * The Bag constructor, calls the Item constructor that will produce
     * no buffs
     * 
     * @param name the name of the bag
     * @param price the price of the bag
     * @param size the amount of items the bag can hold
     */
    public Bag(String name, int price, int size) {
        super(name, price);
        items = new Item[size];
    }

    @Override
    public int getGoldValue() {
        int total = 0;
        for (Item item : items) {
            if (item != null) {
                total += item.getGoldValue();
            }
        }

        return total + super.getGoldValue();
    }

    /**
     * The isEquippable function from the abstract Item class
     */
    @Override
    public boolean isEquippable() {
        return Bag.EQUIPPABLE;
    }

    /**
     * The getType function from the abstract Item class
     */
    @Override
    public String getType() {
        return Bag.TYPE;
    }

    /**
     * Adds an item to the inventory.
     * 
     * @param item The InventoryItem to be added
     */
    public void addItem(Item item) {
        for (int i = 0; i < items.length; i++) {
            // Add to next open spot
            if (items[i] == null) {
                items[i] = item;
                break;
            }
        }
    }

    /**
     * Removes an item from the bag
     * 
     * @param slotNumber The slot the item is in
     * @return the item that was removed from the bag
     */
    public Item remove(int slotNumber) {
        Item item = items[slotNumber];
        items[slotNumber] = null;
        return item;
    }

    /**
     * Checks to see if the bag is full
     * 
     * @return a boolean based on if the bag is full
     */
    public boolean isFull() {
        return this.getUsedSpace() == this.items.length;
    }

    public String getContents() {
        String description = "";

        description += (this.name + ": Total Gold Value: " + this.getGoldValue() + 
                        " | Bag Space: " + this.getUsedSpace() + "/" + this.getTotalSpace() + "\n");

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                description += ("\t" + (i + 1) + ") " + items[i].toString() + "\n");
            }
        }

        return description;
    }

    /**
     * The useItem from the abstract Item class, calls inventory's
     * method for handling bags
     */
    public Item useItem(Inventory inventory) {
        return inventory.useBag(this);
    }

    /**
     * The class's toString method
     */
    @Override
    public String toString() {
        return this.name + ": Gold Value: " + this.price + " | Bag Size: " + this.getTotalSpace() + " items";
    }

    /**
     * Gets the used space of items in the bag
     * 
     * @return The used space
     */
    public int getUsedSpace() {
        int total = 0;
        for (Item item : items) {
            if (item != null) {
                total++;
            }
        }

        return total;
    }

    /**
     * Gets the total space of the bag
     * 
     * @return the total space in the bag
     */
    public int getTotalSpace() {
        return this.items.length;
    }

    /**
     * The applyBuff function from the abstract Item class
     * calls the stat manager's method for handling bags
     * 
     * @param manager the manager to call methods on
     */
    public void applyBuff(StatManager manager) {
        manager.handleBag(this);
    }
}
