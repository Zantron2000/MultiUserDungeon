package model.map.room.concrete_tiles.trap;

import java.util.Random;

import model.map.room.concrete_tiles.TrapCondition;

/**
 * This class represents a Trap tile in its armed condition
 */
public class HiddenTrap implements TrapCondition {
    private final static int BASE_ATTACK = 5; // The default attack value of a trap
    private final static String SYMBOL = " "; // The symbol representation of a hidden trap
    private final static String DESCRIPTION = ""; // The description of the hidden trap
    private final static boolean IS_ARMED = true; // The default armed condition for a hidden trap
    private final static boolean IS_DETECTED = false; // The default detected condition for a hidden trap

    private final int attack; // The attack value of the trap

    /**
     * Constructor for the HiddenTrap class, given a pre-defined attack value
     * 
     * @param attack the attack value of the trap
     */
    public HiddenTrap(int attack) {
        this.attack = attack;
    }    

    /**
     * Constructor for the HiddenTrap class, using the default attack value
     */
    public HiddenTrap() {
        this.attack = BASE_ATTACK;
    }

    /**
     * The triggered function for the TrapCondition interface
     */
    public int triggered() {
        return this.attack;
    }

    /**
     * The attemptDetect function for the TrapCondition interface
     */
    public boolean attemptDetect() {
        Random rand = new Random();
        int chance = rand.nextInt(0, 2);

        return chance == 1;
    }

    /**
     * The attemptDisarm function for the TrapCondition interface
     */
    public boolean attemptDisarm() {
        Random rand = new Random();
        int chance = rand.nextInt(0, 2);

        return chance == 1;
    }

    /**
     * The isArmed function for the TrapCondition interface
     */
    public boolean isArmed() {
        return IS_ARMED;
    }

    /**
     * The isDetected function for the TrapCondition interface
     */
    public boolean isDetected() {
        return IS_DETECTED;
    }

    /**
     * The getDescription function for the TrapCondition interface
     */
    public String getDescription() {
        return HiddenTrap.DESCRIPTION;
    }

    /**
     * The toString method for the HiddenTrap class
     */
    public String toString() {
        return SYMBOL;
    }
}