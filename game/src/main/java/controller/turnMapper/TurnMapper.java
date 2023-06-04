package controller.turnMapper;

import java.util.ArrayList;
import java.util.HashMap;

import model.character.Character;
import model.map.Coordinates;
import model.map.room.tile.Tile;
import model.map.room.tile.Occupiers.Chest;
import model.map.room.tile.Occupiers.Corpse;
import model.map.room.tile.Occupiers.Obstacle;
import model.map.room.tile.Occupiers.Trap;

public interface TurnMapper {
    public HashMap<String, Command> getMoves();

    public ArrayList<Coordinates> getMoveCoordinates();

    public ArrayList<Direction> getMoveDirections();

    public void generateCommand(Obstacle obstacle, Direction direction);

    public void generateCommand(Character enemy, Direction direction);

    public void generateCommand(Chest chest, Direction direction);

    public void generateCommand(Corpse corpse, Direction direction);

    public void generateCommand(Trap trap, Direction direction);

    public void generateCommand(Tile tile, Direction direction);
}
