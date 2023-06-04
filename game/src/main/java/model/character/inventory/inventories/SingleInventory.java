package model.character.inventory.inventories;

import java.util.ArrayList;
import java.util.HashMap;

import model.character.inventory.Inventory;
import model.character.inventory.Item;
import model.character.inventory.items.ItemType;
import model.character.inventory.items.StatsRecord;
import model.character.stats.StatsManager;

public class SingleInventory implements Inventory {
    private Item item;
    private int gold;
    private HashMap<ItemType, Item> equipped;

    public SingleInventory() {
        this.item = null;
        this.gold = 0;
        equipped = new HashMap<>();
    }

    public SingleInventory(int gold) {
        this.item = null;
        this.gold = gold;
        this.equipped = new HashMap<>();
    }

    public boolean isFull() {
        return this.item != null;
    }

    public void destroyItem(int bag, int pos) {
        this.item = null;
    }

    public void addItem(Item item) {
        this.item = item;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public void useItem(int bag, int pos, StatsManager manager) {
        if(this.item != null) {
            StatsRecord record = item.createRecord();

            if(record != null && record.isEquippable()) {
                Item oldItem = this.equipped.get(record.getType());

                this.equipped.put(record.getType(), item);
                this.destroyItem(bag, pos);

                this.addItem(oldItem);
                manager.addBuff(record);
            }
        }
    }

    public String openInventory() {
        String output = "Inventory: " + this.gold + " gold\n\t1. ";
        
        if(this.isFull()) {
            output += this.item.toString();
        } else {
            output += "None";
        }

        return output;
    }

    public int emptyGold() {
        int total = this.gold;
        this.gold = 0;
        return total;
    }

    public Item[] corpsify() {
        ArrayList<Item> allItems = new ArrayList<>();

        for(Item item : equipped.values()) {
            if(item != null) {
                allItems.add(item);
            }
        }

        if(this.item != null) {
            allItems.add(this.item);
        } 

        Item[] items = new Item[allItems.size()];
        return allItems.toArray(items);
    }
}
