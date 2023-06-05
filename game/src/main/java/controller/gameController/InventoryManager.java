package controller.gameController;

import model.character.characters.PlayerCharacter;

public class InventoryManager {
    private PlayerCharacter player;

    public InventoryManager(PlayerCharacter player) {
        this.player = player;
    } 

    public void destroyItem(int bag, int pos) {
        this.player.destroyItem(bag, pos);
    }

    public void useItem(int bag, int pos) {
        this.player.useItem(bag, pos);
    }

    
}
