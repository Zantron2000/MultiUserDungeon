package model.map.room;

import model.map.Coordinates;
import model.map.Direction;

/**
 * Tile represents one 3x3 foot square that can be occupied by several things
 */
public abstract class Tile {
    private Coordinates coords;

    protected Tile(int x, int y) {
        coords = new Coordinates(x, y);
    }

    /**
     * Gets the tile representation of the tile
     * 
     * @return the character representation of the tile for the board
     */
    public abstract String getTileType();

    /**
     * Gets and returns the description of the tile
     * 
     * @return a string that describes the tile
     */
    public abstract String getDescription();

    public Coordinates getNeighbor(Direction direction) {
        return this.coords.nextCoordinates(direction);
    }
}
