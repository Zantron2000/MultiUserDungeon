package tests.model.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

import model.inventory.Inventory;
import model.inventory.Item;
import model.inventory.concrete_items.Armor;
import model.inventory.concrete_items.Bag;
import model.inventory.concrete_items.Buff;
import model.inventory.concrete_items.Food;
import model.inventory.concrete_items.Weapon;

@Testable
public class TestInventory {
    @Test
    public void testInventory() {
        String expectedString = "Inventory:\n" +
                                "\t0 gold\n\t0/3 items\n" +
                                "1) Tiny Bag: Total Gold Value: 30 | Bag Space: 0/3\n";

        Inventory inventory = new Inventory();
        assertFalse(inventory.isFull());
        assertEquals(expectedString, inventory.toString());
    }

    @Test
    public void testAddItem() {
        String expectedString = "Inventory:\n" +
                                "\t0 gold\n\t3/3 items\n" +
                                "1) Tiny Bag: Total Gold Value: 60 | Bag Space: 3/3\n" +
                                "\t1) Dagger: 10 attack - weapon, 10 gold\n" + 
                                "\t2) Dagger: 10 attack - weapon, 10 gold\n" +
                                "\t3) Dagger: 10 attack - weapon, 10 gold\n";

        Inventory inventory = new Inventory();
        Item item = new Weapon("Dagger", 10, 10);
        Item item2 = new Weapon("Dagger", 10, 10);
        Item item3 = new Weapon("Dagger", 10, 10);

        inventory.addItem(item);
        inventory.addItem(item2);
        inventory.addItem(item3);

        assertTrue(inventory.isFull());
        assertEquals(expectedString, inventory.toString());
    }

    @Test
    public void testAddItemFull() {
        String expectedString = "Inventory:\n" +
                                "\t0 gold\n\t3/3 items\n" +
                                "1) Tiny Bag: Total Gold Value: 60 | Bag Space: 3/3\n" +
                                "\t1) Dagger: 10 attack - weapon, 10 gold\n" + 
                                "\t2) Dagger: 10 attack - weapon, 10 gold\n" +
                                "\t3) Dagger: 10 attack - weapon, 10 gold\n";

        Inventory inventory = new Inventory();
        Item item = new Weapon("Dagger", 10, 10);
        Item item2 = new Weapon("Dagger", 10, 10);
        Item item3 = new Weapon("Dagger", 10, 10);
        Item item4 = new Weapon("Weapon", 10, 10);

        inventory.addItem(item);
        inventory.addItem(item2);
        inventory.addItem(item3);
        inventory.addItem(item4);

        assertTrue(inventory.isFull());
        assertEquals(expectedString, inventory.toString());
    }

    @Test
    public void testAddItemBag() {
        String expectedString = "Inventory:\n" +
                                "\t0 gold\n\t0/8 items\n" +
                                "1) Tiny Bag: Total Gold Value: 30 | Bag Space: 0/3\n" +
                                "2) Small Bag: Total Gold Value: 50 | Bag Space: 0/5\n";

        Inventory inventory = new Inventory();
        Item item = new Bag("Small Bag", 50, 5);

        inventory.addItem(item);

        assertFalse(inventory.isFull());
        assertEquals(expectedString, inventory.toString());
    }

    @Test
    public void testAddItems() {
        String expectedString = "Inventory:\n" +
                                "\t0 gold\n\t4/8 items\n" +
                                "1) Tiny Bag: Total Gold Value: 60 | Bag Space: 3/3\n" +
                                "\t1) Dagger: 10 attack - weapon, 10 gold\n" + 
                                "\t2) Dagger: 10 attack - weapon, 10 gold\n" +
                                "\t3) Dagger: 10 attack - weapon, 10 gold\n" + 
                                "2) Small Bag: Total Gold Value: 60 | Bag Space: 1/5\n" +
                                "\t1) Weapon: 10 attack - weapon, 10 gold\n";

        Inventory inventory = new Inventory();
        Item item = new Weapon("Dagger", 10, 10);
        Item item2 = new Weapon("Dagger", 10, 10);
        Item item3 = new Weapon("Dagger", 10, 10);
        Item item4 = new Weapon("Weapon", 10, 10);
        Item item5 = new Bag("Small Bag", 50, 5);

        inventory.addItem(item5);
        inventory.addItem(item);
        inventory.addItem(item2);
        inventory.addItem(item3);
        inventory.addItem(item4);

        assertFalse(inventory.isFull());
        assertEquals(expectedString, inventory.toString());

        inventory.addItem(item);
        inventory.addItem(item2);
        inventory.addItem(item3);
        inventory.addItem(item4);

        assertTrue(inventory.isFull());
    }

    @Test
    public void testAddGold() {
        String expectedString = "Inventory:\n" +
                                "\t100 gold\n\t0/3 items\n" +
                                "1) Tiny Bag: Total Gold Value: 30 | Bag Space: 0/3\n";

        Inventory inventory = new Inventory();
        inventory.addGold(100);

        assertEquals(expectedString, inventory.toString());
    }

    @Test
    public void testSpendGold() {
        String expectedString = "Inventory:\n" +
                                "\t100 gold\n\t0/3 items\n" +
                                "1) Tiny Bag: Total Gold Value: 30 | Bag Space: 0/3\n";

        Inventory inventory = new Inventory();
        inventory.addGold(200);

        assertTrue(inventory.spendGold(100));
        assertEquals(expectedString, inventory.toString());

        expectedString = "Inventory:\n" +
                                "\t100 gold\n\t0/3 items\n" +
                                "1) Tiny Bag: Total Gold Value: 30 | Bag Space: 0/3\n";

        assertFalse(inventory.spendGold(1000));
        assertEquals(expectedString, inventory.toString());
    }

    @Test
    public void testRemoveItems() {
        String expectedString = "Inventory:\n" +
                                "\t0 gold\n\t4/8 items\n" +
                                "1) Tiny Bag: Total Gold Value: 60 | Bag Space: 3/3\n" +
                                "\t1) Dagger: 10 attack - weapon, 10 gold\n" + 
                                "\t2) Dagger: 10 attack - weapon, 10 gold\n" +
                                "\t3) Dagger: 10 attack - weapon, 10 gold\n" + 
                                "2) Small Bag: Total Gold Value: 60 | Bag Space: 1/5\n" +
                                "\t1) Weapon: 10 attack - weapon, 10 gold\n";

        Inventory inventory = new Inventory();
        Item item = new Weapon("Dagger", 10, 10);
        Item item2 = new Weapon("Dagger", 10, 10);
        Item item3 = new Weapon("Dagger", 10, 10);
        Item item4 = new Weapon("Weapon", 10, 10);
        Item item5 = new Bag("Small Bag", 50, 5);

        inventory.addItem(item5);
        inventory.addItem(item);
        inventory.addItem(item2);
        inventory.addItem(item3);
        inventory.addItem(item4);
        inventory.addItem(item4);

        assertEquals(item4, inventory.removeItem(1, 1));
        assertEquals(expectedString, inventory.toString());

        inventory.addItem(item);
        inventory.addItem(item2);
        inventory.addItem(item3);
        inventory.addItem(item4);

        expectedString = "Inventory:\n" +
                                "\t0 gold\n\t7/8 items\n" +
                                "1) Tiny Bag: Total Gold Value: 60 | Bag Space: 3/3\n" +
                                "\t1) Dagger: 10 attack - weapon, 10 gold\n" + 
                                "\t2) Dagger: 10 attack - weapon, 10 gold\n" +
                                "\t3) Dagger: 10 attack - weapon, 10 gold\n" + 
                                "2) Small Bag: Total Gold Value: 90 | Bag Space: 4/5\n" +
                                "\t1) Weapon: 10 attack - weapon, 10 gold\n" + 
                                "\t2) Dagger: 10 attack - weapon, 10 gold\n" + 
                                "\t3) Dagger: 10 attack - weapon, 10 gold\n" + 
                                "\t5) Weapon: 10 attack - weapon, 10 gold\n";

        assertNull(inventory.removeItem(2, 0));
        assertEquals(item3, inventory.removeItem(1, 3));
        assertNull(inventory.removeItem(1, 3));
        assertEquals(expectedString, inventory.toString());
        assertFalse(inventory.isFull());
    }
    
    @Test
    public void testSwapBags() {
        String expectedString = "Inventory:\n" +
                                "\t0 gold\n\t2/25 items\n" +
                                "1) Large Bag: Total Gold Value: 130 | Bag Space: 2/10\n" +
                                "\t1) Large Bag: Gold Value: 50 | Bag Size: 10 items\n" + 
                                "\t2) Tiny Bag: Gold Value: 30 | Bag Size: 3 items\n" + 
                                "2) Small Bag: Total Gold Value: 50 | Bag Space: 0/3\n" +
                                "3) Small Bag: Total Gold Value: 50 | Bag Space: 0/3\n" + 
                                "4) Small Bag: Total Gold Value: 50 | Bag Space: 0/3\n" + 
                                "5) Small Bag: Total Gold Value: 50 | Bag Space: 0/3\n" + 
                                "6) Small Bag: Total Gold Value: 50 | Bag Space: 0/3\n";

        Inventory inventory = new Inventory();
        Bag item = new Bag("Small Bag", 50, 3);
        Bag item4 = new Bag("Small Bag", 50, 3);
        Bag item5 = new Bag("Small Bag", 50, 3);
        Bag item6 = new Bag("Small Bag", 50, 3);
        Bag item7 = new Bag("Small Bag", 50, 3);
        Bag item2 = new Bag("Large Bag", 50, 10);
        Bag item3 = new Bag("Large Bag", 50, 10);

        inventory.addItem(item);
        inventory.addItem(item4);
        inventory.addItem(item5);
        inventory.addItem(item6);
        inventory.addItem(item7);
        inventory.addItem(item2);
        inventory.addItem(item3);

        inventory.useItem(0, 0);

        assertEquals(expectedString, inventory.toString());        
    }

    @Test
    public void testSwapBagsInvalid() {
        Inventory inventory = new Inventory();
        Item item = new Bag("Small Bag", 50, 3);
        Item item4 = new Bag("Small Bag", 50, 3);
        Item item5 = new Bag("Small Bag", 50, 3);
        Item item6 = new Bag("Small Bag", 50, 3);
        Item item7 = new Bag("Small Bag", 50, 3);
        Item item2 = new Bag("Large Bag", 50, 10);
        Item item3 = new Weapon("Dagger", 10, 10);

        String expectedString = "Inventory:\n" +
                                "\t0 gold\n\t3/18 items\n" +
                                "1) Tiny Bag: Total Gold Value: 140 | Bag Space: 3/3\n" +
                                "\t1) Large Bag: Gold Value: 50 | Bag Size: 10 items\n" + 
                                "\t2) Large Bag: Gold Value: 50 | Bag Size: 10 items\n" +
                                "\t3) " + item3.toString() + "\n" +  
                                "2) Small Bag: Total Gold Value: 50 | Bag Space: 0/3\n" +
                                "3) Small Bag: Total Gold Value: 50 | Bag Space: 0/3\n" + 
                                "4) Small Bag: Total Gold Value: 50 | Bag Space: 0/3\n" + 
                                "5) Small Bag: Total Gold Value: 50 | Bag Space: 0/3\n" + 
                                "6) Small Bag: Total Gold Value: 50 | Bag Space: 0/3\n";

        

        inventory.addItem(item);
        inventory.addItem(item4);
        inventory.addItem(item5);
        inventory.addItem(item6);
        inventory.addItem(item7);
        inventory.addItem(item2);
        inventory.addItem(item2);
        inventory.addItem(item3);
        
        inventory.useItem(7, 1);
        assertEquals(expectedString, inventory.toString());    

        inventory.useItem(1, 7);
        assertEquals(expectedString, inventory.toString());
        
        inventory.useItem(1, 4);
        assertEquals(expectedString, inventory.toString());
    }

    @Test
    public void testUseItem() {
        String expectedString = "Inventory:\n" +
                                "\t0 gold\n\t1/30 items\n" +
                                "1) Bag: Total Gold Value: 40 | Bag Space: 1/5\n" +
                                "\t1) Tiny Bag: Gold Value: 30 | Bag Size: 3 items\n" +
                                "2) Small Bag: Total Gold Value: 50 | Bag Space: 0/5\n" + 
                                "3) Small Bag: Total Gold Value: 50 | Bag Space: 0/5\n" + 
                                "4) Small Bag: Total Gold Value: 50 | Bag Space: 0/5\n" + 
                                "5) Small Bag: Total Gold Value: 50 | Bag Space: 0/5\n" + 
                                "6) Small Bag: Total Gold Value: 50 | Bag Space: 0/5\n";

        Inventory inventory = new Inventory();

        Item weapon = new Weapon("Weapon", 10, 10);
        Item armor = new Armor("Armor", 10, 10);
        Item bag = new Bag("Bag", 10, 5);
        Item food = new Food("Food", 10, 10);
        Item buff = new Buff("Buff", 10, 10, 10, 10);
        
        Item bag1 = new Bag("Small Bag", 50, 5);
        Item bag2 = new Bag("Small Bag", 50, 5);
        Item bag3 = new Bag("Small Bag", 50, 5);
        Item bag4 = new Bag("Small Bag", 50, 5);
        Item bag5 = new Bag("Small Bag", 50, 5);

        inventory.addItem(bag1);
        inventory.addItem(bag2);
        inventory.addItem(bag3);
        inventory.addItem(bag4);
        inventory.addItem(bag5);

        inventory.addItem(weapon);
        inventory.addItem(armor);
        inventory.addItem(bag);
        inventory.addItem(food);
        inventory.addItem(buff);
        Item weaponBuffs = inventory.useItem(0, 0);
        Item armorBuffs = inventory.useItem(0, 1);
        Item bagBuffs = inventory.useItem(0, 2);
        Item foodBuffs = inventory.useItem(1, 0);
        Item buffBuffs = inventory.useItem(1, 1);
        
        assertEquals(weapon, weaponBuffs);
        assertEquals(armor, armorBuffs);
        assertEquals(bag, bagBuffs);
        assertEquals(food, foodBuffs);
        assertEquals(buff, buffBuffs);
        assertEquals(expectedString, inventory.toString());
    }

    @Test
    public void testUseSmallerBag() {
        String expectedString = "Inventory:\n" +
                                "\t0 gold\n\t1/28 items\n" +
                                "1) Tiny Bag: Total Gold Value: 40 | Bag Space: 1/3\n" +
                                "\t1) Bag: Gold Value: 10 | Bag Size: 1 items\n" +
                                "2) Small Bag: Total Gold Value: 50 | Bag Space: 0/5\n" + 
                                "3) Small Bag: Total Gold Value: 50 | Bag Space: 0/5\n" + 
                                "4) Small Bag: Total Gold Value: 50 | Bag Space: 0/5\n" + 
                                "5) Small Bag: Total Gold Value: 50 | Bag Space: 0/5\n" + 
                                "6) Small Bag: Total Gold Value: 50 | Bag Space: 0/5\n";

        Inventory inventory = new Inventory();

        Item bag = new Bag("Bag", 10, 1);
        Item bag1 = new Bag("Small Bag", 50, 5);
        Item bag2 = new Bag("Small Bag", 50, 5);
        Item bag3 = new Bag("Small Bag", 50, 5);
        Item bag4 = new Bag("Small Bag", 50, 5);
        Item bag5 = new Bag("Small Bag", 50, 5);

        inventory.addItem(bag1);
        inventory.addItem(bag2);
        inventory.addItem(bag3);
        inventory.addItem(bag4);
        inventory.addItem(bag5);
        inventory.addItem(bag);

        Item item = inventory.useItem(0, 0);

        assertEquals(bag, item);
        assertEquals(expectedString, inventory.toString());
    }

    @Test
    public void testUseReequip() {
        String expectedString = "Inventory:\n" +
                                "\t0 gold\n\t2/8 items\n" +
                                "1) Tiny Bag: Total Gold Value: 50 | Bag Space: 2/3\n" +
                                "\t1) Weapon: 10 attack - weapon, 10 gold\n" +
                                "\t2) Armor: 10 defense - armor, 10 gold\n" +
                                "2) Small Bag: Total Gold Value: 50 | Bag Space: 0/5\n";

        Inventory inventory = new Inventory();

        Item weapon = new Weapon("Weapon", 10, 10);
        Item armor = new Armor("Armor", 10, 10);
        Item weapon1 = new Weapon("Weapon 2", 10, 10);
        Item armor1 = new Armor("Armor 2", 10, 10);
        
        Item bag1 = new Bag("Small Bag", 50, 5);

        inventory.addItem(bag1);
        inventory.addItem(weapon);
        inventory.addItem(armor);
        inventory.addItem(weapon1);
        inventory.addItem(armor1);
        
        inventory.useItem(0, 0);
        inventory.useItem(0, 1);
        Item weaponBuffs = inventory.useItem(0, 2);
        Item armorBuffs = inventory.useItem(1, 0);
        
        
        assertEquals(weapon1, weaponBuffs);
        assertEquals(armor1, armorBuffs);
        assertEquals(expectedString, inventory.toString());
    }    

    
}
