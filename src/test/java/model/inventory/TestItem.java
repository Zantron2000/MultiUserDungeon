package model.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.util.HashMap;
import model.inventory.concrete_items.*;

@Testable
public class TestItem {
    private static String weaponType = "weapon";
    private static String armorType = "armor";
    private static String bagType = "bag";
    private static String foodType = "food";
    private static String buffType = "buff";

    @Test
    public void testTypes() {
        Item weapon = new Weapon("Weapon", 0, 0);
        Item armor = new Armor("Armor", 0, 0);
        Item bag = new Bag("Bag", 0, 0);
        Item food = new Food("Food", 0, 0);
        Item buff = new Buff("Buff", 0, 0, 0, 0);

        assertEquals(weaponType, weapon.getType(), "Weapon type is incorrect");
        assertEquals(armorType, armor.getType(), "Armor type is incorrect");
        assertEquals(bagType, bag.getType(), "Bag type is incorrect");
        assertEquals(foodType, food.getType(), "Food type is incorrect");
        assertEquals(buffType, buff.getType(), "Buff type is incorrect");
    }

    @Test
    public void testEquippables() {
        Item weapon = new Weapon("Weapon", 0, 0);
        Item armor = new Armor("Armor", 0, 0);
        Item bag = new Bag("Bag", 0, 0);
        Item food = new Food("Food", 0, 0);
        Item buff = new Buff("Buff", 0, 0, 0, 0);

        assertTrue(weapon.isEquippable(), "Weapon should be equippable");
        assertTrue(armor.isEquippable(), "Armor should be equippable");
        assertTrue(bag.isEquippable(), "Bag should be equippable");
        assertFalse(food.isEquippable(), "Food shouldn't be equippable");
        assertFalse(buff.isEquippable(), "Buff shouldn't be equippable");
    }

    @Test
    public void testToString() {
        Item weapon = new Weapon("Weapon", 10, 10);
        Item armor = new Armor("Armor", 10, 10);
        Item bag = new Bag("Bag", 10, 2);
        Item food = new Food("Food", 10, 10);
        Item buff = new Buff("Buff", 10, 10, 10, 10);

        assertEquals("Weapon: 10 attack - weapon, 10 gold", weapon.toString(), "Weapon toString incorrect");
        assertEquals("Armor: 10 defense - armor, 10 gold", armor.toString(), "Armor toString incorrect");
        assertEquals("Bag: Gold Value: 10 | Bag Size: 2 items",  bag.toString(), "Empty bag toString incorrect");
        assertEquals("Food: 10 health - food, 10 gold", food.toString(), "Food toString incorrect");
        assertEquals("Buff: 10 attack, 10 defense, 10 health - buff, 10 gold", buff.toString(), "Buff toString incorrect");
    }

    @Test
    public void testGetBuffs() {
        Item weapon = new Weapon("Weapon", 10, 10);
        Item bag = new Bag("Bag", 10, 2);
        Item buff = new Buff("Buff", 10, 10, 10, 10);

        HashMap<String, Integer> weaponBuffs = weapon.getBuffs();
        HashMap<String, Integer> bagBuffs = bag.getBuffs();
        HashMap<String, Integer> buffBuffs = buff.getBuffs();

        assertEquals(weaponBuffs.get(Item.ATTACK_KEY), 10);
        assertEquals(weaponBuffs.get(Item.DEFENSE_KEY), 0);
        assertEquals(weaponBuffs.get(Item.HEALTH_KEY), 0);

        assertNull(bagBuffs);

        assertEquals(buffBuffs.get(Item.ATTACK_KEY), 10);
        assertEquals(buffBuffs.get(Item.DEFENSE_KEY), 10);
        assertEquals(buffBuffs.get(Item.HEALTH_KEY), 10);
    }

    @Test
    public void testBagAddItem() {
        Item weapon = new Weapon("Weapon", 10, 10);
        Bag bag = new Bag("Bag", 10, 2);
        Item buff = new Buff("Buff", 10, 10, 10, 10);

        bag.addItem(weapon);
        bag.addItem(buff);

        String expectedString = "Bag: Total Gold Value: 30 | Bag Space: 2/2\n\t1) " + weapon.toString() + "\n\t2) " + buff.toString() + "\n";
        
        assertTrue(bag.isFull(), "Bag should be full");
        assertEquals(expectedString, bag.getContents(), "Full bag toString incorrect");
    }

    @Test
    public void testBagRemoveItem() {
        Item weapon = new Weapon("Weapon", 10, 10);
        Bag bag = new Bag("Bag", 10, 2);
        Item buff = new Buff("Buff", 10, 10, 10, 10);

        bag.addItem(weapon);
        bag.addItem(buff);
        bag.remove(0);

        String expectedString = "Bag: Total Gold Value: 20 | Bag Space: 1/2\n\t2) " + buff.toString() + "\n";

        assertFalse(bag.isFull(), "Bag shouldn't be full");
        assertEquals(expectedString, bag.getContents(), "Half filled bag toString incorrect");
    }
}
