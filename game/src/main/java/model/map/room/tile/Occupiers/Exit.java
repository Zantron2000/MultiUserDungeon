package model.map.room.tile.Occupiers;

import controller.turnMapper.Direction;
import controller.turnMapper.TurnMapper;
import model.character.Character;
import model.map.Map;
import model.map.room.tile.Terrain;

public class Exit implements Terrain {
    private Direction direction;
    private Map map;

    public Exit(Direction direction, Map map) {
        this.direction = direction;
        this.map = map;
    }

    public void interact(Character character) {
        this.map.moveRooms(character, direction);
    }

    public void moveOnto(Character character) {
        // TODO PTUI for asking if they want to move on
    }

    public void acceptTurnGenerator(TurnMapper mapper, Direction direction) {
        // TODO make method for mapper and call here
    }
}
