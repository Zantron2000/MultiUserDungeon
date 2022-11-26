package model.map.concrete_tiles;

import model.map.Direction;

public class ExitTile extends MovableTile {
    private static String DESCRIPTION = "In front of you lies an exit. Do you want to move to the next room?";
    private static String TILE_TYPE = "E";

    private Direction direction;

    public ExitTile(int x, int y, Direction direction){
        super(x, y);
        this.direction = direction;
    }

    public Direction getDirection() {
        return this.direction;
    }

    /**
     * The getTileType function from the Tile abstract class,
     * overrides the representation of the chest space if a character is on it
     */
    @Override
    public String getTileType() {
        String characterString = super.getTileType();
    
        if(characterString.length() == 0) {
            return ExitTile.TILE_TYPE;
        } else {
            return characterString;
        }
    }

    /**
     * The getDescription function from the Tile abstract class
     */
    @Override
    public String getDescription() {
        return ExitTile.DESCRIPTION + super.getDescription();
    }
}
