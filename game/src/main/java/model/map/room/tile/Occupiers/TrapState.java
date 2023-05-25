package model.map.room.tile.Occupiers;

public interface TrapState {
    public boolean isDisarmed();

    public boolean isSeen();

    public int triggerTrap();

    public int attemptDisarm();
}
