package model.map.room.tile.Occupiers.TrapStates;

import model.map.room.tile.Occupiers.TrapState;

public class TriggeredTrap implements TrapState {
    private static boolean DISARMED = false;
    private static boolean SEEN = false;
    private static int DAMAGE = 0;

    public boolean isDisarmed() {
        return TriggeredTrap.DISARMED;
    }

    public boolean isSeen() {
        return TriggeredTrap.SEEN;
    }

    public int attemptDisarm() {
        return TriggeredTrap.DAMAGE;
    }

    public int triggerTrap() {
        return TriggeredTrap.DAMAGE;
    }
}
