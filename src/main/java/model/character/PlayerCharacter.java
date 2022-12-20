package model.character;

import java.util.ArrayList;

import model.inventory.Inventory;
import model.inventory.Item;
import model.map.room.Tile;

/**
 * Represents a player character in the game, keeps track of inventory of
 * character along with methods to use and remove items
 */
public class PlayerCharacter extends Character {
    private final static int BASE_HEALTH = 100; // The base health for a player character
    private final static int BASE_ATTACK = 10; // The base attack for a player character
    private final static int BASE_DEFENSE = 0; // The base defense for a player character
    private final static String REPRESENTATION = "P"; // The tile representation of an NPC


    private final Inventory inventory; // The inventory manager for a player character
    private Tile currentTile;

    /**
     * The PlayerCharacter's constructor
     * 
     * @param name        the name of the PlayerCharacter
     * @param description the description of the PlayerCharacter
     */
    public PlayerCharacter(String name, String description) {
        super(name, description, BASE_ATTACK, BASE_DEFENSE, BASE_HEALTH);
        this.inventory = new Inventory();
        this.currentTile = null;
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

    public ArrayList<Item> emptyInventory() {
        return this.inventory.emptyInventory();
    }

    public Tile getCurrentTile() {
        return this.currentTile;
    }

    public void moveTo(Tile tile) {
        this.currentTile = tile;
    }

    /**
     * The getRepresentation function from the abstract Character class
     */
    public String getRepresentation() {
        return PlayerCharacter.REPRESENTATION;
    } 

    /**
     * The class's toString method
     */
    public String toString() {
        return "Player " + super.toString();
    }
}
