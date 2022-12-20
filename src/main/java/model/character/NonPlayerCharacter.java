package model.character;

import java.util.ArrayList;
import java.util.Random;

import model.inventory.Item;

public abstract class NonPlayerCharacter extends Character {
    private final static Random rand = new Random(); // Random object for scrambling stats
    private final static int MIN_HEALTH = 50; // The minimum health for an NPC
    private final static int MAX_HEALTH = 151; // The maximum health for an NPC
    private final static int MIN_ATTACK = 5; // The minimum attack for an NPC
    private final static int MAX_ATTACK = 16; // The maximum attack for an NPC
    private final static int MIN_DEFENSE = 0; // The minimum defense for an NPC
    private final static int MAX_DEFENSE = 6; // The maximum defense for an NPC
    public final static String REPRESENTATION = "N"; // The tile representation of an NPC

    private Item item;

    public NonPlayerCharacter(String name, String description, int attack, int defense, int health) {
        super(name, description, attack, defense, health);
        item = null;
    }

    /**
     * The NonPlayerCharacter constructor, generates random stats for the
     * character
     * 
     * @param name the name of the character
     * @param description the description of the character
     */
    public NonPlayerCharacter(String name, String description) {
        super(name, description, 
            rand.nextInt(MIN_ATTACK, MAX_ATTACK), 
            rand.nextInt(MIN_DEFENSE, MAX_DEFENSE), 
            rand.nextInt(MIN_HEALTH, MAX_HEALTH));
        item = null;
    }

    public void equipItem(Item item) {
        this.item = item;
    }

    public ArrayList<Item> emptyInventory() {
        ArrayList<Item> inventory = new ArrayList<>();

        if(this.item != null) {
            inventory.add(this.item);
        }

        return inventory;
    }

    /**
     * The getRepresentation function from the abstract Character class
     */
    public String getRepresentation() {
        return NonPlayerCharacter.REPRESENTATION;
    } 

    /**
     * The NonPlayerCharacter's toString method, displays information
     * about the current state and description of the character
     */
    public String toString() {
        return "Enemy " + super.toString();
    }
}
