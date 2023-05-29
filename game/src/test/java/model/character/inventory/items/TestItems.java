package model.character.inventory.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import model.character.inventory.Item;

@Testable
public class TestItems {

    @Test
    public void testWeaponItem() {
        Weapon weapon = new Weapon("Dagger", "A dagger", 30, 30);

        assertEquals("Dagger", weapon.getName(), "The names of the items are different");
        assertEquals("A dagger", weapon.getDescription(), "The descriptions of the items are different");
        assertEquals(30, weapon.getValue(), "The expected value is different than the expected");
        
        String weaponString = weapon.toString();
        String weaponExpected = "(Weapon) Dagger: +30 attack";

        assertEquals(weaponExpected, weaponString, "The toString value of the weapon is off");

        StatsRecord weaponRecord = weapon.createRecord();

        assertEquals(30, weaponRecord.getAttack(), "The attack of the record is off");
        assertEquals(0, weaponRecord.getDefense(), "The defense of the record is off");
        assertEquals(0, weaponRecord.getHealth(), "The health of the record is off");
        assertEquals(0, weaponRecord.getTurns(), "The turns of the record is off");
        assertEquals(true, weaponRecord.isEquippable(), "The equippability of the record is off");
        assertEquals(ItemType.WEAPON, weaponRecord.getType(), "The type of the record is off");
    }

    @Test
    public void testFoodItem() {
        Food food = new Food("Apple", "An apple", 30, 30);

        assertEquals("Apple", food.getName(), "The names of the items are different");
        assertEquals("An apple", food.getDescription(), "The descriptions of the items are different");
        assertEquals(30, food.getValue(), "The expected value is different than the expected");
        
        String foodString = food.toString();
        String foodExpected = "(Food) Apple: +30 health";

        assertEquals(foodExpected, foodString, "The toString value of the food is off");

        StatsRecord foodRecord = food.createRecord();

        assertEquals(0, foodRecord.getAttack(), "The attack of the record is off");
        assertEquals(0, foodRecord.getDefense(), "The defense of the record is off");
        assertEquals(30, foodRecord.getHealth(), "The health of the record is off");
        assertEquals(0, foodRecord.getTurns(), "The turns of the record is off");
        assertEquals(false, foodRecord.isEquippable(), "The equippability of the record is off");
        assertEquals(ItemType.FOOD, foodRecord.getType(), "The type of the record is off");
    }

    @Test
    public void testArmorItem() {
        Armor armor = new Armor("Chainmail", "Chainmail Armor", 30, 30);

        assertEquals("Chainmail", armor.getName(), "The names of the items are different");
        assertEquals("Chainmail Armor", armor.getDescription(), "The descriptions of the items are different");
        assertEquals(30, armor.getValue(), "The expected value is different than the expected");
        
        String armorString = armor.toString();
        String armorExpected = "(Armor) Chainmail: +30 defense";

        assertEquals(armorExpected, armorString, "The toString value of the armor is off");

        StatsRecord armorRecord = armor.createRecord();

        assertEquals(0, armorRecord.getAttack(), "The attack of the record is off");
        assertEquals(30, armorRecord.getDefense(), "The defense of the record is off");
        assertEquals(0, armorRecord.getHealth(), "The health of the record is off");
        assertEquals(0, armorRecord.getTurns(), "The turns of the record is off");
        assertEquals(true, armorRecord.isEquippable(), "The equippability of the record is off");
        assertEquals(ItemType.ARMOR, armorRecord.getType(), "The type of the record is off");
    }

    @Test
    public void testBuffItem() {
        Buff buff = new Buff("Scroll of Undying", "The undying scroll", 30, 30, 30, 30);

        assertEquals("Scroll of Undying", buff.getName(), "The names of the items are different");
        assertEquals("The undying scroll", buff.getDescription(), "The descriptions of the items are different");
        assertEquals(30, buff.getValue(), "The expected value is different than the expected");
        
        String buffString = buff.toString();
        String buffExpected = "(Buff) Scroll of Undying: +30 attack, +30 defense for 30 turns";

        assertEquals(buffExpected, buffString, "The toString value of the buff is off");

        StatsRecord buffRecord = buff.createRecord();

        assertEquals(30, buffRecord.getAttack(), "The attack of the record is off");
        assertEquals(30, buffRecord.getDefense(), "The defense of the record is off");
        assertEquals(0, buffRecord.getHealth(), "The health of the record is off");
        assertEquals(30, buffRecord.getTurns(), "The turns of the record is off");
        assertEquals(false, buffRecord.isEquippable(), "The equippability of the record is off");
        assertEquals(ItemType.BUFF, buffRecord.getType(), "The type of the record is off");
    }

    @Test
    public void testBagItem() {
        Bag bag = new Bag("Medium Bag", "A Medium Bag", 30, 5);

        assertEquals("Medium Bag", bag.getName(), "The names of the items are different");
        assertEquals("A Medium Bag", bag.getDescription(), "The descriptions of the items are different");
        assertEquals(30, bag.getValue(), "The expected value is different than the expected");
        
        String bagString = bag.toString();
        String bagExpected = "(Bag) Medium Bag: 5 slots";

        assertEquals(bagExpected, bagString, "The toString value of the buff is off");

        StatsRecord bagRecord = bag.createRecord();

        assertNull(bagRecord, "Bag items shouldn't produce a record");
    }

    @Test
    public void testBag() {
        Bag bag = new Bag("Medium Bag", "A Medium Bag", 30, 5);
        Bag biggerBag = new Bag("Medium Bag", "A Medium Bag", 30, 6);
        
        Item weapon = new Weapon("Dagger", "A dagger", 30, 30);
        Item armor = new Armor("Chainmail", "Chainmail Armor", 30, 30);

        String emptyOutput = "Medium Bag 0/5 capacity\n\t\t1. None\n\t\t2. None\n\t\t3. None\n\t\t4. None\n\t\t5. None\n";
        String oneItemOutput = "Medium Bag 1/5 capacity\n\t\t1. " + weapon.toString() + "\n\t\t2. None\n\t\t3. None\n\t\t4. None\n\t\t5. None\n";
        String insertItemOutput = "Medium Bag 2/5 capacity\n\t\t1. " + weapon.toString() + "\n\t\t2. None\n\t\t3. None\n\t\t4. " + armor.toString() + "\n\t\t5. None\n";
        String fullOutput = "Medium Bag 5/5 capacity\n\t\t1. " + weapon.toString() + "\n\t\t2. " + weapon.toString() + "\n\t\t3. " + weapon.toString() + "\n\t\t4. " + armor.toString() + "\n\t\t5. " + weapon.toString() + "\n";
        String removeItemOutput = "Medium Bag 4/5 capacity\n\t\t1. " + weapon.toString() + "\n\t\t2. " + weapon.toString() + "\n\t\t3. " + weapon.toString() + "\n\t\t4. None\n\t\t5. " + weapon.toString() + "\n";
        String biggerBagTransfer = "Medium Bag 4/6 capacity\n\t\t1. " + weapon.toString() + "\n\t\t2. " + weapon.toString() + "\n\t\t3. " + weapon.toString() + "\n\t\t4. " + weapon.toString() + "\n\t\t5. None\n\t\t6. None\n";

        assertEquals(emptyOutput, bag.openInventory(), "The empty bag doesn't match the inventory output");
        assertFalse(bag.isFull(), "The bag shouldn't be full");

        bag.addItem(weapon);
        assertEquals(oneItemOutput, bag.openInventory(), "The 1 item bag doesn't match the inventory output");
        assertFalse(bag.isFull(), "The bag shouldn't be full");
        
        bag.addItem(armor, 3);
        assertEquals(insertItemOutput, bag.openInventory(), "The 2 item bag doesn't match the inventory output");
        assertFalse(bag.isFull(), "The bag shouldn't be full");

        bag.addItem(weapon);
        bag.addItem(weapon);
        bag.addItem(weapon);
        assertEquals(fullOutput, bag.openInventory(), "The full bag doesn't match the inventory output");
        assertTrue(bag.isFull(), "The bag should be full");

        Item removedItem = bag.removeItem(3);
        assertEquals(removeItemOutput, bag.openInventory(), "The 4 item bag doesn't match the inventory output");
        assertFalse(bag.isFull(), "The bag shouldn't be full");
        assertEquals(armor, removedItem, "The two items should be equal if removed from bag");

        assertNull(bag.removeItem(100), "If the index is out of bounds, the bag should return null");

        assertEquals(0, bag.compareTo(bag), "The value should represent how much bigger the current bag is to the argument bag");
        assertEquals(-1, bag.compareTo(biggerBag), "The value should represent how much bigger the current bag is to the argument bag");
        assertEquals(1, biggerBag.compareTo(bag), "The value should represent how much bigger the current bag is to the argument bag");
 
        bag.transferItems(biggerBag);
        assertEquals(biggerBagTransfer, biggerBag.openInventory(), "The transfer of items doesn't match the expected output for the bag transfered into");
        assertEquals(emptyOutput, bag.openInventory(), "The transfer of items doesn't match the expected output for the bag transfered out of");

        Item[] items = biggerBag.corpsify();
        assertEquals(4, items.length, "The created array doesn't match the expected length");
        for(int i = 0; i < items.length; i++) {
            assertEquals(items[i], weapon, "The item in the corpse doesn't match the original content");
        }
    }
}