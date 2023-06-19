package controller.turnMapper.commands;

import controller.InventoryManagement.InventoryManager;
import controller.turnMapper.Command;
import view.interactionPTUI.DungeonPTUI;

public class PtuiCommand implements Command {
    private InventoryManager manager;
    private DungeonPTUI ptui;
    private String action;
    private String results;

    public PtuiCommand(InventoryManager manager, DungeonPTUI ptui, String action) {
        this.manager = manager;
        this.ptui = ptui;
        this.action = action;
        this.results = "This command hasn't been executed yet";
    }

    public String getAction() {
        return this.action;
    }

    public void execute() {
        this.ptui.startInteraction(manager);

        this.results = this.manager.interact();
    }

    public String getResults() {
        return this.results;
    }
}
