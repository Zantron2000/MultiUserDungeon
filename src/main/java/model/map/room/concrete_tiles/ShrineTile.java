package model.map.room.concrete_tiles;

import model.map.room.Tile;

public class ShrineTile extends Tile {
    private final static String DESCRIPTION = "A shrine surrounded by cold air and a strange blue light"; // The typical description for a chest
    private final static String TILE_TYPE = "S"; // The tile representation of a chest tile

    public ShrineTile(int x, int y) {
        super(x, y);
    }

    public void pray() {
        System.out.println("Prayer");
    }

    /**
     * The getTileType function from the Tile abstract class,
     */
    @Override
    public String getTileType() {
        return ShrineTile.TILE_TYPE;
    }

    /**
     * The getDescription function from the Tile abstract class
     */
    @Override
    public String getDescription() {
        return ShrineTile.DESCRIPTION;
    }
}
