package model.map.room.tile.Occupiers;

import controller.turnMapper.Direction;
import controller.turnMapper.TurnMapper;
import model.character.Character;
import model.character.inventory.Item;
import model.map.room.tile.Terrain;
import model.map.room.tile.Tile;
import view.GamePTUI;
import view.interactionPTUI.CorpsePTUI;

public class Corpse implements Terrain {
    private static char ICON = 'D';

    private Item[] items;
    private Tile tile;
    private int gold;

    public Corpse(Item[] items, Tile tile, int gold) {
        this.items = items;
        this.tile = tile;
        this.gold = gold;
    }

    public String interact(Character character) {
        character.addGold(this.gold);
        
        GamePTUI ptui = new CorpsePTUI(character, this, this.gold);
        this.gold = 0;
        ptui.run();

        if(this.isEmpty()) {
            this.tile.removeTerrain();

            return "The corpse reduced to rubble";
        }

        return "Finished looting the corpse";
    }

    public String moveOnto(Character character) {
        return "Stepped onto a rotting corpse";
    }

    public String openInventory() {
        String output = "Corpse Contents:";

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

    public Item takeItem(int pos) {
        if(pos >= 0 && pos < items.length) {
            Item item = items[pos];
            items[pos] = null;

            return item;
        }

        return null;
    }

    public void acceptTurnGenerator(TurnMapper generator, Direction direction) {
        if(!isEmpty()) {
            generator.generateCommand(this, direction);
        } else {
            this.tile.removeTerrain();
        }
    }

    private boolean isEmpty() {
        for(int i = 0; i < this.items.length; i++) {
            if(this.items[i] != null) {
                return false;
            }
        }

        return this.gold == 0;
    }

    public char getIcon() {
        return Corpse.ICON;
    }
}
