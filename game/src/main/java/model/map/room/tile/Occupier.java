package model.map.room.tile;

import controller.turnMapper.TurnElement;
import model.character.Character;

public interface Occupier extends TurnElement {
    public String interact(Character character);

    public char getIcon();
}
