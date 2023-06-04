package model.character.inventory.inventories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import model.character.inventory.Inventory;
import model.character.inventory.Item;
import model.character.inventory.items.Armor;
import model.character.inventory.items.Buff;
import model.character.inventory.items.Food;
import model.character.inventory.items.Weapon;
import model.character.stats.StatsManager;
import model.character.stats.stat.BaseStat;
import model.character.stats.statsManager.SimpleStatsManager;

@Testable
public class TestSingleInventory {
    @Test
    public void testAddGold() {
        Inventory inventory = new SingleInventory(100);
        int initGold = inventory.emptyGold();
        
        assertEquals(100, initGold, "The amount of initial gold doesn't match the emptied amount");

        inventory.addGold(100);
        initGold += inventory.emptyGold();

        assertEquals(200, initGold, "The amount of gold added doesn't match the amount emptied");
    }

    @Test
    public void testEmptyGold() {
        Inventory inventory = new SingleInventory(100);
        int totalGold = inventory.emptyGold();

        assertEquals(100, totalGold, "The amount of initial gold doesn't match the emptied amount");

        totalGold += inventory.emptyGold();
        assertEquals(100, totalGold, "The amount of total gold doesn't match the emptied amount");

        inventory.addGold(100);
        inventory.addGold(100);
        totalGold += inventory.emptyGold();
        assertEquals(300, totalGold, "The amount of total gold doesn't match the emptied amount");
    }

    @Test
    public void testAddItem() {
        Inventory inventory = new SingleInventory();
        Item item = new Weapon("Weapon", "Weapon", 30, 5);

        assertFalse(inventory.isFull(), "The inventory should be empty still");
        inventory.addItem(item);
        assertTrue(inventory.isFull(), "The inventory should now be full");
    }

    @Test
    public void testDestroyItem() {
        Item item = new Weapon("Weapon", "Weapon", 30, 5);
        Inventory inventory = new SingleInventory(0);
        inventory.addItem(item);

        assertTrue(inventory.isFull(), "The inventory should currently be full");
        inventory.destroyItem(0, 0);
        assertFalse(inventory.isFull(), "The inventory should now be empty");
    }

    @Test
    public void testUseItem() {
        Inventory inventory = new SingleInventory(0);
        StatsManager manager = new SimpleStatsManager(0, new BaseStat(10, 10));
        Item weapon = new Weapon("Weapon", "Weapon", 30, 10);
        Item armor = new Armor("Armor", "Armor", 30, 10);
        Item food = new Food("Food", "Food", 30, 10);
        Item buff = new Buff("Buff", "Buff", 30, 10, 10, 10);

        inventory.useItem(0, 0, manager);
        assertFalse(inventory.isFull(), "The inventory should still be empty");

        inventory.addItem(weapon);
        inventory.useItem(0, 0, manager);
        assertFalse(inventory.isFull(), "The inventory should be empty after using the weapon");

        inventory.addItem(armor);
        inventory.useItem(0, 0, manager);
        assertFalse(inventory.isFull(), "The inventory should be empty after using the weapon");

        inventory.addItem(food);
        inventory.useItem(0, 0, manager);
        assertTrue(inventory.isFull(), "The inventory should be full after not being able to use the food");

        inventory.destroyItem(0, 0);
        inventory.addItem(buff);
        inventory.useItem(0, 0, manager);
        assertTrue(inventory.isFull(), "The inventory should be full after not being able to use the buff");

        inventory.destroyItem(0, 0);
        inventory.addItem(weapon);
        inventory.useItem(0, 0, manager);
        assertTrue(inventory.isFull(), "The inventory should be full after swapping out the new weapon with the old one");
    }

    @Test
    public void testOpenInventory() {
        Inventory inventory = new SingleInventory(0);
        StatsManager manager = new SimpleStatsManager(0, new BaseStat(10, 10));
        Item weapon = new Weapon("Weapon", "Weapon", 30, 10);
        Item armor = new Armor("Armor", "Armor", 30, 10);
        
        String emptyInventory = "Inventory: 0 gold\n\t1. None";
        String goldInventory = "Inventory: 100 gold\n\t1. None";
        String weaponInventory = "Inventory: 100 gold\n\t1. " + weapon.toString();
        String armorInventory = "Inventory: 100 gold\n\t1. " + armor.toString();

        assertEquals(emptyInventory, inventory.openInventory(), "The string representation of an empty inventory is wrong");

        inventory.addGold(100);
        assertEquals(goldInventory, inventory.openInventory(), "The representation of adding gold to the inventory is wrong");

        inventory.addItem(weapon);
        assertEquals(weaponInventory, inventory.openInventory(), "The representation of adding an item to the inventory is wrong");

        inventory.useItem(0, 0, manager);
        assertEquals(goldInventory, inventory.openInventory(), "The representation of using an item in the inventory is wrong");

        inventory.addItem(armor);
        assertEquals(armorInventory, inventory.openInventory(), "The representation of adding an item to the inventory is wrong");

        inventory.destroyItem(0, 0);
        assertEquals(goldInventory, inventory.openInventory(), "The representation of destorying an item from the inventory is wrong");

        inventory.emptyGold();
        assertEquals(emptyInventory, inventory.openInventory(), "The string representation of emptying gold from the inventory is wrong");
    }

    @Test
    public void testCorpsify() {
        Inventory inventory = new SingleInventory(0);
        StatsManager manager = new SimpleStatsManager(0, new BaseStat(10, 10));
        Item weapon = new Weapon("Weapon", "Weapon", 30, 10);
        Item armor = new Armor("Armor", "Armor", 30, 10);

        Item[] emptyItems = inventory.corpsify();
        inventory.addItem(armor);
        Item[] oneItem = inventory.corpsify();
        inventory.addItem(armor);
        inventory.useItem(0, 0, manager);
        inventory.addItem(weapon);
        Item[] equippedItem = inventory.corpsify();

        assertEquals(0, emptyItems.length, "There should be no items in an empty corpse");
        assertEquals(1, oneItem.length, "The item in the inventory should be in the corpse");
        assertEquals(2, equippedItem.length, "Both equipped and items on person should be in the corpse");

        assertEquals(armor, oneItem[0], "The item taken from the body was the wrong item");
        assertEquals(armor, equippedItem[0], "The equipped item isn't the same item taken off the equipped list");
        assertEquals(weapon, equippedItem[1], "The second item in corpsify should be the item in the inventory");
    }
}
