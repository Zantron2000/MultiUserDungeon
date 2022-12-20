package model.map.room.concrete_tiles;

public class GoalTile extends MovableTile {
    private final static String DESCRIPTION = "In front of you lies the doors to the outside, will you choose to leave?";
    private final static String TILE_TYPE = "G";

    public GoalTile(int x, int y){
        super(x, y);
    }

    /**
     * The getTileType function from the Tile abstract class,
     * overrides the representation of the chest space if a character is on it
     */
    @Override
    public String getTileType() {
        String characterString = super.getTileType();
    
        if(characterString.length() == 0) {
            return GoalTile.TILE_TYPE;
        } else {
            return characterString;
        }
    }

    /**
     * The getDescription function from the Tile abstract class
     */
    @Override
    public String getDescription() {
        return GoalTile.DESCRIPTION + super.getDescription();
    }
}
