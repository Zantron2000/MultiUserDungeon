package model.character.inventory.inventories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import model.character.inventory.Inventory;
import model.character.inventory.Item;
import model.character.inventory.items.Armor;
import model.character.inventory.items.Bag;
import model.character.inventory.items.Buff;
import model.character.inventory.items.Food;
import model.character.inventory.items.Weapon;
import model.character.stats.StatsManager;
import model.character.stats.stat.BaseStat;
import model.character.stats.statsManager.ComplexStatsManager;

@Testable
public class TestMultiInventory {
    @Test
    public void testAddGold() {
        Bag bag = new Bag("Bag", "Bag", 30, 5);
        Inventory inventory = new MultiInventory(bag);
        inventory.addGold(100);

        int initGold = inventory.emptyGold();
        
        assertEquals(100, initGold, "The amount of initial gold doesn't match the emptied amount");

        inventory.addGold(100);
        initGold += inventory.emptyGold();

        assertEquals(200, initGold, "The amount of gold added doesn't match the amount emptied");
    }

    @Test
    public void testEmptyGold() {
        Bag bag = new Bag("Bag", "Bag", 30, 5);
        Inventory inventory = new MultiInventory(bag);
        inventory.addGold(100);
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
        Bag bag = new Bag("Bag", "Bag", 30, 5);
        Inventory inventory = new MultiInventory(bag);
        Item weapon = new Weapon("Weapon", "Weapon", 10, 10);
        String output;

        for(int i = 0; i < 5; i++) {
            output = "Inventory: 0 gold\n\t1. " + bag.openInventory() + "\n\t2. None\n\t3. None\n\t4. None\n\t5. None\n\t6. None";
            assertFalse(inventory.isFull(), "The inventory shouldn't be full yet");
            assertEquals(output, inventory.openInventory(), "The string representation of the inventory is wrong");
            inventory.addItem(weapon);
        }

        output = "Inventory: 0 gold\n\t1. " + bag.openInventory() + "\n\t2. None\n\t3. None\n\t4. None\n\t5. None\n\t6. None";
        assertTrue(inventory.isFull(), "The inventory should be full now");
        assertEquals(output, inventory.openInventory(), "The string representation of the inventory is wrong");
    }

    @Test
    public void testUseItemBasics() {
        Bag starterBag = new Bag("Bag", "Bag", 5, 5);
        Inventory inventory = new MultiInventory(starterBag);
        StatsManager manager = new ComplexStatsManager(10, new BaseStat(10, 10));
        Item weapon = new Weapon("Weapon", "Weapon", 30, 10);
        Item armor = new Armor("Armor", "Armor", 30, 10);
        Item food = new Food("Food", "Food", 30, 10);
        Item buff = new Buff("Buff", "Buff", 30, 10, 10, 10);
        Bag bag = new Bag("Bag", "Bag", 5, 5);

        String fullBag;
        String weaponUsed;
        String armorUsed;
        String foodUsed;
        String buffUsed;
        String bagUsed;

        inventory.addItem(weapon);
        inventory.addItem(armor);
        inventory.addItem(food);
        inventory.addItem(buff);
        inventory.addItem(bag);
        
        fullBag = "Inventory: 0 gold\n\t1. " + starterBag.openInventory() + "\n\t2. None\n\t3. None\n\t4. None\n\t5. None\n\t6. None";
        assertEquals(fullBag, inventory.openInventory(), "The inventory should contain all items");

        inventory.useItem(0, 0, manager);
        weaponUsed = "Inventory: 0 gold\n\t1. " + starterBag.openInventory() + "\n\t2. None\n\t3. None\n\t4. None\n\t5. None\n\t6. None";
        assertEquals(weaponUsed, inventory.openInventory(), "The inventory shouldn't contain a weapon");
        assertEquals(20, manager.getDamage(), "The weapon should've boosted the manager's damage");

        inventory.useItem(0, 1, manager);
        armorUsed = "Inventory: 0 gold\n\t1. " + starterBag.openInventory() + "\n\t2. None\n\t3. None\n\t4. None\n\t5. None\n\t6. None";
        assertEquals(armorUsed, inventory.openInventory(), "The inventory shouldn't contain armor");
        assertEquals(20, manager.getArmor(), "The armor should've boosted the manager's defense");
        
        manager.takeDamage(9);
        inventory.useItem(0, 2, manager);
        foodUsed = "Inventory: 0 gold\n\t1. " + starterBag.openInventory() + "\n\t2. None\n\t3. None\n\t4. None\n\t5. None\n\t6. None";
        assertEquals(foodUsed, inventory.openInventory(), "The inventory shouldn't contain food");
        manager.takeDamage(9);
        assertFalse(manager.isDead(), "The food should've healed the manager");
        manager.takeDamage(1);
        assertTrue(manager.isDead(), "The manager should've healed just enough health to die from 10 damage");

        inventory.useItem(0, 3, manager);
        buffUsed = "Inventory: 0 gold\n\t1. " + starterBag.openInventory() + "\n\t2. None\n\t3. None\n\t4. None\n\t5. None\n\t6. None";
        assertEquals(buffUsed, inventory.openInventory(), "The inventory shouldn't contain a buff");
        assertEquals(30, manager.getDamage(), "The buff should've boosted the manager's damage");
        assertEquals(30, manager.getArmor(), "The buff should've boosted the manager's defense");

        inventory.useItem(0, 4, manager);
        bagUsed = "Inventory: 0 gold\n\t1. " + starterBag.openInventory() + "\n\t2. " + bag.openInventory() + "\n\t3. None\n\t4. None\n\t5. None\n\t6. None";
        assertEquals(bagUsed, inventory.openInventory(), "The inventory shouldn't contain a bag");
        assertEquals(30, manager.getDamage(), "The bag shouldn't have boosted the manager's damage");
        assertEquals(30, manager.getArmor(), "The bag shouldn't have boosted the manager's defense");
    }

    @Test
    public void testUseItemReplace() {
        Bag starterBag = new Bag("Bag", "Bag", 5, 2);
        Inventory inventory = new MultiInventory(starterBag);
        StatsManager manager = new ComplexStatsManager(10, new BaseStat(10, 10));
        Item weapon = new Weapon("Weapon", "Weapon", 30, 10);
        Item armor = new Armor("Armor", "Armor", 30, 10);
        Item betterWeapon = new Weapon("Weapon", "Weapon", 30, 20);
        Item betterArmor = new Armor("Armor", "Armor", 30, 20);

        String expectedBag = "Bag 2/2 capacity\n\t\t1. " + weapon.toString() + "\n\t\t2. " + armor.toString();
        String betterItemsUsed = "Inventory: 0 gold\n\t1. " + expectedBag + "\n\t2. None\n\t3. None\n\t4. None\n\t5. None\n\t6. None";
        
        inventory.addItem(weapon);
        inventory.addItem(armor);

        inventory.useItem(0, 0, manager);
        inventory.useItem(0, 1, manager);

        inventory.addItem(betterWeapon);
        inventory.addItem(betterArmor);

        inventory.useItem(0, 0, manager);
        inventory.useItem(0, 1, manager);

        assertEquals(betterItemsUsed, inventory.openInventory(), "The expected string representation of the inventory is off");
        assertEquals(30, manager.getDamage(), "The better weapon should've boosted the manager's damage");
        assertEquals(30, manager.getArmor(), "The better armor should've boosted the manager's defense");
    }

    @Test
    public void testUseItemReplaceBags() {
        Bag starterBag = new Bag("Bag", "Bag", 5, 5);
        Inventory inventory = new MultiInventory(starterBag);
        StatsManager manager = new ComplexStatsManager(10, new BaseStat(10, 10));
        
        Bag smallBag1 = new Bag("Bag", "Bag", 10, 3);
        Bag smallBag2 = new Bag("Bag", "Bag", 10, 3);
        Bag smallBag3 = new Bag("Bag", "Bag", 10, 3);
        Bag smallBag4 = new Bag("Bag", "Bag", 10, 3);
        Bag smallBag5 = new Bag("Bag", "Bag", 10, 3);
        Bag mediumBag = new Bag("Bag", "Bag", 10, 3);

        String baggedBag = "Bag 1/5 capacity\n\t\t1. " + smallBag1.toString() + "\n\t\t2. None\n\t\t3. None\n\t\t4. None\n\t\t5. None";
        String oneBag = "Inventory: 0 gold\n\t1. " + starterBag.openInventory() + "\n\t2. None\n\t3. None\n\t4. None\n\t5. None\n\t6. None";
        String twoBags = "Inventory: 0 gold\n\t1. " + starterBag.openInventory() + "\n\t2. " + smallBag1.openInventory() + "\n\t3. None\n\t4. None\n\t5. None\n\t6. None";
        String threeBags = "Inventory: 0 gold\n\t1. " + starterBag.openInventory() + "\n\t2. " + smallBag1.openInventory() + "\n\t3. " + smallBag1.openInventory() + "\n\t4. None\n\t5. None\n\t6. None";
        String fourBags = "Inventory: 0 gold\n\t1. " + starterBag.openInventory() + "\n\t2. " + smallBag1.openInventory() + "\n\t3. " + smallBag1.openInventory() + "\n\t4. " + smallBag1.openInventory() + "\n\t5. None\n\t6. None";
        String fiveBags = "Inventory: 0 gold\n\t1. " + starterBag.openInventory() + "\n\t2. " + smallBag1.openInventory() + "\n\t3. " + smallBag1.openInventory() + "\n\t4. " + smallBag1.openInventory() + "\n\t5. " + smallBag1.openInventory() + "\n\t6. None";
        String sixBags = "Inventory: 0 gold\n\t1. " + starterBag.openInventory() + "\n\t2. " + smallBag1.openInventory() + "\n\t3. " + smallBag1.openInventory() + "\n\t4. " + smallBag1.openInventory() + "\n\t5. " + smallBag1.openInventory() + "\n\t6. " + smallBag1.openInventory() + "";
        String replaceBags = "Inventory: 0 gold\n\t1. " + baggedBag + "\n\t2. " + mediumBag.openInventory() + "\n\t3. " + smallBag1.openInventory() + "\n\t4. " + smallBag1.openInventory() + "\n\t5. " + smallBag1.openInventory() + "\n\t6. " + smallBag1.openInventory() + "";

        assertEquals(oneBag, inventory.openInventory(), "The inventory should only have 1 bag equipped");

        inventory.addItem(smallBag1);
        inventory.useItem(0, 0, manager);
        assertEquals(twoBags, inventory.openInventory(), "The inventory should now have 2 bags equipped");

        inventory.addItem(smallBag2);
        inventory.useItem(0, 0, manager);
        assertEquals(threeBags, inventory.openInventory(), "The inventory should now have 3 bags equipped");

        inventory.addItem(smallBag3);
        inventory.useItem(0, 0, manager);
        assertEquals(fourBags, inventory.openInventory(), "The inventory should now have 4 bags equipped");

        inventory.addItem(smallBag4);
        inventory.useItem(0, 0, manager);
        assertEquals(fiveBags, inventory.openInventory(), "The inventory should now have 5 bags equipped");

        inventory.addItem(smallBag5);
        inventory.useItem(0, 0, manager);
        assertEquals(sixBags, inventory.openInventory(), "The inventory should now have 6 bags equipped");

        inventory.addItem(mediumBag);
        inventory.useItem(0, 0, manager);
        assertEquals(replaceBags, inventory.openInventory(), "The inventory should have swapped out the smaller bag with the bigger bag");        
    }

    @Test
    public void testUseItemBounds() {
        Bag starterBag = new Bag("Bag", "Bag", 5, 5);
        Inventory inventory = new MultiInventory(starterBag);
        StatsManager manager = new ComplexStatsManager(10, new BaseStat(10, 10));
        String oneBag = "Inventory: 0 gold\n\t1. " + starterBag.openInventory() + "\n\t2. None\n\t3. None\n\t4. None\n\t5. None\n\t6. None";

        inventory.useItem(-1, 0, manager);
        inventory.useItem(2, 0, manager);
        inventory.useItem(6, 0, manager);

        inventory.useItem(0, -1, manager);
        inventory.useItem(0, 1, manager);
        inventory.useItem(0, 100, manager);

        inventory.useItem(0, 0, manager);

        assertEquals(oneBag, inventory.openInventory(), "The inventory should of sucessfully managed to handle illegal access");
    }

    @Test
    public void testDestroyItem() {
        Bag starterBag = new Bag("Bag", "Bag", 5, 5);
        Inventory inventory = new MultiInventory(starterBag);
        StatsManager manager = new ComplexStatsManager(10, new BaseStat(10, 10));
        String oneBag = "Inventory: 0 gold\n\t1. " + starterBag.openInventory() + "\n\t2. None\n\t3. None\n\t4. None\n\t5. None\n\t6. None";
        Item weapon = new Weapon("Weapon", "Weapon", 30, 10);

        inventory.addItem(weapon);

        inventory.destroyItem(0, 0);
        assertEquals(oneBag, inventory.openInventory(), "The item should've been destroyed and removed from the inventory");
    }

    @Test
    public void testDestoryItemBounds() {
        Bag starterBag = new Bag("Bag", "Bag", 5, 5);
        Inventory inventory = new MultiInventory(starterBag);
        String oneBag = "Inventory: 0 gold\n\t1. " + starterBag.openInventory() + "\n\t2. None\n\t3. None\n\t4. None\n\t5. None\n\t6. None";

        inventory.destroyItem(-1, 0);
        inventory.destroyItem(2, 0);
        inventory.destroyItem(6, 0);

        inventory.destroyItem(0, -1);
        inventory.destroyItem(0, 1);
        inventory.destroyItem(0, 100);

        inventory.destroyItem(0, 0);

        assertEquals(oneBag, inventory.openInventory(), "The inventory should of sucessfully managed to handle illegal access");
    }

    @Test
    public void testIsFull() {
        Bag starterBag = new Bag("Bag", "Bag", 5, 5);
        Inventory inventory = new MultiInventory(starterBag);
        StatsManager manager = new ComplexStatsManager(10, new BaseStat(10, 10));
        
        Bag smallBag1 = new Bag("Bag", "Bag", 10, 5);
        Bag smallBag2 = new Bag("Bag", "Bag", 10, 5);
        Bag smallBag3 = new Bag("Bag", "Bag", 10, 5);
        Bag smallBag4 = new Bag("Bag", "Bag", 10, 5);
        Bag smallBag5 = new Bag("Bag", "Bag", 10, 5);
        ArrayList<Bag> bags = new ArrayList<>();
        bags.add(smallBag1);
        bags.add(smallBag2);
        bags.add(smallBag3);
        bags.add(smallBag4);
        bags.add(smallBag5);

        Item weapon = new Weapon("Weapon", "Weapon", 30, 10);

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 4; j++) {
                assertFalse(inventory.isFull(), "The inventory shouldn't be full yet");
                inventory.addItem(weapon);
            }

            inventory.addItem(bags.get(i));
            assertTrue(inventory.isFull(), "The inventory should now be full with bag" + i);
            inventory.useItem(i, 4, manager);
            inventory.addItem(weapon);
            assertFalse(inventory.isFull(), "The inventory shouldn't be full yet");
        }

        for(int j = 0; j < 4; j++) {
            assertFalse(inventory.isFull(), "The inventory shouldn't be full yet");
            inventory.addItem(weapon);
        }

        inventory.addItem(weapon);
        assertTrue(inventory.isFull(), "The inventory should now be full with bag");
    }

    @Test
    public void testCorpsifyBags() {
        Bag starterBag = new Bag("Bag", "Bag", 5, 5);
        Inventory inventory = new MultiInventory(starterBag);
        StatsManager manager = new ComplexStatsManager(10, new BaseStat(10, 10));
        
        Bag smallBag1 = new Bag("Bag", "Bag", 10, 3);
        Bag smallBag2 = new Bag("Bag", "Bag", 10, 3);
        Bag smallBag3 = new Bag("Bag", "Bag", 10, 3);
        Bag smallBag4 = new Bag("Bag", "Bag", 10, 3);
        Bag smallBag5 = new Bag("Bag", "Bag", 10, 3);
        ArrayList<Bag> bags = new ArrayList<>();
        bags.add(starterBag);
        bags.add(smallBag1);
        bags.add(smallBag2);
        bags.add(smallBag3);
        bags.add(smallBag4);
        bags.add(smallBag5);

        inventory.addItem(smallBag1);
        inventory.useItem(0, 0, manager);

        inventory.addItem(smallBag2);
        inventory.useItem(0, 0, manager);

        inventory.addItem(smallBag3);
        inventory.useItem(0, 0, manager);

        inventory.addItem(smallBag4);
        inventory.useItem(0, 0, manager);

        inventory.addItem(smallBag5);
        inventory.useItem(0, 0, manager);

        Item[] items = inventory.corpsify();

        for(int i = 0; i < items.length; i++) {
            assertEquals(bags.get(i), items[i], "The order of bags added to the corpse");
        }
    }

    @Test
    public void testCorpsifyEquipped() {
        Bag starterBag = new Bag("Bag", "Bag", 5, 2);
        Inventory inventory = new MultiInventory(starterBag);
        StatsManager manager = new ComplexStatsManager(10, new BaseStat(10, 10));
        Item weapon = new Weapon("Weapon", "Weapon", 30, 10);
        Item armor = new Armor("Armor", "Armor", 30, 10);

        ArrayList<Item> items = new ArrayList<>();
        items.add(starterBag);
        items.add(weapon);
        items.add(armor);
        
        inventory.addItem(weapon);
        inventory.addItem(armor);

        inventory.useItem(0, 0, manager);
        inventory.useItem(0, 1, manager);

        Item[] corpse = inventory.corpsify();

        assertEquals(items.size(), corpse.length, "The corpse should have the same amount of items that existed in the inventory");
        for(int i = 0; i < corpse.length; i++) {
            assertEquals(items.get(i), corpse[i], "The order of bags added to the corpse");
        }
    }

    @Test
    public void testCorpsify() {
        Bag starterBag = new Bag("Bag", "Bag", 5, 2);
        Inventory inventory = new MultiInventory(starterBag);
        Item weapon = new Weapon("Weapon", "Weapon", 30, 10);
        Item armor = new Armor("Armor", "Armor", 30, 10);

        ArrayList<Item> items = new ArrayList<>();
        items.add(weapon);
        items.add(armor);
        items.add(starterBag);
        

        inventory.addItem(weapon);
        inventory.addItem(armor);

        Item[] corpse = inventory.corpsify();

        assertEquals(items.size(), corpse.length, "The corpse should have the same amount of items that existed in the inventory");
        for(int i = 0; i < corpse.length; i++) {
            assertEquals(items.get(i), corpse[i], "The order of bags added to the corpse");
        }
    }
}
