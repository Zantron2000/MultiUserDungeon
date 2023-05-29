package model.character.stats;

import model.character.inventory.items.StatsRecord;

public interface StatsManager {
    public void takeDamage(int damage);

    public boolean isDead();

    public void addBuff(StatsRecord record);

    public int getDamage();

    public int getArmor();

    public void progressTurn();

    public String toString();
}
