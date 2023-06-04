package controller.turnMapper.turnMappers;

import java.util.ArrayList;
import java.util.HashMap;

import controller.turnMapper.Command;
import controller.turnMapper.Direction;
import controller.turnMapper.TurnMapper;
import controller.turnMapper.commands.InteractCommand;
import controller.turnMapper.commands.MoveCommand;
import model.character.Character;
import model.map.Coordinates;
import model.map.room.tile.Tile;
import model.map.room.tile.Occupiers.Chest;
import model.map.room.tile.Occupiers.Corpse;
import model.map.room.tile.Occupiers.Obstacle;
import model.map.room.tile.Occupiers.Trap;

public class PlayerTurnMapper implements TurnMapper {
    private HashMap<String, Command> commands;
    private Character interacter;
    private Coordinates coords;

    public PlayerTurnMapper(Character character) {
        this.interacter = character;
        this.coords = character.getCoordinates();
        this.commands = new HashMap<>();
    }

    public HashMap<String, Command> getMoves() {
        return this.commands;
    }

    public ArrayList<Coordinates> getMoveCoordinates() {
        ArrayList<Coordinates> coords = new ArrayList<>();

        coords.add(this.coords);
        coords.add(Direction.applyDirection(this.coords, Direction.NORTH));
        coords.add(Direction.applyDirection(this.coords, Direction.SOUTH));
        coords.add(Direction.applyDirection(this.coords, Direction.EAST));
        coords.add(Direction.applyDirection(this.coords, Direction.WEST));

        return coords;
    }

    public ArrayList<Direction> getMoveDirections() {
        ArrayList<Direction> directions = new ArrayList<>();

        directions.add(Direction.CURRENT);
        directions.add(Direction.NORTH);
        directions.add(Direction.SOUTH);
        directions.add(Direction.EAST);
        directions.add(Direction.WEST);

        return directions;
    }

    public void generateCommand(Obstacle obstacle, Direction direction) {}

    public void generateCommand(Character enemy, Direction direction) {
        String key = "interact " + direction.toString();
        Command value = new InteractCommand(this.interacter, enemy, "Enemy", direction.toString());

        commands.put(key, value);
    }

    public void generateCommand(Chest chest, Direction direction) {
        String key = "interact " + direction.toString();
        Command value = new InteractCommand(interacter, chest, "Chest", direction.toString());

        commands.put(key, value);
    }

    public void generateCommand(Corpse corpse, Direction direction) {
        String key = "interact " + direction.toString();
        Command value = new InteractCommand(interacter, corpse, "Corpse", direction.toString());

        commands.put(key, value);
    }

    public void generateCommand(Trap trap, Direction direction) {
        String key = "interact " + direction.toString();
        Command value = new InteractCommand(interacter, trap, "Trap", direction.toString());

        commands.put(key, value);
    }

    public void generateCommand(Tile tile, Direction direction) {
        String key = "move " + direction.toString();
        Command value = new MoveCommand(interacter, tile, direction.toString());

        commands.put(key, value);
    }
    
}
