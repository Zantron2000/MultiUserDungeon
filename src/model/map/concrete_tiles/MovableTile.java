package model.map.concrete_tiles;

import model.character.Character;
import model.map.Tile;

public class MovableTile extends Tile {
    protected Character occupier; // The character currently occupying the tile

    /**
     * The MovableTile constructor, sets the tile as unoccupied
     */
    public MovableTile(int x, int y) {
        super(x, y);
    }

    /**
     * The MovableTile constructor, sets the tile as occupied
     * 
     * @param character the character currently occupying the space
     */
    public MovableTile(int x, int y, Character character) {
        super(x, y);
        this.occupier = character;
    }

    /**
     * Gets and returns the GameCharacter occupying the tile
     * 
     * @return GameCharacter, the character currently occupying the tile
     */
    public Character getOccupier() {
        return this.occupier;
    }

    /**
     * Moves a character off of a tile
     * 
     * @return the character moved off the tile
     */
    public Character moveOff() {
        Character character = this.occupier;
        this.occupier = null;
        return character;
    }

    /**
     * Attempts to move a character onto the tile given that it is unoccupied
     * 
     * @param character the character to move try to move onto the tile
     */
    public void moveOn(Character character) {
        if(this.occupier == null) {
            this.occupier = character;
        }
    }

    /**
     * Checks to see if the tile is currently occupied by a character
     * 
     * @return whether the tile is occupied
     */
    public boolean isOccupied() {
        return this.occupier != null;
    }

    /**
     * The getTileType function from the abstract Tile class, returns the type
     * of character on the string if one occupies the space, otherwise returns nothing
     */
    @Override
    public String getTileType() {
        if(this.isOccupied()) {
            return this.occupier.getRepresentation();
        } else {
            return "";
        }
    }

    /**
     * The getDescription function from the abstract Tile class, 
     * modified depending on whether a character occupies the space
     */
    @Override
    public String getDescription() {
        if(this.isOccupied()) {
            return "Tile occupied by " + this.occupier.toString();
        } else {
            return "A currently unoccupied tile";
        }
    }
}
