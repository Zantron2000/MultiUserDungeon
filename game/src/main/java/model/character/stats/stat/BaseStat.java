package model.character.stats.stat;

import model.character.stats.Stat;

public class BaseStat implements Stat {
    private int attack;
    private int defense;

    public BaseStat(int attack, int defense) {
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
