package model.map.room.concrete_tiles;

/**
 * This acts as an interface for all the different conditions a trap can
 * exist as.
 */
public interface TrapCondition {
    /**
     * Gets and returns the damage the trap does when triggered
     * 
     * @return the damage the trap does when triggered
     */
    int triggered();

    /**
     * Rolls chance to see if a character successfully disarms a trap,
     * given a 50/50 chance to disarm
     * 
     * @return whether the player has successfully disarmed a trap
     */
    boolean attemptDisarm();

    /**
     * Rolls a chance to see if a character successfully spots a trap,
     * given a 50/50 chance to spot it
     * 
     * @return if the player successfully detected the trap
     */
    boolean attemptDetect();

    /**
     * Checks and returns if the state of the trap is currently armed
     * 
     * @return whether the trap is armed or not
     */
    boolean isArmed();

    /**
     * Checks and returns if the state of the trap is currently detectable
     * 
     * @return whether the trap is detected
     */
    boolean isDetected();

    /**
     * Gets the trap's description
     * 
     * @return the description of the current condition of the trap
     */
    String getDescription();

    /**
     * The toString representation of the trap
     * 
     * @return the string representation of the trap's state
     */
    String toString();
}
