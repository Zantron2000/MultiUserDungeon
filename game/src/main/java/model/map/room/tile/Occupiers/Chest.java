package model.map.room.tile.Occupiers;

import controller.turnMapper.Direction;
import controller.turnMapper.TurnMapper;
import model.character.Character;
import model.character.inventory.Item;
import model.map.room.tile.Occupier;
import view.GamePTUI;
import view.interactionPTUI.ChestPTUI;

public class Chest implements Occupier {
    private static char ICON = 'C';

    private Item[] items;
    private int gold;

    public Chest(Item[] items, int gold) {
        this.items = items;
        this.gold = gold;
    }

    public String interact(Character character) {
        character.addGold(this.gold);
        
        GamePTUI ptui = new ChestPTUI(character, this, this.gold);
        this.gold = 0;
        ptui.run();

        return character.toString() + " finished looting the chest";
    }

    public Item takeItem(int pos) {
        if(pos >= 0 && pos < items.length) {
            Item item = items[pos];
            items[pos] = null;

            return item;
        }

        return null;
    }

    public String openInventory() {
        String output = "Chest Contents:";

        for(int i = 0; i < this.items.length; i++) {
            Item item = this.items[i];
            output += "\n\t" + (i + 1) + ". ";
            
            if(item == null) {
                output += "None";
            } else {
                output += item.getDescription();
            }
        }

        return output;
    }

    public void acceptTurnGenerator(TurnMapper generator, Direction direction) {
        generator.generateCommand(this, direction);
    }

    public char getIcon() {
        return Chest.ICON;
    }
}
