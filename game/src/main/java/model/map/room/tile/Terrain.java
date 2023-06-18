package model.map.room.tile;

import model.character.Character;

public interface Terrain extends Occupier {
    public String moveOnto(Character character);
}
