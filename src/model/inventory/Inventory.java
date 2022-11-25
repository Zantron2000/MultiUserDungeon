package model.inventory;

import model.inventory.concrete_items.Armor;
import model.inventory.concrete_items.Bag;
import model.inventory.concrete_items.Buff;
import model.inventory.concrete_items.Food;
import model.inventory.concrete_items.Weapon;

public class Inventory {
    private final static int BAG_SLOTS = 6; // The number of bags an inventory can hold

    private int gold; // The amount gold in the inventory
    private Bag[] bags; // The list of bags in the inventory
    private Item armor; // The armor equipped by the player
    private Item weapon; // The weapon equipped by the player

    /**
     * The Inventory constructor
     */
    public Inventory() {
        this.gold = 0;
        this.bags = new Bag[Inventory.BAG_SLOTS];
        this.armor = null;
        this.weapon = null;

        this.bags[0] = new Bag("Tiny Bag", 30, 3);
    }

    /**
     * Uses an item, equipping the item if equippable and
     * returning the item if it modifies character stats
     * 
     * @param bagNum The bag number to get the item from
     * @param slotNum The slot number inside the bag the item is in
     * @return The item containing buff information
     */
    public Item useItem(int bagNum, int slotNum) {
        try {
            Item item = this.removeItem(bagNum, slotNum);
            return item.useItem(this);
        } catch(Exception e) {
            return null;
        }
    }

    /**
     * Attempts to removes an item from the user's inventory
     * 
     * @param bagNum the bag number to take the item from
     * @param slotNum the slot number to take the item from
     * @return the item that's being removed from the inventory
     */
    public Item removeItem(int bagNum, int slotNum) {
        try {
            return bags[bagNum].remove(slotNum);
        } catch(Exception e) {
            return null;
        }
    }

    /**
     * Adds an item into the inventory to the first
     * bag that can fit it
     * 
     * @param item the item to add into the inventory
     * @return boolean based on if the item was successfully added
     */
    public boolean addItem(Item item) {
        if(item != null) {
            if(item.getType().equals("bag") && this.hasFreeSlot()) {
                this.addBag(item);

                return true;
            } else if(this.isFull()) {
                
                for(Bag bag : bags) {
                    if(bag != null && !bag.isFull()) {
                        bag.addItem(item);
                        break;
                    }
                }

                return true;
            }
        }

        return false;
    }

    /**
     * Checks to see if the inventory has enough gold to spend
     * and if so subtracts from the gold count
     * 
     * @param gold the amount of gold to spend
     * @return a boolean depending on if the inventory has enough gold
     */
    public boolean spendGold(int gold) {
        if(this.gold >= gold) {
            this.gold -= gold;

            return true;
        }

        return false;
    }

    /**
     * Adds a given amount of gold into the user's inventory
     * 
     * @param gold the amount fo gold to add into the inventory
     */
    public void addGold(int gold) {
        this.gold += gold;
    }

    /**
     * Checks to see if the inventory is full by checking each
     * bag and seeing if all of them are full
     * 
     * @return boolean based on if all bags in the inventory is full
     */
    public boolean isFull() {
        for(Bag bag : this.bags) {
            if(bag != null && !bag.isFull()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Adds an item to the inventory in the first bag it can
     * 
     * @param item the item to add into the inventory
     */
    private void addBag(Item item) {
        Bag bag = (Bag) item;

        for (int i = 0; i < bags.length; i++) {
            if (bags[i] == null) {
                bags[i] = bag;
                break;
            }
        }
    }

    /**
     * Checks to see if the inventory has an empty bag slot available
     * 
     * @return a boolean depending on if the inventory has an empty bag slot
     */
    private boolean hasFreeSlot() {
        for (Bag bag : bags) {
            if (bag == null) {
                return true;
            }
        }

        return false;
    }

    /**
     * Handles inventory management for a weapon being used
     * 
     * @param weapon the weapon that's being equipped
     * @return the weapon equipped
     */
    public Item useWeapon(Weapon weapon) {
        this.addItem(this.weapon);
        this.weapon = weapon;
        
        return this.weapon;
    }

    /**
     * Handles inventory management for armor being used
     * 
     * @param armor the armor that's being equipped
     * @return the armor equipped
     */
    public Item useArmor(Armor armor) {
        this.addItem(this.armor);
        this.armor = armor;

        return this.armor;
    }

    /**
     * Handles inventory management for a buff being used
     * 
     * @param buff the buff that's being used
     * @return the buff used
     */
    public Item useBuff(Buff buff) {
        return buff;
    }

    /**
     * Handles inventory management for food being used
     * 
     * @param food the food that's being consumed
     * @return the food consumed
     */
    public Item useFood(Food food) {
        return food;
    }

    /**
     * Handles inventory management for a bag being used
     * swaps the bag in for the smallest bag equipped in
     * the user's inventory, placing all contents from the
     * old bag into the new, larger one
     * 
     * @param bag the bag that's being equipped
     * @return null as the bag isn't needed outside of the inventory
     */
    public Item useBag(Bag bag) {
        int index = -1;
        Bag smallestBag = bag;
        Bag oldBag;

        for(int i = 0; i < this.bags.length; i++) {
            oldBag = this.bags[i];

            if(oldBag != null && oldBag.getTotalSpace() < smallestBag.getTotalSpace()) {
                index = i;
                smallestBag = this.bags[i];
            }
        }

        if(index != -1) {
            oldBag = this.bags[index];

            for(int i = 0; i < oldBag.getTotalSpace(); i++) {
                Item item = oldBag.remove(i);
                bag.addItem(item);
            }
                        
            this.bags[index] = bag;
            this.addItem(oldBag);
        } else {
            this.addItem(bag);
        }

        return bag;
    }

    /**
     * The toString for the Inventory class
     */
    public String toString() {
        int totalUsedSpace = 0, totalSpace = 0;
        String bagOutput = "";

        for(int i = 0; i < this.bags.length; i++) {
            Bag bag = this.bags[i];

            if(bag == null) {
                continue;
            } else {
                totalUsedSpace += bag.getUsedSpace();
                totalSpace += bag.getTotalSpace();
                bagOutput += (i + 1) + ") " + bag.getContents();
            }
        }

        return "Inventory:\n\t" + this.gold + " gold\n\t" + totalUsedSpace + "/" + totalSpace + " items\n" + bagOutput;
    }
}
