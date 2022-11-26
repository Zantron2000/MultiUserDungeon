package model.map.concrete_tiles;

import model.map.Tile;

public class ObstacleTile extends Tile {
    private static String TILE_TYPE = "O";
    
    private String description;
    
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
