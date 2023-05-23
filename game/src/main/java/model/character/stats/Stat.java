package model.character.stats;

public interface Stat {
    public int getAttack();

    public int getDefense();

    public Stat progressTurn();
}
