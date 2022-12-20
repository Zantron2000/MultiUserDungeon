package model.inventory;

import java.util.HashMap;

import model.attribute.StatManager;

/**
 * The item interface representing all items to exist in the game.
 * Sets requirements for obtaining information about the item.
 */
public abstract class Item {
    public final static String HEALTH_KEY = "health"; // The key of which health modifiers will be stored under
    public final static String ATTACK_KEY = "attack"; // The key of which attack modifiers will be stored under
    public final static String DEFENSE_KEY = "defense"; // The kye of which defense modifiers will be stored under

    protected String name; // The name of the item
    protected int price; // The price of the item
    protected HashMap<String, Integer> buffs; // The buffs provided by the item

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
        this.buffs = null;
    }

    /**
     * The Item constructor
     * 
     * @param name the Item's name
     * @param price the price of the Item in gold pieces
     * @param attack the attack the Item provides
     * @param defense the defense the Item provides
     * @param health the health the Item provides
     */
    public Item(String name, int price, int attack, int defense, int health) {
        this.name = name;
        this.price = price;
        this.buffs = new HashMap<>();

        this.buffs.put(Item.ATTACK_KEY, attack);
        this.buffs.put(Item.DEFENSE_KEY, defense);
        this.buffs.put(Item.HEALTH_KEY, health);
    }

    /**
     * Checks to see if the item is equippable
     * 
     * @return a boolean depending on if the item can be equipped
     */
    public abstract boolean isEquippable();

    /**
     * Gets the type of the item
     * 
     * @return the type of item it is
     */
    public abstract String getType();

    /**
     * Passes the item into the inventory by calling the proper command
     * 
     * @param inventory the inventory to call on
     * @return the Item after passing through the inventory
     */
    public abstract Item useItem(Inventory inventory);

    /**
     * Passes the item into the stats manager by calling the proper command
     * 
     * @param manager the stats manager to call on
     */
    public abstract void applyBuff(StatManager manager);

    /**
     * Gets the value of the item in gold pieces
     * 
     * @return the value of the item in gold
     */
    public int getGoldValue() {
        return this.price;
    }

    /**
     * Obtains and returns a collection of the stats it will modify
     * 
     * @return a HashMap mapping buff types to their increases
     */
    public HashMap<String, Integer> getBuffs() {
        return this.buffs;
    }
    
    /**
     * The class's toString method
     */
    public String toString() {
        String attackBuff = (this.buffs.get(Item.ATTACK_KEY) == 0) ? "" : this.buffs.get(Item.ATTACK_KEY) + " attack";
        String defenseBuff = (this.buffs.get(Item.DEFENSE_KEY) == 0) ? "" : this.buffs.get(Item.DEFENSE_KEY) + " defense";
        String healthBuff = (this.buffs.get(Item.HEALTH_KEY) == 0) ? "" : this.buffs.get(Item.HEALTH_KEY) + " health";
        String[] buffs = {attackBuff, defenseBuff, healthBuff};
        StringBuilder output = new StringBuilder();

        for (String buff : buffs) {
            if (!buff.equals("") && output.toString().equals("")) {
                output.append(buff);
            } else if (!buff.equals("")) {
                output.append(", ").append(buff);
            }
        }

        return this.name + ": " + output + " - " + this.getType() + ", " + this.price + " gold";
    }
}