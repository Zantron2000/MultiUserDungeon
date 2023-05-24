package model.character.inventory.inventories;

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

    public SingleInventory(int gold, Item item) {
        this.item = item;
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
                this.equipped.put(record.getType(), item);
                this.destroyItem(bag, pos);
                manager.addBuff(record);
            }
        }
    }

    public String openInventory() {
        String output = "Inventory: " + this.gold + " gold\n\t1. ";
        
        if(this.isFull()) {
            output += this.item.getDescription();
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
        Item[] items;

        if(this.item == null) {
            items = new Item[1];
            items[0] = this.item;
            this.item = null;
        } else {
            items = new Item[0];
        }

        return items;
    }
}
