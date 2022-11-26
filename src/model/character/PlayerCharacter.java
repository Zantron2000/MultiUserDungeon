package model.character;

import model.inventory.Inventory;
import model.inventory.Item;

public class PlayerCharacter extends Character {
    private static int BASE_HEALTH = 100; // The base health for a player character
    private static int BASE_ATTACK = 10; // The base attack for a player character
    private static int BASE_DEFENSE = 10; // The base defense for a player character
    private Inventory inventory; // The inventory manager for a player character

    /**
     * The PlayerCharacter's constructor
     * 
     * @param name        the name of the PlayerCharacter
     * @param description the description of the PlayerCharacter
     */
    public PlayerCharacter(String name, String description) {
        super(name, description, BASE_ATTACK, BASE_DEFENSE, BASE_HEALTH);
        inventory = new Inventory();
    }

    /**
     * Obtains the inventory's toString method
     * 
     * @return the inventory of the PlayerCharacter represented as a string
     */
    public String openInvetory() {
        return this.inventory.toString();
    }

    /**
     * Destroys an item from the inventory
     * 
     * @param bagNum  the bag number the item is in
     * @param slotNum the slot number the item is in
     * @return Item that was removed from the inventory
     */
    public Item removeItem(int bagNum, int slotNum) {
        return this.inventory.removeItem(bagNum, slotNum);
    }

    /**
     * Attempts to add an item to the inventory, either adds it into the bag slots
     * or adds it into the inventory slots
     * 
     * @param item the item to attempt to add into the inventory
     * @return boolean based on if the item was added into the inventory
     */
    public boolean addItem(Item item) {
        return this.inventory.addItem(item);
    }

    /**
     * Uses an item in the user's inventory and applies any buffs
     * from it into the user's stats
     * 
     * @param bagNum  the bag number the item is in
     * @param slotNum the slot number the item is in
     */
    public void useItem(int bagNum, int slotNum) {
        Item item = this.inventory.useItem(bagNum, slotNum);

        if(item != null) {
            item.applyBuff(this.manager);
        }
    }

    /**
     * The class's toString method
     */
    public String toString() {
        return "Player " + super.toString();
    }
}
