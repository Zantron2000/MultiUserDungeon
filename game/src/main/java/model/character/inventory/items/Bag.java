package model.character.inventory.items;

import model.character.inventory.Item;

public class Bag implements Item {
    private String name;
    private String description;
    private int value;
    private int capacity;
    private int maxCapacity;
    private Item[] items;

    public Bag(String name, String description, int value, int maxCapacity) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.maxCapacity = maxCapacity;

        this.capacity = 0;
        this.items = new Item[maxCapacity];
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getValue() {
        return this.value;
    }

    public StatsRecord createRecord() {
        return null;
    }

    public boolean isFull() {
        return this.capacity == this.maxCapacity;
    }

    public void addItem(Item item) {
        for(int i = 0; i < this.maxCapacity; i++) {
            if(this.items[i] == null) {
                this.items[i] = item;
                break;
            }
        }
    }

    public void addItem(Item item, int index) {
        this.items[index] = item;
        this.capacity++;
    }

    public Item removeItem(int index) {
        if(!inBounds(index) || this.items[index] == null) {
            return null;
        }

        Item item = this.items[index];
        this.items[index] = null;
        this.capacity--; 

        return item;
    }

    public String openInventory() {
        String output = this.name + " " + this.capacity + "/" + this.maxCapacity + " capacity\n";

        for(int i = 0; i < this.maxCapacity; i++) {
            output += "\t\t" + (i + 1) + ". ";

            if(this.items[i] == null) {
                output += "None";
            } else {
                output += this.items[i].toString();
            }

            output += "\n";
        }

        return output;
    }

    public Item[] corpsify() {
        Item[] items = new Item[this.capacity];
        int counter = 0;

        for(int i = 0; i < this.maxCapacity; i++) {
            Item item = this.items[i];

            if(item != null) {
                items[counter] = item;
                counter++;
            }
        }

        return items;
    }

    private boolean inBounds(int pos) {
        return 0 <= pos && pos < this.maxCapacity;
    }

    public String toString() {
        return "(Bag) " + this.name + ": " + this.maxCapacity + " slots"; 
    }

    public int compareTo(Bag bag) {
        return this.maxCapacity - bag.maxCapacity;
    }

    public void transferItems(Bag bag) {
        int length = (this.maxCapacity > bag.maxCapacity) ? bag.maxCapacity : this.maxCapacity;

        for(int i = 0; i < length; i++) {
            Item item = this.removeItem(i);
            bag.addItem(item);
        }
    }
}
