package view.interactionPTUI;

import java.util.Scanner;

import controller.gameController.Game;
import view.GamePTUI;

public class DungeonPTUI implements GamePTUI {
    private Game game;

    public DungeonPTUI(String map) {
        
        this.game = new Game(map, "name", "description");
    }

    public void run() {
        System.out.println("Welcome to the jungle");
        String input = "";
        Scanner scanner = new Scanner(System.in);
        while(!input.equals("exit")) {
            System.out.println("-----------------------------");
            System.out.println(this.game.getRoomLayout());

            for(String move : this.game.getPlayerMoves()) {
                System.out.println(move);
            }
            System.out.println("open - open inventory");

            System.out.print("Your turn: ");
            input = scanner.nextLine().trim().toLowerCase();

            this.game.executeMove(input);
        }

        scanner.close();
    }
}
