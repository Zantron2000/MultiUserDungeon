package model.map.room.tile.Occupiers.TrapStates;

import model.map.room.tile.Occupiers.Trap;
import model.map.room.tile.Occupiers.TrapState;

public class DetectedTrap implements TrapState {
    private static boolean DISARMED = false;
    private static boolean SEEN = true;
    private static double DiSARM_CHANCE = 0.50;
    
    private Trap trap;
    private int damage;

    public DetectedTrap(Trap trap, int damage) {
        this.trap = trap;
        this.damage = damage;
    }

    public boolean isDisarmed() {
        return DetectedTrap.DISARMED;
    }

    public boolean isSeen() {
        return DetectedTrap.SEEN;
    }

    public int attemptDisarm() {
        double chance = Math.random();

        if(chance > DetectedTrap.DiSARM_CHANCE) {
            this.damage = 0;
        }

        this.trap.setTrapState(new TriggeredTrap());
        return this.damage;
    }

    public int triggerTrap() {
        this.trap.setTrapState(new TriggeredTrap());

        return this.damage;
    }
}
