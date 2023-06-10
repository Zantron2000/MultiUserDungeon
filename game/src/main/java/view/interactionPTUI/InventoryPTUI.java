package view.interactionPTUI;

import view.GamePTUI;

import java.util.Scanner;

import model.character.Character;

public class InventoryPTUI implements GamePTUI {
    private Character player;

    public InventoryPTUI(Character player) {
        this.player = player;
    }

    private void printHelp() {
        System.out.println("Commands:");
        System.out.println("use bag# item# - uses an item in the inventory");
        System.out.println("destroy bag# item# - destroys an item in the inventory");
        System.out.println("open - view inventory and corpse contents");
        System.out.println("exit - exits out of the corpse");
        System.out.println("help - prints out the help menu");
    }

    private void useItem(int bagNumber, int itemNumber) {
        bagNumber--;
        itemNumber--;
        this.player.useItem(bagNumber, itemNumber);
    }

    private void destroyItem(int bagNumber, int itemNumber) {
        bagNumber--;
        itemNumber--;
        this.player.destroyItem(bagNumber, itemNumber);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String[] data;
        System.out.println(this.player.openInventory());
        
        while(true) {
            data = scanner.nextLine().split(" ");

            try {
                if(data[0].equals("use")) {
                    this.useItem(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
                } else if(data[0].equals("destroy")) {
                    this.destroyItem(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
                } else if(data[0].equals("exit")) {
                    scanner.close();
                    break;
                } else if(data[0].equals("open")) {
                    System.out.println(this.player.openInventory());
                } else {
                    this.printHelp();
                }
            } catch(ArrayIndexOutOfBoundsException error) {
                System.out.println("Too few arguments provided");
                this.printHelp();
            } catch(NumberFormatException error) {
                System.out.println("Didn't provide a number");
                this.printHelp();
            }
        }
    }
}
