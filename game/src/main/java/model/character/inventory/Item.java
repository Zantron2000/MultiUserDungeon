package model.character.inventory;

import model.character.inventory.items.StatsRecord;

public interface Item {
    public String getName();

    public String getDescription();

    public int getValue();

    public StatsRecord createRecord();

    public Item clone();
}
