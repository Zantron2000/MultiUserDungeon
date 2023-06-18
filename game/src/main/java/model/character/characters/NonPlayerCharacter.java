package model.character.characters;

import model.character.Character;
import model.character.inventory.Inventory;
import model.character.stats.StatsManager;
import model.map.Cycle;
import model.map.TimeObserver;
import model.map.room.tile.Tile;

public abstract class NonPlayerCharacter extends Character implements TimeObserver {
    private static char ICON = 'N';

    private Cycle cycle;
    private boolean boosted;
    private double buff;
    private double debuff;

    public NonPlayerCharacter(Inventory inventory, StatsManager manager, Tile tile, String name, String description, Cycle cycle, double buff, double debuff) {
        super(inventory, manager, tile, name, description);
        this.buff = buff;
        this.debuff = debuff;

        this.cycle = cycle;
    }

    public void changeTime(Cycle time) {
        this.boosted = (this.cycle == time);
    }

    @Override
    public int getDamage() {
        double modifier = boosted ? buff : debuff;

        return (int) (modifier * super.getDamage());
    }

    @Override
    public int getArmor() {
        double modifier = boosted ? buff : debuff;

        return (int) (modifier * super.getArmor());
    }

    public char getIcon() {
        return NonPlayerCharacter.ICON;
    }
}
