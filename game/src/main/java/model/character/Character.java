package model.character;

import controller.gameController.TurnObserver;
import controller.turnMapper.Direction;
import controller.turnMapper.TurnMapper;
import model.character.inventory.Inventory;
import model.character.inventory.Item;
import model.character.stats.StatsManager;
import model.map.Coordinates;
import model.map.room.tile.Occupier;
import model.map.room.tile.Terrain;
import model.map.room.tile.Tile;
import model.map.room.tile.Occupiers.Corpse;

public abstract class Character implements Occupier, TurnObserver {
    private Inventory inventory;
    private StatsManager manager;
    private Tile tile;
    private String name;
    private String description;

    protected Character(Inventory inventory, StatsManager manager, Tile tile, String name, String description) {
        this.inventory = inventory;
        this.manager = manager;
        this.name = name;
        this.description = description;
    }

    public void processTurn() {
        this.manager.progressTurn();
    }

    public int getDamage() {
        return this.manager.getDamage();
    }

    public int getArmor() {
        return this.manager.getArmor();
    }

    public void takeDamage(int damage) {
        int totalDamage = damage - this.getArmor();

        if(totalDamage <= 0) {
            totalDamage = 1;
        }

        this.manager.takeDamage(damage);
    }

    public boolean isDead() {
        return this.manager.isDead();
    }

    public boolean addItem(Item item) {
        if(this.inventory.isFull()) {
            return false;
        } else {
            this.inventory.addItem(item);
            return true;
        }
    }

    public void destroyItem(int bag, int pos) {
        this.inventory.destroyItem(bag, pos);
    }

    public void addGold(int gold) {
        this.inventory.addGold(gold);
    }

    public void useItem(int bag, int pos) {
        this.inventory.useItem(bag, pos, this.manager);
    }

    public String openInventory() {
        return this.inventory.openInventory();
    }

    public void moveOnto(Tile tile) {
        this.tile = tile;
    }
    
    public Coordinates getCoordinates() {
        return this.tile.getCoordinates();
    }

    public void interact(Character character) {
        int damage = character.getDamage();
        this.takeDamage(damage);

        if(this.isDead()) {
            this.tile.removeOccupier();
            Item[] items = this.inventory.corpsify();

            if(items.length > 0) {
                Terrain corpse = new Corpse(items, this.tile, this.inventory.emptyGold());

                this.tile.replaceTerrain(corpse);
            }
        }
    }

    public void acceptTurnGenerator(TurnMapper generator, Direction direction) {
        generator.generateCommand(this, direction);
    }

    public String toString() {
        return this.name + " - " + this.description + this.manager.toString();
    }
}
