package view.interactionPTUI;

import model.map.room.tile.Occupiers.Corpse;
import view.GamePTUI;

import java.util.Scanner;

import model.character.Character;
import model.character.inventory.Item;

public class CorpsePTUI implements GamePTUI {
    private Character player;
    private Corpse corpse;

    public CorpsePTUI(model.character.Character player, Corpse corpse, int gold) {
        this.player = player;
        this.corpse = corpse;
        System.out.println(gold + " gold peices were taken from the corpse");
    }

    private void printHelp() {
        System.out.println("Commands:");
        System.out.println("take item# - takes an item from the corpse");
        System.out.println("use bag# item# - uses an item in the inventory");
        System.out.println("destroy bag# item# - destroys an item in the inventory");
        System.out.println("open - view inventory and corpse contents");
        System.out.println("exit - exits out of the corpse");
        System.out.println("help - prints out the help menu");
    }

    private void takeItem(int itemNumber) {
        itemNumber--;
        Item item = this.corpse.takeItem(itemNumber);

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
        System.out.println(this.corpse.openInventory());
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
                    System.out.println(this.corpse.openInventory());
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
