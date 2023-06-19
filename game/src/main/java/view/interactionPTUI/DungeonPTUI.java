package view.interactionPTUI;

import java.util.NoSuchElementException;
import java.util.Scanner;

import controller.InventoryManagement.InventoryManager;
import controller.gameController.Game;
import view.GamePTUI;

public class DungeonPTUI implements GamePTUI {
    private Game game;
    private Scanner scanner;

    public DungeonPTUI(String map) {
        this.scanner = new Scanner(System.in);
        this.game = new Game(map, "name", "description");
    }

    public void run() {
        System.out.println("Welcome to the jungle");
        String input = "";
        while(!input.equals("exit")) {
            try {
                System.out.println("-----------------------------");
                System.out.println(this.game.getRoomLayout());

                for(String move : this.game.getPlayerMoves(this)) {
                    System.out.println(move);
                }
                System.out.println("open - open inventory");

                System.out.print("Your turn: ");
                input = this.scanner.nextLine().trim().toLowerCase();

                processCommand(input);
            } catch(NoSuchElementException error) {
                this.scanner.close();
                this.scanner = new Scanner(System.in);
            }
        }

        this.scanner.close();
    }

    public void startInteraction(InventoryManager manager) {
        GamePTUI ptui = new InteractionPTUI(manager, this.scanner);
        ptui.run();
    }

    private void processCommand(String input) {
        if(input.equals("open")) {
            GamePTUI ptui = new InventoryPTUI(this.game.manageInventory(), scanner);
            ptui.run();
        } else {
            System.out.println(this.game.executeMove(input));
        }
    }
}
