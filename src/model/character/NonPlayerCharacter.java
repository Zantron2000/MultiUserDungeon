package model.character;

import java.util.Random;

public abstract class NonPlayerCharacter extends Character {
    private final static Random rand = new Random(); // Random object for scrambling stats
    private final static int MIN_HEALTH = 50; // The minimum health for an NPC
    private final static int MAX_HEALTH = 151; // The maxinum health for an NPC
    private final static int MIN_ATTACK = 5; // The minimum attack for an NPC
    private final static int MAX_ATTACK = 16; // The maximum attack for an NPC
    private final static int MIN_DEFENSE = 0; // The minimum defense for an NPC
    private final static int MAX_DEFENSE = 6; // The maximum defense for an NPC
    private static String REPRESENTATION = "N"; // The tile representation of an NPC

    /**
     * The NonPlayerCharacter constructor for a character with defined stats
     * 
     * @param name the name of the NonPlayerCharacter
     * @param description the description of the NonPlayerCharacter
     * @param health the object's base max health
     * @param attack the object's base attack
     * @param defence the object's base defense
     */
    public NonPlayerCharacter(String name, String description, int health, int attack, int defense) {
        super(name, description, health, attack, defense);
    }

    /**
     * The NonPlayerCharacter constructor, generates random stats for the
     * character
     * 
     * @param name the name of the character
     * @param description the description of the charater
     */
    public NonPlayerCharacter(String name, String description) {
        super(name, description, 
            rand.nextInt(MIN_ATTACK, MAX_ATTACK), 
            rand.nextInt(MIN_DEFENSE, MAX_DEFENSE), 
            rand.nextInt(MIN_HEALTH, MAX_HEALTH));
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
