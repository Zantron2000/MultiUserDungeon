package model.attribute;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import model.inventory.Item;
import model.inventory.concrete_items.Armor;
import model.inventory.concrete_items.Bag;
import model.inventory.concrete_items.Buff;
import model.inventory.concrete_items.Food;
import model.inventory.concrete_items.Weapon;

@Testable
public class TestStats {
    @Test
    public void testStats() {
        Item item = new Buff("Buff", 10, 10, 10, 0);
        Stat stat = new Stat(item.getBuffs());

        assertEquals(10, stat.getAttack());
        assertEquals(10, stat.getDefense());

        for(int i = 0; i < 9; i++) {
            assertFalse(stat.advanceTurn());
        }

        assertTrue(stat.advanceTurn());
    }

    @Test
    public void testNegativeStats() {
        Stat stat = new Stat(10, 10, -1);

        assertEquals(10, stat.getAttack());
        assertEquals(10, stat.getDefense());
        for(int i = 0; i < 9; i++) {
            assertFalse(stat.advanceTurn());        
        }
    }

    @Test
    public void testStatManager() {
        StatManager manager = new StatManager(10, 10, 10);

        assertEquals(10, manager.attack());
        assertFalse(manager.takeDamage(19));
        assertTrue(manager.takeDamage(1));

        for(int i = 0; i < 10; i++) {
            manager.advanceTurn();
        }

        assertEquals(10, manager.attack());
    }

    @Test
    public void testManageArmor() {
        Item armor = new Armor("Armor", 0, 1);
        StatManager manager = new StatManager(10, 10, 10);

        armor.applyBuff(manager);

        assertFalse(manager.takeDamage(20));
        assertTrue(manager.takeDamage(1));
        assertEquals(10, manager.attack());
    }

    @Test
    public void testManageWeapon() {
        Item weapon = new Weapon("Weapon", 0, 1);
        StatManager manager = new StatManager(10, 10, 10);

        weapon.applyBuff(manager);

        assertFalse(manager.takeDamage(19));
        assertTrue(manager.takeDamage(1));
        assertEquals(11, manager.attack());
    }

    @Test
    public void testManageBuff() {
        Item buff = new Buff("Buff", 10, 1, 1, 0);
        StatManager manager = new StatManager(10, 10, 10);

        buff.applyBuff(manager);

        assertFalse(manager.takeDamage(20));
        assertTrue(manager.takeDamage(1));
        assertEquals(11, manager.attack());
    }

    @Test
    public void testManageBag() {
        Item bag = new Bag("Bag", 10, 1);
        StatManager manager = new StatManager(10, 10, 10);

        bag.applyBuff(manager);

        assertFalse(manager.takeDamage(19));
        assertTrue(manager.takeDamage(1));
        assertEquals(10, manager.attack());
    }

    @Test
    public void testManageFood() {
        Item food = new Food("Food", 10, 1);
        StatManager manager = new StatManager(10, 10, 10);

        assertFalse(manager.takeDamage(19));

        food.applyBuff(manager);
        assertFalse(manager.takeDamage(1));

        assertTrue(manager.takeDamage(1));
        assertEquals(10, manager.attack());
    }

    @Test
    public void testManageFoodFull() {
        Item food = new Food("Food", 10, 100);
        StatManager manager = new StatManager(10, 10, 10);
        food.applyBuff(manager);

        assertFalse(manager.takeDamage(19));
        assertTrue(manager.takeDamage(1));
        assertEquals(10, manager.attack());
    }

    @Test
    public void testManageTurn() {
        Item buff = new Buff("Buff", 10, 1, 0, 0);
        StatManager manager = new StatManager(10, 10, 10);
        
        buff.applyBuff(manager);

        for(int i = 0; i < 9; i++) {
            manager.advanceTurn();
            assertEquals(11, manager.attack());
        }

        manager.advanceTurn();
        assertEquals(10, manager.attack());
    }

    @Test
    public void testManageTurnMultiple() {
        Item buff = new Buff("Buff", 10, 100, 0, 0);
        Item buff2 = new Buff("Buff", 10, 100, 0, 0);
        Item buff3 = new Buff("Buff", 10, 100, 0, 0);
        Item buff4 = new Buff("Buff", 10, 100, 0, 0);
        StatManager manager = new StatManager(10, 10, 10);
        
        buff.applyBuff(manager);
        buff2.applyBuff(manager);
        buff3.applyBuff(manager);
        buff4.applyBuff(manager);

        for(int i = 0; i < 10; i++) {
            manager.advanceTurn();
        }

        assertFalse(manager.takeDamage(19));
        assertTrue(manager.takeDamage(1));
        assertEquals(10, manager.attack());
    }

    @Test
    public void testManageArmorReequip() {
        Item armor = new Armor("Armor", 0, 1);
        Item armor2 = new Armor("Armor", 10, 10000);
        StatManager manager = new StatManager(10, 10, 10);

        armor2.applyBuff(manager);
        armor.applyBuff(manager);

        assertFalse(manager.takeDamage(20));
        assertTrue(manager.takeDamage(1));
        assertEquals(10, manager.attack());
    }

    @Test
    public void testManageWeaponReequip() {
        Item weapon = new Weapon("Weapon", 0, 1);
        Item weapon2 = new Weapon("Weapon", 10, 100);
        StatManager manager = new StatManager(10, 10, 10);

        weapon2.applyBuff(manager);
        weapon.applyBuff(manager);

        assertFalse(manager.takeDamage(19));
        assertTrue(manager.takeDamage(1));
        assertEquals(11, manager.attack());
    }
}
