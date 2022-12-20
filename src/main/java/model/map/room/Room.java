package model.map.room;

import java.util.HashMap;

import model.character.NonPlayerCharacter;
import model.map.Coordinates;
import model.map.Direction;
import model.map.room.concrete_tiles.ExitTile;

public class Room {
    private int width;
    private int height;
    private HashMap<Coordinates, Tile> layout;
    private HashMap<Direction, ExitTile> exits;
    private Coordinates coords;
    private String floorPlan;

    public Room(int width, int height, HashMap<Coordinates, Tile> layout, HashMap<Direction, ExitTile> exits, Coordinates coords, String floorPlan) {
        this.width = width;
        this.height = height;
        this.layout = layout;
        this.exits = exits;
        this.coords = coords;
        this.floorPlan = floorPlan;
    }

    public Tile getTile(Coordinates tileCoords) {
        return this.layout.get(tileCoords);
    }

    public Tile getExit(Direction direction) {
        return this.exits.get(direction);
    }

    public Coordinates getNeighbor(Direction direction) {
        return this.coords.nextCoordinates(direction);
    }

    public void changeTile(Coordinates coords, Tile tile) {
        this.layout.put(coords, tile);
    }

    public String getFloorPlan() {
        return this.floorPlan;
    }

    public boolean isSafe() {
        for(int y = this.height - 1; y >= 0; y--) {
            for(int x = 0; x < this.width; x++) {
                Tile tile = this.layout.get(new Coordinates(x, y));
                if(NonPlayerCharacter.REPRESENTATION.equals(tile.getTileType())) {
                    return false;
                }
            }
        }

        return true; 
    }

    public String toString() {
        String room = "";
        for(int y = this.height - 1; y >= 0; y--) {
            for(int x = 0; x < this.width; x++) {
                Tile tile = this.layout.get(new Coordinates(x, y));
                room += "|" + tile.getTileType();
            }

            room += "|\n";
        }

        return room;
    }
}
