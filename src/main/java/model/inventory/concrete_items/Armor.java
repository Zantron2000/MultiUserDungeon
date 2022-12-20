package model.inventory.concrete_items;

import model.attribute.StatManager;
import model.inventory.Inventory;
import model.inventory.Item;

/**
 * The Armor class, representing armor. All armor modify the defense stat
 * for a character. 
 */
public class Armor extends Item {
    private final static String TYPE = "armor"; // The type of every armor
    private final static boolean EQUIPPABLE = true; // Equippable status of all armor

    /**
     * The Armor constructor
     * 
     * @param name the armor's name
     * @param price the price of the armor in gold pieces
     * @param defense the defense the armor provides
     */
    public Armor(String name, int price, int defense) {
        super(name, price, 0, defense, 0);
    }

    /**
     * The useItem from the abstract Item class, calls inventory's
     * method for handling armor
     */
    public Item useItem(Inventory inventory) {
        return inventory.useArmor(this);
    }

    /**
     * The isEquippable function from the abstract Item class
     */
    public boolean isEquippable() {
        return Armor.EQUIPPABLE;
    }

    /**
     * The getType function from the abstract Item class
     */
    public String getType() {
        return Armor.TYPE;
    }

    /**
     * The applyBuff function from the abstract Item class
     * calls the stat manager's method for handling armor
     * 
     * @param manager the manager to call methods on
     */
    public void applyBuff(StatManager manager) {
        manager.handleArmor(this);
    }
}
