package controller.gameGenerators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import controller.gameController.Game;
import controller.gameController.TurnObserver;
import controller.turnMapper.Direction;
import model.character.characters.NonPlayerCharacter;
import model.character.characters.PlayerCharacter;
import model.map.Coordinates;
import model.map.Map;
import model.map.TimeObserver;
import model.map.room.Room;
import model.map.room.tile.Occupier;
import model.map.room.tile.Terrain;
import model.map.room.tile.Tile;
import model.map.room.tile.Occupiers.Chest;
import model.map.room.tile.Occupiers.Exit;
import model.map.room.tile.Occupiers.Goal;
import model.map.room.tile.Occupiers.Obstacle;
import model.map.room.tile.Occupiers.Trap;

public class RoomGenerator {
    private static Map map;
    private static Game game;

    protected static void setMap(Map map) {
        RoomGenerator.map = map;
    }

    protected static void setGame(Game game) {
        RoomGenerator.game = game;
    }

    public static Room generateRoom(int rows, int cols, String floorPlan, PlayerCharacter player) {
        ArrayList<TurnObserver> turnObservers = new ArrayList<>();
        ArrayList<TimeObserver> timeObservers = new ArrayList<>();
        HashMap<Direction, Tile> exits = new HashMap<>();
        Tile[][] layout = new Tile[rows][cols];

        String[] data = floorPlan.split("\n");

        for(int r = 0; r < rows; r++) {
            String[] strip = data[r].split("\\|");

            for(int c = 0; c < cols; c++) {
                Tile tile = RoomGenerator.generateTile(strip[c], r, c, rows, cols, player, turnObservers, timeObservers, exits);

                layout[r][c] = tile;
            }
        }

        return new Room(layout, turnObservers, timeObservers, exits);
    }

    private static Tile generateTile(String occupier, int row, int col, int rows, int cols, PlayerCharacter player, ArrayList<TurnObserver> turnObservers, ArrayList<TimeObserver> timeObservers, HashMap<Direction, Tile> exits) {
        Coordinates coords = new Coordinates(row, col);
        Tile tile;

        if(occupier.equals("B")) {
            tile = new Tile(coords);
        } else if(occupier.equals("P")) {
            tile = new Tile(coords, player);
            player.moveOnto(tile);
        } else if(occupier.equals("C")) {
            Random random = new Random();
            int gold = random.nextInt(101);
            Occupier chest = new Chest(ItemGenerator.generateChestItems(), gold);

            tile = new Tile(coords, chest);
        } else if(occupier.equals("E")) {
            NonPlayerCharacter enemy = EnemyGenerator.generateNPC();
            turnObservers.add(enemy);
            timeObservers.add(enemy);

            tile = new Tile(coords, enemy);
            enemy.moveOnto(tile);
        } else if(occupier.equals("T")) {
            Terrain trap = new Trap(5);

            tile = new Tile(coords, trap);
        } else if(occupier.equals("O")) {
            Occupier obstacle = new Obstacle("A giant rock");

            tile = new Tile(coords, obstacle);
        } else if(occupier.equals("M")) {
            Direction direction = RoomGenerator.findDirection(row, col, rows, cols);
            Exit exit = new Exit(direction, RoomGenerator.map);

            tile = new Tile(coords, exit);
            exits.put(direction, tile);
        } else if(occupier.equals("Q")) {
            Goal goal = new Goal(RoomGenerator.game);

            tile = new Tile(coords, goal);
        } else {
            System.out.println("Error: " + occupier);

            tile = null;
        }

        return tile;
    }

    private static Direction findDirection(int row, int col, int rows, int cols) {
        if(row == 0 && col == 0) {
            return Direction.NORTH_WEST;
        } else if(row == rows - 1 && col == 0) {
            return Direction.SOUTH_WEST;
        } else if(row == 0 && col == cols - 1) {
            return Direction.NORTH_EAST;
        } else if(row == rows - 1 && col == cols - 1) {
            return Direction.SOUTH_EAST;
        } else if(row == 0) {
            return Direction.NORTH;
        } else if(row == rows - 1) {
            return Direction.SOUTH;
        } else if(col == 0) {
            return Direction.WEST;
        } else if(col == cols - 1) {
            return Direction.EAST;
        } else {
            return null;
        }
    }
}
