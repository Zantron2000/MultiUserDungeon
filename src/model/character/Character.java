package model.character;

import model.attribute.StatManager;

public abstract class Character {
    private String name; // The name of the character
    private String description; // The description of the character
    protected StatManager manager; // The StatManager for the character

    /**
     * The Character constructor
     * 
     * @param name the name of the character
     * @param description the description of the character
     * @param attack the base attack stat of the character
     * @param defense the base defense stat of the character
     * @param health the base health stat of the character
     */
    public Character(String name, String description, int attack, int defense, int health) {
        this.name = name;
        this.description = description;
        this.manager = new StatManager(attack, defense, health);
    }

    /**
     * Gets the tile representation of the character on a tile
     * 
     * @return the tile representation of the character
     */
    public abstract String getRepresentation();

    /**
     * Gets the attack stat of the character
     * 
     * @return the attack stat of the character
     */
    public int attack() {
        return this.manager.attack();
    }

    /**
     * The player takes a given amount of damage and checks to see
     * if they're now dead
     * 
     * @param damage the amount of damage the player will initially take
     * @return boolean based on if the character is dead
     */
    public boolean takeDamage(int damage) {
        return this.manager.takeDamage(damage);
    }

    /**
     * The Character's toString method, displays information of its state and stats
     */
    public String toString() {
        return this.name + ": " + this.description + " | " + this.manager.toString();
    }
}
