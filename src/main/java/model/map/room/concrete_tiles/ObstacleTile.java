package model.map.room.concrete_tiles;

import model.map.room.Tile;

public class ObstacleTile extends Tile {
    private final static String TILE_TYPE = "O";
    
    private final String description;
    
    public ObstacleTile(int x, int y, String description) {
        super(x, y);
        this.description = description;
    }

    public String getTileType() {
        return ObstacleTile.TILE_TYPE;
    }

    public String getDescription() {
        return this.description;
    }
}
