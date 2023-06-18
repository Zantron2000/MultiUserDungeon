package controller.turnMapper.commands;

import controller.turnMapper.Command;
import model.character.Character;
import model.map.room.tile.Tile;

public class MoveCommand implements Command {
    private Character character;
    private Tile tile;
    private String action;
    private String result;

    public MoveCommand(Character character, Tile tile, String action) {
        this.character = character;
        this.tile = tile;
        this.action = action;
        this.result = "This command hasn't been executed yet";
    }

    public void execute() {
        this.result = this.tile.occupy(character);
    }

    public String getAction() {
        return this.action;
    }

    public String getResults() {
        return this.result;
    }
}
