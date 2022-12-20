package model.attribute;

import model.inventory.Item;
import java.util.HashMap;

/**
 * Represents a modifier for stats to change either attack or defense of a character.
 * Keeps track of the turns it has remaining until it expires
 * 
 */
public class Stat {
    private final static int BASE_TURN = 10; // Base turn amount for a stat

    protected int attack; // The attack modifier stat
    protected int defense; // The defense modifier stat
    protected int turns; // The amount of turns the stat will last for

    /**
     * The Stat constructor, assumes the turns for the stat will
     * be the base turn
     * 
     * @param stats the modifier of the item being added
     */
    public Stat(HashMap<String, Integer> stats) {
        this.attack = stats.get(Item.ATTACK_KEY);
        this.defense = stats.get(Item.DEFENSE_KEY);
        this.turns = Stat.BASE_TURN;
    }

    /**
     * The Stat constructor, takes in a HashMap containing the stat buffs
     * 
     * @param stats the HashMap containing the attack and defense buff values
     * @param turns the amount of turns the stat will last for
     */
    public Stat(HashMap<String, Integer> stats, int turns) {
        this.attack = stats.get(Item.ATTACK_KEY);
        this.defense = stats.get(Item.DEFENSE_KEY);
        this.turns = turns;
    }

    /**
     * The Stat constructor
     * 
     * @param attack the attack modifier for the stat
     * @param defense the defense modifier for the stat
     * @param turns the amount of turns the stat will last for
     */
    public Stat(int attack, int defense, int turns) {
        this.attack = attack;
        this.defense = defense;
        this.turns = turns;
    }

    /**
     * Passes a turn for the stat and checks to see if it has
     * expired 
     * 
     * @return boolean based on if the stat has expired
     */
    public boolean advanceTurn() {
        if(this.turns > 0) {
            this.turns -= 1;
        }

        return this.turns == 0;
    }

    /**
     * Gets the attack modifier for that stat
     * 
     * @return the attack modifier for that stat
     */
    public int getAttack() {
        return this.attack;
    }

    /**
     * Gets the defense modifier for that stat
     * 
     * @return the defense modifier for that stat
     */
    public int getDefense() {
        return this.defense;
    }
}
