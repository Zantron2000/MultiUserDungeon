package model.character;

import model.attribute.StatManager;

public abstract class Character {
    private String name;
    private String description;
    protected StatManager manager;

    public Character(String name, String description, int attack, int defense, int health) {
        this.name = name;
        this.description = description;
        this.manager = new StatManager(attack, defense, health);
    }

    public int attack() {
        return this.manager.attack();
    }

    public boolean takeDamage(int damage) {
        return this.manager.takeDamage(damage);
    }

    public String toString() {
        return this.name + ": " + this.description + " | " + this.manager.toString();
    }
}
