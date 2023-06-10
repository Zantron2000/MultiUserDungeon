package view.interactionPTUI;

import java.util.Scanner;

import model.character.Character;
import model.character.inventory.Item;
import model.map.room.tile.Occupiers.Chest;
import view.GamePTUI;

public class ChestPTUI implements GamePTUI {
    private Character player;
    private Chest chest;

    public ChestPTUI(Character player, Chest chest, int gold) {
        this.player = player;
        this.chest = chest;
        System.out.println(gold + " gold peices were taken from the chest");
    }

    private void printHelp() {
        System.out.println("Commands:");
        System.out.println("take item# - takes an item from the chest");
        System.out.println("use bag# item# - uses an item in the inventory");
        System.out.println("destroy bag# item# - destroys an item in the inventory");
        System.out.println("open - view inventory and chest contents");
        System.out.println("exit - exits out of the chest");
        System.out.println("help - prints out the help menu");
    }

    private void takeItem(int itemNumber) {
        itemNumber--;
        Item item = this.chest.takeItem(itemNumber);

        if(item == null) {
            System.out.println("Item wasn't found");
        } else if(this.player.addItem(item)) {
            System.out.println("Player inventory is full");
        } else {
            System.out.println("Item taken");
        }
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
        System.out.println(this.chest.openInventory());
        System.out.println(this.player.openInventory());
        
        while(true) {
            data = scanner.nextLine().split(" ");

            try {
                if(data[0].equals("take")) {
                    this.takeItem(Integer.parseInt(data[1]));
                } else if(data[0].equals("use")) {
                    this.useItem(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
                } else if(data[0].equals("destroy")) {
                    this.destroyItem(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
                } else if(data[0].equals("exit")) {
                    scanner.close();
                    break;
                } else if(data[0].equals("open")) {
                    System.out.println(this.chest.openInventory());
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
