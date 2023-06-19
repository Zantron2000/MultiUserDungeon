package controller.InventoryManagement;

import model.character.characters.PlayerCharacter;

public class InventoryController {
    private PlayerCharacter player;

    public InventoryController(PlayerCharacter player) {
        this.player = player;
    }

    public void useItem(int bagNumber, int itemNumber) {
        bagNumber--;
        itemNumber--;
        this.player.useItem(bagNumber, itemNumber);
    }

    public void destroyItem(int bagNumber, int itemNumber) {
        bagNumber--;
        itemNumber--;
        this.player.destroyItem(bagNumber, itemNumber);
    }

    public String openCharacterInventory() {
        return this.player.openInventory();
    }
}
