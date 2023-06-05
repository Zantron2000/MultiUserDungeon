package model.character.characters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import controller.gameGenerators.PlayerGenerator;
import model.character.Character;
import model.character.inventory.Inventory;
import model.character.inventory.Item;
import model.character.inventory.inventories.SingleInventory;
import model.character.inventory.items.Armor;
import model.character.inventory.items.Buff;
import model.character.inventory.items.Weapon;
import model.character.stats.StatsManager;
import model.character.stats.stat.BaseStat;
import model.character.stats.statsManager.SimpleStatsManager;

@Testable
public class TestCharacters {
    private static NonPlayerCharacter generateEnemy() {
        Inventory inventory = new SingleInventory(0);
        StatsManager manager = new SimpleStatsManager(10, new BaseStat(10, 10));

        return new DiurnalNPC(inventory, manager, null, "null", "null");
    }

    @Test
    public void testProcessTurn() {
        Character character = PlayerGenerator.generatePlayer("John", "A Player");
        Item buff = new Buff("Buff", "A buff", 10, 10, 10, 2);
        
        character.addItem(buff);
        character.useItem(0, 0);

        assertEquals(20, character.getDamage(), "The stats manager for the character didn't properly handle the buff");
        assertEquals(10, character.getArmor(), "The stats manager for the character didn't properly handle the buff");
        
        character.processTurn();

        assertEquals(20, character.getDamage(), "The stats manager for the character didn't properly handle the buff");
        assertEquals(10, character.getArmor(), "The stats manager for the character didn't properly handle the buff");
        
        character.processTurn();

        assertEquals(10, character.getDamage(), "The stats manager for the character didn't properly handle the buff");
        assertEquals(0, character.getArmor(), "The stats manager for the character didn't properly handle the buff");
    }

    @Test
    public void testGetDamage() {
        Character character = PlayerGenerator.generatePlayer("John", "Doe");
        assertEquals(10, character.getDamage(), "The character returned the incorrect amount of damage");
    }

    @Test
    public void testGetArmor() {
        Character character = PlayerGenerator.generatePlayer("John", "Doe");
        assertEquals(0, character.getArmor(), "The character returned the incorrect amount of damage");
    }

    @Test
    public void testTakeDamage() {
        Character noArmorCharacter = PlayerGenerator.generatePlayer("John", "Doe");

        noArmorCharacter.takeDamage(99);
        assertFalse(noArmorCharacter.isDead(), "The player should not of taken enough damage to die");
        noArmorCharacter.takeDamage(1);
        assertTrue(noArmorCharacter.isDead(), "The player should of taken enough damage to die");
        
        Character armoredCharacter = PlayerGenerator.generatePlayer("John", "Hoe");
        Item buff = new Buff("Buff", "A buff", 10, 10, 10, 2);
        
        armoredCharacter.addItem(buff);
        armoredCharacter.useItem(0, 0);

        armoredCharacter.takeDamage(10);
        assertFalse(armoredCharacter.isDead(), "The player should not of taken enough damage to die");
        armoredCharacter.takeDamage(109);
        assertTrue(armoredCharacter.isDead(), "The player should of taken enough damage to die");
    }

    @Test
    public void testIsDead() {
        Character noArmorCharacter = PlayerGenerator.generatePlayer("John", "Doe");

        noArmorCharacter.takeDamage(99);
        assertFalse(noArmorCharacter.isDead(), "The player should not be considered dead");
        noArmorCharacter.takeDamage(1);
        assertTrue(noArmorCharacter.isDead(), "The player should be considered dead");
    }

    @Test
    public void testAddItem() {
        Character character = TestCharacters.generateEnemy();
        Item weapon = new Weapon("null", "null", 0, 10);
        Item armor = new Armor("null", "null", 0, 10);
        String inventory = "Inventory: 0 gold\n\t1. " + weapon.toString();

        assertTrue(character.addItem(weapon), "The weapon should've been added to the inventory");
        assertFalse(character.addItem(armor), "The armor shouldn't of been added to the inventory");
        assertEquals(inventory, character.openInventory(), "The inventory of the character is wrong");
    }

    @Test
    public void testDestroyItem() {
        Character character = TestCharacters.generateEnemy();
        Item weapon = new Weapon("null", "null", 0, 10);
        Item armor = new Armor("null", "null", 0, 10);
        String weaponinventory = "Inventory: 0 gold\n\t1. " + weapon.toString();

        assertTrue(character.addItem(armor), "The weapon should've been added to the inventory");
        character.destroyItem(0, 0);
        assertTrue(character.addItem(weapon), "The armor shouldn't of been added to the inventory");
        assertEquals(weaponinventory, character.openInventory(), "The inventory of the character is wrong");
    }

    @Test
    public void testAddGold() {
        Character character = TestCharacters.generateEnemy();
        String inventory100 = "Inventory: 100 gold\n\t1. None";
        String inventory300 = "Inventory: 300 gold\n\t1. None";

        character.addGold(100);
        assertEquals(inventory100, character.openInventory(), "The added amount of gold is wrong");
        character.addGold(200);
        assertEquals(inventory300, character.openInventory(), "The added amount of gold is wrong");
    }

    
}
