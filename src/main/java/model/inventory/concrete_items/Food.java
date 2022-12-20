package model.inventory.concrete_items;

import model.attribute.StatManager;
import model.inventory.Inventory;
import model.inventory.Item;

/**
 * The Food class, representing food. All food modify the health stat
 * for a character. 
 */
public class Food extends Item {
    private final static String TYPE = "food"; // The type of every food
    private final static boolean EQUIPPABLE = false; // Equippable status of all food

    /**
     * The Food constructor
     * 
     * @param name the food's name
     * @param price the price of the food in gold pieces
     * @param health the health the food provides
     */
    public Food(String name, int price, int health) {
        super(name, price, 0, 0, health);
    }

    /**
     * The isEquippable function from the abstract Item class
     */
    public boolean isEquippable() {
        return Food.EQUIPPABLE;
    }

    /**
     * The useItem from the abstract Item class, calls inventory's
     * method for handling food
     */
    public Item useItem(Inventory inventory) {
        return inventory.useFood(this);
    }

    /**
     * The getType function from the abstract Item class
     */
    public String getType() {
        return Food.TYPE;
    }

    /**
     * The applyBuff function from the abstract Item class
     * calls the stat manager's method for handling food
     * 
     * @param manager the manager to call methods on
     */
    public void applyBuff(StatManager manager) {
        manager.handleFood(this);
    }
}
