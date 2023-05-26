package model.map.room.tile;

import model.character.Character;
import model.map.Coordinates;

public class Tile {
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

    public void interact(Character character) {
        if(!isNull(occupier)) {
            this.occupier.interact(character);
        } else {
            this.terrain.interact(character);
        }
    }

    public void replaceTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public void removeTerrain() {
        this.terrain = null;
    }

    public Coordinates getCoordinates() {
        return this.getCoordinates();
    }

    private boolean isNull(Occupier occupier) {
        return occupier == null;
    }
}