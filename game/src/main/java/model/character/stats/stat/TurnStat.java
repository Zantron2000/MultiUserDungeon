package model.character.stats.stat;

import model.character.stats.Stat;

public class TurnStat implements Stat {
    private int attack;
    private int defense;
    private int turns;
    private Stat nextStat;

    public TurnStat(int attack, int defense, int turns, Stat nextStat) {
        this.attack = attack;
        this.defense = defense;
        this.turns = turns;
        this.nextStat = nextStat;
    }

    public int getAttack() {
        return this.attack + this.nextStat.getAttack();
    }

    public int getDefense() {
        return this.defense + this.nextStat.getDefense();
    }

    public Stat progressTurn() {
        this.turns--;

        if(this.turns == 0) {
            return this.nextStat.progressTurn();
        } else {
            this.nextStat = this.nextStat.progressTurn();
            return this;
        }
    }
}
