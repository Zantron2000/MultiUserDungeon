package view;

import view.interactionPTUI.DungeonPTUI;

public class PTUI {
    public static void main(String[] args) {
        GamePTUI game = new DungeonPTUI("data/maps/premade/starter-map.txt");
        game.run();
    }
}

/**
 * PTUIs
 * 
 * 1. Player PTUI - play the game, make moves, see map, open inventory
 * 2. Inventory PTUI - interact with inventory
 *    a. Chest PTUI - interact with inventory and chest contents
 *    b. Corpse PTUI - interact with inventory and corpse contents, deletes self upon empty
 * 3. Exit PTUI - confirm user wants to exit and if so, calls exit's interaction
 * 4. Goal PTUI - confirm user wants to escape dungeon and if so, calls goal's interaction
 * 
 *          -> Corpse / Chest
 *        /
 * Player  --> Inventory
 *        \
 *          -> Exit / Goal
 * 
 */