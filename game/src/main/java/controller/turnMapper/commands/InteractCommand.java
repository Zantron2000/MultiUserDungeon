package controller.turnMapper.commands;

import controller.turnMapper.Command;
import model.character.Character;
import model.map.room.tile.Occupier;

public class InteractCommand implements Command {
    private Character character;
    private Occupier occupier;
    private String action;
    private String result;

    public InteractCommand(Character character, Occupier occupier, String action) {
        this.character = character;
        this.occupier = occupier;
        this.action = action;
        this.result = "This command hasn't been executed yet";
    }

    public void execute() {
        this.result = this.occupier.interact(this.character);
    }

    public String getAction() {
        return this.action;
    }

    public String getResults() {
        return this.result;
    }
}
