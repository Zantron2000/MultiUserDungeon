package model.map.concrete_tiles;

import model.character.Character;

public class EmptyTile extends MovableTile {
    private static String TILE_TYPE = " "; // The representation of an empty tile

    /**
     * The EmptyTile constructor, declares itself as an empty space without
     * a character on it
     */
    public EmptyTile(int x, int y) {
        super(x, y);
    }

    /**
     * The EmptyTile constructor, passing in a GameCharacter to assign
     * to it's spot on the tile
     * 
     * @param character the character to set in the empty tile
     */
    public EmptyTile(int x, int y, Character character) {
        super(x, y, character);
    }

    /**
     * The getTileType function from the Tile abstract class,
     * overrides the representation of the empty space if a character is on it
     */
    @Override
    public String getTileType() {
        String characterString = super.getTileType();
    
        if(characterString.length() == 0) {
            return EmptyTile.TILE_TYPE;
        } else {
            return characterString;
        }
    }
}
