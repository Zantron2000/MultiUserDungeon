package model.character.inventory.inventories;

import java.util.ArrayList;
import java.util.HashMap;

import model.character.inventory.Inventory;
import model.character.inventory.Item;
import model.character.inventory.items.Bag;
import model.character.inventory.items.ItemType;
import model.character.inventory.items.StatsRecord;
import model.character.stats.StatsManager;

public class MultiInventory implements Inventory {
    private static int NUM_BAGS = 6;

    private Bag[] bags;
    private int gold;
    private HashMap<ItemType, Item> equipped;

    public MultiInventory(Bag bag) {
        this.bags = new Bag[NUM_BAGS];
        this.gold = 0;
        this.equipped = new HashMap<>();

        this.bags[0] = bag;
    }

    public int emptyGold() {
        int total = this.gold;
        this.gold = 0;
        return total;
    }

    public void destroyItem(int bag, int pos) {
        if(bag < NUM_BAGS && bag >= 0 && this.bags[bag] != null) {
            this.bags[bag].removeItem(pos);
        }
    }

    public boolean isFull() {
        for(int i = 0; i < NUM_BAGS; i++) {
            if(this.bags[i] != null && !this.bags[i].isFull()) {
                return false;
            }
        }

        return true;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public void addItem(Item item) {
        if(item == null) {
            return;
        }

        for(int i = 0; i < NUM_BAGS; i++) {
            if(this.bags[i] != null && !this.bags[i].isFull()) {
                this.bags[i].addItem(item);
                break;
            }
        }
    }

    public void useItem(int bag, int pos, StatsManager manager) {
        if(!inBounds(bag) || this.bags[bag] == null) {
            return;
        }

        Item item = this.bags[bag].removeItem(pos);
        
        if(item == null) {
            return;
        }

        StatsRecord record = item.createRecord();

        if(record == null) {
            if(!this.attemptEquipBag(item)) {
                this.bags[bag].addItem(item, pos);
            }
        } else if(record.isEquippable()) {
            Item oldItem = this.equipped.get(record.getType());
            this.addItem(oldItem);

            this.equipped.put(record.getType(), item);
            manager.addBuff(record);
        } else {
            manager.addBuff(record);
        }
    }

    public String openInventory() {
        String output = "Inventory: " + this.gold + " gold";

        for(int i = 0; i < NUM_BAGS; i++) {
            Bag bag = this.bags[i];
            output += "\n\t" + (i + 1) + ". ";

            if(bag == null) {
                output += "None";
            } else {
                output += bag.openInventory();
            }
        }

        return output;
    }

    public Item[] corpsify() {
        ArrayList<Item> allItems = new ArrayList<>();

        for(int i = 0; i < NUM_BAGS; i++) {
            Bag bag = this.bags[i];

            if(bag != null) {
                Item[] items = bag.corpsify();
                for(Item item : items) {
                    allItems.add(item);
                }

                allItems.add(bag);
                this.bags[i] = null;
            }
        }

        for(Item item : equipped.values()) {
            if(item != null) {
                allItems.add(item);
            }
        }

        Item[] items = new Item[allItems.size()];
        return allItems.toArray(items);
    }

    private boolean attemptEquipBag(Item item) {
        Bag bag = (Bag) item;

        if(this.attemptInsertNewBag(bag) || this.attemptReplaceBag(bag)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean attemptInsertNewBag(Bag bag) {
        for(int i = 0; i < NUM_BAGS; i++) {
            if(this.bags[i] == null) {
                this.bags[i] = bag;
                return true;
            }
        }
        
        return false;
    }

    private boolean attemptReplaceBag(Bag bag) {
        int index = smallestBagIdx(bag);

        if(index < 0) {
            return false;
        } else {
            Bag oldBag = this.bags[index];
            this.bags[index] = bag;
            oldBag.transferItems(bag);
            this.addItem(oldBag);

            return true;
        }
    }

    private boolean inBounds(int bag) {
        return 0 <= bag && bag < NUM_BAGS;
    }

    private int smallestBagIdx(Bag bag) {
        int idx = -1;
        int minValue = 0;

        for(int i = 0; i < NUM_BAGS; i++) {
            Bag equippedBag = this.bags[i];
            int compare = equippedBag.compareTo(bag);

            if(compare < minValue) {
                idx = i;
                minValue = compare;
            }
        }

        return idx;
    } 
}
