package model.map.room.concrete_tiles.trap;

import model.map.room.concrete_tiles.TrapCondition;

/**
 * This class represents a Trap tile in its disarmed condition
 */
public class DisarmedTrap implements TrapCondition {
    private final static int BASE_ATTACK = 0; // The default attack value of a trap
    private final static String SYMBOL = "V"; // The symbol representation of a hidden trap
    private final static String DESCRIPTION = "There is a disarmed trap on the ground here. "; // The description of the detected trap
    private final static boolean IS_ARMED = false; // The default armed condition for a hidden trap
    private final static boolean IS_DETECTED = true; // The default detected condition for a hidden trap

    private final int attack; // The attack value of the trap

    /**
     * Constructor for the Disarmed class, using the default attack value
     */
    public DisarmedTrap() {
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
        return false;
    }

    /**
     * The attemptDisarm function for the TrapCondition interface
     */
    public boolean attemptDisarm() {
        return false;
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
        return DisarmedTrap.DESCRIPTION;
    }

    /**
     * The toString method for the Disarmed class
     */
    public String toString() {
        return SYMBOL;
    }
}