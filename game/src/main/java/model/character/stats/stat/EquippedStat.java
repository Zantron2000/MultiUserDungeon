package model.character.stats.stat;

import model.character.stats.Stat;

public class EquippedStat implements Stat {
    private int attack;
    private int defense;

    EquippedStat(int attack, int defense) {
        this.attack = attack;
        this.defense = defense;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public Stat progressTurn() {
        return this;
    }
}
