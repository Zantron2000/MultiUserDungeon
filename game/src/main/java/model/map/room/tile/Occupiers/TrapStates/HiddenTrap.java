package model.map.room.tile.Occupiers.TrapStates;

import model.map.room.tile.Occupiers.Trap;
import model.map.room.tile.Occupiers.TrapState;

public class HiddenTrap implements TrapState {
    private static char ICON = ' ';
    private static boolean DISARMED = false;
    private static boolean SEEN = false;
    
    private Trap trap;
    private int damage;

    public HiddenTrap(Trap trap, int damage) {
        this.trap = trap;
        this.damage = damage;
    }

    public boolean isDisarmed() {
        return HiddenTrap.DISARMED;
    }

    public boolean isSeen() {
        return HiddenTrap.SEEN;
    }

    public int attemptDisarm() {
        trap.setTrapState(new TriggeredTrap());

        return this.damage;
    }

    public int triggerTrap() {
        trap.setTrapState(new TriggeredTrap());

        return this.damage;
    }

    public char getIcon() {
        return HiddenTrap.ICON;
    }
}
