package model.inventory.concrete_items;

import java.util.HashMap;

import model.inventory.Inventory;
import model.inventory.Item;

/**
 * The Weapon class, representing a weapon. All weapons modify the attack stat
 * for a character. 
 */
public class Weapon extends Item {
    private final static String TYPE = "weapon"; // The type of every weapon
    private final static boolean EQUIPPABLE = true; // Equippable status of all weapons

    /**
     * The Weapon constructor
     * 
     * @param name the weapon's name
     * @param price the price of the weapon in gold pieces
     * @param attack the attack the weapon provides
     */
    public Weapon(String name, int price, int attack) {
        super(name, price, attack, 0, 0);
    }

    /**
     * The useItem from the abstract Item class, calls inventory's
     * method for handling weapons
     */
    public Item useItem(Inventory inventory) {
        return inventory.useWeapon(this);
    }

    /**
     * The isEquippable function from the abstract Item class
     */
    public boolean isEquippable() {
        return Weapon.EQUIPPABLE;
    }

    /**
     * The getType function from the abstract Item class
     */
    public String getType() {
        return Weapon.TYPE;
    }
}
