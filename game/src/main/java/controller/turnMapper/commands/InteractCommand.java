package controller.turnMapper.commands;

import controller.turnMapper.Command;
import model.character.Character;
import model.map.room.tile.Occupier;

public class InteractCommand implements Command {
    private Character character;
    private Occupier occupier;
    private String description;

    public InteractCommand(Character character, Occupier occupier, String type, String direction) {
        this.character = character;
        this.occupier = occupier;
        this.description = "Interact with " + type + " to the " + direction;
    }

    public void execute() {
        this.occupier.interact(this.character);
    }

    public String getDescription() {
        return this.description;
    }
}
