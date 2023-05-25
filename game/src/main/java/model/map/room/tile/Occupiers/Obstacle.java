package model.map.room.tile.Occupiers;

import model.character.Character;
import model.map.room.tile.Occupier;

public class Obstacle implements Occupier {
    private String description;

    public Obstacle(String description) {
        this.description = description;
    }

    public void interact(Character character) {
        return;
    }
}
