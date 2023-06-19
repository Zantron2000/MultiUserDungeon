package controller.turnMapper.turnMappers;

import java.util.ArrayList;
import java.util.HashMap;

import controller.InventoryManagement.Managers.ChestManager;
import controller.InventoryManagement.Managers.CorpseManager;
import controller.turnMapper.Command;
import controller.turnMapper.Direction;
import controller.turnMapper.TurnMapper;
import controller.turnMapper.commands.InteractCommand;
import controller.turnMapper.commands.MoveCommand;
import controller.turnMapper.commands.PtuiCommand;
import model.character.Character;
import model.map.Coordinates;
import model.map.room.tile.Tile;
import model.map.room.tile.Occupiers.Chest;
import model.map.room.tile.Occupiers.Corpse;
import model.map.room.tile.Occupiers.Exit;
import model.map.room.tile.Occupiers.Goal;
import model.map.room.tile.Occupiers.Obstacle;
import model.map.room.tile.Occupiers.Trap;
import view.interactionPTUI.DungeonPTUI;

public class PlayerTurnMapper implements TurnMapper {
    private HashMap<String, Command> commands;
    private Character interacter;
    private Coordinates coords;
    private DungeonPTUI ptui;

    public PlayerTurnMapper(Character character, DungeonPTUI ptui) {
        this.interacter = character;
        this.coords = character.getCoordinates();
        this.commands = new HashMap<>();
        this.ptui = ptui;
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
        String action = "Fight " + enemy.toString() + " to the " + direction.toString();

        Command value = new InteractCommand(this.interacter, enemy, action);

        commands.put(key, value);
    }

    public void generateCommand(Chest chest, Direction direction) {
        String key = "interact " + direction.toString();
        String action = "Loot a chest to the " + direction.toString();

        Command value = new PtuiCommand(new ChestManager(interacter, chest), this.ptui, action);

        commands.put(key, value);
    }

    public void generateCommand(Corpse corpse, Direction direction) {
        String key = "interact " + direction.toString();
        String action = "Loot a corpse to the " + direction.toString();

        Command value = new PtuiCommand(new CorpseManager(interacter, corpse), this.ptui, action);

        commands.put(key, value);
    }

    public void generateCommand(Trap trap, Direction direction) {
        String key = "interact " + direction.toString();
        String action = "Disarm the trap to the " + direction.toString();

        Command value = new InteractCommand(interacter, trap, action);

        commands.put(key, value);
    }

    public void generateCommand(Exit exit, Direction direction) {
        String key = "interact " + direction.toString();
        String action = "Exit to the next room towards the " + direction.toString();

        Command value = new InteractCommand(interacter, exit, action);

        commands.put(key, value);
    }

    public void generateCommand(Goal goal, Direction direction) {
        String key = "interact " + direction.toString();
        String action = "Escape the dungeon through the " + direction.toString() + " portal";

        Command value = new InteractCommand(interacter, goal, action);

        commands.put(key, value);
    }

    public void generateCommand(Tile tile, Direction direction) {
        String key = "move " + direction.toString();
        String action = "Move onto the " + direction.toString() + " tile";

        Command value = new MoveCommand(interacter, tile, action);

        commands.put(key, value);
    }
    
}
