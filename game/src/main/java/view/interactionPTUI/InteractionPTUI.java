package view.interactionPTUI;

import java.util.Scanner;

import controller.InventoryManagement.InventoryManager;
import view.GamePTUI;

public class InteractionPTUI implements GamePTUI {
    private InventoryManager manager;
    private Scanner scanner;

    public InteractionPTUI(InventoryManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    public void run() {
        while(true) {
            System.out.println("-----------------------------");
            this.printChoices();
            this.printCommands();

            System.out.print("Your move: ");
            String choice = scanner.nextLine().toLowerCase();

            if(choice.equals("exit")) {
                break;
            } else if(choice.equals("help")) {
                this.printChoices();
                this.printCommands();
            } else {
                System.out.println(this.manager.executeChoice(choice));
            }
        }
    }

    private void printCommands() {
        System.out.println("exit - exits out of the manager");
        System.out.println("help - prints out the help menu");
    }

    private void printChoices() {
        for(String choice : manager.getChoices()) {
            System.out.println(choice);
        }
    }
}
