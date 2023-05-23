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
    }

    public Item removeItem(int index) {
        Item item = this.items[index];
        this.items[index] = null;

        return item;
    }
}
