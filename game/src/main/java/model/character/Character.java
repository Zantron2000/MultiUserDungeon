package model.character;

import model.character.inventory.Inventory;
import model.character.inventory.Item;
import model.character.stats.StatsManager;

public abstract class Character {
    private Inventory inventory;
    private StatsManager manager;
    private String name;
    private String description;

    protected Character(Inventory inventory, StatsManager manager, String name, String description) {
        this.inventory = inventory;
        this.manager = manager;
        this.name = name;
        this.description = description;
    }

    public void progressTurn() {
        this.manager.progressTurn();
    }

    public int getDamage() {
        return this.manager.getDamage();
    }

    public void takeDamage(int damage) {
        this.manager.takeDamage(damage);
    }

    public boolean isDead() {
        return this.manager.isDead();
    }

    public boolean addItem(Item item) {
        if(this.inventory.isFull()) {
            return false;
        } else {
            this.inventory.addItem(item);
            return true;
        }
    }

    public void destroyItem(int bag, int pos) {
        this.inventory.destroyItem(bag, pos);
    }

    public void addGold(int gold) {
        this.inventory.addGold(gold);
    }

    public void useItem(int bag, int pos) {
        this.inventory.useItem(bag, pos, this.manager);
    }

    public String openInventory() {
        return this.inventory.openInventory();
    }
}
