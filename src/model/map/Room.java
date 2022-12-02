package model.map;

import java.util.HashMap;

import model.map.concrete_tiles.ExitTile;

public class Room {
    private int width;
    private int height;
    private HashMap<Coordinates, Tile> layout;
    private HashMap<Direction, ExitTile> exits;
    private Coordinates coords;

    public Room(int width, int height, HashMap<Coordinates, Tile> layout, HashMap<Direction, ExitTile> exits, Coordinates coords) {
        this.width = width;
        this.height = height;
        this.layout = layout;
        this.exits = exits;
        this.coords = coords;
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

    public String toString() {
        String room = "";
        for(int i = 0; i < this.height; i++) {
            for(int j = 0; j < this.width; j++) {
                Tile tile = this.layout.get(new Coordinates(i, j));

                room += "|" + tile.getTileType();
            }

            room += "|\n";
        }

        return room;
    }
}
