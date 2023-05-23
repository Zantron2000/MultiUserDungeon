package model.character.inventory.items;

public class StatsRecord {
    private int attack;
    private int defense;
    private int health;
    private int turns;
    private boolean equippable;
    private ItemType type;

    StatsRecord(int attack, int defense, int health, int turns, boolean equippable, ItemType type) {
        this.attack = attack;
        this.defense = defense;
        this.health = health;
        this.turns = turns;
        this.equippable = equippable;
        this.type = type;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public int getHealth() {
        return this.health;
    }

    public int getTurns() {
        return this.turns;
    }

    public boolean isEquippable() {
        return this.equippable;
    }

    public ItemType getType() {
        return this.type;
    }
}
