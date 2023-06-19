package controller.InventoryManagement.Managers;

import java.util.ArrayList;

import controller.InventoryManagement.InventoryManager;
import model.character.inventory.Item;
import model.map.room.tile.Occupiers.Chest;
import model.character.Character;

public class ChestManager implements InventoryManager {
    private Character player;
    private Chest chest;

    public ChestManager(Character player, Chest chest) {
        this.player = player;
        this.chest = chest;
    }

    public ArrayList<String> getChoices() {
        ArrayList<String> choices = new ArrayList<>();

        choices.add("take item# - takes an item from the chest");
        choices.add("use bag# item# - uses an item in the inventory");
        choices.add("destroy bag# item# - destroys an item in the inventory");
        choices.add("open (inventory/chest) - view inventory and che contents");

        return choices;
    }

    public String executeChoice(String choice) {
        String[] data = choice.split(" ");

        try {
            if(data[0].equals("take")) {
                return this.takeItem(Integer.parseInt(data[1]));
            } else if(data[0].equals("use")) {
                this.useItem(Integer.parseInt(data[1]), Integer.parseInt(data[2]));

                return "Used item";
            } else if(data[0].equals("destroy")) {
                this.destroyItem(Integer.parseInt(data[1]), Integer.parseInt(data[2]));

                return "Destroyed item";
            } else if(data[0].equals("open")) {
                return this.openInventory(data[1].toLowerCase());
            } else {
                return "Invalid move";
            }
        } catch(ArrayIndexOutOfBoundsException error) {
            return "Too few arguments provided";
        } catch(NumberFormatException error) {
            return "Didn't provide a number";
        }
    }

    public ArrayList<String> openInventories() {
        ArrayList<String> inventories = new ArrayList<>();

        inventories.add(this.chest.openInventory());
        inventories.add(this.player.openInventory());

        return inventories;
    }

    public String interact() {
        return this.chest.interact(player);
    }

    private String openInventory(String owner) {
        if(owner.equals("inventory")) {
            return this.player.openInventory();
        } else if(owner.equals("chest")) {
            return this.chest.openInventory();
        } else {
            return "Invalid owner given";
        }
    }

    private String takeItem(int itemNumber) {
        itemNumber--;
        Item item = this.chest.takeItem(itemNumber);

        if(item == null) {
            return "Item wasn't found";
        } else if(!this.player.addItem(item)) {
            return "Player inventory is full";
        } else {
            return "Item taken";
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
}
