package model.map.room.factories;

import java.util.HashMap;

import model.character.PlayerCharacter;
import model.map.Coordinates;
import model.map.Direction;
import model.map.room.Room;
import model.map.room.Tile;
import model.map.room.concrete_tiles.*;
import model.map.room.generators.EnemyGenerator;
import model.map.room.generators.ItemGenerator;

public class RoomFactory {
    protected final static char CHEST = 'C';
    protected final static char EMPTY = 'B';
    protected final static char ENEMY = 'N';
    protected final static char EXIT = 'E';
    protected final static char MERCHANT = 'M';
    protected final static char OBSTICLE = 'O';
    protected final static char SHRINE = 'S';
    protected final static char TRAP = 'T';
    protected final static char PLAYER = 'P';
    protected final static char GOAL = 'G';
    
    private ItemGenerator itemGenerator;
    private EnemyGenerator enemyGenerator;
    
    public RoomFactory() {
        itemGenerator = ItemGenerator.instance();
        enemyGenerator = EnemyGenerator.instance();
    }

    public Room generatePremadeRoom(int roomX, int roomY, String layout, PlayerCharacter player) {
        int width;
        int length;
        Coordinates roomCoords = new Coordinates(roomX, roomY);
        HashMap<Coordinates, Tile> mapLayout = new HashMap<>();
        HashMap<Direction, ExitTile> exits = new HashMap<>();

        String[] rows = layout.split("\\|");
        width = rows[0].length();
        length = rows.length;

        for(int y = 0; y < rows.length; y++) {
            for(int x = 0; x < rows[y].length(); x++) {
                char type = rows[y].charAt(x);
                Coordinates coords = new Coordinates(x, y);

                if(type == CHEST) {
                    mapLayout.put(coords, new ChestTile(x, y, itemGenerator.generateChest()));
                } else if(type == EMPTY) {
                    mapLayout.put(coords, new EmptyTile(x, y));
                } else if(type == ENEMY) {
                    mapLayout.put(coords, new EmptyTile(x, y, enemyGenerator.generateEnemy()));
                } else if(type == EXIT) {
                    Direction direction = getDirection(rows[0].length() - 1, rows.length - 1, x, y);
                    ExitTile exit = new ExitTile(y, x, direction);
                    mapLayout.put(coords, exit);
                    exits.put(direction, exit);
                } else if(type == MERCHANT) {
                    mapLayout.put(coords, new MerchantTile(x, y, itemGenerator.generateStore()));
                } else if(type == OBSTICLE) {
                    mapLayout.put(coords, new ObstacleTile(x, y, "A large boulder"));
                } else if(type == SHRINE) {
                    mapLayout.put(coords, new ShrineTile(x, y));
                } else if(type == TRAP) {
                    mapLayout.put(coords, new TrapTile(x, y));
                } else if(type == PLAYER) {
                    Tile tile = new EmptyTile(x, y, player);
                    player.moveTo(tile);
                    mapLayout.put(coords, tile);
                } else if(type == GOAL) {
                    mapLayout.put(coords, new GoalTile(x, y));
                } else {
                    System.out.println("Error reading tile type: " + type);
                    mapLayout.put(coords, new EmptyTile(x, y));
                }
            }
        }

        return new Room(width, length, mapLayout, exits, roomCoords, layout);
    }

    public Room generatePremadeRoom(Coordinates roomCoords, String layout, PlayerCharacter player) {
        int width;
        int length;
        HashMap<Coordinates, Tile> mapLayout = new HashMap<>();
        HashMap<Direction, ExitTile> exits = new HashMap<>();

        String[] rows = layout.split("\\|");
        width = rows[0].length();
        length = rows.length;

        for(int y = 0; y < rows.length; y++) {
            for(int x = 0; x < rows[y].length(); x++) {
                char type = rows[y].charAt(x);
                Coordinates coords = new Coordinates(x, y);

                if(type == CHEST) {
                    mapLayout.put(coords, new ChestTile(x, y, itemGenerator.generateChest()));
                } else if(type == EMPTY) {
                    mapLayout.put(coords, new EmptyTile(x, y));
                } else if(type == ENEMY) {
                    mapLayout.put(coords, new EmptyTile(x, y, enemyGenerator.generateEnemy()));
                } else if(type == EXIT) {
                    Direction direction = getDirection(rows[0].length() - 1, rows.length - 1, x, y);
                    ExitTile exit = new ExitTile(y, x, direction);
                    mapLayout.put(coords, exit);
                    exits.put(direction, exit);
                } else if(type == MERCHANT) {
                    mapLayout.put(coords, new MerchantTile(x, y, itemGenerator.generateStore()));
                } else if(type == OBSTICLE) {
                    mapLayout.put(coords, new ObstacleTile(x, y, "A large boulder"));
                } else if(type == SHRINE) {
                    mapLayout.put(coords, new ShrineTile(x, y));
                } else if(type == TRAP) {
                    mapLayout.put(coords, new TrapTile(x, y));
                } else if(type == PLAYER) {
                    mapLayout.put(coords, new EmptyTile(x, y, player));
                } else if(type == GOAL) {
                    mapLayout.put(coords, new GoalTile(x, y));
                } else {
                    System.out.println("Error reading tile type: " + type);
                    mapLayout.put(coords, new EmptyTile(x, y));
                }
            }
        }

        return new Room(width, length, mapLayout, exits, roomCoords, layout);
    }

    private Direction getDirection(int maxX, int maxY, int x, int y) {
        if(x == maxX) {
            return Direction.EAST;
        } else if(y == maxY) {
            return Direction.NORTH;
        } else if(x == 0) {
            return Direction.WEST;
        } else {
            return Direction.SOUTH;
        }
    }    
}
