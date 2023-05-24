package model.character.inventory;

import model.character.stats.StatsManager;

public interface Inventory {
    public boolean isFull();

    public void useItem(int bag, int slot, StatsManager manager);

    public void addGold(int gold);

    public void addItem(Item item);

    public void destroyItem(int bag, int slot);

    public String openInventory();

    public int emptyGold();

    public Item[] corpsify();
}
