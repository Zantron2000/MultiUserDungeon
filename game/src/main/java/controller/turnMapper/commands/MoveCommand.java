package controller.turnMapper.commands;

import controller.turnMapper.Command;
import model.character.Character;
import model.map.room.tile.Tile;

public class MoveCommand implements Command {
    private Character character;
    private Tile tile;
    private String description;

    public MoveCommand(Character character, Tile tile, String direction) {
        this.character = character;
        this.tile = tile;
        this.description = "Move to the " + direction;
    }

    public void execute() {
        this.tile.occupy(character);
    }

    public String getDescription() {
        return this.description;
    }
}
