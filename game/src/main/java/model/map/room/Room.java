package model.map.room;

import model.map.room.tile.Tile;

public class Room {
    private int width;
    private int height;
    private Tile[][] layout;

    public Room(Tile[][] layout) {
        this.height = layout.length;
        this.width = layout[0].length;
    }
}
