package model.character.inventory.items;

import model.character.inventory.Item;

public class Food implements Item {
    private static int DEFENSE = 0;
    private static int ATTACK = 0;
    private static int TURNS = 0;
    private static boolean EQUIPPABLE = false;

    private String name;
    private String description;
    private int value;
    private int health;

    public Food(String name, String description, int value, int health) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.health = health;
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
        return new StatsRecord(ATTACK, DEFENSE, health, TURNS, EQUIPPABLE, ItemType.FOOD);
    }

    public String toString() {
        return "(Food) " + this.name + ": +" + this.health + " health"; 
    }
}
