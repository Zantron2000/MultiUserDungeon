package model.character.inventory.items;

import model.character.inventory.Item;

public class Buff implements Item {
    private static int HEALTH = 0;
    private static boolean EQUIPPABLE = false;

    private String name;
    private String description;
    private int value;
    private int attack;
    private int defense;
    private int turns;

    public Buff(String name, String description, int value, int attack, int defense, int turns) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.attack = attack;
        this.defense = defense;
        this.turns = turns;
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
        return new StatsRecord(this.attack, this.defense, HEALTH, this.turns, EQUIPPABLE, ItemType.BUFF);
    }
}
