package controller.turnMapper.turnMappers;

import java.util.ArrayList;
import java.util.HashMap;

import controller.turnMapper.Command;
import controller.turnMapper.Direction;
import controller.turnMapper.TurnMapper;
import controller.turnMapper.commands.InteractCommand;
import model.character.Character;
import model.map.Coordinates;
import model.map.room.tile.Tile;
import model.map.room.tile.Occupiers.Chest;
import model.map.room.tile.Occupiers.Corpse;
import model.map.room.tile.Occupiers.Obstacle;
import model.map.room.tile.Occupiers.Trap;

public class EnemyTurnMapper implements TurnMapper {
    private HashMap<String, Command> commands;
    private Character attackee;
    private Coordinates coords;

    public EnemyTurnMapper(Character character) {
        this.attackee = character;
        this.coords = character.getCoordinates();
        this.commands = new HashMap<>();
    }

    public HashMap<String, Command> getMoves() {
        return this.commands;
    }

    public ArrayList<Coordinates> getMoveCoordinates() {
        ArrayList<Coordinates> coords = new ArrayList<>();

        coords.add(Direction.applyDirection(this.coords, Direction.NORTH));
        coords.add(Direction.applyDirection(this.coords, Direction.SOUTH));
        coords.add(Direction.applyDirection(this.coords, Direction.EAST));
        coords.add(Direction.applyDirection(this.coords, Direction.WEST));
        coords.add(Direction.applyDirection(this.coords, Direction.NORTH_WEST));
        coords.add(Direction.applyDirection(this.coords, Direction.NORTH_EAST));
        coords.add(Direction.applyDirection(this.coords, Direction.SOUTH_WEST));
        coords.add(Direction.applyDirection(this.coords, Direction.SOUTH_EAST));

        return coords;
    }

    public ArrayList<Direction> getMoveDirections() {
        ArrayList<Direction> directions = new ArrayList<>();

        directions.add(Direction.NORTH);
        directions.add(Direction.SOUTH);
        directions.add(Direction.EAST);
        directions.add(Direction.WEST);
        directions.add(Direction.NORTH_WEST);
        directions.add(Direction.NORTH_EAST);
        directions.add(Direction.SOUTH_WEST);
        directions.add(Direction.SOUTH_EAST);
        
        return directions;
    }

    public void generateCommand(Obstacle obstacle, Direction direction) {};

    public void generateCommand(Character attacker, Direction direction) {
        String key = "attack " + direction.toString();
        Command value = new InteractCommand(attacker, attackee, "Player", direction.toString());

        commands.put(key, value);
    };

    public void generateCommand(Chest chest, Direction direction) {};

    public void generateCommand(Corpse corpse, Direction direction) {};

    public void generateCommand(Trap trap, Direction direction) {};

    public void generateCommand(Tile tile, Direction direction) {};
}
