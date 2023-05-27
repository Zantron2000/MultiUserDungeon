package model.map.room.tile;

import controller.turnMapper.Direction;
import controller.turnMapper.TurnElement;
import controller.turnMapper.TurnGenerator;
import model.character.Character;
import model.map.Coordinates;

public class Tile implements TurnElement {
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

    public void occupy(Character character) {
        if(!isNull(terrain)) {
            terrain.moveOnto(character);
        }

        character.moveOnto(this);
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

    public void acceptTurnGenerator(TurnGenerator generator, Direction direction) {
        if(this.occupier != null) {
            this.occupier.acceptTurnGenerator(generator, direction);
        } else if(this.terrain != null) {
            this.terrain.acceptTurnGenerator(generator, direction);
            generator.generateCommand(this, direction);
        } else {
            generator.generateCommand(this, direction);
        }
    }
}
