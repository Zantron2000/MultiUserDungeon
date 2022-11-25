package model.inventory.concrete_items;

import java.util.HashMap;

import model.inventory.Inventory;
import model.inventory.Item;

public class Buff extends Item {
    private final static String TYPE = "buff"; // The type of every buff
    private final static boolean EQUIPPABLE = false; // Equippable status of all buffs
    
    /**
     * The Buff constructor
     * 
     * @param name the buff's name
     * @param price the price of the buff in gold pieces
     * @param attack the attack the buff provides
     * @param defense the defense the buff provides
     * @param health the health the buff provides
     */
    public Buff(String name, int price, int attack, int defense, int health) {
        super(name, price, attack, defense, health);
    }

    /**
     * The isEquippable function from the abstract Item class
     */
    public boolean isEquippable() {
        return Buff.EQUIPPABLE;
    }

    /**
     * The getType function from the abstract Item class
     */
    public String getType() {
        return Buff.TYPE;
    }

    /**
     * The useItem from the abstract Item class, calls inventory's
     * method for handling buffs
     */
    public Item useItem(Inventory inventory) {
        return inventory.useBuff(this);
    }
}
