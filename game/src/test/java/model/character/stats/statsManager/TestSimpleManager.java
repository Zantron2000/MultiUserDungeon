package model.character.stats.statsManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import model.character.inventory.Item;
import model.character.inventory.items.Armor;
import model.character.inventory.items.Buff;
import model.character.inventory.items.Food;
import model.character.inventory.items.StatsRecord;
import model.character.inventory.items.Weapon;
import model.character.stats.Stat;
import model.character.stats.StatsManager;
import model.character.stats.stat.BaseStat;

@Testable
public class TestSimpleManager {
    @Test
    public void testTakeDamage() {
        Stat base = new BaseStat(10, 10);

        StatsManager manager = new SimpleStatsManager(10, base);

        manager.takeDamage(5);
        assertFalse(manager.isDead(), "The manager shouldn't be dead yet");
        
        manager.takeDamage(5);
        assertTrue(manager.isDead(), "The manager should be dead");
    }

    @Test
    public void testIsDead() {
        Stat base = new BaseStat(10, 10);

        StatsManager manager = new SimpleStatsManager(10, base);

        manager.takeDamage(0);
        assertFalse(manager.isDead(), "The manager shouldn't be dead yet");
        
        manager.takeDamage(5000);
        assertTrue(manager.isDead(), "The manager should be dead");
    }

    @Test
    public void testAddBuff() {
        Stat base = new BaseStat(10, 10);
        StatsManager manager = new SimpleStatsManager(10, base);

        Item weapon = new Weapon("Weapon", "Weapon", 30, 10);
        Item armor = new Armor("Armor", "Armor", 30, 10);
        Item food = new Food("Food", "Food", 30, 10);
        Item buff = new Buff("Buff", "Buff", 30, 10, 10, 10);
        Item lesserFood = new Food("Food", "Food", 30, 5);

        StatsRecord recordWeapon = weapon.createRecord();
        StatsRecord recordArmor = armor.createRecord();
        StatsRecord recordFood = food.createRecord();
        StatsRecord recordBuff = buff.createRecord();
        StatsRecord recordLesserFood = lesserFood.createRecord();

        manager.takeDamage(9);
        manager.addBuff(recordWeapon);
        manager.addBuff(recordArmor);
        manager.addBuff(recordFood);
        manager.addBuff(recordBuff);
        manager.takeDamage(9);
        
        assertFalse(manager.isDead(), "The food record should've healed the manager and saved them from the damage");
        assertEquals(20, manager.getDamage(), "The damage should increase with the equipped weapon");
        assertEquals(20, manager.getArmor(), "The defense should increase with the equipped armor");

        manager.takeDamage(1);
        assertTrue(manager.isDead(), "The food should've only restored to the manager's max health");

        manager.addBuff(recordLesserFood);
        manager.takeDamage(4);
        assertFalse(manager.isDead(), "The food record should've healed the manager and saved them from the damage");
        manager.takeDamage(1);
        assertTrue(manager.isDead(), "The food should've only restored a limited amount of health");
    }

    @Test
    public void testProgressTurn() {
        Stat base = new BaseStat(10, 10);
        StatsManager manager = new SimpleStatsManager(10, base);

        for(int i = 0; i < 100; i++) {
            manager.progressTurn();
        }

        assertEquals(10, manager.getDamage(), "The manager's attack shouldn't of changed");
        assertEquals(10, manager.getArmor(), "The manager's defense shouldn't of changed");
    }

    @Test
    public void testGetDamage() {
        Stat base = new BaseStat(10, 10);
        StatsManager manager = new SimpleStatsManager(10, base);
        Item weapon = new Weapon("Weapon", "Weapon", 30, 10);
        Item strongerWeapon = new Weapon("Weapon", "Weapon", 30, 15);
        StatsRecord recordWeapon = weapon.createRecord();
        StatsRecord recordStrongerWeapon = strongerWeapon.createRecord();

        assertEquals(10, manager.getDamage(), "The manager's attack shouldn't of changed from the base stats");

        manager.addBuff(recordWeapon);
        assertEquals(20, manager.getDamage(), "The manager's attack should increase with the equipped weapon");

        manager.addBuff(recordStrongerWeapon);
        assertEquals(25, manager.getDamage(), "The manager's attack should increase with the stronger equipped weapon");
    }

    @Test
    public void testGetArmor() {
        Stat base = new BaseStat(10, 10);
        StatsManager manager = new SimpleStatsManager(10, base);
        Item armor = new Armor("Armor", "Armor", 30, 10);
        Item strongerArmor = new Armor("Armor", "Armor", 30, 15);
        StatsRecord recordArmor = armor.createRecord();
        StatsRecord recordStrongerArmor = strongerArmor.createRecord();

        assertEquals(10, manager.getArmor(), "The manager's defense shouldn't of changed from the base stats");

        manager.addBuff(recordArmor);
        assertEquals(20, manager.getArmor(), "The manager's defense should increase with the equipped armor");

        manager.addBuff(recordStrongerArmor);
        assertEquals(25, manager.getArmor(), "The manager's defense should increase with the stronger equipped armor");
    }

    @Test
    public void testToString() {
        Stat base = new BaseStat(10, 10);
        StatsManager manager = new SimpleStatsManager(10, base);
        
        Item weapon = new Weapon("Weapon", "Weapon", 30, 10);
        Item armor = new Armor("Armor", "Armor", 30, 10);
        StatsRecord weaponRecord = weapon.createRecord();
        StatsRecord armorRecord = armor.createRecord();

        String baseManager = "10/10 health, 10 attack, 10 defense";
        String weaponManager = "10/10 health, 20 attack, 10 defense";
        String armorManager = "10/10 health, 20 attack, 20 defense";
        String hurtManager = "5/10 health, 20 attack, 20 defense";

        assertEquals(baseManager, manager.toString(), "The toString value for a manager with no buffs is incorrect");

        manager.addBuff(weaponRecord);
        assertEquals(weaponManager, manager.toString(), "The toString value for a manager with a weapon is incorrect");
    
        manager.addBuff(armorRecord);
        assertEquals(armorManager, manager.toString(), "The toString value for a manager with armor is incorrect");

        manager.takeDamage(5);
        assertEquals(hurtManager, manager.toString(), "The toString value for a manager who took damage is incorrect");
    }
}
