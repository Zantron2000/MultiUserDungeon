package model.map.room.tile.Occupiers;

import controller.turnMapper.Direction;
import controller.turnMapper.TurnMapper;
import model.character.Character;
import model.map.Map;
import model.map.room.tile.Terrain;
public class Exit implements Terrain {
    private static char ICON = 'E';

    private Direction direction;
    private Map map;

    public Exit(Direction direction, Map map) {
        this.direction = direction;
        this.map = map;
    }

    public String interact(Character character) {
        this.map.moveRooms(character, direction);

        return character.toString() + " moved to the next room";
    }

    public String moveOnto(Character character) {
        return "Stepped onto an exit";
    }

    public void acceptTurnGenerator(TurnMapper mapper, Direction direction) {
        mapper.generateCommand(this, direction);
    }

    public char getIcon() {
        return Exit.ICON;
    }
}
