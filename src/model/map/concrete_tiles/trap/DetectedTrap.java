package model.map.concrete_tiles.trap;

import java.util.Random;

import model.map.concrete_tiles.TrapCondition;

/**
 * This class represents a Trap tile in its detected condition
 */
public class DetectedTrap implements TrapCondition  {
    private static int BASE_ATTACK = 5; // The default attack value of a trap
    private static String SYMBOL = "-"; // The symbol representation of a detected trap
    private static String DESCRIPTION = "There is an armed trap on the ground here. "; // The description of the detected trap
    private static boolean IS_ARMED = true; // The default armed condition for a detected trap
    private static boolean IS_DETECTED = true; // The default detected condition for a detected trap

    private int attack; // The attack value of the trap

    /**
     * Constructor for the DetectedTrap class, given a pre-defined attack value
     * 
     * @param attack the attack value of the trap
     */
    public DetectedTrap(int attack) {
        this.attack = attack;
    }    

    /**
     * Constructor for the DetectedTrap class, using the default attack value
     */
    public DetectedTrap() {
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
        return DetectedTrap.DESCRIPTION;
    }

    /**
     * The toString method for the DetectedTrap class
     */
    public String toString() {
        return SYMBOL;
    }
}