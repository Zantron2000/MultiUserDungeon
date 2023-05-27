package model.map.room.tile.Occupiers;

import controller.turnMapper.Direction;
import controller.turnMapper.TurnGenerator;
import model.character.Character;
import model.map.room.tile.Occupier;

public class Obstacle implements Occupier {
    private String description;

    public Obstacle(String description) {
        this.description = description;
    }

    public void interact(Character character) {
        return;
    }

    public void acceptTurnGenerator(TurnGenerator generator, Direction direction) {
        generator.generateCommand(this, direction);
    }
}
