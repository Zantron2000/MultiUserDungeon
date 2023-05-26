package model.character.inventory.items;

import model.character.inventory.Item;

public class Armor implements Item {
    private static int ATTACK = 0;
    private static int HEALTH = 0;
    private static int TURNS = 0;
    private static boolean EQUIPPABLE = true;

    private String name;
    private String description;
    private int value;
    private int defense;

    public Armor(String name, String description, int value, int defense) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.defense = defense;
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
        return new StatsRecord(ATTACK, defense, HEALTH, TURNS, EQUIPPABLE, ItemType.ARMOR);
    }

    public String toString() {
        return "(Armor) " + this.name + ": +" + this.defense + " defense"; 
    }
}
