package model.character.inventory.items;

import model.character.inventory.Item;

public class Weapon implements Item {
    private static int DEFENSE = 0;
    private static int HEALTH = 0;
    private static int TURNS = 0;
    private static boolean EQUIPPABLE = true;

    private String name;
    private String description;
    private int value;
    private int attack;

    public Weapon(String name, String description, int value, int attack) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.attack = attack;
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
        return new StatsRecord(attack, DEFENSE, HEALTH, TURNS, EQUIPPABLE, ItemType.WEAPON);
    }

    public String toString() {
        return "(Weapon) " + this.name + ": +" + this.attack + " attack"; 
    }
}
