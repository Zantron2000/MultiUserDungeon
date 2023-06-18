package model.map.room.tile;

import controller.turnMapper.Direction;
import controller.turnMapper.TurnElement;
import controller.turnMapper.TurnMapper;
import model.character.Character;
import model.map.Coordinates;

public class Tile implements TurnElement {
    private static char ICON = ' ';

    private Terrain terrain;
    private Occupier occupier;
    private Coordinates coords;

    public Tile(Coordinates coords, Terrain terrain) {
        this.coords = coords;
        this.terrain = terrain;
        this.occupier = null;
    }

    public Tile(Coordinates coords, Occupier occupier) {
        this.coords = coords;
        this.occupier = occupier;
        this.terrain = null;
    }

    public Tile(Coordinates coords) {
        this.coords = coords;
        this.occupier = null;
        this.terrain = null;
    }

    public String occupy(Character character) {
        String terrainResult = "";

        if(!isNull(terrain)) {
            terrainResult = terrain.moveOnto(character);
        }
        this.occupier = character;
        character.moveOnto(this);

        return character.toString() + " moved to the next tile. " + terrainResult;
    }

    public void replaceTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public void removeTerrain() {
        this.terrain = null;
    }

    public void removeOccupier() {
        this.occupier = null;
    }

    public Coordinates getCoordinates() {
        return this.coords;
    }

    private boolean isNull(Occupier occupier) {
        return occupier == null;
    }

    public void acceptTurnGenerator(TurnMapper generator, Direction direction) {
        if(direction == Direction.CURRENT) {
            if(!this.isNull(this.terrain)) {
                this.terrain.acceptTurnGenerator(generator, direction);
            }
        } else if(!this.isNull(this.occupier)) {
            this.occupier.acceptTurnGenerator(generator, direction);
        } else if(!this.isNull(this.terrain)) {
            this.terrain.acceptTurnGenerator(generator, direction);
            generator.generateCommand(this, direction);
        } else {
            generator.generateCommand(this, direction);
        }
    }

    public char getIcon() {
        if(!this.isNull(occupier)) {
            return this.occupier.getIcon();
        } else if(!this.isNull(terrain)) {
            return this.terrain.getIcon();
        } else {
            return Tile.ICON;
        }
    }
}
