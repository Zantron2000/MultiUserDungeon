package model.map.room.concrete_tiles;

import model.character.Character;
import model.map.room.concrete_tiles.trap.*;

public class TrapTile extends MovableTile {
    private TrapCondition currCondition; // The condition of the trap in the trap tile

    /**
     * The constructor for the TrapTile class, sets the default current condition
     * of the trap as a hidden trap, used for initial generation
     */
    public TrapTile(int x, int y) {
        super(x, y);
        this.currCondition = new HiddenTrap();
    }

    /**
     * The constructor for the TrapTile class, sets the current condition to a preset
     * condition, for testing and loading data
     * 
     * @param condition the condition the test is in
     */
    public TrapTile(int x, int y, TrapCondition condition) {
        super(x, y);
        this.currCondition = condition;
    }

    /**
     * The getDescription function from the Tile abstract class
     */
    public String getDescription() {
        return this.currCondition.getDescription() + super.getDescription();
    }

    /**
     * The getTileType function from the Tile abstract class,
     * overrides the representation of the trap if a character is on it
     */
    @Override
    public String getTileType() {
        String characterString = super.getTileType();
    
        if(characterString.length() == 0) {
            return this.currCondition.toString();
        } else {
            return characterString;
        }
    }

    /**
     * The moveOn function from the abstract MovableTile class, deals damage to a character
     * that moves on if the tile is armed, changes condition to a disarmed trap, then calls the super
     * 
     */
    public void moveOn(Character character) {
        if(this.currCondition.isArmed()) {
            character.takeDamage(this.currCondition.triggered());
            this.currCondition = new DisarmedTrap();
        }

        super.moveOn(character);
    }

    /**
     * Attempts to disarm the trap on player command, rolls chance of success
     * and calculates the damage to take, then changes the state of the trap to
     * a disarmed trap
     * 
     * @return the amount of damage the player will take from disarming
     */
    public int attemptDisarm() {
        int damage = 0;

        if(this.currCondition.isArmed()) {
            if(!this.currCondition.attemptDisarm()) {
                damage = this.currCondition.triggered();
            }

            this.currCondition = new DisarmedTrap();
        }

        return damage;
    }

    /**
     * Attempts to detect a nearby trap when provoked, rolls a chance of success,
     * and depending on the success, updates the state of the trap to a detected trap
     * 
     * @return whether the trap has now been detected
     */
    public boolean attemptDetect() {
        boolean seen = false;
        if(!this.currCondition.isDetected()) {
            if(this.currCondition.attemptDetect()) {
                this.currCondition = new DetectedTrap();
                seen = true;
            }
        }

        return seen;
    }
    
    /**
     * A tester function for attempt to detect to negate randomness
     * 
     * @param detected if the player successfully detected the trap
     * @return whether the trap is now detected
     */
    public boolean attemptDetectTester(boolean detected) {
        boolean seen = false;
        if(!this.currCondition.isDetected()) {
            if(detected) {
                this.currCondition = new DetectedTrap();
                seen = true;
            }
        }

        return seen;
    }

    /**
     * A tester function for attempt to disarm to negate randomness
     * 
     * @param disarmed if the user successfully disarmed the trap
     * @return the amount of damage the player will take from disarming
     */
    public int attemptDisarmTester(boolean disarmed) {
        int damage = 0;

        if(this.currCondition.isArmed()) {
            if(!disarmed) {
                damage = this.currCondition.triggered();
            }

            this.currCondition = new DisarmedTrap();
        }

        return damage;
    }

    /**
     * A getter for the current condition of the trap, for testing purposes
     * 
     * @return the current trap's condition
     */
    public TrapCondition getTrapCondition() {
        return this.currCondition;
    }
}
