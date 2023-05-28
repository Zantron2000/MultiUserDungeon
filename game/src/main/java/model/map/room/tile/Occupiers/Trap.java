package model.map.room.tile.Occupiers;

import controller.turnMapper.Direction;
import controller.turnMapper.TurnMapper;
import model.character.Character;
import model.map.room.tile.Terrain;
import model.map.room.tile.Occupiers.TrapStates.HiddenTrap;

public class Trap implements Terrain {
    private TrapState phase;

    public Trap(int damage) {
        this.phase = new HiddenTrap(this, damage);
    }

    public Trap(TrapState phase) {
        this.phase = phase;
    }

    public void setTrapState(TrapState phase) {
        this.phase = phase;
    }

    public void interact(Character character) {
        if(this.phase.isSeen() && !this.phase.isDisarmed()) {
            int damage = this.phase.attemptDisarm();

            character.takeDamage(damage);
        }
    }

    public void moveOnto(Character character) {
        if(!this.phase.isDisarmed()) {
            int damage = this.phase.triggerTrap();

            character.takeDamage(damage);
        }
    }

    public void acceptTurnGenerator(TurnMapper generator, Direction direction) {
        if(this.phase.isSeen() && !this.phase.isDisarmed()) {
            generator.generateCommand(this, direction);
        }
    }
}
