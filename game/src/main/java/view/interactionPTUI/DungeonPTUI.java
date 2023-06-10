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
            for(String move : this.game.getPlayerMoves()) {
                System.out.println(move);
            }

            input = scanner.nextLine().trim().toLowerCase();

            this.game.executeMove(input);
        }

        scanner.close();
    }
}
