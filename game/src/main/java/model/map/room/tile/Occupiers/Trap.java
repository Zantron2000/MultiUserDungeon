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

    public String interact(Character character) {
        if(this.phase.isSeen() && !this.phase.isDisarmed()) {
            int damage = this.phase.attemptDisarm();

            character.takeDamage(damage);
            
            if(damage == 0) {
                return "The trap was successfully disarmed";
            }

            return "The trap was set off and closed on " + character.toString();
        }

        return "";
    }

    public String moveOnto(Character character) {
        if(!this.phase.isDisarmed()) {
            int damage = this.phase.triggerTrap();
            character.takeDamage(damage);

            return "Stepped on a live trap!";
        }

        return "Stepped onto a disarmed trap";
    }

    public void acceptTurnGenerator(TurnMapper generator, Direction direction) {
        if(this.phase.isSeen() && !this.phase.isDisarmed()) {
            generator.generateCommand(this, direction);
        }
    }

    public char getIcon() {
        return this.phase.getIcon();
    }
}
