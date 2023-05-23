package model.character.stats.statsManager;

import java.util.HashMap;

import model.character.inventory.items.ItemType;
import model.character.inventory.items.StatsRecord;
import model.character.stats.Stat;
import model.character.stats.StatsManager;
import model.character.stats.stat.EquippedStat;

public class SimpleStatsManager implements StatsManager {
    private HashMap<ItemType, Stat> equipped;
    private int maxHealth;
    private int health;
    private Stat stats;

    public SimpleStatsManager(int maxHealth, Stat baseStat) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.equipped = new HashMap<>();
        this.stats = baseStat;
    }

    public boolean isDead() {
        return this.health <= 0;
    }

    public int getDamage() {
        int damage = 0;

        for(Stat stat : this.equipped.values()) {
            damage += stat.getAttack();
        }

        damage += this.stats.getAttack();

        return damage;
    }

    public void takeDamage(int damage) {
        int armor = 0;

        for(Stat stat : this.equipped.values()) {
            armor += stat.getDefense();
        }

        armor += this.stats.getDefense();

        damage = damage - armor > 0 ? damage - armor : 1;
        
        this.health -= damage;
    }

    public void progressTurn() {
        this.stats = this.stats.progressTurn();
    }

    public void addBuff(StatsRecord record) {
        if(record == null) {
            return;
        }

        this.health = this.health + record.getHealth() > maxHealth ? maxHealth : this.health + record.getHealth();

        if(record.isEquippable()) {
            Stat stat = new EquippedStat(record.getAttack(), record.getDefense());
            equipped.put(record.getType(), stat);
        }
    }
}
