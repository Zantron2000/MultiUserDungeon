package model.map.room.tile.Occupiers;

import controller.turnMapper.Direction;
import controller.turnMapper.TurnMapper;
import model.character.Character;
import model.map.room.tile.Occupier;

public class Obstacle implements Occupier {
    private static char ICON = 'O';

    private String description;

    public Obstacle(String description) {
        this.description = description;
    }

    public String interact(Character character) {
        return this.description + " blocked the path";
    }

    public void acceptTurnGenerator(TurnMapper generator, Direction direction) {
        generator.generateCommand(this, direction);
    }

    public char getIcon() {
        return Obstacle.ICON;
    }
}
